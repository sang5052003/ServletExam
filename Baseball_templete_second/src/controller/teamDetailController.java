package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BaseballTeam;
import service.BaseballTeamService;
import service.logic.BaseballTeamServiceLogic;

@WebServlet("/teamDetail.do")
public class teamDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BaseballTeamService service;
	
	public teamDetailController() {
		this.service = new BaseballTeamServiceLogic();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseballTeam team = this.service.findTeam(request.getParameter("teamId"));
		
		request.setAttribute("team", team);
		
		request.getRequestDispatcher("teamDetail.jsp").forward(request, response);
	}

	//팀목록 버튼
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getParameter("teamListBtn")
		
		response.sendRedirect("teamList.do");
	}

}
