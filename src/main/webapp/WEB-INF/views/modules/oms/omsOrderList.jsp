<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OMS订单管理</title>
	<meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<script type="text/javascript">
	 var BodyHeight = ((document.documentElement.clientHeight || window.document.body.clientHeight) || window.innerHeight);
	   var BodyWidth = ((document.documentElement.clientWidth || window.document.body.clientWidth) || window.innerWidth);
	   //$("#contentTable").height(50);
	   //$("#contentTable").width(50);
	   
	   $(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出采购订单数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/export");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/list");
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
						var shopid="${omsOrder.shopid}";
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
		
		  
	</script>
</head>
<body>
	<%--<ul class="nav nav-tabs">--%>
		<%--<li class="active"><a href="${ctx}/oms/omsOrder/">OMS订单列表</a></li>--%>
		<%--<shiro:hasPermission name="oms:omsOrder:edit"><li><a href="${ctx}/oms/omsOrder/form">OMS订单添加</a></li></shiro:hasPermission>--%>
	<%--</ul>--%>
	<form:form id="searchForm" modelAttribute="omsOrder" action="${ctx}/oms/omsOrder/list" method="post" class="breadcrumb form-search">
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
			<select id="shopid" class="input-medium select2-offscreen" name="shopid" >
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
			<!--  <li><label>商品类别：</label>
             <select id="shopid" class="input-medium select2-offscreen" name="shopid" >
			<option selected="selected" value="">--请选择--</option>
			</select>
            </li>-->
			
			<li><label>创建时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>修改时间：</label>
				<input name="startUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${omsOrder.startUpdateTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${omsOrder.endUpdateTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			
			
			<li class="clearfix"></li>
			<li><label>订单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('orderStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付状态：</label>
				<form:select path="payStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('oms_payStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付时间：</label> 
				<input name="startPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.startPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.endPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>发货时间：</label> 
				<input name="startConsignTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.startConsignTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endConsignTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.endConsignTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>预发货时间：</label> 
				<input name=startInvoiceTime type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.startInvoiceTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> -
				<input name="endInvoiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrder.endInvoiceTime}" pattern="yyyy-MM-dd"/>"
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
			<th>订单金额</th>
			<th>收货人</th>
			<th>收货人电话</th>
			<th>邮编</th>
			<th>用户留言</th>
			<th>收货省份</th>
			<th>收货城市</th>
			<th>收货区县</th>
			<th>收货地址</th>
			<th>酒店id</th>
			<th>酒店名称</th>
			<th>用户id</th>
			<th>用户名</th>
			<th>销售名称</th>
			<th>销售电话</th>
			<th>店铺名称</th>
			<th>发货时间</th>
			<th>预发货时间</th>
			<th>票据类型</th>
			<th>票据号码</th>
			<th>开票日期</th>
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
			<td>${omsOrder.payment}</td>
			<td>${omsOrder.receiverName}</td>
			<td>${omsOrder.receiverMobile}</td>
			<td>${omsOrder.receiverZip}</td>
			<td>${omsOrder.buyerMessage}</td>
			<td> ${omsOrder.receiverState}</td>
			<td> ${omsOrder.receiverCity}</td>
			<td> ${omsOrder.receiverDistrict}</td>
			<td> ${omsOrder.receiverAddress}</td>
			<td>${omsOrder.hmsid}</td>
			<td> ${omsOrder.hotelName}</td>
			<td> ${omsOrder.hmsuserid}</td> 
			<td>${omsOrder.userName} </td>
			<td>${omsOrder.marketName}</td>
			<td> ${omsOrder.marketMoble}</td>
			<td>${omsOrder.shopName}</td>
			<td>${omsOrder.consignTime}</td>
			<td>${omsOrder.invoiceTime}</td>
			<td>${omsOrder.receiptTypeName}</td>
			<td>${omsOrder.receiptCode}</td>
			<td>${omsOrder.receiptDate}</td>			
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