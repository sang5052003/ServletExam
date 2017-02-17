package com.kosta.example.person.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.example.person.domain.Person;
import com.kosta.example.person.service.PersonServiceLogic;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		
		//System.out.println(name + " / " + age + " / " + address);
		
		PersonServiceLogic service = new PersonServiceLogic();
		
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("</head>");
		
		out.println("<body>");
		if(service.insertPerson(new Person(name, age, address))){
			out.println("<h1>");
			out.println(name + "님이 가입 되었다");
			out.println("<h1>");
		}
		else{
			out.println("<h1>");
			out.println("등록되지 않았다");
			out.println("<h1>");
		}
		
		out.println("</body>");
		
		out.println("</html>");
		
		
		
	}

}
