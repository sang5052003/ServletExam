package blog.store.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import blog.domain.Author;

public class AuthorStoreLogicTest {

	private AuthorStoreLogic store;
	
	@Before
	public void setUp(){
		this.store = new AuthorStoreLogic();
	}
	
//	@Test
//	public void testFindAuthor() {
//		
//		Author a = this.store.findAuthor("demonpark");
//		assertNotNull(a);
//		assertEquals("demonpark", a.getId());
//	}
//
//	@Test
//	public void testFindAllAuthors() {
//		
//		List<Author> list = this.store.findAllAuthors();
//		assertEquals(5, list.size());
//		assertEquals("한승용", list.get(0).getName());
//	}
//
//	@Test
//	public void testFindAuthorsByName() {
//		
//		List<Author> list = this.store.findAuthorsByName("박데몬");
//		
//		assertEquals(1, list.size());
//		assertEquals("박데몬", list.get(0).getName());
//	}
//
//	@Test
//	public void testRegistAuthor() {
//		
//		Author author = new Author();
//		author.setId("sangki");
//		author.setPassword("1234");
//		author.setName("김상기");
//		author.setEmail("1234@naver.com");
//		
//		this.store.registAuthor(author);
//		
//		author = this.store.findAuthor("sangki");
//		
//		assertEquals("sangki", author.getId());
//	}
//
	@Test
	public void testUpdateAuthor() {
		
		Author author = this.store.findAuthor("kimgisa");
		
		author.setName("박기사");
		author.setPassword("1234");
		
		int result = this.store.updateAuthor(author);
		
		assertEquals(1, result);
	}
//
//	@Test
//	public void testDeleteAuthor() {
//		
//		int result = this.store.deleteAuthor("sangki");
//		
//		assertEquals(1, result);
//	}
//
//	@Test
//	public void testFindAuthorsByIds() {
//		
//		List<String> ids = new ArrayList<>();
//		ids.add("demonpark");
//		ids.add("kimgisa");
//		
//		List<Author> list = this.store.findAuthorsByIds(ids);
//		
//		assertEquals(2, list.size());
//		
//	}
////
//	@Test
//	public void testFindAuthorByCondition() {
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("id", "demonpark");
//		List<Author> list = this.store.findAuthorByCondition(map);
//		
//		assertEquals(1, list.size());
//		
//		map.remove("id");
//		
//		list = this.store.findAuthorByCondition(map);
//		
//		assertEquals(5, list.size());
//	}

}
