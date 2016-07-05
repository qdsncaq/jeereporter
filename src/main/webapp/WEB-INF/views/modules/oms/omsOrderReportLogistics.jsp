<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title>OMS订单业务报表-物流相关</title>
	<meta name="decorator" content="default"/>
	<script src="${ctx}/static/fileinput/fileinput.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/fileinput/fileinput_locale_zh.js" type="text/javascript"></script>	
	<link href="${ctx}/static/fileinput/fileinput.css" media="all" rel="stylesheet" type="text/css" />		
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>	
		
		
	<style type="text/css">
	
	#s2id_worker .select2-search-choice{height: 17px;}
	
	#jbox-content {
		overflow: hidden !important;;
	}
	</style>

	<script type="text/javascript">
	 
	   
	   $(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出物流数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/exporLogisticsFile");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/reportLogistics");
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });

            $("#btnExportQuery").click(function(){
                top.$.jBox.confirm("确认要导出吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/exporLogisticsFileQuery");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action","${ctx}/oms/omsOrder/reportLogistics");
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });            
            
            $("#btnImport").click(function(){
            	$.jBox("iframe:${ctx}/oms/omsOrder/uploadDialog", {
           		     title: "上传文件",
           		     width: 500,            		     
           		     height:360,
           			 buttons: { '关闭': true }, 
           		     bottomText:"请上传xlsx类型的文件"
            	});
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
							var shopid="${omsOrderLogistic.shopid}";
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
			 
			 var sta="${omsOrderLogistic.status}";
			 if(sta!=null&&sta!=""){
				 var status=sta.split(',');
				 $("#worker").select2().val(status).trigger("change");
			 }
		 })
		
		  
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="omsOrderLogistic" action="${ctx}/oms/omsOrder/reportLogistics" method="post" class="breadcrumb form-search">
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
			
			<li><label>订单状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('orderStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>			
			
			<li><label>支付状态：</label>
				<form:select path="payStatus" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('oms_payStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>			
			
			<li class="clearfix"></li>
			<li><label style="width: 120px;">物流运单号状态：</label>
				<form:select path="logisticsStatus" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:option value="0" label="无"/>
					<form:option value="1" label="有"/>
				</form:select>
			</li>					
			
			<li><label style="width: 150px;">操作发货物流指示时间：</label>
				<input name="expBatchDay" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.expBatchDay}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>			
			<li class="clearfix"></li>
			
			<li><label>创建时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>支付时间：</label> 
				<input name="startPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.startPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> -
				<input name="endPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.endPayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>预发货时间：</label> 
				<input name=startInvoiceTime type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.startInvoiceTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> -
				<input name="endInvoiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${omsOrderLogistic.endInvoiceTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>				
			
			<li class="clearfix"></li>
			
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			    <input id="btnExportQuery" class="btn btn-primary" type="button" value="导出"/>
			    <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
                <input id="btnExport" class="btn btn-primary" type="button" value="发货物流指示"/>
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
				<th>商品编号</th>
				<th>商品名称</th>
				<th>数量</th>
				<th>重量</th>
				
				<th>收货人</th>
				<th>收件公司/个人</th>
				<th>收货人电话</th>
				<th>销售名称</th>
				<th>收货省份</th>
				<th>收货城市</th>
				<th>收货区县</th>			
				<th>收件人地址</th>
				<th>收件人城市</th>				
				<th>发件地</th>
				
				<th>销售区域</th>
				<th>直配送厂家</th>
				<th>配送公司</th>
				<th>物流运单号</th>				
				<th>提货仓库</th>
				<th>订单状态</th>
				<th>支付状态</th>
				
				<th>预发货时间</th>
				<th>操作发货物流指示时间</th>
				<th>发货时间</th>
				<th>签收状态</th>
				<th>用户留言</th>
				<th>卖家留言</th>
				<th>操作日志</th>
			</tr>
		</thead>
		<tbody style="overflow-y:auto;height: 50px; ">
		<c:forEach items="${page.list}" var="omsOrder">
			<tr>
				<td>
				    <a href="${ctx}/oms/omsOrder/form?orderId=${omsOrder.orderId}">
	                    ${omsOrder.orderId}
	                </a>
	            </td>
				<td>${omsOrder.yzOrderid}</td>
				<td>${omsOrder.outerId}</td>
				<td>${omsOrder.comName}</td>
				<td>${omsOrder.num}</td>
				<td>${omsOrder.weight}</td>
				<td>${omsOrder.receiverName}</td>
				<td>${omsOrder.hotelName}</td>
				
				<td>${omsOrder.receiverMobile}</td>	
				<td>${omsOrder.marketName}</td>
				<td>${omsOrder.receiverState}</td>
				<td>${omsOrder.receiverCity}</td>
				<td>${omsOrder.receiverDistrict}</td>
				<td>${omsOrder.receiverAddress}</td>
				<td>${omsOrder.receiverState}</td>
				<td>${omsOrder.wareHouseName}</td>
				<td>${omsOrder.shopName}</td>
				<td>${omsOrder.vendorName}</td>
				<td>${omsOrder.peiSongCmpy}</td>
				<td>${omsOrder.wuliuCode}</td> <!-- 物流运单号， 订单关联的商品多件，怎么记这个值 -->
				<td>${omsOrder.tiHuoCangku}</td>		
				<td>${fns:getDictLabel(omsOrder.status, 'orderStatus', '')}</td>
				<td>
					${fns:getDictLabel(omsOrder.payStatus, 'oms_payStatus', '')}
				</td>
				<td>${omsOrder.invoiceTime}</td>
				<td>${omsOrder.expBatch}</td>
				<td>${omsOrder.consignTime}</td>
				<td>${omsOrder.qianShouStatus}</td>
				<td>${omsOrder.buyerMessage}</td>
				<td>卖家留言</td>
				<td>操作日志</td>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>