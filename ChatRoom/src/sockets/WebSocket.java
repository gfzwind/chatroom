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
	// websockets.size() -> ��ǰ���ߵ��û�����

	// ��ĳ���ͻ������ӻỰ�����ڸ��ͻ��˷�������
	private Session session;

	private UserBean user; // ��ǰ�û�

	// ���ӳɹ�����ʱ���õķ���
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		this.user = (UserBean) httpSession.getAttribute("UserSession");
		// System.out.println("״̬�룺" + user.getStatus());
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
			System.out.println(user.getUserName() + "�ظ���¼");
		}
	}

	// ���ӹر�ʱ���õķ���
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
		System.out.println("�û�" + user.getUserName() + "���ߣ���ǰ����������"
				+ websockets.size());
	}

	// �յ��ͻ��˵���Ϣʱ���õķ���
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("�������յ���Ϣ" + message);
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

	// ��������ʱ���õĴ���
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	// ͬ�����첽������Ϣ
	public void sendMessage(String message) throws IOException {
		this.session.getAsyncRemote().sendText(message);
		// this.session.getBasicRemote().sendText(message);
	}

	// ��ָ���Ķ��󴫵���Ϣ
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

	// �������û�������Ϣ
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
	
	// ��JSON��ʽ��ʽ��Message
	public static String messageFormat(MessageBean message) {
		return JSONObject.fromObject(message).toString();
	}
}
