<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbrules管理</title>
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
		
		function setPsbName(psbname){
			$("#psb_name").val(psbname);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/psbrules/settlementPsbrules/list">PSB科目列表</a></li>
		<li class="active"><a href="${ctx}/psbrules/settlementPsbrules/form?id=${settlementPsbrules.id}">PSB科目<shiro:hasPermission name="psbrules:settlementPsbrules:edit">${not empty settlementPsbrules.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="psbrules:settlementPsbrules:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="settlementPsbrules" action="${ctx}/psbrules/settlementPsbrules/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="psbname" id="psb_name"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">PSB厂家编号：</label>
			<div class="controls">
				<form:select path="psbid" class="input-xlarge required" onchange="setPsbName($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="PSB厂家"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.psbname }" valueid="${psbinfo.psbname }" />
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目名称：</label>
			<div class="controls">
				<form:input path="rulename" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目费用(&lt;10间房)：</label>
			<div class="controls">
				<form:input path="fee10" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目费用(&gt;=10间房)：</label>
			<div class="controls">
				<form:input path="fee20" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${settlementPsbrules.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${settlementPsbrules.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否有效标识：</label>
			<div class="controls">
				<form:input path="delflag" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="psbrules:settlementPsbrules:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>