<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>省管理</title>
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
		<li class="active"><a href="${ctx}/crm/location/tProvince/">省列表</a></li>
		<shiro:hasPermission name="location:tProvince:edit"><li><a href="${ctx}/crm/location/tProvince/form">省添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tProvince" action="${ctx}/crm/location/tProvince/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>proid：</label>
				<form:input path="proid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>code：</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>proname：</label>
				<form:input path="proname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>prosort：</label>
				<form:input path="prosort" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>proremark：</label>
				<form:input path="proremark" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>proid</th>
				<th>code</th>
				<th>proname</th>
				<th>prosort</th>
				<th>proremark</th>
				<th>latitude</th>
				<th>longitude</th>
				<shiro:hasPermission name="location:tProvince:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tProvince">
			<tr>
				<td><a href="${ctx}/crm/location/tProvince/form?id=${tProvince.id}">
					${tProvince.proid}
				</a></td>
				<td>
					${tProvince.code}
				</td>
				<td>
					${tProvince.proname}
				</td>
				<td>
					${tProvince.prosort}
				</td>
				<td>
					${tProvince.proremark}
				</td>
				<td>
					${tProvince.latitude}
				</td>
				<td>
					${tProvince.longitude}
				</td>
				<shiro:hasPermission name="location:tProvince:edit"><td>
    				<a href="${ctx}/crm/location/tProvince/form?id=${tProvince.id}">修改</a>
					<a href="${ctx}/crm/location/tProvince/delete?id=${tProvince.id}" onclick="return confirmx('确认要删除该省吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>