<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>县/区管理</title>
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
		<li class="active"><a href="${ctx}/crm/location/tDistrict/">县/区列表</a></li>
		<shiro:hasPermission name="location:tDistrict:edit"><li><a href="${ctx}/crm/location/tDistrict/form">县/区添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tDistrict" action="${ctx}/crm/location/tDistrict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>code：</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>disname：</label>
				<form:input path="disname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>cityid：</label>
				<form:input path="cityid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>dissort：</label>
				<form:input path="dissort" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>code</th>
				<th>disname</th>
				<th>cityid</th>
				<th>dissort</th>
				<th>latitude</th>
				<th>longitude</th>
				<shiro:hasPermission name="location:tDistrict:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tDistrict">
			<tr>
				<td><a href="${ctx}/crm/location/tDistrict/form?id=${tDistrict.id}">
					${tDistrict.id}
				</a></td>
				<td>
					${tDistrict.code}
				</td>
				<td>
					${tDistrict.disname}
				</td>
				<td>
					${tDistrict.cityid}
				</td>
				<td>
					${tDistrict.dissort}
				</td>
				<td>
					${tDistrict.latitude}
				</td>
				<td>
					${tDistrict.longitude}
				</td>
				<shiro:hasPermission name="location:tDistrict:edit"><td>
    				<a href="${ctx}/crm/location/tDistrict/form?id=${tDistrict.id}">修改</a>
					<a href="${ctx}/crm/location/tDistrict/delete?id=${tDistrict.id}" onclick="return confirmx('确认要删除该县/区吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>