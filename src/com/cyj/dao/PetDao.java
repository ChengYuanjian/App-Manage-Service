package com.cyj.dao;

import com.cyj.bo.Pet;
import com.cyj.bo.RetInfo;

public interface PetDao {
	
	public Pet queryPetById(long id);
	
	public RetInfo addPet(Pet pet);

	public RetInfo updatePet(Pet pet);

}
