<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<td>
				<textarea id="reply-comment" rows="1" class="form-control"></textarea>
				<button id="btn btn-save-reply" class="btn btn-secondary">댓글 등록</button>
			</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function() {
    var postUser = "${post.user.username}";
    var currentUser = "${principal.username}";

    if (postUser === currentUser) {
        $('#editButtons').css('display', 'inline-block');
        $('#updatePostLink').attr('href', "/post/updatePost/" + "${post.id}");
    }
});
</script>
<script src="/js/post.js"></script>
<script src="/js/reply.js"></script>
<%@ include file="../layout/footer.jsp"%>