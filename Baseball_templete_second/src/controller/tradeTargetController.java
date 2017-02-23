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

@WebServlet("/tradeTargetList.do")
public class tradeTargetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseballTeamService service = new BaseballTeamServiceLogic();
		
		List<BaseballTeam> teamList = service.findTradeTargetPlayers(request.getParameter("teamId"));
		
		request.setAttribute("teams", teamList);
		request.setAttribute("srcPlayer", service.findPlayer(request.getParameter("srcPlayerId")));
		
		request.getRequestDispatcher("tradeTargetList.jsp").forward(request, response);
	}

}
