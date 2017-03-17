package com.xwq.example;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

/**
 * 入门
 * @author WQXia
 *
 */
public class MongoApp {

	private static final Log log = LogFactory.getLog(MongoApp.class);
	
	public static void main(String[] args) throws Exception{
		//使用MongoDbFactory实例来配置MongoTemplate
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "MyMongo"));
		Person person = new Person("Joe", 34);
		
		// Insert
		mongoOps.insert(person);
		log.info("Insert:" + person);
		
		//Find
		mongoOps.findById(person.getId(), Person.class);
		log.info("Found:" + person);
		
		//Update
		mongoOps.updateFirst(Query.query(Criteria.where("name").is("Joe")), Update.update("age", 35), Person.class);
		person = mongoOps.findOne(Query.query(Criteria.where("name").is("Joe")), Person.class);
		log.info("Update:" + person);
		
		//Delete
		mongoOps.remove(person);
		
		//Check that deletion worked
		List<Person> people = mongoOps.findAll(Person.class);
		log.info("Number of people = " + people.size());
		
		mongoOps.dropCollection("person");
	}
}
