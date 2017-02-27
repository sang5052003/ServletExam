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

@WebServlet("/emp/list.do")
public class EmployeeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HumanResourceService service = new HumanResourceServiceLogic();
		List<Employee> list = service.findAllEmployee();
		
		//System.out.println("size a;ldkfjaf;dlkjasdlfk " + list.size());
		
//		Department dept = null;
//		for(Employee emp : list){
//			dept = service.findDepartment(emp.getDeptNo());
//		}
		
		//부서번호 대신 부서명..
		List<Department> dept = service.findAllDepartment();
		request.setAttribute("deptList", dept);
		//request.setAttribute("deptName", deptName);
		request.setAttribute("empList", list);
		
		request.getRequestDispatcher("/views/employeeList.jsp").forward(request, response);
	}

}
