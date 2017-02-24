package shop.service.logic;

import java.util.ArrayList;
import java.util.List;

import shop.domain.Product;
import shop.service.facade.ProductService;
import shop.store.facade.ProductStore;
import shop.store.logic.ProductStoreLogic;

public class ProductServiceLogic implements ProductService{

	private ProductStore store;
	
	public ProductServiceLogic() {
		this.store = new ProductStoreLogic();
	}
	
	@Override
	public List<Product> getAllProducts() {
		return this.store.findAll();
	}

	@Override
	public List<Product> getBuyProducts(String[] serials) {
		
		List<Product> list = new ArrayList<>();
		
		for(String serial : serials){
			list.add(this.store.findByNo(Integer.parseInt(serial)));
		}
		
		return list;
	}

	@Override
	public Product getProduct(String serial) {
		return this.store.findByNo(Integer.parseInt(serial));
	}
}
