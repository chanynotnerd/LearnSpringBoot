<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Main</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mynavbar">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="/auth/insertUser">회원가입</a></li>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Search">
					<button class="btn btn-primary" type="button">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="container mt-3">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">포스트 제목</h4>
				<a href="#" class="btn btn-secondary">상세보기</a>
			</div>
		</div>
	</div>
	<br>
	<div class="mt-5 p-4 text-center">
		<p>Create by Chanynotnerd</p>
		<p>연락처: 010-2044-1850, 주소지: 서울시 강남구</p>
		<p>designed by w3school/BS5</p>
	</div>
</body>
</html>

