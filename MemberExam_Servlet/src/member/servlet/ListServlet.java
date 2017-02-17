package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.domain.Member;
import member.service.MemberServiceLogic;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		//resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8"); //넘겨주는 데이터가 html이다 라는 의미
		
		PrintWriter out = resp.getWriter();
		
		MemberServiceLogic service = new MemberServiceLogic();
		
		List<Member> list = service.searchAll();
		
		out.println("<html>");
		out.println("<head><title>전체 멤버</title></head>");
		out.println("<body>");
		
		//action='searchByName.do' -> searchByName.do페이지로 넘어가라 
		out.println("<form action='searchByName.do'>");//태그에서 ""로 해주던 것을 ''로 해줘야한다
		out.println("<input type='text' name='name'>");
		out.println("<input type='submit' value='검색'>");
		out.println("</form>");
		
		out.println("<hr>");
		
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>이름</th><th>이메일</th><th>삭제</th>");
		out.println("</tr>");
		
		out.println("<tbody>");
		
		for(Member m : list){
			out.println("<tr>");
			//'detail.do?no=m.getNo()'
			//<a 태그 링크로 상세페이지 버튼처럼 만든다
			//get방식(url로 보냄)으로 정보를 보낼 때 사용
			//action='detail.do' -> href='detail.do'
			//?(찾는게 누구냐) no(컬럼명) = 실제값
			out.println("<td><a href='detail.do?no=" + m.getNo() + "'>"
			+ m.getName() + "</a></td><td>" + m.getEmail() + "</td>"
			+ "<td><a href='delete.do?no=" + m.getNo() + "'>[삭제]</a></td>"); //"\"");
			out.println("</tr>");
			
			//이 지랄을 편하게 도와주는게 jsp다!!!
			//jsp는 편하게 html을 작성하는것처럼 하면, 자동으로 이런식의 servlet을 만들어 준다
		}
		
		out.println("</tbody>");
		out.println("</table>");
		
		
		
		out.println("<form action='registerForm.html'>");//태그에서 ""로 해주던 것을 ''로 해줘야한다
		out.println("<input type='submit' value='등록으로 돌아가기'>");
		//redirect추가
		//resp.sendRedirect("register.do");//가입 성공 시
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
