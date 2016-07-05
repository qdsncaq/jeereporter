<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
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
		<li><a href="${ctx}/crm/task/rfCrmUserTask/">任务列表</a></li>
		<li class="active"><a href="${ctx}/crm/task/rfCrmUserTask/form?id=${rfCrmUserTask.id}">任务<shiro:hasPermission name="task:rfCrmUserTask:edit">${not empty rfCrmUserTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="task:rfCrmUserTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rfCrmUserTask" action="${ctx}/crm/task/rfCrmUserTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class='row'>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">任务名称：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
 			</div>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">优先级：</label>
					<div class="controls">
						<form:select path="priority" class="input-xlarge required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('crm_priority')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
		</div>	
		<div class='row'>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">任务说明：</label>
					<div class="controls">
						<form:textarea path="remark" rows="6" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
 			</div>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">截止时间：</label>
					<div class="controls">
						<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							value="<fmt:formatDate value="${rfCrmUserTask.endTime}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>
		</div>	
		
		<c:if test="${!taskView}">
			<div class='row'>
				<div class='span7'>
					<div class="control-group">
						<label class="control-label">省编码：</label>
						<div class="controls">
							<%-- <form:input path="provCode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
			
							<form:select path="provCode" class="input-medium required" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
								<form:option value="" label="选择省"/>
								<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
								<c:forEach items="${provinceList}" var="prov" varStatus="status">
									<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
								</c:forEach>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
	 			</div>
				<div class='span7'>
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
				</div>
			</div>	
		</c:if>
		<c:if test="${taskView}">
			<table id="contentTable" style="width:70%" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>接收人姓名</th>
					<th>接收人电话</th>
					<th>是否完成</th>
					<th>发布任务时间</th>
					<th>完成任务时间</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="syOrgUser">
				<tr>
					<td>
						${syOrgUser.userName}
					</td>
					<td>
						${syOrgUser.userMobile}
					</td>
					<td>
						<c:if test="${syOrgUser.isComplete == '2'}">未完成</c:if>
						<c:if test="${syOrgUser.isComplete == '1'}">已完成</c:if>
					</td>
					<td>
						<fmt:formatDate value="${syOrgUser.taskCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${syOrgUser.taskCompleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</c:if>
	
	<div class="form-actions">
		<c:if test="${!taskView}">
			<shiro:hasPermission name="task:rfCrmUserTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</c:if>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form:form>
</body>
</html>