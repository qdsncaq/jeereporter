package com.thinkgem.jeesite.crm.mongo;

import com.mongodb.client.model.Filters;
import com.thinkgem.crm.mongo.dao.OnOffLineDetailMongoDao;
import com.thinkgem.crm.mongo.entity.OnOffLineDetailMongo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 注解方式获取spring管理的对象
 */
//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"" +
		"classpath:spring-test-context.xml"
		,"classpath:spring-context.xml"
//		,"classpath:spring-context-activiti.xml"
//		,"classpath:spring-context-rest.xml"
//		,"classpath:spring-context-jedis.xml"
//		,"classpath:spring-context-dubbo-provider.xml"
//		,"classpath:spring-context-dubbo-consumer.xml"
//		,"classpath:spring-context-shiro.xml"
//		,"classpath:spring-mvc.xml"
})
public class OnOffLineDetailMongoDaoTest {

//	// 模拟request,response
//	private MockHttpServletRequest request;
//	private MockHttpServletResponse response;
//
//	@Before
//	public void setUp(){
//		request = new MockHttpServletRequest();
//		request.setCharacterEncoding("UTF-8");
//		response = new MockHttpServletResponse();
//	}


	private @Autowired
	OnOffLineDetailMongoDao mongoDao;

	@Test
	public void findTest(){
		Bson filter = Filters.and(Filters.eq("id", 955648));
		Bson sort = new Document("id", -1);
		List<OnOffLineDetailMongo> list = mongoDao.find(filter,sort);
		System.out.println(list);
	}
}
