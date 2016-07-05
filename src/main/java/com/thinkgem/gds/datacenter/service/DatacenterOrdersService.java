/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.datacenter.service;

import java.math.BigDecimal;
import java.util.List;

import com.fangbaba.order.common.enums.OrderTypeEnum;
import com.fangbaba.order.service.OrderService;
import com.thinkgem.jeesite.common.annotation.DataSourceName;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.gds.datacenter.dao.DatacenterOrdersDao;
import com.thinkgem.gds.datacenter.entity.DatacenterOrders;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 分销订单查询Service
 * @author LYN
 * @version 2016-03-15
 */
@Service
@DataSourceName("gds")
@Transactional(readOnly = true)
public class DatacenterOrdersService extends CrudService<DatacenterOrdersDao, DatacenterOrders> {

    @Autowired
    private SystemService systemService;

    @Autowired
    private OrderService ordersService;

//	@Autowired
//	OrderService orderService;
	
	public DatacenterOrders get(String id) {
		return super.get(id);
	}
	
	public List<DatacenterOrders> findList(DatacenterOrders datacenterOrders) {
		return super.findList(datacenterOrders);
	}

	public Page<DatacenterOrders> findPage(Page<DatacenterOrders> page, DatacenterOrders datacenterOrders) {

        // 根据当前用户不同角色查询不同数据
        List<Role> roleList = systemService.findAllRole();
        if (!Collections3.isEmpty(roleList)) {
            for (Role role : roleList) {
                if (role.getName().equalsIgnoreCase("GDS运营")) {
                    if (StringUtils.isBlank(datacenterOrders.getOrdertype()) ||
                            datacenterOrders.getOrdertype().equalsIgnoreCase(String.valueOf(OrderTypeEnum.travel.getId()))) {
                        datacenterOrders.setOrdertype(OrderTypeEnum.travel.getId() + "");
                    } else {
                        datacenterOrders.setOrdertype("");
                    }
                }

                if (role.getName().equalsIgnoreCase("客服角色")) {
                    if (StringUtils.isBlank(datacenterOrders.getOrdertype()) ||
                            datacenterOrders.getOrdertype().equalsIgnoreCase(String.valueOf(OrderTypeEnum.ota.getId()))) {
                        datacenterOrders.setOrdertype(OrderTypeEnum.ota.getId() + "");
                    } else {
                        datacenterOrders.setOrdertype("");
                    }
                }
            }
        } else {
            logger.info("当前用户没有任何角色");
        }

		return super.findPage(page, datacenterOrders);
	}

    public Page<DatacenterOrders> findPageAll(Page<DatacenterOrders> page, DatacenterOrders datacenterOrders) {

        return super.findPage(page, datacenterOrders);
    }
	
	@Transactional(readOnly = false)
	public void save(DatacenterOrders datacenterOrders) {
		super.save(datacenterOrders);
	}
	
	@Transactional(readOnly = false)
	public void delete(DatacenterOrders datacenterOrders) {
		super.delete(datacenterOrders);
	}
	
	@Transactional(readOnly = false)
	public void update(DatacenterOrders datacenterOrders) {
		super.update(datacenterOrders);
	}


    @Transactional(readOnly = false)
    public boolean setDiscout(String orderId, String totalPrice, String oldDiscount, String discount) {

        try {
            // 获取当前用户信息
            User user = UserUtils.getUser();

            logger.info("用户" + user.getId() + "开始设置订单" + orderId + "的优惠金额" + discount);

            // 订单优惠金额若是第二次设置,必须把原总金额计算出来
            BigDecimal oldTotalPrice = null;
            if (StringUtils.isNotBlank(oldDiscount)) {
                oldTotalPrice = new BigDecimal(totalPrice).add(new BigDecimal(oldDiscount));
            } else {
                oldTotalPrice = new BigDecimal(totalPrice);
            }

            // 调用接口计算优惠金额之后的订单应付金额
            String optuser = user.getLoginName() + "("+user.getName()+")";
            BigDecimal newTotalPrice = ordersService.modifyOrderPrice(Long.parseLong(orderId), oldTotalPrice,
                    new BigDecimal(discount), optuser);

            if (null == newTotalPrice) {
                logger.error("订单[orderId=" + orderId + "]设置优惠金额调用接口返回为null");
                return false;
            }

            // 封装数据,更新表数据
            DatacenterOrders datacenterOrders = new DatacenterOrders();
            datacenterOrders.setId(orderId);
            datacenterOrders.setTotalprice(newTotalPrice.toString());
            datacenterOrders.setDiscount(new BigDecimal(discount));

            super.save(datacenterOrders);

        } catch (Exception e) {
            logger.error("设置订单[orderId=" + orderId + "]优惠金额出现异常", e);
            return false;
        }

        return true;
    }
}