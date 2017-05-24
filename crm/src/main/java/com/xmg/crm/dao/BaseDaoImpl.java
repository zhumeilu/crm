package com.xmg.crm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmg.crm.query.NameEntry;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.QueryObject;


public class BaseDaoImpl<T> implements IBaseDao<T>{

	private Class<T> classType;
	private String classTypeName;
	public BaseDaoImpl() {
		Type clzz = this.getClass().getGenericSuperclass();
		ParameterizedType superclass =(ParameterizedType)clzz;
		classType=(Class<T>) superclass.getActualTypeArguments()[0];
		classTypeName=classType.getSimpleName();
	}
	
	@Autowired
	protected SessionFactory sessionFactory;
	public void save(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
	}

	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
		
	}

	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	public T get(Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		
		return (T) session.get(classType, id);
	}

	public List queryForList(QueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select obj from "+classTypeName+" obj "+qo.getQueryString();
		Query query = session.createQuery(hql);
		setParam(qo, query);
		return query.list();
	}
	/**
	 * 条件查询
	 */
	public PageResult queryForCondition(QueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select count(obj) from "+classTypeName+" obj "+qo.getQueryString();
		Query query = session.createQuery(hql);
		setParam(qo, query);
		Long count = (Long) query.uniqueResult();
		if(count==null){
			return new PageResult(0,null);
		}else{
			hql="select obj from "+classTypeName+" obj "+qo.getQueryString();
			query= session.createQuery(hql);
			query.setFirstResult(qo.getPageSize()*(qo.getPageNumber()-1)).setMaxResults(qo.getPageSize());
			setParam(qo, query);
			return new PageResult(count.intValue(),query.list());
		}
		
	}

	public void setParam(QueryObject qo, Query query) {
		for (NameEntry entry: qo.getParams()) {
			query.setParameter(entry.getName(), entry.getValue());
		}
	}

}
