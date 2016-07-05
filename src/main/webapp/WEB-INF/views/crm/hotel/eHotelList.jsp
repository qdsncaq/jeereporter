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
		<li class="active"><a href="${ctx}/crm/hotel/eHotel/">任务列表</a></li>
<%-- 		<shiro:hasPermission name="hotel:eHotel:edit"><li><a href="${ctx}/crm/hotel/eHotel/form">任务添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="EHotel" action="${ctx}/crm/hotel/eHotel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="bossPhone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店id</th>
				<th>酒店名称</th>
				<th>酒店老板</th>
				<th>老板手机号</th>
				<shiro:hasPermission name="hotel:eHotel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="eHotel">
			<tr>
				<td><a href="${ctx}/crm/hotel/eHotel/form?id=${eHotel.id}">
					${eHotel.id}
				</a></td>
				<td>
					${eHotel.hotelname}
				</td>
				<td>
					${eHotel.bossName}
				</td>
				<td>
					${eHotel.bossPhone}
				</td>
				<shiro:hasPermission name="hotel:eHotel:edit"><td>
    				<a href="${ctx}/crm/hotel/eHotel/form?id=${eHotel.id}">修改手机号</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>