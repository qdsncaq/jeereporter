<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店管理</title>
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
		<li class="active"><a href="${ctx}/hotel/rfCrmHotelPublic/">酒店列表</a></li>
		<shiro:hasPermission name="hotel:rfCrmHotelPublic:edit"><li><a href="${ctx}/hotel/rfCrmHotelPublic/form">酒店添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmHotelPublic" action="${ctx}/hotel/rfCrmHotelPublic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>私海负责人ID：</label>
				<form:input path="hotelUserId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否私海[1，是；2，不是]：</label>
				<form:input path="isPrivate" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>周期开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmHotelPublic.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>周期结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmHotelPublic.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>可用[1，可用；2，不可用]：</label>
				<form:input path="available" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>添加时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmHotelPublic.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>修改时间：</label>
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmHotelPublic.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>私海退还公海时间：</label>
				<input name="returnTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmHotelPublic.returnTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>是否确认[1，已确认；2，未确认]：</label>
				<form:input path="isConfirm" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>确认人ID：</label>
				<form:input path="confirmUserId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店ID</th>
				<th>是否私海[1，是；2，不是]</th>
				<shiro:hasPermission name="hotel:rfCrmHotelPublic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmHotelPublic">
			<tr>
				<td><a href="${ctx}/hotel/rfCrmHotelPublic/form?id=${rfCrmHotelPublic.id}">
					${rfCrmHotelPublic.hotelId}
				</a></td>
				<td>
					${rfCrmHotelPublic.isPrivate}
				</td>
				<shiro:hasPermission name="hotel:rfCrmHotelPublic:edit"><td>
    				<a href="${ctx}/hotel/rfCrmHotelPublic/form?id=${rfCrmHotelPublic.id}">修改</a>
					<a href="${ctx}/hotel/rfCrmHotelPublic/delete?id=${rfCrmHotelPublic.id}" onclick="return confirmx('确认要删除该酒店吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>