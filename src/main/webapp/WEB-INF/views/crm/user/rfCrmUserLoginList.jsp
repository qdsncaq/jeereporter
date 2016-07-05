<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出登录统计记录吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/user/rfCrmUserDevic/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/user/rfCrmUserDevic/omsLoginList");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/user/rfCrmUserDevic/omsLoginList">登录列表</a></li>
		<%--<shiro:hasPermission name="user:rfCrmUserDevic:edit"><li><a href="${ctx}/crm/user/rfCrmUserDevic/form">信息添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmUserDevic" action="${ctx}/crm/user/rfCrmUserDevic/omsLoginList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店名称：</label>
				<form:input path="hotelName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>老板账号</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>省</th>
				<th>市</th>
				<%--<th>区/县</th>--%>
				<th>是否开通分销</th>
				<th>最早登录时间</th>
				<th>最近登录时间</th>
				<th>销售名称</th>
				<th>销售账号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmUserDevic">
			<tr>
				<td>
					${rfCrmUserDevic.loginName}
				</td>
				<td>
					${rfCrmUserDevic.hotelId}
				</td>
				<td>
					${rfCrmUserDevic.hotelName}
				</td>
				<td>
					${rfCrmUserDevic.provName}
				</td>
				<td>
					${rfCrmUserDevic.cityName}
				</td>
				<%--<td>--%>
					<%--${rfCrmUserDevic.disName}--%>
				<%--</td>--%>
				<td>
					${rfCrmUserDevic.distributCooperateName}
				</td>
				<%--<td>--%>
					<%--${rfCrmUserDevic.washCooperateName}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${rfCrmUserDevic.supplyCooperateName}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${rfCrmUserDevic.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserDevic.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rfCrmUserDevic.salerName}
				</td>
				<td>
					${rfCrmUserDevic.salerMobile}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>