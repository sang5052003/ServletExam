package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BaseballTeam;
import service.BaseballTeamService;
import service.logic.BaseballTeamServiceLogic;

@WebServlet("/forTeamPlayerList.do")
public class ForTeamPlayerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BaseballTeam team = (BaseballTeam)request.getAttribute("team");

		List<BaseballTeam> teamList = new ArrayList<>();
		
		teamList.add(team); //
		
		request.setAttribute("teams", teamList);	
		
		request.getRequestDispatcher("playerList.jsp").forward(request, response);
	}
}
