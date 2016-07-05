<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbrules管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action","${ctx}/psbrules/settlementPsbrules/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		// 查询
		function query(){
			$("#searchForm").attr("action","${ctx}/psbrules/settlementPsbrules/list");
			$("#searchForm").submit();
        	return false;
        }
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/psbrules/settlementPsbrules/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/psbrules/settlementPsbrules/list">PSB科目列表</a></li>
		<shiro:hasPermission name="psbrules:settlementPsbrules:edit"><li><a href="${ctx}/psbrules/settlementPsbrules/form">PSB科目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPsbrules" action="${ctx}/psbrules/settlementPsbrules/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>科目编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>科目名称：</label>
				<form:input path="rulename" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>PSB厂商：</label>
				<form:select path="psbid" class="input-medium" >
					<form:option value="" label="--选择psb编码--"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.id } : ${psbinfo.psbname }" />
					</c:forEach>
				</form:select>
			</li>
			<%-- <li><label>科目费用(&lt;10间房)：</label>
				<form:input path="fee10" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>科目费用(&gt;=10间房)：</label>
				<form:input path="fee20" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> 
			<li><label>描述说明：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrules.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrules.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>是否删除：</label>
				<form:select path="delflag" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('psbdelflag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcel()" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>科目编码</th>
				<th>科目名称</th>
				<th>PSB编号</th>
				<th>PSB厂商</th>
				<th>科目费用(&lt;10间房)</th>
				<th>科目费用(&gt;=10间房)</th>
				<th>描述说明</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<!-- <th>是否有效标识</th> -->
				<shiro:hasPermission name="psbrules:settlementPsbrules:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPsbrules">
			<tr>
				<td><a href="${ctx}/psbrules/settlementPsbrules/form?id=${settlementPsbrules.id}">
					${settlementPsbrules.id}
				</a></td>
				<td>
					${settlementPsbrules.rulename}
				</td>
				<td>
					<%-- ${fns:getDictLabel(settlementPsbrules.psbid, '', '')} --%>
					${settlementPsbrules.psbid}
				</td>
				<td>
					${settlementPsbrules.psbname}
				</td>
				<td>
					${settlementPsbrules.fee10}
				</td>
				<td>
					${settlementPsbrules.fee20}
				</td>
				<td>
					${settlementPsbrules.remarks}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrules.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrules.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${settlementPsbrules.delflag}
				</td> --%>
				<shiro:hasPermission name="psbrules:settlementPsbrules:edit"><td>
					<c:if test="${settlementPsbrules.delflag == 1}">
	    				<a href="${ctx}/psbrules/settlementPsbrules/form?id=${settlementPsbrules.id}">修改</a>
						<a href="${ctx}/psbrules/settlementPsbrules/delete?id=${settlementPsbrules.id}" onclick="return confirmx('确认要删除该psbrules吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${settlementPsbrules.delflag == 2}">
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
