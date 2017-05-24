package com.xmg.crm.dao;

import java.io.Serializable;
import java.util.List;

import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.QueryObject;

public interface IBaseDao<T> {

	public void save(T t);
	public void update(T t);
	public void delete(T t);
	public T get(Serializable id);
	public List queryForList(QueryObject qo);
	public PageResult queryForCondition(QueryObject qo);
}
