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

//팀 숫자를 클릭 했을 때 나올 화면구성 -> forTeamPlayerListController.do에서 화면 뿌림
@WebServlet("/teamPlayerList.do")
public class TeamPlayerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BaseballTeamService service = new BaseballTeamServiceLogic();
		
		BaseballTeam team = service.findTeam(request.getParameter("teamId"));
		
		request.setAttribute("team", team);
		
		//response.sendRedirect("forTeamPlayerList.do");
		request.getRequestDispatcher("forTeamPlayerList.do").forward(request, response);
	}

}
