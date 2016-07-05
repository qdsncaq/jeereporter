<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<title></title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sc/order/list">订单查询</a></li>
		<li class="active"><a href="form?id=${area.id}&parent.id=${area.parent.id}">订单<shiro:hasPermission name="sys:area:edit">${not empty area.id?'查询':'修改'}</shiro:hasPermission><shiro:lacksPermission name="sys:area:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<%-- <form:form id="inputForm" action="${ctx }/sc/order/save" method="post" class="form-horizontal"> --%>
	<%-- <form:form id="searchForm" action="${ctx }/sc/order/save" method="post" class="breadcrumb form-search"> --%>
	<form:form id="inputForm"  action="${ctx }/sc/order/save" method="post" class="form-horizontal">
		<input id="orderId" name="orderId" type="hidden" value="${params.orderId }"/>
 	
 		<div class="control-group">
			<label class="control-label">订单号:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.orderId }" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用类型:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.feeType }" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算类型:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.settlementCategory }" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结账日期:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.settlementTime }" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原始金额:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.originalSum }" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额:</label>
			<div class="controls">
				<input type="text" name="orderId" value="${params.settlementSum }" maxlength="50" />
			</div>
		</div>
		
		<%-- <label class="col-sm-3 control-label no-padding-right"> 订单号: </label>${params.orderId } <br />
		<label class="col-sm-3 control-label no-padding-right"> 费用类型: </label>${params.feeType } <br />
		<label class="col-sm-3 control-label no-padding-right"> 结算类型: </label>${params.settlementCategory } <br />
		<label class="col-sm-3 control-label no-padding-right"> 结账日期: </label>${params.settlementTime } <br />
		<label class="col-sm-3 control-label no-padding-right"> 原始金额: </label>${params.originalSum } <br />
		<label class="col-sm-3 control-label no-padding-right"> 金额: </label>${params.settlementSum } <br /> --%>
		<div class="control-group">
			<label class="control-label">修改金额：</label>
			<input type="number" name="csettlementSum" />
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sc:order:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>