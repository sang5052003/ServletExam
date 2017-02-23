package lecture.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("loginForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		
		//default 30분(session 유지 시간)
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginId);
		session.setAttribute("isAdmin", this.isAdminUser(loginId));
		
		response.sendRedirect("list.do");
	}
	
	private boolean isAdminUser(String loginId){
		List<String> adminUsers = new ArrayList<>();
		adminUsers.add("jin");
		adminUsers.add("kim");
		adminUsers.add("park");
		
		return adminUsers.contains(loginId);
	}

}
