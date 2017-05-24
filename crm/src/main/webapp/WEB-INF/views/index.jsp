<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
$(function(){
	//设置菜单树显示
	$("#index_tree").tree({
		url:'tree.json',
	});
	//点击菜单树,先判断是否已经打开过相应的页面
	$("#index_tree").tree({
		"onClick":function(node){
			//如果该标签已经打开,则选中
			if($("#index_tabs").tabs("exists",node.text)){
				$("#index_tabs").tabs("select",node.text);
			}else{
				$("#index_tabs").tabs("add",{
					title:node.text,
					selected:true,
					closable:true,
					content:"<iframe src='"+node.attributes.url+"' style='width:100%;height:100%' frameborder='0'></iframe>"
				})
			}
		}
	})
	
})
</script>
</head>
<body>

<div class="easyui-layout" fit="true">   
    <div data-options="region:'north'" style="height:100px;background: url('/image/banner-pic.gif') no-repeat;background-size:cover">
    	<h1 align="center">客户关系管理系统</h1>
    	<div align="right" style="margin-right: 30px;">当前用户:${USER_IN_SESSION.nickname}<a href="/login.jsp">注销</a></div>
    </div>   
    <div data-options="region:'west'" style="width:130px;">
    	<div id="index_accordion" class="easyui-accordion" fit="true">
    		<div title="系统菜单" iconCls="icon-sum" >
    			<div id="index_tree" class="easyui-tree"></div>
    		</div>
    		<div title="帮助" iconCls="icon-help"></div>
    	</div>
    </div>   
    <div data-options="region:'center'" style="background:#eee;">
    	<div id="index_tabs" class="easyui-tabs" fit="true">
    		<div data-options="title:'欢迎',iconCls:'icon-ok'" closable="true">
    			<h3 align="center">欢迎进入本系统</h3>
    		</div>
    	</div>
    </div>   
    <div data-options="region:'south'" style="height:100px;background: url('/image/banner-pic.gif') no-repeat;background-size:cover">
    	<h5 align="center">版权所有@</h5>
    </div>   
</div> 

</body>
</html>