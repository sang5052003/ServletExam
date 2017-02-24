package shop.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop.domain.Customer;
import shop.store.facade.CustomerStore;
import shop.store.factory.ConnectionFactory;
import shop.store.utils.JdbcUtils;

public class CustomerStroreLogic implements CustomerStore{

	//메소드가 하나인데 멤버변수로 가지고 있는 이유
	//추가확장의 가능성이 높기 때문에(register 메소드 등..)
	private ConnectionFactory factory;
	
	public CustomerStroreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public Customer findCustomerById(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer cus = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement("SELECT userId, name, password FROM customer_tb WHERE userId = ?");
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cus = new Customer(
						rs.getString("name"),
						rs.getString("userId"),
						rs.getString("password")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return cus;
	}

}
