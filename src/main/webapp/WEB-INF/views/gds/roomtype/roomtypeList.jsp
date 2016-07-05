<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房型管理</title>
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
		<li class="active"><a href="${ctx}/roomtype/roomtype/">房型列表</a></li>
		<shiro:hasPermission name="roomtype:roomtype:edit"><li><a href="${ctx}/roomtype/roomtype/form">房型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="roomtype" action="${ctx}/roomtype/roomtype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>id_1：</label>
				<form:input path="id1" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>房型名字：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>房型pms：</label>
				<form:input path="roomtypepms" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>房型下房间数：</label>
				<form:input path="roomnum" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>门市价：</label>
				<form:input path="cost" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>房型类型：1、普通房型，2、特价房型：</label>
				<form:input path="type" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>updatetime：</label>
				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${roomtype.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>id_1</th>
				<th>酒店id</th>
				<th>房型名字</th>
				<th>房型pms</th>
				<th>房型下房间数</th>
				<th>门市价</th>
				<th>房型类型：1、普通房型，2、特价房型</th>
				<th>updatetime</th>
				<shiro:hasPermission name="roomtype:roomtype:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="roomtype">
			<tr>
				<td><a href="${ctx}/roomtype/roomtype/form?id=${roomtype.id}">
					${roomtype.id}
				</a></td>
				<td>
					${roomtype.id1}
				</td>
				<td>
					${roomtype.hotelid}
				</td>
				<td>
					${roomtype.name}
				</td>
				<td>
					${roomtype.roomtypepms}
				</td>
				<td>
					${roomtype.roomnum}
				</td>
				<td>
					${roomtype.cost}
				</td>
				<td>
					${roomtype.type}
				</td>
				<td>
					<fmt:formatDate value="${roomtype.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="roomtype:roomtype:edit"><td>
    				<a href="${ctx}/roomtype/roomtype/form?id=${roomtype.id}">修改</a>
					<a href="${ctx}/roomtype/roomtype/delete?id=${roomtype.id}" onclick="return confirmx('确认要删除该房型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>