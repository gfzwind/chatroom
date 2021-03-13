package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.MessageBean;
import beans.UserBean;
import connection.DBConnection;

public class MessageDao {
	private static Connection conn; // ���ݿ�����
	private static PreparedStatement pstmt; // ���ݿ��ѯ���

	// ���������¼
	public static boolean addChatMessage(MessageBean message) {
		boolean flag = false;
		try {
			conn = DBConnection.getConnction();
			String strsql = "insert into MessageInfo values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, message.getSender());
			pstmt.setString(2, message.getReceiver());
			pstmt.setString(3, message.getMessage());
			pstmt.setString(4, message.getOnlineCount());
			pstmt.setString(5, message.getStatus());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
