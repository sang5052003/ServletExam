package blog.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import blog.domain.Blog;

public class BlogStoreLogicTest {

	private BlogStoreLogic store;
	private AuthorStoreLogic authorStore;
	
	@Before
	public void setup(){
		this.store = new BlogStoreLogic();
		this.authorStore = new AuthorStoreLogic();
	}
	
//	@Test
//	public void testFindBlog() {
//		
//		Blog blog = this.store.findBlog(1);
//		
//		assertEquals("하늘바람별", blog.getTitle());
//		assertEquals("demonpark", blog.getAuthor().getId());
//		
//		assertNotNull(blog.getPosts());
//		assertEquals(2, blog.getPosts().size());
//		
//		assertEquals("mybatis", blog.getPosts().get(0).getSubject());
//		assertEquals("mybatis의 이해와 적용", blog.getPosts().get(0).getContents());
//		
//		assertNotNull(blog.getPosts().get(0).getComments());
//		assertEquals(1, blog.getPosts().get(0).getComments().size());
//		
//		//
//		assertEquals("임재락", blog.getPosts().get(0).getComments().get(0).getName());
//		
//		assertEquals("mybatis", blog.getPosts().get(0).getTags().get(0).getName());
//	}

//	@Test
//	public void testFindBlogByAuthorId() {
//		
//		Blog blog = this.store.findBlogByAuthorId("demonpark");
//		
//		assertEquals(1, blog.getId());
//		assertEquals("하늘바람별", blog.getTitle());
//		
//		//
//		assertNotNull(blog.getPosts().get(0).getComments());
//		assertEquals(1, blog.getPosts().get(0).getComments().size());
//		
//		//
//		assertEquals("임재락", blog.getPosts().get(0).getComments().get(0).getName());
//		
//		assertEquals("mybatis", blog.getPosts().get(0).getTags().get(0).getName());
//	}
////
//	@Test
//	public void testFindAllBlogs() {
//		
//		List<Blog> list = this.store.findAllBlogs();
//		
//		assertEquals(3, list.size());
//
//		assertNotNull(list.get(0).getPosts().get(0).getComments());
//		assertEquals(1, list.get(0).getPosts().get(0).getComments().size());
//		
//		//
//		assertEquals("임재락", list.get(0).getPosts().get(0).getComments().get(0).getName());
//		
//		assertEquals("mybatis", list.get(0).getPosts().get(0).getTags().get(0).getName());
//	}
////
//	@Test
//	public void testFindBlogsByTitle() {
//		
//		List<Blog> list = this.store.findBlogsByTitle("하늘", "eykim");
//
//		assertEquals(2, list.size());
//		assertEquals("eykim", list.get(1).getAuthor().getId());
//		
//		assertNotNull(list.get(0).getPosts().get(0).getComments());
//		assertEquals(1, list.get(0).getPosts().get(0).getComments().size());
//		
//		//
//		assertEquals("임재락", list.get(0).getPosts().get(0).getComments().get(0).getName());
//		
//		assertEquals("mybatis", list.get(0).getPosts().get(0).getTags().get(0).getName());
//	}

//
//	@Test
//	public void testRegistBlog() {
//		
//		Blog blog = new Blog();
//		blog.setAuthor(this.authorStore.findAuthor("demonpark"));
//		blog.setTitle("title02");
//		this.store.registBlog(blog);
//		
//		assertEquals("title02", this.store.findBlogByAuthorId(blog.getAuthor().getId()).getTitle());
//	}
//
//	@Test
//	public void testUpdateBlog() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testDeleteBlog() {
		
		this.store.deleteBlog(1);
		
		assertNull(this.store.findBlog(1));
		
		//post삭제
		
		//코멘틀 삭제
		
		//태그삭제
		
	}

}
