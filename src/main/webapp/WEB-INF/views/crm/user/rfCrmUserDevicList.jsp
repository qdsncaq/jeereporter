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
			$("#searchForm").attr("action","${ctx}/crm/user/rfCrmUserDevic/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/user/rfCrmUserDevic/">信息列表</a></li>
		<%--<shiro:hasPermission name="user:rfCrmUserDevic:edit"><li><a href="${ctx}/crm/user/rfCrmUserDevic/form">信息添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmUserDevic" action="${ctx}/crm/user/rfCrmUserDevic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>主键：</label>--%>
				<%--<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>用户ID：</label>
				<form:input path="userId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>登录名称：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--<li><label>channelId：</label>--%>
				<%--<form:input path="channelId" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			<%--</li>--%>

			<li><label>设备信息：</label>
				<form:input path="devicInfo" htmlEscape="false" maxlength="512" class="input-medium"/>
			</li>
			<%--<li><label>创建时间：</label>--%>
				<%--<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${rfCrmUserDevic.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%--<li><label>更新时间：</label>--%>
				<%--<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${rfCrmUserDevic.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%--<li><label>备注信息：</label>--%>
				<%--<form:input path="remarks" htmlEscape="false" maxlength="512" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>登录设备：</label>
				<form:select path="loginDevic" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="android" label="Android"/>
					<form:option value="ios" label="iOS"/>
				</form:select>
			</li>
			<li><label>终端类型：</label>
				<form:select path="sysType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="1" label="CRM"/>
					<form:option value="2" label="OMS"/>
				</form:select>
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
				<%--<th>主键</th>--%>
				<th>用户ID</th>
				<th>登录名称</th>
				<th>channelId</th>
				<th>登录设备</th>
				<th>设备信息</th>
				<th>登录时间</th>
				<%--<th>更新时间</th>--%>
				<%--<th>备注信息</th>--%>
				<th>终端类型</th>
				<%--<shiro:hasPermission name="user:rfCrmUserDevic:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmUserDevic">
			<tr>
				<%--<td>--%>
					<%--<a href="${ctx}/crm/user/rfCrmUserDevic/form?id=${rfCrmUserDevic.id}">--%>
						<%--${rfCrmUserDevic.id}--%>
					<%--</a>--%>
				<%--</td>--%>
				<td>
					${rfCrmUserDevic.userId}
				</td>
				<td>
					${rfCrmUserDevic.loginName}
				</td>
				<td>
					${rfCrmUserDevic.channelId}
				</td>
				<td>
					${rfCrmUserDevic.loginDevic}
				</td>
				<td>
					${rfCrmUserDevic.devicInfo}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmUserDevic.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${rfCrmUserDevic.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${rfCrmUserDevic.remarks}--%>
				<%--</td>--%>
				<td>
					<%--${rfCrmUserDevic.sysType}--%>
					${rfCrmUserDevic.sysTypeName}
				</td>
				<%--<shiro:hasPermission name="user:rfCrmUserDevic:edit">--%>
					<%--<td>--%>
						<%--<a href="${ctx}/crm/user/rfCrmUserDevic/form?id=${rfCrmUserDevic.id}">修改</a>--%>
						<%--<a href="${ctx}/crm/user/rfCrmUserDevic/delete?id=${rfCrmUserDevic.id}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>--%>
					<%--</td>--%>
				<%--</shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>