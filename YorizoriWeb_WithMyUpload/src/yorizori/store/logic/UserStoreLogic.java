package yorizori.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;


import yorizori.common.util.JdbcUtils;
import yorizori.domain.User;
import yorizori.store.UserStore;


public class UserStoreLogic implements UserStore {

	//커넥션 풀링용 멤버변수
	private DataSource dataSource;
	
	public UserStoreLogic(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void create(User user) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO user_tb(user_id, passwd, user_name) VALUES(?,?,?)"
					);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(pstmt, conn);
		}
	}

	@Override
	public User retrieve(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		
		try{
			conn = this.dataSource.getConnection();
			
			pstmt = conn.prepareStatement(
					"SELECT user_id, passwd, user_name FROM user_tb WHERE user_id = ?"
					);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = this.convertToDomain(rs);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			JdbcUtils.closeQuietly(rs, pstmt, conn);
		}
		
		return user;
	}
	
	private User convertToDomain(ResultSet rs) throws SQLException{
		User user = new User();
		user.setUserId(rs.getString("user_Id"));
		user.setPassword(rs.getString("passwd"));
		user.setName(rs.getString("user_name"));
		
		return user;
	}

}
