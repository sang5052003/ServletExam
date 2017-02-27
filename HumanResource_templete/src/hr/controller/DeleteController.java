package hr.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.service.HumanResourceService;
import hr.service.logic.HumanResourceServiceLogic;

@WebServlet("/dept/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HumanResourceService service = new HumanResourceServiceLogic();
		
		String deptNo = request.getParameter("deptNo");
		service.removeDepartment(deptNo);
		
		response.sendRedirect(request.getContextPath() + "/dept/list.do");
	}

}
