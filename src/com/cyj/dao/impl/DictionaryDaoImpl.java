package com.cyj.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyj.base.dao.BaseDAO;
import com.cyj.dao.DictionaryDao;

public class DictionaryDaoImpl extends BaseDAO implements DictionaryDao {

	private String namespace = "com.cyj.dao.DictionaryDao";

	@Override
	public List<Map<String, Object>> getArea(int code) {
		return (List<Map<String, Object>>) queryForList(namespace + ".getArea",
				code);
	}

	@Override
	public Map<String, Object> getAllArea() {
		List<Map<String, Object>> provinces = getArea(0);

		Map<String, Object> areaResultMap = new HashMap<String, Object>();

		for (Map<String, Object> province : provinces) {
			List<Object> cityList = new ArrayList<Object>();

			List<Map<String, Object>> citys = getArea((Integer) (province
					.get("code")));

			for (Map<String, Object> city : citys) {
				Map<String, List<String>> cityMap = new HashMap<String, List<String>>();

				List<Map<String, Object>> districts = getArea((Integer) (city
						.get("code")));
				List<String> districtlist = new ArrayList<String>();
				for (Map<String, Object> district : districts) {
					districtlist.add(district.get("name").toString());
				}
				cityMap.put(city.get("name").toString(), districtlist);
				cityList.add(cityMap);
			}
			areaResultMap.put(province.get("name").toString(), cityList);
		}
		return areaResultMap;
	}

	@Override
	public Map<String, Object> getAllPet() {
		List<Map<String, Object>> categories = getPet(0);

		Map<String, Object> petResultMap = new HashMap<String, Object>();

		for (Map<String, Object> category : categories) {
			List<Object> categoryList = new ArrayList<Object>();

			List<Map<String, Object>> types = getPet((Integer) (category
					.get("code")));

			for (Map<String, Object> type : types) {
				Map<String, List<String>> categoryMap = new HashMap<String, List<String>>();

				List<Map<String, Object>> items = getPet((Integer) (type
						.get("code")));
				List<String> itemlist = new ArrayList<String>();
				for (Map<String, Object> item : items) {
					itemlist.add(item.get("name").toString());
				}
				categoryMap.put(type.get("name").toString(), itemlist);
				categoryList.add(categoryMap);
			}
			petResultMap.put(category.get("name").toString(), categoryList);
		}
		return petResultMap;
	}

	@Override
	public List<Map<String, Object>> getPet(int code) {
		return (List<Map<String, Object>>) queryForList(namespace + ".getPet",
				code);
	}

}
