<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店老板利率设置</title>
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
		<li class="active"><a href="${ctx}/settlementbaseinterest/settlementBaseInterest/">老板利率设置列表</a></li>
		<shiro:hasPermission name="settlementbaseinterest:settlementBaseInterest:edit"><li><a href="${ctx}/settlementbaseinterest/settlementBaseInterest/form">酒店老板利率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementBaseInterest" action="${ctx}/settlementbaseinterest/settlementBaseInterest/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>PMSID：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
			    	<form:option value="" label=""/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>利率：</label>
				<form:input path="interestrate" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>计息类型：</label>
				<form:select path="interestratetype" class="input-medium">
			    	<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('InterestrateTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开关 ：</label>
				<form:select path="status" class="input-medium">
				    <form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('SettlementBaseInterestStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否复利：</label>
				<form:select path="compoundinterest" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('CompoundinterestEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开始时间：</label>
				<input name="beginBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBaseInterest.beginBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementBaseInterest.endBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>PMSID</th>
				<th>账户角色</th>
				<th>利率</th>
				<th>计息类型</th>
				<th>开关</th>
				<th>是否复利</th>
				<th>开始计息时间</th>
				<th>最近更新时间</th>
				<shiro:hasPermission name="settlementbaseinterest:settlementBaseInterest:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementBaseInterest">
			<tr>
				<td><a href="${ctx}/settlementbaseinterest/settlementBaseInterest/form?id=${settlementBaseInterest.id}">
					${settlementBaseInterest.hotelid}
				</a></td>
				<td>
					${settlementBaseInterest.hotelname}
				</td>
				<td>
					${settlementBaseInterest.hotelpms}
				</td>
				<td>
					${fns:getDictLabel(settlementBaseInterest.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
					${settlementBaseInterest.interestrate}
				</td>
				<td>
					${fns:getDictLabel(settlementBaseInterest.interestratetype, 'InterestrateTypeEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementBaseInterest.status, 'SettlementBaseInterestStatusEnums', '')}
				</td>
				<td>
				    ${fns:getDictLabel(settlementBaseInterest.compoundinterest, 'CompoundinterestEnums', '')}
				</td>
				<td>
					<fmt:formatDate value="${settlementBaseInterest.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementBaseInterest.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="settlementbaseinterest:settlementBaseInterest:edit"><td>
    				<a href="${ctx}/settlementbaseinterest/settlementBaseInterest/form?id=${settlementBaseInterest.id}">修改</a>
					<a href="${ctx}/settlementbaseinterest/settlementBaseInterest/delete?id=${settlementBaseInterest.id}" onclick="return confirmx('确认要删除该酒店利率设置信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>