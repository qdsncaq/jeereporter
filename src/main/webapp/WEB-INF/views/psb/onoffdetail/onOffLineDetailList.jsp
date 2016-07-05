<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>onoffdetail管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		function serSubTree(treeId){
			// alert(treeId); return false;
			//var url = "${ctx}/crm/location/tCity/serSubTree";
			var url ="${ctx}/psbrulesrelation/settlementPsbrulesrelation/serSubTree";
			
			//重置select2控件的值
			var subIdSelect = $("#citycode");
			$("#citycode option:not(:first)").remove();
			subIdSelect.select2("val","");
			//区县
			var thirdIdSelect = $("#discode");
			$("#discode option:not(:first)").remove();
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
						var option = "<option value='"+secTreeList[node].code+"' valueid='" + secTreeList[node].cityid + ',' +secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}
		
		function serThirdTree(treeId){
			var arr = treeId.split(",");
			$("#city_hidname").val(arr[1]);
			var url = "${ctx}/crm/location/tDistrict/serSubTree";
			//重置select2控件的值
			var thirdIdSelect = $("#discode");
			$("#disCode option:not(:first)").remove();
			thirdIdSelect.select2("val","");

			$.ajax({
				url: url,
				dataType: 'json',
				data:{
					cityid:arr[0]
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
		
		// 导出 
        function exportexcel(){
            $("#searchForm").attr("action","${ctx}/onoffdetail/onOffLineDetail/exportexcel");
            $("#searchForm").submit();
            return false;
        }
		
		// 查询
        function query(){
            $("#searchForm").attr("action","${ctx}/onoffdetail/onOffLineDetail/list");
            $("#searchForm").submit();
            return false;
        }
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/onoffdetail/onOffLineDetail/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/onoffdetail/onOffLineDetail/">酒店上下线列表</a></li>
		<shiro:hasPermission name="onoffdetail:onOffLineDetail:edit"><li><a href="${ctx}/onoffdetail/onOffLineDetail/form">onoffdetail添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="onOffLineDetail" action="${ctx}/onoffdetail/onOffLineDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店ID：</label>
				<form:input path="hotelId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店PMS：</label>
				<form:input path="pms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>选择城市：</label>
				<%-- <form:input path="proname" htmlEscape="false" maxlength="200" class="input-medium"/> --%>
				<form:select path="procode" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<%--<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">--%>
				<form:select path="citycode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<form:select path="discode" class="input-medium">
					<form:option value="" label="选择县/区"/>
					<form:options items="${districtList}" itemLabel="disname" itemValue="code" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>上下线时间：</label>
				<input name="beginOnOffTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${onOffLineDetail.beginOnOffTime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> - 
				<input name="endOnOffTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${onOffLineDetail.endOnOffTime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/>
			</li>
			<li class="clearfix"></li>
			<li><label>当前状态：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="1" label="已上线"/>
					<form:option value="2" label="已下线"/>
				</form:select>
			</li>
			<%-- <li><label>删除标志：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="return page();" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="exportexcel()" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店ID</th>
				<th>酒店PMS</th>
				<th>酒店名称</th>
				<th>省/市/区县</th>
				<th>操作人ID</th>
				<th>操作人名称</th>
				<th>上下线时间</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>备注信息</th>
				<!-- <th>数据类型</th> -->
				<th>当前状态</th>
				<!-- <th>删除标志</th> -->
				<shiro:hasPermission name="onoffdetail:onOffLineDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="onOffLineDetail">
			<tr>
				<td>
					${onOffLineDetail.hotelId}
				</td>
				<td>
					${onOffLineDetail.pms}
				</td>
				<td>
					${onOffLineDetail.hotelname}
				</td>
				<td>
					${onOffLineDetail.proname}<br>
					${onOffLineDetail.cityname}<br>
					${onOffLineDetail.disname}
				</td>
				<td>
					${onOffLineDetail.operateId}
				</td>
				<td>
					${onOffLineDetail.operateName}
				</td>
				<td>
					<fmt:formatDate value="${onOffLineDetail.onOffTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${onOffLineDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${onOffLineDetail.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${onOffLineDetail.remarks}
				</td>
				<%-- <td>
					${fns:getDictLabel(onOffLineDetail.remarkType, '', '')}
				</td> --%>
				<td>
					<%-- ${fns:getDictLabel(onOffLineDetail.type, '', '')} --%>
					<c:if test="${onOffLineDetail.type  == 1}">上线</c:if>
					<c:if test="${onOffLineDetail.type  == 2}">下线</c:if>
				</td>
				<%-- <td>
					${fns:getDictLabel(onOffLineDetail.delFlag, 'del_flag', '')}
				</td> --%>
				<shiro:hasPermission name="onoffdetail:onOffLineDetail:edit"><td>
    				<a href="${ctx}/onoffdetail/onOffLineDetail/form?id=${onOffLineDetail.id}">修改</a>
					<a href="${ctx}/onoffdetail/onOffLineDetail/delete?id=${onOffLineDetail.id}" onclick="return confirmx('确认要删除该onoffdetail吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>