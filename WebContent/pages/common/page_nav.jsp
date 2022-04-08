<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page_nav">
		<c:if test="${page.pageNo>1}">
			<a href="${page.url}pageNo=1">首页</a>
			<a href="${page.url}pageNo=${page.pageNo-1}">上一页</a>
		</c:if>
		<c:choose>
			<c:when test="${page.pageTotal<=5}">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="${page.pageTotal}"/>	
			</c:when>
			<c:when test="${page.pageTotal>5}">
				<c:choose>
					<c:when test="${page.pageNo<=3}">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="5"/>
					</c:when>
					<c:when test="${page.pageNo>page.pageTotal-3}">
						<c:set var="begin" value="${page.pageTotal-4}"/>
						<c:set var="end" value="${page.pageTotal}"/>
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${page.pageNo-2}"/>
						<c:set var="end" value="${page.pageNo+2}"/>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		<c:forEach begin="${begin}" end="${end}" var="i">
			<c:if test="${i == requestScope.page.pageNo}">
				【${i}】
			</c:if>
			<c:if test="${i != requestScope.page.pageNo}">
				<a href="${page.url}pageNo=${i}">${i}</a>
			</c:if>
		</c:forEach>
		<c:if test="${page.pageNo<page.pageTotal}">
			<a href="${page.url}pageNo=${page.pageNo+1}">下一页</a>
			<a href="${page.url}pageNo=${page.pageTotal}">末页</a>
		</c:if>
			共${page.pageTotal}页，${page.pageTotalCount}条记录
			到<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn"  type="button" value="确定">
			<script type="text/javascript">
				$(function () {
					$("#searchPageBtn").click(function () {
						var pageNo = $("#pn_input").val();
					location.href = "${page.url}pageNo=" + pageNo;
				});
				});
			</script>
		</div>
	</div>