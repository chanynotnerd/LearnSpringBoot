<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ssamz.demo.domain.Post"%>
<%@ page import="com.ssamz.demo.domain.User"%>
<%@ page import="com.ssamz.demo.domain.Reply"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.ssamz.demo.security.UserDetailsImpl"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../layout/header.jsp"%>

<br>
<br>
<div class="contaner border">
	<br>
	<div>
		<h3>${post.title}</h3>
	</div>
	<br>
	<div>
		<div>${post.content}</div>
	</div>

	<br>
	<div>
		포스트 번호: <span id="id"><i>${post.id}</i></span><br> 작성자: <span><i>${post.user.username}</i></span>
	</div>

	<hr>
	<div style="display: inline-block;">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

		<div id="editButtons" style="display: none;">
			<a href="" id="updatePostLink" class="btn btn-warning">수정하기</a>
			<button id="btn-delete" class="btn btn-danger">삭제하기</button>
		</div>
	</div>


	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	Post post = (Post) request.getAttribute("post");
	if (post.getReplyList() != null && !post.getReplyList().isEmpty()) {
		// 스프링 시큐리티를 사용하기 때문에 사용자 정보는 UserDetails 객체에 저장되고, 이는 SecurityContext에 저장되며
		// SecurityContextHolder에 의해 관리가 된다. 그렇기에 사용자 정보를 가져오려면 SecurityContextHolder를 사용해야 한다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User currentUser = userDetails.getUser();
		
	    // 로그 출력 코드 추가, 로그인 된 유저 확인용
	    System.out.println("currentUser: " + currentUser);
	    if (currentUser != null) {
	        System.out.println("currentUser's username: " + currentUser.getUsername());
	    }
	%>
	<div class="container mt-3">
		<table class="table">
			<thead>
				<tr>
					<th width="60%">내용</th>
					<th width="10%">작성자</th>
					<th width="20%">시간</th>
					<th width="10%">삭제</th>
					<!-- 항상 삭제 열을 표시 -->
				</tr>
			</thead>
			<tbody>
				<%
				for (Reply reply : post.getReplyList()) {
					
				    // 로그 출력 코드 추가, 댓글 사용자 확인용
				    System.out.println("reply's user: " + reply.getUser());
				    if (reply.getUser() != null) {
				        System.out.println("reply's user's username: " + reply.getUser().getUsername());
				    }
				%>
				<tr>
					<td><%=reply.getContent()%></td>
					<td><%=reply.getUser().getUsername()%></td>
					<td><%=reply.getCreateDate().format(formatter)%></td>
					<td>
						<%
						// 사용자가 로그인한 경우 삭제 버튼 표시.
						if (currentUser != null && reply.getUser().getUsername() != null
								&& reply.getUser().getUsername().equals(currentUser.getUsername())) {
						%>
						<button onclick="replyObject.deleteReply(<%=post.getId()%>,<%=reply.getId()%>)">삭제</button> <%
 }
 %>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
	<%
	}
	%>
	<br> <br>
	<div class="container mt-3">
		<input type="hidden" id="postId" value="${post.id}">
		<table class="table">
			<thead>
				<tr>
					<th><h4>댓글 목록</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr align="right">
					<td><textarea id="reply-comment" rows="1" class="form-control"></textarea>
						<button id="btn-save-reply" class="btn btn-secondary">댓글 등록</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(
			function() {
				var postUser = "${post.user.username}";
				var currentUser = "${principal.username}";

				if (postUser === currentUser) {
					$('#editButtons').css('display', 'inline-block');
					$('#updatePostLink').attr('href',
							"/post/updatePost/" + "${post.id}");
				}
			});
</script>
<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>
<%@ include file="../layout/footer.jsp"%>