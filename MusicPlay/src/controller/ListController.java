package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Music;
import service.MusicService;
import service.logic.MusicServiceLogic;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicService service;
	
	public ListController() {
		this.service = new MusicServiceLogic();
	}
	
	//최초 리스트 화면
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Music> list = this.service.findAll();
		
		request.setAttribute("musicList", list);
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}
	
	//검색 시
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Music> list = this.service.findByName(req.getParameter("musicName"));
		
		req.setAttribute("musicList", list);
		
		req.getRequestDispatcher("list.jsp").forward(req, resp);
	}
}
