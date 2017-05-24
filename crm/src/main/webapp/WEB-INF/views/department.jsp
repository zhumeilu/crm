<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
$(function(){
	//初始化表格数据
	$("#department_datagrid").datagrid({
		fit:true,
		fitColumns:true,
		url:'/department_list.do',
		toolbar:"#tb",
		singleSelect:true,
		pagination:true,
	})
	//选择部门管理员使用combogrid
	$("#manager_combogrid").combogrid({    
	    panelWidth:450,    
	    value:'',    
	     
	    idField:'id',    
	    textField:'nickname',    
	    url:'/employee_list.do?status=1',    
	    columns:[[    
	        {field:'username',title:'用户名',width:60},    
	        {field:'nickname',title:'昵称',width:100},    
	        {field:'email',title:'邮箱',width:120},    
	        {field:'dept.name',title:'所属部门',width:100}    
	    ]] 
	    
	});
	//获取combogrid中的表格,添加监听事件
	var grid=$("#manager_combogrid").combogrid("grid");
	grid.datagrid({
		onClickRow:function(rowIndex, rowData){
			if(rowData["mdept.id"]){
				$.messager.alert("温馨提示","该员工已经管理了部门,请选择其他员工","info");
				$("#manager_combogrid").combogrid("clear");
			}
			return;
		}
	}) 
	//选择父分类使用combotree
	$("#pdept_combotree").combotree({
		url:'/department_getParent.do'
	})
	//修改  父部门时添加监听事件
	var myTree=$("#pdept_combotree").combotree("tree");
	myTree.tree({
		onClick:function(node){
			//编辑
			var target=node.target;
			if($("[name='id']").val()){
				//获取当前选中列
				var selectedRow=$("#department_datagrid").datagrid("getSelected");
				if(selectedRow.id==node.id){
					$.messager.alert("温馨提示","不能选择自己作为自己的父部门","info");
					$("#pdept_combotree").combotree("clear");
				}else if(findChild(selectedRow.id,node)){
					$.messager.alert("温馨提示","不能选择自己的子部门作为自己的父部门","info");
					$("#pdept_combotree").combotree("clear");
				}
				
			}
		}
	})

})
function findChild(selectedId,node){
	
	//获取当前选中的节点的父节点
	var myTree=$("#pdept_combotree").combotree("tree");
	var parent=myTree.tree("getParent",node.target);
	if(parent){
		if(parent.id!=selectedId){
			return findChild(selectedId,parent)
		}else{
			return true;
		}
	}
	return false;
}
//添加按钮
function add(){
	$("#department_dialog").dialog({
		title:'添加',
		closed:false
	})
	
}
//编辑按钮
function edit(){
	//获取选中的一行
	var row=$("#department_datagrid").datagrid("getSelected");
	if(row){
		//有数据
		//将row中的数据加载到表单中.
		$("#department_Form").form("load",row);
		$("#department_dialog").dialog({
			title:'编辑',
			closed:false
		})
	}else{
		//数据为空
		$.messager.alert("温馨提示","请选择一条数据","info");
	}
	
		
}
//删除按钮
function del(){
	//获取选择的一条数据
	var row=$("#department_datagrid").datagrid("getSelected");
	if(row){
		//有数据,发送异步请求
		$.get(
			'/department_delete.do',
			{id:row.id},
			function(data){
				if(data.success){
					//删除成功
					$.messager.alert("温馨提示",data.msg,"info");
					//刷新页面
					$("#department_datagrid").datagrid("reload");
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
	$("#department_datagrid").datagrid("reload");
}
//输入框提交按钮
function commit(){
	var url;
	//如果id有值,则编辑,否则保存
	console.debug()
	if($("[name='id']").val()){
		url="/department_edit.do"
	}else{
		url="/department_save.do"
	}
	$("#department_Form").form("submit",{
		url:url,
		success:function(data){
			var json = $.parseJSON(data);
			if(json.success){
				//添加成功
				$.messager.alert("温馨提示",json.msg,"info");
				//关闭对话框
				$("#department_dialog").dialog("close");
				//清空表单数据
				$("#department_Form").form("clear");
				//刷新页面
				$("#department_datagrid").datagrid("reload");
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
	$("#department_dialog").dialog("close");
	//清空表单数据
	$("#department_Form").form("clear");
}
</script>
</head>
<body>
<table id="department_datagrid">
	<thead>
		<tr>
			<th data-options="field:'name',width:1">部门名称</th>
			<th data-options="field:'sn',width:1">编号</th>
			<th data-options="field:'parent.name',width:1">父部门</th>
			<th data-options="field:'manager.nickname',width:1">管理员</th>
		</tr>
	</thead>
</table>
<div id="tb">
	<div>
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="add()">添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onClick="edit()">编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onClick="del()">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onClick="reflash()">刷新</a>
	</div>
</div>
<div id="department_dialog" class="easyui-dialog" data-options="width:300,height:300,buttons:'#bb',closed:true">
	<form id="department_Form" action="" method="post">
	<input type="hidden" name="id">
		<table align="center" style="margin-top: 20px;">
		<tr>
			<td>部门名称</td>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<td>编号</td>
			<td><input type="text" name="sn"/></td>
		</tr>
		<tr>
			<td>管理员</td>
			<td><input id="manager_combogrid" type="text" name="manager.id"/></td>
		</tr>
		<tr>
			<td>父部门</td>
			<td><input id="pdept_combotree" name="parent.id"/></td>
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