package blog.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import blog.domain.Author;
import blog.domain.Comment;
import blog.domain.Post;

public class PostStoreLogicTest {

	private PostStoreLogic store;

	@Before
	public void setup() {
		this.store = new PostStoreLogic();
	}

	 @Test
	 public void testFindPost() {
	
	 Post post = this.store.findPost(1);
	
	 assertNotNull(post);
	 assertEquals("mybatis", post.getSubject());
	
	 assertEquals("박데몬", post.getAuthor().getName());
	
	 assertEquals("블로그01", post.getBlog().getTitle());
	
	 assertEquals("임재락", post.getComments().get(0).getName());
	
	 assertEquals("mybatis", post.getTags().get(0).getName());
	
	 }
	//

	// @Test
	// public void testFindAllPost() {
	//
	// List<Post> list = this.store.findAllPost();
	//
	// assertEquals(3, list.size());
	//
	// assertEquals("박데몬", list.get(0).getAuthor().getName());
	//
	// assertEquals("이해가 잘 안되요", list.get(0).getComments().get(0).getComment());
	// }
	//
	// @Test
	// public void testFindPostsByBlogId() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testFindPostsByAuthorName() {
	//
	// List<Post> list = this.store.findPostsByAuthorName("박데몬");
	//
	// assertEquals(2, list.size());
	// System.out.println(list.get(0).getComments().size());
	// assertNotNull(list.get(0).getComments());
	//
	// assertEquals("임재락", list.get(0).getComments().get(0).getName());
	//
	// }
	//
//	@Test
//	public void testFindPostsByBlogTitle() {
//
//		List<Post> list = this.store.findPostsByBlogTitle("하늘바람별");
//
//		assertEquals(2, list.size());
//		assertNotNull(list.get(0).getComments());
//
//		assertEquals("임재락", list.get(0).getComments().get(0).getName());
//	}
//
//	//
//	@Test
//	public void testFindPostsBySubject() {
//
//		List<Post> list = this.store.findPostsBySubject("mybatis");
//
//		assertEquals(1, list.size());
//		assertNotNull(list.get(0).getComments());
//
//		assertEquals("임재락", list.get(0).getComments().get(0).getName());
//
//	}
	//
//	 @Test
//	 public void testFindPostsByContents() {
//		 
//		 List<Post> list = this.store.findPostsByContents("mybatis의 이해와 적용");
//
//		assertEquals(1, list.size());
//		assertNotNull(list.get(0).getComments());
//
//		assertEquals("임재락", list.get(0).getComments().get(0).getName());
//
//	 }
	//
	// @Test
	// public void testRegistPost() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testUpdatePost() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testDeletePost() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testFindCommentsByPostId() {
	//
	// List<Comment> list = this.store.findCommentsByPostId(1);
	//
	// assertEquals(1, list.size());
	// assertEquals("임재락", list.get(0).getName());
	// }

}
