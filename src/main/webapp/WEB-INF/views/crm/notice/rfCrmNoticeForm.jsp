<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
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
<body >
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/crm/notice/rfCrmNotice/">公告列表</a></li>
		<li class="active"><a href="${ctx}/crm/notice/rfCrmNotice/form?id=${rfCrmNotice.id}">公告<shiro:hasPermission name="notice:rfCrmNotice:edit">${not empty rfCrmNotice.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="notice:rfCrmNotice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rfCrmNotice" action="${ctx}/crm/notice/rfCrmNotice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="noticeStatus" value='10'/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">通知类型：</label>
			<div class="controls">
				<c:if test="${empty rfCrmNotice.id}">
					<form:select path="noticeType" class="input-medium required">
						<form:option value="" label="--请选择--"/>
						<form:options items="${noticeTypeList}" itemLabel="valueLabel" itemValue="keyLabel" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${not empty rfCrmNotice.id}">
					<form:input path="noticeTypeName" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知标题：</label>
			<div class="controls">
				<form:input path="noticeTitle" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知内容：</label>
			<div class="controls">
				<form:textarea path="noticeContent" htmlEscape="false" maxlength="4000" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知是否置顶：</label>
			<div class="controls">
				<form:select path="noticeTop" class="input-medium required">
					<form:option value="1" label="置顶"/>
					<form:option value="0" label="非置顶"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知是否必读：</label>
			<div class="controls">
				<form:select path="noticeRead" class="input-medium required">
					<form:option value="1" label="必读"/>
					<form:option value="0" label="非必读"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">通知状态：10-已发送 20-暂存：</label>
			<div class="controls">
				<form:input path="noticeStatus" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">通知时间：</label>
			<div class="controls">
				<input name="noticeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${rfCrmNotice.noticeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知附件路径：</label>
			<div class="controls">
				<form:input path="noticeAttachment" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${empty rfCrmNotice.id}">
				<shiro:hasPermission name="notice:rfCrmNotice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>