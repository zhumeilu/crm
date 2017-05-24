package com.xmg.crm.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PermissionQueryObject extends QueryObject{

	private Integer status;
	private String keyword;
	@Override
	protected void customized() {
		if(StringUtils.hasLength(keyword)){
			NameEntry entry=new NameEntry("name",keyword);
			super.addQuery(" (obj.username like :name or obj.nickname like :name) ",entry);
		}
		
		if(status!=null&&status!=-1){
			NameEntry entry=new NameEntry("status",status);
			super.addQuery(" obj.status= :status ", entry);
		}
	}
}
