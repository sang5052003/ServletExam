package yorizori.web.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yorizori.domain.ImageFile;
import yorizori.domain.Recipe;
import yorizori.service.CookbookService;
import yorizori.service.ServiceFactory;

@WebServlet("/recipe/image.do")
public class RecipeImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int recipeId = Integer.parseInt(request.getParameter("recipeId"));
		
		CookbookService service = ServiceFactory.getCookbookService();
		
		Recipe recipe = service.findRecipeById(recipeId);
		
		ImageFile rImage = recipe.getRecipeImage();
		
		String fileName = null;
		InputStream in = null;
		
		if(rImage != null){
			//이미지의 확장자명(ex : png, jpg..)
			//response로 client에게 이미지 확장자명을 알려준다
			response.setContentType(rImage.getContentType());
			
			//web.xml에 설정한 param에서 가져옴(전역변수라고 생각..)
			String imagePath = getServletContext().getInitParameter("imagePath");
			// String imagePath = "/yorizori"; 위와 동일
			
			//c드라이브/폴더/파일명
			fileName = imagePath + "/" + rImage.getFileName();
			
			in = new BufferedInputStream(new FileInputStream(fileName));
			
		}
		
		//client에게 넘겨줄 outputstream을 연다..
		OutputStream out = response.getOutputStream();
		
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
