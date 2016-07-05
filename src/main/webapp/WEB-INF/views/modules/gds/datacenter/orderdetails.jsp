<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>订单详情</title>
	<meta name="decorator" content="default"/>
	<%@include file="../layouts/importJs.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
            $(function ()
                    { $("[data-toggle='popover']").popover();
                    });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }	
		
		
	    function detailexportexcel() {
	    	var orderid = $("#orderid").val()
	  		window.location.href = "${ctx}/datacenter/orders/exportexcel?id=" + orderid;
	    }
	</script>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/datacenter/orders/list">分销订单查询列表</a></li>
		<li class="active"><a href="#">订单详情</a></li>
	</ul>
	<sys:message content="${message}"/>
		
	<form:form id="inputForm" modelAttribute="datacenterOrders"  class="breadcrumb form-search">
	<ul class="ul-form">
		<li><label>订单ID：</label>
            <form:input path="id" id="orderid" htmlEscape="false" maxlength="20" class="input-xlarge required digits" readonly="true" />
		</li>
		<li>
			<label >酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-xlarge required digits" readonly="true" />
		</li>
		<li>
			<label >酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label >酒店电话：</label>
				<form:input path="hotelphone" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
            <label>预抵时间：</label>
                <input name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                    value="<fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                     />
        </li>
        <li>
            <label  >预离时间：</label>
                <input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                    value="<fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                     />
        </li>
        <li>
            <label>总价格：</label>
                <form:input path="totalprice" htmlEscape="false" class="input-xlarge required" readonly="true" />
        </li>
        <li>
            <label>总房数：</label>
                <form:input path="totalroom" htmlEscape="false" maxlength="50" class="input-xlarge " readonly="true" />
        </li>
        <li>
            <label>联系人：</label>
                <form:input path="contacts" htmlEscape="false" maxlength="25" class="input-xlarge " readonly="true" />
        </li>
        <li>
            <label>联系电话：</label>
                <form:input path="contactsphone" htmlEscape="false" maxlength="25" class="input-xlarge " readonly="true" />
        </li>    
        <li>
            <label >渠道名称：</label>
                <form:input path="channelname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
        </li>
        <li>
			<label >老板：</label>
				<form:input path="bossname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label >老板电话：</label>
				<form:input path="bossphone" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li >
			<label >省：</label>
				<form:input path="provname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li >
			<label >市：</label>
				<form:input path="cityname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li >
			<label  >县：</label>
				<form:input path="disname" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label >支付类型：</label>
			<input  type="text"   value="${fns:getDictLabel(datacenterOrders.paytype, 'PayTypeEnum', '')}"  class="input-xlarge" readonly="true"/>				
		</li>

		<li>
			<label>订单状态：</label>
				<input type="text" value="${fns:getDictLabel(datacenterOrders.status, 'OrderStatusEnum', '')}" htmlEscape="false" maxlength="4" class="input-xlarge required" readonly="true" />
		</li>
		<li>
			<label>第三方id：</label>
				<form:input path="orderno" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label>pms订单号：</label>
				<form:input path="pmsorderid" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label>批量订单ID：</label>
				<form:input path="grouporderid" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label>订单类型：</label>
				<input type="text" value="${fns:getDictLabel(datacenterOrders.ordertype, 'OrderTypeEnum', '')}" htmlEscape="false" maxlength="4" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label>取消原因：</label>
				<input type="text" value="${fns:getDictLabel(datacenterOrders.cancelreason, 'OrderCancelreasonEnum', '')}" htmlEscape="false" maxlength="4" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label>创建时间：</label>
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${datacenterOrders.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					  />
		</li>
		<li>
			<label >修改时间：</label>
				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${datacenterOrders.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
		</li>
		<li>
			<label >销售：</label>
				<form:input path="salername" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true" />
		</li>
		<li>
			<label >销售电话：</label>
				<form:input path="salerphone"  maxlength="100" class="input-xlarge " readonly="true" />
		</div>
		<li>
			<label >结算状态：</label>
				<input type="text" value="${fns:getDictLabel(datacenterOrders.settlestatus, 'OrderSettleStatusEnum', '')}" htmlEscape="false" maxlength="4" class="input-xlarge " readonly="true" />
		</li>		
		<li>
			<label >备注：</label>
				<form:input path="note" htmlEscape="false" class="input-xlarge "  readonly="true"/>
		</li>
	</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>房型ID</th>
				<th>房型名称</th>
				<th>预订数量</th>
				<th>渠道总价</th>
                <th>分销总价</th>
				<th>预抵时间</th>
				<th>预离时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${otaorderdetails}" var="orderdetails">
			<tr>
				<td>
					${orderdetails.orderid}
				</td>				
				<td>
					${orderdetails.roomtypeid}
				</td>
				<td>${orderdetails.roomtypename }</td>
				<td>	${orderdetails.booknum}</td>
               <td>
                    <%--<button data-toggle="modal" data-target="#myModal" >--%>
                            <%--${orderdetails.totalOTAPrice}--%>
                    <%--</button>--%>
                <span name="otaPrice"
                      data-container="body" data-toggle="popover" data-placement="right" data-html="true"
                      data-trigger="hover" data-title="房间单价"
                      data-content='<c:forEach var="roomprice" items="${orderdetails.roompriceList}" >
                                    <c:if test="${roomprice.type == '1'}">
							           <div class="row" style="width:210px;">
					  						<div class="span6"><fmt:formatDate value="${roomprice.actiondate}" pattern="yyyy-MM-dd"/></div>
					  						<div class="span6"><span><h4>${roomprice.price}</h4></span></div>
					  				   </div>
					  			    </c:if>
			                       </c:forEach>'>${orderdetails.totalOTAPrice}</span>
                </td>
                <td>
                <span name="clearingPrice"
                      data-container="body" data-toggle="popover" data-placement="right" data-html="true"
                      data-trigger="hover" data-title="房间单价"
                      data-content='<c:forEach var="roomprice" items="${orderdetails.roompriceList}" >
                                    <c:if test="${roomprice.type != '1'}">
							           <div class="row" style="width:210px;">
					  						<div class="span6"><fmt:formatDate value="${roomprice.actiondate}" pattern="yyyy-MM-dd"/></div>
					  						<div class="span6"><span><h4>${roomprice.price}</h4></span></div>
					  				   </div>
					  			    </c:if>
			                       </c:forEach>'>${orderdetails.totalClearingPrice}</span></td>				
				<td>
                    <fmt:formatDate value="${datacenterOrders.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                    <fmt:formatDate value="${datacenterOrders.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<table id="log_table" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="15%">业务类型</th>
				<th width="15%">订单编号</th>
				<th width="15%">操作员</th>
				<th width="40%">内容</th>
				<th width="15%">操作时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${loglist}" var="log">
			<tr>
				<td>
					${log.bussinssType}
				</td>
				<td>
					${log.bussinessId}
				</td>
				<td>
					${log.operator}
				</td>
				<td>
					${log.content}
				</td>
				<td>
					<fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="form-actions">			
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		<input id="btnexport" class="btn" type="button" value="导出Excel" onclick="detailexportexcel()"/>
		<shiro:hasAnyRoles name="gds,dept">
				<c:if test="${datacenterOrders.refuseorder == 1}">
	        		<input id="btnSetHotelPaymentAmount" class="btn" type="button" value="设置酒店赔偿金额" />
	        	</c:if>
	        	<c:if test="${datacenterOrders.refuseorder == 1}">
	        		<input id="btnSetBossPaymentAmount" class="btn" type="button" value="设置渠道赔偿金额" />
	        	</c:if>			<script type="text/javascript">
	        	$("#btnSetHotelPaymentAmount").click(function(){
					setPaymentAmount("酒店");
				});
				$("#btnSetBossPaymentAmount").click(function(){
					setPaymentAmount("渠道");
				});
				function setPaymentAmount(objname){
	                top.$.jBox.open("iframe:${ctx}/datacenter/orders/setPaymentAmount/" + objname, "设置" + objname + "赔付金额", 500, 285,{
	                    buttons:{"确认":"ok", "关闭":true}, bottomText:"",submit:function(v, h, f){
	                        var payment_amount = h.find("iframe")[0].contentWindow.$("#payment_amount").val().trim();
	                        var note = h.find("iframe")[0].contentWindow.$("#note").val().trim();
	                        var orderid = $("#orderid").val();
	                        if (v=="ok"){
	                        	if(!h.find("iframe")[0].contentWindow.checkform()){
	                        		return false;
	                        	}
	                        	if (payment_amount == '' || !IsNum(payment_amount)) {
									alert("输入异常，赔付金额应为数字");
									return false;
								}
	                            var onOk = function(data, status, xhr) {
	                                if(data.result=='SUCCESS'){
	                                	var msg = data.message ? data.message : "设置赔付金额成功!";
	                                	layer.alert(msg, {icon: 1}, function(){
	                                		window.location.href ="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
	                                		});
	                                }else{
	                                    layer.msg(data.result, { icon : 2 });
	                                }
	                            }
	                            var onErr = function(data, status, xhr) {
	                                layer.msg('设置赔付金额失败', { icon : 2 });
	                            }            
	                            var param = { 'orderid' : orderid, 'payment_amount' : payment_amount, 'note': note, 'objname':objname};
	                            AjaxEx("json", "POST", "${ctx}/datacenter/orders/setpaymentamount", param, onOk, onErr, false, true);
	                            return true;
	                        } else if (v=="clear"){
	                            return false;
	                        }
	                    }, loaded:function(h){
	                        $(".jbox-content", top.document).css("overflow-y","hidden");
	                    }
	                });
	            };
				function IsNum(s) {
				    if (s!=null && s!="") {
				        return !isNaN(s);
				    }
				   	return false;
				}
		    </script>
		</shiro:hasAnyRoles>
	</div>
</body>
</html>