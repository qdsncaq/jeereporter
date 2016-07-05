<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出酒店审核数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/hotel/eHotel/exportAuditHotels");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/hotel/eHotel/checkList");
			$("#searchForm").submit();
			return false;
		}


		function serSubTree(treeId){
			var url = "${ctx}/crm/location/tCity/serSubTree";
			//重置select2控件的值
			var subIdSelect = $("#citycode");
			$("#citycode option:not(:first)").remove();
			subIdSelect.select2("val","");
			//区县
			var thirdIdSelect = $("#disCode");
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
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}
		exportAuditHotels
		function serThirdTree(treeId){
			var url = "${ctx}/crm/location/tDistrict/serSubTree";
			//重置select2控件的值
			var thirdIdSelect = $("#discode");
			$("#discode option:not(:first)").remove();
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
	 <%--  <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/hotel/eHotel/checkList">酒店审核</a></li>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="EHotel" action="${ctx}/crm/hotel/eHotel/checkList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店id：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
            <li><label>酒店地址：</label>
                <form:input path="detailaddr" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
			<li><label>销售：</label>
				<form:input path="userName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
            <li class="clearfix"></li>
            <li><label>前台电话：</label>
                <form:input path="qtphone" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
            <li><label>负责人姓名：</label>
                <form:input path="bossName" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
            <li><label>负责人电话：</label>
                <form:input path="bossPhone" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
			<li><label>上下线：</label>
				<form:select path="visible" class="input-medium">
					<form:option value="T" label="上线"/>
					<form:option value="F" label="下线"/>
				</form:select>
			</li>
            <li class="clearfix"></li>
            <li><label>审核类型：</label>
                <form:select path="state" class="input-medium">
                    <form:option value="" label="--全部--"/>
                    <form:options items="${fns:getDictList('hotel_audit_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
			<li><label>审核状态：</label>
				<form:select path="checkState" class="input-medium">
					<form:option value="" label="--全部--"/>
					<form:options items="${fns:getDictList('hotelCheckState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核时间：</label>
				<input name="beginOperateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${EHotel.beginOperateTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-
				<input name="endOperateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${EHotel.endOperateTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>最后审核人：</label>
				<form:input path="operateName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
            <li><label>选择城市：</label>
                <form:select path="provcode" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
                    <form:option value="" label="选择省"/>
                    <%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
                    <c:forEach items="${provinceList}" var="prov" varStatus="status">
                        <form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
                    </c:forEach>
                </form:select>
                &nbsp;&nbsp;
                <form:select path="citycode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
                    <%--<form:select path="cityCode" class="input-medium">--%>
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
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店id</th>
				<th>酒店名称</th>
				<th>省/市/县</th>
				<th>酒店地址</th>
				<th>酒店老板</th>
				<%--<th>审核类型</th>--%>
				<th>最后审核人</th>
				<%--<th>最后审核时间</th>--%>
				<th>审核状态</th>
<%-- 				<shiro:hasPermission name="hotel:eHotel:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="eHotel">
			<tr>
				<td>
					<a href="${ctx}/crm/hotel/eHotel/checkform?id=${eHotel.id}">
                    	 ${eHotel.id}
					</a>
				</td>				
				<td>
					${eHotel.hotelname}
				</td>
				<td>
					${eHotel.provName}/${eHotel.cityName}/${eHotel.disName}
				</td>
				<td>
					${eHotel.detailaddr}
				</td>
				<td>
					${eHotel.hotelcontactname}(${eHotel.bossPhone})
				</td>
				<%--<td>--%>
					<%--<c:if test="${eHotel.state == 3}">--%>
						<%--<c:set value="color: red;" var="textColor" />--%>
					<%--</c:if>--%>
					<%--<span style="${textColor}">${fns:getDictLabel(eHotel.state, 'hotel_audit_type', '')}</span>--%>
				<%--</td>--%>
				<td>
						${eHotel.operateName}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${eHotel.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
			    <td>
                    <a href="${ctx}/crm/hotel/eHotel/checkform?id=${eHotel.id}">
                    	${fns:getDictLabel(eHotel.checkState, 'hotelCheckState', '')}
					</a>
                </td>
				<%-- <shiro:hasPermission name="hotel:eHotel:edit"><td>
                    <c:if test="${eHotel.checkState == 1}">
                        <a href="${ctx}/crm/hotel/eHotel/checkform?id=${eHotel.id}">审核</a>
                    </c:if>
                </td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div> 
</body>
</html>