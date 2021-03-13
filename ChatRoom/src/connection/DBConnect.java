/*
 * 鍔熻兘锛氫笌SqlServer鏁版嵁搴撳彇寰楄繛鎺�
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 鏁版嵁搴撹繛鎺ョ被
 * 
 * @param DRIVER_CLASS
 *            SqlServer JDBC椹卞姩绫�
 * @param DATABASE_URL
 *            鏁版嵁搴撹繛鎺RL
 * @param DATABASE_USRE
 *            鐢ㄦ埛鍚�
 * @param DATABASE_PASSWORD
 *            瀵嗙爜
 * 
 */
public class DBConnect {
	
	// JDBC椹卞姩绫�
	private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// 鏁版嵁搴揢RL鍦板潃localhost
	private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=chatroom";
	// 杩炴帴鏁版嵁搴撶敤鎴峰悕
	private static final String DATABASE_USRE = "sa";
	// 杩炴帴鏁版嵁搴撳瘑鐮�
	private static final String DATABASE_PASSWORD = "aa123456";

	/**
	 * 杩斿洖杩炴帴
	 * 
	 * @return Connection
	 */
	public static Connection getConnction() {
		Connection dbConnection = null;
		try {
			Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(DATABASE_URL,DATABASE_USRE, DATABASE_PASSWORD);
		} catch (Exception e) {
			System.out.println("连接错误");
			e.printStackTrace();
		}
		return dbConnection;
	}

	/**
	 * 鍏抽棴杩炴帴
	 * 
	 * @param dbConnection
	 *            Connection
	 */
	public static void closeConnection(Connection dbConnection) {
		try {
			if (dbConnection != null && (!dbConnection.isClosed())) {
				dbConnection.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	/**
	 * 鍏抽棴缁撴灉闆�
	 * 
	 * @param res
	 *            ResultSet
	 */
	public static void closeResultSet(ResultSet res) {
		try {
			if (res != null) {
				res.close();
				res = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 鍏抽棴璇彞
	 * 
	 * @param pStatement
	 *            PreparedStatement
	 */

	public static void closeStatement(PreparedStatement pStatement) {
		try {
			if (pStatement != null) {
				pStatement.close();
				pStatement = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
