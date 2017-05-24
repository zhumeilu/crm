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
	var roleDatagrid = $("#role_datagrid");
	var allPermission = $("#allPermission");
	var selectedPermission = $("#selectedPermission");
	//初始化表格数据
	roleDatagrid.datagrid({
		fit:true,
		fitColumns:true,
		url:'/role_list.do',
		toolbar:"#tb",
		singleSelect:true,
		pagination:true,
	})

	//选择权限时的所有权限
	allPermission.datagrid({
		width:300,
		height:200,
		url:"/permission_queryAllForRole.do",
		fitColumns:true,
		singleSelect:true,
		pagination:true,
		columns:[[
		          {field:"name",title:"权限名称",width:1},
		          {field:"expression",title:"权限表达式",width:1}
		          ]],
		onDblClickRow:function(rowIndex, rowData){
			//双击事件,判断左边的表格中是否已经存在.
			var rows = selectedPermission.datagrid("getRows");
			var flag=true;
			$.each(rows,function(index,item){
				if(item.id==rowData.id){
					flag=false;
					return ;
				}
			})
			if(flag){
				selectedPermission.datagrid("appendRow",rowData);
			}
			
		}
	
	});
	//设置简单分页
	var pager=$("#allPermission").datagrid("getPager");
	pager.pagination({
		displayMsg:'',
		showPageList:false
	})
	
	//选择权限时的已选择权限
	selectedPermission.datagrid({
		width:300,
		height:200,
		url:"",
		fitColumns:true,
		singleSelect:true,
		columns:[[
		          {field:"name",title:"权限名称",width:1},
		          {field:"expression",title:"权限表达式",width:1}
		          ]],
        onDblClickRow:function(rowIndex, rowData){
			//双击事件,判断左边的表格中是否已经存在.
			selectedPermission.datagrid("deleteRow",rowIndex);
		}
	
	});
	var cmdObj={
			add:function(){
				$("#role_dialog").dialog({
					title:'添加',
					closed:false
				})
				//清空表单数据
				$("#role_Form [name=id],#role_Form [name=name],#role_Form [name=description]").val("");
				//清空选择权限的数据
				var rows = selectedPermission.datagrid("getRows");
				$.each(rows,function(index,item){
					selectedPermission.datagrid("deleteRow",index);
				})
			},
			edit:function(){
				//获取选中的一行
				var row=roleDatagrid.datagrid("getSelected");
				if(row){
					//有数据
					//将row中的数据加载到表单中.
					$("#role_Form").form("load",row);
					$("#role_dialog").dialog({
						title:'编辑',
						closed:false
					})
					//加载选择表格数据
					var options = selectedPermission.datagrid("options");
					options.url="/permission_queryForSelected.do?rid="+row.id;
					selectedPermission.datagrid("reload");
				}else{
					//数据为空
					$.messager.alert("温馨提示","请选择一条数据","info");
				}
			},
			del:function(){
				//获取选择的一条数据
				var row=roleDatagrid.datagrid("getSelected");
				if(row){
					
					$.messager.confirm("温馨提示","你确认要删除这条数据吗?",function(r){
						if(r){
							//有数据,发送异步请求
							$.get(
								'/role_delete.do',
								{id:row.id},
								function(data){
									if(data.success){
										//删除成功
										$.messager.alert("温馨提示",data.msg,"info");
										//刷新页面
										roleDatagrid.datagrid("reload");
									}else{
										//删除失败
										$.messager.alert("温馨提示",data.msg,"info");
									}
								},
								"json"
							)
						}
					})
				}else{
					//数据为空
					$.messager.alert("温馨提示","请选择一条数据","info");
				}
					
			},
			reflash:function(){
				roleDatagrid.datagrid("reload");
			},
			commit:function(){
				var url;
				//如果id有值,则编辑,否则保存
				if($("[name='id']").val()){
					url="/role_update.do"
				}else{
					url="/role_save.do"
				}
				$("#role_Form").form("submit",{
					url:url,
					onSubmit:function(param){
						//param["permissions[0].id"]
						var rows = selectedPermission.datagrid("getRows");
						$.each(rows,function(index,item){
							param["permissions["+index+"].id"]=item.id;
						})
					},
					success:function(data){
						var json = $.parseJSON(data);
						if(json.success){
							//添加成功
							$.messager.alert("温馨提示",json.msg,"info");
							//关闭对话框
							$("#role_dialog").dialog("close");
							//清空表单数据
							$("#role_Form").form("clear");
							//刷新页面
							roleDatagrid.datagrid("reload");
						}else{
							//添加失败
							$.messager.alert("温馨提示",json.msg,"info");
						}
					}
				})
			},
			cancel:function(){
				//关闭对话框
				$("#role_dialog").dialog("close");
				//清空表单数据
				$("#role_Form").form("clear");
			},
			searchForm:function(){
				var data=$("#searchForm").serializeArray();
				var param={};
				$.each(data,function(index,obj){
					param[obj.name]=obj.value;
				})
				console.debug(param);
				roleDatagrid.datagrid("load",param);
			}
	}
	
	//给所有的button添加点击事件
	$(".easyui-linkbutton").on("click",function(){
		var cmd = $(this).data("cmd");
		cmdObj[cmd]();
	})

})



</script>
</head>
<body>
<table id="role_datagrid">
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
<div id="role_dialog" class="easyui-dialog" data-options="width:800,height:500,buttons:'#bb',closed:true">
	<form id="role_Form" action="" method="post">
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
</div>
</body>
</html>