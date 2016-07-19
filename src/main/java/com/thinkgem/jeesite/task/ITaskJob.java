package com.thinkgem.jeesite.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务接口类.
 * 
 * @author aiqing.chu
 *
 */
public interface ITaskJob {

	Logger logger = LoggerFactory.getLogger(ITaskJob.class);
	
	void execute();
}
