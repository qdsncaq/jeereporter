/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.entity;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * BMS用户表Entity
 * @author 李雪楠
 * @version 2016-03-23
 */
public class SyOrgUser extends DataEntity<SyOrgUser> {
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户编码
	private String userCodeView;		// 用户编码
	private String userLoginName;		// 用户系统登录名
	private String userName;		// 用户名称
	private String deptCode;		// 部门编码
	private String userPassword;		// 用户密码
	private String userSort;		// 用户排序号
	private String userHomePhone;		// 家庭电话
	private String userMobile;		// 手机号码
	private String userQq;		// 用户QQ
	private String userEmail;		// 用户邮箱
	private String userWorkLoc;		// 工位号
	private String userPost;		// 用户职位
	private String userPostLevel;		// 职位级别
	private String userRoom;		// 房间号
	private String userWorkNum;		// 工号
	private String userIdCard;		// 身份证号
	private String userBirthday;		// 出生日期
	private String userOfficePhone;		// 办公电话
	private String userNation;		// 民族
	private String userHeight;		// 身高(cm)
	private String userSex;		// 用户性别,0:男;1:女
	private String userHomeLand;		// 籍贯
	private String userPolitics;		// 政治面貌
	private String userMarriage;		// 婚姻状况
	private String userEduLevle;		// 学历
	private String userEduSchool;		// 学校
	private String userEduMajor;		// 专业
	private String userTitle;		// 职称
	private String userTitleDate;		// 职称日期
	private String userWorkDate;		// 参加工作日期
	private String userCmpyDate;		// 入职日期
	private String userState;		// 人员状态，1：在职；2：离职；3：退休
	private String cmpyCode = "1";		// 公司编码 默认值为1
	private String sFlag;		// 启用标志，1：是；2：否
	private String sUser;		// 添加者
	private String userLoginType;		// 登录认证类型，1：密码；2：动态口令；3：CA认证
	private String userExpireDate;		// 用户有效日期
	private String userPasswordDate;		// 密码有效日期
	private String sMtime;		// 更新时间
	private String userImgSrc;		// 用户头像源图片
	private String ptId;		// 用户模版编码
	private String userFrom;		// 来自哪个系统
	private String jiangangFlag;		// 兼岗标记：1兼岗用户；2非兼岗用户
	private String userShortName;		// 用户简称：名称拼音首字母简写
	private String userEnName;		// 用户拼音：用户英文名或者拼音
	private String userImg;		// user_img
	private String userEdulevle;		// user_edulevle
	private String usereExpireDate;		// usere_expire_date
	/** 发布任务用到的 start  */
	private List<String> userCodeList ; //userCode集合 '','',''
	private String isComplete;		// usere_expire_date
	private Long taskId;		// usere_expire_date
	private Date taskCreateTime;		// usere_expire_date
	private Date taskCompleteTime;		// usere_expire_date
	/** 发布任务用到的 end */
	
	/** 用户所属省份 */
	private String  provCode;
	/** 用户所属城市 */
	private String  cityCode;
	/** 用户所属省份 */
	private String  provName;
	/** 用户所属城市 */
	private String  cityName;
	private String  user;
	private String userMobileNew;		// 手机号码

    private String level;       // 级别  职务
	private Integer type;       // 所属公司  1-乐住,2-眯客
	private Integer roleId;		// 角色编码

    private String qzUserCode;
    private String qzUserName;
    private String qjUserCode;
    private String qjUserName;
	
	public SyOrgUser() {
		super();
	}

	public SyOrgUser(String id){
		super(id);
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

    @ExcelField(title="用户系统登录名", align=2, sort=10)
	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

    @ExcelField(title="用户名称", align=2, sort=20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserSort() {
		return userSort;
	}

	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}

	public String getUserHomePhone() {
		return userHomePhone;
	}

	public void setUserHomePhone(String userHomePhone) {
		this.userHomePhone = userHomePhone;
	}

    @ExcelField(title="手机号码", align=2, sort=30)
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

    @ExcelField(title="用户邮箱", align=2, sort=60)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserWorkLoc() {
		return userWorkLoc;
	}

	public void setUserWorkLoc(String userWorkLoc) {
		this.userWorkLoc = userWorkLoc;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public String getUserPostLevel() {
		return userPostLevel;
	}

	public void setUserPostLevel(String userPostLevel) {
		this.userPostLevel = userPostLevel;
	}

	public String getUserRoom() {
		return userRoom;
	}

	public void setUserRoom(String userRoom) {
		this.userRoom = userRoom;
	}

	public String getUserWorkNum() {
		return userWorkNum;
	}

	public void setUserWorkNum(String userWorkNum) {
		this.userWorkNum = userWorkNum;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserOfficePhone() {
		return userOfficePhone;
	}

	public void setUserOfficePhone(String userOfficePhone) {
		this.userOfficePhone = userOfficePhone;
	}

	public String getUserNation() {
		return userNation;
	}

	public void setUserNation(String userNation) {
		this.userNation = userNation;
	}

	public String getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(String userHeight) {
		this.userHeight = userHeight;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserHomeLand() {
		return userHomeLand;
	}

	public void setUserHomeLand(String userHomeLand) {
		this.userHomeLand = userHomeLand;
	}

	public String getUserPolitics() {
		return userPolitics;
	}

	public void setUserPolitics(String userPolitics) {
		this.userPolitics = userPolitics;
	}

	public String getUserMarriage() {
		return userMarriage;
	}

	public void setUserMarriage(String userMarriage) {
		this.userMarriage = userMarriage;
	}

	public String getUserEduLevle() {
		return userEduLevle;
	}

	public void setUserEduLevle(String userEduLevle) {
		this.userEduLevle = userEduLevle;
	}

	public String getUserEduSchool() {
		return userEduSchool;
	}

	public void setUserEduSchool(String userEduSchool) {
		this.userEduSchool = userEduSchool;
	}

	public String getUserEduMajor() {
		return userEduMajor;
	}

	public void setUserEduMajor(String userEduMajor) {
		this.userEduMajor = userEduMajor;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserTitleDate() {
		return userTitleDate;
	}

	public void setUserTitleDate(String userTitleDate) {
		this.userTitleDate = userTitleDate;
	}

	public String getUserWorkDate() {
		return userWorkDate;
	}

	public void setUserWorkDate(String userWorkDate) {
		this.userWorkDate = userWorkDate;
	}

	public String getUserCmpyDate() {
		return userCmpyDate;
	}

	public void setUserCmpyDate(String userCmpyDate) {
		this.userCmpyDate = userCmpyDate;
	}

    @ExcelField(title="人员状态", align=2, sort=110, dictType="crm_user_state")
	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCmpyCode() {
		return cmpyCode;
	}

	public void setCmpyCode(String cmpyCode) {
		this.cmpyCode = cmpyCode;
	}

    @ExcelField(title="启用标志", align=2, sort=110, dictType="crm_sflag")
	public String getsFlag() {
		return sFlag;
	}

	public void setsFlag(String sFlag) {
		this.sFlag = sFlag;
	}

	public String getsUser() {
		return sUser;
	}

	public void setsUser(String sUser) {
		this.sUser = sUser;
	}

	public String getUserLoginType() {
		return userLoginType;
	}

	public void setUserLoginType(String userLoginType) {
		this.userLoginType = userLoginType;
	}

	public String getUserExpireDate() {
		return userExpireDate;
	}

	public void setUserExpireDate(String userExpireDate) {
		this.userExpireDate = userExpireDate;
	}

	public String getUserPasswordDate() {
		return userPasswordDate;
	}

	public void setUserPasswordDate(String userPasswordDate) {
		this.userPasswordDate = userPasswordDate;
	}

	public String getsMtime() {
		return sMtime;
	}

	public void setsMtime(String sMtime) {
		this.sMtime = sMtime;
	}

	public String getUserImgSrc() {
		return userImgSrc;
	}

	public void setUserImgSrc(String userImgSrc) {
		this.userImgSrc = userImgSrc;
	}

	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public String getJiangangFlag() {
		return jiangangFlag;
	}

	public void setJiangangFlag(String jiangangFlag) {
		this.jiangangFlag = jiangangFlag;
	}

	public String getUserShortName() {
		return userShortName;
	}

	public void setUserShortName(String userShortName) {
		this.userShortName = userShortName;
	}

	public String getUserEnName() {
		return userEnName;
	}

	public void setUserEnName(String userEnName) {
		this.userEnName = userEnName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserEdulevle() {
		return userEdulevle;
	}

	public void setUserEdulevle(String userEdulevle) {
		this.userEdulevle = userEdulevle;
	}

	public String getUsereExpireDate() {
		return usereExpireDate;
	}

	public void setUsereExpireDate(String usereExpireDate) {
		this.usereExpireDate = usereExpireDate;
	}

	public List<String> getUserCodeList() {
		return userCodeList;
	}

	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public Date getTaskCompleteTime() {
		return taskCompleteTime;
	}

	public void setTaskCompleteTime(Date taskCompleteTime) {
		this.taskCompleteTime = taskCompleteTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUserMobileNew() {
		return userMobileNew;
	}

	public void setUserMobileNew(String userMobileNew) {
		this.userMobileNew = userMobileNew;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

    @ExcelField(title="所属省市", align=2, sort=40)
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

    @ExcelField(title="所属城市", align=2, sort=50)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getUserCodeView() {
		return userCodeView;
	}

	public void setUserCodeView(String userCodeView) {
		this.userCodeView = userCodeView;
	}

    @ExcelField(title="职务", align=2, sort=80, dictType="area_level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQzUserCode() {
        return qzUserCode;
    }

    public void setQzUserCode(String qzUserCode) {
        this.qzUserCode = qzUserCode;
    }

    public String getQjUserCode() {
        return qjUserCode;
    }

    public void setQjUserCode(String qjUserCode) {
        this.qjUserCode = qjUserCode;
    }

    @ExcelField(title="所属区总", align=2, sort=90)
    public String getQzUserName() {
        return qzUserName;
    }

    public void setQzUserName(String qzUserName) {
        this.qzUserName = qzUserName;
    }

    @ExcelField(title="所属区经", align=2, sort=100)
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}