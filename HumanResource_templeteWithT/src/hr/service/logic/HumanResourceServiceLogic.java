package hr.service.logic;

import java.util.List;

import hr.domain.Department;
import hr.domain.Employee;
import hr.service.HumanResourceService;
import hr.store.DepartmentStore;
import hr.store.EmployeeStore;
import hr.store.logic.DepartmentStoreLogic;
import hr.store.logic.EmployeeStoreLogic;
import hr.store.utils.DepartmentNames;

public class HumanResourceServiceLogic implements HumanResourceService{

	private DepartmentStore deptStore;
	private EmployeeStore empStore;
	
	
	public HumanResourceServiceLogic() {
		this.deptStore = new DepartmentStoreLogic();
		this.empStore = new EmployeeStoreLogic();
		//this.deptNames = new DepartmentNames(); //서비스 인자로 넘겨주는 방향으로 change..
	}
	
	@Override
	public List<Department> findAllDepartment() {
		return this.deptStore.retrieveAll();
	}

	@Override
	public void registeDepartment(Department department) {
		this.deptStore.create(department);
	}

	@Override
	public Department findDepartment(String deptNo) {
		
		//부서정보를 조회
		Department dept = this.deptStore.retrieve(deptNo);
		
		//부서에 속한 직원들 조회
		List<Employee> list = this.empStore.retrieveByDeptNo(deptNo);
		
		dept.setEmployees(list);
		
		return dept;
	}

	@Override
	public List<Employee> findAllEmployee() {
		return this.empStore.retrieveAll();
	}

	@Override
	public void registEmployee(Employee employee) {
		this.empStore.create(employee);
	}

	@Override
	public void removeDepartment(String deptNo) {
		
		//부서정보 조회
		Department dept = this.findDepartment(deptNo);
		List<Employee> empList = dept.getEmployees();
		
		//삭제대상 부서 employ부서 초기화
		for(Employee emp : empList){
			emp.setDeptNo(null);
			this.modify(emp);
		}
		
		this.deptStore.delete(deptNo);
	}

	@Override
	public Employee findEmployee(String empNo) {
		return this.empStore.retrieve(empNo);
	}

	@Override
	public void modify(Employee employee) {
		this.empStore.update(employee);
	}
	
//	//service에 인터페이스 메소드 추가했다..
//	public DepartmentNames getDepartmentNames(){
//		DepartmentNames deptNames = new DepartmentNames();
//		deptNames.setDeptNames(this);
//		return deptNames;
//	}
	

}
