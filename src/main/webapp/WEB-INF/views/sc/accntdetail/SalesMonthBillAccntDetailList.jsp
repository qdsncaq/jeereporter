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
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/salesbillFinddetails");
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
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntdetail/settlementAccntDetail/salesbillFinddetails">销售月账单列表</a></li>
		<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><li><a href="${ctx}/accntdetail/settlementAccntDetail/form">销售月账单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntDetail" action="${ctx}/accntdetail/settlementAccntDetail/salesbillFinddetails" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="no" name="no" type="hidden" value="${settlementAccntDetail.no}"/>
		<input id="no1" name="no1" type="hidden" value="${settlementAccntDetail.no1}"/>
		<ul class="ul-form">
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="btns"><input onclick="history.go(-1)" class="btn btn-primary" type="button" value="返回"/></li>
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
				<th>业务类型</th>
				<th>账户角色</th>
				<th>费用类型</th>
				<th>订单金额</th>
				<th>结算金额</th>
				<th>充值优惠金额</th>
				<th>下单时间</th>
				<th>创建时间</th>
				<th>预离时间</th>
				<th>状态</th>
				<th>调整说明</th>
				<th>支付时间</th>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntDetail">
			<tr>
				<td nowrap><a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">
					${settlementAccntDetail.accountid}
				</a></td>
				<td nowrap>
					${settlementAccntDetail.accountname}
				</td>
				<td nowrap>
					${settlementAccntDetail.orderid}
				</td>
				<td nowrap>
                    ${settlementAccntDetail.bizorderid}
                </td>
                <td nowrap>
                    ${settlementAccntDetail.thirdno}
                </td>
				<td nowrap>
					${settlementAccntDetail.hotelid}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelname}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.biztype, 'BizTypeEnums', '')}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.feetype, 'FeeTypeEnums', '')}
				</td>
				<td nowrap>
					${settlementAccntDetail.strOrderprice}
				</td>
				<td nowrap>
					${settlementAccntDetail.ordertotal}
				</td>
				<td nowrap>
					${settlementAccntDetail.strFreetotal}
				</td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccntDetail.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccntDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccntDetail.leftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.status, 'AccountStatusEnums', '')}
				</td>
				<td nowrap>
					${settlementAccntDetail.remarks}
				</td>
				<td nowrap>
				    <fmt:formatDate value="${settlementAccntDetail.callbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    <c:if test="${settlementAccntDetail.status == 9 && empty settlementAccntDetail.callbackTime}">
				    	<fmt:formatDate value="${settlementAccntDetail.ordertime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </c:if>
				</td>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><td nowrap>
    				<a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">修改</a>
					<a href="${ctx}/accntdetail/settlementAccntDetail/delete?id=${settlementAccntDetail.id}" onclick="return confirmx('确认要删除该accntdetail吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
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