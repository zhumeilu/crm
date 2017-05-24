package com.xmg.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.crm.dao.IDepartmentDao;
import com.xmg.crm.domain.Department;
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

	@Autowired
	private IDepartmentDao dao;
	/**
	 * 重写delete方法,先打破关联关系
	 */
	@Override
	public void delete(Department t) {
		//消除员工的引用
		dao.updateEmpForDept(t);
		
		//消除父部门的引用
		dao.updateDeptForParent(t);
		
		super.delete(t);
	}
	
	
	
}
