package com.thinkgem.psb.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.psb.psbbill.entity.SettlementPsbhotelbill;
import com.thinkgem.psb.psbrules.entity.SettlementPsbrules;

/**
 * 导出Excel文件（导出PSB结算收据单）
 * 
 * @author futao
 * @version 2016-04-20
 */
public class ExportPsbReceipt {

	private static Logger log = LoggerFactory.getLogger(ExportPsbReceipt.class);

	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook wb;

	/**
	 * 工作表对象
	 */
	private Sheet sheet;

	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;

	/**
	 * 当前行号
	 */
	private int rownum = 0;

	/**
	 * 要导出的数据
	 */
	List<Object[]> listData = Lists.newArrayList();

	/**
	 * 合计
	 */
	BigDecimal total=new BigDecimal(0);

	/**
	 * 1 构造函数
	 * 
	 * @param cls
	 *            实体对象，通过annotation.ExportField获取标题
	 */
	public ExportPsbReceipt(String[] columnName2, List<SettlementPsbhotelbill> list,
			List<SettlementPsbrules> prlist) {
		String[] columnName1 = { "序号", "省份", "城市", "区域", "酒店编号", "PMS酒店名", "房间数", "HMS状态", "区域经理", "装机工程师", "结算起始日期",
				"结算截止日期", "上线时间", "下线时间", "HMS有效天数" };
 	    int[] column1 = { 6, 12, 12, 12, 12, 33, 9, 10, 9, 11, 21, 21, 21, 21, 15};
 	    int[] column =new int[columnName1.length+columnName2.length+1];
		int clenth=column.length;
		for(int i=0;i<column1.length;i++){
			column[i]=column1[i];
		}
		//动态列的宽度
		for(int i=0;i<columnName2.length;i++){
			column[columnName1.length+i]=35;
		}
		//最后一列的宽度
		clenth=column.length;
		column[clenth-1]=16;
		initTitle("结算签收单", column, columnName1, columnName2, "结算金额");
		try {
			collateData(list, prlist, columnName1.length + columnName2.length + 1);
			initData();
			initTern(total, columnName1.length, columnName2.length);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 整理要导出的数据
	private void collateData(List<SettlementPsbhotelbill> list, List<SettlementPsbrules> prlist, int columnSize) {
		Object[] obj = null;
		SettlementPsbhotelbill spb=null;
		for (int i = 0; i <list.size(); i++) {
			spb=list.get(i);
			obj = new Object[columnSize];
			obj[0]=i+1; //序号
			obj[1]=spb.getProname();
			obj[2]=spb.getCityname();
			obj[3]=spb.getDisname();
			obj[4]=spb.getHotelid();
			obj[5]=spb.getHotelname();
			obj[6]=spb.getRoomnums();
			obj[7]=spb.getStrOnlinestate();
			obj[8]=spb.getAreamanager();
			obj[9]=spb.getOperator();
			obj[10]=DateUtils.getDatetime(spb.getBegintime()); 
			obj[11]=DateUtils.getDatetime(spb.getRealendtime());
			obj[12]=DateUtils.getDatetime(spb.getOnlinetime());
			obj[13]=DateUtils.getDatetime(spb.getOfflinetime());
			obj[14]=spb.getOnlinedays();
			//最后一列
			obj[columnSize-1]=spb.getRecoveramount();
			//金盾分类列的值
			if(prlist!=null){
				obj=getJinDunData(obj, spb.getAmount(),spb.getRoomnums()*12, prlist);
			}else{
				obj[15]=spb.getAmount();
			}
			//算出小计的值
			 total=total.add(spb.getRecoveramount());
			listData.add(obj);
		}
	}

	//金盾分类列的值
	private  Object[] getJinDunData(Object[] obj ,BigDecimal amount,Integer roomnums,List<SettlementPsbrules> prlist){
		for(int i = 0; i <prlist.size(); i++){
	    	SettlementPsbrules spr=prlist.get(i);
	    	if(roomnums>=10){
	    		obj[15+i]=spr.getFee20();
	    	}else{
	    		obj[15+i]=getfee10(spr.getFee10(), amount);
	    	}
	    }
		return obj;
	}

	//有计算的类型
	private  String getfee10(String fee10, BigDecimal amount){
		if(fee10==null || fee10.indexOf("roomnums")==0){
			return  fee10;
		}else{
			 ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
		        try {
		        	fee10=fee10.replaceAll("roomnums", "12");
		            BigDecimal d = new BigDecimal(se.eval(fee10).toString()).setScale(2, BigDecimal.ROUND_HALF_UP) ;
		            return d.toString();
		        } catch (ScriptException e) {
		            e.printStackTrace();
		        }
		}
		return fee10;
	}
	
	
	/**
	 * 初始化 表头
	 * 
	 * @param title
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头列表
	 */
	private void initTitle(String title, int[] column, String[] columnName1, String[] columnName2, String columnName3) {
		int hs = columnName1.length + columnName2.length;
		this.wb = new SXSSFWorkbook(500);
		this.sheet = wb.createSheet("Export");
		this.styles = createStyles(wb);
		this.sheet.setDefaultColumnStyle(100, styles.get("data"));
		// 设置宽度
		for (int i = 0; i < column.length; i++) {
			sheet.setColumnWidth(i, column[i] * 200);
		}

		// Create title 第一行标题
		if (StringUtils.isNotBlank(title)) {
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(
					new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(), hs));
		}

		// 第二行开始
		// 开始部分的列名（前15个）
		Row titleRow2 = sheet.createRow(rownum++);
		titleRow2.setHeightInPoints(20);
		int cnlength = columnName1.length;
		for (int i = 0; i < cnlength; i++) {
			Cell cell230 = titleRow2.createCell(i);
			cell230.setCellStyle(styles.get("data2"));
			cell230.setCellValue(columnName1[i]);
			// 设置列为数字类型
			// if(i==cnlength-1){
			// cell230.set
			// }
			sheet.addMergedRegion(new CellRangeAddress(titleRow2.getRowNum(), titleRow2.getRowNum() + 1, i, i));
		}
		// 基础数据的几列
		titleRow2.setHeightInPoints(20);
		Cell cell21 = titleRow2.createCell(columnName1.length);
		cell21.setCellStyle(styles.get("data2"));
		cell21.setCellValue("费用基数");
		sheet.addMergedRegion(new CellRangeAddress(titleRow2.getRowNum(), titleRow2.getRowNum(), columnName1.length,
				columnName1.length + columnName2.length - 1));

		// 最后一格第二行和第三行合并
		Cell cell34 = titleRow2.createCell(cnlength + columnName2.length);
		cell34.setCellStyle(styles.get("data2"));
		;
		cell34.setCellValue(columnName3);
		sheet.addMergedRegion(new CellRangeAddress(titleRow2.getRowNum(), titleRow2.getRowNum() + 1,
				cnlength + columnName2.length, cnlength + columnName2.length));
		sheet.autoSizeColumn(cnlength + columnName2.length);

		// 第二行结束，第三行开始 费用基础下的详细列
		Row titleRow3 = sheet.createRow(rownum++);
		for (int i = 0; i < columnName2.length; i++) {
			Cell cell33 = titleRow3.createCell(cnlength + i);
			cell33.setCellStyle(styles.get("data2"));
			cell33.setCellValue(columnName2[i]);
			sheet.autoSizeColumn(cnlength + i);
		}

		log.debug("Initialize title success.");
	}

	/**
	 * 把要导出的数据放如 excel表格
	 * 
	 * @param title
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头列表
	 */
	public void initData() throws IOException {
//	int m=0;
     for (Object[] obj : listData) {
    	 Row row = sheet.createRow(rownum++);
    	 row.setHeightInPoints(20);
    	 for(int n = 0; n < obj.length; n++){
//    		 m++;
    		 Cell cell = row.createCell(n);
    		 cell.setCellStyle(styles.get("data1"));
    		 if(obj[n]==null){
    			 cell.setCellValue("");
    		 }else {
    			 cell.setCellValue(obj[n].toString());
//    			 System.out.println("第"+(m+1)+"行第"+(n+1)+"列的值是======="+obj[n]); 
			 }
    	 }
	 }
     
	}

	/**
	 * 初始化尾部
	 * 
	 * @param counts
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头列表
	 */
	public void initTern(BigDecimal total, int cnlength1, int cnlength2) throws IOException {
		int m = cnlength1 + cnlength2;
		// 合计那一行
		Row tRow0 = sheet.createRow(rownum++);
		tRow0.setHeightInPoints(20);
		Cell c0 = tRow0.createCell(0);
		c0.setCellStyle(styles.get("data2"));
		c0.setCellValue("小计");

		// 中间空白处
		sheet.addMergedRegion(new CellRangeAddress(tRow0.getRowNum(), tRow0.getRowNum(), 1, m - 1));
		// 总计值
		Cell c1 = tRow0.createCell(m);
		c1.setCellStyle(styles.get("data1"));
		c1.setCellValue(total.doubleValue());

		// 声明那一行
		Row tRow1 = sheet.createRow(rownum++);
		tRow1.setHeightInPoints(20);
		Cell c2 = tRow1.createCell(0);
		c2.setCellStyle(styles.get("data1"));
		c2.setCellValue("申明：此结算签收单为我单位对本月满足条件的酒店所进行的结算，情况属实；我单位承诺对该结算表内容和金额无异议。");
		sheet.addMergedRegion(new CellRangeAddress(tRow1.getRowNum(), tRow1.getRowNum(), 0, m));

		// 备注那一行
		Row tRow2 = sheet.createRow(rownum++);
		tRow2.setHeightInPoints(20);
		Cell c3 = tRow2.createCell(0);
		c3.setCellStyle(styles.get("data1"));
		c3.setCellValue("备注：每个结算周期中新增上线酒店及下线酒店才做结算；该结算周期截止日期仍处于上线状态的不用在该结算周期中结算；本结算表双方签字盖章有效，一式两份。");
		sheet.addMergedRegion(new CellRangeAddress(tRow2.getRowNum(), tRow2.getRowNum(), 0, m));
		// 结算申请单位那一行
		Row tRow3 = sheet.createRow(rownum++);
		tRow3.setHeightInPoints(25);
		Cell c4 = tRow3.createCell(0);
		c4.setCellStyle(styles.get("data1"));
		c4.setCellValue("结算申请单位：北京航天金盾科技有限公司 __________分公司");
		int s = cnlength1 / 2 - 1;
		sheet.addMergedRegion(new CellRangeAddress(tRow3.getRowNum(), tRow3.getRowNum(), 0, s-1));

		Cell c5 = tRow3.createCell(s);
		c5.setCellStyle(styles.get("data1"));
		c5.setCellValue("结算审核单位：上海乐住信息技术有限公司 __________分公司");
		sheet.addMergedRegion(new CellRangeAddress(tRow3.getRowNum(), tRow3.getRowNum(), s , cnlength1 - 2));

		Cell c6 = tRow3.createCell(cnlength1 - 1);
		c6.setCellStyle(styles.get("data1"));
		c6.setCellValue("结算审核单位：上海乐住信息技术有限公司 __________分公司");
		sheet.addMergedRegion(
				new CellRangeAddress(tRow3.getRowNum(), tRow3.getRowNum(), cnlength1 - 1, cnlength1 + cnlength2));

		// 代表签字： 那一行
		Row tRow4 = sheet.createRow(rownum++);
		tRow4.setHeightInPoints(40);
		Cell c7 = tRow4.createCell(0);
		c7.setCellStyle(styles.get("data1"));
		c7.setCellValue("代表签字：                                 日期：");
		sheet.addMergedRegion(new CellRangeAddress(tRow4.getRowNum(), tRow4.getRowNum(), 0, s-1));

		Cell c8 = tRow4.createCell(s );
		c8.setCellStyle(styles.get("data1"));
		c8.setCellValue("代表签字：                                 日期：");
		sheet.addMergedRegion(new CellRangeAddress(tRow4.getRowNum(), tRow4.getRowNum(), s , cnlength1 - 2));

		Cell c9 = tRow4.createCell(cnlength1 - 1);
		c9.setCellStyle(styles.get("data1"));
		c9.setCellValue("盖章：");
		sheet.addMergedRegion(
				new CellRangeAddress(tRow4.getRowNum(), tRow4.getRowNum(), cnlength1 - 1, cnlength1 + cnlength2));

	}

	/**
	 * 输出到客户端
	 * 
	 * @param fileName
	 *            输出文件名
	 */
	public ExportPsbReceipt write(HttpServletResponse response, String fileName) throws IOException {
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}

	/**
	 * 清理临时文件
	 */
	public ExportPsbReceipt dispose() {
		wb.dispose();
		return this;
	}

	/**
	 * 构造函数
	 * 
	 * @param filePath
	 *            文件模板路径
	 */
	public ExportPsbReceipt(String filePath) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new ClassPathResource(filePath).getInputStream());
		this.wb = new SXSSFWorkbook(xssfWorkbook);
	}

	/**
	 * 创建表格样式
	 * 
	 * @param wb
	 *            工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);

		XSSFDataFormat format = (XSSFDataFormat) wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));
		styles.put("data", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);

		style.setDataFormat(format.getFormat("@"));
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setDataFormat(format.getFormat("@"));
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		style.setDataFormat(format.getFormat("@"));
		styles.put("data3", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		// style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);

		return styles;
	}

	/**
	 * 输出数据流
	 * 
	 * @param os
	 *            输出数据流
	 */
	public ExportPsbReceipt write(OutputStream os) throws IOException {
		wb.write(os);
		return this;
	}

	/**
	 * 输出到文件
	 * 
	 * @param name
	 *            输出文件名
	 */
	public ExportPsbReceipt writeFile(String name) throws FileNotFoundException, IOException {
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}

}