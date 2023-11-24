// userObject 객체 선언, let 키워드는 선언하는 키워드
let loginObject =
{
	init: function()	// init: 함수는 loginObject 객체가 초기화되어 처음 사용될 준비가 될 때 호출되는 함수,
	{
		$("#btn-login").on("click", () => {	// jQuery를 사용하여 "#btn-login"라는 HTML 요소에 클릭 이벤트 설정
			this.login();		// 클릭되면 login() 함수 호출.
		});
	},

	login: function() {	// 회원가입 요청됨 이라는 알림창을 띄우는 함수.
		alert("로그인 요청됨");
		// 사용자가 입력한 값(username, password) 추출
		let data = {	// user 객체 선언
			username: $("#username").val(),	// $().val() 메소드는 선택한 HTML 요소의 값을 반환하는 jQuery 메소드
			password: $("#password").val()	// #username, #password, #email 전부 각각 해당 id를 가진
											// HTML 요소를 선택하는 selectors 다? 그냥 값 반환해주기 위한 id 정도로 생각하자.
		}
		// Ajax를 이용한 비동기 호출
		// done() 함수 : 요청 처리에 성공했을 때 실행될 코드
		// fail() 함수 : 요청 처리에 실패했을 때 실행될 코드
		$.ajax({
			type: "POST",	// 요청 방식
			url: "/auth/login",	// 요청 경로
			data: JSON.stringify(data),	// user 객체를 JSON 형식으로 변환
			// HTTP의 body에 설정되는 데이터 마임타임
			contentType: "application/json; charset=utf-8"
			// 응답으로 들어온 JSON 데이터를 response로 받는다.
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			location.href = "/jblog/view";
			// 에러 발생 시 error로 에러 정보를 받는다.
		}).fail(function(error) {
			let message = error["data"];
			alert("문제 발생 : " + message);
		});
	},
}

// loginObject 객체의 init() 함수 호출
loginObject.init();	// loginObject.init() 함수를 호출하여 초기화를 진행한 뒤, btn-save 요소의 클릭 이벤트 진행 -> 회원가입 요청됨 알림창 띄우기.
