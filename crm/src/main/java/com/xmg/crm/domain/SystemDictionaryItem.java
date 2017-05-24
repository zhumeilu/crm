package com.xmg.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典项
 * @author zhumeilu
 *
 */
@Getter
@Setter
public class SystemDictionaryItem extends BaseDomain {

	private String name;
	private String intro;
	private String sn;
	private SystemDictionary parent;
}
