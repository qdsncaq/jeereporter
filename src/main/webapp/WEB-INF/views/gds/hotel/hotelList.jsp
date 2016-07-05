<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var ctx = "${ctx}";
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/hotel/hotel/");
			$("#searchForm").submit();
        	return false;
        }
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/hotel/hotel/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		function query(){
			$("#searchForm").attr("action",ctx+"/hotel/hotel/");
			$("#searchForm").submit();
			return false;
        }
		function queryforReturn(){
			var ids = $("#ids").val();
			var salesName = $("#salesName").val();
			if(ids.length==0 && salesName.length==0){
				alert("请输入酒店ID(多个ID用,号分割)或销售名字");
				return ;
			}
			$("#searchForm").attr("action",ctx+"/hotel/hotel/queryforReturn");
			$("#searchForm").submit();
			return false;
        }
		function assignHotelToSeller(){
			$("#searchForm").attr("action",ctx+"/hotel/hotel/assignHotelToSeller");
			$("#searchForm").submit();
			return false;
        }
		
		function queryforTransforSeller(){
			$("#searchForm").attr("action",ctx+"/hotel/hotel/queryforTransforSeller");
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
	<style type="text/css">
		#contentTable .title-th-align{text-align: center;vertical-align:middle;!important;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotel/hotel/">酒店查询</a></li>
		<shiro:hasPermission name="hotel:hotel:edit"><li><a href="${ctx}/hotel/hotel/form">添加酒店</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hotel" action="${ctx}/hotel/hotel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店ID：</label>
				<form:input path="ids" htmlEscape="false" class="input-medium"/>
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
			<li><label>销售姓名：</label>
				<form:input path="salesName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>销售电话：</label>
				<form:input path="salesMobile" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>安装人手机：</label>
				<form:input path="pmsUser" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>安装人名称：</label>
				<form:input path="pmsUserName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>pms编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
				<%--<li><label>联系人：</label>--%>
				<%--<form:input path="hotelcontactname" htmlEscape="false" maxlength="25" class="input-medium"/>--%>
				<%--</li>--%>
				<%--<li><label>联系电话：</label>--%>
				<%--<form:input path="hotelphone" htmlEscape="false" maxlength="20" class="input-medium"/>--%>
				<%--</li>--%>
				<%--<li><label>省：</label>--%>
				<%--<form:select path="provcode" class="input-medium">--%>
				<%--<form:option value="" label="全部"/>--%>
				<%--<form:options items="${provinces}" itemLabel="name" itemValue="code" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--</li>--%>
				<%--<li><label>市：</label> --%>
				<%--<form:select path="citycode" class="input-medium">--%>
				<%--<form:option value="" label="全部"/>--%>
				<%--<form:options items="${citys}" itemLabel="name" itemValue="code" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--</li>--%>
			<li class="clearfix"></li>
			<li><label>房间数：</label>
				<form:input path="roomcountbegin" htmlEscape="false" maxlength="3" class="input-medium" />
				——
				<form:input path="roomcountend" htmlEscape="false" maxlength="3" class="input-medium" />
			</li>
			<li><label>公私海：</label>
				<form:select path="isPrivate" class="input-medium">
					<form:option value="" label="选择公私海"/>
					<form:option value="1" label="私海"/>
					<form:option value="2" label="公海"/>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li><label>是否分销：</label>
					<%-- 				<form:input path="switchOpen" htmlEscape="false" maxlength="10" class="input-medium"/> --%>
				<form:select path="switchOpen" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('BusinessEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>分销时间：</label>
				<input name="switchcreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${hotel.switchcreatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> -
				<input name="switchcreatetimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${hotel.switchcreatetimeEnd}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="clearfix"></li>
            <li><label>是否上线：</label>
                    <%-- 				<form:input path="switchOpen" htmlEscape="false" maxlength="10" class="input-medium"/> --%>
                <form:select path="isvisible" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('yesOrNo')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
			<li><label>上线时间：</label>
				<input name="startOnLineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${hotel.startOnLineTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> -
				<input name="endOnlineTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${hotel.endOnlineTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>是否自建：</label>
				<form:select path="isSelfBuildHotel" class="input-medium">
					<form:option value="" label="--全部--"/>
					<form:option value="T" label="是"/>
					<form:option value="F" label="否"/>
				</form:select>
			</li>
            <%--<li><label>是否在线：</label>--%>
                    <%--&lt;%&ndash; 				<form:input path="switchOpen" htmlEscape="false" maxlength="10" class="input-medium"/> &ndash;%&gt;--%>
                <%--<form:select path="isonline" class="input-medium">--%>
                    <%--<form:option value="" label="全部"/>--%>
                    <%--<form:options items="${fns:getDictList('yesOrNo')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                <%--</form:select>--%>
            <%--</li>--%>
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
			<li class="btns"><input onclick="query()" id="btnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			
			<shiro:hasPermission name="hotel:hotel:return">
				<li class="btns">
					  <input onclick="queryforReturn()" id="btnReturnBack" class="btn btn-primary" type="button" value="退回公海"/>
			   </li>
		   </shiro:hasPermission>
		   
			<%-- <form id="transferForm" action="${ctx}/hotel/hotel/transferToOtherSeller" method="post" class="hide">
				<input id="code" type="hidden" name="code" value=""/>
			</form> --%>
			<shiro:hasPermission name="hotel:hotel:transfer">
				<li class="btns">
						<input id="btnTransferToSeller" class="btn btn-primary" type="button" value="私海酒店转到其它销售"/> 
				</li>
      	    </shiro:hasPermission>
			
			 
			<script type="text/javascript">
			$("#btnTransferToSeller").click(function(){
				var ids = $("#ids").val();
				var salesName = $("#salesName").val();
				if(ids.length==0 && salesName.length==0){
					alert("请输入酒店ID(多个ID用,号分割)或销售名字");
					return ;
				}
				top.$.jBox.open("iframe:${ctx}/hotel/hotel/transferToSeller", "酒店批量转销售",810,$(top.document).height()-240,{
					buttons:{"确定转":"ok", "关闭":true}, bottomText:"通过选择酒店转到新的销售。",submit:function(v, h, f){
						var code = h.find("iframe")[0].contentWindow.code;
						//var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						//alert("user_code=1111=="+code);
						if (v=="ok"){
							// 删除''的元素
							/*
							if(ids[0]==''){
								ids.shift();
								pre_ids.shift();
							}
							if(pre_ids.sort().toString() == ids.sort().toString()){
								top.$.jBox.tip("未给销售【${role.name}】分配新酒店！", 'info');
								return false;
							};*/

					    	// 执行保存
					    	//alert("---user_code==="+code);
					    	//loading('正在提交，请稍等...');
					    	var url = "${ctx}/hotel/hotel/transferToOtherSeller?code="+ code+"&ids="+ids+"&salesName="+salesName;
					    	jQuery.post(url
									, function(data) {
					    		
					    	});
					    	//$('#code').val(code);
					    	//$('#transferForm').submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr style="line-height: 25px;">
				<th style="width: 2%;" class="title-th-align">酒店id</th>
				<th class="title-th-align">酒店<br>名称</th>
				<th class="title-th-align">公私海</th>
				<th class="title-th-align">房间数</th>
				<th class="title-th-align">销售</th>
				<th class="title-th-align">老板</th>
				<th class="title-th-align">前台电话</th>
				<th class="title-th-align">是否开<br>通分销</th>
				<th class="title-th-align">详细地址</th>
				<th class="title-th-align">注册<br>时间</th>
				<th class="title-th-align">上线<br>时间</th>
				<th class="title-th-align">开业<br>时间</th>
				<th class="title-th-align">最后<br>装修</th>
				<th class="title-th-align">pms</th>
				<th class="title-th-align">pms安装人</th>
				<th class="title-th-align">审核状态</th>
				<th class="title-th-align">是否自建</th>
				<th class="title-th-align"><shiro:hasPermission name="hotelbusiness:hotelbusiness:operate">操作</shiro:hasPermission></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hotel">
			<tr>
				<td><a href="${ctx}/hotel/hotel/form?id=${hotel.id}">
					${hotel.id}
				</a></td>
				<td><a href="${ctx}/hotel/hotel/form?id=${hotel.id}">
					${hotel.hotelname}
				</a></td>
				<td>
					${hotel.isPrivateName}
				</td>
				<td>
						${hotel.roomnum}
				</td>
				<td>
						${hotel.salesName}<br>
						${hotel.salesMobile}<br>
						<fmt:formatDate value="${hotel.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${hotel.bossName}
					<br>${hotel.bossMobile}
				</td>
				<td>
					<br>${hotel.qtphone}
				</td>
				<td>
				   <%--  <c:choose>
				       <c:when test="${hotel.switchOpen == null || hotel.switchOpen=='F'}">
				           <b style="color:red">未开通</b>
				       </c:when>
				       <c:otherwise>
				          <b style="color:green">已开通</b>
				       </c:otherwise>
				    </c:choose> --%>
				    ${fns:getDictLabel(hotel.switchOpen, 'BusinessEnums', '')}<br>
				   <c:if test="${hotel.switchOpen != null && hotel.switchOpen !=''}">
					   <fmt:formatDate value="${hotel.switchcreatetime}" pattern="yyyy-MM-dd"/>
				   </c:if>
				</td>
				<td>
						${hotel.detailaddr}
				</td>
				<td>
					<fmt:formatDate value="${hotel.regtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${hotel.onLineTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${hotel.opentime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${hotel.repairtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${hotel.hotelpms}
				</td>
				<td>
						${hotel.pmsUserName}<br>
						${hotel.pmsUser}
				</td>
				<td>
						${hotel.statusName}
				</td>
				<td>
						${hotel.isSelfBuildHotelName}
				</td>
				<td style="line-height: 25px;">
					<shiro:hasPermission name="hotelbusiness:hotelbusiness:operate">
						<c:choose>
						   <c:when test="${hotel.switchOpen != null && hotel.switchOpen !='' && hotel.switchOpen !='10'}">
							   <a href="${ctx}/hotel/hotel/closeDistribution?id=${hotel.id}" onclick="return confirmx('确认要关闭分销吗？', this.href)">关闭分销</a>
						   </c:when>
						   <c:otherwise>
							  <a href="${ctx}/hotel/hotel/openDistribution?id=${hotel.id}" onclick="return confirmx('确认要开通分销吗？', this.href)">开通分销</a>
						   </c:otherwise>
						</c:choose>
					</shiro:hasPermission>
					<br>
					<shiro:hasPermission name="hotel:rfCrmHotelPublic:return">
						<c:if test="${hotel.isPrivate == '1'}">
							<a href="${ctx}/crm/hotel/rfCrmHotelPublic/returnPublic?hotelId=${hotel.id}" onclick="return confirmx('确认要该酒店释放公海吗？', this.href)">释放公海</a>
						</c:if>
					</shiro:hasPermission>
					<shiro:hasPermission name="hotel:hotelOpensetting:view">
						<a href="${ctx}/hotel/hotelOpensetting/form?hotelid=${hotel.id}&page_type=hotel">关闭房间</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
