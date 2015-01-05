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

import com.cyj.bo.Pet;
import com.cyj.bo.RetInfo;
import com.cyj.dao.PetDao;
import com.cyj.util.JSONUtil;
import com.cyj.util.LoggerUtil;

@Controller
@RequestMapping("/pet")
public class PetController {
	private LoggerUtil logger = new LoggerUtil(PetController.class);

	@Autowired
	private PetDao petDao;

	@RequestMapping(value = "/query/{phone}", method = RequestMethod.GET)
	public @ResponseBody
	Object getPet(@PathVariable
	long phone) {
		try {
			List<Pet> pets = petDao.queryPetByPhone(phone);
			if (CollectionUtils.isEmpty(pets))
				return new RetInfo(-30000);
			return pets;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addPet(@RequestBody
	Map<String, Object> map) {
		try {
			Pet pet = (Pet) JSONUtil.map2bean(map, Pet.class);
			if (petDao.addPet(pet))
				return new RetInfo(1);
			return new RetInfo(-31000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody
	RetInfo updatePet(@RequestBody
	Map<String, Object> map) {
		try {
			Pet pet = (Pet) JSONUtil.map2bean(map, Pet.class);
			if (petDao.updatePet(pet) > 0)
				return new RetInfo(1);
			return new RetInfo(-32000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new RetInfo(0);
		}
	}

	public PetDao getPetDao() {
		return petDao;
	}

	public void setPetDao(PetDao petDao) {
		this.petDao = petDao;
	}

}
