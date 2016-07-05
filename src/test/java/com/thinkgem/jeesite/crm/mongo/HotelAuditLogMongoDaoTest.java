package com.thinkgem.jeesite.crm.mongo;

import com.mongodb.client.model.Filters;
import com.thinkgem.crm.mongo.dao.HotelAuditLogMongoDao;
import com.thinkgem.crm.mongo.entity.HotelAuditLogMongo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 注解方式获取spring管理的对象
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"" +
		"classpath:spring-test-context.xml"
		,"classpath:spring-context.xml"
})
public class HotelAuditLogMongoDaoTest {

	private @Autowired
	HotelAuditLogMongoDao mongoDao;

	@Test
	public void findTest(){
		Bson filter = Filters.and(Filters.eq("hotel_id", 90057));
		Bson sort = new Document("id", -1);
		List<HotelAuditLogMongo> list = mongoDao.find(filter,sort);
		System.out.println(list);
	}

	@Test
	public void saveTest(){
		HotelAuditLogMongo modifyLog = new HotelAuditLogMongo();
		modifyLog.setHotelId(90057l);
		modifyLog.setPms("PkaskaJsdjndn");
		modifyLog.setBefore("1");
		modifyLog.setAfter("2");
		modifyLog.setDifferent("3");
		modifyLog.setOperateId("10023");
		modifyLog.setOperateName("段文昌");
//		mongoDao.save(modifyLog);
	}
}
