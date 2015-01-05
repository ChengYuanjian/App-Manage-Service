package com.cyj.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.base.exception.BizLogicException;
import com.cyj.bo.Msg;
import com.cyj.bo.RetInfo;
import com.cyj.dao.MsgDao;
import com.cyj.util.JSONUtil;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/msg")
public class MsgController {

	private LoggerUtil logger = new LoggerUtil(MsgController.class);

	@Autowired
	private MsgDao msgDao;

	@RequestMapping(value = "/c", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addMsg(@RequestBody
	Map<String, Object> map) {
		try {
			Msg msg = (Msg) JSONUtil.map2bean(map, Msg.class);
			if (msgDao.addMsg(msg))
				return new RetInfo(1);
			else
				return new RetInfo(-31000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/u", method = RequestMethod.PUT)
	public @ResponseBody
	RetInfo updateMsg(@RequestBody
	Map<String, Object> map) {
		try {
			Msg msg = (Msg) JSONUtil.map2bean(map, Msg.class);
			if (msgDao.updateMsg(msg) > 0)
				return new RetInfo(1);
			else
				return new RetInfo(-32000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/r/{msgid}", method = RequestMethod.GET)
	public @ResponseBody
	Object getMsg(@PathVariable
	long msgid) {
		try {
			Msg msg = msgDao.getMsgsById(msgid);
			if (null == msg)
				return new RetInfo(-30000);
			return msg;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/r/{phone}/{from}/{to}", method = RequestMethod.GET)
	public @ResponseBody
	Object getMsgs(@PathVariable
	long phone, @PathVariable
	int from, @PathVariable
	int to) {
		try {
			if (to < from)
				throw new BizLogicException(-40001);

			List<Msg> msgs = msgDao.getMsgsByPhone(phone, from, to);

			if (CollectionUtils.isEmpty(msgs))
				return new RetInfo(-30000);
			return msgs;
		} catch (BizLogicException e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(e.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/d/{msgid}", method = RequestMethod.DELETE)
	public @ResponseBody
	RetInfo deleteMsg(@PathVariable
	long msgid) {
		try {
			if (msgDao.deleteMsg(msgid) > 0)
				return new RetInfo(1);
			else
				return new RetInfo(-33000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	public MsgDao getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(MsgDao msgDao) {
		this.msgDao = msgDao;
	}

}
