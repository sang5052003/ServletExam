package store;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Customer;

public class CustomerStoreLogic {
	private static final String resource = "config.xml";
	
	private SqlSessionFactory getSessionFactory(){
		
		Reader reader = null;
		
		try {
			//myBatis의 config(설정)를 읽어 온다
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new SqlSessionFactoryBuilder().build(reader);
	}
	
	public int regist(Customer customer){
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		try{
			//"insertCustomer" -> mapper에 설정한 insert문의 id
			int result = sqlSession.insert("insertCustomer", customer);
			
			if(result > 0){
				sqlSession.commit(); //auto커밋이 아니다
			} else{
				sqlSession.rollback();
			}
			
			return result;
			
		}finally {
			sqlSession.close();
		}
	}
	
	public List<Customer> searchAll(){

		//반환값으로 리스트가 필요없다 알아서 만들어줌
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		try{
			return sqlSession.selectList("selectAll"); //결과로 null값이 되지 않는다(리스트는 생성되니까)
		} finally {
			sqlSession.close();
		}
	}
	
	public int update(Customer cus){
		
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		try{
			
			int result = sqlSession.update("update", cus);
			
			if(result > 0){
				sqlSession.commit();
			}
			else{
				sqlSession.rollback();
			}
			
			return result;
			
		} finally {
			sqlSession.close();
		}
	}
	
	public int delete(String id){
		SqlSession sqlSession = this.getSessionFactory().openSession();
		
		
		try{
			int result = sqlSession.delete("delete", id);
			if(result > 0){
				sqlSession.commit();
			}
			else{
				sqlSession.rollback();
			}
			
			return result;
			
		} finally{
			sqlSession.close();
		}
	}
}
