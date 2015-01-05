package com.cyj.util;

import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.cyj.base.exception.SystemException;

public class SecurityUtil {

	private static final String DES = "DES";

	private static LoggerUtil logger = new LoggerUtil(SecurityUtil.class);

	private static final String key = "_CYJ_";

	public static Map<String, String> generate() {
		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	public static String getAccessToken(String appid, String secret,
			String signature) throws SystemException {
		if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret)
				|| StringUtils.isEmpty(signature))
			return "";
		else {
			return encode(appid
					+ "|"
					+ secret
					+ "|"
					+ signature
					+ "|"
					+ (System.currentTimeMillis() + Long
							.parseLong(PropertiesUtil
									.getProperties("token.time.limit"))));
		}
	}

	/**
	 * md5 encoder
	 * 
	 * @param source
	 * @return
	 * @throws SystemException
	 */
	public static String md5(String source) throws SystemException {
		if (StringUtils.isEmpty(source)) {
			return "";
		} else {
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
				logger.error(-10001, ex);
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder
						.encode(md5.digest(source.getBytes("UTF-8")));
			} catch (Exception ex) {
				logger.error(PropertiesUtil.getProperties("-10002") + source,
						ex);
				throw new SystemException(PropertiesUtil
						.getProperties("-10002")
						+ source, ex);
			}
			return new StringBuilder(value).reverse().toString();
		}
	}

	public static String base64encode(String strMing) {
		BASE64Encoder base64en = new BASE64Encoder();
		return base64en.encode(strMing.getBytes());
	}

	public static String base64decode(String strMi) throws SystemException {
		BASE64Decoder base64De = new BASE64Decoder();
		try {
			return new String(base64De.decodeBuffer(strMi));
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * base64 encoder with a private key
	 * 
	 * @param strMing
	 * @return
	 * @throws SystemException
	 */
	public static String encode(String strMing) throws SystemException {
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			return base64en.encode(getEncCode(strMing.getBytes("UTF8")));
		} catch (Exception e) {
			throw new SystemException(PropertiesUtil.getProperties("-10003")
					+ strMing, e);
		}
	}

	/**
	 * base64 decoder with a private key
	 * 
	 * @param strMi
	 * @return
	 * @throws SystemException
	 */
	public static String decode(String strMi) throws SystemException {
		BASE64Decoder base64De = new BASE64Decoder();
		try {
			return new String(getDesCode(base64De.decodeBuffer(strMi)), "UTF8");
		} catch (Exception e) {
			throw new SystemException(PropertiesUtil.getProperties("-10002")
					+ strMi, e);
		}
	}

	private static byte[] getEncCode(byte[] byteS) throws SystemException {
		byte[] byteFina = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return byteFina;
	}

	private static byte[] getDesCode(byte[] byteD) throws SystemException {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return byteFina;
	}

	public static Key generateKey(String strKey) throws SystemException {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance(DES);
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static boolean validate(String signature, String timestamp,
			String nonce) throws SystemException {
		String token = base64decode(PropertiesUtil
				.getProperties("token.identity"));
		String[] arrTmp = { token, timestamp, nonce };
		Arrays.sort(arrTmp);
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arrTmp.length; i++) {
			sb.append(arrTmp[i]);
		}
		String expectedSignature;
		try {
			expectedSignature = encrypt(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException(e);
		}

		if (expectedSignature.equals(signature)) {
			return true;
		}
		return false;
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder sbDes = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0xFF));
			if (tmp.length() == 1) {
				sbDes.append("0");
			}
			sbDes.append(tmp);
		}
		return sbDes.toString();
	}

	private static String encrypt(String strSrc)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		digest.update(bt);
		strDes = byte2hex(digest.digest());
		return strDes;
	}

}
