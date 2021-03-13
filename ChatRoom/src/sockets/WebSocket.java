package sockets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import daos.MessageDao;
import daos.UserSave;
import net.sf.json.JSONObject;
import beans.MessageBean;
import beans.UserBean;
import connection.DBConnect;

@ServerEndpoint(value = "/chatroom", configurator = HttpSessionConfigurator.class)
public class WebSocket {

	private static Map<String, WebSocket> websockets = new HashMap<String, WebSocket>();
	// websockets.size() -> 当前在线的用户数量

	// 与某个客户端连接会话，用于给客户端发送数据
	private Session session;

	private UserBean user; // 当前用户

	// 连接成功建立时调用的方法
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		this.user = (UserBean) httpSession.getAttribute("UserSession");
		// System.out.println("状态码：" + user.getStatus());
		UserSave.userList.add(user);
		UserSave.userNameList.add(user.getUserName());
		this.session = session;
		MessageBean message = new MessageBean();
		message.setSender(user.getUserName());
		message.setOnlineCount(String.valueOf(websockets.size()));
		message.setStatus("-1");
		message.setUserNameList(UserSave.userNameList);
		if (!websockets.containsKey(user.getUserName())) {
			websockets.put(user.getUserName(), this);
			// System.out.println(messageFormat(message));
			sendMessageToAll(message);
		} else {
			System.out.println(user.getUserName() + "重复登录");
		}
	}

	// 连接关闭时调用的方法
	@OnClose
	public void onClose() {
		websockets.remove(this.user.getUserName());
		UserSave.userList.remove(this.user);
		UserSave.userNameList.remove(this.user.getUserName());
		MessageBean message = new MessageBean();
		message.setSender(user.getUserName());
		message.setOnlineCount(String.valueOf(websockets.size()));
		message.setStatus("3");
		message.setUserNameList(UserSave.userNameList);
		sendMessageToAll(message);
		System.out.println("用户" + user.getUserName() + "下线，当前在线人数："
				+ websockets.size());
	}

	// 收到客户端的消息时调用的方法
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("服务器收到消息" + message);
		JSONObject jsonObject = JSONObject.fromObject(message);
		MessageBean mess = new MessageBean();
		mess.setStatus(jsonObject.getString("status"));
		mess.setUserNameList(UserSave.userNameList);
		mess.setMessage(jsonObject.getString("message"));
		mess.setSender(jsonObject.getString("sender"));
		if (mess.getStatus().equals("1")) {
			mess.setReceiver(jsonObject.getString("receiver"));
			sendMessageTo(mess, user);
			System.out.println("-----------------");
		} else if (mess.getStatus().equals("2")) {
			sendMessageToAll(mess);
		}
		MessageDao.addChatMessage(mess);
	}

	// 发生错误时调用的代码
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	// 同步和异步传递消息
	public void sendMessage(String message) throws IOException {
		this.session.getAsyncRemote().sendText(message);
		// this.session.getBasicRemote().sendText(message);
	}

	// 给指定的对象传递消息
	public void sendMessageTo(MessageBean message, UserBean user) {
		WebSocket receiver = websockets.get(message.getReceiver());
		WebSocket sender = websockets.get(user.getUserName());
		try {
			synchronized (receiver) {
				receiver.session.getBasicRemote().sendText(
						messageFormat(message));
				System.out.println("---------" + message);
			}
			synchronized (sender) {
				sender.session.getBasicRemote()
						.sendText(messageFormat(message));
				System.out.println("+++++++++" + message);
			}
		} catch (IOException e1) {
			try {
				receiver.session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 给所有用户传递消息
	public void sendMessageToAll(MessageBean message) {
		for (WebSocket websocket : websockets.values()) {
			try {
				synchronized (websocket) {
					message.setReceiver(websocket.user.getUserName());
					websocket.session.getBasicRemote().sendText(
							messageFormat(message));
				}
			} catch (IOException e1) {
				try {
					websocket.session.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	// 以JSON格式格式化Message
	public static String messageFormat(MessageBean message) {
		return JSONObject.fromObject(message).toString();
	}
}
