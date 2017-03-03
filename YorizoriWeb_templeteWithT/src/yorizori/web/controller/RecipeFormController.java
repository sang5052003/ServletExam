package yorizori.web.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yorizori.domain.Cookbook;
import yorizori.domain.ImageFile;
import yorizori.domain.Procedure;
import yorizori.domain.Recipe;
import yorizori.domain.User;
import yorizori.service.CookbookService;
import yorizori.service.ServiceFactory;

@WebServlet("/recipe/register.do")
public class RecipeFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cbId = request.getParameter("cbId");
		
		request.setAttribute("cbId", cbId);
		request.getRequestDispatcher("/views/recipeForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = null;
		
		HttpSession session = request.getSession(false);
		if(session != null){
			user = (User)session.getAttribute("loginUser");
			
			request.setAttribute("loginUser", user);
			
		}
		else{
			response.sendRedirect(request.getContextPath()); //메인으로..
		}
		
		CookbookService service = ServiceFactory.getCookbookService();
		
		String cbId = request.getParameter("cbId");
		
		Cookbook cookbook = service.findCookbook(Integer.parseInt(cbId));
		
		
		
		Recipe recipe = new Recipe();
		
		recipe.setCookbook(cookbook);
		recipe.setIngredients(request.getParameter("recipeIngredients"));
		recipe.setLevel(Integer.parseInt(request.getParameter("recipeLevel")));
		recipe.setName(request.getParameter("recipeName"));
		recipe.setTime(Integer.parseInt(request.getParameter("recipeTime")));
		recipe.setWriter(user);
		
		Procedure p = new Procedure();
		p.setProcedure(request.getParameter("recipeProcedure"));
		p.setSequence(1); //무조건 하나 지금은
		List<Procedure> list = new ArrayList<>();
		list.add(p);
		recipe.setProcedures(list);
		
		//로컬저장소의 이미지 파일을 불러오기(c:yorizoriUpload)
		ImageFile rImage = new ImageFile();
		String imgType = request.getParameter("imageCont");
		rImage.setContentType("image/" + imgType);
		rImage.setFileName(request.getParameter("imageName") + "." + imgType);
		recipe.setRecipeImage(rImage);
		
		//불러온 이미지 파일을 저장하는 곳에 저장(c:yorizori)
		this.getImage(response, rImage);
		
		//그 경로를 db로 보냄
		service.registerRecipe(recipe);
		
		response.sendRedirect(request.getContextPath()); //메인으로..
	}
	
	public void getImage(HttpServletResponse response, ImageFile rImage) throws IOException{
		
		String fileName = null;
		InputStream in = null;
		
		if(rImage != null){
			//이미지의 확장자명(ex : png, jpg..)
			//response로 client에게 이미지 확장자명을 알려준다
			response.setContentType(rImage.getContentType());
			
			//web.xml에 설정한 param에서 가져옴(전역변수라고 생각..)
			String imagePath = "/yorizori";
			//getServletContext().getInitParameter("imagePath");
			// String imagePath = "/yorizori"; 위와 동일
			
			//c드라이브/폴더/파일명
			fileName = imagePath + "/" + rImage.getFileName();
			
			in = new BufferedInputStream(new FileInputStream(fileName));
			
		}
		
		//client에게 넘겨줄 outputstream을 연다..
		//로컬저장소로 넘겨줄 stream을 연다@!#!@#!@#!@#
		String downLoadPath = "c:\\yorizoriDownLoad\\";
		OutputStream out = new FileOutputStream(downLoadPath + rImage.getFileName());
		System.out.println("path : " + downLoadPath + fileName);
		//avaliable(내부버퍼 사용)로 하면 안되는 이유..
		//user가 천명이라면..
		//읽는 바이트의 수를 계산해야하는데..
		
		//내가 정한다 -> 이미지 사이즈가 몇 메가 이하라고 정해져 있어서
		//한번에 읽을 바이트 수
		byte[] buf = new byte[8096];
		int readByte = 0;
		while((readByte = in.read(buf)) > -1){
			out.write(buf, 0, readByte);
		}
		
		out.close();
		in.close();
	}

}
