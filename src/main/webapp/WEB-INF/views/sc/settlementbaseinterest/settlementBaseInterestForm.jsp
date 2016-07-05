<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SettlementBaseInterest管理</title>
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
		<li><a href="${ctx}/settlementbaseinterest/settlementBaseInterest/">酒店老板利率列表</a></li>
		<li class="active"><a href="${ctx}/settlementbaseinterest/settlementBaseInterest/form?id=${settlementBaseInterest.id}">酒店老板利率<shiro:hasPermission name="settlementbaseinterest:settlementBaseInterest:edit">${not empty settlementBaseInterest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="settlementbaseinterest:settlementBaseInterest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="settlementBaseInterest" action="${ctx}/settlementbaseinterest/settlementBaseInterest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">酒店ID：</label>
			<div class="controls">
				<form:input path="hotelid" htmlEscape="false"   required="required"  readonly="${not empty settlementBaseInterest.id?'true':'false'}"  maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		
		<div style="display:${not empty settlementBaseInterest.id?'display':'none'};" >
		<div class="control-group">
			<label class="control-label">酒店名称：</label>
			<div class="controls">
				<form:input path="hotelname" htmlEscape="false" readonly="${not empty settlementBaseInterest.id?'true':'false'}"  maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PMSID：</label>
			<div class="controls">
				<form:input path="hotelpms" htmlEscape="false" readonly="${not empty settlementBaseInterest.id?'true':'false'}"   maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">账户角色：</label>
			<div class="controls"  style="display:${not empty settlementBaseInterest.id?'none':'display'};"  >
				<form:select path="accountrole" class="input-xlarge "  >
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			<div class="controls"  style="display:${not empty settlementBaseInterest.id?'display':'none'};"  >
				<form:input path="strAccountrole" htmlEscape="false" readonly="true"   maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利率：</label>
			<div class="controls">
				<form:input path="interestrate"  required="required"  htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计息类型：</label>
			<div class="controls">
		     	<form:select path="interestratetype" class="input-xlarge ">
		     	  <form:option value="1" label="日息"/>
<%-- 					<form:options items="${fns:getDictList('InterestrateTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开关 ：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('SettlementBaseInterestStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否复利：</label>
			<div class="controls">
				<form:select path="compoundinterest" class="input-xlarge ">
				    <form:option value="1" label="复利计算"/>
<%-- 					<form:options items="${fns:getDictList('CompoundinterestEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始计息日期：</label>
			<div class="controls">
				<input name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${settlementBaseInterest.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</div>
		</div>
	 
		<div class="form-actions">
			<shiro:hasPermission name="settlementbaseinterest:settlementBaseInterest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>