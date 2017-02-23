package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BaseballTeam;
import domain.Player;
import service.BaseballTeamService;
import service.logic.BaseballTeamServiceLogic;

@WebServlet("/playerList.do")
public class PlayerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseballTeamService teamService = new BaseballTeamServiceLogic();
		
		//플레이어가 존재하는 팀만
		List<BaseballTeam> teamList = teamService.findAllTeamsWithPlayers();
		
		request.setAttribute("teams", teamList);
		
		request.getRequestDispatcher("playerList.jsp").forward(request, response);
	}

}
