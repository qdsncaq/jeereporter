<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accntledger管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
        // 导出 
        function exportexcel(){
            $("#searchForm").attr("action",ctx+"/accntledger/settlementAccntLedger/exportexcel");
            $("#searchForm").submit();
            return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntledger/settlementAccntLedger/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/accntledger/settlementAccntLedger/">账户余额列表</a></li>
        <shiro:hasPermission name="accntledger:settlementAccntLedger:edit"><li><a href="${ctx}/accntledger/settlementAccntLedger/form">账户余额添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntLedger" action="${ctx}/accntledger/settlementAccntLedger/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>自增主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li> --%>
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
			<%-- <li><label>PMS编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> 
			<li><label>账户密码：</label>
				<form:input path="password" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> 
			<li><label>状态：</label>
				<form:select path="accountstate" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>冻结起始时间：</label>
				<input name="beginFrozendate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.beginFrozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endFrozendate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.endFrozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>冻结结束时间：</label>
				<input name="beginUnfrozendate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.beginUnfrozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUnfrozendate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.endUnfrozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li><label>账户余额：</label>
				<form:input path="beginBalance" htmlEscape="false" class="input-medium"/>
				- <form:input path="endBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<%-- <li><label>更新时间：</label>
				<input name="beginUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.beginUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntLedger.endUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>存储中间金额：</label>
				<form:input path="waitpayment" htmlEscape="false" class="input-medium"/>
			</li> --%>
			<li><label>可用额度：</label>
				<form:input path="beginPostpayBalance" htmlEscape="false" class="input-medium"/>
				- <form:input path="endPostpayBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>后付费额度：</label>
				<form:input path="beginPostpayThreshold" htmlEscape="false" class="input-medium"/>
				- <form:input path="endPostpayThreshold" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>账户类型：</label>
				<form:select path="accounttype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('TripAccntTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<!-- <th>自增主键</th> -->
				<th>账户ID</th>
				<th>账户名称</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<!-- <th>PMS编码</th> -->
				<!-- <th>账户密码</th> -->
				<!-- <th>状态</th>
				<th>冻结起始时间</th>
				<th>冻结结束时间</th> -->
				<th>账户角色</th>
				<th>旅行社账户类型</th>
				<th>当前余额</th>
				<th>可用额度</th>
				<th>后付费额度</th>
				<th>创建时间</th>
				<!-- <th>更新时间</th>
				<th>存储中间金额</th> -->
				<shiro:hasPermission name="accntledger:settlementAccntLedger:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntLedger">
			<tr>
				<%-- <td><a href="${ctx}/accntledger/settlementAccntLedger/form?id=${settlementAccntLedger.id}">
					${settlementAccntLedger.id}
				</a></td> --%>
				<td><a href="${ctx}/accntledger/settlementAccntLedger/form?id=${settlementAccntLedger.id}">
					${settlementAccntLedger.accountid}</a>
				</td>
				<td>
					${settlementAccntLedger.accountname}
				</td>
				<td>
					${settlementAccntLedger.hotelid}
				</td>
				<td>
					${settlementAccntLedger.hotelname}
				</td>
				<%-- <td>
					${settlementAccntLedger.hotelpms}
				</td>
				<td>
					${settlementAccntLedger.password}
				</td> --%>
				<td>
					${fns:getDictLabel(settlementAccntLedger.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntLedger.accounttype, 'TripAccntTypeEnums', '')}
				</td>
				<td>
					${settlementAccntLedger.balance}
				</td>
				<td>
					${settlementAccntLedger.postpayBalance}
				</td>
				<td>
					${settlementAccntLedger.postpayThreshold}
				</td>
				<%-- <td>
					${fns:getDictLabel(settlementAccntLedger.accountstate, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntLedger.frozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntLedger.unfrozendate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					<fmt:formatDate value="${settlementAccntLedger.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${settlementAccntLedger.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementAccntLedger.waitpayment}
				</td> --%>
				<shiro:hasPermission name="accntledger:settlementAccntLedger:edit"><td>
    				<a href="${ctx}/accntledger/settlementAccntLedger/form?id=${settlementAccntLedger.id}">修改</a>
					<a href="${ctx}/accntledger/settlementAccntLedger/delete?id=${settlementAccntLedger.id}" onclick="return confirmx('确认要删除该accntledger吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>