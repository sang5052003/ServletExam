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

public class SearchDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		MemberServiceLogic service = new MemberServiceLogic();
		
		PrintWriter out =  resp.getWriter();
		
		Member member = service.searchDetail(Integer.parseInt(req.getParameter("no")));
		
		out.println("<html>");
		out.println("<head><title>상세 정보 결과</title><head>");
		
		
		out.println("<body>");
		
		out.println("<hr>");
		
		if(member == null){
			out.println("<span>" + "존재 하지 않는 회원" + "</span><br>");
		}
		else{
			out.println("<span>" + member.toString() + "</span><br>");
		}
		
		out.println("<form action='list.do'>");//태그에서 ""로 해주던 것을 ''로 해줘야한다
		out.println("<input type='submit' value='리스트로 돌아가기'>");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
