<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
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
		<li class="active"><a href="${ctx}/crm/task/rfCrmUserTask/">任务列表</a></li>
		<shiro:hasPermission name="task:rfCrmUserTask:edit"><li><a href="${ctx}/crm/task/rfCrmUserTask/form">任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmUserTask" action="${ctx}/crm/task/rfCrmUserTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>发布人姓名：</label>
				<form:input path="senderUserName" htmlEscape="false" maxlength="100" class="input-medium"/>
			<%-- 	<form:select path="senderUserName" class="input-medium">
					<form:option value="" label="选择"/>
					<c:forEach items="${syOrgUserList}" var="user" varStatus="status">
						<form:option value="${user.userCode}" label="${user.userName}-${user.userMobile}"/>
					</c:forEach>
				</form:select> --%>
			</li>
			<li><label>优先级</label>
				<form:select path="priority" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('crm_priority')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li><label>发布时间起：</label>
				<input name="startCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserTask.startCreateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>发布时间止：</label>
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserTask.endCreateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
<!-- 				<th>发布人id</th> -->
				<th>发布人姓名</th>
				<th>任务名称</th>
				<th>优先级</th>
				<th>任务发布时间</th>
				<th>任务完成截止时间</th>
<%-- 				<shiro:hasPermission name="task:rfCrmUserTask:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmUserTask">
			<tr>
				<td><a href="${ctx}/crm/task/rfCrmUserTask/form?id=${rfCrmUserTask.id}">
					${rfCrmUserTask.id}
				</a></td>
				<%-- <td>
					${rfCrmUserTask.senderUserId}
				</td> --%>
				<td>
					${rfCrmUserTask.senderUserName}
				</td>
				<td>
					${rfCrmUserTask.name}
				</td>
				<td>
					${fns:getDictLabel(rfCrmUserTask.priority, 'crm_priority', '')}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserTask.endTime}" pattern="yyyy-MM-dd"/>
				</td>
				<%-- <shiro:hasPermission name="task:rfCrmUserTask:edit">
				<td>
    				<a href="${ctx}/crm/task/rfCrmUserTask/form?id=${rfCrmUserTask.id}">修改</a>
					<a href="${ctx}/crm/task/rfCrmUserTask/delete?id=${rfCrmUserTask.id}" onclick="return confirmx('确认要删除该任务吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>