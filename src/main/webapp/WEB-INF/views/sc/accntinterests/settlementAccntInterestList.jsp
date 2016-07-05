<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accntInterests管理</title>
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
		<li class="active"><a href="${ctx}/accntinterests/settlementAccntInterest/">酒店利息明细列表</a></li>
		<shiro:hasPermission name="accntinterests:settlementAccntInterest:edit"><li><a href="${ctx}/accntinterests/settlementAccntInterest/form">accntInterests添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccntInterest" action="${ctx}/accntinterests/settlementAccntInterest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
			<li><label>PMS编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>计息天数：</label>
				<form:input path="days" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>起息日期：</label>
				<input name="begininterestdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntInterest.begininterestdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>止息日期：</label>
				<input name="endinterestdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntInterest.endinterestdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>利率：</label>
				<form:input path="interestrate" htmlEscape="false" class="input-medium"/>
			</li>
			<%-- <li><label>利率类型：</label>
				<form:input path="ratetype" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>是否复利：</label>
				<form:input path="isrepeat" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>主键id</th> -->
				<!-- <th>总账主键id</th> -->
				<th>账户ID</th>
				<th>账户名称</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>酒店PMS编码</th>
				<th>计息金额</th>
				<th>开始计息日期</th>
				<th>截止计息日期</th>
				<th>计息天数</th>
				<th>利率</th>
				<!-- <th>利率类型</th> -->
				<!-- <th>是否复利</th> -->
				<th>利息</th>
				<!-- <th>余额是否已更新</th>
				<th>状态</th> -->
				<th>生成时间</th>
				<%-- <shiro:hasPermission name="accntinterests:settlementAccntInterest:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntInterest">
			<tr>
				<%-- <td><a href="${ctx}/accntinterests/settlementAccntInterest/form?id=${settlementAccntInterest.id}">
					${settlementAccntInterest.id}
				</a></td>
				<td>
					${settlementAccntInterest.ledgerid}
				</td> --%>
				<td>
					${settlementAccntInterest.accountid}
				</td>
				<td>
					${settlementAccntInterest.accountname}
				</td>
				<td>
					${settlementAccntInterest.hotelid}
				</td>
				<td>
					${settlementAccntInterest.hotelname}
				</td>
				<td>
					${settlementAccntInterest.hotelpms}
				</td>
				<td>
					${settlementAccntInterest.pvalue}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntInterest.begininterestdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntInterest.endinterestdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementAccntInterest.days}
				</td>
				<td>
					${settlementAccntInterest.interestrate}
				</td>
				<%-- <td>
					${settlementAccntInterest.ratetype}
				</td>
				<td>
					${settlementAccntInterest.isrepeat}
				</td> --%>
				<td>
					${settlementAccntInterest.interests}
				</td>
				<%-- <td>
					${settlementAccntInterest.balanceupdated}
				</td>
				<td>
					${settlementAccntInterest.status}
				</td> --%>
				<td>
					<fmt:formatDate value="${settlementAccntInterest.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="accntinterests:settlementAccntInterest:edit"><td>
    				<a href="${ctx}/accntinterests/settlementAccntInterest/form?id=${settlementAccntInterest.id}">修改</a>
					<a href="${ctx}/accntinterests/settlementAccntInterest/delete?id=${settlementAccntInterest.id}" onclick="return confirmx('确认要删除该accntInterests吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>