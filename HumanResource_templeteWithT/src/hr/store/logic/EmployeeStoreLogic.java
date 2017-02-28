package hr.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.domain.Department;
import hr.domain.Employee;
import hr.store.EmployeeStore;
import hr.store.factory.ConnectionFactory;
import hr.store.utils.JdbcUtils;

public class EmployeeStoreLogic implements EmployeeStore{

	private ConnectionFactory factory; 
	
	public EmployeeStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public List<Employee> retrieveAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = null;
		Employee emp = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT empNo, name, deptNo FROM EMPLOYEE_TB ORDER BY empNo ASC"
					);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				emp = new Employee(rs.getString("empNo"), rs.getString("name"), rs.getString("deptNo"));
				
				list.add(emp);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//런타임 exception으로 전환 하는 것도 생각
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public void create(Employee employee) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO employee_tb (empNo, name, deptNo) VALUES(?,?,?)"
					);
			
			pstmt.setString(1, employee.getNo());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getDeptNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

	@Override
	public List<Employee> retrieveByDeptNo(String deptNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = null;
		Employee emp = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT empNo, name, deptNo FROM EMPLOYEE_TB where deptNo = ? ORDER BY empNo ASC"
					);
			
			pstmt.setString(1, deptNo);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				emp = new Employee(rs.getString("empNo"), rs.getString("name"), rs.getString("deptNo"));
				
				list.add(emp);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//런타임 exception으로 전환 하는 것도 생각
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public void update(Employee employee) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"UPDATE employee_tb SET deptNo = ? where empNo = ?"
					);
			
			pstmt.setString(1, employee.getDeptNo());
			pstmt.setString(2, employee.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

	@Override
	public Employee retrieve(String empNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee emp = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT empNo, name, deptNo FROM EMPLOYEE_TB WHERE empNo = ?"
					);
			
			pstmt.setString(1, empNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				emp = new Employee(rs.getString("empNo"), rs.getString("name"), rs.getString("deptNo"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//런타임 exception으로 전환 하는 것도 생각
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return emp;
	}
}
