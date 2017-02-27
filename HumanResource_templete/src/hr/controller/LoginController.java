package hr.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String loginId = req.getParameter("loginId");
		String passwd = req.getParameter("passwd");
		
		//패스워드가 1234이면 로그인 성공으로..
		if("1234".equals(passwd)){
			//로그인에 성공하면 session에 로그인 아이디 저장
			HttpSession session = req.getSession();
			session.setAttribute("loginId", loginId);
			
			//메뉴페이지로 이동
			resp.sendRedirect(req.getContextPath() + "/views/menu.jsp");
			
		}
		else{
			//로그인에 실패하면 로그인 페이지로..
			//redirect에서는 이런식으로 path작성
			//resp.sendRedirect("views/login.jsp");
			//("/views") -> 라고 쓰는 경우.. url에서 hr이 빠져버린다
			//req.getContextPath() -> hr
			// req.getContextPath() + "/views/login.jsp" = "hr/views/login.jsp"
			// hr/ -> 직접적으로 하지 않는 이유..
			resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
		}
	}

}
