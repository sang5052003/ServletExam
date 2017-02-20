package lecture.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.service.LectureService;
import lecture.service.LectureServiceLogic;

@WebServlet("/remove.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LectureService service = new LectureServiceLogic();
		
		service.remove(request.getParameter("id"));
		
		response.sendRedirect("list.do");
	}

}
