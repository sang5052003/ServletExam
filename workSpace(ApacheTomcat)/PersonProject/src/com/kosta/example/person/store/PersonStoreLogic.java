package com.kosta.example.person.store;

import java.util.ArrayList;

import com.kosta.example.person.domain.Person;

public class PersonStoreLogic {
	private ArrayList<Person> list;
	
	public PersonStoreLogic() {
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
}
