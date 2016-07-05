<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>市管理</title>
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
		<li class="active"><a href="${ctx}/crm/location/tCity/">市列表</a></li>
		<shiro:hasPermission name="location:tCity:edit"><li><a href="${ctx}/crm/location/tCity/form">市添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCity" action="${ctx}/crm/location/tCity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>cityid：</label>
				<form:input path="cityid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>code：</label>
				<form:input path="code" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>cityname：</label>
				<form:input path="cityname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>proid：</label>
				<form:input path="proid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>citysort：</label>
				<form:input path="citysort" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>城市简称：</label>
				<form:input path="simplename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>是否热门城市 T-是，F-否：</label>
				<form:input path="ishotcity" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>城市半径长度（KM）：</label>
				<form:input path="range" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>是否查询城市 F-是，F-否：</label>
				<form:input path="isselect" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>查询城市名称：</label>
				<form:input path="querycityname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>城市等级：</label>
				<form:input path="level" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>cityid</th>
				<th>code</th>
				<th>cityname</th>
				<th>proid</th>
				<th>citysort</th>
				<th>latitude</th>
				<th>longitude</th>
				<th>城市简称</th>
				<th>是否热门城市 T-是，F-否</th>
				<th>城市半径长度（KM）</th>
				<th>是否查询城市 F-是，F-否</th>
				<th>查询城市名称</th>
				<th>城市等级</th>
				<shiro:hasPermission name="location:tCity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCity">
			<tr>
				<td><a href="${ctx}/crm/location/tCity/form?id=${tCity.id}">
					${tCity.cityid}
				</a></td>
				<td>
					${tCity.code}
				</td>
				<td>
					${tCity.cityname}
				</td>
				<td>
					${tCity.proid}
				</td>
				<td>
					${tCity.citysort}
				</td>
				<td>
					${tCity.latitude}
				</td>
				<td>
					${tCity.longitude}
				</td>
				<td>
					${tCity.simplename}
				</td>
				<td>
					${tCity.ishotcity}
				</td>
				<td>
					${tCity.range}
				</td>
				<td>
					${tCity.isselect}
				</td>
				<td>
					${tCity.querycityname}
				</td>
				<td>
					${tCity.level}
				</td>
				<shiro:hasPermission name="location:tCity:edit"><td>
    				<a href="${ctx}/crm/location/tCity/form?id=${tCity.id}">修改</a>
					<a href="${ctx}/crm/location/tCity/delete?id=${tCity.id}" onclick="return confirmx('确认要删除该市吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>