package com.xmg.crm.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.PermissionQueryObject;

@Controller
public class PermissionController extends BaseController{

	@RequestMapping("/permission")
	public String index(){
		return "permission";
	}
	@RequestMapping("/permission_list")
	@ResponseBody
	public PageResult list(PermissionQueryObject qo){
		return permissionService.queryForCondition(qo);
	}
	
	@RequestMapping("/permission_queryAllForRole")
	@ResponseBody
	public PageResult queryAllForRole(PermissionQueryObject qo){
		qo.setPs(new String[]{"id","name","expression"});
		return permissionService.queryForCondition(qo);
	}
	//通过角色id查询权限
	@RequestMapping("/permission_queryForSelected")
	@ResponseBody
	public List queryForSelected(Long rid){
		return permissionService.queryForSelected(rid);
	}
	
}
