/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.crm.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.crm.user.entity.RfCrmUserRelation;

/**
 * 用户关系维护DAO接口
 * @author jiajianhong
 * @version 2016-03-30
 */
@MyBatisDao
public interface RfCrmUserRelationDao extends CrudDao<RfCrmUserRelation> {

    public  RfCrmUserRelation findByQzUserCode(String userCode);

    public RfCrmUserRelation findByQjUserCode(String userCode);

    public RfCrmUserRelation findBySalerUserCode(String userCode);

    public  RfCrmUserRelation findByUserCode(String userCode);
}