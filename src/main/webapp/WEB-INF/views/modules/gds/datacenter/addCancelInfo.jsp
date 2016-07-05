<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>取消订单</title>
	<meta name="decorator" content="blank"/>
	<%@include file="../layouts/importJs.jsp" %>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>	
    <style type="text/css">
        #jbox-content {
            overflow-y: hidden;
        }
        label#remark_label, textarea#desc{
            display: inline-block;
            width: 100%;
        }
        textarea#desc{
            padding: 0;
        }
    </style>
        
 <script type="text/javascript">
	$(document).ready(function() {
	    $("textarea#desc").keyup(function(){ 	    	
	    	var area=$(this); 
	    	var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值 
	    	if(max>0){ 
	        	if(area.val().length>=max){ //textarea的文本长度大于maxlength 
	        		//area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
	        		layer.tips('备注不能超过1000', '#desc',  {  tips: 1 });
	        	} 
	    	} 
	    	});
	});
	function checkForm(){
		var txt = $("#desc").val().trim();
		if(txt.length == 0){
			layer.tips('请填写取消原因', '#desc',  {  tips: 1 });
			return false;
		}
		return true;
	}
    </script>
</head>
<body>
    <form:form id="inputForm" modelAttribute="datacenterOrders"  class="breadcrumb form-search">
    <ul class="ul-form">
        <li style="height: auto; float: inherit; overflow: hidden;">
            <table class="table">
                <tr>
                    <td style="width: 10%; text-align: right;">
                        <label id="remark_label">备注：</label>
                    </td>
                    <td style="width: 90%; text-align: right;">
                        <textarea id="desc" rows="5"  maxlength="1000" ></textarea>                        
                    </td>
                </tr>
            </table>
        </li>
    </ul>
    </form:form>
</body>
</html>
