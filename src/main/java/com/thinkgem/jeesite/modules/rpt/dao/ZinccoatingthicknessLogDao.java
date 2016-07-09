/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.rpt.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingWeeklyReport;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingWorkTeamReport;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingthicknessLog;

/**
 * 锌层测厚日志DAO接口
 * @author aiqing.chu
 * @version 2016-07-07
 */
@MyBatisDao
public interface ZinccoatingthicknessLogDao extends CrudDao<ZinccoatingthicknessLog> {
	
	/**
	 * 实时数据报表查询.
	 * 
	 * @param zinccoatingthicknessLog
	 * @return
	 */
	List<ZinccoatingthicknessLog> queryList(ZinccoatingthicknessLog zinccoatingthicknessLog);
	
	/**
	 * 查询锌层测厚班组报表.
	 * 
	 * @param zinccoatingWorkTeamReport
	 * @return
	 */
	List<ZinccoatingWorkTeamReport> queryZinccoatingworkteamReport(ZinccoatingWorkTeamReport zinccoatingWorkTeamReport);
	
	/**
	 * 查询锌层测厚周报表.
	 * 
	 * @param zinccoatingWeeklyReport
	 * @return
	 */
	List<ZinccoatingWeeklyReport> queryZinccoatingweeklyReport(ZinccoatingWeeklyReport zinccoatingWeeklyReport);
	
}