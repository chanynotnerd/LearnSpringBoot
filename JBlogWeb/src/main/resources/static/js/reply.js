let replyObject = {
	init: function() {
		$("btn-save-reply").on("click", () => {
			this.insertReply();
		});
	},

	insertReply: function() {
		alert("댓글 등록 요청됨");

		let id = $("#postId").val();

		let reply = {
			content: $("#reply-content")
		}

		$.ajax({
			type: "POST",
			url: "/reply/" + id,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location = "/post/" + id;
		});
	},
}

replyObject.init();