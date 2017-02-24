package shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.service.facade.CustomerService;
import shop.service.logic.CustomerServiceLogic;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		//인터페이스로 만드는 이유
		//spring의 핵심기능 DI
		//DI -> Dependency Injection
		CustomerService service = new CustomerServiceLogic();
		
		if(service.login(userId, password)){
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			response.sendRedirect("productList.do");
			
			//id만 넣는 이유, 세션을 최소화 하기 위해서
			//Customer cus = service.getCustomer(userId);
			//session.setAttribute("customer", cus);
		}
		else{
			response.sendRedirect("login.html");
		}
		
		//session으로 attribute를 정의하는 이유
		//모든걸 dispatch로 해버리면(attribute를 정의)
		//url로 접근을 못한다 -> 바로 창 띄우기 불가(즐겨찾기 불가..) -> 모든 페이지의 시작이 한곳이다ㅜㅜ
		//그래서 redirect를 쓰게 된다(url이 남기 때문에..)
		
	}
}
