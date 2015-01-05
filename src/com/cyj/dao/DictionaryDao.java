package com.cyj.dao;

import java.util.List;
import java.util.Map;

public interface DictionaryDao {
	
	public List<Map<String,Object>> getArea(int code);
	
	public Map<String,Object> getAllArea();
	
	public List<Map<String,Object>> getPet(int code);
	
	public Map<String,Object> getAllPet();

}
