/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户关系维护Entity
 * @author jiajianhong
 * @version 2016-03-30
 */
public class RfCrmUserRelation extends DataEntity<RfCrmUserRelation> {
	
	private static final long serialVersionUID = 1L;
    private Long rid;
    private String userCode;
	private String supperUserCode;		// 超级用户编码
	private String userCode0;		// 0级用户编码
	private String qzUserCode;		// 区总编码
    private String qzUserName;
    private String userName;      // 区总名称
	private String qjUserCode;		// 区经编码
    private String qjUserName;
	private String salerUserCode;		// 销售编码
    private String salerUserName;
	private String userCode1;		// 用户编码扩展1
	private String userCode2;		// 用户编码扩展2
	private String level;		// 级别 职务
	private Integer roleId;		// 角色编码
	private Integer type;		//公司所属类型,1-乐住,2-金盾
	
	public RfCrmUserRelation() {
		super();
	}

	public RfCrmUserRelation(String id){
		super(id);
	}

	public enum UserRelationRoleEnum{

		SALER_IMIKE(2,"销售"),
		PARTNER_IMIKE(8,"合伙人"),
		MGR_IMIKE(7,"区域经理"),
		LARGE_MGR_IMIKE(100,"区总"),

		MGR_GOLD(9,"金盾区经"),
		PARTNER_GOLD(10,"金盾合伙人"),
		LARGE_MGR_GOLD(200,"金盾区总");



		private Integer key;
		private String value;

		UserRelationRoleEnum(Integer key, String value) {
			this.key = key;
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	@Length(min=1, max=100, message="超级用户编码长度必须介于 1 和 100 之间")
	public String getSupperUserCode() {
		return supperUserCode;
	}

	public void setSupperUserCode(String supperUserCode) {
		this.supperUserCode = supperUserCode;
	}
	
	@Length(min=1, max=100, message="0级用户编码长度必须介于 1 和 100 之间")
	public String getUserCode0() {
		return userCode0;
	}

	public void setUserCode0(String userCode0) {
		this.userCode0 = userCode0;
	}
	
	@Length(min=1, max=100, message="区总编码长度必须介于 1 和 100 之间")
	public String getQzUserCode() {
		return qzUserCode;
	}

	public void setQzUserCode(String qzUserCode) {
		this.qzUserCode = qzUserCode;
	}
	
	@Length(min=0, max=100, message="区经编码长度必须介于 0 和 100 之间")
	public String getQjUserCode() {
		return qjUserCode;
	}

	public void setQjUserCode(String qjUserCode) {
		this.qjUserCode = qjUserCode;
	}
	
	@Length(min=0, max=100, message="销售编码长度必须介于 0 和 100 之间")
	public String getSalerUserCode() {
		return salerUserCode;
	}

	public void setSalerUserCode(String salerUserCode) {
		this.salerUserCode = salerUserCode;
	}
	
	@Length(min=0, max=100, message="用户编码扩展1长度必须介于 0 和 100 之间")
	public String getUserCode1() {
		return userCode1;
	}

	public void setUserCode1(String userCode1) {
		this.userCode1 = userCode1;
	}
	
	@Length(min=0, max=100, message="用户编码扩展2长度必须介于 0 和 100 之间")
	public String getUserCode2() {
		return userCode2;
	}

	public void setUserCode2(String userCode2) {
		this.userCode2 = userCode2;
	}
	
	@Length(min=1, max=40, message="级别长度必须介于 1 和 40 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@NotNull(message="角色编码不能为空")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getQzUserName() {
        return qzUserName;
    }

    public void setQzUserName(String qzUserName) {
        this.qzUserName = qzUserName;
    }

    public String getSalerUserName() {
        return salerUserName;
    }

    public void setSalerUserName(String salerUserName) {
        this.salerUserName = salerUserName;
    }

    public String getQjUserName() {
        return qjUserName;
    }

    public void setQjUserName(String qjUserName) {
        this.qjUserName = qjUserName;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}