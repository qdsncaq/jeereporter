<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>分销订单查询管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="../layouts/importJs.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#searchForm").attr("action", ctx + "/datacenter/orders/list");
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function exportexcel(){
			$("#searchForm").attr("action", ctx + "/datacenter/orders/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		function query(){
			$("#searchForm").attr("action", ctx + "/datacenter/orders/list");
			$("#searchForm").submit();
			return false;
        }
		function serSubTree(treeId){
			var url = ctx+"/crm/location/tCity/serSubTree";
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
<style type="text/css">
	.table-td-nowrap{white-space: nowrap;}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/datacenter/orders/list">分销订单列表</a></li>		
	</ul>
	<form:form id="searchForm" modelAttribute="datacenterOrders" action="${ctx}/datacenter/orders/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			 <li><label>订单号：</label>
				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium" />
			</li> 
			<li><label>酒店号：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>	
			<li><label>老板：</label>
				<form:input path="bossname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>	
			<li><label>老板电话：</label>
				<form:input path="bossphone" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>					
			<li><label>支付类型：</label>
			<form:select path="paytype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('PayTypeEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			<li><label>订单状态：</label>
			<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('OrderStatusEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</li>	
			<li><label>预抵时间：</label>
				<input name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				~
				<input name="begintime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.begintime1}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>预离时间：</label>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd" />"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					~
				<input name="endtime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.endtime1}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>联系人：</label>
				<form:input path="contacts" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="contactsphone" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>	
			<li>
				<label>第三方单号：</label> <form:input path="orderno" htmlEscape="false" maxlength="100" class="input-medium" />
			</li>		
			<li><label>渠道名称：</label>
				<form:input path="channelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>			
			<li><label>订单类型：</label>
				<form:select path="ordertype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('OrderTypeEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>			
			</li>
			<li><label>预订时间：</label>
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.createtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					~
				<input name="createtime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${datacenterOrders.createtime1}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>销售：</label>
				<form:input path="salername" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>省：</label>
				<form:input path="provname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>		
			<li><label>市：</label>
				<form:input path="cityname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>到店无房：</label>
				<form:select path="refuseorder" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('RefuseOrderEnum')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			</ul>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" onclick="query()" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit"  onclick="exportexcel()" class="btn btn-primary" type="submit" value="导出Excel"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-td-nowrap">
		<thead>
			<tr>
				<th>订单ID</th>
				<th>酒店id</th>
				<th>酒店信息</th>
				<th>渠道信息</th>
				<th>预订时间</th>
				<th>状态</th>
				<th>总房数</th>
				<th>订单价格</th>				
				<th>住宿时间</th>
				<th>联系人信息</th>
				<th>销售信息</th>
				<th>酒店PMS接收</th>
				<shiro:hasAnyRoles name="cs,dept"><th>操作</th></shiro:hasAnyRoles>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="datacenterOrders">
			<tr>
				<td>
				<a href="${ctx}/datacenter/otaOrderdetail/listorderid?orderid=${datacenterOrders.id}" >										
					${datacenterOrders.id}
				</a>
				</td>
				<td>
					<a href="#" onclick="window.location.href='${ctx}/hotel/hotel/form?id=${datacenterOrders.hotelid}'">${datacenterOrders.hotelid}</a>
				</td>
				<td>
					<a href="javascript:void(0)" onclick="top.addTabPage('酒店信息', '${ctx}/hotel/hotel/form?id=${datacenterOrders.hotelid}')" jerichotabindex="11">
							${datacenterOrders.hotelname}
					</a>
					<c:if test="${datacenterOrders.hotelphone!=null && datacenterOrders.hotelphone!=''}">
						<br>前台tel：${datacenterOrders.hotelphone}
					</c:if>
					<c:if test="${datacenterOrders.bossphone!=null && datacenterOrders.bossphone!=''}">
						<br>老板tel：${datacenterOrders.bossphone}
					</c:if>
				</td>
				<td>
					${datacenterOrders.channelname}
					<c:if test="${datacenterOrders.orderno!=null && datacenterOrders.orderno!=''}">
						<br>单号：${datacenterOrders.orderno}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${datacenterOrders.createtime}" pattern="yyyy-MM-dd HH:mm" />
				</td>
				<td>
					${fns:getDictLabel(datacenterOrders.status, 'OrderStatusEnum', '')}
					<c:if test="${datacenterOrders.refuseorder==1}">
						(${fns:getDictLabel(datacenterOrders.refuseorder, 'RefuseOrderEnum', '')})
					</c:if>
				</td>
				<td>${datacenterOrders.totalroom}</td>	
					<td>
					${datacenterOrders.totalprice}
				</td>				
				<td>
					<fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd HH:mm" />
					<br>
					<fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd HH:mm" />
				</td>
				<td>${datacenterOrders.contacts}<br>${datacenterOrders.contactsphone}</td>
				<td>
					${datacenterOrders.salername}
					<c:if test="${datacenterOrders.salerphone!=null && datacenterOrders.salerphone!=''}">
						<br>销售电话：${datacenterOrders.salerphone}
					</c:if>
				</td>
				<td>
					<c:if test="${ datacenterOrders.pmsbosscomfirmorder == 100}">
						是
					</c:if>
					<c:if test="${ datacenterOrders.pmsbosscomfirmorder != 100}">
						<span style="color:#FF3939;">否</span>
					</c:if>
				</td>
				<td>		
					<a href="${ctx}/datacenter/otaOrderdetail/listorderid?orderid=${datacenterOrders.id}" >订单详情</a><br>
					<a href="${ctx}/hotel/hotelOpensetting/form?hotelid=${datacenterOrders.hotelid}&page_type=order_query" style="color:#FF3939;">关闭房间</a>
	            </td>
			</tr>
		</c:forEach>		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<shiro:lacksPermission name="datacenter:order:view">您没有操作本页面的权限.</shiro:lacksPermission>
</body>    
</html>