<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mt-3">
	<form action="/auth/login" method="post">
		<div class="mb-3">
			<label for="uname">Username:</label> <input type="text" class="form-control" id="username" placeholder="Enter username">
		</div>
		<div class="mb-3">
			<label for="pwd">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password">
		</div>
	</form>
	<button id="btn-login" class="btn btn-secondary" type="submit">로그인</button>
</div>

<script src="/js/login.js"></script>
<%@ include file="../layout/footer.jsp"%>
