<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售账单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/salesBillExportexcel");
			$("#searchForm").submit();
		    return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/salesMonthBill");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntbill/settlementAccntBill/salesMonthBill">销售月账单列表</a></li>
 		<%-- <shiro:hasPermission name="accntbill:settlementAccntBill:edit"><li><a href="${ctx}/accntbill/settlementAccntBill/form">销售月账单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntBill" action="${ctx}/accntbill/settlementAccntBill/salesMonthBill" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="accountyear" name="accountyear" type="hidden" value="${settlementAccntBill.accountyear}"/>
		<input id="accountmonth" name="accountmonth" type="hidden" value="${settlementAccntBill.accountmonth}"/>
		<ul class="ul-form">
			<li><label>开始日期：</label>
			    <%--
				<input name="beginBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.beginBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.endBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> --%>
                <input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccntBill.beginDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>结束日期：</label>
				<%-- <input name="beginEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.beginEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.endEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> --%>
                <input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccntBill.endDate}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>账号ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>销售角色：</label>
				<form:select path="accountrole" class="input-medium">
                    <form:option value="" label="--所有销售角色--"/>
                    <form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</li>
			<li><label>业务类型：</label>
                <form:select path="biztype" class="input-medium">
                    <form:option value="" label="--所有业务类型--"/>
                    <form:options items="${fns:getDictList('BizTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>账单类型：</label>
                <form:select path="accountstate" class="input-medium">
                    <form:option value="" label="--所有账单类型--"/>
                    <form:options items="${fns:getDictList('SettlementTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
			<li><label>财务状态：</label>
				<form:select path="scstate" class="input-medium">
					<form:option value="" label="--所有财务状态--"/>
					<form:options items="${fns:getDictList('SettlementStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>订单总额：</label>
				<form:input path="beginBalance" htmlEscape="false" class="input-medium"/>
				- <form:input path="endBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>销售奖金：</label>
				<form:input path="beginScsum" htmlEscape="false" class="input-medium"/>
				- <form:input path="endScsum" htmlEscape="false" class="input-medium"/>
			</li> --%>
			<li class="btns"><input onclick="return page();" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" id="" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="btns"><input onclick="history.go(-1)" class="btn btn-primary" type="button" value="返回"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账单ID</th>
				<th>开始日期</th>
		        <th>结束日期</th>
		        <th>账户ID</th>
		        <th>销售角色</th>
		        <th>业务类型</th>
		        <th>账单类型</th>
		        <th>财务状态</th>
		        <th>订单总额(元)</th>
		        <th>销售奖金(元)</th>
		        <th>操作</th>
				<%-- <shiro:hasPermission name="accntbill:settlementAccntBill:edit"><th>修改</th></shiro:hasPermission> --%>
				<shiro:hasRole name="SC_FI_SALERBILLCONFIRM"><th>财务操作</th></shiro:hasRole>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntBill">
			<tr>
                <td>
					${settlementAccntBill.id}
                </td>
				<td>
					<fmt:formatDate value="${settlementAccntBill.beginDate}" type="date" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntBill.endDate}" type="date" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${settlementAccntBill.accountid}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntBill.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
                    ${fns:getDictLabel(settlementAccntBill.biztype, 'BizTypeEnums', '')}
                </td>
                <td>
                    ${fns:getDictLabel(settlementAccntBill.accountstate, 'SettlementTypeEnums', '')}
                </td>
				<td>
					${fns:getDictLabel(settlementAccntBill.scstate, 'SettlementStatusEnums', '')}
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strBalance.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strBalance}
					<c:if test="${settlementAccntBill.strBalance.startsWith(\"-\") }"></font></c:if>
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strScsum.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strScsum}
					<c:if test="${settlementAccntBill.strScsum.startsWith(\"-\") }"></font></c:if>
				</td>
				<td>
	            	<a href="${ctx }/accntdetail/settlementAccntDetail/salesbillFinddetails?no=${settlementAccntBill.id}&biztype=${settlementAccntBill.biztype}&accountrole=${settlementAccntBill.accountrole}">明细</a>
	            </td>
				<%-- <shiro:hasPermission name="accntbill:settlementAccntBill:edit"><td>
    				<a href="${ctx}/accntbill/settlementAccntBill/form?id=${settlementAccntBill.id}">修改</a>
					<a href="${ctx}/accntbill/settlementAccntBill/delete?id=${settlementAccntBill.id}" onclick="return confirmx('确认要删除该accntbill吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
				<shiro:hasRole name="SC_FI_SALERBILLCONFIRM">
                    <td nowrap>
                        <c:choose>
                            <c:when test="${settlementAccntBill.accountstate == 1}">
                                <a href="javascript:;" confirmaction="${ctx}/accntbill/settlementAccntBill/confirm?id=${settlementAccntBill.id}&accountrole=${settlementAccntBill.accountrole}&biztype=${settlementAccntBill.biztype}"
                                    onclick="return confirmx('您真的要确认当前账单吗？', $(this).attr('confirmaction'))">确认账单</a>
                            </c:when>
                            <c:when test="${settlementAccntBill.accountstate == 2}">财务已确认</c:when>
                            <c:when test="${settlementAccntBill.accountstate == 3}">酒店已确认</c:when>
                            <c:otherwise>未知</c:otherwise>
                        </c:choose>
                    </td>
                </shiro:hasRole>
			</tr>
		</c:forEach>
        <c:if test="${page.list.size() == 0 }">
          <tr>
            <td colspan="100%" style="word-wrap:break-word;word-break:break-all;">
                未查找到符合条件的数据.
            </td>
          </tr>
        </c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>