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
            
            $("textarea[maxlength]").keyup(function(){ 
            	var area=$(this); 
            	var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值 
            	if(max>0){ 
	            	if(area.val().length>=max){ //textarea的文本长度大于maxlength 
	            		//area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
	            		layer.tips('备注不能超过1000', '#cs_remark');
	            	} 
            	} 
            	});
		});   
            
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
        function saveCSRemarks(){    
            var remark = $.trim($("#cs_remark").val());
               var orderid = $.trim($("#orderid").val());
               if(remark==null||remark==""){
                    layer.msg("客服备注不能为空", {
                       icon : 2
                   }); 
                    return false;
               }              
           	if( remark.length > 1000 ) {
           		layer.tips('备注不能超过1000', '#cs_remark');
	            return;
           	}
                   var onOk = function(data, status, xhr) {
                       var fresult = data.result;
                       if(fresult=='SUCCESS'){
                           layer.msg("添加客服备注成功!", {
                               icon : 1
                           }); 
                           window.location.href ="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid="+orderid;
                       }else{
                           /* layer.msg(fresult, {
                               icon : 2
                           }); */
                       }
                   }
                   var onErr = function(data, status, xhr) {
                   
                   }                
                   var param = {
                           'orderid' :orderid,
                            'remark' :remark
                           };
                   AjaxEx("json", "POST", ctx+"/orderdetail/orderdetails/savecsremark", param, onOk, onErr,
                           false, true); 
       }
        
       function detailexportexcel() {
    	  var orderid = $("#orderid").val()
 		  window.location.href = "${ctx}/datacenter/orders/exportexcel?id=" + orderid;
       }
        
	</script>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/datacenter/orders/cslist">分销订单查询列表</a></li>
		<li class="active"><a href="#">订单详情</a></li>
	</ul>
	<%--<sys:message content="${message}"/>--%>
		
	<form:form id="inputForm" modelAttribute="datacenterOrders"  class="breadcrumb form-search">
        <sys:message content="${message}"/>
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
            <label >自建酒店：</label>
            <input type="text" value="${fns:getDictLabel(datacenterOrders.hotelSelfBuild, 'HotelSelfBuildLabel', '')}" htmlEscape="false" maxlength="4" class="input-xlarge required" readonly="true" />
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
			<label >支付状态：</label>
			<input  type="text"   value="${fns:getDictLabel(datacenterOrders.paystatus, 'OrderPayStatusEnum', '')}"  class="input-xlarge" readonly="true"/>
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
			<label>取消方式：</label>
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
		</li>
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
                <%--${orderdetails.totalClearingPrice}</td>--%>
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
				<th width="22%">操作员</th>
				<th width="33%">内容</th>
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
<shiro:hasAnyRoles name="cs,dept">
    <table id="log_table" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>备注</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td >                
                    <textarea  id="cs_remark"   rows=""  cols=""  style="width:700px;height: 70px;" maxlength="1000"   ></textarea>
                        
                    <input  class="btn" type="button" value="保存" onclick="saveCSRemarks()"/>
                </td>
            </tr>
        </tbody>
    </table>
</shiro:hasAnyRoles>
	<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			<input id="btnexport" class="btn" type="button" value="导出Excel" onclick="detailexportexcel()"/>
			<c:if test="${datacenterOrders.status >= 20}">
	        	<input id="no_room" class="btn" type="button" value="到店无房" />
	        </c:if>
	        <script type="text/javascript">
	            $("#no_room").click(function(){
	                top.$.jBox.open("iframe:${ctx}/datacenter/orders/cancel", "到店无房",670, 241, {
	                    buttons:{"确认":"ok", "关闭":true}, bottomText:"到店无房投诉,需要填写操作原因",submit:function(v, h, f){
	                        var content = h.find("iframe")[0].contentWindow.$("#desc").val();
	                        var orderid = $("#orderid").val();
	                        if (v=="ok"){
	                         	if( content.length > 1000 ) {
	                           		layer.tips('原因不能超过1000', '#cs_remark');
	                	            return;
	                           	}          
	                                var onOk = function(data, status, xhr) {
	                                    var fresult = data.result;
	                                    if(fresult=='SUCCESS'){
	                                        layer.msg("投诉订单成功!", {
	                                            icon : 1
	                                        });
	                                        window.location.href ="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
	                                    }else{
	                                        layer.msg(fresult, {
	                                            icon : 2
	                                        });
	                                    }
	                                }
	                                var onErr = function(data, status, xhr) {
	                                    layer.msg('投诉订单失败', {
	                                        icon : 2
	                                    });
	                                }            
	                                var param = {
	                                        'orderid' : orderid,
	                                        'content' : content
	                                        };
	                                AjaxEx("json", "POST", "${ctx}/datacenter/orders/noroom", param, onOk, onErr,
	                                        false, true);
	                                return true;    
	                        } else if (v=="clear"){
	                            return false;
	                        }
	                    }, loaded:function(h){
	                        $(".jbox-content", top.document).css("overflow-y","hidden");
	                    }
	                });
	            });
	        </script>
			<shiro:hasAnyRoles name="cs,dept,gds">
	        <c:if test="${ datacenterOrders.status == 0 }">
            	<input id="confirm_order" class="btn" type="button" value="确认订单" />
            </c:if>
            <c:if test="${ datacenterOrders.status < 20 }">
            	<input id="cancel_order" class="btn" type="button" value="取消订单" />
            </c:if>
            </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="cs,dept">
	        	<c:if test="${datacenterOrders.status == 20}">
	        	<input id="order_in" class="btn" type="button" value="订单入住" />
	        	</c:if>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="cs,dept">
	        	<c:if test="${datacenterOrders.status >= 40 and  datacenterOrders.status < 100}">
	        	<input id="order_ok" class="btn" type="button" value="订单完成" />
	        	</c:if>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="cs,dept">
	        	<c:if test="${datacenterOrders.status == 20}">
	        	<input id="order_noshow" class="btn" type="button" value="未到店" />
	        	</c:if>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="gds,dept">
	        	<c:if test="${datacenterOrders.refuseorder.intValue() == 1}">
	        	<input id="btnSetHotelPaymentAmount" class="btn" type="button" value="设置酒店赔偿金额" />
	        	</c:if>
	        </shiro:hasAnyRoles>
	        <shiro:hasAnyRoles name="gds,dept">
	        	<c:if test="${datacenterOrders.refuseorder == 1}">
	        	<input id="btnSetBossPaymentAmount" class="btn" type="button" value="设置渠道赔偿金额" />
	        	</c:if>
	        </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="gds,dept">
                    <c:if test="${datacenterOrders.hotelSelfBuild.equalsIgnoreCase('T') and datacenterOrders.ordertype.equalsIgnoreCase('1') and datacenterOrders.status < 20}">
                        <input id="btnSetDiscount" class="btn" type="button" value="设置订单优惠金额"
                               onclick="setDisCountFrame('设置订单优惠金额', '优惠金额',
                                       '${ctx}/datacenter/orders/setDiscount?orderId=' + ${datacenterOrders.id} +
                                       '&totalPrice=' + ${datacenterOrders.totalprice} +
                                       '&oldDiscount=' + ${datacenterOrders.discount} + '&discount=')"/>
                    </c:if>
                </shiro:hasAnyRoles>
            <script type="text/javascript">
            $("#confirm_order").click(function(){    
                layer.confirm("确定吗？", function() {
                    var orderid = $("#orderid").val();
                    var onOk = function(data, status, xhr) {
                        var fresult = data.result;
                        if(fresult=='SUCCESS'){
                            layer.msg("确认订单成功!", {
                                icon : 1
                            });
                            window.location.href ="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
                        }else{
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
                    AjaxEx("json", "POST", "${ctx}/datacenter/orders/confirm", param, onOk, onErr,
                            false, true);
                });
            });
            
            $("#cancel_order").click(function(){
                top.$.jBox.open("iframe:${ctx}/datacenter/orders/cancel", "取消订单", 670, 241,{
                    buttons:{"确认取消":"ok", "关闭":true}, bottomText:"取消订单操作,需要填写操作原因",submit:function(v, h, f){
                        var content = h.find("iframe")[0].contentWindow.$("#desc").val().trim();
                        var orderid = $("#orderid").val();
                        if (v=="ok"){
                        	if(!h.find("iframe")[0].contentWindow.checkForm()){
                        		return false;
                        	}
                           	if( content.length > 1000 ) {
                           		layer.tips('原因不能超过1000', '#cs_remark');
                	            return false;
                           	}                        	
                                var onOk = function(data, status, xhr) {
                                    var fresult = data.result;
                                    if(fresult=='SUCCESS'){
                                        layer.msg("取消订单成功!", {
                                            icon : 1
                                        });
                                        window.location.href ="${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
                                    }else{
                                        layer.msg(fresult, {
                                            icon : 2
                                        });
                                    }
                                }
                                var onErr = function(data, status, xhr) {
                                    layer.msg('取消订单失败', {
                                        icon : 2
                                    });
                                }            
                                var param = {
                                        'orderid' : orderid,
                                        'content' : content
                                        };
                                AjaxEx("json", "POST", "${ctx}/datacenter/orders/refuse", param, onOk, onErr,
                                        false, true);
                                return true;
                        } else if (v=="clear"){
                            return false;
                        }
                    }, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
            });
            
            $("#order_in").click(function() {
                layer.confirm("设置订单入住吗？", function() {
                    var orderid = $("#orderid").val();
                    var onOk = function(data, status, xhr) {
                        var fresult = data.result;
                        if (fresult == 'SUCCESS') {
                            layer.msg("订单入住成功!", {
                                icon: 1
                            });
                            window.location.href = "${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
                        } else {
                            layer.msg(fresult, {
                                icon: 2
                            });
                        }
                    }
                    var onErr = function(data, status, xhr) {
                        layer.msg('订单入住失败', {
                            icon: 2
                        });
                    }
                    var param = {
                        'orderid': orderid
                    };
                    AjaxEx("json", "POST", "${ctx}/datacenter/orders/orderin", param, onOk, onErr, false, true);
                });
            });
            $("#order_ok").click(function() {
                layer.confirm("设置订单完成吗？", function() {
                    var orderid = $("#orderid").val();
                    var onOk = function(data, status, xhr) {
                        var fresult = data.result;
                        if (fresult == 'SUCCESS') {
                            layer.msg("订单入住成功!", {
                                icon: 1
                            });
                            window.location.href = "${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
                        } else {
                            layer.msg(fresult, {
                                icon: 2
                            });
                        }
                    }
                    var onErr = function(data, status, xhr) {
                        layer.msg('设置订单完成失败', {
                            icon: 2
                        });
                    }
                    var param = {
                        'orderid': orderid
                    };
                    AjaxEx("json", "POST", "${ctx}/datacenter/orders/orderok", param, onOk, onErr, false, true);
                });
            });
            $("#order_noshow").click(function() {
                layer.confirm("设置订单为未到吗？", function() {
                    var orderid = $("#orderid").val();
                    var onOk = function(data, status, xhr) {
                        var fresult = data.result;
                        if (fresult == 'SUCCESS') {
                            layer.msg("订单设置未到成功!", {
                                icon: 1
                            });
                            window.location.href = "${ctx}/datacenter/otaOrderdetail/cslistorderid?orderid=" + orderid;
                        } else {
                            layer.msg(fresult, {
                                icon: 2
                            });
                        }
                    }
                    var onErr = function(data, status, xhr) {
                        layer.msg('设置订单设置未到失败', {
                            icon: 2
                        });
                    }
                    var param = {
                        'orderid': orderid
                    };
                    AjaxEx("json", "POST", "${ctx}/datacenter/orders/ordernoshow", param, onOk, onErr, false, true);
                });
            });
			$("#btnSetHotelPaymentAmount").click(function(){
				setPaymentAmount("酒店");
			});
			$("#btnSetBossPaymentAmount").click(function(){
				setPaymentAmount("渠道");
			});

            function setDisCountFrame(title, lable, href, closed){
                top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>" + lable + "：<input type='text' id='txt' name='txt'/></div>", {
                    buttons:{"确认":"ok", "关闭":true}, title: title, submit: function (v, h, f){
                        if (v == 'ok') {
                            if (f.txt == '') {
                                top.$.jBox.tip("请输入" + lable + "。", 'error');
                                return false;
                            }

                            if (!IsNum(f.txt)) {
                                top.$.jBox.tip("优惠金额应为数字", 'error');
                                return false;
                            }

                            if (typeof href == 'function') {
                                href();
                            }else{
                                resetTip(); //loading();
                                location = href + encodeURIComponent(f.txt);
                            }
                        }
                    },closed:function(){
                        if (typeof closed == 'function') {
                            closed();
                        }
                    }});
                return false;
            }

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
                                } else {
                                    layer.alert(data.result, { icon : 2 });
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

	</div>
</body>
</html>