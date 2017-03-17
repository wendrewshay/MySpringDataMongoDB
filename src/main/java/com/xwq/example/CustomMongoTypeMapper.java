package com.xwq.example;

import java.util.Set;

import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;

import com.mongodb.DBObject;

/**
 * 通过Spring Java配置配置自定义MongoTypeMapper---类型映射
 * @author WQXia
 *
 */
public class CustomMongoTypeMapper extends DefaultMongoTypeMapper{

	@Override
	public void writeTypeRestrictions(DBObject result, Set<Class<?>> restrictedTypes) {
		//TODO
		super.writeTypeRestrictions(result, restrictedTypes);
	}

}
