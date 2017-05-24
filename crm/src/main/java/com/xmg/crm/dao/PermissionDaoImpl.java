package com.xmg.crm.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.xmg.crm.domain.Permission;


@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements IPermissionDao{

	@Override
	public List queryForSelected(Long rid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select p from Role r join r.permissions p where r.id=:rid";
		Query query = session.createQuery(hql);
		query.setParameter("rid", rid);
		return query.list();
	}
	/*通过员工id查询
	 * (non-Javadoc)
	 * @see com.xmg.crm.dao.IPermissionDao#queryByEid(java.lang.Long)
	 */
	@Override
	public List<Permission> queryByEid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select p from Employee e join e.roles r join r.permissions p where e.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}
	/*
	 * 通过expression查询权限
	 * @see com.xmg.crm.dao.IPermissionDao#queryByExpression(java.lang.String)
	 */
	@Override
	public Permission queryByExpression(String expression) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select p from Permission p where p.expression=:expr";
		Query query = session.createQuery(hql);
		query.setParameter("expr", expression);
		return (Permission) query.uniqueResult();
	}


}
