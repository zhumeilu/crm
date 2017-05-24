package com.xmg.crm.dao;

import java.util.List;

import com.xmg.crm.domain.Employee;

public interface IEmployeeDao extends IBaseDao<Employee>{

	Employee queryForLogin(String username, String password);

	List queryForRid(Long id);

}
