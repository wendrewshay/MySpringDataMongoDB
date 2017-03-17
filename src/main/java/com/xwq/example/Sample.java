package com.xwq.example;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public class Sample {

	Contact value;
	
	public static void main(String[] args) {
		Sample sample = new Sample();
		sample.value = new Person();
		
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("localhost"), "MyMongo");
		mongoTemplate.save(sample);
		
		mongoTemplate.findAll(Object.class, "sample");
		
	}
}
