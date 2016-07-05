<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>settlementpay管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/settlementpay/settlementPay/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/settlementpay/settlementPay/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/settlementpay/settlementPay/list">支付记录列表</a></li>
		<shiro:hasPermission name="settlementpay:settlementPay:edit"><li><a href="${ctx}/settlementpay/settlementPay/form">支付记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPay" action="${ctx}/settlementpay/settlementPay/list" method="post" class="breadcrumb form-search">
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
			<%-- <li><label>PMS编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>商户订单号：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>业务单号：</label>
				<form:input type="number" path="sysOrderid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label style="width: 130px">微信/支付宝交易号：</label>
				<form:input path="thirdno" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>业务类型：</label>
				<form:select path="biztype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('BizTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>支付金额：</label>
				<form:input path="sum" htmlEscape="false" class="input-medium"/>
			</li> --%>
			<li><label>支付类型：</label>
				<form:select path="paychannel" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('PayChannelEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>类型：</label>
				<form:select path="paytype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>第三方账号ID：</label>
				<form:input path="buyerid" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>第三方账号：</label>
				<form:input path="buyermail" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>付款银行：</label>
				<form:input path="banktype" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li> --%>
			<li><label>支付状态：</label>
				<form:select path="paystatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('PayStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>错误编码：</label>
				<form:input path="errCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>错误描述：</label>
				<form:input path="errCodeDes" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>trade_status：</label>
				<form:input path="tradeStatus" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li><label>支付金额：</label>
				<form:input path="beginPrice" htmlEscape="false" class="input-medium"/>
				- <form:input path="endPrice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>支付时间：</label>
				<input name="beginPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.beginPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.endPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>完成时间：</label>
				<input name="beginCallbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.beginCallbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCallbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.endCallbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<%-- <li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="beginUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.beginUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPay.endUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcel()" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>系统编号</th>
				<th>账户ID</th>
				<th>账户名称</th>
				<th>酒店ID</th>
				<!-- <th>PMS编码</th> -->
				<th>酒店名称</th>
				<th>商户订单号</th>
				<th>业务单号</th>
				<th>微信/支付宝交易号</th>
				<th>第三方账号ID</th>
				<th>第三方账号</th>
				<th>支付金额</th>
				<!-- <th>支付金额</th> -->
				<th>业务类型</th>
				<th>支付类型</th>
				<!-- <th>类型</th>
				<th>第三方账号ID</th>
				<th>第三方账号</th>
				<th>付款银行</th> -->
				<th>支付状态</th>
				<!-- <th>错误编码</th>
				<th>错误描述</th>
				<th>trade_status</th> -->
				<!-- <th>支付时间</th> -->
				<th>创建时间</th>
				<th>支付完成时间</th>
				<!-- <th>更新时间</th> -->
				<shiro:hasPermission name="settlementpay:settlementPay:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPay">
			<tr>
				<td><a href="${ctx}/settlementpay/settlementPay/form?id=${settlementPay.id}">
					${settlementPay.id}
				</a></td>
				<td>
					${settlementPay.accountid}
				</td>
				<td>
					${settlementPay.accountname}
				</td>
				<td>
					${settlementPay.hotelid}
				</td>
				<%-- <td>
					${settlementPay.hotelpms}
				</td> --%>
				<td>
					${settlementPay.hotelname}
				</td>
				<td>
					${settlementPay.orderid}
				</td>
				<td>
					${settlementPay.sysOrderid}
				</td>
				<td>
					${settlementPay.thirdno}
				</td>
                <td>
                    ${settlementPay.buyerid}
                </td>
                <td>
                    ${settlementPay.buyermail}
                </td>
				<td>
					${settlementPay.price}
				</td>
				<td>
					${fns:getDictLabel(settlementPay.biztype, 'BizTypeEnums', '')}
				</td>
				<%-- <td>
					${settlementPay.sum}
				</td> --%>
				<td>
					${fns:getDictLabel(settlementPay.paychannel, 'PayChannelEnums', '')}
				</td>
				<%-- <td>
					${fns:getDictLabel(settlementPay.paytype, '', '')}
				</td> --%>
				<%-- <td>
					${settlementPay.buyerid}
				</td>
				<td>
					${settlementPay.buyermail}
				</td>
				<td>
					${settlementPay.banktype}
				</td> --%>
				<td>
					${fns:getDictLabel(settlementPay.paystatus, 'PayStatusEnums', '')}
				</td>
				<%-- <td>
					${settlementPay.errCode}
				</td>
				<td>
					${settlementPay.errCodeDes}
				</td>
				<td>
					${settlementPay.tradeStatus}
				</td> --%>
				<%-- <td>
					<fmt:formatDate value="${settlementPay.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					<fmt:formatDate value="${settlementPay.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPay.callbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${settlementPay.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<shiro:hasPermission name="settlementpay:settlementPay:edit"><td>
    				<a href="${ctx}/settlementpay/settlementPay/form?id=${settlementPay.id}">修改</a>
					<a href="${ctx}/settlementpay/settlementPay/delete?id=${settlementPay.id}" onclick="return confirmx('确认要删除该settlementpay吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>