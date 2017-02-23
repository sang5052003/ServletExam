package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BaseballTeam;
import service.BaseballTeamService;
import service.logic.BaseballTeamServiceLogic;

@WebServlet("/teamList.do")
public class TeamListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseballTeamService teamService = new BaseballTeamServiceLogic();
		
		List<BaseballTeam> teamList = teamService.findAllTeams();
		
		request.setAttribute("teams", teamList);
		
		request.getRequestDispatcher("teamList.jsp").forward(request, response);
	}

}
