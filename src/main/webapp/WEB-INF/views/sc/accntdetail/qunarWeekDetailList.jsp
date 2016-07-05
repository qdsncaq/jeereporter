<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accntdetail管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/finddetails");
			$("#searchForm").submit();
        	return false;
        }
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/exportexcel");
			$("#searchForm").submit();
		    return false;
        }
		function showUpdate(id, orderid, accountrole, ordertotal){
			// alert(orderid);return false;
			// alert(3333);
			$("#id").val(id);
			$("#corderid").val(orderid);
			$("#accountrole2").val(accountrole);
			$("#ordertotal").val(ordertotal);
			// alert("accountrole=" + accountrole);return false;
			layer.open({
			    type: 1,
			    //skin: 'layui-layer-rim', //加上边框
			    area: ['35%', '70%'], //宽高
			    content: $("#content")
			});
		}
		$(function(){
			$("#savebtnSubmit").click(function(){ 
				var id = $("#id").val();
				var changeOrdertotal = $("#changeOrdertotal").val();
				var remarks = $("#remarks").val();
				var data = { id:id, 
							ordertotal:changeOrdertotal, 
							remarks:remarks};
				// alert(id + changeOrdertotal + remarks + "aaaaaa");return false;
				if(changeOrdertotal == null || changeOrdertotal == ""){
					alertx("调整金额不能为空!");return false;
				}
				$.post('${ctx }/accntdetail/settlementAccntDetail/modify', data, function(data){
					// alert(data);return false;
					/* if(data == 444){
						alert(data);
						return false;
					} else if(data > 0){
						layer.msg("修改完成！");
						setTimeout("window.location.reload('tripWeekDerailList.jsp')",1000);
					} else if(data == 999){
						layer.msg("修改失败,结算中心报错！");return false;
					} else {
						layer.msg("修改失败！");return false;
					} */
					alertx(data + '该单对应销售、酒店是否都进行调整.');setTimeout("window.location.reload('qunarWeekDerailList.jsp')",1000);return false;
				}) 
			 });
		}) 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;"onclick="query()">去那儿账单流水列表</a></li>
		<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><li><a href="${ctx}/accntdetail/settlementAccntDetail/form">去那儿账单流水添加</a></li></shiro:hasPermission>
	</ul>
	
	<div id="content" hidden="" class="divclass">
      	<input type="hidden" name="id" id="id" value="" maxlength="50" disabled="disabled" />
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">订&nbsp;&nbsp;单&nbsp;号: </label> 
			<!-- <span id="orderId"></span> -->
			<input type="text" name="corderid" id="corderid" value=""  maxlength="50" disabled="disabled" />
		</div>
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">账户角色: </label>
			<input type="text" name="accountrole2" id="accountrole2" maxlength="50" disabled="disabled" />
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
			<input id="savebtnSubmit" class="btn btn-primary" type="button" value="保 存"/>
		</div>
	</div>
	
	<form:form id="searchForm" modelAttribute="settlementAccntDetail" action="${ctx}/accntdetail/settlementAccntDetail/finddetails" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:input type="hidden" name="no" id="no" path="no" maxlength="50" disabled="disabled" />
        <form:input type="hidden" name="no1" id="no1" path="no1" maxlength="50" disabled="disabled" />
		<ul class="ul-form">
			<li><label>账户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户名称：</label>
				<form:input path="accountname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>订单ID：</label>
				<form:input path="orderid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>业务类型：</label>
				<form:select path="biztype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('BizTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>费用类型：</label>
				<form:select path="feetype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('FeeTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>原始金额：</label>
				<form:input path="beginOrderprice" htmlEscape="false" class="input-medium"/>
				- <form:input path="endOrderprice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>结算金额：</label>
				<form:input path="beginOrdertotal" htmlEscape="false" class="input-medium"/>
				- <form:input path="endOrdertotal" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>充值优惠：</label>
				<form:input path="beginFreetotal" htmlEscape="false" class="input-medium"/>
				- <form:input path="endFreetotal" htmlEscape="false" class="input-medium"/>
			</li>
			<br><br>
			<li><label>下单时间：</label>
				<input name="beginOrdertime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginOrdertime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOrdertime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endOrdertime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>离店时间：</label>
				<input name="beginLeftDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.beginLeftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endLeftDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntDetail.endLeftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><input onclick="return page();" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" class="btn btn-primary" type="button" value="导出excel"/></li>
            <li class="btns"><input onclick="history.go(-1)" class="btn btn-primary" type="button" value="返回"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账户ID</th>
				<th>账户名称</th>
				<th>订单ID</th>
				<th>第三方单号</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<!-- <th>PMS编码</th>
				<th>旅行社名称</th> -->
				<th>业务类型</th>
				<th>账户角色</th>
				<th>费用类型</th>
				<th>原始金额</th>
				<th>结算金额</th>
				<th>充值优惠金额</th>
				<th>下单时间</th>
				<th>创建时间</th>
				<th>离店时间</th>
				<th>状态</th>
				<th>调整说明</th>
				<shiro:hasRole name="SC_FI_BILLCONFIRM">
					<th>操作</th>
				</shiro:hasRole>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntDetail">
			<tr>
				<td><a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">
					${settlementAccntDetail.accountid}
				</a></td>
				<td>
					${settlementAccntDetail.accountname}
				</td>
				<td>
					${settlementAccntDetail.orderid}
				</td>
                <td>
                    ${settlementAccntDetail.orderno}
                </td>
				<td>
					${settlementAccntDetail.hotelid}
				</td>
				<td>
					${settlementAccntDetail.hotelname}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.biztype, 'BizTypeEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.feetype, 'FeeTypeEnums', '')}
				</td>
				<td>
					${settlementAccntDetail.orderprice}
				</td>
				<td>
					${settlementAccntDetail.ordertotal}
				</td>
				<td>
					${settlementAccntDetail.freetotal}
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntDetail.leftDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(settlementAccntDetail.status, 'AccountStatusEnums', '')}
				</td>
				<td>
					${settlementAccntDetail.remarks}
				</td>
				<shiro:hasRole name="SC_FI_BILLCONFIRM">
					<td>
						<c:if test="${settlementAccntDetail.feetype != 400}">
		            		<%-- <a onclick="showUpdate('${settlementAccntDetail.id }','${settlementAccntDetail.orderid }','${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}','${settlementAccntDetail.ordertotal }')">调整金额</a>  --%>
		            		<a href="javascript:;"onclick="showUpdate('${settlementAccntDetail.id }','${settlementAccntDetail.orderid }','${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}','${settlementAccntDetail.ordertotal }')">调整金额</a>
		            	</c:if>
		            </td>
	            </shiro:hasRole>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><td>
    				<a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">修改</a>
					<a href="${ctx}/accntdetail/settlementAccntDetail/delete?id=${settlementAccntDetail.id}" onclick="return confirmx('确认要删除该accntdetail吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>