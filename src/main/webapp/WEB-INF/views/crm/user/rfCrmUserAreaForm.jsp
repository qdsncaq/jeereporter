<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>所属区域管理</title>
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
		
		function serSubTree(treeId){
			var url = "${ctx}/crm/location/tCity/serSubTree";
			//重置select2控件的值
			var subIdSelect = $("#cityCode");
			$("#cityCode option:not(:first)").remove();
			subIdSelect.select2("val","");
			//区县
			var thirdIdSelect = $("#disCode");
			$("#disCode option:not(:first)").remove();
			thirdIdSelect.select2("val","");

			$.ajax({
				url: url,
				dataType: 'json',
				data:{
					proid:treeId
				},
				cache: false,
				async: true,
				success: function(data, textStatus) {
					var secTreeList = data;
					for(var node in secTreeList){
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/crm/user/rfCrmUserArea/">所属区域列表</a></li>
		<li class="active"><a href="${ctx}/crm/user/rfCrmUserArea/form?id=${rfCrmUserArea.id}">所属区域<shiro:hasPermission name="user:rfCrmUserArea:edit">${not empty rfCrmUserArea.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:rfCrmUserArea:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rfCrmUserArea" action="${ctx}/crm/user/rfCrmUserArea/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<c:if test="${not empty syOrgUser}">	
			<div class="control-group">
				<label class="control-label">用户名称：</label>
				<div class="controls" style="display:none">
					<form:input path="userCode"  htmlEscape="false" maxlength="40" class="input-xlarge required" value="${syOrgUser.userCode }"/>
				</div>
				<div class="controls">
					<form:input path="userCode" disabled="true" class="input-xlarge required" value="${syOrgUser.userName }"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${empty syOrgUser}">	
			<div class="control-group">
				<label class="control-label">用户名称：</label>
				<div class="controls">
					<form:select path="userCode" class="input-medium">
						<form:option value="" label="选择"/>
						<c:forEach items="${syOrgUserList}" var="user" varStatus="status">
							<form:option value="${user.userCode}" label="${user.userName}-${user.userMobile}"/>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">省编码：</label>
			<div class="controls">
				<%-- <form:input path="provCode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>

				<form:select path="provCode" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市编码：</label>
			<div class="controls">
				<%-- <form:input path="cityCode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
				<form:select path="cityCode" class="input-medium">
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
					</c:forEach>
				</form:select>
				
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">区编码：</label>
			<div class="controls">
				<form:input path="disCode" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否可用：</label>
			<div class="controls">
				<form:select path="available" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('crm_available')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group"  style="display:none">
			<label class="control-label">添加时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${rfCrmUserArea.createTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group"  style="display:none">
			<label class="control-label">修改时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${rfCrmUserArea.updateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:rfCrmUserArea:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>