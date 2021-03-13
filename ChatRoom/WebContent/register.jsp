<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Register</title>
<style>
body {
	margin: 0;
	padding: 0;
	background-image: url('./images/login-bg.jpg');
	background-repeat: no-repeat;
	background-position: center top;
}

#loginBox {
	border: 2px solid cadetblue;
	border-radius: 5px;
	height: 300px;
	width: 400px;
	margin: 130px auto;
	background-image: url('./images/login.png');
	background-position: center -10px;
	background-size: 250% 200%;
	background-repeat: no-repeat;
}

.input {
	position: relative;
	height: 30px;
	margin: 12px auto;
	text-align: center;
}

.login {
	display: inline;
}

#hint {
	width: 100px;
	position: absolute;
	right: -25px;
	top: 0px;
	color: red;
}
</style>
<script src="./jquery.min.js"></script>
<script>
	window.onload = function() {

		var name = document.getElementsByClassName("login")[1];
		var info = {};

		name.onblur = function() {
			info.name = name.value;
			check(info);
		}
		var confirm = document.getElementsByClassName("button")[1];

		confirm.onclick = function() {
			submit();
		}

		function check(info) {
			$.ajax({
				url : "registercheck",
				type : "get",
				dataType : "text",
				contentType : "application/text;charset=utf-8",
				data : info,
				success : function(data) {
					document.getElementById("hint").innerText = data;
				},
				error : function(XMLHttpResponse, textStatus, errorThrown) {
					console.log("1 异步调用返回失败,XMLHttpResponse.readyState:"
							+ XMLHttpResponse.readyState);
					console.log("2 异步调用返回失败,XMLHttpResponse.status:"
							+ XMLHttpResponse.status);
					console.log("3 异步调用返回失败,textStatus:" + textStatus);
					console.log("4 异步调用返回失败,errorThrown:" + errorThrown);
				}
			});
		}

		function transfer(account) {
			$.ajax({
				url : "register",
				type : "get",
				dataType : "text",
				contentType : "application/text;charset=utf-8",
				data : account,
				success : function(data) {
					if (data == "true") {
						alert("注册成功");
						window.location = "./login.jsp";
					} else if (data == "false") {
						alert("该用户已存在");
					} else {
						alert("未知错误");
					}
				},
				error : function(XMLHttpResponse, textStatus, errorThrown) {
					console.log("1 异步调用返回失败,XMLHttpResponse.readyState:"
							+ XMLHttpResponse.readyState);
					console.log("2 异步调用返回失败,XMLHttpResponse.status:"
							+ XMLHttpResponse.status);
					console.log("3 异步调用返回失败,textStatus:" + textStatus);
					console.log("4 异步调用返回失败,errorThrown:" + errorThrown);
				}
			});
		}

		function submit() {
			var name = document.getElementsByClassName("login")[1];
			var password = document.getElementsByClassName("login")[3];
			var passwordCheck = document.getElementsByClassName("login")[5];
			var phone = document.getElementsByClassName("login")[7];
			var code = document.getElementsByClassName("login")[9];
			var account = {};
			if (name.value.length == 0) {
				alert("用户名不能为空");
			} else if (password.value.length < 6) {
				alert("密码不能小于六位");
			} else if (password.value != passwordCheck.value) {
				alert("两次输入的密码不一致！");
			} else if (code.value.length == 0) {
				alert("验证码不为空！");
			} else {
				account.name = name.value;
				account.password = password.value;
				account.phone = phone.value;
				//console.log(account);
				transfer(account);
				//window.location.href = "./login.jsp";
			}
		}

	}
</script>
</head>

<body>
	<div id="loginBox">
		<div class="input">
			<p class="login">&nbsp&nbsp&nbsp用户名:</p>
			<input type="text" class="login" placeholder="用户名">
			<div id="hint"></div>
		</div>
		<div class="input">
			<p class="login">设置密码:</p>
			<input type="text" class="login" placeholder="设置密码">
		</div>
		<div class="input">
			<p class="login">确认密码:</p>
			<input type="text" class="login" placeholder="确认密码">
		</div>
		<div class="input">
			<p class="login">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp手机号码:</p>
			<input type="text" class="login" placeholder="手机号码"> <input
				type="button" value="Go" class="button">
		</div>
		<div class="input">
			<p class="login">&nbsp&nbsp&nbsp验证码:</p>
			<input type="text" class="login" placeholder="验证码">
		</div>
		<div class="input">
			<input type="button" value="确认" class="button">
		</div>
	</div>
</body>

</html>