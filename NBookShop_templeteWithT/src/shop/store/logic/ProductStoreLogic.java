package shop.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shop.domain.Customer;
import shop.domain.Product;
import shop.store.facade.ProductStore;
import shop.store.factory.ConnectionFactory;
import shop.store.utils.JdbcUtils;

public class ProductStoreLogic implements ProductStore {

	private ConnectionFactory factory;

	public ProductStoreLogic() {

		this.factory = ConnectionFactory.getInstance();
	}

	@Override
	public List<Product> findAll() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = null;
		Product product = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement("SELECT serialNo, name, price, userLike FROM product_tb");
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()){
				product = new Product(rs.getInt("serialNo"),
									  rs.getString("name"),
									  rs.getInt("price"),
									  rs.getInt("userLike")
									  );
										
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public Product findByNo(int serial) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement("SELECT serialNo, name, price, userLike FROM product_tb where serialNo = ?");
			
			pstmt.setInt(1, serial);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				product = new Product(rs.getInt("serialNo"),
									  rs.getString("name"),
									  rs.getInt("price"),
									  rs.getInt("userLike")
									  );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, pstmt, rs);
		}
		
		return product;
	}

}
