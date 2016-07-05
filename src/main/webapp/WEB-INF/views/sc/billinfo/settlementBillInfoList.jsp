<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>billinfo管理</title>
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
		<li class="active"><a href="${ctx}/billinfo/settlementBillInfo/">账单收款列表</a></li>
		<shiro:hasPermission name="billinfo:settlementBillInfo:edit"><li><a href="${ctx}/billinfo/settlementBillInfo/form?billid=${settlementBillInfo.billid}">账单收款添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementBillInfo" action="${ctx}/billinfo/settlementBillInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="billscsum" name="billscsum" type="hidden" value="${settlementBillInfo.billscsum}"/>
		<ul class="ul-form">
			<%-- <li><label>账单收款记录表,自增主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li> --%>
			<li><label>账单ID：</label>
				<form:input path="billid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<%-- <li><label>账单金额：</label>
				<form:input path="billscsum" htmlEscape="false" class="input-medium"/>
			</li> --%>
			<li><label>收款金额：</label>
				<form:input path="settlesum" htmlEscape="false" class="input-medium"/>
				- <form:input path="settlesum" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>收款日期：</label>
				<input name="beginSettletime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.beginSettletime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endSettletime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.endSettletime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>账单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SettlementStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>备注：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<%-- <li><label>更新时间：</label>
				<input name="beginUpdatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.beginUpdatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBillInfo.endUpdatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-2)"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>账单收款记录表,自增主键</th> -->
				<th>账单ID</th>
				<th>账单结算金额</th>
				<th>收款金额</th>
				<th>收款日期</th>
				<th>账单收款状态</th>
				<th>备注</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="billinfo:settlementBillInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementBillInfo">
			<tr>
				<%-- <td><a href="${ctx}/billinfo/settlementBillInfo/form?id=${settlementBillInfo.id}">
					${settlementBillInfo.id}
				</a></td> --%>
				<td>
					<a href="${ctx}/billinfo/settlementBillInfo/form?id=${settlementBillInfo.id}">
						${settlementBillInfo.billid}</a>
				</td>
				<td>
					${settlementBillInfo.billscsum}
				</td>
				<td>
					${settlementBillInfo.settlesum}
				</td>
				<td>
					<fmt:formatDate value="${settlementBillInfo.settletime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(settlementBillInfo.status, 'SettlementStatusEnums', '')}
				</td>
				<td>
					${settlementBillInfo.remarks}
				</td>
				<td>
					<fmt:formatDate value="${settlementBillInfo.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementBillInfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="billinfo:settlementBillInfo:edit"><td>
    				<a href="${ctx}/billinfo/settlementBillInfo/form?id=${settlementBillInfo.id}">添加</a>
					<%-- <a href="${ctx}/billinfo/settlementBillInfo/delete?id=${settlementBillInfo.id}" onclick="return confirmx('确认要删除该billinfo吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>