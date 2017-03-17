package com.xwq.common;

import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.WriteConcernResolver;

import com.mongodb.WriteConcern;

/**
 * POJO与值的映射策略
 * @author WQXia
 *
 */
public class MyAppWriteConcernResolver implements WriteConcernResolver{

	@Override
	public WriteConcern resolve(MongoAction action) {
		if(action.getEntityType().getSimpleName().contains("Person")){
			return WriteConcern.UNACKNOWLEDGED;
		}else if(action.getEntityType().getSimpleName().contains("Other")) {
			return WriteConcern.JOURNALED;
		}
		return action.getDefaultWriteConcern();
	}

}
