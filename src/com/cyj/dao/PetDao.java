package com.cyj.dao;

import java.util.List;

import com.cyj.bo.Pet;

public interface PetDao {
	
	public List<Pet> queryPetByPhone(long phone);
	
	public boolean addPet(Pet pet);

	public int updatePet(Pet pet);

}
