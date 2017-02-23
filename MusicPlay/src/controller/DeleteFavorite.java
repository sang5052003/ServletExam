package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserMusicService;
import service.logic.UserMusicServiceLogic;

@WebServlet("/deleteFavorite.do")
public class DeleteFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserMusicService service = new UserMusicServiceLogic();
		
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("userId") == null){
			response.sendRedirect("login.do");
		}
		
		service.remove(session.getAttribute("userId").toString(), Integer.parseInt(request.getParameter("musicId")));
		
		//send redirect해도 될듯(set attribute안하기 때문에..)
		request.getRequestDispatcher("favoriteList.do").forward(request, response);
	}

}
