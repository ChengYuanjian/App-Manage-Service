package com.cyj.dao.impl;

import java.util.List;

import com.cyj.base.dao.BaseDAO;
import com.cyj.bo.Pet;
import com.cyj.dao.PetDao;
import com.cyj.util.LoggerUtil;

public class PetDaoImpl extends BaseDAO implements PetDao {

	private String namespace = "com.cyj.dao.PetDao";
	private LoggerUtil logger = new LoggerUtil(PetDaoImpl.class);

	@Override
	public boolean addPet(Pet pet) {
		try {
			add(namespace + ".addPet", pet);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public List<Pet> queryPetByPhone(long phone) {
		try {
			return (List<Pet>) queryForList(namespace + ".getPetByPhone", phone);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public int updatePet(Pet pet) {
		try {
			return update(namespace + ".updatePetById", pet);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

}
