<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>billinfo管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/billinfo/settlementBillInfo/">账单收款列表</a></li>
		<li class="active"><a href="${ctx}/billinfo/settlementBillInfo/form?id=${settlementBillInfo.id}">账单收款<shiro:hasPermission name="billinfo:settlementBillInfo:edit">${not empty settlementBillInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="billinfo:settlementBillInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="settlementBillInfo" action="${ctx}/billinfo/settlementBillInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">账单ID：</label>
			<div class="controls">
				<form:input path="billid" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账单结算金额：</label>
			<div class="controls">
				<form:input path="billscsum" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款金额：</label>
			<div class="controls">
				<form:input path="settlesum" htmlEscape="false" class="input-xlarge required "/><span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款日期：</label>
			<div class="controls">
				<input name="settletime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${settlementBillInfo.settletime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/><span class="help-inline"><font color="red">*</font> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账单收款状态：</label>
			<div class="controls">
				<%-- <form:select path="status" class="input-xlarge required ">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SettlementStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select><span class="help-inline"><font color="red">*</font> --%>
				<select id="status" name="status" style="width:200px;margin-right:15px;" class="input-xlarge required " >
	                   <option value="">--请选择--</option>
	                   <option value="10">部分收款</option>
	                   <option value="11">已收款</option>
	                   <option value="12">超额收款</option><span class="help-inline"><font color="red">*</font>
              	</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${settlementBillInfo.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${settlementBillInfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="billinfo:settlementBillInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>