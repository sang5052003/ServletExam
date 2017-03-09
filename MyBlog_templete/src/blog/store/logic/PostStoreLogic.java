package blog.store.logic;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import blog.domain.Author;
import blog.domain.Blog;
import blog.domain.Comment;
import blog.domain.Post;
import blog.domain.Tag;
import blog.store.facade.PostStore;
import blog.store.mapper.AuthorMapper;
import blog.store.mapper.BlogMapper;
import blog.store.mapper.PostMapper;

public class PostStoreLogic implements PostStore {

	SqlSessionFactory factory;

	public PostStoreLogic() {
		this.factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public Post findPost(int id) {

		SqlSession session = this.factory.openSession();
		Post post = null;

		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;

		try {

			PostMapper mapper = session.getMapper(PostMapper.class);
			post = mapper.findPost(id);

			author = this.setAuthor(session, post);
			post.setAuthor(author);

			blog = this.setBlog(session, post);
			post.setBlog(blog);

			// date

			comments = this.findCommentsByPostId(id);
			post.setComments(comments);

			tags = this.findTagsByPostId(id);
			post.setTags(tags);

		} finally {
			session.close();
		}

		return post;
	}

	@Override
	public List<Post> findAllPost() {
		
		SqlSession session = this.factory.openSession();
		List<Post> list = null;
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		try{
			
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findAllPost();
			
			//셋팅
			for(Post post : list){
				author = this.setAuthor(session, post);
				post.setAuthor(author);

				blog = this.setBlog(session, post);
				post.setBlog(blog);

				// date

				comments = this.findCommentsByPostId(post.getId());
				post.setComments(comments);

				tags = this.findTagsByPostId(post.getId());
				post.setTags(tags);
			}
			
		}finally{
			session.close();
		}
		
		return list;
	}

	//annotation으로 처리해서 스토어 로직이 필요가 없네..
	@Override
	public List<Post> findPostsByBlogId(int id) {
		
//		SqlSession session = this.factory.openSession();
//		List<Post> list = null;
//		try{
//			PostMapper mapper = session.getMapper(PostMapper.class);
//			list = mapper.findPostsByBlogId(id);
//			
//			//
//			for(Post p : list){
//				p = this.findPost(p.getId());
//			}
//			
//		}finally{
//			session.close();
//		}
//		
//		return list;
		return null;
	}

	@Override
	public List<Post> findPostsByAuthorName(String authorName) {
		
		SqlSession session = this.factory.openSession();
		List<Post> list = null;
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		try{
			
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findPostsByAuthorName(authorName);
			
			//셋팅
			for(Post post : list){
				author = this.setAuthor(session, post);
				post.setAuthor(author);

				blog = this.setBlog(session, post);
				post.setBlog(blog);

				// date

				comments = this.findCommentsByPostId(post.getId());
				post.setComments(comments);

				tags = this.findTagsByPostId(post.getId());
				post.setTags(tags);
			}
			
		}finally{
			session.close();
		}
		
		return list;
	}

	@Override
	public List<Post> findPostsByBlogTitle(String blogTitle) {
		
		SqlSession session = this.factory.openSession();
		List<Post> list = null;
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		try{
			
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findPostsByBlogTitle(blogTitle);
			
			//셋팅
			for(Post post : list){
				author = this.setAuthor(session, post);
				post.setAuthor(author);

				blog = this.setBlog(session, post);
				post.setBlog(blog);

				// date

				comments = this.findCommentsByPostId(post.getId());
				post.setComments(comments);

				tags = this.findTagsByPostId(post.getId());
				post.setTags(tags);
			}
			
		}finally{
			session.close();
		}
		
		return list;
	}

	@Override
	public List<Post> findPostsBySubject(String subject) {
		
		SqlSession session = this.factory.openSession();
		List<Post> list = null;
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		try{
			
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findPostsBySubject(subject);
			
			//셋팅
			for(Post post : list){
				author = this.setAuthor(session, post);
				post.setAuthor(author);

				blog = this.setBlog(session, post);
				post.setBlog(blog);

				// date

				comments = this.findCommentsByPostId(post.getId());
				post.setComments(comments);

				tags = this.findTagsByPostId(post.getId());
				post.setTags(tags);
			}
			
		}finally{
			session.close();
		}
		
		return list;
	}

	@Override
	public List<Post> findPostsByContents(String contents) {
		
		SqlSession session = this.factory.openSession();
		List<Post> list = null;
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		try{
			
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findPostsByContents(contents);
			
			//셋팅
			for(Post post : list){
				author = this.setAuthor(session, post);
				post.setAuthor(author);

				blog = this.setBlog(session, post);
				post.setBlog(blog);

				// date

				comments = this.findCommentsByPostId(post.getId());
				post.setComments(comments);

				tags = this.findTagsByPostId(post.getId());
				post.setTags(tags);
			}
			
		}finally{
			session.close();
		}
		
		return list;
	}

	@Override
	public int registPost(Post post) {
		
		
		
		return 0;
	}

	@Override
	public int updatePost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	//
	@Override
	public List<Comment> findCommentsByPostId(int postId) {

		SqlSession session = this.factory.openSession();
		List<Comment> list = null;

		try {
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findCommentsByPostId(postId);
		} finally {
			session.close();
		}
		return list;
	}

	public List<Tag> findTagsByPostId(int postId) {

		SqlSession session = this.factory.openSession();
		List<Tag> list = null;

		try {
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.findTagsByPostId(postId);
		} finally {
			session.close();
		}
		return list;
	}
	
	private Post setDomainById(Post post, SqlSession session){

		Post rtPost = new Post();
		
		Author author = null;
		Blog blog = null;
		Date createdOn = null; // ..
		List<Comment> comments = null;
		List<Tag> tags = null;
		
		rtPost.setId(post.getId());
		rtPost.setContents(post.getContents());
		rtPost.setSubject(post.getSubject());
		
		//date
		//rtPost.setCreatedOn(post.getCreatedOn());
		
		author = this.setAuthor(session, rtPost);
		rtPost.setAuthor(author);

		blog = this.setBlog(session, rtPost);
		rtPost.setBlog(blog);


		comments = this.findCommentsByPostId(rtPost.getId());
		rtPost.setComments(comments);

		tags = this.findTagsByPostId(rtPost.getId());
		rtPost.setTags(tags);
		
		return rtPost;
	}

	//
	private Author setAuthor(SqlSession session, Post post) {

		Author author = null;
		AuthorMapper mapper = session.getMapper(AuthorMapper.class);
		author = mapper.findAuthor(post.getAuthor().getId());
		
		return author;
	}

	private Blog setBlog(SqlSession session, Post post) {

		Blog blog = null;
		
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		blog = mapper.findBlog(post.getBlog().getId());
		
		return blog;
	}

}
