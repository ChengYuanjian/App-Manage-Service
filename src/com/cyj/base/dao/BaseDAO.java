package com.cyj.base.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("deprecation")
public class BaseDAO extends SqlMapClientDaoSupport   {
	
	public int add(String statementName, Object parameterObject)
	{
		return getSqlMapClientTemplate().insert(statementName, parameterObject)==null?0:1;
	}
	
	public int delete(String statementName, Object parameterObject)
	{
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}
	
	public int update(String statementName, Object parameterObject)
	{
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}
	
	public Object queryForObject(String statementName, Object parameterObject)
	{
		return getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}
	
	public List<?> queryForList(String statementName, Object parameterObject)
	{
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}
		
}
