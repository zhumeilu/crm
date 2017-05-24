package com.xmg.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResult {

	private boolean success;
	private String msg;
	public AjaxResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public AjaxResult() {
		super();
	}
	
}
