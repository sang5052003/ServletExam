package shop.service.facade;

import java.util.List;

import shop.domain.Product;


/**
 * ProductService
 * 
 * @since 2016. 9. 28.
 * @author 진권기 (kwonkijin@nextree.co.kr)
 */
public interface ProductService {

	//전체상품
	List<Product> getAllProducts();

	//체크박스후 주문정보 보여줄 때 사용
	List<Product> getBuyProducts(String [] serials);
	
	Product getProduct(String serial);
}
