<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户余额账单明细</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/listOfBalance");
			$("#searchForm").submit();
        	return false;
        }
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/exportexcel");
			$("#searchForm").submit();
		    return false;
        } 
	</script>
</head>
<body  >
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntdetail/settlementAccntDetail/listOfBalance">账户余额账单明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntDetail" action="${ctx}/accntdetail/settlementAccntDetail/listOfBalance" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户名称：</label>
				<form:input path="accountname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>业务单号：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>酒店PMS：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>旅行社名称：</label>
				<form:input path="tripname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> 
			<li><label>业务类型：</label>
				<form:select path="biztype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('BizTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>费用类型：</label>
				<form:select path="feetype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('FeeTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>原始金额：</label>
				<form:input type="number" path="beginOrderprice" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endOrderprice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>结算金额：</label>
				<form:input type="number" path="beginOrdertotal" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endOrdertotal" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<br><br>
			<li><label>下单时间：</label>
				<input name="beginOrdertime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginOrdertime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOrdertime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endOrdertime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
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
			    <th>ID</th>
				<th>账户ID</th>
				<th>账户名称</th>
				<th>业务单号</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>酒店PMS</th>
				<th>旅行社名称</th>
				<th>业务类型</th>
				<th>账户角色</th>
				<th>费用类型</th>
				<th>原始金额</th>
				<th>结算金额</th>
				<th>历史余额</th>
				<th>当前余额</th>
				<th>下单时间</th>
				<th>创建时间</th>
				<th>财务状态</th>
				<th>是否处理</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntDetail">
			<tr>
			    <td nowrap><a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">
                    ${settlementAccntDetail.id}
                </a></td>
				<td nowrap>
					${settlementAccntDetail.accountid}
				</td>
				<td nowrap>
					${settlementAccntDetail.accountname}
				</td>
				<td nowrap>
					${settlementAccntDetail.orderid}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelid}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelname}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelpms}
				</td>
				<td nowrap>
					${settlementAccntDetail.tripname}
				</td>
				<td nowrap style="text-align:center;">
					${fns:getDictLabel(settlementAccntDetail.biztype, 'BizTypeEnums', '')}
				</td>
				<td nowrap style="text-align:center;">
					${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td nowrap style="text-align:center;">
					${fns:getDictLabel(settlementAccntDetail.feetype, 'FeeTypeEnums', '')}
				</td>
				<td nowrap style="text-align:right;">
					${settlementAccntDetail.strOrderprice}
				</td>
				<td nowrap style="text-align:right;">
					${settlementAccntDetail.strOrdertotal}
				</td>
				<td nowrap style="text-align:right;">
					<fmt:formatNumber value="${settlementAccntDetail.balanceonafterupdate}" pattern="0.00"/>
				</td>
				<td nowrap style="text-align:right;">
                    <fmt:formatNumber value="${settlementAccntDetail.balance}" pattern="0.00"/>
                </td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccntDetail.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccntDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap style="text-align:center;">
					${fns:getDictLabel(settlementAccntDetail.status, 'AccountStatusEnums', '')}
				</td>
                <td nowrap style="text-align:center;">
                    ${fns:getDictLabel(settlementAccntDetail.flag1, 'COMMON_YESNO', '')}
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>