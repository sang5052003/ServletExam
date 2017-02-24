package shop.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Order
 * 
 * @since 2016. 9. 28.
 * @author 진권기 (kwonkijin@nextree.co.kr)
 */
//order table은 주문 한건에 하나
public class Order {

	private Customer customer; 		//누가 주문했냐
	private List<Product> products; //한번 주문에 여러개의 상품
	private PaymentMethod payment;  // 지급방법(char) -> why, enum으로 하려고(sql에는 true/false가 없다?)
	private String shipAddress; 	// 주소 barchar
	
	public Order() {
		//
		this.products = new ArrayList<Product>();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public PaymentMethod getPayment() {
		return payment;
	}
	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	
	//--------------------------------------------------------------------------
	public void addProduct(Product product) {
		//
		products.add(product);
	}
	
	public int getTotalPrice() {
		//
		int total = 0;
		for (Product product : products) {
			total += product.getPrice();
		}
		return total;
	}
}
