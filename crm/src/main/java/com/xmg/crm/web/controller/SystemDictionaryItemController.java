package com.xmg.crm.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.AjaxResult;
import com.xmg.crm.domain.SystemDictionaryItem;
import com.xmg.crm.query.SystemDictionaryItemQueryObject;
@Controller
public class SystemDictionaryItemController extends BaseController{
	@RequestMapping("/systemDictionaryItem_queryByParent")
	@ResponseBody
	public List queryByParent(SystemDictionaryItemQueryObject qo){
		qo.setPs(new String[]{"id","name","intro","sn"});
		return systemDictionaryItemService.queryForList(qo);
	}
	
	@RequestMapping("/systemDictionaryItem_save")
	@ResponseBody
	public AjaxResult save(SystemDictionaryItem item){
		try {
			systemDictionaryItemService.save(item);
		} catch (Exception e) {
			return new AjaxResult(false, "保存失败");
		}
		return new AjaxResult(true, "保存成功");
	}
	
	@RequestMapping("/systemDictionaryItem_update")
	@ResponseBody
	public AjaxResult update(SystemDictionaryItem item){
		try {
			systemDictionaryItemService.update(item);
		} catch (Exception e) {
			return new AjaxResult(false, "保存失败");
		}
		return new AjaxResult(true, "保存成功");
	}
	
	@ModelAttribute	
	public void preHandle(@RequestParam(defaultValue="-1") Long id ,Model model){
		if(id!=-1){
			SystemDictionaryItem item = systemDictionaryItemService.get(id);
			item.setParent(null);
			model.addAttribute(item);
		}
	}
}
