<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购发票管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/invoice/omsReceipt/list");
			$("#searchForm").submit();
        	return false;
        };
        
	    /**
         * 导出
         */
        function exportexcel(){
            $("#searchForm").attr("action",ctx+"/invoice/omsReceipt/exportexcel");
            $("#searchForm").submit();
            return false;
        };
   
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/invoice/omsReceipt/">采购发票列表</a></li>
		<shiro:hasPermission name="invoice:omsReceipt:add"><li><a href="${ctx}/invoice/omsReceipt/form">采购发票添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="omsReceipt" action="${ctx}/invoice/omsReceipt/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>票据类型：</label>
                <form:select path="rtype" class="input-medium">
                    <form:option value="" label="--请选择--"/>
                    <form:options items="${fns:getDictList('InvoiceTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>票据抬头：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>纳税人号：</label>
				<form:input path="identiid" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>发票号码：</label>
				<form:input path="receiptcode" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" onclick="return page();" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" id="exportbtn" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主键</th>
				<th>票据ID</th>
				<th>金额(元)</th>
				<th>票据类型</th>
				<th>票据抬头</th>
				<th>纳税人号</th>
				<th>发票内容</th>
				<th>发票号码</th>
				<th>开票日期</th>
				<shiro:hasPermission name="invoice:omsReceipt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="omsReceipt">
			<tr>
				<td><a href="${ctx}/invoice/omsReceipt/form?id=${omsReceipt.id}">
					${omsReceipt.id}
				</a></td>
				<td>
					${omsReceipt.code}
				</td>
				<td style="text-align:right;"><fmt:formatNumber value="${omsReceipt.money}" pattern="0.00"/>
				</td>
				<td>
					${fns:getDictLabel(omsReceipt.rtype, 'InvoiceTypeEnums', '')}
				</td>
				<td>
					${omsReceipt.title}
				</td>
				<td>
					${omsReceipt.identiid}
				</td>
                <td>
                    ${omsReceipt.content}
                </td>
				<td>
					${omsReceipt.receiptcode}
				</td>
				<td>
					${omsReceipt.receiptdate}
				</td>
				<shiro:hasPermission name="invoice:omsReceipt:edit"><td>
				    <a href="${ctx}/invoice/omsReceipt/invoiceorders?id=${omsReceipt.id}">订单</a>
    				<c:choose>
                        <c:when test="${omsReceipt.isopen == 2}">
    				        <a href="${ctx}/invoice/omsReceipt/form?id=${omsReceipt.id}">开票</a>
                        </c:when>
                        <c:when test="${omsReceipt.isopen == 1}">
                            已开票
                        </c:when>
                        <c:otherwise>
                            未知状态
                        </c:otherwise>
                    </c:choose>
				</td></shiro:hasPermission>
                <shiro:hasPermission name="invoice:omsReceipt:delete"><td>
                    <a href="${ctx}/invoice/omsReceipt/delete?id=${omsReceipt.id}" onclick="return confirmx('确认要删除该采购发票吗？', this.href)">删除</a>
                </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>