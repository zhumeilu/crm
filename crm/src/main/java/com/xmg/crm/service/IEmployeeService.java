package com.xmg.crm.service;

import java.util.List;

import com.xmg.crm.domain.Employee;


public interface IEmployeeService extends IBaseService<Employee>{

	Employee queryForLogin(String username, String password);

	List queryForRid(Long id);
}
