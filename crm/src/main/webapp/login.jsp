<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小码哥客户关系管理系统</title>
<link rel="stylesheet" href="css/style.css">
<%@include file="/common/common.jsp" %>
<script type="text/javascript">
$(function(){
	$(document).on("keyup",function(obj){
		if(obj.keyCode==13){
			submitForm();
		}
	});
})
	function submitForm(){
		$("#myForm").form("submit",{    
		    url:"/login.do",    
		    success:function(data){
		    	var json=$.parseJSON(data);
		    	if(json.success){
		    		window.location.href="/index.do";
		    	}else{
		    		$.messager.alert("温馨提示",json.msg,"info")
		    	} 
		    }    
		});  
	
	}
</script>
</head>
<body>
<%session.invalidate(); %>
  <section class="container">
    <div class="login">
      <h1>用户登录</h1>
      <form id="myForm" method="post">
        <p><input type="text" name="username" value="admin" placeholder="账号"></p>
        <p><input type="password" name="password" value="123" placeholder="密码"></p>
        <p class="submit">
        	<input type="button" value="登录" onclick="submitForm()">
        	<input type="button" value="重置" onclick="resetForm()">
        </p>
      </form>
    </div>
  </section>
<div style="text-align:center;" class="login-help">
	<p>Copyright ©2015 广州小码哥教育科技有限公司</p>
</div>
</html>