package com.lz.mongo.bislog;

import java.util.Date;

import org.bson.Document;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 业务日志实体.
 * <p>
 * 可以采用链式赋值.
 *
 * @author zhaoshb
 * @since 0.0.1
 */
public class BisLog extends DataEntity<BisLog> {

	/**
	 *
	 */
	private static final long serialVersionUID = -6016004536838792301L;

	public static final String CN_SYSTEM = "system";

	public static final String CN_BUSSINESSID = "bussinessId";

	public static final String CN_BUSSINESSTYPE = "bussinssType";

	public static final String CN_CONTENT = "content";

	public static final String CN_OPERATOR = "operator";

	public static final String CN_CREATETIME = "createTime";

	private String system = null;

	private String bussinessId = null;

	private String bussinssType = null;

	private String content = null;

	private String operator = null;

	private Date createTime = new Date();

	public String getSystem() {
		return this.system;
	}

	public BisLog setSystem(String system) {
		this.system = system;

		return this;
	}

	public String getBussinessId() {
		return this.bussinessId;
	}

	public BisLog setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;

		return this;
	}

	public String getBussinssType() {
		return this.bussinssType;
	}

	public BisLog setBussinssType(String bussinssType) {
		this.bussinssType = bussinssType;

		return this;
	}

	public String getContent() {
		return this.content;
	}

	public BisLog setContent(String content) {
		this.content = content;

		return this;
	}

	public String getOperator() {
		return this.operator;
	}

	public BisLog setOperator(String operator) {
		this.operator = operator;

		return this;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public BisLog setCreateTime(Date createTime) {
		this.createTime = createTime;

		return this;
	}

	public Document toDocument() {
		this.check();

		Document doc = new Document();
		this.put(doc, BisLog.CN_SYSTEM, this.getSystem());
		this.put(doc, BisLog.CN_BUSSINESSID, this.getBussinessId());
		this.put(doc, BisLog.CN_BUSSINESSTYPE, this.getBussinssType());
		this.put(doc, BisLog.CN_CONTENT, this.getContent());
		this.put(doc, BisLog.CN_OPERATOR, this.getOperator());
		this.put(doc, BisLog.CN_CREATETIME, this.getCreateTime());

		return doc;
	}

	public static BisLog toBisLog(Document doc) {
		BisLog bisLog = new BisLog();
		bisLog.setSystem(doc.getString(BisLog.CN_SYSTEM));
		bisLog.setBussinessId(doc.getString(BisLog.CN_BUSSINESSID));
		bisLog.setBussinssType(doc.getString(BisLog.CN_BUSSINESSTYPE));
		bisLog.setContent(doc.getString(BisLog.CN_CONTENT));
		bisLog.setOperator(doc.getString(BisLog.CN_OPERATOR));
		bisLog.setCreateTime(doc.getDate(BisLog.CN_CREATETIME));

		return bisLog;
	}

	private void check() {
		if (this.getSystem() == null) {
			throw new IllegalArgumentException("BisLog system field can't  be null！");
		}
		if (this.getBussinessId() == null) {
			throw new IllegalArgumentException("BisLog bussinessId field can't  be null！");
		}
		if (this.getBussinssType() == null) {
			throw new IllegalArgumentException("BisLog bussinssType field can't  be null！");
		}
		if (this.getContent() == null) {
			throw new IllegalArgumentException("BisLog content field can't  be null！");
		}
		if (this.getOperator() == null) {
			throw new IllegalArgumentException("BisLog operator can't  be null！");
		}
	}

	private void put(Document doc, String key, Object value) {
		if (value == null) {
			return;
		}
		doc.put(key, value);
	}

}
