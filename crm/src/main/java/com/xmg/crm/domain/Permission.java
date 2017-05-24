package com.xmg.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限domain
 * @author zhumeilu
 *
 */
@Getter
@Setter
public class Permission extends BaseDomain{

	private String name;
	private String expression;
}
