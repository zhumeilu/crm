package com.xmg.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmg.crm.service.IArticleService;
import com.xmg.crm.service.IDepartmentService;
import com.xmg.crm.service.IEmployeeService;
import com.xmg.crm.service.IPermissionService;
import com.xmg.crm.service.IRoleService;
import com.xmg.crm.service.ISystemDictionaryItemService;
import com.xmg.crm.service.ISystemDictionaryService;

public class BaseController {

	@Autowired
	public IEmployeeService employeeService;
	@Autowired
	public IDepartmentService departmentService;
	@Autowired
	public IPermissionService permissionService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ISystemDictionaryService systemDictionaryService;
	@Autowired
	public ISystemDictionaryItemService systemDictionaryItemService;
	@Autowired
	public IArticleService articleService;
}
