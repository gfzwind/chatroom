package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import connection.DBConnect;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
	    PreparedStatement pstmt; // 数据库查询结果
		ResultSet rs; // 数据库查询结果集
		UserBean user;
	    request.setCharacterEncoding("UTF-8");
	    String username=(String)request.getParameter("username");
	    String password=(String)request.getParameter("password");//取出login.jsp的值
	    username = username.trim();
	    password = password.trim();
	    System.out.println(username);;
	    //下面是数据库操作
	    try {
	    	conn = DBConnect.getConnction();
			String strsql = "select * from userinfo where username="+"'"+username+"'";
			pstmt = conn.prepareStatement(strsql);

			rs = pstmt.executeQuery();

		    if(rs.next())
		    {
		    	if(password.equals(rs.getString("password"))) {
		    		user = new UserBean(rs.getString("username"), rs.getString("username"));
		    		request.getSession().setAttribute("UserSession", user);
		    		response.getWriter().append("ChatRoom");
		    		
		    	} else {
		    		response.getWriter().append("error1");
		    	}
		    }
		    else 
		    {
		    	response.getWriter().append("error2");
		    }
		    DBConnect.closeResultSet(rs);
		    DBConnect.closeStatement(pstmt);
		    DBConnect.closeConnection(conn);
	    } catch(SQLException e) {
	    	System.out.print("访问数据库失败");
	    	response.getWriter().append("errorDB");
	    	response.setHeader("refresh", "0;url=login.jsp");
	    	
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
