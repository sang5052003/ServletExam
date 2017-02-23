package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserMusicService;
import service.logic.UserMusicServiceLogic;

@WebServlet("/addFavorite.do")
public class AddFavoriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserMusicService service = new UserMusicServiceLogic();
		
		HttpSession session = request.getSession(false); // 인자로 false -> 세션이 없으면 null리턴(true -> 새로 생성한다, default는 true)
		if(session == null || session.getAttribute("userId") == null){
			response.sendRedirect("login.do");
		}
		else{
			int musicId = Integer.parseInt(request.getParameter("musicId"));
			String loginId = session.getAttribute("userId").toString();
			
			//이곳은 프리젠테이션 영역이므로
			//비지니스로직에서 할일(이미 가지고 있는 음악인지 확인하는 것)은
			//service(persistance layer)에서 처리한다
			//register내부에서 처리
			service.register(loginId, musicId);
			
			//여기서는 등록할지 안할지의 기능만 부른다(mvc), 보여주는 것 구성은 다음 servlet(favoriteList.do)에서 하도록 한다
			//유저가 가지고 있는 리스트를 다시 불러야되므로 attribute추가 하고 다시 화면도 뿌려줘야 한다
			//why, 담기 버튼 말고 그냥 내 음악리스트 버튼이 있을것이므로..
			//servlet에서 servlet으로 다시 호출
			response.sendRedirect("favoriteList.do");
		}
		
	}

}
