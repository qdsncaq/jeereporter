<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>accntbill管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
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
		// 导出
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/qunarexportexcel");
			$("#searchForm").submit();
		    return false;
        }
		// 结算===收款
		function bill(){
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/bill");
			$("#searchForm").submit();
		    return false;
			var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		// alertx(selectedIds);return false;
	   		if(selectedIds==""){
	   			alertx("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			$.post('${ctx }/accntbill/settlementAccntBill/bill',
						{ids : selectedIds},
						function(result){
							// alertx(result);return false;
							if(result == 100){
								alertx("没有选择要结算的数据！");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							} else if(result > null){
								alertx("结算数据"+result+"条.");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							} else{
								alertx("结算数据0条.");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							}
						}
				);
	   		} 
		}
		//提交财务
		function throughaudit(){
	     	var tableElement = document.getElementById("contentTable");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		// alertx(selectedIds);return false;
	   		if(selectedIds==""){
	   			alertx("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			$.post('${ctx }/accntwithdraw/settlementAccountWithdrawcash/update',
						{ids : selectedIds},
						function(result){
							// alertx(result);return false;
							if(result == 100){
								alertx("没有选择要结算的数据！");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							} else if(result > null){
								alertx("审核通过！"+result+"条.");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							} else{
								alertx("没有可审核的数据.");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							}
						}
				);
	   		}
		}
		// 重新结算
		function rebill(id){
			// alertx(id);
			$.post('${ctx }/accntbill/settlementAccntBill/qunarrebill',
					{id : id},
					function(result){
						// alertx(result);return false;
						if(result ){
							alertx("重新结算成功！");
							window.location.reload(50000);return false;//刷新当前页面.return false;
						} else{
							alertx("重新结算异常.");
							window.location.reload(50000);return false;//刷新当前页面.return false;
						}
					}
			);
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/qunarlist");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accntbill/settlementAccntBill/qunarlist">去哪儿周账单列表</a></li>
		<shiro:hasPermission name="accntbill:settlementAccntBill:edit"><li><a href="${ctx}/accntbill/settlementAccntBill/form">去哪儿周账单添加</a></li></shiro:hasPermission>
	</ul>
	
	<%-- <div id="content" hidden="" class="divclass">
	  <form:form id="contentsearchForm" modelAttribute="settlementBillInfo" action="${ctx}/billinfo/settlementBillInfo/save" method="post" class="breadcrumb form-search">
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">账&nbsp;&nbsp;单&nbsp;号: </label> 
			<input type="text" name="contentid" id="contentid" value=""  maxlength="50" disabled="disabled" />
		</div>
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">结算金额: </label>
			<input type="text" name="contentscsum" id="contentscsum" maxlength="50" disabled="disabled" />
		</div>
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">收款金额: </label>
			<input type="text" id="contentbillsum" name="contentbillsum" onkeyup="value=value.replace(/[^\-?\d.]/g,'')" /> (限制文本框只能输入正数，负数，小数)
		</div>
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">收款日期: </label>
			<input id="contentbilltime" name="contentbilltime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
		</div>
		<div class="control-group" style="padding-left: 19px; padding-top: 15px; padding-bottom: 5px;">
			<label class="control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注: </label>
			<textarea rows="" cols="" id="contentremarks" name="contentremarks"></textarea>
		</div>
		<div style="padding-top: 15px; padding-bottom: 5px; text-align: center;">
			<input id="savebtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	  </form:form>
	</div> --%>
	
	<form:form id="searchForm" modelAttribute="settlementAccntBill" action="${ctx}/accntbill/settlementAccntBill/qunarlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始日期：</label>
				<input name="beginBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.beginBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.endBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="beginEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.beginEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${settlementAccntBill.endEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>客户ID：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<%-- <li><label>结算方式：</label>
				<form:select path="sctype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SettlementTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<%-- <li><label>付款方式：</label>
				<form:select path="paytype" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('TripAccntTypeEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>财务状态：</label>
				<form:select path="scstate" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SettlementStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>结算总额：</label>
				<form:input path="beginScsum" htmlEscape="false" class="input-medium"/>
				- <form:input path="endScsum" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>订单总额：</label>
				<form:input path="beginBalance" htmlEscape="false" class="input-medium"/>
				- <form:input path="endBalance" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input onclick="return page();" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="exportexcel()" id="" class="btn btn-primary" type="button" value="导出excel"/></li>
			<!-- <li class="btns"><input onclick="bill()" id="billbtnSubmit" class="btn btn-primary" type="button" value="结算"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
				<th>账单号</th>
				<th>开始日期</th>
		        <th>结束日期</th>
		        <th>账户ID</th>
		        <th>客户名称</th>
		        <th>业务类型</th>
		        <th>付款方式</th>
		        <th>财务状态</th>
		        <th>结算金额</th>
		        <th>订单总额</th>
		        <th>折扣金额</th>
		        <th>调整金额</th>
		        <th>充值卡抵扣金额</th>
		        <th>操作</th>
				<shiro:hasPermission name="accntbill:settlementAccntBill:edit"><th>修改</th></shiro:hasPermission>
				<shiro:hasRole name="SC_FI_BILLCONFIRM"><th>财务操作</th></shiro:hasRole>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlementAccntBill">
			<tr>
				<td>
                   <input id="checkboxId" type="checkbox" name="check" value="${settlementAccntBill.id}" /> 
                </td>
                <td>
                	<a href="${ctx}/accntbill/settlementAccntBill/qunarform?id=${settlementAccntBill.id}">
						${settlementAccntBill.id}
					</a>
                </td>
				<td>
					<fmt:formatDate value="${settlementAccntBill.beginDate}" type="date" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${settlementAccntBill.endDate}" type="date" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${settlementAccntBill.accountid}
				</td>
				<td>
					${settlementAccntBill.hotelname}
				</td>
				<td>
                    ${fns:getDictLabel(settlementAccntBill.biztype, 'BizTypeEnums', '')}
                </td>
				<td>
					${fns:getDictLabel(settlementAccntBill.paytype, 'TripAccntTypeEnums', '')}
				</td>
				<td>
					${fns:getDictLabel(settlementAccntBill.scstate, 'SettlementStatusEnums', '')}
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strScsum.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strScsum}
					<c:if test="${settlementAccntBill.strScsum.startsWith(\"-\") }"></font></c:if>
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strBalance.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strBalance}
					<c:if test="${settlementAccntBill.strBalance.startsWith(\"-\") }"></font></c:if>
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strDiscount.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strDiscount}
					<c:if test="${settlementAccntBill.strDiscount.startsWith(\"-\") }"></font></c:if>
				</td>
				<td style="text-align:right;">
				    <c:if test="${settlementAccntBill.strAdjustvalue.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strAdjustvalue}
					<c:if test="${settlementAccntBill.strAdjustvalue.startsWith(\"-\") }"></font></c:if>
				</td>
				<td style="text-align:right;">
					<c:if test="${settlementAccntBill.strDeposit.startsWith(\"-\") }"><font color="red"></c:if>
					${settlementAccntBill.strDeposit}
					<c:if test="${settlementAccntBill.strDeposit.startsWith(\"-\") }"></font></c:if>
				</td>
				<td>
                    <a href="${ctx }/accntdetail/settlementAccntDetail/qunarfinddetails?accountid=${settlementAccntBill.accountid}&accountrole=${settlementAccntBill.accountrole}&beginLeftDate=<fmt:formatDate value="${settlementAccntBill.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&endLeftDate=<fmt:formatDate value="${settlementAccntBill.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&biztype=${settlementAccntBill.biztype}">明细</a>
	            	<c:if test="${settlementAccntBill.accountstate == 1 }"> | <a href="javascript:;" onclick="rebill(${settlementAccntBill.id })">重新结算</a></c:if>
	            	<shiro:hasPermission name="accntbill:settlementAccntBill:operate">
						 | <a href="${ctx}/billinfo/settlementBillInfo/form?billid=${settlementAccntBill.id }&billscsum=${settlementAccntBill.scsum}">收款</a>
						 | <a href="${ctx}/billinfo/settlementBillInfo/list?billid=${settlementAccntBill.id }">收款明细</a>
 					</shiro:hasPermission>
	            </td>
				<shiro:hasPermission name="accntbill:settlementAccntBill:edit"><td>
    				<a href="${ctx}/accntbill/settlementAccntBill/form?id=${settlementAccntBill.id}">修改</a>
					<a href="${ctx}/accntbill/settlementAccntBill/delete?id=${settlementAccntBill.id}" onclick="return confirmx('确认要删除该accntbill吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
                <shiro:hasRole name="SC_FI_BILLCONFIRM">
                    <td nowrap>
                        <c:choose>
                            <c:when test="${settlementAccntBill.accountstate == 1}">
                                <a href="${ctx}/accntbill/settlementAccntBill/confirm?id=${settlementAccntBill.id}">确认账单</a>
                            </c:when>
                            <c:when test="${settlementAccntBill.accountstate == 2}">
                                财务已确认
                            </c:when>
                            <c:when test="${settlementAccntBill.accountstate == 3}">
                                酒店已确认
                            </c:when>
                            <c:otherwise>
                                未知
                            </c:otherwise>
                        </c:choose>
                    </td>
                </shiro:hasRole>
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