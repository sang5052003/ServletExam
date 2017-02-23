package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Player;
import service.BaseballTeamService;
import service.logic.BaseballTeamServiceLogic;

@WebServlet("/trade.do")
public class tradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		System.out.println("dododododododoodododo");
//		
//		String targetPlayerId = request.getParameter("targetPlayer");
//		String sourcePlayerId = request.getParameter("srcPlayerId");
//		
//		BaseballTeamService service = new BaseballTeamServiceLogic();
//		
//		service.tradePlayer(sourcePlayerId, targetPlayerId);
//		
//		response.sendRedirect("playerList.do");
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String targetPlayerId = req.getParameter("targetPlayer");
		
		String sourcePlayerId = req.getParameter("srcPlayer");
		
		BaseballTeamService service = new BaseballTeamServiceLogic();
		
		service.tradePlayer(sourcePlayerId, targetPlayerId);
		
		resp.sendRedirect("playerList.do");
	}

}
