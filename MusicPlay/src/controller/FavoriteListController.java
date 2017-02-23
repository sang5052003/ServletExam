package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Music;
import service.UserMusicService;
import service.logic.UserMusicServiceLogic;

@WebServlet("/favoriteList.do")
public class FavoriteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserMusicService service;
	
	public FavoriteListController() {
		this.service = new UserMusicServiceLogic();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//추가하기

		HttpSession session = request.getSession(false);

		String userId = session.getAttribute("userId").toString();
		String userName = session.getAttribute("userName").toString();

		// 뿌리기
		List<Music> list = this.service.findMusicsByUser(userId);

		request.setAttribute("musics", list);
		request.setAttribute("userName", userName);

		request.getRequestDispatcher("myList.jsp").forward(request, response);
	}

}
