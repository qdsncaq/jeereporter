<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自建酒店审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出自建酒店查询数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/crm/hotel/eHotel/exportSelfBuildHotels");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
            $("#searchForm").attr("action","${ctx}/crm/hotel/eHotel/selfBuildHotelList");
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
                        var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
                        subIdSelect.append(option);
                    }
                }
            });
        }

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
	<form:form id="searchForm" modelAttribute="EHotel" action="${ctx}/crm/hotel/eHotel/selfBuildHotelList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <form:input path="isSelfBuildHotel" type="hidden" value="T"/>
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
            <li class="clearfix"></li>
            <li><label>审核状态：</label>
                <form:select path="checkState" class="input-medium">
                    <form:option value="" label="--全部--"/>
                    <form:options items="${fns:getDictList('hotelCheckState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>销售：</label>
                <form:input path="userName" htmlEscape="false" maxlength="10" class="input-medium"/>
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
                <th>省>市>区/县</th>
				<th>酒店名称</th>
                <th>酒店地址</th>
                <th>前台电话</th>
                <th>负责人</th>
                <th>销售</th>
                <th>审核状态</th>
				<shiro:hasPermission name="hotel:eHotel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="eHotel">
			<tr>
				<td><a href="${ctx}/crm/hotel/eHotel/selfBuildHotelform?id=${eHotel.id}">
					${eHotel.id}
				</a></td>
                <td>
                        ${eHotel.provName}<br/>
                        ${eHotel.cityName}<br/>
                        ${eHotel.disName}
                </td>
				<td>
					${eHotel.hotelname}
				</td>
                <td>
                        ${eHotel.detailaddr}
                </td>
                <td>
                        ${eHotel.qtphone}
                </td>

                <td>
                        ${eHotel.bossName}<br/>
                        ${eHotel.bossPhone}
                </td>
                <td>
                        ${eHotel.userName}<br/>
                        ${eHotel.userMobile}
                </td>
                <td>
                    ${fns:getDictLabel(eHotel.checkState, 'hotelCheckState', '')}
                </td>
				<shiro:hasPermission name="hotel:eHotel:edit"><td>
                    <c:if test="${eHotel.checkState == 1}">
                        <a href="${ctx}/crm/hotel/eHotel/checkSelfBuildHotel?id=${eHotel.id}" onclick="return confirmx('确认要审核该酒店吗？', this.href)">审核</a>
                        <a href="${ctx}/crm/hotel/eHotel/rejectSelfBuildHotel?id=${eHotel.id}&reason=" onclick="return promptx('驳回申请', '驳回原因', this.href)">驳回</a>
                    </c:if>
                </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>