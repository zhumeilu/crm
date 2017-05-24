package com.xmg.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmg.crm.dao.IPermissionDao;
import com.xmg.crm.domain.Permission;
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {
	@Autowired
	private IPermissionDao permissionDao;
	@Override
	public List queryForSelected(Long rid) {
		return permissionDao.queryForSelected(rid);
	}
	@Override
	public List<Permission> queryByEid(Long id) {
		return permissionDao.queryByEid(id);
	}
	@Override
	public Permission queryByExpression(String expression) {
		return permissionDao.queryByExpression(expression);
	}

}
