package com.cyj.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cyj.bo.RetInfo;
import com.cyj.util.LoggerUtil;
import com.cyj.util.PropertiesUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static LoggerUtil logger = new LoggerUtil(UploadController.class);

	@RequestMapping(value = "/single", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo uploadSingle(MultipartFile file, HttpServletRequest request) {
		if (file == null || file.isEmpty())
			return new RetInfo(-30002);

		if (file.getSize() > Long.parseLong(PropertiesUtil
				.getProperties("file.maxsize")))
			return new RetInfo(-30001);

		if (!isAllowedFile(file.getOriginalFilename()))
			return new RetInfo(-30003);

		String user = request.getParameter(PropertiesUtil
				.getProperties("login.user"));// get user from post data

		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/upload/" + user;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		String uploadFile = uploadPath + "/" + new Date().getTime()
				+ file.getOriginalFilename();

		File newFile = new File(uploadFile);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
		} catch (Exception e) {
			logger.error(PropertiesUtil.getProperties("-30004") + ":"
					+ file.getOriginalFilename(), e);
			return new RetInfo(-30004);
		}
		return new RetInfo(1);
	}

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo uploadBatch(@RequestParam
	MultipartFile[] files, HttpServletRequest request) {
		for (MultipartFile file : files) {
			if (file == null || file.isEmpty())
				return new RetInfo(-30002);

			long filesize = Long.parseLong(PropertiesUtil
					.getProperties("file.maxsize"));
			if (file.getSize() > filesize)
				return new RetInfo(-30001, "The size of the file "
						+ file.getName() + " cannot beyond " + filesize / 1024
						+ "KB");

			if (!isAllowedFile(file.getOriginalFilename()))
				return new RetInfo(-30003);

			String user = request.getParameter(PropertiesUtil
					.getProperties("login.user"));// get user from post data

			String uploadPath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/upload/" + user;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();

			String uploadFile = uploadPath + "/" + new Date().getTime()
					+ file.getOriginalFilename();

			File newFile = new File(uploadFile);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
			} catch (Exception e) {
				logger.error(PropertiesUtil.getProperties("-30004") + ":"
						+ file.getOriginalFilename(), e);
				return new RetInfo(-30004);
			}
		}
		return new RetInfo(1);
	}

	private boolean isAllowedFile(String name) {
		if (StringUtils.isEmpty(name))
			return false;

		for (String s : PropertiesUtil.getProperties("file.extend.name").split(
				",")) {
			if (name.endsWith(s))
				return true;
		}
		return false;
	}

}
