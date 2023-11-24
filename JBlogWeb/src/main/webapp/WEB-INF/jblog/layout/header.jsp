<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
<link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
<script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/jblog/view">Main</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="mynavbar">
				<%
                    // 세션에서 principal 객체 가져오기.
                    // 세션에 저장된 principal 객체는 로그인한 사용자의 정보를 담고있으므로
                    // 이 객체의 값이 null이면 로그인 되지 않은 상태이므로 이를 통해 로그인 유효 검사를 확인.
                    HttpSession session1 = request.getSession();
                    Object principal = session1.getAttribute("principal");
                    
                    if (principal == null) {
                %>
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
				</ul>
				<%
                    } else {
                %>
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="/user/updateUser">회원 상세</a></li>
					<li class="nav-item"><a class="nav-link" href="/post/insertPost">포스트 등록</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/logout">로그아웃</a></li>
				</ul>
				<%
                    }
                %>

				<form class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Search">
					<button class="btn btn-primary" type="button">Search</button>
				</form>
			</div>
		</div>
	</nav>