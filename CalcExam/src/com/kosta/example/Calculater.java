package com.kosta.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Calculater")
public class Calculater extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String operand = request.getParameter("operand");
		writer.println("<html>");
		writer.println("<body>");
		switch(operand){
		case "+":
			writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
			break;
		case "-":
			break;
		case "*":
			break;
		case "/":
			writer.println(num1 + " / " + num2 + " = " + ((double)num1 / num2));
			break;
		}
		writer.println("</body>");
		writer.println("</html>");
	}

}
