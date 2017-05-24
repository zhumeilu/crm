package com.xmg.crm.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Employee extends BaseDomain{

	private String username;//用户名
	private String password;//密码
	private String nickname;//昵称
	private String email;//邮箱
	private Integer status;//1表示在职,0表示离职
	private Department dept;//所属部门
	private Department mdept;//管理的部门
	private List<Role> roles=new ArrayList<>();//所拥有的角色
	
}
