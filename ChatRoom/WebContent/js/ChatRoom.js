window.onload = function() {
	var websocket = null;
	var myCode = "";
    var textContent = document.getElementById("text-content");
    var personList = document.getElementById("person-list");
    var userList = personList.getElementsByTagName("div");
    var textButton = document.getElementById("text-button");
    var textArea = document.getElementById("text-area");
    var contentArea = document.getElementById("content-area");
    var receiverUser = "";
    
	//判断浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/ChatRoom/chatroom");
	} else {
		alert('当前浏览器 Not support websocket');
	}

	//连接时发生错误回调的方法
	websocket.onerror = function() {
		alert("错误");
	}

	//连接成功回调的方法
	websocket.onopen = function() {
		//alert("成功");
	}

	function accountSet(data) {
		var name = document.getElementById("person-info");
		name.innerText = data.receiver;
		myCode = data.receiver;
	}

	function userListUpdate(data) {
		personList.innerHTML = "";
		
		var sendToAll = document.createElement("div");
		sendToAll.innerText = "发送给所有人";
		sendToAll.onclick = function() {
			receiverUser = "";
			textContent.innerHTML = "";
		}
		personList.appendChild(sendToAll);
		
		for (var i = 0; i < data.userNameList.length; i++) {
			var user = document.createElement("div");

			user.innerText = data.userNameList[i];
			personList.appendChild(user);
			user.onclick = function() {
	            receiverUser = this.innerText;
	            textContent.innerHTML = "你将发送给：<strong>" + this.innerHTML+"</strong> 消息";
	        };
		}
	}
	
	function sendMessage(data) {
		if(data.receiver == data.sender && data.status == 1) return;
			
		var divOut = document.createElement("div");
		var div1 = document.createElement("div");
		var div2 = document.createElement("div");
		var div3 = document.createElement("div");
		
		if(data.status == 1) {
			if(myCode != data.sender) {
				div3.className = "single";
				div3.innerText = "私聊";
			} else {
				div3.className = "singleTo";
				div3.innerText = "私聊："+data.receiver;
			}
		} else {
			div3.className = "";
		}
		
		if(myCode != data.sender) {
			divOut.className = "others";
			div1.className = "others-name";
			div2.className = "others-word";
		} else {
			divOut.className = "myself";
			div1.className = "myself-name";
			div2.className = "myself-word";
		}
		
		div1.innerText = data.sender;
		div2.innerText = data.message;
		divOut.appendChild(div3);
		divOut.appendChild(div1);
		divOut.appendChild(div2);
		contentArea.appendChild(divOut);
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		var data = JSON.parse(event.data);
		console.log(data);
		if (data.status == -1) {
			userListUpdate(data);
			accountSet(data);
		} else if (data.status == 1 || data.status == 2) {
			sendMessage(data);
		} else if (data.status == 3) {
			userListUpdate(data);
		}
	}

	//连接关闭时的回调方法
	websocket.onclose = function() {
		alert("连接关闭");
	}

	//强制关闭浏览器时调用该方法
	window.onunload = function() {
		closeWebSocket();
	}

	//刷新浏览器之前调用
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	//显示接收到的消息
	function setMessage(message) {
//		alert(message);
	}

	//关闭websocket连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send() {
		var message = {};
		if (receiverUser == "all" || receiverUser == "" || receiverUser == null || receiverUser == undefined) {
			message.receiver = "all";
			message.status = "2";
		} else {
			message.receiver = receiverUser;
			message.status = "1";
		}
		message.sender = myCode;

		message.message = textArea.value;
		websocket.send(JSON.stringify(message));
	};
	
    textButton.onclick = function() {
        send();
        textArea.value = "";
    }	
}

