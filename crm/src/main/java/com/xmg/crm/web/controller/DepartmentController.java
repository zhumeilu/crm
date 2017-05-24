package com.xmg.crm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.Department;
import com.xmg.crm.query.DepartmentQueryObject;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.service.IDepartmentService;
import com.xmg.crm.util.ParseJson;

@Controller
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	/**
	 * 表单选择父分类时,查询所有分类,并对其格式做转换
	 * @return
	 */
	@RequestMapping("/department_getParent")
	@ResponseBody
	public List getParent(DepartmentQueryObject qo){
		qo.setPs(new String[]{"id","name","parent.id"});
		PageResult pageResult = departmentService.queryForCondition(qo);
		List<Map<String,Object>> list=ParseJson.parse(pageResult.getRows());
		return list;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/department_delete")
	@ResponseBody
	public Map delete(Long id){
		Map<String,Object> map=new HashMap<>();
		try {
			departmentService.delete(departmentService.get(id));
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
	 * @param dept
	 * @return
	 */
	@RequestMapping("/department_save")
	@ResponseBody
	public Map save(Department dept){
		Map<String,Object> map=new HashMap<>();
		try {
			System.out.println("-------"+dept);
			departmentService.save(dept);
			map.put("success", true);
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "保存失败");
		}
		return map;
	}
	//预处理操作
	@ModelAttribute
	public Model preEdit(@RequestParam(defaultValue="-1")Long id,Model model){
		if(id!=-1){
			Department department = departmentService.get(id);
			//打破关系
			department.setManager(null);
			department.setParent(null);
			model.addAttribute(department);
		}
		return model;
	}
	/**
	 * 编辑
	 * @param dept
	 * @return
	 */
	@RequestMapping("/department_edit")
	@ResponseBody
	public Map eidt(Department dept){
		Map<String,Object> map=new HashMap<>();
		try {
			departmentService.update(dept);
			map.put("success", true);
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "保存失败");
		}
		return map;
	}
	
	
	/**
	 * 获取数据
	 * @return
	 */
	@RequestMapping("/department_list")
	@ResponseBody
	public PageResult list(DepartmentQueryObject qo){
		qo.setPs(new String[]{"id","sn","name","parent.id","parent.name","manager.id","manager.nickname"});
		PageResult pageResult = departmentService.queryForCondition(qo);
		return pageResult;
	}
	/**
	 * 跳转到department.jsp页面
	 */
	@RequestMapping("/department")
	public void index(){
		
	}
}
