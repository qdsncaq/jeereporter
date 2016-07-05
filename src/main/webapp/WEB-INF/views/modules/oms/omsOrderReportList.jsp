<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OMS订单业务报表</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	
	#s2id_worker .select2-search-choice{height: 17px;}
	
	</style>

	<script type="text/javascript">
	 
	   
	   $(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/reportexport");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/reportList");
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		 $.ajax({
                    url: "${ctx}/oms/omsMarketInfo/marketSelect",
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
						if(data!=null){
							   var control = $('#shopid');
							var shopid="${omsOrderReport.shopid}";
							$.each(data, function(i, item){  
								control.append('<option  value="'+item.id+'">'+item.value+'</option>');
							}); 
						}
						$("#shopid").select2('val',shopid);
                    },
                    error: function () {
                        alert("");
                    }
                });
		 $(function(){
			 
			 var sta="${omsOrderReport.status}";
			 if(sta!=null&&sta!=""){
				 var status=sta.split(',');
				 $("#worker").select2().val(status).trigger("change");
			 }
		 })
		
		  
	</script>
</head>
<body>
	<%--<ul class="nav nav-tabs">--%>
		<%--<li class="active"><a href="${ctx}/oms/omsOrder/">OMS订单列表</a></li>--%>
		<%--<shiro:hasPermission name="oms:omsOrder:edit"><li><a href="${ctx}/oms/omsOrder/form">OMS订单添加</a></li></shiro:hasPermission>--%>
	<%--</ul>--%>
	<form:form id="searchForm" modelAttribute="omsOrderReport" action="${ctx}/oms/omsOrder/reportList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店ID：</label>
				<form:input path="hmsid" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelName" htmlEscape="false"  class="input-medium"/>
			</li>
			<li><label>销售名称：</label>
				<form:input path="marketName" htmlEscape="false"  class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>商铺名称：</label>
			<select id="shopid" class="input-medium" name="shopid" >
			<option selected="selected" value="">--请选择--</option>
			</select>
			
			</li>
			<li><label>订单ID：</label>
				<form:input path="searchId" htmlEscape="false"  class="input-medium"/>
			</li>
			<li><label>有赞订单号：</label>
				<form:input path="yzOrderid" htmlEscape="false"  class="input-medium"/>
			</li>
			
			<li class="clearfix"></li>
			<li><label>收货省份：</label>
				<form:input path="receiverState" htmlEscape="false"  class="input-medium"/>
			</li>
			<li><label>收货城市：</label>
				<form:input path="receiverCity" htmlEscape="false"  class="input-medium"/>
			</li>
			<li><label>收货区/县：</label>
				<form:input path="receiverDistrict" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			
			<!-- class="input-medium" multiple="true"  -->
			<li><label>订单状态：</label>
				<form:select path="status"  class="input-medium" id="worker"  style="width:600px"  multiple="multiple"  >
					<form:options items="${fns:getDictList('orderStatus')}" itemLabel="label" itemValue="value" />
				</form:select> 
			</li>
			<li class="clearfix"></li>
			<li><label>创建时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>修改时间：</label>
				<input name="startUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${omsOrderReport.startUpdateTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${omsOrderReport.endUpdateTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			
			
			<li class="clearfix"></li>
			<li><label>商品类型：</label>
				<form:input path="comType" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>支付状态：</label>
				<form:select path="payStatus" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('oms_payStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
            
			
			<li><label>支付时间：</label> 
				<input name="startPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.startPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.endPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			
			<li><label>预发货时间：</label> 
				<input name=startInvoiceTime type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.startInvoiceTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> -
				<input name="endInvoiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderReport.endInvoiceTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<!--  --> 
	<table id="contentTable"  class="table table-striped table-bordered table-condensed" style="overflow-x:auto">
		<thead>
			<tr>
			<th>订单id</th>
			<th>有赞订单号</th>
			<th>创建时间</th>
			<th>修改时间</th>
			<th>订单状态</th>
			<th>支付状态</th>
			<th>支付时间</th>
			
			<th>收货人</th>
			<th>收货人电话</th>
			<th>邮编</th>
			<th>用户留言</th>
			<th>发货时间</th>
			<th>预发货时间</th>
			<th>收货省份</th>
			<th>收货城市</th>
			<th>收货区县</th>
			<th>收货地址</th>
			
			<th>商品单价</th>
			<th>件数</th>
			<th>商品金额</th>
			<th>订单金额</th>
			<th>SKU编码</th>
			<th>商品类别</th>
			<th>商品名称</th>
			<th>买家会员名</th>
			
			
			<th>酒店id</th>
			<th>酒店名称</th>
			<th>用户id</th>
			<th>用户名</th>
			<th>销售名称</th>
			<th>销售电话</th>
			<th>店铺名称</th>
			<!-- 
                <th  style="width:500px ">商品名称</th>
                <th  style="width:200px ">成交品类</th>
                <th  style="width:200px ">收货城市</th>
                <th  style="width:200px ">区县</th>
                <th  style="width:200px ">收货地址</th>
                <th  style="width:200px ">联系电话</th>
                <th  style="width:200px ">订单状态</th>
                <th  style="width:200px ">支付状态</th>
                <th  style="width:200px ">支付备注</th>
                <th  style="width:200px ">成交数量</th>
                <th  style="width:200px ">成交金额</th>
				<th  style="width:200px ">创建时间</th>
				<th  style="width:200px ">修改时间</th>
                <th  style="width:200px ">酒店id</th>
				<th  style="width:200px ">酒店</th>
                <th  style="width:200px ">有赞订单id</th>
                <th  style="width:200px ">销售</th>
                 -->
				<%--<shiro:hasPermission name="oms:omsOrder:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody style="overflow-y:auto;height: 50px; ">
		<c:forEach items="${page.list}" var="omsOrder">
			<tr>
			
			<td><a href="${ctx}/oms/omsOrder/form?orderId=${omsOrder.orderId}">
                    ${omsOrder.orderId}
                </a></td>
			<td>${omsOrder.yzOrderid}</td>
			<td>${omsOrder.sAtime}</td>
			<td>${omsOrder.sMtime}</td>
			<td>${fns:getDictLabel(omsOrder.status, 'orderStatus', '')}</td>
			<td>
			${fns:getDictLabel(omsOrder.payStatus, 'oms_payStatus', '')}</td>
			<td>${omsOrder.payMemo}</td>
			
			<td>${omsOrder.receiverName}</td>
			<td>${omsOrder.receiverMobile}</td>
			<td>${omsOrder.receiverZip}</td>
			<td>${omsOrder.buyerMessage}</td>
			<td>${omsOrder.consignTime}</td>
			<td>${omsOrder.invoiceTime}</td>
			<td> ${omsOrder.receiverState}</td>
			<td> ${omsOrder.receiverCity}</td>
			<td> ${omsOrder.receiverDistrict}</td>
			<td> ${omsOrder.receiverAddress}</td>
			
			
			<td>${omsOrder.comFee}</td>
			<td>${omsOrder.num}</td>
			<td>${omsOrder.comtotoleFee}</td>
			<td>${omsOrder.payment}</td>
			<td>${omsOrder.sku}</td>
			<td>${omsOrder.comType}</td>
			<td>${omsOrder.comName}</td>
			<td>${omsOrder.buyerNick}</td>
			
			
			<td>${omsOrder.hmsid}</td>
			<td> ${omsOrder.hotelName}</td>
			<td> ${omsOrder.hmsuserid}</td> 
			<td>${omsOrder.userName} </td>
			<td>${omsOrder.marketName}</td>
			<td> ${omsOrder.marketMoble}</td>
			<td>${omsOrder.shopName}</td>
	
			<!--  
			 
                <td><a href="${ctx}/oms/omsOrder/form?orderId=${omsOrder.orderId}">
                    ${omsOrder.productName}
                </a></td>
                <td>
                        ${omsOrder.kind}
                </td>
                <td>
                        ${omsOrder.receiverCity}
                </td>
                <td>
                        ${omsOrder.receiverDistrict}
                </td>
                <td>
                        ${omsOrder.receiverAddress}
                </td>
                <td>
                        ${omsOrder.receiverMobile}
                </td>
                <td>
                        ${fns:getDictLabel(omsOrder.status, 'orderStatus', '')}
                </td>
                <td>
                        ${fns:getDictLabel(omsOrder.payStatus, 'oms_payStatus', '')}
                </td>
                <td>
                        ${omsOrder.payMemo}
                </td>
                <td>
                        ${omsOrder.num}
                </td>
                <td>
                        ${omsOrder.productTotalFee}
                </td>
				<td>
					${omsOrder.sAtime}
				</td>
				<td>
					${omsOrder.sMtime}
				</td>
                <td>
                    ${omsOrder.sCmpy}
                </td>
				<td>
					${omsOrder.hotelName}
				</td>
                <td>
                        ${omsOrder.yzOrderid}
                </td>
               -->
                 <!--  
                <td>
                        ${omsOrder.marketName}
                </td>-->
                
				<%--<shiro:hasPermission name="oms:omsOrder:edit"><td>--%>
    				<%--<a href="${ctx}/oms/omsOrder/form?id=${omsOrder.id}">修改</a>--%>
					<%--<a href="${ctx}/oms/omsOrder/delete?id=${omsOrder.id}" onclick="return confirmx('确认要删除该OMS订单吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>