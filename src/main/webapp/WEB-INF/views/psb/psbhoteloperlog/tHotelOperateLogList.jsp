<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbhoteloperlog管理</title>
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
		<li class="active"><a href="${ctx}/psbhoteloperlog/tHotelOperateLog/">酒店操作记录列表</a></li>
		<shiro:hasPermission name="psbhoteloperlog:tHotelOperateLog:edit"><li><a href="${ctx}/psbhoteloperlog/tHotelOperateLog/form">psbhoteloperlog添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tHotelOperateLog" action="${ctx}/psbhoteloperlog/tHotelOperateLog/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		
			 <li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%-- <li><label>用户ID：</label>
				<form:input path="usercode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用户姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li> --%>
			<%-- <li><label>审核类型：</label>
				<form:select path="checktype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核类型名称</label>
				<form:input path="checktypename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li> --%>
			<li><label style="width: 120px">审核类型名称：</label>
				<form:select path="checktype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<%-- <form:options items="${fns:getDictList('HotelOnlineState')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:option value="3" label="上线" />
					<form:option value="4" label="下线" />
				</form:select>
			</li>
			<li><label>审核时间：</label>
				<input name="beginChecktime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tHotelOperateLog.beginChecktime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endChecktime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tHotelOperateLog.endChecktime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> 



	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>用户ID</th>
				<th>用户姓名</th>
				<th>审核时间</th>
				<!-- <th>审核类型（1，初次审核；2，更新审核）</th> -->
				<th>审核类型名称</th>
				<shiro:hasPermission name="psbhoteloperlog:tHotelOperateLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tHotelOperateLog">
			<tr>
				<td><a href="${ctx}/psbhoteloperlog/tHotelOperateLog/form?id=${tHotelOperateLog.id}">
					${tHotelOperateLog.id}
				</a></td>
				<td>
					${tHotelOperateLog.hotelid}
				</td>
				<td>
					${tHotelOperateLog.hotelname}
				</td>
				<td>
					${tHotelOperateLog.usercode}
				</td>
				<td>
					${tHotelOperateLog.username}
				</td>
				<td>
					<fmt:formatDate value="${tHotelOperateLog.checktime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${fns:getDictLabel(tHotelOperateLog.checktype, '', '')}
				</td> --%>
				<td>
					${tHotelOperateLog.checktypename}
				</td>
				<shiro:hasPermission name="psbhoteloperlog:tHotelOperateLog:edit"><td>
    				<a href="${ctx}/psbhoteloperlog/tHotelOperateLog/form?id=${tHotelOperateLog.id}">修改</a>
					<a href="${ctx}/psbhoteloperlog/tHotelOperateLog/delete?id=${tHotelOperateLog.id}" onclick="return confirmx('确认要删除该psbhoteloperlog吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>