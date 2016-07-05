<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbhotelbill管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		// 导出 
        function exportexcel(){
            $("#searchForm").attr("action","${ctx}/psbhotelbill/settlementPsbhotelbillinit/exportexcel");
            $("#searchForm").submit();
            return false;
        }
		
		// 查询
        function query(){
            $("#searchForm").attr("action","${ctx}/psbhotelbill/settlementPsbhotelbillinit/list");
            $("#searchForm").submit();
            return false;
        }
        
		function serSubTree(treeId){
			// alert(treeId); return false;
			//  var url = "${ctx}/crm/location/tCity/serSubTree";
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
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/psbhotelbill/settlementPsbhotelbillinit/list");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/psbhotelbill/settlementPsbhotelbillinit/list">账单明细列表</a></li>
		<%-- <shiro:hasPermission name="psbhotelbill:settlementPsbhotelbillinit:edit"><li><a href="${ctx}/psbhotelbill/settlementPsbhotelbillinit/form">psbhotelbill添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementPsbhotelbillinit" action="${ctx}/psbhotelbill/settlementPsbhotelbillinit/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<form:select path="citycode" class="input-medium">
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
					</c:forEach>
				</form:select>
			</li>
			<li><label>开始日期：</label>
				<input name="beginBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.beginBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBegintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.endBegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> <span style="color: red">* 结算开始日期</span>
			</li> 
			<li><label>截止日期：</label>
				<input name="beginEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.beginEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endEndtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.endEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> <span style="color: red">* 结算截止日期(+360天 日期不变)</span>
			</li> 
			<li><label>结算日期：</label>
				<input name="beginRealendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.beginRealendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endRealendtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.endRealendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> <span style="color: red">* 实际截止日期(计算下线天数推迟)</span>
			</li> 
			<li><label>创建时间：</label>
				<input name="beginCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.beginCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementPsbhotelbillinit.endCreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return page();" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<!-- <th>PSB编码</th> -->
				<th>PSB厂商</th>
				<th>酒店ID</th>
				<!-- <th>PMS编码</th> -->
				<th>酒店名称</th>
				<!-- <th>省编码</th> -->
				<th>省名称</th>
				<!-- <th>市编码</th> -->
				<th>市名称</th>
				<!-- <th>区编码</th>
				<th>区名称</th> -->
				<th>房间数</th>
				<!-- <th>当前酒店HMS在线状态(1,在线/2,离线)</th> -->
				<th>在线状态</th>
				<th>区域工程师</th>
				<th>装机工程师</th>
				<th>开始日期</th>
				<th>截止日期</th>
				<th>结算日期</th>
				<th>上线时间</th>
				<th>下线时间</th>
				<th>本年年费</th>
				<th>科目</th>
				<!-- <th>HMS有效天数（账期已统计天数）</th> -->
				<th>有效天数</th>
				<!-- <th>结算金额(预付给psb金额)</th> -->
				<th>结算金额</th>
				<th>应收金额</th>
				<th>创建时间</th>
				<!-- <th>更新时间</th> -->
				<%-- <shiro:hasPermission name="psbhotelbill:settlementPsbhotelbillinit:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementPsbhotelbillinit">
			<tr>
				<%-- <td><a href="${ctx}/psbhotelbill/settlementPsbhotelbillinit/form?id=${settlementPsbhotelbillinit.id}">
					${settlementPsbhotelbillinit.id}
				</a></td> --%>
				<td>
					${settlementPsbhotelbillinit.id}
				</td>
				<%-- <td>
					${settlementPsbhotelbillinit.psbid}
				</td> --%>
				<td>
					${settlementPsbhotelbillinit.psbname}
				</td>
				<td>
					${settlementPsbhotelbillinit.hotelid}
				</td>
				<%-- <td>
					${settlementPsbhotelbillinit.hotelpms}
				</td> --%>
				<td>
					${settlementPsbhotelbillinit.hotelname}
				</td>
				<%-- <td>
					${settlementPsbhotelbillinit.procode}
				</td> --%>
				<td>
					${settlementPsbhotelbillinit.proname}
				</td>
				<%-- <td>
					${settlementPsbhotelbillinit.citycode}
				</td> --%>
				<td>
					${settlementPsbhotelbillinit.cityname}
				</td>
				<%-- <td>
					${settlementPsbhotelbillinit.discode}
				</td>
				<td>
					${settlementPsbhotelbillinit.disname}
				</td> --%>
				<td>
					${settlementPsbhotelbillinit.roomnums}
				</td>
				<td>
					${fns:getDictLabel(settlementPsbhotelbillinit.onlinestate, 'HotelOnlineState', '')}
				</td>
				<td>
					${settlementPsbhotelbillinit.areamanager}
				</td>
				<td>
					${settlementPsbhotelbillinit.operator}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.realendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.onlinetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.offlinetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${settlementPsbhotelbillinit.fee}
				</td>
				<td>
					${settlementPsbhotelbillinit.feetypeids}
				</td>
				<td>
					${settlementPsbhotelbillinit.onlinedays}
				</td>
				<td>
					${settlementPsbhotelbillinit.amount}
				</td>
				<td>
					${settlementPsbhotelbillinit.recoveramount}
				</td>
				<td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${settlementPsbhotelbillinit.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<%-- <shiro:hasPermission name="psbhotelbill:settlementPsbhotelbillinit:edit"><td>
    				<a href="${ctx}/psbhotelbill/settlementPsbhotelbillinit/form?id=${settlementPsbhotelbillinit.id}">修改</a>
					<a href="${ctx}/psbhotelbill/settlementPsbhotelbillinit/delete?id=${settlementPsbhotelbillinit.id}" onclick="return confirmx('确认要删除该psbhotelbill吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>