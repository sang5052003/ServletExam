package store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;
import store.UserStore;
import store.factory.ConnectionFactory;
import store.utils.JdbcUtils;

public class UserStoreLogic implements UserStore {

private ConnectionFactory factory;
	
	public UserStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public boolean create(User user) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"insert into user_tb(loginid, password, name) values(?, ?, ?)"
					);
			
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt);
		}
		
		return count > 0;
	}

	@Override
	public User read(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"select loginid, password, name from user_tb where loginid = ?"
					);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				u = new User();
				
				u.setLoginId(rs.getString("loginid"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return u;
	}

}
