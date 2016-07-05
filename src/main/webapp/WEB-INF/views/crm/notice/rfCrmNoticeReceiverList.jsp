<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
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
		<li class="active"><a href="${ctx}/notice/rfCrmNoticeReceiver/">通知列表</a></li>
		<shiro:hasPermission name="notice:rfCrmNoticeReceiver:edit"><li><a href="${ctx}/notice/rfCrmNoticeReceiver/form">通知添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmNoticeReceiver" action="${ctx}/notice/rfCrmNoticeReceiver/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>通知ID：</label>
				<form:input path="noticeId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>通知接收人ID：</label>
				<form:input path="receiveUserId" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>消息状态:1-未读,2-已读：</label>
				<form:input path="readStatus" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmNoticeReceiver.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新者：</label>
				<form:input path="updateBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmNoticeReceiver.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>删除标记：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>通知ID</th>
				<th>通知接收人ID</th>
				<th>消息状态:1-未读,2-已读</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="notice:rfCrmNoticeReceiver:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmNoticeReceiver">
			<tr>
				<td><a href="${ctx}/notice/rfCrmNoticeReceiver/form?id=${rfCrmNoticeReceiver.id}">
					${rfCrmNoticeReceiver.id}
				</a></td>
				<td>
					${rfCrmNoticeReceiver.noticeId}
				</td>
				<td>
					${rfCrmNoticeReceiver.receiveUserId}
				</td>
				<td>
					${rfCrmNoticeReceiver.readStatus}
				</td>
				<td>
					${rfCrmNoticeReceiver.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmNoticeReceiver.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rfCrmNoticeReceiver.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmNoticeReceiver.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rfCrmNoticeReceiver.remarks}
				</td>
				<td>
					${fns:getDictLabel(rfCrmNoticeReceiver.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="notice:rfCrmNoticeReceiver:edit"><td>
    				<a href="${ctx}/notice/rfCrmNoticeReceiver/form?id=${rfCrmNoticeReceiver.id}">修改</a>
					<a href="${ctx}/notice/rfCrmNoticeReceiver/delete?id=${rfCrmNoticeReceiver.id}" onclick="return confirmx('确认要删除该通知吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>