package hr.store.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.domain.Department;
import hr.service.HumanResourceService;
import hr.service.logic.HumanResourceServiceLogic;

public class DepartmentNames {
	
	private HashMap<Integer, String> deptNameMap;
	
	public DepartmentNames() {
		this.deptNameMap = new HashMap<>();
	}
	
	//새로운 부서 추가하면..
	public void setDeptNames(HumanResourceService service){
		List<Department> list = service.findAllDepartment();
		
		for(Department dept : list){
			this.deptNameMap.put(Integer.parseInt(dept.getNo()), dept.getName());
		}
	}
	
	//
//	public HashMap<Integer, String> getDeptNameMap(){
//
//		return this.deptNameMap;
//	}
	
	public String getDeptName(int deptNo){
		return this.deptNameMap.get(deptNo);
	}
}
