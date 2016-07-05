/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.gds.roomtype.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.DateUtil;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房型价格Entity
 * @author jiajianhong
 * @version 2016-05-24
 */
public class RoomtypePrices {

	private static final long serialVersionUID = 1L;

    private BigDecimal rackRatePrice;       // 门市价
    private Integer week;                   // 周几
    private BigDecimal weekPrice;           // 周末价
    private Integer day;                    // 日期
    private String dayStr;                  // 日期格式,yyyy-mm-dd
    private BigDecimal dayPrice;            // 特殊价

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDayStr() {

        String dayTemp = day.toString();

        this.dayStr = dayTemp.substring(0, 4) + "-" + dayTemp.substring(4, 6) + "-" + dayTemp.substring(6);

        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public BigDecimal getRackRatePrice() {
        return rackRatePrice;
    }

    public void setRackRatePrice(BigDecimal rackRatePrice) {
        this.rackRatePrice = rackRatePrice;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public BigDecimal getWeekPrice() {
        return weekPrice;
    }

    public void setWeekPrice(BigDecimal weekPrice) {
        this.weekPrice = weekPrice;
    }
}