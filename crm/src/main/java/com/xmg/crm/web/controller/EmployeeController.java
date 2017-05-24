package com.xmg.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.Employee;
import com.xmg.crm.domain.Permission;
import com.xmg.crm.query.EmployeeQueryObject;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.util.UserContext;

@Controller
public class EmployeeController extends BaseController{
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/employee_delete")
	@ResponseBody
	public Map delete(Long id){
		Map<String,Object> map=new HashMap<>();
		try {
			employeeService.delete(employeeService.get(id));
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除失败");
		}
		map.put("success", true);
		map.put("msg", "删除成功");
		return map;
	}
	
	/**
	 * 保存
	 * @param emp
	 * @return
	 */
	@RequestMapping("/employee_save")
	@ResponseBody
	public Map save(Employee emp){
		Map<String,Object> map=new HashMap<>();
		try {
			emp.setStatus(1);
			employeeService.save(emp);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "保存失败");
		}
		map.put("success", true);
		map.put("msg", "保存成功");
		return map;
	}
	//预处理操作
	@ModelAttribute
	public Model preEdit(@RequestParam(defaultValue="-1")Long id,Model model){
		if(id!=-1){
			Employee employee = employeeService.get(id);
			//打破关系
			employee.setDept(null);
			employee.setMdept(null);
			employee.setRoles(null);
			model.addAttribute(employee);
		}
		return model;
	}
	/**
	 * 编辑
	 * @param emp
	 * @return
	 */
	@RequestMapping("/employee_edit")
	@ResponseBody
	public Map edit(Employee emp){
		Map<String,Object> map=new HashMap<>();
		try {
			employeeService.update(emp);
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "更新失败");
		}
		map.put("success", true);
		map.put("msg", "更新成功");
		return map;
	}
	
	
	/**
	 * 获取数据
	 * @return
	 */
	@RequestMapping("/employee_list")
	@ResponseBody
	public PageResult list(EmployeeQueryObject qo){
		qo.setPs(new String[]{"id","username","email","dept.name","dept.id","nickname","status","mdept.id","mdept.nickname"});
		PageResult pageResult = employeeService.queryForCondition(qo);
		return pageResult;
	}
	/**
	 * 通过员工id查询员工拥有的角色的权限id
	 * @param id
	 * @return
	 */
	@RequestMapping("/employee_queryForRid")
	@ResponseBody
	public List queryForRid(Long id){
		return employeeService.queryForRid(id);
	}
	/**
	 * 跳转到employee.jsp页面
	 */
	@RequestMapping("/employee")
	public void index(){
		
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map login(String username,String password,HttpSession session){
		Employee emp=employeeService.queryForLogin(username,password);
		Map<String,Object> map=new HashMap<>();
		if(emp==null){
			//如果emp为空,
			map.put("success",false);
			map.put("msg","登录失败!");
		}else{
			//登录成功之后将用户的权限查询出来存储到session中
			List<Permission> permissions=permissionService.queryByEid(emp.getId());
			session.setAttribute(UserContext.PERMISSION_IN_SESSION, permissions);
			//存储员工
			session.setAttribute(UserContext.EMPLOYEE_IN_SESSION, emp);
			map.put("success",true);
			map.put("msg","登录成功!");
			
		}
		return map;
	}
}
