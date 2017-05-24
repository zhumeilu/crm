package com.xmg.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.BaseDomain;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.SystemDictionaryQueryObject;
@Controller
public class SystemDictionaryController extends BaseController{

	@RequestMapping("/systemDictionary")
	public String index(){
		return "systemDictionary";
	}
	/**
	 * 
	 * @param qo
	 * @return
	 */
	@RequestMapping("/systemDictionary_list")
	@ResponseBody
	public PageResult list(SystemDictionaryQueryObject qo){
		qo.setPs(new String[]{"id","name","intro","sn"});
		return systemDictionaryService.queryForCondition(qo);
	}
}
