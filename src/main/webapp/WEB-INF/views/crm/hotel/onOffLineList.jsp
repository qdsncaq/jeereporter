<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认导出酒店上下线数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/hotel/onOffLine/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/hotel/onOffLine//list");
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

		function serThirdTree(treeId){
			var url = "${ctx}/crm/location/tDistrict/serSubTree";
			//重置select2控件的值
			var thirdIdSelect = $("#disCode");
			$("#disCode option:not(:first)").remove();
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
		<li class="active"><a href="${ctx}/crm/hotel/onOffLine/list">上下线列表</a></li>
		<%--<shiro:hasPermission name="hotel:onOffLine:edit"><li><a href="${ctx}/crm/hotel/onOffLine/form">信息添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="onOffLine" action="${ctx}/crm/hotel/onOffLine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="psbType" name="psbType" type="hidden" value="201"/>
		<ul class="ul-form">
			<li><label>快速查询：</label>
				<form:radiobuttons path="condition" items="${fns:getDictList('crm_condition')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="clearfix"></li>
			<li><label>酒店ID：</label>
				<form:input path="hotelId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店PMS：</label>
				<form:input path="pms" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>酒店地址：</label>
				<form:input path="hotelAddr" htmlEscape="false" maxlength="512" class="input-medium"/>
			</li>
			<%--<li><label>上下线类型：</label>--%>
				<%--<form:select path="type" class="input-medium">--%>
					<%--<form:option value="" label="--请选择--"/>--%>
					<%--<form:option value="1" label="上线"/>--%>
					<%--<form:option value="2" label="下线"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<%--<li><label>psb类型：</label>--%>
				<%--<form:select path="psbType" class="input-medium">--%>
					<%--<form:option value="" label="--请选择--"/>--%>
					<%--<form:option value="101" label="房爸爸"/>--%>
					<%--<form:option value="201" label="金盾"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li class="clearfix"></li>
			<li><label>当前状态：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="1" label="已上线"/>
					<form:option value="2" label="已下线"/>
					<form:option value="3" label="未录入"/>
					<form:option value="4" label="未上线"/>
				</form:select>
			</li>
			<li><label>选择城市：</label>
				<form:select path="provCode" class="input-medium" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
					<%--<form:select path="cityCode" class="input-medium">--%>
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<form:select path="disCode" class="input-medium">
					<form:option value="" label="选择县/区"/>
					<form:options items="${districtList}" itemLabel="disname" itemValue="code" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li><label>上线时间：</label>
				<input name="onlineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${onOffLine.onlineTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-
				<input name="onlineTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${onOffLine.onlineTimeEnd}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>下线时间：</label>
				<input name="offlineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${onOffLine.offlineTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-
				<input name="offlineTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${onOffLine.offlineTimeEnd}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>酒店地址</th>
				<th>pms</th>
				<th>省/市/区县</th>
				<%--<th>市</th>--%>
				<%--<th>区/县</th>--%>
				<%--<th>操作类型</th>--%>
				<%--<th>psb类型</th>--%>
				<%--<th>操作人名称</th>--%>
				<th>首次上线时间</th>
				<th>末次下线时间</th>
				<th>当前状态</th>
				<th>&nbsp;说明&nbsp;</th>
				<%--<th>上下线时间</th>--%>
				<%--<th>IP地址</th>--%>
				<shiro:hasPermission name="hotel:onOffLine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="onOffLine">
			<tr>
				<td>
					<%-- <a href="${ctx}/crm/hotel/onOffLine/details?hotelId=${onOffLine.hotelId}"> --%>
						${onOffLine.hotelId}
					<!-- </a> -->
					<%--${onOffLine.hotelId}--%>
				</td>
				<td>
					${onOffLine.hotelName}
				</td>
				<td>
					${onOffLine.hotelAddr}
				</td>
				<td>
					${onOffLine.pms}
				</td>
				<td>
					${onOffLine.provName}<br>
					${onOffLine.cityName}<br>
					${onOffLine.disName}
				</td>
				<%--<td>--%>
					<%--${onOffLine.cityName}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${onOffLine.disName}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${onOffLine.type}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${onOffLine.psbType}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${onOffLine.operateName}--%>
				<%--</td>--%>
				<td>
					<fmt:formatDate value="${onOffLine.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${onOffLine.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/crm/hotel/onOffLine/details?pms=${onOffLine.pms}">
					${onOffLine.visibleName}
					</a>
				</td>
				<td>
					${onOffLine.remarks}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${onOffLine.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${onOffLine.ip}--%>
				<%--</td>--%>
				<shiro:hasPermission name="hotel:onOffLine:edit">
					<td>
    				<%--<a href="${ctx}/crm/hotel/onOffLine/form?id=${onOffLine.id}">修改</a>--%>
					<%--<a href="${ctx}/crm/hotel/onOffLine/delete?id=${onOffLine.id}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>--%>
						<%-- <a href="${ctx}/crm/hotel/onOffLine/details?hotelId=${onOffLine.hotelId}">明细</a>
						<p></p> --%>
						<a href="${ctx}/crm/hotel/onOffLine/hotelRateList?HOTEL_ID=${onOffLine.pms}">pms详情</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>