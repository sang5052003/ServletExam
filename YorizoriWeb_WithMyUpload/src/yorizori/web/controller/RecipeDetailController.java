package yorizori.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yorizori.domain.Recipe;
import yorizori.service.CookbookService;
import yorizori.service.ServiceFactory;

@WebServlet("/recipe/detail.do")
public class RecipeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String recipeId = request.getParameter("recipeId");
		
		CookbookService service = ServiceFactory.getCookbookService();
		
		Recipe recipe = service.findRecipeById(Integer.parseInt(recipeId));
		
		request.setAttribute("recipe", recipe);
		
		request.getRequestDispatcher("/views/recipeDetail.jsp").forward(request, response);
	}
}
