package com.thinkgem.crm.mongo.dao;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.thinkgem.crm.mongo.entity.HotelAuditLogMongo;
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
public class HotelAuditLogMongoDao {

	@Autowired
	private MongoClientDelegate mongoClient;

	private MongoCollection<Document> collection;

	public void save(HotelAuditLogMongo entity) {
		collection.insertOne(this.toDocument(entity));
	}

	public void batchSave(List<HotelAuditLogMongo> entityList) {
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
	public List<HotelAuditLogMongo> find(Bson filter, Bson sort) {
		return this.find(filter, sort, -1, -1);
	}

	/**
	 * 按条件筛选查询总条数
	 * @param filter
	 * @return
     */
	public Long findCount(Bson filter) {
		return collection.count(filter);
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
	public List<HotelAuditLogMongo> find(Bson filter, Bson sort, int skip, int limit) {
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

	private List<HotelAuditLogMongo> transfer(FindIterable<Document> docIter) {
		List<HotelAuditLogMongo> entityList = new ArrayList<HotelAuditLogMongo>();
		docIter.forEach(new BlockAction(entityList));
		return entityList;
	}

	private List<Document> toDocList(List<HotelAuditLogMongo> entityList) {
		List<Document> docList = new ArrayList<Document>();
		for (HotelAuditLogMongo entity : entityList) {
			docList.add(this.toDocument(entity));
		}
		return docList;
	}


	private class BlockAction implements Block<Document> {

		private List<HotelAuditLogMongo> entityList = null;

		public BlockAction(List<HotelAuditLogMongo> entityList) {
			super();
			this.entityList = entityList;
		}

		@Override
		public void apply(Document t) {
			this.getEntityList().add(toEntity(t));
		}

		private List<HotelAuditLogMongo> getEntityList() {
			return this.entityList;
		}

	}

	@Autowired
	private void getMongoCollection(){
		collection = mongoClient.getMongoCollection(MongoClientDelegate.MongoCollectionEnum.CRM_HOTEL_AUDIT_LOG);
	}


	private Document toDocument(HotelAuditLogMongo entity) {
		Document doc = new Document();
		this.put(doc, "hotel_id", entity.getHotelId()); // 酒店ID
		this.put(doc, "pms", entity.getPms()); // pms编码
		this.put(doc, "operate_id", entity.getOperateId()); // 操作人ID
		this.put(doc, "operate_name", entity.getOperateName()); // 操作人名称
		this.put(doc, "operate_time", new Date()); // 操作时间
		this.put(doc, "remarks", entity.getRemarks()); //文字说明
		this.put(doc, "type", entity.getType()); // 100-提交,200-驳回,999-通过
		this.put(doc, "before", entity.getBefore()); // 之前
		this.put(doc, "after", entity.getAfter()); // 之后
		this.put(doc, "different", entity.getDifferent()); // 不同
		return doc;
	}

	private HotelAuditLogMongo toEntity(Document doc) {
		HotelAuditLogMongo entity = new HotelAuditLogMongo();
		entity.setId(doc.getObjectId("_id").toString()); // mongo ID
		entity.setHotelId(doc.getLong("hotel_id")); // 酒店ID
		entity.setPms(doc.getString("pms")); // pms编码
		entity.setOperateId(doc.getString("operate_id")); // 操作人ID
		entity.setOperateName(doc.getString("operate_name")); // 操作人名称
		entity.setOperateTime(doc.getDate("operate_time")); //操作时间
		entity.setRemarks(doc.getString("remarks")); //文字说明
		entity.setType(doc.getInteger("type")); // 100-提交,200-驳回,999-通过
		entity.setBefore(doc.get("before")); // 之前
		entity.setAfter(doc.get("after")); // 之后
		// 不同部分为json数据,get出后为ArrayList
		Object different = doc.get("different"); // 不同
		entity.setDifferent(different); // 不同
		if(different != null && (different instanceof List)){ //是数组时,对象才为List
			List<Document> list = (List<Document>) different;
			for(Document document: list){
				entity.getDifferents().add(document.getString("desc"));
			}
		}
		return entity;
	}

	private void put(Document doc, String key, Object value) {
		if (value == null) {
			return;
		}
		doc.put(key, value);
	}
}