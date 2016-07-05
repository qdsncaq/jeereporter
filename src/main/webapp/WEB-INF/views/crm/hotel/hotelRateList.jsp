<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/crm/hotel/onOffLine/list">上下线列表</a></li>
	<li class="active"><a>PMS详情</a></li>
</ul>
<form:form id="searchForm" modelAttribute="hotelRate" action="${ctx}/crm/hotel/onOffLine/hotelRateList" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="HOTEL_ID" name="HOTEL_ID" type="hidden" value="${hotelRate.HOTEL_ID}"/>
	<ul class="ul-form">
		<li class="btns">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</li>
		<li><label>日期：</label>
			<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${hotelRate.startDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			-
			<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${hotelRate.endDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
		</li>
		<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>酒店PMS</th>
		<th>日期</th>
		<th>可售房间数</th>
		<th>出租房间数</th>
		<th>入住房间数</th>
		<th>出租率</th>
		<th>在线时长(分钟)</th>
		<th>办理入住次数</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="rate">
		<tr>
			<td>
				${hotelRate.HOTEL_ID}
			</td>
			<td>
				${rate.TIME}
			</td>
			<td>
				${rate.ROOMNUM}
			</td>
			<td>
				${rate.RATEROOMNUM}
			</td>
			<td>
				${rate.CHECKROOMNUM}
			</td>
			<td>
				${rate.rate}
			</td>
			<td>
				${rate.onlinecount}
			</td>
			<td>
				${rate.handleChecknum}
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>