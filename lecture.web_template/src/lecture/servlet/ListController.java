package lecture.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lecture.domain.Lecture;
import lecture.service.LectureService;
import lecture.service.LectureServiceLogic;

//web.xml에서 servlet mapping해주는 효과
@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LectureService service = new LectureServiceLogic();
		List<Lecture> list = service.findAll();
		request.setAttribute("lectures", list);
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}

}
