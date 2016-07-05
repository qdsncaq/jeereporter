/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 酒店上下线信息统计Entity
 * @author 段文昌
 * @version 2016-04-21
 */
public class OnOffLine extends DataEntity<OnOffLine> {
	
	private static final long serialVersionUID = 1L;
	private Long hotelId;		// 酒店ID
	private String hotelName;		// 酒店名称
	private String hotelAddr;		// 酒店地址
    private String pms;             // pms编码
	private String provCode;		// 省编码
	private String provName;		// 省名称
	private String cityCode;		// 市编码
	private String cityName;		// 市名称
	private String disCode;		// 区编码
	private String disName;		// 区名称
	private Integer type;		// 上下线类型，1-上线，2-下线
	private String typeName;		// 上下线类型，1-上线，2-下线
	private Integer psbType;		// psb类型,101-自家，201-金盾,[301...n01-其它逐渐扩展]
	private String psbTypeName;		// psb类型,101-自家，201-金盾,[301...n01-其它逐渐扩展]
	private String operateId;		// 操作人ID
	private String operateName;		// 操作人名称
	private Date createTime;		// 上下线时间
    private Date createTimeEnd;		// 上下线筛选
	private Date onlineTime;		// 上线时间
	private Date onlineTimeEnd;	// 上线筛选结束时间
	private Date offlineTime;		// 下线时间
    private Date offlineTimeEnd;	// 下线筛选结束时间
    private Date firstTime;		// 首次上线时间
    private Date lastTime;		// 末次下线时间
    private String ip;		// IP地址
	private String visible;	//是否在线 T-在线,F-下线
	private String visibleName;	//是否在线 T-在线,F-下线
	private Integer condition; //查询条件 1全部;2上过线;3上线;4下线
	private String orderBy;	//sort
	private Date onOffTime;//明细操作时间
	private Integer detailType;//上下线类型
	private String detailTypeName;//操作类型
	private String detailRemarks;//明细原因

	public OnOffLine() {
		super();
	}

	public OnOffLine(String id){
		super(id);
	}

    @ExcelField(title = "酒店ID", align = 2, sort = 10)
	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

    @ExcelField(title = "酒店名称", align = 2, sort = 20)
	@Length(min=0, max=128, message="酒店名称长度必须介于 0 和 128 之间")
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
    @ExcelField(title = "酒店地址", align = 2, sort = 40)
	@Length(min=0, max=512, message="酒店地址长度必须介于 0 和 512 之间")
	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}
	
	@Length(min=0, max=12, message="省编码长度必须介于 0 和 12 之间")
	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	@ExcelField(title = "省", align = 2, sort = 50)
	@Length(min=0, max=64, message="省名称长度必须介于 0 和 64 之间")
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	@Length(min=0, max=12, message="市编码长度必须介于 0 和 12 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
    @ExcelField(title = "市", align = 2, sort = 60)
	@Length(min=0, max=64, message="市名称长度必须介于 0 和 64 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=12, message="区编码长度必须介于 0 和 12 之间")
	public String getDisCode() {
		return disCode;
	}

	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}
    @ExcelField(title = "区", align = 2, sort = 70)
	@Length(min=0, max=64, message="区名称长度必须介于 0 和 64 之间")
	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPsbType() {
		return psbType;
	}

	public void setPsbType(Integer psbType) {
		this.psbType = psbType;
	}
	
	@Length(min=0, max=64, message="操作人ID长度必须介于 0 和 64 之间")
	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	
	@Length(min=0, max=32, message="操作人名称长度必须介于 0 和 32 之间")
	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=20, message="IP地址长度必须介于 0 和 20 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

    public String getTypeName() {
        if(1 == this.type){
            this.typeName = "上线";
        } else if(2 == this.type){
            this.typeName = "下线";
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPsbTypeName() {
        if(Integer.valueOf("101").equals(this.psbType)){
            this.psbTypeName = "房爸爸";
        } else if(Integer.valueOf("201").equals(this.psbType)){
            this.psbTypeName = "金盾";
        }
        return psbTypeName;
    }

    public void setPsbTypeName(String psbTypeName) {
        this.psbTypeName = psbTypeName;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
    @ExcelField(title = "首次上线时间", align = 2, dataFormat = "yyyy-MM-dd HH:mm:ss", sort = 80)
    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }
    @ExcelField(title = "末次下线时间", align = 2, dataFormat = "yyyy-MM-dd HH:mm:ss", sort = 90)
    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    @ExcelField(title = "pms", align = 2, sort = 30)
    public String getPms() {
        return pms;
    }

    public void setPms(String pms) {
        this.pms = pms;
    }

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	public Date getOfflineTimeEnd() {
		return offlineTimeEnd;
	}

	public void setOfflineTimeEnd(Date offlineTimeEnd) {
		this.offlineTimeEnd = offlineTimeEnd;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOnlineTimeEnd() {
		return onlineTimeEnd;
	}

	public void setOnlineTimeEnd(Date onlineTimeEnd) {
		this.onlineTimeEnd = onlineTimeEnd;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	@ExcelField(title = "当前状态", align = 2, sort = 100)
	public String getVisibleName() {
//		if(("T").equals(this.visible)){
//			this.visibleName = "已上线";
//		} else if(("F").equals(this.visible)){
//			this.visibleName = "已下线";
//		}
		if(type.equals(1)){
			this.visibleName = "已上线";
		}else if(type.equals(2)){
			this.visibleName = "已下线";
		}else if(type.equals(3)){
			this.visibleName = "未录入";
		}else if(type.equals(4)){
			this.visibleName = "未上线";
		}
		return visibleName;
	}

	public void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	@ExcelField(title = "说明", align = 2, sort = 120)
	@Override
	public String getRemarks() {
		return super.getRemarks();
	}

	@Override
	public void setRemarks(String remarks) {
		// TODO Auto-generated method stub
		super.setRemarks(remarks);
	}
	

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getOnOffTime() {
		return onOffTime;
	}

	public void setOnOffTime(Date onOffTime) {
		this.onOffTime = onOffTime;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	public String getDetailTypeName() {
        if(1 == this.detailType){
            this.detailTypeName = "上线";
        } else if(2 == this.detailType){
            this.detailTypeName = "下线";
        }
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}

	public String getDetailRemarks() {
		return detailRemarks;
	}

	public void setDetailRemarks(String detailRemarks) {
		this.detailRemarks = detailRemarks;
	}

	@Override
	public String toString() {
		return "OnOffLine{" +
				"hotelId=" + hotelId +
				", hotelName='" + hotelName + '\'' +
				", hotelAddr='" + hotelAddr + '\'' +
				", pms='" + pms + '\'' +
				", provCode='" + provCode + '\'' +
				", provName='" + provName + '\'' +
				", cityCode='" + cityCode + '\'' +
				", cityName='" + cityName + '\'' +
				", disCode='" + disCode + '\'' +
				", disName='" + disName + '\'' +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", psbType=" + psbType +
				", psbTypeName='" + psbTypeName + '\'' +
				", operateId='" + operateId + '\'' +
				", operateName='" + operateName + '\'' +
				", createTime=" + createTime +
				", createTimeEnd=" + createTimeEnd +
				", onlineTime=" + onlineTime +
				", onlineTimeEnd=" + onlineTimeEnd +
				", offlineTime=" + offlineTime +
				", offlineTimeEnd=" + offlineTimeEnd +
				", firstTime=" + firstTime +
				", lastTime=" + lastTime +
				", ip='" + ip + '\'' +
				", visible='" + visible + '\'' +
				", visibleName='" + visibleName + '\'' +
				", condition='" + condition + '\'' +
				", remarks='" + getRemarks() + '\'' +
				", onOffTime='" + onOffTime + '\'' +
				", detailType='" + detailType + '\'' +
				", detailTypeName='" + detailTypeName + '\'' +
				", detailRemarks='" + detailRemarks + '\'' +
				'}';
	}
}