

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> sport = new ArrayList<>();
		ArrayList<String> team = new ArrayList<>();
		
		sport.add("'AAA'");
		sport.add("'BBB'");
		sport.add("'CCC'");
		
		team.add("1");
		team.add("2");
		team.add("3");
		
		request.setAttribute("sport", sport);
		request.setAttribute("team", team);
		
		request.getRequestDispatcher("select.jsp").forward(request, response);
	}

}
