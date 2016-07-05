/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.hotel.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CRM酒店信息Entity
 * @author 李雪楠
 * @version 2016-03-28
 */
public class EHotelExport extends EHotel {

	private static final long serialVersionUID = 1L;
	private String hotelname;		// hotelname
	private String hotelcontactname;		// hotelcontactname
	private Date regtime;		// regtime
	private String disid;		// disid
	private String detailaddr;		// detailaddr
	private String longitude;		// longitude
	private String latitude;		// latitude
	private Date opentime;		// opentime
	private Date repairtime;		// repairtime
	private String roomnum;		// roomnum
	private String introduction;		// introduction
	private String traffic;		// traffic
	private String hotelpic;		// hotelpic
    private String facadePic;       // 门头照片
    private List<String> lobby;       // 大堂照片
    private List<String> mainHousing;       // 主力房型
	private String peripheral;		// peripheral
	private String businesslicensefront;		// businesslicensefront
	private String businesslicenseback;		// businesslicenseback
	private String pms;		// pms
	private String state;		// state
    private String checkState;  // 自建酒店审核状态
	private String visible;		// visible
	private String reason;		// reason
	private Date reasontime;		// reasontime
	private Date updatetime;		// updatetime
	private String pricestate;		// pricestate
	private String pricereason;		// pricereason
	private String pmsstatus;		// pmsstatus
	private String pmsuser;		// pmsuser
	private String idcardfront;		// idcardfront
	private String idcardback;		// idcardback
	private String retentiontime;		// retentiontime
	private String defaultleavetime;		// defaultleavetime
	private String hotelphone;		// hotelphone
	private String isnewpms;		// isnewpms
	private String rulecode;		// 规则值
	private String isthreshold;		// 阈值结算字段
	private String provcode;		// 省行政编码
    private String provName;
	private String citycode;		// 市行政编码
    private String cityName;
	private String discode;		// 区县行政编码
    private String disName;
	private String areacode;		// 商圈行政编码
	private String areaname;		// 商圈名称
	private String qtphone;		// 前台电话
	private String hoteltype;		// 酒店类型
	private String pmshotelname;		// pmshotelname
	private String bossPhone;		// bossPhone
	private String bossPhoneNew;		// bossPhoneNew更换老板手机号用到
	private String bossName;		// bossName
	private String modifyType;//修改手机号还是转让酒店
    private String isSelfBuildHotel;
    private String userCode;        // 销售id
    private String userName;        // 销售名称
    private String userMobile;      // 销售电话
    private List<TRoomType> roomTypeList; //房型list
    private List<Map<String,Object>> basicService; //酒店设施等数据
    private List<Map<String,Object>> typeSpecial; //酒店类型特色数据
    private List<Map<String,Object>> businessArea ; //酒店商圈数据


	public EHotelExport() {
		super();
	}

	public EHotelExport(String id){
		super(id);
	}

	@Length(min=1, max=50, message="hotelname长度必须介于 1 和 50 之间")
    @ExcelField(title = "酒店名称", align = 2, sort = 20)
	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	
	@Length(min=0, max=25, message="hotelcontactname长度必须介于 0 和 25 之间")
	public String getHotelcontactname() {
		return hotelcontactname;
	}

	public void setHotelcontactname(String hotelcontactname) {
		this.hotelcontactname = hotelcontactname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	@Length(min=0, max=11, message="disid长度必须介于 0 和 11 之间")
	public String getDisid() {
		return disid;
	}

	public void setDisid(String disid) {
		this.disid = disid;
	}
	
	@Length(min=0, max=100, message="detailaddr长度必须介于 0 和 100 之间")
	@ExcelField(title = "酒店地址", align = 2, sort = 60)
	public String getDetailaddr() {
		return detailaddr;
	}

	public void setDetailaddr(String detailaddr) {
		this.detailaddr = detailaddr;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairtime() {
		return repairtime;
	}

	public void setRepairtime(Date repairtime) {
		this.repairtime = repairtime;
	}
	
	@Length(min=0, max=11, message="roomnum长度必须介于 0 和 11 之间")
	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	
	public String getHotelpic() {
		return hotelpic;
	}

	public void setHotelpic(String hotelpic) {
        if (StringUtils.isNotBlank(hotelpic)) {
            try {// 要进行解析json
                JSONArray jsonArray = JSONArray.parseArray(hotelpic);
				lobby = new ArrayList<String>();
				mainHousing = new ArrayList<String>();
				for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
					//门头
                    if (jsonObject.getString("name").equalsIgnoreCase("def")) {
                        this.facadePic = jsonObject.getJSONArray("pic").getJSONObject(0).getString("url");
                    }
                    //大堂
                    if (jsonObject.getString("name").equalsIgnoreCase("lobby")) {
						JSONArray lobbys = jsonObject.getJSONArray("pic");
						for(int t = 0;t < lobbys.size(); t++){
							lobby.add(lobbys.getJSONObject(t).getString("url"));
						}
                    }
					//主力房型
					if (jsonObject.getString("name").equalsIgnoreCase("mainHousing")) {
						JSONArray mainHousings = jsonObject.getJSONArray("pic");
						for(int t = 0;t < mainHousings.size(); t++){
							mainHousing.add(mainHousings.getJSONObject(t).getString("url"));
						}
					}
                }
            } catch (Exception e) {

            }
        }

		this.hotelpic = hotelpic;
	}

	public List<String> getMainHousing() {
		return mainHousing;
	}

	public void setMainHousing(List<String> mainHousing) {
		this.mainHousing = mainHousing;
	}

	public String getPeripheral() {
		return peripheral;
	}

	public void setPeripheral(String peripheral) {
		this.peripheral = peripheral;
	}
	
	@Length(min=0, max=100, message="businesslicensefront长度必须介于 0 和 100 之间")
	public String getBusinesslicensefront() {
		return businesslicensefront;
	}

	public void setBusinesslicensefront(String businesslicensefront) {
		this.businesslicensefront = businesslicensefront;
	}
	
	@Length(min=0, max=100, message="businesslicenseback长度必须介于 0 和 100 之间")
	public String getBusinesslicenseback() {
		return businesslicenseback;
	}

	public void setBusinesslicenseback(String businesslicenseback) {
		this.businesslicenseback = businesslicenseback;
	}
	
	@Length(min=0, max=50, message="pms长度必须介于 0 和 50 之间")
	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}
	
	@Length(min=1, max=11, message="state长度必须介于 1 和 11 之间")
	@ExcelField(title = "审核类型", align = 2, sort = 90, dictType="hotel_audit_type")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=1, message="visible长度必须介于 1 和 1 之间")
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	@Length(min=0, max=200, message="reason长度必须介于 0 和 200 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReasontime() {
		return reasontime;
	}

	public void setReasontime(Date reasontime) {
		this.reasontime = reasontime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Length(min=0, max=11, message="pricestate长度必须介于 0 和 11 之间")
	public String getPricestate() {
		return pricestate;
	}

	public void setPricestate(String pricestate) {
		this.pricestate = pricestate;
	}
	
	@Length(min=0, max=200, message="pricereason长度必须介于 0 和 200 之间")
	public String getPricereason() {
		return pricereason;
	}

	public void setPricereason(String pricereason) {
		this.pricereason = pricereason;
	}
	
	@Length(min=1, max=11, message="pmsstatus长度必须介于 1 和 11 之间")
	public String getPmsstatus() {
		return pmsstatus;
	}

	public void setPmsstatus(String pmsstatus) {
		this.pmsstatus = pmsstatus;
	}
	
	@Length(min=0, max=100, message="pmsuser长度必须介于 0 和 100 之间")
	public String getPmsuser() {
		return pmsuser;
	}

	public void setPmsuser(String pmsuser) {
		this.pmsuser = pmsuser;
	}
	
	@Length(min=0, max=255, message="idcardfront长度必须介于 0 和 255 之间")
	public String getIdcardfront() {
		return idcardfront;
	}

	public void setIdcardfront(String idcardfront) {
		this.idcardfront = idcardfront;
	}
	
	@Length(min=0, max=255, message="idcardback长度必须介于 0 和 255 之间")
	public String getIdcardback() {
		return idcardback;
	}

	public void setIdcardback(String idcardback) {
		this.idcardback = idcardback;
	}
	
	@Length(min=0, max=6, message="retentiontime长度必须介于 0 和 6 之间")
	public String getRetentiontime() {
		return retentiontime;
	}

	public void setRetentiontime(String retentiontime) {
		this.retentiontime = retentiontime;
	}
	
	@Length(min=0, max=6, message="defaultleavetime长度必须介于 0 和 6 之间")
	public String getDefaultleavetime() {
		return defaultleavetime;
	}

	public void setDefaultleavetime(String defaultleavetime) {
		this.defaultleavetime = defaultleavetime;
	}
	
	@Length(min=0, max=400, message="hotelphone长度必须介于 0 和 400 之间")
	public String getHotelphone() {
		return hotelphone;
	}

	public void setHotelphone(String hotelphone) {
		this.hotelphone = hotelphone;
	}
	
	@Length(min=0, max=1, message="isnewpms长度必须介于 0 和 1 之间")
	public String getIsnewpms() {
		return isnewpms;
	}

	public void setIsnewpms(String isnewpms) {
		this.isnewpms = isnewpms;
	}
	
	@Length(min=0, max=20, message="规则值长度必须介于 0 和 20 之间")
	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}
	
	@Length(min=0, max=1, message="阈值结算字段长度必须介于 0 和 1 之间")
	public String getIsthreshold() {
		return isthreshold;
	}

	public void setIsthreshold(String isthreshold) {
		this.isthreshold = isthreshold;
	}
	
	@Length(min=0, max=10, message="省行政编码长度必须介于 0 和 10 之间")
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	
	@Length(min=0, max=10, message="市行政编码长度必须介于 0 和 10 之间")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	
	@Length(min=0, max=10, message="区县行政编码长度必须介于 0 和 10 之间")
	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}
	
	@Length(min=0, max=10, message="商圈行政编码长度必须介于 0 和 10 之间")
	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	
	@Length(min=0, max=50, message="商圈名称长度必须介于 0 和 50 之间")
	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
	@Length(min=0, max=20, message="前台电话长度必须介于 0 和 20 之间")
	public String getQtphone() {
		return qtphone;
	}

	public void setQtphone(String qtphone) {
		this.qtphone = qtphone;
	}
	
	@Length(min=0, max=2, message="酒店类型长度必须介于 0 和 2 之间")
	public String getHoteltype() {
		return hoteltype;
	}

	public void setHoteltype(String hoteltype) {
		this.hoteltype = hoteltype;
	}
	
	@Length(min=0, max=50, message="pmshotelname长度必须介于 0 和 50 之间")
	public String getPmshotelname() {
		return pmshotelname;
	}

	public void setPmshotelname(String pmshotelname) {
		this.pmshotelname = pmshotelname;
	}

	@ExcelField(title = "酒店电话", align = 2, sort = 80)
	public String getBossPhone() {
		return bossPhone;
	}

	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}

	@ExcelField(title = "酒店老板", align = 2, sort = 70)
	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public String getBossPhoneNew() {
		return bossPhoneNew;
	}

	public void setBossPhoneNew(String bossPhoneNew) {
		this.bossPhoneNew = bossPhoneNew;
	}

	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

    public String getIsSelfBuildHotel() {
        return isSelfBuildHotel;
    }

    public void setIsSelfBuildHotel(String isSelfBuildHotel) {
        this.isSelfBuildHotel = isSelfBuildHotel;
    }

    public String getFacadePic() {
        return facadePic;
    }

    public void setFacadePic(String facadePic) {
        this.facadePic = facadePic;
    }

    @ExcelField(title = "市", align = 2, sort = 40)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @ExcelField(title = "区", align = 2, sort = 50)
    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    @ExcelField(title = "省", align = 2, sort = 30)
    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title = "审核状态", align = 2, sort = 100, dictType="hotelCheckState")
    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    @ExcelField(title = "酒店id", align = 2, sort = 10)
    public String getHotelId() {
        return this.id;
    }

	public List<String> getLobby() {
		return lobby;
	}

	public void setLobby(List<String> lobby) {
		this.lobby = lobby;
	}

	public List<TRoomType> getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(List<TRoomType> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public List<Map<String,Object>> getBasicService() {
		return basicService;
	}

	public void setBasicService(List<Map<String,Object>> basicService) {
		this.basicService = basicService;
	}

	public List<Map<String,Object>> getTypeSpecial() {
		return typeSpecial;
	}

	public void setTypeSpecial(List<Map<String,Object>> typeSpecial) {
		this.typeSpecial = typeSpecial;
	}

	public List<Map<String,Object>> getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(List<Map<String,Object>> businessArea) {
		this.businessArea = businessArea;
	}

}