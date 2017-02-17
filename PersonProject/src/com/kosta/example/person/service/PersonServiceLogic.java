package com.kosta.example.person.service;

import com.kosta.example.person.domain.Person;
import com.kosta.example.person.store.PersonStoreLogic;

public class PersonServiceLogic {
	private PersonStoreLogic store;
	
	public PersonServiceLogic() {
		store = new PersonStoreLogic();
	}
	
	public Person getPerson(String name){
		return this.store.getPerson(name);
	}
	
	public boolean insertPerson(Person person){
		return this.store.insertPerson(person);
	}
}
