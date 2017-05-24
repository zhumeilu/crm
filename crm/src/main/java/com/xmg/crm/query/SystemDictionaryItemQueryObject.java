package com.xmg.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionaryItemQueryObject extends QueryObject{

	private Long pid=-1L;
	
	@Override
	protected void customized() {
		if(pid!=-1L){
			NameEntry entry=new NameEntry("pid", pid);
			addQuery(" obj.parent.id=:pid", entry);
		}
		
	}
}
