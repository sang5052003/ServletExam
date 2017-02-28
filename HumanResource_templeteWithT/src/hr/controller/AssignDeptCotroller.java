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

@WebServlet("/dept/assign.do")
public class AssignDeptCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HumanResourceService service = new HumanResourceServiceLogic();
		
		String empNo = req.getParameter("empNo");
		
		Employee emp = service.findEmployee(empNo);
		
		List<Department> list = service.findAllDepartment();
		
		req.setAttribute("employee", emp);
		req.setAttribute("deptList", list);
		
		req.getRequestDispatcher("/views/assignDept.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String deptNo = request.getParameter("deptNo");
		String empNo = request.getParameter("empNo");
		
		HumanResourceService service = new HumanResourceServiceLogic();
		
		Employee employee = service.findEmployee(empNo);
		employee.setDeptNo(deptNo);
		service.modify(employee);
		
		//
		response.sendRedirect(request.getContextPath() + "/emp/list.do");
	}
}
