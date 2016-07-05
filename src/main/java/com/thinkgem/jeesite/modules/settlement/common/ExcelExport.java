package com.thinkgem.jeesite.modules.settlement.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class ExcelExport {
	
	/**导出之前调用此方法*/
	public static  HttpServletResponse exprotBefore(HttpServletRequest request,
		HttpServletResponse response,String excelName) {
		HttpSession session = request.getSession();
		session.setAttribute("state", null);
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		try {
			excelName = java.net.URLEncoder.encode(excelName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		excelName=excelName+getDate()+"导出数据.xls";
		response.setHeader("content-disposition", "attachment;filename="+excelName);
		response.setHeader("x-filename", excelName);
		return response;
	}
	
	/**导出的方法*/
	public static HSSFWorkbook export(String fileName, String[] titile,
			List<String[]> listData, int[] columnWidth, Boolean[] isdigit)throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(fileName);
			int columnNumber=titile.length;
			//第一行标题
			Row row0 = sheet.createRow(0);
			//设置剧中的样式
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			for (int i = 0; i < columnNumber; i++) {
				Cell c0 = row0.createCell(i);
				//设置列宽度
				sheet.setColumnWidth(i, columnWidth[i] * 100);  
				c0.setCellStyle(style);
				c0.setCellValue(titile[i]);
			}
			//第二行和后面的
			int listSize=listData.size();
			for (int m = 0; m < listSize; m++) {
				String[] s=listData.get(m);
				Row row = sheet.createRow(m+1);
				for (int n = 0; n < columnNumber; n++) {
					Cell cell = row.createCell(n);
					if(s[n]!=null ){
						if(isdigit!=null &&
								isdigit[n]!=null &&
								isdigit[n]){
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							try {
								cell.setCellValue(Double.valueOf(s[n]));
							} catch (Exception e) {
								 e.getMessage();
							}
							
						}else {
							cell.setCellValue(s[n]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
 
    private static String getDate() {
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("[yyyy-MM-dd]");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	 
}
