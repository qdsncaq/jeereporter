<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>schedulercenter管理</title>
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
		<li class="active"><a href="${ctx}/schedulercenter/settlementSchedulerCenter/">任务调度列表</a></li>
		<shiro:hasPermission name="schedulercenter:settlementSchedulerCenter:edit"><li><a href="${ctx}/schedulercenter/settlementSchedulerCenter/form">任务调度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementSchedulerCenter" action="${ctx}/schedulercenter/settlementSchedulerCenter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>序号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>任务名称：</label>
				<form:input path="taskName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>执行机器IP：</label>
				<form:input path="currentMachine" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>执行时间：</label>
				<input name="beginTickTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementSchedulerCenter.beginTickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endTickTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementSchedulerCenter.endTickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>阈值：</label>
				<form:input path="beginThreshold" htmlEscape="false" maxlength="20" class="input-medium"/>
				- <form:input path="endThreshold" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>任务名称</th>
				<th>执行机器IP</th>
				<th>上次执行时间</th>
				<th>阈值</th>
				<shiro:hasPermission name="schedulercenter:settlementSchedulerCenter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementSchedulerCenter">
			<tr>
				<td><a href="${ctx}/schedulercenter/settlementSchedulerCenter/form?id=${settlementSchedulerCenter.id}">
					${settlementSchedulerCenter.id}
				</a></td>
				<td>
					${settlementSchedulerCenter.taskName}
				</td>
				<td>
					${settlementSchedulerCenter.currentMachine}
				</td>
				<td>
					<fmt:formatDate value="${settlementSchedulerCenter.tickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementSchedulerCenter.threshold}
				</td>
				<shiro:hasPermission name="schedulercenter:settlementSchedulerCenter:edit"><td>
    				<a href="${ctx}/schedulercenter/settlementSchedulerCenter/form?id=${settlementSchedulerCenter.id}">修改</a>
					<a href="${ctx}/schedulercenter/settlementSchedulerCenter/delete?id=${settlementSchedulerCenter.id}" onclick="return confirmx('确认要删除该schedulercenter吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>