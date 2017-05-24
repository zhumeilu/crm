package com.xmg.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.crm.dao.IEmployeeDao;
import com.xmg.crm.domain.Employee;
import com.xmg.crm.util.WebContext;
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public Employee queryForLogin(String username, String password) {
		return employeeDao.queryForLogin(username,password);
	}
	/**
	 * 重写delete方法
	 */
	@Override
	public void delete(Employee t) {
		//修改staus状态
		t.setStatus(0);
		//更新
		employeeDao.update(t);
	}
	@Override
	public List queryForRid(Long id) {
		return employeeDao.queryForRid(id);
	}
	
}
