package hr.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hr.domain.Department;
import hr.domain.Employee;
import hr.service.HumanResourceService;
import hr.service.logic.HumanResourceServiceLogic;

@WebServlet("/emp/create.do")
public class EmployeeCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String deptNo = request.getParameter("deptNo");
		
		HumanResourceService service = new HumanResourceServiceLogic();
		
		Department dept = service.findDepartment(deptNo);
		
		request.setAttribute("department", dept);
		
		request.getRequestDispatcher("/views/employeeCreate.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HumanResourceService service = new HumanResourceServiceLogic();
		
		String deptNo = request.getParameter("deptNo");
		Employee emp = new Employee();
		emp.setNo(request.getParameter("empNo"));
		emp.setName(request.getParameter("empName"));
		emp.setDeptNo(deptNo);
		service.registEmployee(emp);
		
		//직원등록후 상세보기로 다시 넘어가기
		//상세보기에서 넘어왔으므로..
		response.sendRedirect(request.getContextPath() + "/dept/detail.do?deptNo=" + deptNo);
	}

}
