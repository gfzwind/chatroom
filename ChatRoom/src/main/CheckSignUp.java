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
	    PreparedStatement pstmt; // ���ݿ��ѯ���
	    ResultSet rs; // ���ݿ��ѯ�����
	    request.setCharacterEncoding("UTF-8");
	    String username=(String)request.getParameter("username");
	    String password=(String)request.getParameter("password");//ȡ��login.jsp��ֵ
	    System.out.println(username);
	    username = username.trim();
	    password = password.trim();
	    
	    
	    //���������ݿ����
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
	    	System.out.print("�������ݿ�ʧ��");
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
