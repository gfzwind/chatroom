package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sockets.WebSocket;
import beans.UserBean;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// ��¼����
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		UserBean userSession = (UserBean) requ.getSession().getAttribute(
				"UserSession");
		// if (userSession != null)
		// System.out.println(userSession.getUserName() + " "
		// + userSession.getPassword());
		if (userSession == null) {
			res.sendRedirect("/ChatRoom/login.jsp");
		} else {
			arg2.doFilter(request, response);
			// System.out.println(userSession.toString());
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("��ȡweb.xml����");
		String site_init = config.getInitParameter("site_init");
		System.out.println("Filter��������Ϣ��" + site_init);
	}
}
