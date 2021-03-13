package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import connection.DBConnection;
import beans.UserBean;

public class UserDao {

	private static Connection conn; // ���ݿ�����
	private static PreparedStatement pstmt; // ���ݿ��ѯ���

	// �������û�
	public static boolean addUser(UserBean user) {
		boolean flag = false;
		try {
			conn = DBConnection.getConnction();
			String strsql = "insert into UserInfo values(?,?)";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// ɾ����ǰ�û�
	public static boolean deleteUser(UserBean user) {
		boolean flag = false;
		try {
			conn = DBConnection.getConnction();
			String strsql = "delete from UserInfo where UserName = ? and Password = ?";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// �޸��û�����
	public static boolean updateUser(UserBean user) {
		boolean flag = false;
		try {
			conn = DBConnection.getConnction();
			String strsql = "update UserInfo set Password =? where UserName = ?";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(2, user.getUserName());
			pstmt.setString(1, user.getPassword());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// ��ѯ�Ƿ����ͬ���û�
	public static boolean checkUserName(UserBean user) {
		boolean flag = true;
		try {
			conn = DBConnection.getConnction();
			String strsql = "select * from UserInfo where UserName = ?";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, user.getUserName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = false;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// ��¼��֤
	public static boolean checkUser(UserBean user) {
		boolean flag = false;
		try {
			conn = DBConnection.getConnction();
			String strsql = "select * from UserInfo where UserName = ? and Password = ?";
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = true;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// // ��ѯ�����б�
	// public static List<String> queryFriends(UserBean user) {
	// List<String> friends = null;
	// try {
	// conn = DBConnection.getConnction();
	// String strsql =
	// "select * from UserInfo where UserName = ? and Password = ?";
	// pstmt = conn.prepareStatement(strsql);
	// pstmt.setString(1, user.getUserName());
	// pstmt.setString(2, user.getPassword());
	// ResultSet rs = pstmt.executeQuery();
	// String users[] = null;
	// while (rs.next()) {
	// users = rs.getString("friends").split("-");
	// System.out.println(1);
	// }
	// for (int i = 0; i < users.length; i++) {
	// System.out.println(users[i]);
	// // friends.add(users[i]);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return friends;
	// }
	//
	// public static void main(String[] args) {
	// UserBean user = new UserBean("123", "123");
	// List<String> friends = UserDao.queryFriends(user);
	// // for (int i = 0; i < friends.size(); i++) {
	// // System.out.println(friends.get(i));
	// // }
	// }
}
