package com.xmg.crm.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmg.crm.dao.IBaseDao;
import com.xmg.crm.domain.Employee;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.QueryObject;
import com.xmg.crm.util.CommUtil;

public class BaseServiceImpl<T> implements IBaseService<T>{

	//spirng注入是在构造器之后才执行的,所以可以按照类型构造相应的service
	@Autowired
	protected IBaseDao<T> dao;
	@Override
	public void save(T t) {
		dao.save(t);
	}

	@Override
	public void update(T t) {
		dao.update(t);
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

	@Override
	public T get(Serializable id) {
		return (T) dao.get(id);
	}

	@Override
	public List queryForList(QueryObject qo) {
		List list = dao.queryForList(qo);
		List maps=new ArrayList<>();
		if(qo.getPs()!=null){
			for (Object obj: list) {
				Map map=CommUtil.obj2map(obj,qo.getPs());
				maps.add(map);
			}
			return maps;
		}
		
		return list;
	}

	@Override
	public PageResult queryForCondition(QueryObject qo) {
		PageResult pageResult = dao.queryForCondition(qo);
		List<Map<String,Object>> list=new ArrayList<>();
		if(qo.getPs()!=null){
			for (Object obj: pageResult.getRows()) {
				Map map=CommUtil.obj2map(obj,qo.getPs());
				list.add(map);
			}
			pageResult.setRows(list);
		}
		return pageResult;
	}

}
