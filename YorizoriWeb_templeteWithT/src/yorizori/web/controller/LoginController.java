package yorizori.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yorizori.common.exception.YzRuntimeException;
import yorizori.domain.User;
import yorizori.service.ServiceFactory;
import yorizori.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("userId".equals(cookie.getName())){
					request.setAttribute("userId", cookie.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = ServiceFactory.getUserService();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
	
		String saveIdYn = request.getParameter("saveIdYn");
		
		Cookie cookie = null;
		if("Y".equals(saveIdYn)){
			cookie = new Cookie("userId", userId);
			
			//생존시간을 적어줘야 브라우저 꺼도 쿠키가 살아있는다
			cookie.setMaxAge(3600); 
			
			cookie.setPath("/"); //절대경로
			response.addCookie(cookie);
		}
		else{
			cookie = new Cookie("userId", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		User user = service.findUser(userId);
		
		if (user != null
			&& userId.equals(user.getUserId())
			&& password.equals(user.getPassword())){
			
			//세션에 로그인 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			
			//메인화면으로..
			response.sendRedirect(request.getContextPath());
		}
		else{
			
			//세션정보 무효과(로그아웃)
			HttpSession session = request.getSession(false);
			if(session != null){
				session.invalidate();
			}
			
			//예외처리
			//back으로 이전 페이지 가므로
			//url셋팅이 필요없음 -> 비어있으면 ""
			throw new YzRuntimeException("로그인 정보를 확인하세요");
		}
		
	}

}
