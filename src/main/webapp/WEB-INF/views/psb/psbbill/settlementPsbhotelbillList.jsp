<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbbill管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var all_checked = false;
			   $(":checkbox").click(function() {
			       var table = $(this).parents("table");
			        //如果点击了全选的，那么下面都选或者不选
			       if($(this).attr("id") === "all") {
			           table.find(":checkbox").prop("checked", !all_checked);
			           all_checked = !all_checked;
			       } else {
			           table.find(":checkbox[id!=all]").each(function (i) {
			               if(!$(this).is(":checked")) {
			                   table.find("#all").prop("checked", false);
			                   all_checked = false;
			                   return false;
			               }
			               $("#all").prop("checked", true);
			               all_checked = true;
			           });
			      }
			});
		});
		
		// 导出 
        function exportexcel(){
            $("#searchForm").attr("action","${ctx}/psbbill/settlementPsbhotelbill/exportexcel");
            $("#searchForm").submit();
            return false;
        }
		
		// 查询
        function query(){
            $("#searchForm").attr("action","${ctx}/psbbill/settlementPsbhotelbill/list");
            $("#searchForm").submit();
            return false;
        }
		
		// 已结算
		function settlebill(){
			var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		// alertx(selectedIds);return false;
	   		if(selectedIds==""){
	   			alertx("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			$.post('${ctx }/psbbill/settlementPsbhotelbill/settlebill',
						{ids : selectedIds},
						function(result){
							alertx(result);
							window.location.reload(100000);return false;//刷新当前页面.return false;
						}
				);
	   		}
		}
		
		// 本期不结算
		function nobill(){
			var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		// alertx(selectedIds);return false;
	   		if(selectedIds==""){
	   			alertx("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			$.post('${ctx }/psbbill/settlementPsbhotelbill/nobill',
						{ids : selectedIds},
						function(result){
							alertx(result);
							window.location.reload(100000);return false;//刷新当前页面.return false;
						}
				);
	   		}
		}
		
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
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/psbbill/settlementPsbhotelbill/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/psbbill/settlementPsbhotelbill/">结算账单列表</a></li>
		<shiro:hasPermission name="psbbill:settlementPsbhotelbill:edit"><li><a href="${ctx}/psbbill/settlementPsbhotelbill/form">psbbill添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPsbhotelbill" action="${ctx}/psbbill/settlementPsbhotelbill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>PSB编码：</label>
				<%-- <form:input path="psbid" htmlEscape="false" maxlength="11" class="input-medium"/> --%>
				<form:select path="psbid" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.id } : ${psbinfo.psbname }" />
					</c:forEach>
				</form:select>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
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
			<li><label style="width: 120px;">账单开始日期：</label>
				<input name="beginBillstarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.beginBillstarttime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> - 
				<input name="endBillstarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.endBillstarttime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/>
			</li> 
			<li><label style="width: 120px;">账单截止日期：</label>
				<input name="beginBillendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.beginBillendtime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> - 
				<input name="endBillendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.endBillendtime}" pattern="yyyy-MM-dd 00:00:00"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/>
			</li> 
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbill.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>账单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('psbbillstatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>在线状态：</label>
				<form:select path="onlinestate" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('HotelOnlineState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>账单类型：</label>
				<form:select path="billtype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('psbbilltype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="btns"><input onclick="settlebill()" class="btn btn-primary" type="button" value="已结算"/></li>
			<li class="btns"><input onclick="nobill()" class="btn btn-primary" type="button" value="本期不结算"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
				<th>编号</th>
				<!-- <th>PSB编码</th> -->
				<th>PSB名称</th>
				<th>酒店ID</th>
				<!-- <th>PMS编码</th> -->
				<th>酒店名称</th>
				<th>省/市/区县</th>
				<th>房间数</th>
				<!-- <th>当前酒店HMS在线状态(1,在线/2,离线)</th> -->
				<th>在线状态</th>
				<th>上线开始日期</th>
				<th>360天截止日期</th>
				<th>实际结算日期</th>
				<th>记录上线时间</th>
				<th>记录下线时间</th>
				<th>本年年费</th>
				<th>科目</th>
				<!-- <th>HMS有效天数（账期已统计天数）</th> -->
				<th>有效天数</th>
				<!-- <th>结算金额(预付给psb金额)</th> -->
				<th>结算金额</th>
				<th>应收金额</th>
				<th>账单类型</th>
				<th>账单状态</th>
				<th>账单开始日期</th>
				<th>账单截止日期</th>
				<th>账单创建时间</th>
				<%-- <shiro:hasPermission name="psbbill:settlementPsbhotelbill:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPsbhotelbill">
			<tr>
				<%-- <td><a href="${ctx}/psbbill/settlementPsbhotelbill/form?id=${settlementPsbhotelbill.id}">
					${settlementPsbhotelbill.id}
				</a></td> --%>
				<td>
                   <input id="checkboxId" type="checkbox" name="check" value="${settlementPsbhotelbill.id}" /> 
                </td>
				<td>
					${settlementPsbhotelbill.id}
				</td>
				<td>
					${settlementPsbhotelbill.psbname}
				</td>
				<td>
					${settlementPsbhotelbill.hotelid}
				</td>
				<td>
					${settlementPsbhotelbill.hotelname}
				</td>
				<td>
					${settlementPsbhotelbill.proname}<br>
					${settlementPsbhotelbill.cityname}<br>
					${settlementPsbhotelbill.disname}
				</td>
				<td>
					${settlementPsbhotelbill.roomnums}
				</td>
				<td>
					${fns:getDictLabel(settlementPsbhotelbill.onlinestate, 'HotelOnlineState', '')}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.realendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.onlinetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.offlinetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementPsbhotelbill.fee}
				</td>
				<td>
					${settlementPsbhotelbill.feetypeids}
				</td>
				<td>
					${settlementPsbhotelbill.onlinedays}
				</td>
				<td>
					${settlementPsbhotelbill.amount}
				</td>
				<td>
					${settlementPsbhotelbill.recoveramount}
				</td>
				<td>
					${settlementPsbhotelbill.billtypedesc}
				</td>
				<td>
					${fns:getDictLabel(settlementPsbhotelbill.status, 'psbbillstatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.billstarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.billendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbill.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="psbbill:settlementPsbhotelbill:edit"><td>
    				<a href="${ctx}/psbbill/settlementPsbhotelbill/form?id=${settlementPsbhotelbill.id}">修改</a>
					<a href="${ctx}/psbbill/settlementPsbhotelbill/delete?id=${settlementPsbhotelbill.id}" onclick="return confirmx('确认要删除该psbbill吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>