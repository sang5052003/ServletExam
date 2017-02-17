package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.domain.Member;
import member.service.MemberServiceLogic;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberServiceLogic service = new MemberServiceLogic();
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		Member member = new Member();
		
		member.setEmail(req.getParameter("email"));
		member.setName(req.getParameter("name"));
		member.setPassword(req.getParameter("password"));
		
		out.println("<html>");
		out.println("<head><title>가입결과 페이지</title><head>");
		
		out.println("<body>");
		if(service.registerMember(member)){
			//out.println("<h1>가입 완료</h1>");
			//redirect추가
			resp.sendRedirect("list.do");//가입 성공 시
		}
		else{
			out.println("<h1>가입  실패</h1>");
		}
		
		
	}
}
