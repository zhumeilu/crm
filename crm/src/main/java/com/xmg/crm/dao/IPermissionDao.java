package com.xmg.crm.dao;

import java.util.List;

import com.xmg.crm.domain.Permission;


public interface IPermissionDao extends IBaseDao<Permission>{

	List queryForSelected(Long rid);

	List<Permission> queryByEid(Long id);

	Permission queryByExpression(String expression);


}
