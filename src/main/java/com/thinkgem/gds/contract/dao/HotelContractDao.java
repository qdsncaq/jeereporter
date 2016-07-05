/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.contract.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.gds.contract.entity.HotelContract;

/**
 * 合同DAO接口
 * @author zhaochuanbin
 * @version 2016-03-15
 */
@MyBatisDao
public interface HotelContractDao extends CrudDao<HotelContract> {
	
}