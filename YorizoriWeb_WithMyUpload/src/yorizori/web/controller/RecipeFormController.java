package yorizori.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

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
		
		String cbId = null;
		String recipeIngredients = null;
		String recipeLevel = null;
		String recipeName = null;
		String recipeTime = null;
		String recipeProcedure = null;
		//
		MultipartRequest multi = new MultipartRequest(request, "c:\\yorizori");
		Enumeration<Object> params = multi.getParameterNames();
		while(params.hasMoreElements()){
			String name = (String)params.nextElement();
			if(name.equals("cbId")){
				cbId = multi.getParameter(name);
			}
			else if(name.equals("recipeIngredients")){
				recipeIngredients = multi.getParameter(name);
			}
			else if(name.equals("recipeLevel")){
				recipeLevel = multi.getParameter(name);
			}
			else if(name.equals("recipeName")){
				recipeName = multi.getParameter(name);
			}
			else if(name.equals("recipeTime")){
				recipeTime = multi.getParameter(name);
			}
			else if(name.equals("recipeProcedure")){
				recipeProcedure = multi.getParameter(name);
			}
		}
		
		Cookbook cookbook = service.findCookbook(Integer.parseInt(cbId));
		
		Recipe recipe = new Recipe();
		
		recipe.setIngredients(recipeIngredients);
		recipe.setLevel(Integer.parseInt(recipeLevel));
		recipe.setName(recipeName);
		recipe.setTime(Integer.parseInt(recipeTime));
		//
		recipe.setCookbook(cookbook);
		recipe.setWriter(user);
		
		Procedure p = new Procedure();
		p.setProcedure(recipeProcedure);
		p.setSequence(1); //무조건 하나 지금은
		List<Procedure> list = new ArrayList<>();
		list.add(p);
		recipe.setProcedures(list);
		
		//로컬저장소의 이미지 파일을 불러오기(c:yorizoriUpload)
		//request.getParameter("imageFile");
				
		//불러온 이미지 파일을 저장하는 곳에 저장(c:yorizori)
		try {
			
			ImageFile imageFile = this.getImage(request, response);
			
			recipe.setRecipeImage(imageFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//그 경로를 db로 보냄
		service.registerRecipe(recipe);
		
		response.sendRedirect(request.getContextPath()); //메인으로..
	}
	
	public ImageFile getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ImageFile imageFile = null;
		String filePath = "";
		int maxFileSize = 50 * 1024;
		int maxMemSize = 4 * 1024;
		File file = null;
		String contentType = "";
		String fileName = "";
		
		filePath = "/yorizori";
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에 저장될 최대 크기
		factory.setSizeThreshold(maxMemSize);
		
		//maxMemSize의 크기를 넘어선 data가 저장될 위치
		factory.setRepository(new File("c:\\yorizoriUpload"));
		
		//파일업로드 핸들러
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 용량 설정
		upload.setSizeMax(maxFileSize);
		
		try {
			List<FileItem> list = upload.parseRequest(request);
			Iterator<FileItem> iter = list.iterator();
			
			while(iter.hasNext()){
				
				FileItem fi = (FileItem)iter.next();
				if(!fi.isFormField()){
					String fieldName = fi.getFieldName();
					fileName = fi.getName();
					contentType = fi.getContentType();
					boolean isInMem = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					
					//파일쓰기
					if(fileName.lastIndexOf("\\") >= 0){
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					}else{
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					
					fi.write(file);
					
				}
				
			}
			
			//
			imageFile = new ImageFile();
			imageFile.setContentType("image/" + contentType);
			imageFile.setFileName(fileName + "." + contentType);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageFile;
	}
	
	

}
