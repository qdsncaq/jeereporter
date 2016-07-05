package com.thinkgem.crm.mongo.dao;

import com.lz.mongo.MongoDelegate;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取mongodb中MongoDocment对象
 */
@Service
public class MongoClientDelegate {
    private static final Logger logger = LoggerFactory.getLogger(MongoClientDelegate.class);

    private static final String DB_NAME = "lezhu";

    /** mongo代理 **/
    private @Autowired
    MongoDelegate mongoDelegate;

    /**
     * 获取crm_onoffline_detal集合
     * @param collection MongoCollectionEnum对象,如需新collection,在枚举内添加
     * @return MongoCollection<Document>
     */
    public MongoCollection<Document> getMongoCollection(MongoCollectionEnum collection) {
        /** mongo连接 **/
        MongoClient mongoClient = mongoDelegate.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoClientDelegate.DB_NAME);
        return db.getCollection(collection.value());
    }

    /**
     * MongoCollection 集合
     * Created by duanwc on 16/4/28.
     */
    public enum MongoCollectionEnum {

        /** crm_onoffline_detail **/
        CRM_ONOFFLINE_DETAIL("crm_onoffline_detail"),
        /** crm_hotel_audit_log **/
        CRM_HOTEL_AUDIT_LOG("crm_hotel_audit_log");

        MongoCollectionEnum(String value) {
            this.value = value;
        }

        private String value;

        public String value() {
            return value;
        }
    }
}
