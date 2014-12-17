package com.cyj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyj.bo.Pet;
import com.cyj.bo.RetInfo;
import com.cyj.dao.PetDao;

@Controller
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetDao petDao;

	@RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Pet getPet(@PathVariable("id")
	long id) {
		return petDao.queryPetById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo addPet(Pet pet) {
		return petDao.addPet(pet);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	RetInfo updatePet(Pet pet) {
		return petDao.updatePet(pet);
	}

}
