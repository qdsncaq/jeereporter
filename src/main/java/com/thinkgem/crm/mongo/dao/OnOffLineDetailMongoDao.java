package com.thinkgem.crm.mongo.dao;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.thinkgem.crm.mongo.entity.OnOffLineDetailMongo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Component
public class OnOffLineDetailMongoDao {

	@Autowired
	private MongoClientDelegate mongoClient;

	private MongoCollection<Document> collection;

	public void save(OnOffLineDetailMongo entity) {
		collection.insertOne(this.toDocument(entity));
	}

	public void batchSave(List<OnOffLineDetailMongo> entityList) {
		collection.insertMany(this.toDocList(entityList));
	}

	/**
	 * 查询业务日志.
	 *
	 * @param filter
	 *            过滤器，编写参照{code Filters}，字段名称参照{code T}
	 * @param sort
	 *            排序条件<br>
	 *            排序写法:new Document("borough", 1).append("address.zipcode", 1)
	 *            升降序说明:1 for ascending and -1 for descending.
	 * @return 业务日志列表.
	 *
	 * @see com.mongodb.client.model.Filters
	 */
	public List<OnOffLineDetailMongo> find(Bson filter, Bson sort) {
		return this.find(filter, sort, -1, -1);
	}

	/**
	 * 查询业务日志.
	 *
	 * @param filter
	 *            过滤器，编写参照{code Filters}，字段名称参照{code T}
	 * @param sort
	 *            排序条件<br>
	 *            排序写法:new Document("borough", 1).append("address.zipcode", 1)
	 *            升降序说明:1 for ascending and -1 for descending.
	 * @param skip
	 *            偏移量(-1不设置).
	 * @param limit
	 *            返回数量(-1不设置).
	 * @return 业务日志列表.
	 *
	 * @see com.mongodb.client.model.Filters
	 */
	public List<OnOffLineDetailMongo> find(Bson filter, Bson sort, int skip, int limit) {
		FindIterable<Document> docIter = collection.find(filter);
		if (sort != null) {
			docIter.sort(sort);
		}
		if (skip != -1) {
			docIter.skip(skip);
		}
		if (limit != -1) {
			docIter.limit(limit);
		}
		return this.transfer(docIter);
	}

	/**
	 * 删除业务日志.
	 *
	 * @param filter
	 *            过滤器.
	 *
	 * @see com.mongodb.client.model.Filters
	 */
	public void delete(Bson filter) {
		collection.deleteMany(filter);
	}

	private List<OnOffLineDetailMongo> transfer(FindIterable<Document> docIter) {
		List<OnOffLineDetailMongo> entityList = new ArrayList<OnOffLineDetailMongo>();
		docIter.forEach(new BlockAction(entityList));
		return entityList;
	}

	private List<Document> toDocList(List<OnOffLineDetailMongo> entityList) {
		List<Document> docList = new ArrayList<Document>();
		entityList.forEach(entity -> docList.add(this.toDocument(entity)));
		return docList;
	}


	private class BlockAction implements Block<Document> {

		private List<OnOffLineDetailMongo> entityList = null;

		public BlockAction(List<OnOffLineDetailMongo> entityList) {
			super();
			this.entityList = entityList;
		}

		@Override
		public void apply(Document t) {
			this.getEntityList().add(toEntity(t));
		}

		private List<OnOffLineDetailMongo> getEntityList() {
			return this.entityList;
		}

	}

	@Autowired
	private void getMongoCollection(){
		collection = mongoClient.getMongoCollection(MongoClientDelegate.MongoCollectionEnum.CRM_ONOFFLINE_DETAIL);
	}


	private Document toDocument(OnOffLineDetailMongo entity) {
		Document doc = new Document();
		this.put(doc, "id", entity.getId());	// 自增ID
		this.put(doc, "hotel_id", entity.getHotelId()); // 酒店ID
		this.put(doc, "pms", entity.getPms()); // pms编码
		this.put(doc, "operate_id", entity.getOperateId()); // 操作人ID
		this.put(doc, "operate_name", entity.getOperateName()); // 操作人名称
//		this.put(doc, "on_off_time", entity.getOnOffTime()); //明细操作时间
		this.put(doc, "on_off_time", entity.getOnOffTime()); //明细操作时间
//		this.put(doc, "create_time", entity.getCreateTime()); // 上下线时间
		this.put(doc, "create_time", new Date()); // 上下线时间
//		this.put(doc, "update_time", entity.getUpdateTime()); // 更新时间
		this.put(doc, "update_time", new Date()); // 更新时间
		this.put(doc, "remarks", entity.getRemarks()); //文字说明
		this.put(doc, "remark_type", entity.getRemarkType()); //文字说明
		this.put(doc, "type", entity.getType()); // 上下线类型，1-上线，2-下线
//		this.put(doc, "del_flag", entity.getDelFlag()); // 0-删除,1-正常
		this.put(doc, "del_flag", "1"); // 0-删除,1-正常
		return doc;
	}

	private OnOffLineDetailMongo toEntity(Document doc) {
		OnOffLineDetailMongo entity = new OnOffLineDetailMongo();
		entity.setId(doc.getLong("id"));
		entity.setHotelId(doc.getLong("hotel_id")); // 酒店ID
		entity.setPms(doc.getString("pms")); // pms编码
		entity.setOperateId(doc.getString("operate_id")); // 操作人ID
		entity.setOperateName(doc.getString("operate_name")); // 操作人名称
		entity.setOnOffTime(doc.getDate("on_off_time")); //明细操作时间
		entity.setRemarks(doc.getString("remarks")); //文字说明
		entity.setRemarkType(doc.getString("remark_type")); //文字说明
		entity.setType(doc.getString("type")); // 上下线类型，1-上线，2-下线
		return entity;
	}

	private void put(Document doc, String key, Object value) {
		if (value == null) {
			return;
		}
		doc.put(key, value);
	}
}