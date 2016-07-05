package com.thinkgem.jeesite.modules.oms.entity;

import org.springframework.web.multipart.MultipartFile;

public class OmsLogisticImp {

	private MultipartFile xlsFile = null;

	public MultipartFile getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(MultipartFile xlsFile) {
		this.xlsFile = xlsFile;
	}
	
}
