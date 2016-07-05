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
	<form:form id="searchForm" modelAttribute="omsOrder" class="breadcrumb form-search">
        <ul class="ul-form">
            <%--
            <shiro:hasPermission name="invoice:omsReceipt:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="开票"/></shiro:hasPermission>
            --%>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable"  class="table table-striped table-bordered table-condensed" style="overflow-x:auto">
		<thead>
			<tr>
			<th>订单id</th>
			<th>有赞订单号</th>
			<th>订单状态</th>
			<th>支付状态</th>
			<th>支付时间</th>
			<th>订单金额</th>
			<th>用户留言</th>
			<th>酒店id</th>
			<th>酒店名称</th>
			<th>用户id</th>
			<th>用户名</th>
			<th>发票id</th>
			</tr>
		</thead>
		<tbody style="overflow-y:auto;height: 50px; ">
		<c:forEach items="${list}" var="omsOrder">
			<tr>
			<td><a href="${ctx}/oms/omsOrder/form?orderId=${omsOrder.orderId}">${omsOrder.orderId}</a></td>
			<td>${omsOrder.yzOrderid}</td>
			<td>${fns:getDictLabel(omsOrder.status, 'orderStatus', '')}</td>
			<td>${fns:getDictLabel(omsOrder.payStatus, 'oms_payStatus', '')}</td>
			<td>${omsOrder.payMemo}</td>
			<td>${omsOrder.payment}</td>
			<td>${omsOrder.buyerMessage}</td>
			<td>${omsOrder.hmsid}</td>
			<td>${omsOrder.hotelName}</td>
			<td>${omsOrder.hmsuserid}</td> 
			<td>${omsOrder.userName}</td>
			<td>${omsOrder.receiptid}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>