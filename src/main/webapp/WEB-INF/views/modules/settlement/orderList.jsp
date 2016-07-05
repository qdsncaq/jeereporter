<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function chaxun() {
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sc/order/list">订单查询</a></li>
		<shiro:hasPermission name="sc:order:form"><li><a href="${ctx}/sc/order/form">订单修改</a></li></shiro:hasPermission>
	</ul> --%>
	<div>
		<form id="searchForm" action="${ctx}/sc/order/list" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
				<label class="control-label no-padding-right" >订单号:</label><input type="number" name="orderId" value="${params.orderId}" >
		        <label class="control-label no-padding-right" >结算方名称:</label><input type="text" name="settlementTargetName" value="${params.settlementTargetName}" >
		        <%-- <label class="control-label no-padding-right">结算类型: </label>
		        <select name = "settlementCategory" >
					<option value="0" >全部</option>
		 			<option value="1" <c:if test="${params.settlementCategory == '1' }">selected = "selected"</c:if>>收入</option>
					<option value="-1" <c:if test="${params.settlementCategory == '-1' }">selected = "selected"</c:if>>支出</option>
		        </select> --%>
		              
		       <label class="control-label no-padding-right">结账日期:</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:130px;"
				value="<fmt:formatDate value="${params.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			   <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:130px;"
				value="<fmt:formatDate value="${params.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> 
		             
		       <button type="button" class="btn btn-primary" onclick="chaxun()" >查询</button>
		       <!-- <button type="button" class="btn btn-primary" ng-click="export1(false)">导出全部</button>
		       <button type="button" class="btn btn-primary" ng-click="export1(true)">导出本页</button> -->
		       
		       
			</ul>
		</form>
		       <div id="content" hidden="" class="divclass">
			       <form id="inputForm" method="post" class="form-horizontal">
			       		<input type="hidden" name="id" id="id" value="" maxlength="50" disabled="disabled" />
						<div class="control-group" >
							<label class="control-label">订&nbsp;&nbsp;单&nbsp;号:</label> 
							<!-- <span id="orderId"></span> -->
							<input type="text" name="orderId" id="orderId" value=""  maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">费用类型: </label>
							<input type="text" name="feeType" id="feeType" value="" maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">结算类型: </label>
							<input type="text" name="settlementCategory" id="settlementCategory" value="" maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">结账日期: </label>
							<input type="text" name="settlementTime" id="settlementTime" value="" maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">原始金额: </label>
							<input type="text" name="originalSum" id="originalSum" value="" maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额: </label>
							<input type="text" name="settlementSum" id="settlementSum" value="" maxlength="50" disabled="disabled" />
						</div>
						<div class="control-group">
							<label class="control-label">修改金额: </label>
							<input type="text" id="changeSettlementSum" name="csettlementSum" onkeyup="value=value.replace(/[^\-?\d.]/g,'')" /> (限制文本框只能输入正数，负数，小数)
						</div>
						<div>
							<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
						</div>
				   </form>
		       </div>
		
		<script type="text/javascript">
			$(function(){
				$("#btnSubmit").click(function(){ 
					var id = $("#id").val();
					var orderId = $("#orderId").val();
					var feeType = $("#feeType").val();
					var settlementCategory = $("#settlementCategory").val();
					var settlementTime = $("#settlementTime").val();
					var originalSum = $("#originalSum").val();
					var settlementSum = $("#settlementSum").val();
					var changeSettlementSum = $("#changeSettlementSum").val();
					var data = { id:id, 
								orderId:orderId, 
								feeType:feeType, 
								settlementCategory:settlementCategory, 
								settlementTime:settlementTime,
								originalSum:originalSum,
								settlementSum:settlementSum,
								changeSettlementSum:changeSettlementSum};
					// alert(settlementCategory);return false;
					$.post('${ctx}/sc/order/save', data, function(data){
						layer.msg("修改完成！");
						setTimeout("window.location.reload('orderList.jsp')",1000);
					}) 
				 });
			}) 
			function showUpdate(id,orderid,originalSum,settlementSum,strFeeType,strSettlementCategory,strSettlementType,strSettlementState,strCreateTime,strSettlementTime){
				$("#id").val(id);
				/* $("#orderId").text(orderid); */
				$("#orderId").val(orderid);
				$("#feeType").val(strFeeType);
				$("#settlementCategory").val(strSettlementCategory);
				$("#settlementTime").val(strSettlementTime);
				$("#originalSum").val(originalSum);
				$("#settlementSum").val(settlementSum);
				layer.open({
				    type: 1,
				    //skin: 'layui-layer-rim', //加上边框
				    area: ['760px', '590px'], //宽高
				    content: $("#content")
				});
			    
			}
		</script>
		
		<form id="editForm" action="${ctx }/sc/order/form">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		    <tr>
		        <th>订单号</th>
		        <!-- <th>原始金额</th> -->
		        <th>金额</th>
		        <th>费用类型</th>
		        <!-- <th>结算类型</th> -->
		        <th>销售周期</th>
		        <th>统计状态</th>
		        <th>结算方名称</th>
		        <th>创建时间</th>
		        <th>结账日期</th>
		        <th>操作</th>
		    </tr>
		    <c:forEach items="${message.result }" var="item">
			    <tr >
			    	<%-- <td id="id">${item.id}</td> --%>
			    	<td id="orderId">${item.orderId}</td>
			        <%-- <td id="originalSum">${item.originalSum}</td> --%>
			        <td id="settlementSum">${item.settlementSum}</td>
			        <td id="strFeeType">${item.strFeeType}</td>
			        <%-- <td id="strSettlementCategory">${item.strSettlementCategory}</td> --%>
			        <td id="strSettlementType">周结</td>
			        <td id="strSettlementState">${item.strSettlementState}</td>
			        <td id="settlementTargetName">${item.settlementTargetName}</td>
			        <td id="strCreateTime">${item.strCreateTime}</td>
			        <td id="strSettlementTime">${item.strSettlementTime}</td>
			        <td >
			            <button id="editButton" type="button" onclick="showUpdate(${item.id},${item.orderId},'${item.originalSum}','${item.settlementSum}','${item.strFeeType}','${item.strSettlementCategory}','${item.strSettlementType}','${item.strSettlementState}','${item.strCreateTime}','${item.strSettlementTime}')" class="btn btn-primary" >修改金额</button>
			        </td>
			    </tr>
		    </c:forEach>
	    </table>
	    </form>
	    
	    <div class="pagination">${page}</div>
	 </div>   
</body>
</html>