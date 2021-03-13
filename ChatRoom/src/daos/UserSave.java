package daos;

import java.util.ArrayList;
import java.util.List;

import beans.UserBean;

public class UserSave {
	public static List<UserBean> userList = new ArrayList<UserBean>();

	public static List<String> userNameList = new ArrayList<String>();

	// // 获得保存的用户列表
	// public static List<String> getUserNameList(List<UserBean> userList) {
	// List<String> nameList = null;
	// for (int i = 0; i < userList.size(); i++) {
	// nameList.add(userList.get(i).getUserName());
	// }
	// return nameList;
	// }

	public static void talk() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getUserName());
		}
	}
}
