<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>settlementAccntEmobalance管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
        // 导出 
        function exportexcel(){
            $("#searchForm").attr("action",ctx+"/settlementaccntemobalance/settlementAccntEomBalance/exportexcel");
            $("#searchForm").submit();
            return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/settlementaccntemobalance/settlementAccntEomBalance/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/settlementaccntemobalance/settlementAccntEomBalance/">固化账户余额列表</a></li>
		<shiro:hasPermission name="settlementaccntemobalance:settlementAccntEomBalance:edit"><li><a href="${ctx}/settlementaccntemobalance/settlementAccntEomBalance/form">settlementAccntEmobalance添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntEomBalance" action="${ctx}/settlementaccntemobalance/settlementAccntEomBalance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户名称：</label>
				<form:input path="accountname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>账户余额：</label>
				<form:input path="beginBalance" htmlEscape="false" class="input-medium"/>
				- <form:input path="endBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntEomBalance.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntEomBalance.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>年：</label>
				<form:input path="year" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>月：</label>
				<form:input path="month" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input onclick="return page();" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账户ID</th>
				<th>账户名称</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>PMS编码</th>
				<th>账户余额</th>
				<th>账户角色</th>
				<th>创建时间</th>
				<th>年</th>
				<th>月</th>
				<shiro:hasPermission name="settlementaccntemobalance:settlementAccntEomBalance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntEomBalance">
			<tr>
				<td>
					${settlementAccntEomBalance.accountid}
				</td>
				<td>
					${settlementAccntEomBalance.accountname}
				</td>
				<td>
					${settlementAccntEomBalance.hotelid}
				</td>
				<td>
					${settlementAccntEomBalance.hotelname}
				</td>
				<td>
					${settlementAccntEomBalance.hotelpms}
				</td>
				<td>
					${settlementAccntEomBalance.balance}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntEomBalance.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntEomBalance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementAccntEomBalance.year}
				</td>
				<td>
					${settlementAccntEomBalance.month}
				</td>
				<shiro:hasPermission name="settlementaccntemobalance:settlementAccntEomBalance:edit"><td>
    				<a href="${ctx}/settlementaccntemobalance/settlementAccntEomBalance/form?id=${settlementAccntEomBalance.id}">修改</a>
					<a href="${ctx}/settlementaccntemobalance/settlementAccntEomBalance/delete?id=${settlementAccntEomBalance.id}" onclick="return confirmx('确认要删除该settlementAccntEmobalance吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>