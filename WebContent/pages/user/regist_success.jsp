<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>英谷会员注册页面</title>
<base href="http://localhost:8080/new_BookStore/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.png" >
				<span class="wel_word"></span>
				<%@include file="/pages/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index_page">转到主页</a></h1>
	
		</div>
		
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>