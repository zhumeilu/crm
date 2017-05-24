<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/common/common.jsp"%>
<script type="text/javascript">
	$(function() {
		var systemDictionary_datagrid;
		var systemDictionaryItem_datagrid;
		//表单
		var systemDictionaryItemForm;
		//对话框
		var systemDictionaryItemDialog;
		systemDictionaryItemDialog = $("#systemDictionaryItem_dialog");
		systemDictionaryItemForm = $("#systemDictionaryItem_Form");
		//系统字典项
		systemDictionaryDatagrid=$("#systemDictionary_datagrid").datagrid({
			url:'/systemDictionary_list.do',
			fit:true,
			fitColumns:true,
			pagination:true,
			singleSelect:true,
			onClickRow:function(rowIndex, rowData){
				$("#item_bt a").linkbutton("enable");
				var options = systemDictionaryItemDatagrid.datagrid("options");
				options.url="/systemDictionaryItem_queryByParent.do?pid="+rowData.id;
				systemDictionaryItemDatagrid.datagrid("reload");
			}
		})
		//系统字典明细
		systemDictionaryItemDatagrid=$("#systemDictionaryItem_datagrid").datagrid({
			fitColumns:true,
			toolbar:'#item_bt'
		})
		//页面加载时禁用按钮
		$("#item_bt a").linkbutton("disable");
		
		var cmdObj = {
			add : function() {
				systemDictionaryItemDialog.dialog({
					title : '添加',
					closed : false
				})
				//清空表单数据
				$("#systemDictionaryItem_Form").form("clear");
			},
			edit : function() {
				//获取选中的一行
				var row = systemDictionaryItemDatagrid.datagrid("getSelected");
				if (row) {
					//有数据
					//将row中的数据加载到表单中.
					systemDictionaryItemForm.form("load", row);
					systemDictionaryItemDialog.dialog({
						title : '编辑',
						closed : false
					})
				} else {
					//数据为空
					$.messager.alert("温馨提示", "请选择一条数据", "info");
				}
			},
			del : function() {
				//获取选择的一条数据
				var row = systemDictionaryDatagrid.datagrid("getSelected");
				if (row) {

					$.messager.confirm("温馨提示", "你确认要删除这条数据吗?", function(r) {
						if (r) {
							//有数据,发送异步请求
							$.get('/systemDictionary_delete.do', {
								id : row.id
							},
									function(data) {
										if (data.success) {
											//删除成功
											$.messager.alert("温馨提示", data.msg,
													"info");
											//刷新页面
											systemDictionaryDatagrid
													.datagrid("reload");
										} else {
											//删除失败
											$.messager.alert("温馨提示", data.msg,
													"info");
										}
									}, "json")
						}
					})
				} else {
					//数据为空
					$.messager.alert("温馨提示", "请选择一条数据", "info");
				}

			},
			reflash : function() {
				systemDictionaryDatagrid.datagrid("reload");
			},
			commit : function() {
				var url;
				//如果id有值,则编辑,否则保存
				if ($("[name='id']").val()) {
					url = "/systemDictionaryItem_update.do"
				} else {
					url = "/systemDictionaryItem_save.do"
				}
				systemDictionaryItemForm.form("submit", {
					url : url,
					onSubmit : function(param) {
						//获取选中的字典项的数据
						var row=systemDictionaryDatagrid.datagrid("getSelected");
						//parent.id
						param["parent.id"]=row.id;
					},
					success : function(data) {
						var json = $.parseJSON(data);
						if (json.success) {
							//添加成功
							$.messager.alert("温馨提示", json.msg, "info");
							//关闭对话框
							systemDictionaryItemDialog.dialog("close");
							//清空表单数据
							systemDictionaryItemForm.form("clear");
							//刷新页面
							systemDictionaryItemDatagrid.datagrid("reload");
						} else {
							//添加失败
							$.messager.alert("温馨提示", json.msg, "info");
						}
					}
				})
			},
			cancel : function() {
				//关闭对话框
				systemDictionaryItemDialog.dialog("close");
				//清空表单数据
				systemDictionaryItemForm.form("clear");
			}
			
		}

		//给所有的button添加点击事件
		$(".easyui-linkbutton").on("click", function() {
			var cmd = $(this).data("cmd");
			cmdObj[cmd]();
		})

	})
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'west',width:400">
			<table id="systemDictionary_datagrid">
				<thead>
					<tr>
						<th data-options="field:'name',width:1">字典名称</th>
						<th data-options="field:'sn',width:1">字典编号</th>
						<th data-options="field:'intro',width:1">字典介绍</th>
					</tr>
				</thead>
			</table>
		</div>
		<div data-options="region:'center'">
			<table id="systemDictionaryItem_datagrid">
				<thead>
					<tr>
						<th data-options="field:'name',width:1">字典明细名称</th>
						<th data-options="field:'sn',width:1">字典明细编号</th>
						<th data-options="field:'intro',width:1">字典明细介绍</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 按钮 -->
	<div id="item_bt">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add" >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del" >删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reflash">刷新</a>
	</div>
	<!-- 添加系统字典明细表单 -->
	<div id="systemDictionaryItem_dialog" class="easyui-dialog" data-options="width:400,height:300,buttons:'#item_bb',closed:true">
	<form id="systemDictionaryItem_Form" action="" method="post">
	<input type="hidden" name="id">
		<table align="center" style="margin-top: 20px;">
		<tr>
			<td>字典明细名称:</td>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<td>字典明细编号:</td>
			<td><input type="text" name="sn"/></td>
		</tr>
		<tr>
			<td>字典明细介绍:</td>
			<td><input type="text" name="intro"/></td>
		</tr>
		</table>
	</form>
</div>

<div id="item_bb">
	<a class="easyui-linkbutton" iconCls="icon-ok" data-cmd="commit">确定</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div>
	<!-- <table id="systemDictionary_datagrid">
	<thead>
		<tr>
			<th data-options="field:'name',width:1">角色名称</th>
			<th data-options="field:'description',width:1">角色描述</th>
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
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add" >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del" >删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reflash">刷新</a>
	</div>
</div>
<div id="systemDictionary_dialog" class="easyui-dialog" data-options="width:800,height:500,buttons:'#bb',closed:true">
	<form id="systemDictionary_Form" action="" method="post">
	<input type="hidden" name="id">
		<table align="center" style="margin-top: 20px;">
		<tr>
			<td>角色名称:<input type="text" name="name"/></td>
			<td>角色描述:<input type="text" name="description"/></td>
		</tr>
		<tr>
			<td colspan="2">权限分配</td>
		</tr>
		<tr>
			<td><table id="selectedPermission"></table></td>
			<td><table id="allPermission"></table></td>
		</tr>
		<tr>
			
		</tr>
		</table>
	</form>
</div>

<div id="bb">
	<a class="easyui-linkbutton" iconCls="icon-ok" data-cmd="commit">确定</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" data-cmd="cancel">取消</a>
</div> -->
</body>
</html>