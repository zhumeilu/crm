package com.xmg.crm.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.xmg.crm.domain.Employee;


public class HibernateTest {
	@Test
	public void test(){
		Configuration cfg=new Configuration().configure();
		SessionFactory factory=cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		Employee e=new Employee();
		e.setUsername("zml");
		e.setPassword("zml");
		session.save(e);
		session.getTransaction().commit();
	}
}
