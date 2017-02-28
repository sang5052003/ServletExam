package hr.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.domain.Department;
import hr.store.DepartmentStore;
import hr.store.factory.ConnectionFactory;
import hr.store.utils.JdbcUtils;

public class DepartmentStoreLogic implements DepartmentStore{

	private ConnectionFactory factory;
	
	public DepartmentStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public List<Department> retrieveAll() {
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		List<Department> list = null;
		Department dept = null;
		
		try {
			conn = this.factory.createConnection();
			
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT deptNo, deptName ");
			sqlBuilder.append("FROM dept_tb ");
			sqlBuilder.append("ORDER BY deptNo ASC");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuilder.toString());
			
			list = new ArrayList<>();
			while(rs.next()){
				
				dept = new Department(rs.getString("deptNo"), rs.getString("deptName"));
				
				list.add(dept);
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//런타임 exception으로 전환 하는 것도 생각
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, stmt, rs);
		}
				
		return list;
	}

	@Override
	public void create(Department department) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO dept_tb (deptNo, deptName) VALUES(?,?)"
					);
			
			pstmt.setString(1, department.getNo());
			pstmt.setString(2, department.getName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
	}

	@Override
	public Department retrieve(String deptNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept = null;
		
		try {
			conn = this.factory.createConnection();
			
			
			pstmt = conn.prepareStatement(
					"SELECT deptNo, deptName FROM dept_tb where deptNo = ?"
					);
			
			pstmt.setString(1, deptNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dept = new Department(rs.getString("deptNo"), rs.getString("deptName"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//런타임 exception으로 전환 하는 것도 생각
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return dept;
	}

	@Override
	public void delete(String deptNo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"DELETE dept_tb WHERE deptNo = ?"
					);
			
			pstmt.setString(1, deptNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
	}

}
