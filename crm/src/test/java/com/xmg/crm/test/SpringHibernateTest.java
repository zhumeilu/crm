package com.xmg.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmg.crm.domain.Employee;
import com.xmg.crm.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class SpringHibernateTest {
	@Autowired
	private IEmployeeService employeeService;
	@Test
	public void test(){
		Employee obj=new Employee();
		obj.setUsername("test");
		obj.setPassword("123");
		employeeService.save(obj);
	}
}
