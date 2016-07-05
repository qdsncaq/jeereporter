<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
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
	<li><a href="${ctx}/crm/hotel/onOffLine/list">上下线列表</a></li>
	<li class="active"><a>上下线明细</a></li>
</ul>
<form:form id="searchForm" modelAttribute="onOffLine" action="${ctx}/crm/hotel/onOffLine/details" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<form:hidden path="hotelId"/>
	<ul class="ul-form">

		<li class="btns">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>酒店ID</th>
		<th>酒店名称</th><!-- 
		<th>酒店地址</th> -->
		<th>pms</th><!-- 
		<th>省</th>
		<th>市</th>
		<th>区/县</th> -->
		<th>操作类型</th>
		<th>操作说明</th>
		<%--<th>psb类型</th>--%>
		<%--<th>操作人名称</th>--%>
		<%--<th>当前是否在线</th>--%>
		<th>操作时间</th>
		<%--<th>IP地址</th>--%>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="onOffLine">
		<tr>
			<td>
				<%--<a href="${ctx}/hotel/onOffLine/form?id=${onOffLine.id}">--%>
					<%--${onOffLine.hotelId}--%>
				<%--</a>--%>
				${onOffLine.hotelId}
			</td>
			<td>
				${onOffLine.hotelName}
			</td><%-- 
			<td>
				${onOffLine.hotelAddr}
			</td> --%>
			<td>
				${onOffLine.pms}
			</td><%-- 
			<td>
				${onOffLine.provName}
			</td>
			<td>
				${onOffLine.cityName}
			</td>
			<td>
				${onOffLine.disName}
			</td> --%>
			<td>
				${onOffLine.detailTypeName}
			</td>
			<td>
				${onOffLine.detailRemarks}
			</td>
			<%--<td>--%>
				<%--${onOffLine.psbType}--%>
			<%--</td>--%>
			<%--<td>--%>
				<%--${onOffLine.operateName}--%>
			<%--</td>--%>
			<%-- <td>
					${onOffLine.visibleName}
			</td> --%>
			<td>
				<fmt:formatDate value="${onOffLine.onOffTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
				<%--<td>--%>
				<%--${onOffLine.ip}--%>
				<%--</td>--%>
				<%--<shiro:hasPermission name="hotel:onOffLine:edit">--%>
				<%--<td>--%>
				<%--<a href="${ctx}/crm/hotel/onOffLine/form?id=${onOffLine.id}">修改</a>--%>
				<%--<a href="${ctx}/crm/hotel/onOffLine/delete?id=${onOffLine.id}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>--%>
				<%--</td>--%>
				<%--</shiro:hasPermission>--%>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
<%--<div class="form-actions">--%>
	<%--<input id="btnCancel_buttom" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
<%--</div>--%>
</body>
</html>