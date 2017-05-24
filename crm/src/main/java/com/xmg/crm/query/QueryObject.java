package com.xmg.crm.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

public class QueryObject {

	@Getter@Setter
	private Integer pageNumber=1;
	@Getter@Setter
	private Integer pageSize=10;
	// 保存查询条件
	private List<String> conditions = new ArrayList<>();
	//查询参数
	private List<NameEntry> params = new ArrayList<>();
	private StringBuilder sb=new StringBuilder(100);
	@Getter@Setter
	private String[] ps;
	private boolean flag=false;
	//用户自定义查询语句方法
	protected void customized(){
	}
	//添加查询
	public void addQuery(String condiitons,NameEntry obj){
		this.conditions.add(condiitons);
		this.params.add(obj);
	}
	
	
	//初始化拼接参数
	void init(){
		customized();
		if(!flag){
			if(conditions.size()>0){
				sb.append(" where ");
			}
			sb.append(StringUtils.join(conditions, " and "));
		}
		flag=true;
	}
	
	//拼接查询语句
	public String getQueryString(){
		init();
		
		return sb.toString();
	}
	public List<NameEntry> getParams(){
		init();
		return params;
	}
	
}
