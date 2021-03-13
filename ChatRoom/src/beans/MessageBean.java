package beans;

import java.util.List;

public class MessageBean {
	private String sender;
	private String receiver;
	private String message;
	private String onlineCount;
	private List<String> userNameList;
	private String status;

	/*
	 * 0:用户登录返回的消息： sender -> 当前用户 onlineCount -> 在线用户数量 userNameList -> 用户列表
	 * statu：0 -> 状态码
	 * 
	 * 1:用户给指定对象发送消息： sender -> 当前用户 receiver -> 接收者 all->所有人 message -> 消息
	 * statu：1 -> 状态码
	 */

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(String onlineCount) {
		this.onlineCount = onlineCount;
	}

	public List<String> getUserNameList() {
		return userNameList;
	}

	public void setUserNameList(List<String> userNameList) {
		this.userNameList = userNameList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void talk() {
		System.out.println("status:" + this.status + " receiver:"
				+ this.receiver + " sender:" + this.sender + " message:"
				+ this.message);
	}
}
