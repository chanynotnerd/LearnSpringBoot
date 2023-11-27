<%@page import="com.ssamz.demo.domain.Post"%>
<%@page import="org.springframework.data.domain.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./layout/header.jsp"%>

<br>
<div class="container mt-3">
	<%
	if (request.getAttribute("postList") != null) {
		Page<Post> postPage = (Page<Post>) request.getAttribute("postList");
		List<Post> postList = postPage.getContent();
		for (Post post : postList) {
	%>
	<div class="card">
		<div class="card-body">
			<h4 class="card-title"><%=post.getTitle()%></h4>
			<a href="/post/<%=post.getId() %>" class="btn btn-secondary">상세보기</a>
		</div>
	</div>
	<%
	}
	}
	%>
	<br>
	<ul class="pagination justify-content-end">
		<%
		if (request.getAttribute("postList") != null) {
			Page<Post> postPage = (Page<Post>) request.getAttribute("postList");
			int currentPage = postPage.getNumber();
		%>
		<% if (!postPage.isFirst()) { %>
		<li class="page-item"><a class="page-link" href="?page=<%=currentPage - 1%>">이전 페이지</a></li>
		<% } %>
		<% if (!postPage.isLast()) { %>
		<li class="page-item"><a class="page-link" href="?page=<%=currentPage + 1%>">다음 페이지</a></li>
		<% } %>
		<%
		}
		%>
	</ul>
</div>


<%@ include file="./layout/footer.jsp"%>