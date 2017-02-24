package shop.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import shop.domain.Customer;
import shop.domain.Order;
import shop.domain.Product;
import shop.store.facade.OrderStore;
import shop.store.factory.ConnectionFactory;
import shop.store.utils.JdbcUtils;

public class OrderStoreLogic implements OrderStore {

	private ConnectionFactory factory;
	
	public OrderStoreLogic() {
		this.factory = ConnectionFactory.getInstance();
	}
	
	@Override
	public boolean insertOrder(Order order) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = 0;
		int orderId = 0;
		
		Customer customer = order.getCustomer();
		List<Product> list = order.getProducts();
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"INSERT INTO order_tb VALUES(order_seq.nextval, ?, ?, ?)"
					);
			pstmt.setString(1, customer.getUserId());
			pstmt.setString(2, order.getPayment().getCode());
			pstmt.setString(3, order.getShipAddress());
			
			check = pstmt.executeUpdate();
			
			if(check > 0){
				check = 0;
				pstmt = conn.prepareStatement(
						"SELECT orderId FROM order_tb WHERE userId = ?"
						);
				
				pstmt.setString(1, customer.getUserId());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					orderId = rs.getInt("orderId");
					for(Product product : list){
						pstmt = conn.prepareStatement(
								"INSERT INTO orderproduct_tb VALUES(?, ?)"
								);
						
						pstmt.setInt(1,  orderId);
						pstmt.setInt(2,  product.getSerialNo());
						
						check += pstmt.executeUpdate();
					}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(conn, pstmt, rs);
		}

		
		return check > 0;
	}

}
