package com.xmg.crm.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.crm.domain.AjaxResult;
import com.xmg.crm.domain.Article;
import com.xmg.crm.domain.Employee;
import com.xmg.crm.query.ArticleQueryObject;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.util.UserContext;
import com.xmg.crm.util.WebContext;

@Controller
public class ArticleController extends BaseController {
	@RequestMapping("/article")
	public String index(){
		return "article";
	}
	@RequestMapping("/article_list")
	@ResponseBody
	public PageResult list(ArticleQueryObject qo){
		qo.setPs(new String[]{"id","publishedTime","author.nickName","content","title"});
		PageResult result = articleService.queryForCondition(qo);
		return result;
	}
	@RequestMapping("/article_update")
	@ResponseBody
	public AjaxResult update(Article article){
		AjaxResult result = null;
		try{
			article.setAuthor((Employee)WebContext.getSession().getAttribute(UserContext.EMPLOYEE_IN_SESSION));
			article.setPublishedTime(new Date());
			articleService.update(article);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult(false,"更新异常,请联系管理员");
		}
		return result;
	}
	@RequestMapping("/article_save")
	@ResponseBody
	public AjaxResult save(Article article){
		AjaxResult result = null;
		try{
			article.setAuthor((Employee)WebContext.getSession().getAttribute(UserContext.EMPLOYEE_IN_SESSION));
			article.setPublishedTime(new Date());
			articleService.save(article);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult(false,"保存异常,请联系管理员");
		}
		return result;
	}
	@RequestMapping("/article_delete")
	@ResponseBody
	public AjaxResult delete(Long aid){
		AjaxResult result = null;
		try{
			Article article = new Article();
			article.setId(aid);
			articleService.delete(article);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult(false,"删除异常,请联系管理员");
			
		}
		return result;
	}
	
}
