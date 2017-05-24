package com.xmg.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameEntry {
	private String name;
	private Object value;
	public NameEntry(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}
	public NameEntry() {
		super();
	}
	
}
