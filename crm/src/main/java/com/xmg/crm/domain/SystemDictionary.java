package com.xmg.crm.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典
 * @author zhumeilu
 *
 */
@Getter
@Setter
public class SystemDictionary extends BaseDomain{

	private String name;
	private String sn;
	private String intro;
	private List<SystemDictionaryItem> details;
}
