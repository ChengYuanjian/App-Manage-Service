package com.cyj.base.dao;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class BaseDAO {

	@SuppressWarnings("deprecation")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("deprecation")
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	@SuppressWarnings("deprecation")
	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@SuppressWarnings("deprecation")
	public Object add(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().insert(statementName, parameterObject);
	}

	@SuppressWarnings("deprecation")
	public int delete(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}

	@SuppressWarnings("deprecation")
	public int update(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}

	@SuppressWarnings("deprecation")
	public Object queryForObject(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForObject(statementName,
				parameterObject);
	}

	@SuppressWarnings("deprecation")
	public List<?> queryForList(String statementName, Object parameterObject) {
		return getSqlMapClientTemplate().queryForList(statementName,
				parameterObject);
	}

}
