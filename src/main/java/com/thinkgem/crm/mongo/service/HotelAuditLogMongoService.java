package com.thinkgem.crm.mongo.service;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.thinkgem.crm.mongo.dao.HotelAuditLogMongoDao;
import com.thinkgem.crm.mongo.dao.MongoClientDelegate;
import com.thinkgem.crm.mongo.entity.HotelAuditLogMongo;
import com.thinkgem.jeesite.common.persistence.Page;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 酒店审核修改日志记录
 */
@Service
@Transactional(readOnly = true)
public class HotelAuditLogMongoService {
	/** 酒店操作日志 */
	@Autowired
	private HotelAuditLogMongoDao auditLogMongoDao;

	public Page<HotelAuditLogMongo> auditLogList(Page<HotelAuditLogMongo> page, HotelAuditLogMongo auditLogMongo) {
		if(auditLogMongo.getHotelId() == null){
			return null;
		}
		Bson filter = Filters.and(Filters.eq("hotel_id", auditLogMongo.getHotelId()));
//		Bson filter = new BsonDocument();
		Bson sort = new Document("_id", -1);
		//必须在前,查看下面注释
		Long allCount = auditLogMongoDao.findCount(filter);
		page.setCount(allCount);
		// 要想使用page.getFirstResult(), page.getMaxResults()作为分页起始结束位置,需先设置以上page.setCount(allCount);
		List<HotelAuditLogMongo> list = auditLogMongoDao.find(filter,sort,page.getFirstResult(), page.getMaxResults());
		page.setList(list);
		return page;
	}
}