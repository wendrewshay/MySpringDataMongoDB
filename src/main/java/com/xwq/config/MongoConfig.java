package com.xwq.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.xwq.common.CustomMongoTypeMapper;
import com.xwq.common.MyAppWriteConcernResolver;

/**
 * MongoDB配置
 * @author WQXia
 *
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration{

	/**
	 * 使用Spring的MongoClientFactoryBean注册一个com.mongodb.Mongo对象并启用Spring的异常转换支持
	 * Factory bean that creates the com.mongodb.Mongo instance
	 * @return
	 */
//	public @Bean MongoClientFactoryBean mongo() throws Exception{
//		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
//		mongo.setHost("localhost");
//		return mongo;
//	}
	
	/**
	 * 注册mongo实例
	 */
	@Override
	public @Bean Mongo mongo() throws Exception {
		MongoCredential userCredentials = MongoCredential.createCredential("wqxia", getDatabaseName(), "123456".toCharArray());
		List<MongoCredential> credentialsList = new ArrayList<>();
		credentialsList.add(userCredentials);
		
		return new MongoClient(new ServerAddress("localhost"), credentialsList);
	}
	
	/**
	 * 数据库名称
	 */
	@Override
	protected String getDatabaseName() {
		return "MyMongo";
	}
	
	/**
	 * 注册类型映射器
	 * @return
	 */
	public @Bean MongoTypeMapper mongoTypeMapper() {
		return new CustomMongoTypeMapper();
	}

	/**
	 * 注册映射转换器
	 */
	@Override
	public @Bean MappingMongoConverter mappingMongoConverter() throws Exception {
		 MappingMongoConverter mappingMongoConverter = super.mappingMongoConverter();
		 mappingMongoConverter.setTypeMapper(mongoTypeMapper());
		 return null;
	}
	
	/**
	 * 向容器注册MongoDbFactory实例
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoDbFactory mongoDbFactory() throws Exception{
		return new SimpleMongoDbFactory((MongoClient)mongo(), getDatabaseName());
	}
	
	/**
	 * 向容器注册pojo与值的映射策略实例
	 * @return
	 */
	public @Bean WriteConcernResolver writeConcernResolver() {
		return new MyAppWriteConcernResolver();
	}
	
	/**
	 * 使用MongoDbFactory注册MongoTemplate的实例与容器
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoTemplate mongoTemplate() throws Exception{
		 MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
		 //设置写结果检查策略，记录日志、抛异常或默认什么也不做
		 mongoTemplate.setWriteResultChecking(WriteResultChecking.LOG);
		 //设置pojo与值的映射策略
		 mongoTemplate.setWriteConcernResolver(writeConcernResolver());
		 return mongoTemplate;
	}
	
}
