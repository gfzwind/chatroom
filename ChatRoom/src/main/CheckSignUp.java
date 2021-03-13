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

import connection.DBConnect;
/**
 * Servlet implementation class CheckSignUp
 */
@WebServlet("/CheckSignUp")
public class CheckSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
	    PreparedStatement pstmt; // 数据库查询结果
	    ResultSet rs; // 数据库查询结果集
	    request.setCharacterEncoding("UTF-8");
	    String username=(String)request.getParameter("username");
	    String password=(String)request.getParameter("password");//取出login.jsp的值
	    System.out.println(username);
	    username = username.trim();
	    password = password.trim();
	    
	    
	    //下面是数据库操作
	    try {
	    	conn = DBConnect.getConnction();
			String strsql = "select * from userinfo where username="+"'"+username+"'";
			pstmt = conn.prepareStatement(strsql);

			rs = pstmt.executeQuery();
		    if(rs.next())
		    {
		    	response.getWriter().append("error1");
		    }
		    else 
		    {
		    	if(password.equals("")) {
			    	response.getWriter().append("error2");
		    	} else {
		    		strsql = "insert into userinfo(username,password) values('"+username+"','"+password+"')";
					pstmt = conn.prepareStatement(strsql);

					pstmt.executeUpdate();
					response.getWriter().append("status");
		    	}
		    }
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
