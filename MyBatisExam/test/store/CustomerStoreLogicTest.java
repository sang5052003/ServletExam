package store;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Customer;

public class CustomerStoreLogicTest {

	private CustomerStoreLogic store;
	
	@Before
	public void setUp(){
		this.store = new CustomerStoreLogic();
	}
	
//	@Test
//	public void testRegistCustomer() {
//		
//		Customer cus = new Customer();
//		cus.setAge(20);
//		cus.setName("hong");
//		cus.setId("1234");
//		assertEquals(1, this.store.regist(cus));
//	}
	
//	@Test
//	public void testSearchAll(){
//		assertNotNull(this.store.searchAll());
//		assertEquals("1234", this.store.searchAll().get(0).getId());
//	}
	
//	@Test
//	public void testUpdate(){
//		
//		Customer cus = new Customer();
//		
//		cus.setId(this.store.searchAll().get(0).getId());
//		cus.setName("kim");
//		cus.setAge(11);
//		
//		this.store.update(cus);
//		
//		assertEquals(11, this.store.searchAll().get(0).getAge());
//	}
	
	@Test
	public void testDelete(){
		
		int count = this.store.delete("1234");
		
		assertEquals(0, count);
		assertEquals(0, this.store.searchAll().size());
	}
}
