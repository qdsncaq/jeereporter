<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>所属区域管理</title>
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
		
		function serSubTree(treeId){
			var url = "${ctx}/crm/location/tCity/serSubTree";
			//重置select2控件的值
			var subIdSelect = $("#cityCode");
			$("#cityCode option:not(:first)").remove();
			subIdSelect.select2("val","");
			//区县
			var thirdIdSelect = $("#disCode");
			$("#disCode option:not(:first)").remove();
			thirdIdSelect.select2("val","");

			$.ajax({
				url: url,
				dataType: 'json',
				data:{
					proid:treeId
				},
				cache: false,
				async: true,
				success: function(data, textStatus) {
					var secTreeList = data;
					for(var node in secTreeList){
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/user/rfCrmUserArea/">所属区域列表</a></li>
		<shiro:hasPermission name="user:rfCrmUserArea:edit"><li><a href="${ctx}/crm/user/rfCrmUserArea/form">所属区域添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmUserArea" action="${ctx}/crm/user/rfCrmUserArea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>自增：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>机构编码：</label>
				<form:input path="userCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li> --%>
			<li><label>销售名称：</label>
				<form:select path="userCode" class="input-medium">
					<form:option value="" label="选择"/>
					<c:forEach items="${syOrgUserList}" var="user" varStatus="status">
						<form:option value="${user.userCode}" label="${user.userName}-${user.userMobile}"/>
					</c:forEach>
				</form:select>
			</li>
			<li><label>省：</label>
				<form:select path="provCode" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
					</c:forEach>
				</form:select>
			</li>
			<li><label>市：</label>
				<%--<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">--%>
				<form:select path="cityCode" class="input-medium">
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
					</c:forEach>
				</form:select>
			</li>
			<%--
			<li><label>省编码：</label>
				<form:input path="provCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			 <li><label>市编码：</label>
				<form:input path="cityCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>区编码：</label>
				<form:input path="disCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li> --%>
			<li><label>是否可用：</label>
				<form:select path="available" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('crm_available')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!--
 			<li><label>添加时间：</label>
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserArea.createTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>修改时间：</label>
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${rfCrmUserArea.updateTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			 -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
<!-- 				<th>自增</th> -->
				<th>用户编码</th>
				<th>用户名称</th>
				<th>省编码</th>
				<th>省名称</th>
				<th>市编码</th>
				<th>市名称</th>
				<th>是否可用</th>
<!-- 				<th>区编码</th> -->
<!-- 				<th>区名称</th> -->
<!-- 				<th>添加时间</th> -->
<!-- 				<th>修改时间</th> -->
				<shiro:hasPermission name="user:rfCrmUserArea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmUserArea">
			<tr>
				<%-- <td><a href="${ctx}/crm/user/rfCrmUserArea/form?id=${rfCrmUserArea.id}">
					${rfCrmUserArea.id}
				</a></td> --%>
				<td>
					${rfCrmUserArea.userCode}
				</td>
				<td>
					${rfCrmUserArea.userName}
				</td>
				<td>
					${rfCrmUserArea.provCode}
				</td>
				<td>
					${rfCrmUserArea.provName}
				</td>
				<td>
					${rfCrmUserArea.cityCode}
				</td>
				<td>
					${rfCrmUserArea.cityName}
				</td>
				<td>
					${rfCrmUserArea.availableName}
				</td>
				<%--<td>
 					${rfCrmUserArea.disCode} 
				</td>
				<td>
 					${rfCrmUserArea.disName} 
				</td>--%>
				<%-- <td>
					<fmt:formatDate value="${rfCrmUserArea.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<%-- <td>
					<fmt:formatDate value="${rfCrmUserArea.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<shiro:hasPermission name="user:rfCrmUserArea:edit"><td>
    				<a href="${ctx}/crm/user/rfCrmUserArea/form?id=${rfCrmUserArea.id}">修改</a>
<%-- 					<a href="${ctx}/crm/user/rfCrmUserArea/delete?id=${rfCrmUserArea.id}" onclick="return confirmx('确认要删除该所属区域吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>