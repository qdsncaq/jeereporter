<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>message管理</title>
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
		<li class="active"><a href="${ctx}/message/lMessageLog/">短信详情</a></li>
		<shiro:hasPermission name="message:lMessageLog:edit"><li><a href="${ctx}/message/lMessageLog/form">message添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="LMessageLog" action="${ctx}/message/lMessageLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发送时间：</label>
				<input name="time" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${LMessageLog.time}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>短信类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('MessageTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否成功：</label>
				<form:select path="success" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('SuccessEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>发送时间</th>
				<th>手机号</th>
				<th>消息内容</th>
				<th>短信类型</th>
				<th>是否成功</th>
				<shiro:hasPermission name="message:lMessageLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lMessageLog">
			<tr>
				<td>
					${lMessageLog.id}
				</td>
				<td>
					<fmt:formatDate value="${lMessageLog.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lMessageLog.phone}
				</td>
				<td>
					${lMessageLog.message}
				</td>
				<td>
					${fns:getDictLabel(lMessageLog.type, 'MessageTypeEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(lMessageLog.success, 'SuccessEnums', '')}
				</td>
				<shiro:hasPermission name="message:lMessageLog:edit"><td>
    				<a href="${ctx}/message/lMessageLog/form?id=${lMessageLog.id}">修改</a>
					<a href="${ctx}/message/lMessageLog/delete?id=${lMessageLog.id}" onclick="return confirmx('确认要删除该message吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>