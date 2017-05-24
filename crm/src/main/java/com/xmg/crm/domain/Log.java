package com.xmg.crm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Log extends BaseDomain{

	private Employee operator;//操作人
	private Date date;//操作时间
	private String operation;//操作
	
}
