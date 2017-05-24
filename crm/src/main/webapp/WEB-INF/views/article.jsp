<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="/js/view/article.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="article_datagrid"></table>
	<!-- CRUD按钮 -->
	<div id="article_bt">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a> 
			<a class="easyui-linkbutton" iconCls="icon-edit"  plain="true" data-cmd="edit">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除</a> 
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="refresh">刷新</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="updateIndex">更新索引</a>
		</div>
		<div>
			关键字:<input type="text" id="keyContent">
			<a class="easyui-linkbutton" iconCls="icon-search" data-cmd="search">查询</a>
		</div>
	</div>
	<!-- 编辑框 -->
	<div id="article_dialog">
		<form id="article_dialog_form" action="save.json" method="post">
			<table align="center" style="margin-top: 15px;">
				<input type="hidden" name="id">
				<tr>
					<td>标题</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>内容</td>
					<td><textarea rows="" cols="" name="content"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 底部按钮 -->
	<div id="article_dialog_bb">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a> 
		<a class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" data-cmd="cancel">取消</a>
	</div>
	<!-- 文章显示 -->
	<div id="article_show"></div>
</body>
</html>