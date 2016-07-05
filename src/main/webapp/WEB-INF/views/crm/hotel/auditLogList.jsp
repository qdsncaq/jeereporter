<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/hotel/eHotel/auditLogList");
			$("#searchForm").submit();
			return false;
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="hotelTab">
		<li><a id="tab1" href="${ctx}/crm/hotel/eHotel/checkform?id=${param.hotelId}&tabVal=tab1">酒店基本信息</a></li>
		<li><a id="tab2" href="${ctx}/crm/hotel/eHotel/checkform?id=${param.hotelId}&tabVal=tab2">酒店图片</a></li>
		<li><a id="tab3" href="${ctx}/crm/hotel/eHotel/checkform?id=${param.hotelId}&tabVal=tab3">房型信息</a></li>
		<li class="active"><a href="">操作日志</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hotelAuditLogMongo" action="${ctx}/crm/hotel/eHotel/auditLogList" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作类型</th>
				<th>操作时间</th>
				<th>酒店ID</th>
				<th>pms</th>
				<th>操作人</th>
				<th>备注</th>
				<th>操作内容</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditLog">
			<tr>
				<td>
					${auditLog.typeName}
				</td>
				<td>
					<fmt:formatDate value="${auditLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${auditLog.hotelId}
				</td>
				<td>
					${auditLog.pms}
				</td>
				<td>
					${auditLog.operateName}
				</td>
				<td>
						${auditLog.remarks}
				</td>
				<td>
					<c:forEach items="${auditLog.differents}" var="diff">
						${diff} <br>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>