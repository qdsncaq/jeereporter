<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function showUpdate(id, orderid, accountrole, ordertotal){
			// alert(orderid);return false;
			// alert(3333);
			$("#id").val(id);
			$("#corderid").val(orderid);
			$("#accountrole").val(accountrole);
			$("#ordertotal").val(ordertotal);
			// alert("orderid=" + orderid);return false;
			layer.open({
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['35%', '70%'], //宽高
			    content: $("#content")
			});
		}
		$(function(){
			$("#btnSubmit").click(function(){ 
				var id = $("#id").val();
				var changeOrdertotal = $("#changeOrdertotal").val();
				var remarks = $("#remarks").val();
				var data = { id:id, 
							changeOrdertotal:changeOrdertotal, 
							remarks:remarks};
				// alert(id + changeOrdertotal + remarks);return false;
				$.post('${ctx }/scadmin/tripdetail/update', data, function(data){
					// alert(data);return false;
					if(data > 0){
						layer.msg("修改完成！");
						setTimeout("window.location.reload('tripDetailList.jsp')",1000);
					} else if(data == 999){
						layer.msg("修改失败,结算中心报错！");return false;
					} else {
						layer.msg("修改失败！");return false;
					}
				}) 
			 });
		}) 
		// 导出
		function exportAll(){
			var id = $("#id").val();
			var orderid = $("#orderid").val();
			var hotelid = $("#hotelid").val();
			var hotelname = $("#hotelname").val();
			location.href = "${ctx }/scadmin/tripdetail/exportall?orderid="+orderid+"&hotelid="+hotelid+"&hotelname="+hotelname+"&id="+id;
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/auth/authRole/">账单明细</a></li>
	</ul>
	<div>
		<form:form id="searchForm" action="${ctx}/scadmin/tripdetail/list" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
				<div>
					<!-- billid -->
			        <input type="hidden" id="id" name="id" value="${params.id }">
			        <%-- <input type="hidden" id="id" name="id" value="${id }"> --%>
			                   订单ID:<input type="text" id="orderid" name="orderid" value="${params.orderid }" style="width:130px;margin-right:15px;" > 
			                   酒店ID:<input type="text" id="hotelid" name="hotelid" value="${params.hotelid }" style="width:130px;margin-right:15px;" > 
			                   酒店名称:<input type="text" id="hotelname" name="hotelname" value="${params.hotelname }" style="width:130px;margin-right:15px;" > 
                   <%-- 状态: <select name="status" style="width:130px;margin-right:15px;" >
	                   <option value="">全部</option>
	                   <option value="1" <c:if test="${params.status == '1' }">selected = "selected"</c:if>>未结算</option>
	                   <option value="2" <c:if test="${params.status == '2' }">selected = "selected"</c:if>>已结算</option>
	              </select> --%>
			                         
			       <button type="submit" class="btn btn-primary" >查询</button>
			       <button class="btn btn-primary" id="btnExport" name="btnExport" type="button" onclick="exportAll()" >导出</button>
				</div>
			</ul>
		</form:form>
		
		<div id="content" hidden="" class="divclass">
       		<input type="hidden" name="id" id="id" value="" maxlength="50" disabled="disabled" />
			<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
				<label class="control-label">订&nbsp;&nbsp;单&nbsp;号: </label> 
				<!-- <span id="orderId"></span> -->
				<input type="text" name="corderid" id="corderid" value=""  maxlength="50" disabled="disabled" />
			</div>
			<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
				<label class="control-label">账户角色: </label>
				<input type="text" name="accountrole" id="accountrole" value="" maxlength="50" disabled="disabled" />
			</div>
			<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
				<label class="control-label">结算金额: </label>
				<input type="text" name="ordertotal" id="ordertotal" value="" maxlength="50" disabled="disabled" />
			</div>
			<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
				<label class="control-label">调整金额: </label>
				<input type="text" id="changeOrdertotal" name="changeOrdertotal" onkeyup="value=value.replace(/[^\-?\d.]/g,'')" /> (限制文本框只能输入正数，负数，小数)
			</div>
			<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
				<label class="control-label">调整原因: </label>
				<textarea rows="" cols="" id="remarks" name="remarks"></textarea>
			</div>
			
			<div style="padding-top: 15px; padding-bottom: 5px; text-align: center;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
			</div>
		</div>
		
		
		 <table id="table1" class="table table-bordered table-hover" style="margin-top:10px;">
                <tr>
			        <!-- <th>id</th>  -->
			        <th>订单号</th>
			        <th>账户ID</th>
			        <th>账户名称</th>
			        <th>酒店ID</th>
			        <th>酒店名称</th>
			        <th>订单金额</th>
			        <th>下单时间</th>
			        <th>离店时间</th>
			        <th>费用类型</th>
			        <th style="width:380px;">调整原因</th>
			        <th>操作</th>
			    </tr>
			    <c:forEach items = "${message.result }" var = "item">
	                <tr >
	                    <%-- <td>${item.id }</td> --%>
	                    <td>${item.orderid }</td>
	                    <td>${item.accountid }</td>
	                    <td>${item.hotelname }</td>
	                    <td>${item.hotelid }</td>
	                    <td>${item.hotelname }</td>
	                    <td>${item.ordertotal }</td>
			            <td>${item.strOrdertime }</td>
			            <td>${item.strLeftDate }</td>
			            <td>${item.strFeetype }</td>
			            <td style="word-break:break-all;">${item.remarks }</td>
			            <td>
			            	<a onclick="showUpdate('${item.id }','${item.orderid }','${item.strAccountrole }','${item.ordertotal }')">调整金额</a> 
			            </td>
                	</tr>
                </c:forEach>
            </table>
	    <div class="pagination">${page}</div>
	 </div>   
</body>
</html>