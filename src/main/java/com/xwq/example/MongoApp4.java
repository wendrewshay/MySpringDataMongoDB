package com.xwq.example;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.xwq.dao.MyExampleRepository;
import com.xwq.domain.Person2;

/**
 * Example查询
 * @author Think
 *
 */
public class MongoApp4 {

	public static void main(String[] args) {
		MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "MyMongo"));
		
		Person2 person2 = new Person2();
		person2.setFirstname("Dave");
		template.insert(person2);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstname", match -> match.endsWith())
				.withMatcher("firstname", match -> match.startsWith());
//		.withIgnorePaths("lastname")
//		.withIncludeNullValues()
//		.withStringMatcher(StringMatcher.ENDING);
		
		Example<Person2> example = Example.of(person2, exampleMatcher);
		
	}
}
