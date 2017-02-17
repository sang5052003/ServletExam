package com.kosta.example.person.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kosta.example.person.domain.Person;
import com.kosta.example.person.store.factory.ConnectionFactory;
import com.kosta.example.person.store.factory.JDBCUtils;

public class PersonStoreLogic {
	private ArrayList<Person> list;
	
	private ConnectionFactory factory;
	//ConnectionFactory connection pool이 구현한 팩토리가 존재(아파치에..)
	
	public PersonStoreLogic() {
		
		this.factory = ConnectionFactory.getInstance();
		
		this.list = new ArrayList<>();
		list.add(new Person("진강사", 25, "서울 관악구"));
		list.add(new Person("홍길동", 30, "서울 금천구"));
		list.add(new Person("이대호", 33, "부산시 사하구"));
	}
	
	public Person getPerson(String name){
		Person person = null;
		for(Person p : this.list){
			if(p.getName().equals(name)){
				return p;
			}
		}
		return person;
	}
	
	public boolean insertPerson(Person person){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = this.factory.createConnection();
			
			pstmt = conn.prepareStatement(
					"insert into person_tb(name, age, address) values(?,?,?)"
					);
			
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setString(3, person.getAddress());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.clearResource(conn, pstmt);
		}
		
		return count > 0;
	}
}
