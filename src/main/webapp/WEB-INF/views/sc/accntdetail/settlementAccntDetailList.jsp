<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accntdetail管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/list");
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
		<li class="active"><a href="${ctx}/accntdetail/settlementAccntDetail/list">账单流水列表</a></li>
		<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><li><a href="${ctx}/accntdetail/settlementAccntDetail/form">账单流水添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntDetail" action="${ctx}/accntdetail/settlementAccntDetail/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户名称：</label>
				<form:input path="accountname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>订单ID：</label>
				<form:input type="number" path="orderid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<%-- <li><label>旅行社名称：</label>
				<form:input path="tripname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> 
			<li><label>PMS编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>--%>
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
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单金额：</label>
				<form:input type="number" path="beginOrderprice" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endOrderprice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>结算金额：</label>
				<form:input type="number" path="beginOrdertotal" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endOrdertotal" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>充值优惠：</label>
				<form:input type="number" path="beginFreetotal" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endFreetotal" htmlEscape="false" class="input-medium"/>
			</li>
			<%-- <li><label>房间数：</label>
				<form:input path="beginRoomnums" htmlEscape="false" maxlength="8" class="input-medium"/>
				- <form:input path="endRoomnums" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>天数：</label>
				<form:input path="beginDays" htmlEscape="false" maxlength="4" class="input-medium"/>
				- <form:input path="endDays" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li> --%>
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
			<%-- <li><label>更新时间：</label>
				<input name="beginUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li><label>预离时间：</label>
				<input name="beginLeftDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginLeftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endLeftDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endLeftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
            <li><label>支付时间：</label>
                <input name="beginCallbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccntDetail.beginCallbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
                <input name="endCallbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccntDetail.endCallbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li>
			<%-- <li><label>调整说明：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<%-- <li><label>规则ID：</label>
				<form:input path="scruleid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>比率：</label>
				<form:input path="snapshotrate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>规则描述：</label>
				<form:input path="scruledesc" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<form:input path="accountyear" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>月：</label>
				<form:input path="accountmonth" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>周：</label>
				<form:input path="accountweek" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li> --%>
			<%-- <li><label>账期起始时间：</label>
				<input name="beginBegindate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginBegindate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endBegindate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endBegindate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>账期截止时间：</label>
				<input name="beginEnddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginEnddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endEnddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endEnddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<%-- <li><label>账单ID：</label>
				<form:input path="no" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li> --%>
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
				<th>订单ID</th>
				<th>商户订单号</th>
                <th>微信/支付宝交易号</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<!-- <th>PMS编码</th>
				<th>旅行社名称</th> -->
				<th>业务类型</th>
				<th>账户角色</th>
				<th>费用类型</th>
				<th>订单金额</th>
				<th>结算金额</th>
				<th> 
		  		  ${bzt==6?'充值优惠金额':'订单成功率系数'}
				</th>
				<th>下单时间</th>
				<th>创建时间</th>
				<th>预离时间</th>
				<!-- <th>房间数</th>
				<th>天数</th> -->
				<!-- <th>更新时间</th> -->
				<th>状态</th>
				<!-- <th>订单状态</th> -->
				<th>调整说明</th>
				<!-- <th>规则ID</th>
				<th>比率</th>
				<th>规则描述</th>
				<th>年</th>
				<th>月</th>
				<th>周</th>
				<th>账期起始时间</th>
				<th>账期截止时间</th> --> 
				<!-- <th>账单ID</th>  -->
				<th>支付时间</th>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntDetail">
			<tr>
				<td><a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">
					${settlementAccntDetail.accountid}
				</a></td>
				<td>
					${settlementAccntDetail.accountname}
				</td>
				<td>
					${settlementAccntDetail.orderid}
				</td>
				<td>
                    ${settlementAccntDetail.bizorderid}
                </td>
                <td>
                    ${settlementAccntDetail.thirdno}
                </td>
				<td>
					${settlementAccntDetail.hotelid}
				</td>
				<td>
					${settlementAccntDetail.hotelname}
				</td>
				<%-- <td>
					${settlementAccntDetail.hotelpms}
				</td>
				<td>
					${settlementAccntDetail.tripname}
				</td> --%>
				<td>
					${fns:getDictLabel(settlementAccntDetail.biztype, 'BizTypeEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.feetype, 'FeeTypeEnums', '')}
				</td>
				<td>
					${settlementAccntDetail.strOrderprice}
				</td>
				<td>
					<%-- ${settlementAccntDetail.strOrdertotal} --%>
					${settlementAccntDetail.ordertotal}
				</td>
				<td>
					${settlementAccntDetail.strFreetotal}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.leftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${settlementAccntDetail.roomnums}
				</td>
				<td>
					${settlementAccntDetail.days}
				</td> --%>
				<%-- <td>
					<fmt:formatDate value="${settlementAccntDetail.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					${fns:getDictLabel(settlementAccntDetail.status, 'AccountStatusEnums', '')}
				</td>
				<%-- <td>
					${settlementAccntDetail.orderstatus}
				</td> --%>
				<td>
					${settlementAccntDetail.remarks}
				</td>
				<%-- <td>
					${settlementAccntDetail.scruleid}
				</td>
				<td>
					${settlementAccntDetail.snapshotrate}
				</td>
				<td>
					${settlementAccntDetail.scruledesc}
				</td>
				<td>
					${settlementAccntDetail.accountyear}
				</td>
				<td>
					${settlementAccntDetail.accountmonth}
				</td>
				<td>
					${settlementAccntDetail.accountweek}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.begindate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<%-- <td>
					${settlementAccntDetail.no}
				</td>  --%>
				<td>
				    <fmt:formatDate value="${settlementAccntDetail.callbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    <c:if test="${settlementAccntDetail.status == 9 && empty settlementAccntDetail.callbackTime}">
				    	<fmt:formatDate value="${settlementAccntDetail.ordertime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </c:if>
				</td>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><td>
    				<a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">修改</a>
					<a href="${ctx}/accntdetail/settlementAccntDetail/delete?id=${settlementAccntDetail.id}" onclick="return confirmx('确认要删除该accntdetail吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>