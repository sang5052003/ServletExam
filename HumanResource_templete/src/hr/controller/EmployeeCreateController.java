package hr.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hr.domain.Employee;
import hr.service.HumanResourceService;
import hr.service.logic.HumanResourceServiceLogic;

@WebServlet("/emp/create.do")
public class EmployeeCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String deptNo = request.getParameter("deptNo");
		
		request.setAttribute("deptNo", deptNo);
		
		request.getRequestDispatcher("/views/employeeCreate.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = session.getAttribute("loginId").toString();
		if(!loginId.equals("admin")){
			throw new RuntimeException("인사 등록 권한이 없는 사용자");
		}
		
		HumanResourceService service = new HumanResourceServiceLogic();
		
		//부서 상세정보에서 등록..
		if(!request.getParameter("deptNo").equals(null)){
			//response.sendRedirect(request.getContextPath() + "/emp/createWithDept.do");
			String deptNo = request.getParameter("deptNo");
			Employee emp = new Employee();
			emp.setNo(request.getParameter("empNo"));
			emp.setName(request.getParameter("empName"));
			emp.setDeptNo(deptNo);
			service.registEmployee(emp);
			
			response.sendRedirect(request.getContextPath() + "/views/menu.jsp");
			
			return;
		}
		
		//사이드메뉴에서 등록(부서번호없이)
		Employee emp = new Employee(request.getParameter("empNo"), request.getParameter("empName"));
		service.registEmployee(emp);

		response.sendRedirect(request.getContextPath() + "/views/menu.jsp");
		
	}

}
