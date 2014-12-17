package com.cyj.dao.impl;

import java.util.Date;

import com.cyj.bo.Pet;
import com.cyj.bo.RetInfo;
import com.cyj.dao.PetDao;

public class PetDaoImpl implements PetDao {

	@Override
	public RetInfo addPet(Pet pet) {
		return new RetInfo(1);
	}

	@Override
	public Pet queryPetById(long id) {
		Pet pet = new Pet();
		pet.setPetid(id);
		pet.setBirthday(new Date());
		pet.setMasterid(1l);
		pet.setPetcategory("狗");
		pet.setPetname("哈尼");
		pet.setPetsex(1);
		pet.setPettype("中华田园犬");
		return pet;
	}

	@Override
	public RetInfo updatePet(Pet pet) {
		return new RetInfo(1);
	}

}
