<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店老板提现申请列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var all_checked = false;
		    $(":checkbox").click(function() {
		        var table = $(this).parents("table");
		         //如果点击了全选的，那么下面都选或者不选
		        if($(this).attr("id") === "all") {
		            table.find(":checkbox").prop("checked", !all_checked);
		            all_checked = !all_checked;
		        } else {
		            table.find(":checkbox[id!=all]").each(function (i) {
		                if(!$(this).is(":checked")) {
		                    table.find("#all").prop("checked", false);
		                    all_checked = false;
		                    return false;
		                }
		                $("#all").prop("checked", true);
		                all_checked = true;
		            });
		        }
		    });
		});
		
		/**
		 * 提交财务
		 */
		function throughaudit(rolename){
	     	var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		var length22 = 0;
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   				length22 = length22 + 1;
	   			}
	   		}
	   		if(selectedIds==""){
	   			alertx("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			confirmx('确认审核通过吗?共勾选'+length22+'条数据', function(){
		   				$.post('${ctx }/accntwithdraw/settlementAccountWithdrawcash/update',
							{ids : selectedIds},
							function(result){
								if(result == 100){
									loading();
									alertx("没有选择要结算的数据！");
									window.location.reload(50000);return false;
								} else if(result > null){
									loading();
									alertx("审核通过！"+result+"条.");
									window.location.reload(50000);return false;
								} else{
									loading();
									alertx("没有可审核的数据.");
									window.location.reload(50000);return false;
								}
							}
						);
	   			});
	   		}
		}
		
		/**
		 * 驳回
		 */
		function throughauditNo(rolename){
	     	var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		var length = 0;
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   				length = length + 1;
	   			}
	   		}
	   		if(selectedIds==""){
	   			alertx("没有选择要驳回的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			var url = "${ctx }/accntwithdraw/settlementAccountWithdrawcash/updateno";
	   			confirmx('确认驳回吗?共勾选'+length+'条数据.', function(){
	   				$.post( url,
						{ids : selectedIds},
						function(result){
							if(result == 100){
								loading();
								alertx("没有选择要驳回的数据！");
								window.location.reload(50000);return false;
							}if(result > 0){
								loading();
								alertx("已完成!驳回记录共"+result+"条.");
								window.location.reload(50000);return false;
							} else {
								loading();
								alertx("没有记录驳回.");
								window.location.reload(50000);return false;
								return false;
							}
						}
					);
	   			});
	   		}
		}
		
		/**
		 * 导出
		 */
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntwithdraw/settlementAccountWithdrawcash/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		
		/**
		 * 财务打款处理
		 */
		function paydone(){
	     	var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		var length = 0;
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   				length = length + 1;
	   			}
	   		}
	   		if(selectedIds==""){
	   			alertx("没有选择数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			confirmx('是否已经打款?共勾选'+length+'条数据.', function(){
	   				$.post('${ctx }/accntwithdraw/settlementAccountWithdrawcash/paydone',
						{ids : selectedIds},
						function(result){
							if(result == 100){
								loading();
								alertx("没有选择数据！");
								window.location.reload(50000);return false;
							} else if(result > 0){
								loading();
								alertx("完成!修改打款记录"+result+"条.");
								window.location.reload(50000);return false;
							} else {
								loading();
								alertx("没有记录可修改为已打款.");
								window.location.reload(50000);return false;
							}
						}
					);
	   			});
	   		}
		}
		
		/**
		 * 分页查询
		 */
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntwithdraw/settlementAccountWithdrawcash/listOfHotel");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfHotel">酒店提现申请列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementAccountWithdrawcash" action="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfHotel" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>省名称：</label>
				<form:input path="proname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>市名称：</label>
				<form:input path="cityname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>区名称：</label>
				<form:input path="disname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店ID：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>账户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>账户名称：</label>
				<form:input path="accountname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="accountphone" htmlEscape="false" maxlength="400" class="input-medium"/>
			</li>
			<li><label>户名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>开户行：</label>
				<form:input path="bank" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>申请日期：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccountWithdrawcash.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccountWithdrawcash.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>提取金额：</label>
				<form:input type="number" path="beginCash" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endCash" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>当前余额：</label>
				<form:input type="number" path="beginPrebalance" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endPrebalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>提现后余额：</label>
				<form:input type="number" path="beginBalance" htmlEscape="false" class="input-medium"/>
				- <form:input type="number" path="endBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>账户角色：</label>
				<form:select path="accountrole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>提现状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('WithdrawStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
            <li class="clearfix"></li>
			<li class="btns"><input onclick="return page();"  id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcel()" value="导出excel"/></li>
			
		    <shiro:hasRole name="HOTEL_TXSH_OPERATOR">
			    <li class="btns"><input id="ThroughAuditOperator" class="btn btn-primary" type="button" onclick="throughaudit('HOTEL_TXSH_OPERATOR')" value="运营确认"/></li>
			    <li class="btns"><input id="ThroughAuditnoOperator" class="btn btn-primary" type="button" onclick="throughauditNo('HOTEL_TXSH_OPERATOR')" value="运营驳回"/></li>
		    </shiro:hasRole>
            <shiro:hasRole name="HOTEL_TXSH_HR">
                <li class="btns"><input id="ThroughAuditHR" class="btn btn-primary" type="button" onclick="throughaudit('HOTEL_TXSH_HR')" value="人事确认"/></li>
                <li class="btns"><input id="ThroughAuditnoHR" class="btn btn-primary" type="button" onclick="throughauditNo('HOTEL_TXSH_HR')" value="人事驳回"/></li>
            </shiro:hasRole>
            <shiro:hasRole name="HOTEL_TXSH_FI">
                <li class="btns"><input id="ThroughAuditFI" class="btn btn-primary" type="button" onclick="throughaudit('HOTEL_TXSH_FI')" value="财务打款"/></li>
                <li class="btns"><input id="ThroughAuditnoFI" class="btn btn-primary" type="button" onclick="throughauditNo('HOTEL_TXSH_FI')" value="财务驳回"/></li>
            </shiro:hasRole>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
				<th>编号</th>
				<th>省份</th>
				<th>城市</th>
				<th>区域</th>
				<th>酒店ID</th>
				<th>酒店名称</th>
				<th>账户ID</th>
				<th>账户名称</th>
				<th>联系电话</th>
				<th>账户角色</th>
				<th>可提现金额</th>
				<th>提现金额</th>
				<th>提现后账户余额</th>
				<th>提现申请日期</th>
                <th>员工编号</th>
				<th>户名</th>
				<th>账号</th>
				<th>开户行</th>
				<th>支行</th>
				<th>提现状态</th>
				<shiro:hasAnyRoles name="HOTEL_TXSH_OPERATOR,HOTEL_TXSH_HR,HOTEL_TXSH_FI">
				<th>提现审核</th>
				</shiro:hasAnyRoles>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccountWithdrawcash">
			<tr>
				<td nowrap>
                    <input id="checkboxId" type="checkbox" name="check" value="${settlementAccountWithdrawcash.id}" /> 
                </td>
                <td nowrap>
	                ${settlementAccountWithdrawcash.id}
                </td>
				<td nowrap>
					<a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/form?id=${settlementAccountWithdrawcash.id}">
					${settlementAccountWithdrawcash.proname}</a>
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.cityname}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.disname}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.hotelid}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.hotelname}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.accountid}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.accountname}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.accountphone}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccountWithdrawcash.accountrole, 'AccountRoleEnums', '')}
				</td>
				<td nowrap style="text-align:right;">
					${settlementAccountWithdrawcash.strPrebalance}
				</td>
				<td nowrap style="text-align:right;">
					${settlementAccountWithdrawcash.strCash}
				</td>
				<td nowrap style="text-align:right;">
					${settlementAccountWithdrawcash.strBalance}
				</td>
				<td nowrap>
					<fmt:formatDate value="${settlementAccountWithdrawcash.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.empNo}
				</td>
                <td nowrap>
					${settlementAccountWithdrawcash.name}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.account}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.bank}
				</td>
				<td nowrap>
					${settlementAccountWithdrawcash.bankbranch}
				</td>
				<td nowrap>
					${fns:getDictLabel(settlementAccountWithdrawcash.status, 'WithdrawStatusEnums', '')}
				</td>
				<shiro:hasAnyRoles name="HOTEL_TXSH_OPERATOR,HOTEL_TXSH_HR,HOTEL_TXSH_FI">
				<td nowrap>
                    <c:choose>
                        <c:when test="${settlementAccountWithdrawcash.status == 1}">
                        <shiro:hasRole name="HOTEL_TXSH_OPERATOR">
                          <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您要确认该提现申请吗？', $(this).attr('confirmaction'))">运营确认</a>
                        | <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您要驳回该提现申请吗？', $(this).attr('confirmaction'))">运营驳回</a>
                        </shiro:hasRole>
                        <shiro:lacksRole name="HOTEL_TXSH_OPERATOR"><font color="#ff0000">【等待运营审核】</font></shiro:lacksRole>
                        </c:when>
                        <c:when test="${settlementAccountWithdrawcash.status == 10}">
                        <shiro:hasRole name="HOTEL_TXSH_HR">
                          <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您要确认该提现申请吗？', $(this).attr('confirmaction'))">人事确认</a>
                        | <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您要驳回该提现申请吗？', $(this).attr('confirmaction'))">人事驳回</a>
                        </shiro:hasRole>
                        <shiro:lacksRole name="HOTEL_TXSH_HR"><font color="#ff0000">【等待人事审核】</font></shiro:lacksRole>
                        </c:when>
                        <c:when test="${settlementAccountWithdrawcash.status == 12 || settlementAccountWithdrawcash.status == 2}">
                        <shiro:hasRole name="HOTEL_TXSH_FI">
                          <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您确定要打款吗？', $(this).attr('confirmaction'))">财务打款</a>
                        | <a href="javascript:;" confirmaction="${ctx}/accntwithdraw/settlementAccountWithdrawcash/update?id=${settlementAccountWithdrawcash.id}" onclick="return confirmx('您要驳回该提现申请吗？', $(this).attr('confirmaction'))">财务驳回</a>
                        </shiro:hasRole>
                        <shiro:lacksRole name="HOTEL_TXSH_FI"><font color="#ff0000">【等待财务审核】</font></shiro:lacksRole>
                        </c:when>
                        <c:otherwise><font color="#008000">【审核流程结束】</font></c:otherwise>
                    </c:choose>
                </td>
				</shiro:hasAnyRoles>
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
	<div class="pagination">${page}</div>
</body>
</html>
