package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;
import service.logic.UserServiceLogic;

@WebServlet("/login.do")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = new UserServiceLogic();
		User u = new User();
		u.setLoginId(request.getParameter("loginId"));
		u.setPassword(request.getParameter("password"));
		u = service.login(u);
		
		if(u == null){
			response.sendRedirect("login.jsp");
			return;
		}

		//세션에 올려놓으면 시간(default 30분?)동안 전체페이지 접근 유효
		HttpSession s = request.getSession();
		s.setAttribute("userId", u.getLoginId());
		s.setAttribute("userName", u.getName());

		//request값에 attribute를 셋팅하고 그 속성 값을 가지고 가고 싶다면, dispatch한다
		//여기서는 send redirect로 가능할듯..
		request.getRequestDispatcher("list.do").forward(request, response);
	}

}
