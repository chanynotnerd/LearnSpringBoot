<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container mt-3">
	<form action="/auth/login" method="post">
		<div class="mb-3">
			<label for="uname"><spring:message code="user.login.form.username"/>
			</label> <input type="text" class="form-control" name="username" placeholder="Enter username" value="test">
		</div>
		<div class="mb-3">
			<label for="pwd"><spring:message code="user.login.form.password"/>
			</label> <input type="password" class="form-control" name="password" placeholder="Enter password" value="test123">
			<button id="btn-login" class="btn btn-secondary" type="submit">로그인</button>
		</div>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
