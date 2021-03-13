<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/chatroom.css">
</head>
<body>
	<div id="background"></div>	
    <div id="container">
        <div id="left">
            <div id="person-info">
            </div>
            <div id="person-list">
            </div>
        </div>
        <div id="right">
            <div id="content">
                <div id="content-top">欢迎来到聊天室</div>
                <div id="content-area">
                </div>
            </div>
            <div id="text-container">
                <div id="text-content">               
                </div>
                <textarea id="text-area" placeholder="请输入聊天内容" maxlength="40"></textarea>
                <div id="text-submit">
                    <input type="button" id="text-button" value="发送消息">
                </div>
            </div>
        </div>
    </div>
    <script src="js/ChatRoom.js"></script>
</body>
</html>