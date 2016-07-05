<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>orderdetail管理</title>
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
		<li class="active"><a href="${ctx}/orderdetail/orderdetails/">订单详情</a></li>
		<%-- <shiro:hasPermission name="orderdetail:orderdetails:edit"><li><a href="${ctx}/orderdetail/orderdetails/form">orderdetail添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="orderdetails" action="${ctx}/orderdetail/orderdetails/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单id：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>预抵时间：</label>
				<input name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orderdetails.begintime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>预离时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orderdetails.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>订单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('OrderDetailStatusEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orderdetails.createtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>酒店id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>房型名称：</label>
				<form:input path="roomtypename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="window.location.href='${ctx}/order/orders'" class="btn" type="button" value="返回"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单id</th>
				<th>预抵时间</th>
				<th>预离时间</th>
				<th>总价格</th>
				<th>订单状态</th>
				<th>创建时间</th>
				<th>酒店id</th>
				<th>房型名称</th>
				<%-- <shiro:hasPermission name="orderdetail:orderdetails:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderdetails">
			<tr>
				<td>
					<%-- <a href="${ctx}/orderdetail/orderdetails/form?id=${orderdetails.id}">
					</a> --%>
					${orderdetails.orderid}
				</td>
				<td>
					<fmt:formatDate value="${orderdetails.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orderdetails.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderdetails.totalprice}
				</td>
				<td>
					${fns:getDictLabel(orderdetails.status, 'OrderDetailStatusEnum', '')}
				</td>
				<td>
					<fmt:formatDate value="${orderdetails.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderdetails.hotelid}
				</td>
				<td>
					${orderdetails.roomtypename}
				</td>
				<%-- <shiro:hasPermission name="orderdetail:orderdetails:edit"><td>
    				<a href="${ctx}/orderdetail/orderdetails/form?id=${orderdetails.id}">修改</a>
					<a href="${ctx}/orderdetail/orderdetails/delete?id=${orderdetails.id}" onclick="return confirmx('确认要删除该orderdetail吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>