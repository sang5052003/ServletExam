package blog.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import blog.domain.Blog;
import blog.domain.Comment;
import blog.store.facade.BlogStore;
import blog.store.mapper.BlogMapper;

public class BlogStoreLogic implements BlogStore {

	private SqlSessionFactory factory;

	public BlogStoreLogic() {
		this.factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public Blog findBlog(int id) {

		SqlSession session = this.factory.openSession();
		Blog blog = null;

		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			blog = mapper.findBlog(id);

		} finally {
			session.close();
		}
		return blog;
	}
	
	

	@Override
	public Blog findBlogByAuthorId(String authorId) {

		SqlSession session = this.factory.openSession();
		Blog blog = null;

		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			blog = mapper.findBlogByAuthorId(authorId);

		} finally {
			session.close();
		}
		return blog;
	}

	@Override
	public List<Blog> findAllBlogs() {
		SqlSession session = this.factory.openSession();
		List<Blog> list = null;

		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			list = mapper.findAllBlogs();

		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Blog> findBlogsByTitle(String title, String authorId) {
		
		SqlSession session = this.factory.openSession();
		List<Blog> list = null;
//		HashMap<String , String> map = new HashMap<>();
//		map.put(title, authorId);

		try {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			list = mapper.findBlogsByTitle(title, authorId);
			
			//list = session.selectList("findBlogsByTitle", map);

		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public void registBlog(Blog blog) {
		
		SqlSession session = this.factory.openSession();
		
		try{
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			mapper.registBlog(blog);
			session.commit();
			
		}finally{
			session.close();
		}
	}

	@Override
	public int updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBlog(int id) {
		
		SqlSession session = this.factory.openSession();
		int result = 0;
		
		try{
			
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			result = mapper.deleteBlog(id);
			
			if(result > 0){
				session.commit();
			}else{
				session.rollback();
			}
			
		}finally{
			session.close();
		}
		
		return result;
	}

}
