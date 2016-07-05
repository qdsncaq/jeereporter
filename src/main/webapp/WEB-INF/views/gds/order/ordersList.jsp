<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>order管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    var ctx = "${ctx}";
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/order/orders/");
			$("#searchForm").submit();
        	return false;
        }
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/order/orders/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		function query(){
			$("#searchForm").attr("action",ctx+"/order/orders/");
			$("#searchForm").submit();
			return false;
        }
		function reset(){
			$("#searchForm").attr("action",ctx+"/order/orders/");
			$("#searchForm").reset();
			$("#searchForm select").val('');
			return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/orders/">订单列表</a></li>
		<%-- <shiro:hasPermission name="order:orders:edit"><li><a href="${ctx}/order/orders/form">order添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="orders" action="${ctx}/order/orders/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>支付类型：</label>
				<form:select path="paytype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('PayTypeEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orders.createtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>预抵时间：</label>
				<input name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orders.begintime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>预离时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orders.endtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>订单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('OrderStatusEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>联系人：</label>
				<form:input path="contacts" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="contactsphone" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>分销商名称：</label>
				<form:input path="channelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input onclick="query()" id="btnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
			<!-- <li class="btns"><input onclick="reset()" class="btn btn-primary" type="button" value="清空"/></li> -->
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单id</th>
				<th>酒店id</th>
				<th>酒店名称</th>
				<th>支付类型</th>
				<th>预抵时间</th>
				<th>预离时间</th>
				<th>订单状态</th>
				<th>创建时间</th>
				<th>总价格</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>分销商名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orders">
			<tr>
				<td>
				<a href="${ctx}/order/orders/form?id=${orders.id}">
					${orders.id}
				</a>
				</td>
				<td>
					${orders.hotelid}
				</td>
				<td>
					${orders.hotelname}
				</td>
				<td>
					${fns:getDictLabel(orders.paytype, 'PayTypeEnum', '')}
				</td>
				<td>
					<fmt:formatDate value="${orders.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${orders.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(orders.status, 'OrderStatusEnum', '')}
				</td>
				<td>
					<fmt:formatDate value="${orders.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orders.totalprice}
				</td>
				<td>
					${orders.contacts}
				</td>
				<td>
					${orders.contactsphone}
				</td>
				<td>
					${orders.channelname}
				</td>
				<td>
					<a href="${ctx}/orderdetail/orderdetails?orderid=${orders.id}">订单详情</a>
				</td>
				<%-- <shiro:hasPermission name="order:orders:edit"><td>
    				<a href="${ctx}/order/orders/form?id=${orders.id}">修改</a>
					<a href="${ctx}/order/orders/delete?id=${orders.id}" onclick="return confirmx('确认要删除该order吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>