package com.xmg.crm.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色domain
 * @author zhumeilu
 *
 */
@Getter
@Setter
public class Role extends BaseDomain{

	private String name;
	private String description;
	List<Permission> permissions=new ArrayList<>();
}
