<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
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
		<li class="active"><a href="${ctx}/crm/notice/rfCrmNotice/">公告列表</a></li>
		<shiro:hasPermission name="notice:rfCrmNotice:edit"><li><a href="${ctx}/crm/notice/rfCrmNotice/form">公告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmNotice" action="${ctx}/crm/notice/rfCrmNotice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>通知类型：</label>
				<form:select path="noticeType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${noticeTypeList}" itemLabel="valueLabel" itemValue="keyLabel" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>通知标题：</label>
				<form:input path="noticeTitle" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>通知内容：</label>
				<form:input path="noticeContent" htmlEscape="false" maxlength="4000" class="input-medium"/>
			</li>
			<li><label>是否必读 ：</label>
				<form:select path="noticeRead" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="1" label="必读"/>
					<form:option value="0" label="非必读"/>
				</form:select>
			</li>
			<li><label style="width: 100px;">通知开始时间：</label>
				<input name="noticeStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmNotice.noticeStartTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width: 100px;">通知结束时间：</label>
				<input name="noticeEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmNotice.noticeEndTime}" pattern="yyyy-MM-dd"/>"
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
				<th>通知类型</th>
				<th>通知标题</th>
				<th>通知内容</th>
				<th>通知是否置顶</th>
				<th>通知是否必读</th>
				<th>通知时间</th>
				<th>通知附件路径</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmNotice">
			<tr>
				<td>
					${rfCrmNotice.noticeTypeName}
				</td>
				<td>
					${rfCrmNotice.noticeTitle}
				</td>
				<td>
					${rfCrmNotice.noticeContent}
				</td>
				<td>
					<c:if test="${rfCrmNotice.noticeTop == 1}">是</c:if>
					<c:if test="${rfCrmNotice.noticeTop != 1}">否</c:if>
				</td>
				<td>
					<c:if test="${rfCrmNotice.noticeRead == 1}">是</c:if>
					<c:if test="${rfCrmNotice.noticeRead != 1}">否</c:if>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmNotice.noticeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rfCrmNotice.noticeAttachment}
				</td>
				<td>
					${rfCrmNotice.remarks}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmNotice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/crm/notice/rfCrmNotice/form?id=${rfCrmNotice.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>