<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改收货地址</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	
	<form:form id="inputForm" modelAttribute="omsOrder" action="${ctx}/oms/omsOrder/updateReceiver" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${omsOrder.orderId }">
		<sys:message content="${message}"/>	
		 <div class="control-group">
            <label class="control-label">标题：</label>
            <div class="controls">
            ${omsOrder.title}
            </div>
           
        </div>
        <div class="control-group">
			<label class="control-label">有赞订单id：</label>
			<div class="controls">
			${omsOrder.yzOrderid}
			</div>
		</div>	
		 
		<div class="control-group">
			<label class="control-label">收货人姓名：</label>
			<div class="controls">
				<form:input path="receiverName" htmlEscape="true" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人电话：</label>
			<div class="controls">
				<form:input path="receiverMobile" htmlEscape="true" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人邮编：</label>
			<div class="controls">
				<form:input path="receiverZip" htmlEscape="true" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货省份：</label>
			<div class="controls">
				<form:input path="receiverState" htmlEscape="true" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货城市：</label>
			<div class="controls">
				<form:input path="receiverCity" htmlEscape="true" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货区县：</label>
			<div class="controls">
				<form:input path="receiverDistrict" htmlEscape="true" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人地址：</label>
			<div class="controls">
				<form:input path="receiverAddress" htmlEscape="true" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<!--  
		<div class="control-group">
			<label class="control-label">预发货时间：</label>
			<div class="controls">
			<input name="invoiceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.invoiceDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</div>
		</div>
		-->
		
		<div class="form-actions">
			<shiro:hasPermission name="oms:omsOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>