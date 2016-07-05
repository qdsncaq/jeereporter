<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbinfo管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		// 导出 
        function exportexcel(){
            $("#searchForm").attr("action",ctx+"/psbinfo/settlementPsbinfo/exportexcel");
            $("#searchForm").submit();
            return false;
        }
        // 查询 
        function query(){
            $("#searchForm").attr("action",ctx+"/psbinfo/settlementPsbinfo/list");
            $("#searchForm").submit();
            return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/psbinfo/settlementPsbinfo/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/psbinfo/settlementPsbinfo/list">PSB厂家列表</a></li>
		<shiro:hasPermission name="psbinfo:settlementPsbinfo:edit"><li><a href="${ctx}/psbinfo/settlementPsbinfo/form">PSB厂家添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPsbinfo" action="${ctx}/psbinfo/settlementPsbinfo/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>PSB编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>PSB厂商：</label>
				<form:input path="psbname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>开始日期：</label>
				<input name="beginBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.beginBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.endBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="beginEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.beginEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.endEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>年费：</label>
				<form:input path="beginFee" htmlEscape="false" class="input-medium"/>
				- <form:input path="endFee" htmlEscape="false" class="input-medium"/>
			</li>
			<%-- <li><label>创建时间：</label>
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbinfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li><label>是否删除：</label>
				<form:select path="delflag" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('psbdelflag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>PSB编号</th>
				<th>PSB厂商</th>
				<th>合作开始日期</th>
				<th>合作结束日期</th>
				<th>年费</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="psbinfo:settlementPsbinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPsbinfo">
			<tr>
				<td><a href="${ctx}/psbinfo/settlementPsbinfo/form?id=${settlementPsbinfo.id}">
					${settlementPsbinfo.id}
				</a></td>
				<td>
					${settlementPsbinfo.psbname}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbinfo.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbinfo.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementPsbinfo.fee}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbinfo.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbinfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="psbinfo:settlementPsbinfo:edit"><td>
					<c:if test="${settlementPsbinfo.delflag == 1}">
	    				<a href="${ctx}/psbinfo/settlementPsbinfo/form?id=${settlementPsbinfo.id}">修改</a>
						<a href="${ctx}/psbinfo/settlementPsbinfo/delete?id=${settlementPsbinfo.id}" onclick="return confirmx('确认要删除该psb厂家信息吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${settlementPsbinfo.delflag == 2}">
						已删除
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
