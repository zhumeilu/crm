package com.xmg.crm.dao;

import com.xmg.crm.domain.Department;

public interface IDepartmentDao extends IBaseDao<Department>{

	void updateEmpForDept(Department t);

	void updateDeptForParent(Department t);

}
