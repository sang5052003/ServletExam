package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Music;
import service.MusicService;
import service.UserMusicService;
import service.logic.MusicServiceLogic;

@WebServlet("/detail.do")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MusicService service = new MusicServiceLogic();
		
		request.setCharacterEncoding("utf-8");
		
		//id가 detail.do?musicId=
		Music m = service.find(Integer.parseInt(request.getParameter("musicId")));
		
		request.setAttribute("music", m);
		
		HttpSession session = request.getSession(false);

		request.getRequestDispatcher("detail.jsp").forward(request, response);
		//세션등록 안되어있으면..
		if(session == null || session.getAttribute("userId") == null){
			
			//request.getRequestDispatcher("detail.jsp").forward(request, response);
			
//			request.setAttribute("userId", session.getAttribute("userId").toString());
//			request.setAttribute("userName", session.getAttribute("userName").toString());
		}
		else{
			
			//response.sendRedirect("detail.jsp");
		}
	
		
	}

}
