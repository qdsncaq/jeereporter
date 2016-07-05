<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
    <title></title>
</head>
<body>
<div style="margin:10px;">
    1、选择酒店
	<select  onchange="changeHotel(this)">
	    
	    <option value="">请选择</option>
	   <c:forEach var="item" items="${hotelList }">
	    <option value="${item.id }_${item.hotelname }">${item.hotelname }</option>
	    </c:forEach>
	</select>
	
    <b style="color:red"> 选择你需要生成二维码的酒店</b>	
</div>	
<div style="margin:10px;">
2、录入基本信息  <b style="color:red">(酒店可以选择从上一步查询，或者自己录入)</b>	
<div>
<form action="" id="form1">
   <table  style="padding:20px;">
	   <tr>
	   <td>酒店id：
		<input name="hotelid" id="hotelid" type="text" />
		</td>
	   <td>酒店名称：
		<input name="hotelname" id="hotelname" type="text" />
		</td>
		</tr>
		<tr>
		<td>
		数量：<input name="num" type="text" id="num"/>
		</td>
		<td>
		送洗类型：<select name="qrtype" style="width:150px;">
		    <option value="1">正常洗</option>
		    <option value="2">送洗</option>
		</select>
		</td>
		<td>
	       <input value="submit" type="button"  onclick="create()" />
		</td>
	   </tr>
	</table>

</form>
</div>	
</div>	

<div style="margin:10px;">
3、二维码
<c:forEach var="item" items="${list }">
<img src="/qrcode/paint?hotelid=${item.hotelid }&hotelname=${item.hotelname }&qrtype=${item.qrtype }&id=${item.id }">
</c:forEach>
</div>
</body>
</html>
<script>
function create(){
	if(document.getElementById("hotelid").value==null||document.getElementById("hotelid").value==""){
		alert("请输入酒店id");
		return false;
	}
	if(document.getElementById("hotelname").value==null||document.getElementById("hotelname").value==""){
		alert("请输入酒店名称");
		return false;
	}
	if(document.getElementById("num").value==null||document.getElementById("num").value==""){
		alert("请输入数量");
		return false;
	}
	document.getElementById("form1").submit();
	
}
function changeHotel(obj){
	var array = obj.value.split('_')
	
	document.getElementById("hotelid").value=array[0];
	document.getElementById("hotelname").value=array[1];
}
</script>

<%
  String urlPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>print</title>
  <style type="text/css">
    .print-row{
      width: 1250px;
      margin: 0 auto;
      overflow: hidden;
    }
    .print-column{
      text-align: center;
      font-weight: bold;
    }
    .print-cell {
      float: left;
    }
    .print-cell img{
      width: 150px;
      height: 150px;
      margin: 10px;
      border: 1px solid #cccccc;
      padding: 3px;
      border-radius: 5px;
    }
    .print-bottom{
      text-align: right;
      font-weight: bold;
    }
  </style>
</head>
<body>
<div id="print_container"></div>
<script type="text/javascript" src="<%=urlPath %>/main/plug-in/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=urlPath %>/main/plug-in/underscore/underscore-min.js"></script>
<script type="text/javascript">
  var printList = window.opener.printList;
  var thisHotelName = "测试酒店";
  //存在数据
  if (printList && printList.length > 0) {
    //多个
    if (printList.length > 1) {
      var rows = new Array();
      for (var i = 0; i < 5; i++) {
        rows.push("<div class='print-row'>");
        //支持IE8-
        var row = new Array();
        _.map(printList, function(rowData) {
          if (i === 0) {
            row.push("<div class='print-cell'>");
            row.push("<div class='print-column'>", rowData.tag, "</div>");
            row.push("<img src='/qrcode/paint?content=");
            row.push(encodeURIComponent(rowData.content), "'/>");
            row.push("</div>");
          } else {
            row.push("<div class='print-cell'>");
            row.push("<img src='<%=urlPath %>/i2dimcodes/userI2DimCode?content=");
            row.push(encodeURIComponent(rowData.content), "'/>");
            row.push("</div>");
          }
        });
        rows.push(row.join(""));
        rows.push("</div>");
      }
      jQuery("#print_container").append(rows.join(""));
      jQuery("#print_container").append("<div class='print-row'><div class='print-bottom'>酒店：" + thisHotelName + "</div></div>");
      //一个
    } else {
      //支持IE8-
      var rows = new Array();
      var rowData = printList[0];
      for (var i = 0; i < 5; i++) {
        rows.push("<div class='print-row'>");
        var row = new Array();
        for (var j = 0; j < 7; j++) {
          if (i === 0) {
            row.push("<div class='print-cell'>");
            row.push("<div class='print-column'>", rowData.tag, "</div>");
            row.push("<img src='<%=urlPath %>/i2dimcodes/userI2DimCode?content=");
            row.push(encodeURIComponent(rowData.content), "'/>");
            row.push("</div>");
          } else {
            row.push("<div class='print-cell'>");
            row.push("<img src='<%=urlPath %>/i2dimcodes/userI2DimCode?content=");
            row.push(encodeURIComponent(rowData.content), "'/>");
            row.push("</div>");
          }
        }
        rows.push(row.join(""));
        rows.push("</div>");
      }
      jQuery("#print_container").append(rows.join(""));
      jQuery("#print_container").append("<div class='print-row'><div class='print-bottom'>酒店：" + thisHotelName + "</div></div>");
    }
  }
  //打印
  window.onload = function() {
    window.print();
  }
</script>
</body>
</html>
<!-- &hotelid=123&hotelname=123&type=123 -->