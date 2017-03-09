package blog.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import blog.domain.Author;
import blog.store.facade.AuthorStore;
import blog.store.mapper.AuthorMapper;

public class AuthorStoreLogic implements AuthorStore {

	private SqlSessionFactory factory;
	
	public AuthorStoreLogic() {
		this.factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public Author findAuthor(String id) {
		
		SqlSession session = this.factory.openSession();
		Author author = null;
		
		try{
			
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			author = mapper.findAuthor(id);
			
		}finally{
			session.close();
		}
		
		return author;
	}

	@Override
	public List<Author> findAllAuthors() {
		
		SqlSession session = this.factory.openSession();
		List<Author> list = null;
		try{
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			list = mapper.findAllAuthors();
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<Author> findAuthorsByName(String name) {
		
		SqlSession session = this.factory.openSession();
		List<Author> list = null;
		try{
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			list = mapper.findAuthorsByName(name);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public void registAuthor(Author author) {
		
		SqlSession session = this.factory.openSession();
		
		try{
			
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			mapper.registAuthor(author);
			session.commit();
			//?
//			if(mapper != null){
//				
//			}else{
//				session.rollback(); 
//			}
			
		}finally{
			session.close();
		}
	}

	@Override
	public int updateAuthor(Author author) {
		
		SqlSession session = this.factory.openSession();
		int result = 0;
		
		try{
			
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			result = mapper.updateAuthor(author);
			
			if(result > 0){
				session.commit();
			}
			else{
				session.rollback();
			}
			
		}finally{
			session.close();
		}
		
		return result;
	}

	@Override
	public int deleteAuthor(String id) {
		
		SqlSession session = this.factory.openSession();
		int result = 0;
		try{
			
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			result = mapper.deleteAuthor(id);
			
			if(result > 0){
				session.commit();
			}
			else{
				session.rollback();
			}
			
		}finally{
			session.close();
		}
		
		return result;
	}

	@Override
	public List<Author> findAuthorsByIds(List<String> ids) {
		
		SqlSession session = this.factory.openSession();
		List<Author> list = null;
		try{
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			list = mapper.findAuthorsByIds(ids);
			
			
		}finally{
			session.close();
		}
		
		return list;
	}

	@Override
	public List<Author> findAuthorByCondition(Map<String, String> conditionMap) {
		
		SqlSession session = this.factory.openSession();
		List<Author> list = null;
		try{
			AuthorMapper mapper = session.getMapper(AuthorMapper.class);
			list = mapper.findAuthorByCondition(conditionMap);
			
		}finally{
			session.close();
		}
		
		return list;
	}

}
