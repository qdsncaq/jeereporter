<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店银行账号管理</title>
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
		<li class="active"><a href="${ctx}/crm/bank/tHotelBank/">酒店银行账号列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="THotelBank" action="${ctx}/crm/bank/tHotelBank/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店Id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>开户行：</label>
				<form:input path="bank" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>支行：</label>
				<form:input path="bankbranch" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>用名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店Id</th>
				<th>酒店名称</th>
				<th>开户行</th>
				<th>支行</th>
				<th>用名</th>
				<th>卡号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tHotelBank">
			<tr>
				<td>
					${tHotelBank.hotelid}
				</td>
				<td>
					${tHotelBank.hotelname}
				</td>
				<td>
					${tHotelBank.bank}
				</td>
				<td>
					${tHotelBank.bankbranch}
				</td>
				<td>
					${tHotelBank.name}
				</td>
				<td>
					${tHotelBank.account}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>