<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li class="active"><a href="${ctx}/crm/task/sub/rfCrmUserTaskReceive/">用户列表</a></li>
		<shiro:hasPermission name="task:sub:rfCrmUserTaskReceive:edit"><li><a href="${ctx}/crm/task/sub/rfCrmUserTaskReceive/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmUserTaskReceive" action="${ctx}/crm/task/sub/rfCrmUserTaskReceive/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>接收人用户id：</label>
				<form:input path="receiveUserId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>任务Id：</label>
				<form:input path="taskId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>是否完成,1-是,2-否：</label>
				<form:select path="isComplete" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('crm_complete')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>添加时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserTaskReceive.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>修改时间：</label>
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserTaskReceive.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主键</th>
				<th>接收人用户id</th>
				<th>任务Id</th>
				<th>是否完成,1-是,2-否</th>
				<th>备注</th>
				<th>添加时间</th>
				<th>修改时间</th>
				<shiro:hasPermission name="task:sub:rfCrmUserTaskReceive:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmUserTaskReceive">
			<tr>
				<td><a href="${ctx}/crm/task/sub/rfCrmUserTaskReceive/form?id=${rfCrmUserTaskReceive.id}">
					${rfCrmUserTaskReceive.id}
				</a></td>
				<td>
					${rfCrmUserTaskReceive.receiveUserId}
				</td>
				<td>
					${rfCrmUserTaskReceive.taskId}
				</td>
				<td>
					${fns:getDictLabel(rfCrmUserTaskReceive.isComplete, 'crm_complete', '')}
				</td>
				<td>
					${rfCrmUserTaskReceive.remark}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserTaskReceive.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserTaskReceive.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="task:sub:rfCrmUserTaskReceive:edit"><td>
    				<a href="${ctx}/crm/task/sub/rfCrmUserTaskReceive/form?id=${rfCrmUserTaskReceive.id}">修改</a>
					<a href="${ctx}/crm/task/sub/rfCrmUserTaskReceive/delete?id=${rfCrmUserTaskReceive.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>