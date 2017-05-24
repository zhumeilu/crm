package com.xmg.crm.service;

import java.util.List;

import com.xmg.crm.domain.Permission;


public interface IPermissionService extends IBaseService<Permission>{

	List queryForSelected(Long rid);

	List<Permission> queryByEid(Long id);

	Permission queryByExpression(String expression);

}
