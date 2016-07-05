<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>奖金分配比例管理</title>
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
        
        /**
         * 
         */
        function serSubTree(treeId){
            var url = "${ctx}/crm/location/tCity/serSubTree";
            //重置select2控件的值
            var subIdSelect = $("#city");
            $("#city option:not(:first)").remove();
            subIdSelect.select2("val","");
            //区县
            var thirdIdSelect = $("#district");
            $("#district option:not(:first)").remove();
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

        /**
         * 
         */
        function serThirdTree(treeId){
            var url = "${ctx}/crm/location/tDistrict/serSubTree";
            //重置select2控件的值
            var thirdIdSelect = $("#district");
            $("#district option:not(:first)").remove();
            thirdIdSelect.select2("val","");

            $.ajax({
                url: url,
                dataType: 'json',
                data:{
                    cityid:treeId
                },
                cache: false,
                async: true,
                success: function(data, textStatus) {
                    var secTreeList = data;
                    for(var node in secTreeList){
                        var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].id+"'>"+secTreeList[node].disname+"</option>";
                        thirdIdSelect.append(option);
                    }
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/settlementBonusProportion/">奖金分配比例列表</a></li>
		<shiro:hasPermission name="base:settlementBonusProportion:add"><li><a href="${ctx}/base/settlementBonusProportion/form">奖金分配比例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementBonusProportion" action="${ctx}/base/settlementBonusProportion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>省级编码：</label>
			    <!--
				<form:input path="province" htmlEscape="false" maxlength="20" class="input-medium"/>
				-->
				<form:select path="province" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
                    <form:option value="" label="选择省"/>
                    <c:forEach items="${provinceList}" var="prov" varStatus="status">
                        <form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
                    </c:forEach>
                </form:select>
			</li>
			<!--
			<li><label>市级编码：</label>
				<form:select path="city" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
                    <form:option value="" label="选择市"/>
                    <c:forEach items="${cityList}" var="city" varStatus="status">
                        <form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
                    </c:forEach>
                </form:select>
			</li>
			<li><label>区级编码：</label>
				<form:select path="district" class="input-medium">
                    <form:option value="" label="选择县/区"/>
                    <form:options items="${districtList}" itemLabel="disname" itemValue="code" htmlEscape="false"/>
                </form:select>
			</li>
			-->
			<li><label>角色：</label>
				<form:select path="role" class="input-medium">
                    <form:option value="" label="--请选择--"/>
                    <form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <!--
				<th>id</th>
				-->
				<th>省级编码</th>
				<!--
				<th>市级编码</th>
				<th>区级编码</th>
				-->
				<th>省份名称</th>
				<th>角色</th>
				<th>分配比例</th>
				<!--
				<th>创建时间</th>
				<th>修改时间</th>
				<th>操作人</th>
				-->
				<shiro:hasPermission name="base:settlementBonusProportion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementBonusProportion">
			<tr>
			    <!--
				<td><a href="${ctx}/base/settlementBonusProportion/form?id=${settlementBonusProportion.id}">
					${settlementBonusProportion.id}
				</a></td>
				-->
				<td>
					${settlementBonusProportion.province}
				</td>
				<!--
				<td>
					${settlementBonusProportion.city}
				</td>
				<td>
					${settlementBonusProportion.district}
				</td>
				-->
				<td>
					${settlementBonusProportion.proname}
				</td>
				<td>
					${fns:getDictLabel(settlementBonusProportion.role, 'AccountRoleEnums', '')}
				</td>
				<td>
					${settlementBonusProportion.proportion}
				</td>
				<!--
				<td>
					<fmt:formatDate value="${settlementBonusProportion.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementBonusProportion.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementBonusProportion.operator}
				</td>
				-->
				<shiro:hasPermission name="base:settlementBonusProportion:edit"><td>
    				<a href="${ctx}/base/settlementBonusProportion/form?id=${settlementBonusProportion.id}">修改</a>
				</td></shiro:hasPermission>
                <shiro:hasPermission name="base:settlementBonusProportion:delete"><td>
                    <a href="${ctx}/base/settlementBonusProportion/delete?id=${settlementBonusProportion.id}" onclick="return confirmx('确认要删除该奖金分配比例吗？', this.href)">删除</a>
                </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>