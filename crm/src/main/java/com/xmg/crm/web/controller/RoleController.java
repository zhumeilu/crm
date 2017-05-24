package com.xmg.crm.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.AjaxResult;
import com.xmg.crm.domain.Role;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.RoleQueryObject;

@Controller
public class RoleController extends BaseController{

	/**
	 * 主页跳转
	 * @return
	 */
	@RequestMapping("/role")
	public String index(){
		return "role";
	}
	/**
	 * 主页显示
	 * @param qo
	 * @return
	 */
	@RequestMapping("/role_list")
	@ResponseBody
	public PageResult list(RoleQueryObject qo){
		qo.setPs(new String[]{"id","name","description"});
		return roleService.queryForCondition(qo);
	}
	/**
	 * 保存操作
	 * @param role
	 * @return
	 */
	@RequestMapping("/role_delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		try {
			Role role=new Role();
			role.setId(id);
			roleService.delete(role);
		} catch (Exception e) {
			return new AjaxResult(false, "删除失败");
		}
		return new AjaxResult(true, "删除成功");
	}
	/**
	 * 保存操作
	 * @param role
	 * @return
	 */
	@RequestMapping("/role_save")
	@ResponseBody
	public AjaxResult save(Role role){
		try {
			roleService.save(role);
		} catch (Exception e) {
			return new AjaxResult(false, "保存失败");
		}
		return new AjaxResult(true, "保存成功");
	}
	/**
	 * 更新操作
	 * @param role
	 * @return
	 */
	@RequestMapping("/role_update")
	@ResponseBody
	public AjaxResult update(Role role){
		try {
			roleService.update(role);
		} catch (Exception e) {
			return new AjaxResult(false, "保存失败");
		}
		return new AjaxResult(true, "保存成功");
	}
	/**
	 * 预处理
	 * @param id
	 * @param model
	 */
	@ModelAttribute	
	public void preHandle(@RequestParam(defaultValue="-1") Long id ,Model model){
		if(id!=-1){
			Role role = roleService.get(id);
			role.setPermissions(null);
			model.addAttribute(role);
		}
	}
	
	@RequestMapping("/role_queryForEmp")
	@ResponseBody
	public List queryForEmp(RoleQueryObject qo){
		qo.setPs(new String[]{"id","name","description"});
		return roleService.queryForList(qo);
	}
	
}
