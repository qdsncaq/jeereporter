<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>分销订单查询管理</title>
<meta name="decorator" content="default" />
<%@include file="../layouts/importJs.jsp"%>
<script src="<%=path%>/static/adver/yanue.pop.js"></script>
<script src="<%=path%>/static/jPlayer/dist/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#order_jplayer").jPlayer({
			ready: function (event) {
				$(this).jPlayer("setMedia", {
					title: "Bubble",
					m4a: "<%=path%>/static/m4a/new_order_rem.m4a"
				});
			},
			swfPath: "<%=path%>/static/jPlayer/dist/jplayer",
			supplied: "m4a",
			wmode: "window",
			useStateClassSkin: true,
			autoBlur: false,
			smoothPlayBar: true,
			keyEnabled: true,
			remainingDuration: true,
			toggleDuration: true
		});
	});


	function page(n, s) {
		$("#searchForm").attr("action", ctx + "/datacenter/orders/cslist");
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function exportexcel() {
		$("#searchForm").attr("action", ctx + "/datacenter/orders/exportexcel");
		$("#searchForm").submit();
		return false;
	}
	function query() {
		$("#searchForm").attr("action", ctx + "/datacenter/orders/cslist");
		$("#searchForm").submit();
		return false;
	}
	
	function query_confim() {
		$("#status").find("option[value='0']").attr("selected",true);
		$("#searchForm").attr("action", ctx + "/datacenter/orders/cslist");
		$("#searchForm").submit();
		return false;
	}
	
	function serSubTree(treeId) {
		var url = ctx + "/crm/location/tCity/serSubTree";
		//重置select2控件的值
		var subIdSelect = $("#citycode");
		$("#citycode option:not(:first)").remove();
		subIdSelect.select2("val", "");
		//区县
		var thirdIdSelect = $("#discode");
		$("#discode option:not(:first)").remove();
		thirdIdSelect.select2("val", "");

		$.ajax({
					url : url,
					dataType : 'json',
					data : {
						proid : treeId
					},
					cache : false,
					async : true,
					success : function(data, textStatus) {
						var secTreeList = data;
						for ( var node in secTreeList) {
							var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"
									+ secTreeList[node].cityname + "</option>";
							subIdSelect.append(option);
						}
					}
				});
	}

	function serThirdTree(treeId) {
		var url = "${ctx}/crm/location/tDistrict/serSubTree";
		//重置select2控件的值
		var thirdIdSelect = $("#disCode");
		$("#disCode option:not(:first)").remove();
		thirdIdSelect.select2("val", "");

		$
				.ajax({
					url : url,
					dataType : 'json',
					data : {
						cityid : treeId
					},
					cache : false,
					async : true,
					success : function(data, textStatus) {
						var secTreeList = data;
						for ( var node in secTreeList) {
							var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].id+"'>"
									+ secTreeList[node].disname + "</option>";
							thirdIdSelect.append(option);
						}
					}
				});
	}
	function confirmorder(orderid) {
		layer.confirm(
						"确定吗？",
						function() {
							var onOk = function(data, status, xhr) {
								var fresult = data.result;
								if (fresult == 'SUCCESS') {
									layer.msg("确认订单成功!", {
										icon : 1
									});
									window.location.href = "${ctx}/datacenter/orders/cslist?status=0";
								} else {
									layer.msg(fresult, {
										icon : 2
									});
								}
							}
							var onErr = function(data, status, xhr) {
								layer.msg('确认订单失败', {
									icon : 2
								});
							}
							var param = {
								'orderid' : orderid
							};
							AjaxEx("json", "POST",
									"${ctx}/datacenter/orders/confirm", param,
									onOk, onErr, false, true);
						});
	}

	$(function(){
				var url = "${ctx}/datacenter/orders/neworder";
				setInterval(new_order, 10000);
				function new_order() {
					$.ajax({
						type: 'POST',
						url : url,
						cache : false,
						async : true,
						success : function(data, textStatus) {
							if(data > 0) {
								var pop_val = $("#pop").css("display");
								if(pop_val == "block") {
								     $('#pop').stop(true, true).fadeOut();
								}
								new Pop("", "", "您有新的待确认订单!");
								$("#order_jplayer").jPlayer("play");
							}
								
						}
					});
				}
			})
</script>
<style type="text/css">
	.table-td-nowrap{white-space: nowrap;}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/datacenter/orders/cslist">分销订单确认列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="datacenterOrders"
		action="${ctx}/datacenter/orders/cslist" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>订单号：</label> <form:input path="id" htmlEscape="false"
					maxlength="20" class="input-medium" /></li>
			<li><label>酒店名称：</label> <form:input path="hotelname"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>老板：</label> <form:input path="bossname"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>老板电话：</label> <form:input path="bossphone"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>订单状态：</label> <form:select path="status"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('OrderStatusEnum')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>支付状态：</label> <form:select path="paystatus"
												  class="input-medium">
				<form:option value="" label="全部" />
				<form:options items="${fns:getDictList('OrderPayStatusEnum')}"
							  itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select></li>
			<li><label>预订时间：</label> <input name="createtime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.createtime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				~
				<input name="createtime1" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.createtime1}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</li>
			<li><label>预抵时间：</label> <input name="begintime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				~
				<input name="begintime1" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.begintime1}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</li>
			<li><label>预离时间：</label> <input name="endtime" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				~
				<input name="endtime1" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${datacenterOrders.endtime1}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</li>
			<li><label>联系人：</label> <form:input path="contacts"
					htmlEscape="false" maxlength="25" class="input-medium" /></li>
			<li><label>联系电话：</label> <form:input path="contactsphone"
					htmlEscape="false" maxlength="25" class="input-medium" /></li>
			<li><label>第三方单号：</label> <form:input path="orderno"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>渠道名称：</label> <form:input path="channelname"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>销售：</label> <form:input path="salername"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>省：</label> <form:input path="provname"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>市：</label> <form:input path="cityname"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>到店无房：</label>
				<form:select path="refuseorder" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('RefuseOrderEnum')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
		</ul>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" onclick="query()"
				class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input id="btnSubmit" onclick="exportexcel()"
				class="btn btn-primary" type="submit" value="导出Excel" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-td-nowrap">
		<thead>
			<tr>
				<th>订单号</th>
				<th>酒店信息</th>
				<th>创建时间</th>
				<th>住宿时间</th>
				<th>订单价格</th>
				<th>房间总数</th>
				<th>状态</th>
				<th>支付状态</th>
				<th>联系人信息</th>
				<th>渠道信息</th>
				<th>酒店PMS接收</th>
				<shiro:hasAnyRoles name="cs,dept">
					<th>操作</th>
				</shiro:hasAnyRoles>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="datacenterOrders">
				<tr>
					<td><a
						href="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=${datacenterOrders.id}">
							${datacenterOrders.id} </a></td>
					<!-- <td>
					<a href="#" onclick="window.location.href='${ctx}/hotel/hotel/form?id=${datacenterOrders.hotelid}'">${datacenterOrders.hotelid}</a>
				</td> -->
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
						<fmt:formatDate value="${datacenterOrders.createtime}" pattern="yyyy-MM-dd HH:mm" />
					</td>
					<td>
						<fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd HH:mm" />
						<br>
						<fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd HH:mm" />
					</td>
					<td>${datacenterOrders.totalprice}</td>
					<td>${datacenterOrders.totalroom}</td>
					<td>
						${fns:getDictLabel(datacenterOrders.status, 'OrderStatusEnum', '')}
						<c:if test="${datacenterOrders.refuseorder==1}">
							(${fns:getDictLabel(datacenterOrders.refuseorder, 'RefuseOrderEnum', '')})
						</c:if>
					</td>
					<td>
						${fns:getDictLabel(datacenterOrders.paystatus, 'OrderPayStatusEnum', '')}
					</td>
					<td>${datacenterOrders.contacts}<br>${datacenterOrders.contactsphone}</td>
					<td>
						${datacenterOrders.channelname}
						<c:if test="${datacenterOrders.orderno!=null && datacenterOrders.orderno!=''}">
							<br>单号：${datacenterOrders.orderno}
						</c:if>
					</td>
					<td>
						<c:if test="${ datacenterOrders.pmsbosscomfirmorder == 100}">
							是
						</c:if>
						<c:if test="${ datacenterOrders.pmsbosscomfirmorder != 100}">
							<span style="color:#FF3939;">否</span>
						</c:if>
					<td>
						<a href="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=${datacenterOrders.id}">订单详情</a><br>
						<shiro:hasAnyRoles name="cs,dept,gds">
							<c:if test="${ datacenterOrders.status == 0 }">
								<a href="#" style="color:#20B863;" onclick="confirmorder('${datacenterOrders.id}')">确认订单</a><br>
							</c:if>
							<c:if test="${ datacenterOrders.status < 20 }">
								<a href="#" id="cancel_order" style="color:#FF3939;" onclick="cancelOrder('${datacenterOrders.id}')">取消订单</a><br>
							</c:if>
						</shiro:hasAnyRoles>
						<shiro:hasPermission name="hotel:hotelOpensetting:view">
							<a href="${ctx}/hotel/hotelOpensetting/form?hotelid=${datacenterOrders.hotelid}&page_type=order">关闭房间</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			<script type="text/javascript">
				function cancelOrder(orderid) {
					top.$.jBox
							.open(
									"iframe:${ctx}/datacenter/orders/cancel",
									"取消订单",
									670,
									231,
									{
										buttons : {
											"确认取消" : "ok",
											"关闭" : true
										},
										bottomText : "取消订单操作,需要填写操作原因",
										submit : function(v, h, f) {
											var content = h.find("iframe")[0].contentWindow.$("#desc").val();
											if (v == "ok") {
												if(!h.find("iframe")[0].contentWindow.checkForm()){
					                        		return false;
					                        	}
												var onOk = function(data,
														status, xhr) {
													var fresult = data.result;
													if (fresult == 'SUCCESS') {
														layer.msg("取消订单成功!", {
															icon : 1
														});
														window.location.href = "${ctx}/datacenter/orders/cslist";
													} else {
														layer.msg(fresult, {
															icon : 2
														});
													}
												}
												var onErr = function(data,
														status, xhr) {
													layer.msg('取消订单失败', {
														icon : 2
													});
												}
												var param = {
													'orderid' : orderid,
													'content' : content
												};
												AjaxEx(
														"json",
														"POST",
														"${ctx}/datacenter/orders/refuse",
														param, onOk, onErr,
														false, true);
												return true;
											} else if (v == "clear") {
												return false;
											}
										},
										loaded : function(h) {
											$(".jbox-content", top.document)
													.css("overflow-y", "hidden");
										}
									});
				}
			</script>



		</tbody>
	</table>

	<div id="pop" style="display: none;">
		<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#pop {
	background: #fff;
	width: 260px;
	border: 1px solid #e0e0e0;
	font-size: 12px;
	position: fixed;
	right: 10px;
	bottom: 10px;
}

#popHead {
	line-height: 32px;
	background: #f6f0f3;
	border-bottom: 1px solid #e0e0e0;
	position: relative;
	font-size: 12px;
	padding: 0 0 0 10px;
}

#popHead h2 {
	font-size: 12px;
	color: #666;
	line-height: 32px;
	height: 32px;
}

#popHead #popClose {
	position: absolute;
	right: 10px;
	top: 1px;
}

#popHead a#popClose:hover {
	color: #f00;
	cursor: pointer;
}

#popContent {
	padding: 5px 10px;
}

#popTitle a {
	line-height: 24px;
	font-size: 14px;
	font-family: '微软雅黑';
	color: #333;
	font-weight: bold;
	text-decoration: none;
}

#popTitle a:hover {
	color: #f60;
}

#popIntro {
	text-indent: 24px;
	line-height: 160%;
	margin: 5px 0;
	color: #666;
}

#popMore {
	text-align: right;
	border-top: 1px dotted #ccc;
	line-height: 24px;
	margin: 8px 0 0 0;
}

#popMore a {
	color: #f60;
}

#popMore a:hover {
	color: #f00;
}
</style>
		<div id="popHead">
			<a id="popClose" title="关闭">关闭</a>
			<h2>温馨提示</h2>
		</div>
		<div id="popContent">
			<div id="order_jplayer"></div>
			<dl>
				<dt id="popTitle">
					<a href="" target="_blank"></a>
				</dt>
				<dd id="popIntro"></dd>
			</dl>
			<p id="popMore">
				<a href="#" onclick="query_confim()">查看 »</a>
			</p>
		</div>
	</div>
	<div class="pagination">${page}</div>

	<shiro:lacksPermission name="datacenter:order:view">您没有操作本页面的权限.</shiro:lacksPermission>
</body>

</html>