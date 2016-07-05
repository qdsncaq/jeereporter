<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>oms订单详情管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		function orderEdit(){
			 window.location.href="${ctx}/oms/omsOrder/orderEdit?orderId=${omsOrder.orderId}"; 
		}
		
		
		function orderInvoiceEdit(id){
			var time=$("#"+id+"_invoiceTime").val();
			  top.$.jBox.confirm("你确认要修改预发货时间为："+time+"？","系统提示",function(v,h,f){
                  if(v=="ok"){
                	  $.ajax({
                          url: "${ctx}/oms/omsOrder/updateInvoiceTime",
                          dataType: "json",
                          type: "POST",
                          data:{"id":id,"invoiceTime":time},
                          success: function (data) {
             					if(data!=null){
             					  alert(data.msg);
             					}
             					
                          },
                          error: function(XMLHttpRequest, textStatus, errorThrown) {
                        	  alert(XMLHttpRequest.status);
                        	  alert(XMLHttpRequest.readyState);
                        	  alert(textStatus);
                        	    }
                      });
                	  
                	  
                	  
                  }
              },{buttonsFocus:1});
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="omsOrder" action="${ctx}/oms/omsOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <div class="control-group">
            <label class="control-label">标题：</label>
            <div class="controls">
            ${omsOrder.title}
            </div>
           
        </div>
        <div class="control-group">
			<label class="control-label">有赞订单id：</label>
			<div class="controls">
			${omsOrder.yzOrderid}
			</div>
		</div>
		 <div class="control-group">
            <label class="control-label">订单状态：</label>
            <div class="controls">
                ${fns:getDictLabel(omsOrder.status, 'orderStatus', '')}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">创建时间：</label>
            <div class="controls">
            ${omsOrder.sAtime}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">修改时间：</label>
            <div class="controls">
             ${omsOrder.sMtime}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">支付状态：</label>
            <div class="controls">
               ${fns:getDictLabel(omsOrder.payStatus, 'oms_payStatus', '')}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">支付时间：</label>
            <div class="controls">
            ${omsOrder.payMemo}
            </div>
        </div>
        
		<div class="control-group">
			<label class="control-label">邮寄费用：</label>
			<div class="controls">
				${omsOrder.postFee}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品总费用：</label>
			<div class="controls">
			${omsOrder.totoleFee}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">折扣：</label>
			<div class="controls">
			${omsOrder.discount}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单总费用：</label>
			<div class="controls">
			${omsOrder.payment}
			</div>
		</div>
        
         <div class="control-group">
            <label class="control-label">收货人：</label>
            <div class="controls">
            ${omsOrder.receiverName}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">联系电话：</label>
            <div class="controls">
              ${omsOrder.receiverMobile}
            </div>
        </div>
        
        <div class="control-group">
			<label class="control-label">收货邮编：</label>
			<div class="controls">
			   ${omsOrder.receiverZip}
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">收货省份：</label>
            <div class="controls">
            ${omsOrder.receiverState}
            </div>
        </div>
        
        
        <div class="control-group">
            <label class="control-label">收货城市：</label>
            <div class="controls">
             ${omsOrder.receiverCity}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">收货区县：</label>
            <div class="controls">
            ${omsOrder.receiverDistrict}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">收货地址：</label>
            <div class="controls">
            ${omsOrder.receiverAddress}
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">用户留言：</label>
			<div class="controls">
			${omsOrder.buyerMessage}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预发货时间：</label>
			<div class="controls">
			${omsOrder.invoiceTime}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货时间：</label>
			<div class="controls">
				${omsOrder.consignTime}
			</div>
		</div>
        
       <div class="control-group">
			<label class="control-label">交易备注：</label>
			<div class="controls">
				${omsOrder.tradeMemo}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改备注：</label>
			<div class="controls">
				${omsOrder.remarks}
			</div>
		</div>
		

<div class="control-group">
			<label class="control-label">用户昵称：</label>
			<div class="controls">
				${omsOrder.buyerNick}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">用户ID：</label>
			<div class="controls">
			${omsOrder.userid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一用户ID：</label>
			<div class="controls">
			${omsOrder.hmsuserid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户登录名：</label>
			<div class="controls">
			${omsOrder.userName}
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">酒店id：</label>
            <div class="controls">
            ${omsOrder.hmsid}
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">酒店编码：</label>
            <div class="controls">
            ${omsOrder.sCmpy}
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">酒店名称：</label>
            <div class="controls">
            ${omsOrder.hotelName}
            </div>
        </div>
        

		<div class="control-group">
			<label class="control-label">销售id：</label>
			<div class="controls">
			${omsOrder.marketid}
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">销售名称：</label>
            <div class="controls">
            ${omsOrder.marketName}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">销售电话：</label>
            <div class="controls">
            ${omsOrder.marketMoble}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">店铺名称：</label>
            <div class="controls">
            ${omsOrder.shopName}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">票据类型：</label>
            <div class="controls">
            ${omsOrder.receiptTypeName}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">票据号码：</label>
            <div class="controls">
            ${omsOrder.receiptCode}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">开票日期：</label>
            <div class="controls">
            ${omsOrder.receiptDate}
            </div>
        </div>                

			<div class="control-group">
				<label class="control-label">订单商品表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>订单创建时间</th>
								<th>订单修改时间</th>
								<th>酒店编码</th>
								<th>有赞物品id</th>
								<th>物品数量</th>
								<th>物品名称</th>
								<th>商品总费用</th>
								<th>折扣</th>
								<th>支付费用</th>
								<th>预发货时间</th>
								<th>修改备注</th>
								<%--<shiro:hasPermission name="oms:omsOrder:edit"><th width="10">&nbsp;</th></shiro:hasPermission>--%>
							</tr>
						</thead>
						<tbody id="omsOrderItemList">
						</tbody>
						<%--<shiro:hasPermission name="oms:omsOrder:edit"><tfoot>--%>
							<%--<tr><td colspan="11"><a href="javascript:" onclick="addRow('#omsOrderItemList', omsOrderItemRowIdx, omsOrderItemTpl);omsOrderItemRowIdx = omsOrderItemRowIdx + 1;" class="btn">新增</a></td></tr>--%>
						<%--</tfoot></shiro:hasPermission>--%>
					</table>
					<script type="text/template" id="omsOrderItemTpl">//<!--
						<tr id="omsOrderItemList{{idx}}">
							<td class="hide">
								<input id="omsOrderItemList{{idx}}_id" name="omsOrderItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="omsOrderItemList{{idx}}_delFlag" name="omsOrderItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_sAtime" name="omsOrderItemList[{{idx}}].sAtime" type="text" value="{{row.sAtime}}" maxlength="40" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_sMtime" name="omsOrderItemList[{{idx}}].sMtime" type="text" value="{{row.sMtime}}" maxlength="40" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_sCmpy" name="omsOrderItemList[{{idx}}].sCmpy" type="text" value="{{row.sCmpy}}" maxlength="40" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_skuid" name="omsOrderItemList[{{idx}}].skuid" type="text" value="{{row.skuid}}" maxlength="40" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_num" name="omsOrderItemList[{{idx}}].num" type="text" value="{{row.num}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_title" name="omsOrderItemList[{{idx}}].title" type="text" value="{{row.title}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_totoleFee" name="omsOrderItemList[{{idx}}].totoleFee" type="text" value="{{row.totoleFee}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_discount" name="omsOrderItemList[{{idx}}].discount" type="text" value="{{row.discount}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="omsOrderItemList{{idx}}_payment" name="omsOrderItemList[{{idx}}].payment" type="text" value="{{row.payment}}" maxlength="11" class="input-small "/>
							</td>
							<td>
									
								<input id="{{row.selfId}}_invoiceTime"  name="omsOrderItemList[{{idx}}].invoiceTime" type="text" 
									value="{{row.invoiceTime}}" onfocus="WdatePicker({minDate:'%y-%M-<%="#"%>{%d+1}',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true});"
					              class="input-small "/>
						<c:if test="${omsOrder.status=='WAIT_SELLER_SEND_GOODS' }">
								<shiro:hasPermission name="oms:omsOrder:edit">
						<input id="btnInvoiceEdit" class="btn" type="button" ids="{{row.selfId}}" onclick="orderInvoiceEdit('{{row.selfId}}')" value="修改"/>
						</shiro:hasPermission>
						</c:if>							
						</td>
							<td>
								<input id="omsOrderItemList{{idx}}_remarks" name="omsOrderItemList[{{idx}}].remarks" type="text" value="{{row.remarks}}"  class="input-small"/>
							</td>


						</tr>//-->
					</script>
					<script type="text/javascript">
						var omsOrderItemRowIdx = 0, omsOrderItemTpl = $("#omsOrderItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(omsOrder.omsOrderItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#omsOrderItemList', omsOrderItemRowIdx, omsOrderItemTpl, data[i]);
								omsOrderItemRowIdx = omsOrderItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
		<c:if test="${omsOrder.status=='WAIT_SELLER_SEND_GOODS' }">
			<shiro:hasPermission name="oms:omsOrder:edit">
			
			<input id="btnEdit" class="btn" type="button"  onclick="orderEdit()" value="修改发货地址"/>&nbsp;
			
			</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>