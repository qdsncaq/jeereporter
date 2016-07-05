<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购商品提成比例管理</title>
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
		<li class="active"><a href="${ctx}/base/settlementBaseGoodsrate/">采购商品提成比例列表</a></li>
		<shiro:hasPermission name="base:settlementBaseGoodsrate:edit"><li><a href="${ctx}/base/settlementBaseGoodsrate/form">采购商品提成比例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementBaseGoodsrate" action="${ctx}/base/settlementBaseGoodsrate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>品类名称：</label>
				<form:input path="classname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>商品编码：</label>
				<form:input path="gcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="gname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>提成比例：</label>
				<form:input path="beginRatepercent" htmlEscape="false" class="input-medium"/>
				-<form:input path="endRatepercent" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主键id</th>
				<th>品类名称</th>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>提成比例</th>
				<shiro:hasPermission name="base:settlementBaseGoodsrate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementBaseGoodsrate">
			<tr>
				<td><a href="${ctx}/base/settlementBaseGoodsrate/form?id=${settlementBaseGoodsrate.id}">
					${settlementBaseGoodsrate.id}
				</a></td>
				<td>
					${settlementBaseGoodsrate.classname}
				</td>
				<td>
					${settlementBaseGoodsrate.gcode}
				</td>
				<td>
					${settlementBaseGoodsrate.gname}
				</td>
				<td>
					${settlementBaseGoodsrate.ratepercent}
				</td>
				<shiro:hasPermission name="base:settlementBaseGoodsrate:edit"><td>
    				<a href="${ctx}/base/settlementBaseGoodsrate/form?id=${settlementBaseGoodsrate.id}">修改</a>
					<a href="${ctx}/base/settlementBaseGoodsrate/delete?id=${settlementBaseGoodsrate.id}" onclick="return confirmx('确认要删除该采购商品提成比例吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>