<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://crm.520it.com/myFn" prefix="myFn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
$(function(){
	//初始化表格数据
	$("#employee_datagrid").datagrid({
		fit:true,
		fitColumns:true,
		url:'/employee_list.do',
		toolbar:"#tb",
		singleSelect:true,
		pagination:true,
	})

	//监听选中条目,如果是已删除,则将编辑和删除按钮失效
	$("#employee_datagrid").datagrid({
		onClickRow:function(rowIndex, rowData){
			if(!rowData.status){
				$("#tb a").eq(1).linkbutton("disable");
				$("#tb a").eq(2).linkbutton("disable");
			}else{
				$("#tb a").eq(1).linkbutton("enable");
				$("#tb a").eq(2).linkbutton("enable");
				
			}
		}
	})
	//选择部门使用combotree
	$("#dept_combotree").combotree({
		url:'/department_getParent.do'
	})
	
	//选择角色
	$("#roles_combobox").combobox({
		url:'/role_queryForEmp.do',    
	    valueField:'id',    
	    textField:'name',
	    multiple:true
	})
	

})

//过滤status
function statusFomat(value,row,index){
	console.debug(row);
	if(row.status){
		return "在职";
	}else{
		return "离职";
	}
	
}
//添加按钮
function add(){
	$("#employee_dialog").dialog({
		title:'添加',
		closed:false
	})
	
}
//编辑按钮
function edit(){
	//获取选中的一行
	var row=$("#employee_datagrid").datagrid("getSelected");
	if(row){
		console.debug(row);
		//有数据
		//将row中的数据加载到表单中.
		$("#employee_Form").form("load",row);
		$("#employee_dialog").dialog({
			title:'编辑',
			closed:false
		})
		console.debug(row.id);	
		//设置角色回显
		//通过同步请求,获取数据
		 var html = $.ajax({
		  url: "/employee_queryForRid.do",
		  data:{id:row.id},
		  async: false
		 }).responseText;
		console.debug(html);
		$("#roles_combobox").combobox("setValues",$.parseJSON(html));
	}else{
		//数据为空
		$.messager.alert("温馨提示","请选择一条数据","info");
	}
	
		
}
//删除按钮
function del(){
	//获取选择的一条数据
	var row=$("#employee_datagrid").datagrid("getSelected");
	if(row){
		//有数据,发送异步请求
		$.get(
			'/employee_delete.do',
			{id:row.id},
			function(data){
				if(data.success){
					//删除成功
					$.messager.alert("温馨提示",data.msg,"info");
					//刷新页面
					$("#employee_datagrid").datagrid("reload");
				}else{
					//删除失败
					$.messager.alert("温馨提示",data.msg,"info");
				}
			},
			"json"
		)
	}else{
		//数据为空
		$.messager.alert("温馨提示","请选择一条数据","info");
	}
		
}
//刷新按钮
function reflash(){
	$("#employee_datagrid").datagrid("reload");
}
//输入框提交按钮
function commit(){
	var url;
	//如果id有值,则编辑,否则保存
	if($("[name='id']").val()){
		url="/employee_edit.do"
	}else{
		url="/employee_save.do"
	}
	$("#employee_Form").form("submit",{
		url:url,
		onSubmit:function(param){
			var values = $("#roles_combobox").combobox("getValues");
			$.each(values,function(index,item){
				param["roles["+index+"].id"]=item;
			})
		},
		success:function(data){
			var json = $.parseJSON(data);
			if(json.success){
				//添加成功
				$.messager.alert("温馨提示",json.msg,"info");
				//关闭对话框
				$("#employee_dialog").dialog("close");
				//清空表单数据
				$("#employee_Form").form("clear");
				//刷新页面
				$("#employee_datagrid").datagrid("reload");
			}else{
				//添加失败
				$.messager.alert("温馨提示",json.msg,"info");
			}
		}
	})
	
}
//表单取消按钮
function cancel(){
	//关闭对话框
	$("#employee_dialog").dialog("close");
	//清空表单数据
	$("#employee_Form").form("clear");
}
//搜索按钮
function searchForm(){
	var data=$("#searchForm").serializeArray();
	var param={};
	$.each(data,function(index,obj){
		param[obj.name]=obj.value;
	})
	console.debug(param);
	$("#employee_datagrid").datagrid("load",param);
}
</script>
</head>
<body>
<table id="employee_datagrid">
	<thead>
		<tr>
			<th data-options="field:'username',width:1">用户名</th>
			<th data-options="field:'nickname',width:1">昵称</th>
			<th data-options="field:'email',width:1">邮箱</th>
			<th data-options="field:'status',width:1,fomatter:statusFomat">状态</th>
			<th data-options="field:'dept.name',width:1">部门</th>
		</tr>
	</thead>
</table>
<div id="tb">
	<form id="searchForm" action="">
		<div>
			关键字<input type="text" name="keyword"/>
		状态:<select class="easyui-combobox" name="status" style="width:100px;">
			<option value="-1">全部</option>
			<option value="1">在职</option>
			<option value="0">离职</option>
			</select>
		<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchForm()">查询</a>
		</div>
	</form>
	<div>
		<c:if test="${myFn:hasPermission('com.xmg.crm.web.controller.EmployeeController:save')}">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="add()">添加</a>
		</c:if>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="edit()">编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="del()">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onClick="reflash()">刷新</a>
	</div>
</div>
<div id="employee_dialog" class="easyui-dialog" data-options="width:300,height:300,buttons:'#bb',closed:true">
	<form id="employee_Form" action="" method="post">
	<input type="hidden" name="id">
		<table align="center" style="margin-top: 20px;">
		<tr>
			<td>用户名</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>昵称</td>
			<td><input type="text" name="nickname"/></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td>部门</td>
			<td><input id="dept_combotree" name="dept.id"/></td>
		</tr>
		<tr>
			<td>角色</td>
			<td><input id="roles_combobox"/></td>
		</tr>
		</table>
	</form>
</div>

<div id="bb">
	<a class="easyui-linkbutton" iconCls="icon-ok" onclick="commit()">确定</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
</div>
</body>
</html>