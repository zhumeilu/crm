package com.xmg.crm.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xmg.crm.domain.Log;
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements ILogDao{

	/**
	 * 重写save方法,使用openSession开启session
	 */
	@Override
	public void save(Log t) {
		Session session = sessionFactory.openSession();
		session.save(t);
		session.close();
	}

	


}
