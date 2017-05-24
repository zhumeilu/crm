package com.xmg.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department extends BaseDomain{

	private String sn;
	private String name;
	private Department parent;//父分类
	private Employee manager;//部门管理员
	
}
