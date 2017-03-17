package com.xwq.example;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.BasicQuery;

import com.mongodb.MongoClient;
import com.xwq.domain.Person;

/**
 * 从纯JSON字符串创建Query实例
 * @author WQXia
 *
 */
public class MongoApp3 {

	private static Log log = LogFactory.getLog(MongoApp3.class);
	
	public static void main(String[] args) {
		MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "MyMongo"));
		BasicQuery basicQuery = new BasicQuery("{ name : 'Lucy' }");
		
//		BasicQuery query = new BasicQuery("{ age : { $lt : 50 }, accounts.balance : { $gt : 1000.00 }}");
		
		List<Person> result = template.find(basicQuery, Person.class);
		log.info(result.size());
	}
}
