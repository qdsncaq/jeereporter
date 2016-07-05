<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店老板周账单</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
        jQuery(document).ready(function() {
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
         * 重新结算
         */
        function confirmRebill(id) {
            return confirmx("您真的要重新生成选择的账单吗？账单数据将会删除，您要继续吗？", "javascript:rebill(" + id + ");");
        };
        function rebill(id){
            $.post('${ctx}/accntbill/settlementAccntBill/hotelrebill',
                    {id : id},
                    function(result){
                        if(result ){
                            alertx("酒店老板周账单重新结算成功！");
                            window.location.reload(50000);return false;//刷新当前页面.return false;
                        } else{
                            alertx("酒店老板周账单重新结算异常.");
                            window.location.reload(50000);return false;//刷新当前页面.return false;
                        }
                    }
            );
        };
        
        /**
         * 翻页
         */
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/hotellist");
			$("#searchForm").submit();
        	return false;
        };
        /**
         * 导出
         */
        function exportexcel(){
            $("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/exphotellist2excel");
            $("#searchForm").submit();
            return false;
        };
        /**
         * 查询
         */
        function query(){
            $("#searchForm").attr("action",ctx+"/accntbill/settlementAccntBill/hotellist");
            $("#searchForm").submit();
            return false;
        };
        /**
         * 批量确认
         */
        function batchConfirmBill() {
            var dataRows = document.getElementsByName("check");
            if (dataRows.length == 0) {
                alertx("没有账单数据。");
                return false;
            }
            var tableElement = document.getElementById("table1");
            var size = tableElement.rows.length;
            var selectedIds = "";
            var selectedCounts = 0;
            for (var i = 0; i < size - 1; i++) {
                var checkbox = dataRows[i];
                if (checkbox.checked === true) {
                    selectedCounts ++;
                    if (selectedIds != "") {
                        selectedIds += "," + checkbox.value;
                    } else {
                        selectedIds += checkbox.value;
                    }
                }
            }
            if (selectedIds === "") {
                alertx("没有选择要确认的酒店账单！");
                return false;
            }
            var batchconfirmUrl = "${ctx}/accntbill/settlementAccntBill/batchconfirm?billtype=HOTEL&ids=" + selectedIds;
            return confirmx("您真的要确认所选择的" + selectedCounts + "条酒店账单吗?", batchconfirmUrl);
        };
	</script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/accntbill/settlementAccntBill/hotellist">酒店老板周账单列表</a></li>
        <shiro:hasPermission name="accntbill:settlementAccntBill:edit"><li><a href="${ctx}/accntbill/settlementAccntBill/form">酒店老板周账单添加</a></li></shiro:hasPermission>
    </ul>
		<form:form id="searchForm" modelAttribute="settlementAccntBill" action="${ctx}/accntbill/settlementAccntBill/hotellist" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
	            <li><label>酒店ID：</label>
	                <form:input path="hotelid" htmlEscape="false" value="${settlementAccntBill.hotelid}" maxlength="50" class="input-medium"/>
	            </li>
 	            <li><label>酒店名称：</label>
	                <form:input path="hotelname" htmlEscape="false" value="${settlementAccntBill.hotelname }" maxlength="500" class="input-medium"/>
	            </li>
 	            <li><label>开始日期：</label>
	                <input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
	                    value="<fmt:formatDate value="${settlementAccntBill.beginDate}" pattern="yyyy-MM-dd"/>"
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
	            </li>
	            <li><label>结束日期：</label>
	                <input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
	                    value="<fmt:formatDate value="${settlementAccntBill.endDate}" pattern="yyyy-MM-dd"/>"
	                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
	            </li>
	            <li><label>金额：</label>
                    <form:input type="number" path="beginBalance" htmlEscape="false" class="input-medium"/>-
                    <form:input type="number" path="endBalance" htmlEscape="false" class="input-medium"/>
                </li>
	            <li class="btns"><input onclick="return page();" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
                <li class="btns"><input onclick="exportexcel()" id="exportbtn" class="btn btn-primary" type="button" value="导出excel"/></li>
                <shiro:hasRole name="SC_FI_BILLCONFIRM">
                <li class="btns"><input onclick="batchConfirmBill()" id="batchConfirmbtn" class="btn btn-primary" type="button" value="确认选择账单"/></li>
                </shiro:hasRole>
                <li class="clearfix"></li>
			</ul>
		</form:form>
		<table  id="table1" class="table table-bordered table-hover"  style="margin-top:10px;">
		    <thead>
                <tr>
                    <th style="width:10px;"><input type="checkbox" name="all" id="all" /></th>
			        <th>账户ID</th>
			        <th>酒店ID</th>
			        <th>酒店名称</th>
			        <th>开始日期</th>
			        <th>结束日期</th>
			        <th>类型</th>
			        <th>结算状态</th>
			        <th>金额</th>
			        <th>操作</th>
			        <shiro:hasRole name="SC_FI_BILLCONFIRM"><th>财务操作</th></shiro:hasRole>
			    </tr>
			</thead>
			<tbody><%request.setAttribute("strEnter", "\n");request.setAttribute("strTab", "\t");%>
			    <c:forEach items = "${page.list}" var = "item">
	                <tr>
	                    <td><input id="checkboxId" type="checkbox" name="check" value="${item.id}" /></td>
	                    <td>${item.accountid}</td>
	                    <td>${item.hotelid}</td>
	                    <td>${item.hotelname}</td>
	                	<td><fmt:formatDate value="${item.beginDate}" type="date" pattern="yyyy-MM-dd"/></td>
			            <td><fmt:formatDate value="${item.endDate}" type="date" pattern="yyyy-MM-dd"/></td>
	                    <td>${fns:getDictLabel(item.biztype, 'BizTypeEnums', '')}</td>
	                    <td>${fns:getDictLabel(item.scstate, 'SettlementStatusEnums', '')}</td>
			            <td style="text-align:right;">${item.strBalance}</td>
                        <td>
                            <a href="${ctx }/accntdetail/settlementAccntDetail/finddetails?accountid=${item.accountid}&accountrole=${item.accountrole}&beginLeftDate=<fmt:formatDate value="${item.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&endLeftDate=<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&biztype=${item.biztype}">明细</a>
                            <shiro:hasRole name="SC_FI_BILLCONFIRM">
                            	<c:if test="${item.accountstate == 1 }"> | <a href="javascript:;" onclick="confirmRebill(${item.id })">重新结算</a></c:if>
                            </shiro:hasRole>
                        </td>
                
                        <shiro:hasRole name="SC_FI_BILLCONFIRM">
                            <td nowrap>
                                <c:choose>
                                    <c:when test="${item.accountstate == 1}">
                                        <a href="${ctx}/accntbill/settlementAccntBill/confirm?id=${item.id}">确认账单</a>
                                    </c:when>
                                    <c:when test="${item.accountstate == 2}">
                                        财务已确认
                                    </c:when>
                                    <c:when test="${item.accountstate == 3}">
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