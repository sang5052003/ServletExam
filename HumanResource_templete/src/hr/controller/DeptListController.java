package hr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.domain.Department;
import hr.domain.Employee;
import hr.service.HumanResourceService;
import hr.service.logic.HumanResourceServiceLogic;

@WebServlet("/dept/list.do")
public class DeptListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HumanResourceService service = new HumanResourceServiceLogic();
		List<Department> list = service.findAllDepartment();
		
		request.setAttribute("deptList", list);
		
		//dispatch할때는 이미 context path가 붙어 있기 때문에
		//why, 다시 client로 갔다가 오지 않고 바로 가니까..
		//request.getContextPath() 를 붙이지 않는다
		request.getRequestDispatcher("/views/deptList.jsp").forward(request, response);
		
	}

}
