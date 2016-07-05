<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拜访记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出拜访记录吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/crm/visit/rfCrmVisitRecord/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/crm/visit/rfCrmVisitRecord/list");
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

        function showSalers() {
            top.$.jBox.open("iframe:${ctx}/crm/visit/rfCrmVisitRecord/allAreas?code=" + $("#visitUser").val(), "选择销售",810,$(top.document).height()-240,{
                buttons:{"确定选择":"ok", "清除已选":"clear", "关闭":true}, bottomText:"通过选择省市区，然后选择其下的销售。",submit:function(v, h, f){
                    var selectUsers = h.find("iframe")[0].contentWindow.selectUsers;
                    var selectUsersName = h.find("iframe")[0].contentWindow.selectUsersName;
                    //nodes = selectedTree.getSelectedNodes();
                    if (v=="ok"){
                        // 执行保存
//                        loading('正在提交，请稍等...');
                        var visitUser = "";
                        var visitUserName = "";
                        for (var i = 0; i<selectUsers.length; i++) {
                            visitUser = (visitUser + selectUsers[i]) + (((i + 1)== selectUsers.length) ? '':',');
                            visitUserName = (visitUserName + selectUsersName[i]) + (((i + 1)== selectUsersName.length) ? '':',');
                        }
                        $('#visitUser').val(visitUser);
                        $('#visitUserName').val(visitUserName);
                        return true;
                    } else if (v=="clear"){
                        h.find("iframe")[0].contentWindow.clearAssign();
                        return false;
                    }
                }, loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/crm/visit/rfCrmVisitRecord/list?queryVisitType=${param.queryVisitType}">拜访记录列表</a></li>
		<%--<shiro:hasPermission name="visit:rfCrmVisitRecord:edit"><li><a href="${ctx}/visit/rfCrmVisitRecord/form">拜访记录添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="rfCrmVisitRecord" action="${ctx}/crm/visit/rfCrmVisitRecord/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="queryVisitType" />
		<ul class="ul-form">
			<%--<li><label>拜访酒店ID：</label>--%>
				<%--<form:input path="visitHotelId" htmlEscape="false" maxlength="20" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>拜访日期：</label>
				<input name="visitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${rfCrmVisitRecord.visitTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				-
				<input name="visitTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${rfCrmVisitRecord.visitTimeEnd}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="visitHotelName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>销售：</label>
                <form:hidden id="visitUser" path="visitUser" htmlEscape="false" class="input-medium" />
				<form:input id="visitUserName" path="visitUserName" htmlEscape="false" class="input-medium" readonly="true"
                        onclick="showSalers();" />
			</li>
		<c:if test="${empty param.queryVisitType}">
			<li><label>拜访类型：</label>
				<form:select path="visitType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${visitTypeList}" itemLabel="valueLabel" itemValue="keyLabel" htmlEscape="false"/>
				</form:select>
			</li>
		</c:if>
			<li class="clearfix"></li>
			<%--<li><label>采购意向：</label>--%>
				<%--<form:input path="purchaseIntention" htmlEscape="false" maxlength="10" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>拜访方式：</label>
				<form:select path="visitWay" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${visitWayList}" itemLabel="valueLabel" itemValue="keyLabel" htmlEscape="false"/>
				</form:select>
			</li>
		<c:if test="${'marketing' == param.queryVisitType || empty param.queryVisitType}">
			<li><label>样品发放：</label>
				<form:select path="sampleGrant" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${visitSampleGrantList}" itemLabel="valueLabel" itemValue="keyLabel" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>采购意向：</label>
				<form:select path="purchaseIntention" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="T" label="有意向"/>
					<form:option value="F" label="无意向"/>
				</form:select>
			</li>
		</c:if>

		<c:if test="${'switch' == param.queryVisitType  || empty param.queryVisitType}">
			<li><label>是否签约：</label>
				<form:select path="isSign" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="T" label="是"/>
					<form:option value="F" label="否"/>
				</form:select>
			</li>
		</c:if>
			<c:if test="${'wash' == param.queryVisitType || empty param.queryVisitType}">
				<%--布草洗涤签约部分--%>
				<li><label>洗涤意向：</label>
					<form:select path="washIntention" class="input-medium">
						<form:option value="" label="--请选择--"/>
						<form:option value="T" label="有意向"/>
						<form:option value="F" label="无意向"/>
					</form:select>
				</li>
				<li><label>洗涤签约：</label>
					<form:select path="washSign" class="input-medium">
						<form:option value="" label="--请选择--"/>
						<form:option value="T" label="是"/>
						<form:option value="F" label="否"/>
					</form:select>
				</li>
				<li><label>签约日期：</label>
					<input name="washSignDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						   value="<fmt:formatDate value="${rfCrmVisitRecord.washSignDate}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					-
					<input name="washSignDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						   value="<fmt:formatDate value="${rfCrmVisitRecord.washSignDateEnd}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</li>
			</c:if>
				<li class="clearfix"></li>
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
				<th style="width: 130px;">拜访日期</th>
				<th style="width: 5%;">省>市>区/县</th>
				<%--<th style="width: 5%;">市</th>--%>
				<%--<th style="width: 5%;">区县</th>--%>
				<th style="width: 10%;">酒店名称</th>
				<th style="width: 5%;">拜访方式</th>
				<th style="width: 5%;">联系人</th>
			<c:if test="${empty param.queryVisitType}">
				<th style="width: 6%;">拜访类型</th>
			</c:if>
				<%--<th>拜访酒店意向[1-合作意向]</th>--%>
				<th style="width: 5%;">销售</th>
			<c:if test="${'switch' == param.queryVisitType || empty param.queryVisitType}">
				<th style="width: 5%;">是否签约</th>
				<th style="width: 5%;">签约失败原因</th>
			</c:if>
			<c:if test="${'wash' == param.queryVisitType || empty param.queryVisitType}">
				<%--布草洗涤签约部分--%>
				<th style="width: 5%;">签约意向</th>
				<th style="width: 5%;">是否签约</th>
				<th style="width: 5%;">洗涤签约日期</th>
			</c:if>
			<c:if test="${'marketing' == param.queryVisitType || empty param.queryVisitType}">
				<th>采购意向</th>
				<th style="width: 8%;">样品发放</th>
				<th>牙具</th>
				<th>沐浴露</th>
				<th>香皂</th>
				<th>洗发液</th>
				<th>梳子</th>
				<th>水</th>
				<th>纸</th>
				<th>垃圾袋</th>
			</c:if>
				<th>拜访反馈</th>
				<th>拜访地址</th>
				<th>创建时间</th>
				<shiro:hasPermission name="visit:rfCrmVisitRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rfCrmVisitRecord">
			<tr>
				<%--<td><a href="${ctx}/crm/visit/rfCrmVisitRecord/form?id=${rfCrmVisitRecord.id}">--%>
					<%--${rfCrmVisitRecord.id}--%>
				<%--</a></td>--%>
				<td>
						${rfCrmVisitRecord.visitHotelId}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmVisitRecord.visitTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${rfCrmVisitRecord.provName}<br>
						${rfCrmVisitRecord.cityName}<br>
						${rfCrmVisitRecord.disName}
				</td>
				<%--<td>--%>
						<%--${rfCrmVisitRecord.cityName}--%>
				<%--</td>--%>
				<%--<td>--%>
						<%--${rfCrmVisitRecord.disName}--%>
				<%--</td>--%>
				<td>
					${rfCrmVisitRecord.visitHotelName}
				</td>
				<td>
					${rfCrmVisitRecord.visitWayName}
				</td>
				<td>
					${rfCrmVisitRecord.hotelContacts}
				</td>
                    <c:if test="${empty param.queryVisitType}">
                        <td>
                                ${rfCrmVisitRecord.visitTypeName}
                        </td>
                    </c:if>
				<%--<td>--%>
					<%--${rfCrmVisitRecord.visitHotelIntention}--%>
				<%--</td>--%>
				<td>
					<%--${rfCrmVisitRecord.visitUser}--%>
					${rfCrmVisitRecord.visitUserName}
				</td>
			<c:if test="${'switch' == param.queryVisitType || empty param.queryVisitType}">
				<td>
					${rfCrmVisitRecord.isSignName}
				</td>
				<td>
					${rfCrmVisitRecord.signFailure}
				</td>
			</c:if>
			<c:if test="${'wash' == param.queryVisitType || empty param.queryVisitType}">
				<td>
						${rfCrmVisitRecord.washIntentionName}
				</td>
				<td>
						${rfCrmVisitRecord.washSignName}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmVisitRecord.washSignDate}" pattern="yyyy-MM-dd"/>
				</td>
			</c:if>
			<c:if test="${'marketing' == param.queryVisitType || empty param.queryVisitType}">
				<td>
					<%--${rfCrmVisitRecord.purchaseIntention}--%>
					${rfCrmVisitRecord.purchaseIntentionName}
				</td>
				<td>
					<%--${rfCrmVisitRecord.sampleGrant}--%>
					${rfCrmVisitRecord.sampleGrantName}
				</td>
				<td>
						${rfCrmVisitRecord.yaju}
				</td>
				<td>
						${rfCrmVisitRecord.muyulu}
				</td>
				<td>
						${rfCrmVisitRecord.xiangzao}
				</td>
				<td>
						${rfCrmVisitRecord.xifaye}
				</td>
				<td>
						${rfCrmVisitRecord.shuzi}
				</td>
				<td>
						${rfCrmVisitRecord.shui}
				</td>
				<td>
						${rfCrmVisitRecord.zhi}
				</td>
				<td>
						${rfCrmVisitRecord.lajidai}
				</td>
			</c:if>
				<td>
						${rfCrmVisitRecord.visitDesc}
				</td>
				<td>
						${rfCrmVisitRecord.lbsAddress}
				</td>
				<td>
					<fmt:formatDate value="${rfCrmVisitRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="visit:rfCrmVisitRecord:edit"><td>
    				<a href="${ctx}/crm/visit/rfCrmVisitRecord/view?id=${rfCrmVisitRecord.id}&queryVisitType=${param.queryVisitType}">查看</a>
    				<%--<a href="${ctx}/crm/visit/rfCrmVisitRecord/form?id=${rfCrmVisitRecord.id}">修改</a>--%>
					<%--<a href="${ctx}/crm/visit/rfCrmVisitRecord/delete?id=${rfCrmVisitRecord.id}" onclick="return confirmx('确认要删除该拜访记录吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>