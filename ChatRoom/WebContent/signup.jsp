<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>注册页面</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
	<img id="login-ground" src="img/login-ground.jpg" alt="">
    <div class="word-big">注册用户</div>
    <div class="word-small">注册成为聊天室用户</div>
    <form id="login-form" action="#">
    	<div id="tip"></div>
        <p><input id="user" type="text" placeholder="账号"></p>
        <p><input id="pwd" type="password" placeholder="密码"></p>
        <p><input id="pwdagin" type="password" placeholder="再次输入密码"></p>
        <p><input id="signup" class="form-button" type="button" value="注册"></p>
        <div class="form-up"><a href="./login.jsp">返回登入</a></div>
    </form>
<script src="js/jquery-min.js"></script>
<script>
	var signIn = document.getElementById("user");
	var pwd = document.getElementById("pwd");
	var pwdAgin = document.getElementById("pwdagin");
	var signUp = document.getElementById("signup");
	var tip = document.getElementById("tip");
	var json = {};
	
	signIn.onblur = function() {
		if(signIn.value.trim() === "") {
			tip.innerText = "请输入名字";
			signIn.style.borderColor = "red";
		} else {
			tip.innerText = "";
			signIn.style.borderColor = "#ddd";
		}
	}
	
	pwd.onblur = function() {
		if(pwd.value.trim() === "") {
			tip.innerText = "请输入密码";
			pwd.style.borderColor = "red";
		} else {
			tip.innerText = "";
			pwd.style.borderColor = "#ddd";
		}
	}
	
	pwdAgin.onblur = function() {
		if(pwd.value.trim() !== pwdAgin.value.trim()) {
			tip.innerText = "两次密码不正确";
			pwdAgin.style.borderColor = "red";
		} else {
			tip.innerText = "";
			pwdAgin.style.borderColor = "#ddd";
		}
	}
	
	signUp.onclick = function() {
		if(signIn.value.trim() === "" || pwd.value.trim() === "" || pwd.value.trim() !== pwdAgin.value.trim()) {
			alert("数据不正确，不可登入");
			return;
		}
		if(pwd.value.trim().length < 6) {
			alert("密码长度太短");
			return;
		}
		json.username = signIn.value;
		json.password = pwd.value;
		
		$.ajax({
			type: "post",
			url: "CheckSignUp",
			data: json,
			dataType: "text",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success: function(data) {
				if(data==="status") {
					alert("注册成功，两秒钟后跳转到登入页面");
					setTimeout(function() {
						window.location="login.jsp";
					}, 2000);
				} else if (data==="error1") {
					alert("用户已存在");
				} else if (data==="error2") {
					alert("密码为空");
				} else {
					alert(data);
				}
			},
			error: function() {
				alert("无法连接服务器");
			}
		});
	}
</script>
</body>
</html>