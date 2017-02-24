package shop.store.facade;

import java.util.List;

import shop.domain.Product;

/**
 * ProductStore
 * 
 * @since 2016. 9. 28.
 * @author 진권기 (kwonkijin@nextree.co.kr)
 */

public interface ProductStore {
	
	List<Product> findAll();
	
	//db 프로시저에서 처리 가능하나
	//요새 추세는 자바단으로 끌어올리고 있다
	//요새는 rdb로 불가능해지기 때문에 -> 빅데이터(nosql)
	//서버부하 최소화..
	Product findByNo(int serial); 
	

}
