<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品物流数据管理</title>
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
		<li class="active"><a href="${ctx}/oms/omsLogisticsGoods/">商品物流数据列表</a></li>
		<shiro:hasPermission name="oms:omsLogisticsGoods:edit"><li><a href="${ctx}/oms/omsLogisticsGoods/form">商品物流数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="omsLogisticsGoods" action="${ctx}/oms/omsLogisticsGoods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主键</th>
				<th>物流编码</th>
				<th>物流名称</th>
				<th>仓库编码</th>
				<th>仓库名称</th>
				<th>区域编码</th>
				<th>区域名称</th>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>是否有效</th>
				<shiro:hasPermission name="oms:omsLogisticsGoods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="omsLogisticsGoods">
			<tr>
				<td><a href="${ctx}/oms/omsLogisticsGoods/form?id=${omsLogisticsGoods.id}">
					${omsLogisticsGoods.id}
				</a></td>
				<td>
					${omsLogisticsGoods.logisticsCode}
				</td>
				<td>
					${omsLogisticsGoods.logisticsName}
				</td>
				<td>
					${omsLogisticsGoods.warehouseCode}
				</td>
				<td>
					${omsLogisticsGoods.warehouseName}
				</td>
				<td>
					${omsLogisticsGoods.distinctCode}
				</td>
				<td>
					${omsLogisticsGoods.distinctName}
				</td>
				<td>
					${omsLogisticsGoods.commodityCode}
				</td>
				<td>
					${omsLogisticsGoods.commodityName}
				</td>
				<td>
					${omsLogisticsGoods.sFlag}
				</td>
				<shiro:hasPermission name="oms:omsLogisticsGoods:edit"><td>
    				<a href="${ctx}/oms/omsLogisticsGoods/form?id=${omsLogisticsGoods.id}">修改</a>
					<a href="${ctx}/oms/omsLogisticsGoods/delete?id=${omsLogisticsGoods.id}" onclick="return confirmx('确认要删除该商品物流数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>