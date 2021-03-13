import beans.UserBean;
import net.sf.json.JSONObject;


public class Test {
	public static void main(String[] args) {
		UserBean user = new UserBean("123123","dflakj");
		System.out.println(user.getUserName());
	}
}
