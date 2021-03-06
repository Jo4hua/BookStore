<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/pages/common/head.jsp"%>
<script type="text/javascript">
$(function () {
	$("button.addToCart").click(function () {
		var bookId = $(this).attr("bookId");
		location.href ="add_item?id="+bookId;
	});
});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" width="360px" height="80px" alt="" src="static/img/logo.png" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty user}">
					<a href="pages/user/login.jsp">登录</a> | 
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty user}">
					<a href="pages/order/order.jsp">我的订单</a> | 
					<a href="log_out">注销</a> &nbsp;&nbsp;
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="index_page" method="get">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 - 
						<input id="max" type="text" name="max" value="${param.max}"> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<c:if test="${empty cart.items}">
				<span> </span>
				<div style="text-align: center">
					<span style="color: red">当前购物车为空</span>
				</div>
			</c:if>
			<c:if test="${not empty cart.items}">
				<div style="text-align: center">
					<span>您的购物车中有${cart.getTotalCount()}件商品</span>
					<div>
						您刚刚将<span style="color: red">${lastName}</span>加入到了购物车中
					</div>
				</div>
			</c:if>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="static/img/default.png" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		</div>
	<%@include file="/pages/common/page_nav.jsp"%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>