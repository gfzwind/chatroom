package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	// JDBC������
	private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// ���ݿ�URL��ַLocalhost
	private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=chatroom";
	// �������ݿ��û���
	private static final String DATABASE_USRE = "sa";
	// �������ݿ�����
	private static final String DATABASE_PASSWORD = "aa123456";

	public static Connection getConnction() {
		Connection dbConnection = null;
		try {
			Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(DATABASE_URL,
					DATABASE_USRE, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConnection;
	}

	public static void closeConnection(Connection dbConnection) {
		try {
			if (dbConnection != null && (!dbConnection.isClosed())) {
				dbConnection.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

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
	 * �ر����
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
