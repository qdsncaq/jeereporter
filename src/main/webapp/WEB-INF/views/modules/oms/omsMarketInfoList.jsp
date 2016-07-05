<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商场管理管理</title>
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
		<li class="active"><a href="${ctx}/oms/omsMarketInfo/">商场管理列表</a></li>
		<shiro:hasPermission name="oms:omsMarketInfo:edit"><li><a href="${ctx}/oms/omsMarketInfo/form">商场管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="omsMarketInfo" action="${ctx}/oms/omsMarketInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商铺名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商铺名称</th>
				<th>备注</th>
				<shiro:hasPermission name="oms:omsMarketInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="omsMarketInfo">
			<tr>
				<td><a href="${ctx}/oms/omsMarketInfo/form?id=${omsMarketInfo.id}">
					${omsMarketInfo.name}
				</a></td>
				<td>
					${omsMarketInfo.remarks}
				</td>
				<shiro:hasPermission name="oms:omsMarketInfo:edit"><td>
    				<a href="${ctx}/oms/omsMarketInfo/form?id=${omsMarketInfo.id}">修改</a>
					<a href="${ctx}/oms/omsMarketInfo/delete?id=${omsMarketInfo.id}" onclick="return confirmx('确认要删除该商场管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>