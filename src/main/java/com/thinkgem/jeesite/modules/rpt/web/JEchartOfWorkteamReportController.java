package com.thinkgem.jeesite.modules.rpt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Bar;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.rpt.entity.ZinccoatingWorkTeamReport;


/**
 * 班组echart图表报表controller.
 * 
 * @author aiqing.chu
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/jechart/workteam")
public class JEchartOfWorkteamReportController extends BaseController {

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryEchartOptionData"/*, method = RequestMethod.POST*/)
	public ResponseEntity<JSONObject> queryEchartOptionData(ZinccoatingWorkTeamReport zinccoatingWorkTeamReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		//创建Option
	    Option option = new Option();
	    Object[] names = new String[]{"上锌量(千克)","过钢量(吨)"};
	    option.title("班组").tooltip(Trigger.axis).legend(names);
	    //横轴为值轴
	    //option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
	    option.yAxis(new ValueAxis().boundaryGap(0d, 0.01));
	    //创建类目轴
	    CategoryAxis category = new CategoryAxis();
	    //柱状数据
	    Bar bar1 = new Bar(String.valueOf(names[0]));
	    bar1.setCoordinateSystem("cartesian2d");
	    //饼图数据
	    Bar bar2 = new Bar(String.valueOf(names[1]));
	    bar2.setCoordinateSystem("cartesian2d");
	    
	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Map<String, Object> item = new HashMap<String, Object>();
	    item.put("gencode", "gencode001");
	    item.put("totalsteel", 100.01);
	    item.put("zinctotalinc", 10.01);
	    list.add(item);
	    
	    item = new HashMap<String, Object>();
	    item.put("gencode", "gencode002");
	    item.put("totalsteel", 100.02);
	    item.put("zinctotalinc", 10.02);
	    list.add(item);
	    
	    item = new HashMap<String, Object>();
	    item.put("gencode", "gencode003");
	    item.put("totalsteel", 100.03);
	    item.put("zinctotalinc", 10.03);
	    list.add(item);
	    //循环数据
	    for (Map<String, Object> objectMap : list) {
	        //设置类目
	        category.data(objectMap.get("gencode"));
	        //类目对应的柱状图
	        bar1.data(objectMap.get("totalsteel"));
	        //饼图数据
	        bar2.data(objectMap.get("zinctotalinc"));
	    }
	    //设置类目轴
	    option.xAxis(category);
	    //设置数据
	    option.series(bar1, bar2);
	    option.grid().x(180);
		
		//
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("success", true);
		jsonObj.put("option", option);
		return new ResponseEntity<JSONObject>(jsonObj, HttpStatus.OK);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
