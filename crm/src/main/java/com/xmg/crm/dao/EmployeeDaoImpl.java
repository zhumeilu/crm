package com.xmg.crm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xmg.crm.domain.Employee;
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements IEmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Employee queryForLogin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select obj from Employee obj where obj.username=:username and obj.password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return (Employee) query.uniqueResult();
	}
	@Override
	public List queryForRid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql="select r.id from Employee e join e.roles r where e.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}

}
