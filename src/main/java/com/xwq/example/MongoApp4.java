package com.xwq.example;

import org.springframework.data.domain.Example;

import com.xwq.domain.Person2;

public class MongoApp4 {

	public static void main(String[] args) {
		Person2 person2 = new Person2();
		person2.setFirstname("Dave");
		
		Example<Person2> example = Example.of(person2);
		
		
	}
}
