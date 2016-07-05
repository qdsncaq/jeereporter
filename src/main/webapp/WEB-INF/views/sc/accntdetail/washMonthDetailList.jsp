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
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/washfinddetails");
			$("#searchForm").submit();
        	return false;
        }
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntdetail/settlementAccntDetail/washexportexcel");
			$("#searchForm").submit();
		    return false;
        }
		function showUpdate(id, orderid, accountrole, ordertotal){
			$("#id").val(id);
			$("#corderid").val(orderid);
			$("#accountrole2").val(accountrole);
			$("#ordertotal").val(ordertotal);
			layer.open({
			    type: 1,
			    skin: 'layui-layer-rim', //加上边框
			    area: ['50%', '90%'], //宽高
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
				if(changeOrdertotal == null || changeOrdertotal == ""){
					alertx("调整金额不能为空!");return false;
				}
				$.post('${ctx }/accntdetail/settlementAccntDetail/modify', data, function(data){
					if(data > 0){
						layer.msg("修改完成！");
						setTimeout("window.location.reload('washMonthDerailList.jsp')",1000);
					} else if(data == 999){
						layer.msg("修改失败,结算中心报错！");return false;
					} else {
						layer.msg("修改失败！");return false;
					}
				}) 
			 });
		}) 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntdetail/settlementAccntDetail/washfinddetails">洗涤月账单流水列表</a></li>
		<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><li><a href="${ctx}/accntdetail/settlementAccntDetail/form">洗涤月账单流水添加</a></li></shiro:hasPermission>
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
	
	<form:form id="searchForm" modelAttribute="settlementAccntDetail" action="${ctx}/accntdetail/settlementAccntDetail/washfinddetails" method="post" class="breadcrumb form-search">
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
				<th>收货日期</th>
				<th>订单ID</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<!-- <th>PMS编码</th>
				<th>旅行社名称</th> -->
				<th>业务类型</th>
				<th>账户角色</th>
				<th>费用类型</th>
				<th>原始金额</th>
				<th>结算金额</th>
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
				<td nowrap><a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">
					${settlementAccntDetail.accountid}
				</a></td>
				<td nowrap>
					${settlementAccntDetail.accountname}
				</td>
                <td nowrap>
                    <fmt:formatDate value="${settlementAccntDetail.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
				<td nowrap>
					${settlementAccntDetail.orderid}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelid}
				</td>
				<td nowrap>
					${settlementAccntDetail.hotelname}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.biztype, 'BizTypeEnums', '')}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.feetype, 'FeeTypeEnums', '')}
				</td>
				<td nowrap>
					${settlementAccntDetail.orderprice}
				</td>
				<td nowrap>
					${settlementAccntDetail.ordertotal}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccntDetail.status, 'AccountStatusEnums', '')}
				</td>
				<td nowrap>
					${settlementAccntDetail.remarks}
				</td>
	    		<shiro:hasRole name="SC_FI_BILLCONFIRM">
					<td nowrap>
						<c:if test="${settlementAccntDetail.feetype != 400 && settlementAccntDetail.status == 3}">
		            		<a onclick="showUpdate('${settlementAccntDetail.id }','${settlementAccntDetail.orderid }','${fns:getDictLabel(settlementAccntDetail.accountrole, 'AccountRoleEnums', '')}','${settlementAccntDetail.ordertotal }')">调整金额</a> 
		            	</c:if>
		            </td>
	            </shiro:hasRole>
				<shiro:hasPermission name="accntdetail:settlementAccntDetail:edit"><td nowrap>
    				<a href="${ctx}/accntdetail/settlementAccntDetail/form?id=${settlementAccntDetail.id}">修改</a>
					<a href="javascript:;" confirmaction="${ctx}/accntdetail/settlementAccntDetail/delete?id=${settlementAccntDetail.id}" onclick="return confirmx('确认要删除该accntdetail吗？', $(this).attr('confirmaction'))">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
        <c:if test="${page.list.size() == 0 }">
          <tr>
            <td colspan="100%" style="word-wrap:break-word;word-break:break-all;">
                未查找到符合条件的数据.
            </td>
          </tr>
        </c:if>
		</tbody>
	</table>
	<!--
	<div class="pagination">${page}</div>
	-->
</body>
</html>