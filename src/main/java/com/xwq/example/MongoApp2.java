package com.xwq.example;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.xwq.domain.Person;

/**
 * 乐观锁demo
 * @author WQXia
 *
 */
public class MongoApp2 {

	public static void main(String[] args) {
		MongoTemplate template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "MyMongo"));
		//如下设置不会抛出乐观锁异常，如果设置WriteConcern为ACKNOWLEDGED，则会抛出异常
		//template.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
		
		Person daenerys = new Person("Daenerys");
		//内部插入文档，version设置为0
		template.insert(daenerys);
		
		//加载刚插入的文档，version仍然是0
		Person tmp = template.findOne(Query.query(Criteria.where("id").is(daenerys.getId())), Person.class);
		
		//更新文档version = 0，碰撞设置version为1
		daenerys.setName("Lucy");
		template.save(daenerys);
		
		//尝试更新先前加载的文档version=0，但此时实际version=1，版本不匹配，抛出OptimisticLockingFailureException异常
		template.save(tmp);// throws OptimisticLockingFailureException
	}
}
