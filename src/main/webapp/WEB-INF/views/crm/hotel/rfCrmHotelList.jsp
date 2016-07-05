<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出酒店查询数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/hotel/rfCrmHotel/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnExportAll").click(function(){
				top.$.jBox.confirm("确认要导出酒店全部数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/hotel/rfCrmHotel/exportAll");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/hotel/rfCrmHotel/list");
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
		<li class="active"><a href="${ctx}/crm/hotel/rfCrmHotel/">酒店列表</a></li>
		<%--<shiro:hasPermission name="hotel:rfCrmHotel:edit"><li><a href="${ctx}/crm/hotel/rfCrmHotel/form">酒店添加</a></li></shiro:hasPermission>--%>
	</ul>
	<%--<div id="messageBox" class="alert alert-info" style="margin-bottom:-6px;background-color: #d9edf7;border-color: #bce8f1;">--%>
		<%--<strong class="lead" style="font-size:12px;color:#9f9f9f;">为了便于查询为您提供不大于1000条的查询数据，如需浏览所有数据，请选择导出全部后查看</strong>--%>
	<%--</div>--%>
	<form:form id="searchForm" modelAttribute="rfCrmHotel" action="${ctx}/crm/hotel/rfCrmHotel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店地址：</label>
				<form:input path="detailAddr" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>销售姓名：</label>
				<form:input path="salesName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>销售电话：</label>
				<form:input path="salesMobile" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>

			<li class="clearfix"></li>


			<li><label>公私海：</label>
				<form:select path="isPrivate" class="input-medium">
					<form:option value="" label="选择公私海"/>
					<form:option value="1" label="私海"/>
					<form:option value="2" label="公海"/>
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
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<shiro:hasPermission name="hotel:rfCrmHotel:edit">
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				</shiro:hasPermission>
				<%--<input id="btnExportAll" class="btn btn-primary" type="button" value="导出全部"/>--%>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店编号</th>
				<th>酒店名称</th>
				<th>公私海</th>
				<th>房间数</th>
				<th>销售</th>
				<th>销售电话</th>
				<th>老板</th>
				<th>老板电话</th>
				<th>联系人</th>
				<th>省份</th>
				<th>城市</th>
				<th>区域</th>
				<th>酒店地址</th>
				<th>开业时间</th>
				<th>注册时间</th>
				<th>最后装修</th>
				<th>pms</th>
				<th>审核状态</th>
				<shiro:hasPermission name="hotel:rfCrmHotel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmHotel">
			<tr>
				<td>
					<%--<a href="${ctx}/crm/hotel/rfCrmHotel/form?id=${rfCrmHotel.id}"></a>--%>
					${rfCrmHotel.id}
				</td>
				<td>
					${rfCrmHotel.hotelName}
				</td>
				<td>
					${rfCrmHotel.isPrivateName}
				</td>
				<td>
					${rfCrmHotel.roomNum}
				</td>
				<td>
					${rfCrmHotel.salesName}
				</td>
				<td>
					${rfCrmHotel.salesMobile}
				</td>
				<td>
					${rfCrmHotel.bossName}
				</td>
				<td>
					${rfCrmHotel.bossMobile}
				</td>
				<td>
					${rfCrmHotel.hotelContactName}
				</td>
				<td>
					${rfCrmHotel.provName}
				</td>
				<td>
					${rfCrmHotel.cityName}
				</td>
				<td>
					${rfCrmHotel.disName}
				</td>
				<td>
					${rfCrmHotel.detailAddr}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmHotel.openTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmHotel.createTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${rfCrmHotel.repairTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${rfCrmHotel.pms}
				</td>
				<td>
					${rfCrmHotel.statusName}
				</td>
				<shiro:hasPermission name="hotel:rfCrmHotel:edit"><td>
    				<%--<a href="${ctx}/crm/hotel/rfCrmHotel/form?id=${rfCrmHotel.id}">修改</a>--%>
					<%--<a href="${ctx}/crm/hotel/rfCrmHotel/delete?id=${rfCrmHotel.id}" onclick="return confirmx('确认要删除该酒店吗？', this.href)">删除</a>--%>
					<shiro:hasPermission name="hotel:rfCrmHotelPublic:return">
					<c:if test="${rfCrmHotel.isPrivate == '1'}">
						<a href="${ctx}/crm/hotel/rfCrmHotelPublic/returnPublic?hotelId=${rfCrmHotel.id}" onclick="return confirmx('确认要该酒店释放公海吗？', this.href)">释放公海</a>
					</c:if>
					</shiro:hasPermission>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>