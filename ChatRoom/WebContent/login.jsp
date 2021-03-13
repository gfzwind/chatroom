<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>登入多人聊天室</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
	<img id="login-ground" src="img/login-ground.jpg" alt="">
    <div class="word-big">小型即时聊天室</div>
    <div class="word-small">登入聊天室，大家一起聊天吧</div>
    <form id="login-form" action="#">
        <div class="form-word">登入聊天室</div>
        <hr>
        <p><input id="user" type="text" placeholder="账号"></p>
        <p><input id="pwd" type="password" placeholder="密码"></p>
        <p><input id="signin" class="form-button" type="button" value="登入"></p>
        <div class="form-up">
            <a href="./signup.jsp">注册</a>
        </div>
    </form>
<script src="js/jquery-min.js"></script>
<script>
	var signIn = document.getElementById("signin");
	var user = document.getElementById("user");
	var pwd = document.getElementById("pwd");
	var json = {};

	signIn.onclick = function() {
		json.username = user.value;
		json.password = pwd.value;
		
		$.ajax({
			type: "post",
			url: "CheckLogin",
			data: json,
			dataType: "text",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success: function(data) {
				if(data == "error1") {
					alert("密码错误");
				} else if(data == "error2") {
					alert("当前用户不存在");
				} else if (data == "errorDB"){
					alert("数据库连接错误");
				} else {
					window.location=data+".jsp	";
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