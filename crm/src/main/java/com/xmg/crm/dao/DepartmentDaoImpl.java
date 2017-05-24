package com.xmg.crm.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xmg.crm.domain.Department;
@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements IDepartmentDao{

	//消除员工表中对象部门的引用
	@Override
	public void updateEmpForDept(Department t) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql="update Employee e set e.dept=null where e.dept.id=:deptId";
		session.createQuery(hql).setParameter("deptId", t.getId()).executeUpdate();
	}
	//消除部门表中父分类中的引用
	@Override
	public void updateDeptForParent(Department t) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql="update Department d set d.parent=null where d.parent.id=:deptId";
		session.createQuery(hql).setParameter("deptId", t.getId()).executeUpdate();
	}


}
