package shop.service.logic;

import shop.domain.Customer;
import shop.service.facade.CustomerService;
import shop.store.facade.CustomerStore;
import shop.store.logic.CustomerStroreLogic;

public class CustomerServiceLogic implements CustomerService{

	private CustomerStore store;
	
	public CustomerServiceLogic() {
		this.store = new CustomerStroreLogic();
	}
	
	@Override
	public boolean login(String userId, String password) {

		//멤버메소드 호출하면 되지만..수정가능성..
		Customer cus = this.store.findCustomerById(userId);
		
		if(cus != null && cus.getPassword().equals(password)){
			return true;
		}
		return false;
	}

	@Override
	public Customer getCustomer(String userId) {
		return this.store.findCustomerById(userId);
	}

}
