<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbrulesrelation管理</title>
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
			$("#searchForm").attr("action","${ctx}/psbrulesrelation/settlementPsbrulesrelation/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		// 查询
		function query(){
			$("#searchForm").attr("action","${ctx}/psbrulesrelation/settlementPsbrulesrelation/list");
			$("#searchForm").submit();
        	return false;
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

		// 批量删除
		function deletelist(){
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
	   			$.post('${ctx }/psbrulesrelation/settlementPsbrulesrelation/deletelist',
						{ids : selectedIds},
						function(result){
							alertx(result);
							window.location.reload(100000);return false;//刷新当前页面.return false;
						}
				);
	   		}
		}
		
		// 批量修改
		function updatelist(){
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
	   			
	   		}
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/psbrulesrelation/settlementPsbrulesrelation/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/list">规则配置列表</a></li>
		<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><li><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/form">酒店规则配置</a></li></shiro:hasPermission>
		<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><li><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/disform">区域规则配置</a></li></shiro:hasPermission>
		<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><li><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/cityform">城市规则配置</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPsbrulesrelation" action="${ctx}/psbrulesrelation/settlementPsbrulesrelation/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>快速查询：</label>
				<form:radiobuttons path="areatype" items="${fns:getDictList('psbruleareaEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="clearfix"></li>
			<%-- <li><label>自增主键：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li> --%>
			<li><label>PSB厂商：</label>
				<%-- <form:input path="psbid" htmlEscape="false" maxlength="11" class="input-medium"/> --%>
				<form:select path="psbid" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.id } : ${psbinfo.psbname }" />
					</c:forEach>
				</form:select>
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
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<%-- <li><label>PMS编码：</label>
				<form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<%-- <li><label>城市对应规则：</label>
				<form:checkboxes path="cityrules" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>酒店对应规则：</label>
				<form:checkboxes path="hotelrules" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li><label>生效时间：</label>
				<input name="beginBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.beginBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.endBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>失效时间：</label>
				<input name="beginEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.beginEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.endEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>是否删除：</label>
				<form:select path="delflag" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('psbdelflag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcel()" value="导出excel"/></li>
			<!-- <li class="btns"><input class="btn btn-primary" type="button" onclick="updatelist()" value="批量修改"/></li> -->
			<li class="btns"><input class="btn btn-primary" type="button" onclick="deletelist()" value="批量删除"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
				<!-- <th>自增主键</th> -->
				<th>酒店ID</th>
				<!-- <th>PMS编码</th> -->
				<th>酒店名称</th>
				<th>PSB厂商</th>
				<!-- <th>省编码</th> -->
				<th>省名称</th>
				<!-- <th>市编码</th> -->
				<th>市名称</th>
				<!-- <th>区编码</th> -->
				<th>区名称</th>
				<th>城市规则</th>
				<th>区域规则</th>
				<th>酒店规则</th>
				<th style="width: 10.5%;">规则描述</th>
				<th>生效时间</th>
				<th>失效时间</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPsbrulesrelation">
			<tr>
				<%-- <td><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/form?id=${settlementPsbrulesrelation.id}">
					${settlementPsbrulesrelation.id}
				</a></td> --%>
				<td>
                   <input id="checkboxId" type="checkbox" name="check" value="${settlementPsbrulesrelation.id}" /> 
                </td>
				<td>
					${settlementPsbrulesrelation.hotelid}
				</td>
				<%-- <td>
					${settlementPsbrulesrelation.hotelpms}
				</td> --%>
				<td>
					${settlementPsbrulesrelation.hotelname}
				</td>
				<td>
					${settlementPsbrulesrelation.psbid} : ${settlementPsbrulesrelation.psbname }
				</td>
				<%-- <td>
					${settlementPsbrulesrelation.procode}
				</td> --%>
				<td>
					${settlementPsbrulesrelation.proname}
				</td>
				<%-- <td>
					${settlementPsbrulesrelation.citycode}
				</td> --%>
				<td>
					${settlementPsbrulesrelation.cityname}
				</td>
				<%-- <td>
					${settlementPsbrulesrelation.discode}
				</td> --%>
				<td>
					${settlementPsbrulesrelation.disname}
				</td>
				<td>
					<%-- ${fns:getDictLabel(settlementPsbrulesrelation.cityrules, '', '')} --%>
					${settlementPsbrulesrelation.cityrules}
				</td>
				<td>
					<%-- ${fns:getDictLabel(settlementPsbrulesrelation.cityrules, '', '')} --%>
					${settlementPsbrulesrelation.disrules}
				</td>
				<td>
					<%-- ${fns:getDictLabel(settlementPsbrulesrelation.hotelrules, '', '')} --%>
					${settlementPsbrulesrelation.hotelrules}
				</td>
				<td>
					${settlementPsbrulesrelation.ruledesc }
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrulesrelation.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrulesrelation.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrulesrelation.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbrulesrelation.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><td>
					<c:if test="${settlementPsbrulesrelation.delflag == 1 }">
						<c:if test="${settlementPsbrulesrelation.areatype == 3 }" >
		    				<a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/form?id=${settlementPsbrulesrelation.id}">修改</a>
						</c:if>
						<c:if test="${settlementPsbrulesrelation.areatype == 2 }">
							<a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/disform?id=${settlementPsbrulesrelation.id}">修改</a>
						</c:if>
						<c:if test="${settlementPsbrulesrelation.areatype == 1 }">
							<a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/cityform?id=${settlementPsbrulesrelation.id}">修改</a>
						</c:if>
						<a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/delete?id=${settlementPsbrulesrelation.id}" onclick="return confirmx('确认要删除该规则映射信息吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${settlementPsbrulesrelation.delflag == 2 }">
						已删除
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
