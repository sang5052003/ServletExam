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

//@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8"); //받아온 데이터의 인코딩 바꿔주기(한글깨짐)
		String name = req.getParameter("name");
		
		PersonServiceLogic service = new PersonServiceLogic();
		Person p = service.getPerson(name);
		
		//
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8"); //주는 데이터의 인코딩 바꿔주기(한글깨짐)
		PrintWriter out = resp.getWriter();
		
		if(p != null){
			out.println("이름 : " + p.getName());
			out.println("나이 : " + p.getAge());
			out.println("주소 : " + p.getAddress());
		}else{
			out.println("사용자가 없다");
		}
	}
}
