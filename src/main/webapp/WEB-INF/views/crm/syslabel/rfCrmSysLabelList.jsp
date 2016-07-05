<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统配置管理</title>
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
		<li class="active"><a href="${ctx}/crm/syslabel/rfCrmSysLabel/">系统配置列表</a></li>
		<shiro:hasPermission name="syslabel:rfCrmSysLabel:edit"><li><a href="${ctx}/syslabel/rfCrmSysLabel/form">系统配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmSysLabel" action="${ctx}/syslabel/rfCrmSysLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>标签类型：</label>
				<form:input path="codeLabel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>标签key：</label>
				<form:input path="keyLabel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>标签value：</label>
				<form:input path="valueLabel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>排序字段：</label>
				<form:input path="orderBy" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>默认显示[1，是；2，不是]：</label>
				<form:input path="isShow" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>type：</label>
				<form:input path="type" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>操作人：</label>
				<form:input path="userId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>可用[1，可用；2，不可用]：</label>
				<form:input path="available" htmlEscape="false" maxlength="1" class="input-medium"/>
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
				<th>标签类型</th>
				<th>标签key</th>
				<th>标签value</th>
				<th>排序字段</th>
				<th>默认显示[1，是；2，不是]</th>
				<th>type</th>
				<th>操作人</th>
				<th>可用[1，可用；2，不可用]</th>
				<th>添加时间</th>
				<th>修改时间</th>
				<shiro:hasPermission name="syslabel:rfCrmSysLabel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmSysLabel">
			<tr>
				<td><a href="${ctx}/crm/syslabel/rfCrmSysLabel/form?id=${rfCrmSysLabel.id}">
					${rfCrmSysLabel.id}
				</a></td>
				<td>
					${rfCrmSysLabel.codeLabel}
				</td>
				<td>
					${rfCrmSysLabel.keyLabel}
				</td>
				<td>
					${rfCrmSysLabel.valueLabel}
				</td>
				<td>
					${rfCrmSysLabel.orderBy}
				</td>
				<td>
					${rfCrmSysLabel.isShow}
				</td>
				<td>
					${rfCrmSysLabel.type}
				</td>
				<td>
					${rfCrmSysLabel.userId}
				</td>
				<td>
					${rfCrmSysLabel.available}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmSysLabel.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmSysLabel.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="syslabel:rfCrmSysLabel:edit"><td>
    				<a href="${ctx}/crm/syslabel/rfCrmSysLabel/form?id=${rfCrmSysLabel.id}">修改</a>
					<a href="${ctx}/crm/syslabel/rfCrmSysLabel/delete?id=${rfCrmSysLabel.id}" onclick="return confirmx('确认要删除该系统配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>