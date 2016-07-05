<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出销售用户数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/crm/user/syOrgUser/export");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action","${ctx}/crm/user/syOrgUser/");
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
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
		<li class="active"><a href="${ctx}/crm/user/syOrgUser/">用户列表</a></li>
		<shiro:hasPermission name="user:syOrgUser:edit"><li><a href="${ctx}/crm/user/syOrgUser/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="syOrgUser" action="${ctx}/crm/user/syOrgUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="userName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<%-- <li><label>部门编码：</label>
				<form:input path="deptCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li> --%>
			<li><label>手机号码：</label>
				<form:input path="userMobile" htmlEscape="false" maxlength="40" class="input-medium"/>
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
			<li class="clearfix"></li>
			<li><label>人员状态：</label>
				<form:select path="userState" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('crm_user_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>启用标志：</label>
				<form:select path="sFlag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('crm_sflag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属公司：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="1" label="乐住"/>
					<form:option value="2" label="金盾"/>
				</form:select>
			</li>
            <li><label>职务：</label>
                <form:select path="level" class="input-medium">
                    <form:option value="" label="--请选择--"/>
                    <form:options items="${fns:getDictList('area_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>区总：</label>
                    <%--<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">--%>
                <form:select path="qzUserCode" class="input-medium">
                    <form:option value="" label="无"/>
                    <%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
                    <c:forEach items="${qzList}" var="qz" varStatus="status">
                        <form:option value="${qz.userCode}" label="${qz.userName}"/>
                    </c:forEach>
                </form:select>
            </li>
            <li><label>区经：</label>
                    <%--<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">--%>
                <form:select path="qjUserCode" class="input-medium">
                    <form:option value="" label="无"/>
                    <%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
                    <c:forEach items="${qjList}" var="qj" varStatus="status">
                        <form:option value="${qj.userCode}" label="${qj.userName}"/>
                    </c:forEach>
                </form:select>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户系统登录名</th>
				<th>用户名称</th>
<!-- 				<th>部门编码</th> -->
				<th>手机号码</th>
				<th>所属省份</th>
				<th>所属城市</th>
				<th>用户邮箱</th>
                <th>职务</th>
                <th>所属区总</th>
                <th>所属区经</th>
				<th>人员状态</th>
				<th>启用标志</th>
				<shiro:hasPermission name="user:syOrgUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="syOrgUser">
			<tr>
<!-- 				<td> -->
<%-- 					<a href="${ctx}/user/syOrgUser/form?id=${syOrgUser.userCode}"> --%>
<%-- 					${syOrgUser.userCode} --%>
<!-- 				</a></td> -->
				<td>
					${syOrgUser.userLoginName}
				</td>
				<td>
					${syOrgUser.userName}
				</td>
				<td>
					${syOrgUser.userMobile}
				</td>
				<td>
					${syOrgUser.provName}
				</td>
				<td>
					${syOrgUser.cityName}
				</td>
				<td>
					${syOrgUser.userEmail}
				</td>
                <td>
                        ${fns:getDictLabel(syOrgUser.level, 'area_level', syOrgUser.level)}
                </td>
                <td>
                        ${syOrgUser.qzUserName}
                </td>
                <td>
                        ${syOrgUser.qjUserName}
                </td>
				<td>
					${fns:getDictLabel(syOrgUser.userState, "crm_user_state", "在职")}
				</td>
				<td>
					${fns:getDictLabel(syOrgUser.sFlag, "crm_sflag", "启用")}
				</td>
				<shiro:hasPermission name="user:syOrgUser:edit"><td>
    				<a href="${ctx}/crm/user/syOrgUser/form?id=${syOrgUser.userCode}">修改</a>
<%-- 					<a href="${ctx}/crm/user/syOrgUser/delete?id=${syOrgUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>