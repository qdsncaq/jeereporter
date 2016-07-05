<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>销售提现查询列表</title>
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
         * 导出销售提现申请记录
         */
        function exportexcelSalerQuery(){
            $("#searchForm").attr("action",ctx+"/accntwithdraw/settlementAccountWithdrawcash/exportexcelSalerQuery");
            $("#searchForm").submit();
            return false;
        }
        
        /**
         * 分页查询
         */
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").attr("action",ctx+"/accntwithdraw/settlementAccountWithdrawcash/listOfSalerQuery");
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfSalerQuery">销售提现查询列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="settlementAccountWithdrawcash" action="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfSalerQuery" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li><label>省份：</label>
                <form:input path="proname" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li><label>城市：</label>
                <form:input path="cityname" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li><label>区域：</label>
                <form:input path="disname" htmlEscape="false" maxlength="50" class="input-medium"/>
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
            <li><label>申请时间：</label>
                <input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccountWithdrawcash.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
                <input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccountWithdrawcash.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li>
            <li><label>可提现金额：</label>
                <form:input type="number" path="beginPrebalance" htmlEscape="false" class="input-medium"/>
                - <form:input type="number" path="endPrebalance" htmlEscape="false" class="input-medium"/>
            </li>
            <li><label>提取金额：</label>
                <form:input type="number" path="beginCash" htmlEscape="false" class="input-medium"/>
                - <form:input type="number" path="endCash" htmlEscape="false" class="input-medium"/>
            </li>
            <li><label>提现后余额：</label>
                <form:input type="number" path="beginBalance" htmlEscape="false" class="input-medium"/>
                - <form:input type="number" path="endBalance" htmlEscape="false" class="input-medium"/>
            </li>
            <li><label>提现状态：</label>
                <form:select path="status" class="input-medium">
                    <form:option value="" label="--所有提现状态--"/>
                    <form:options items="${fns:getDictList('WithdrawStatusEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li class="clearfix"></li>
            <li class="btns"><input onclick="return page();"  id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcelSalerQuery()" value="导出excel"/></li>      
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
                <th>账户ID</th>
                <th>账户名称</th>
                <th>联系电话</th>
                <th>账户角色</th>
                <th>可提现金额</th>
                <th>提取金额</th>
                <th>提现后余额</th>
                <th>申请时间</th>
                <th>员工编号</th>
                <th>户名</th>
                <th>账号</th>
                <th>开户行</th>
                <th>支行</th>
                <th>提现状态</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="settlementAccountWithdrawcash">
            <tr>
                <td nowrap>
                    <input id="checkboxId" type="checkbox" name="check" value="${settlementAccountWithdrawcash.id}" /> 
                </td>
                <td nowrap>
                    <a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/form?id=${settlementAccountWithdrawcash.id}">
                    ${settlementAccountWithdrawcash.id}
                </td>
                <td nowrap>
                    ${settlementAccountWithdrawcash.proname}</a>
                </td>
                <td nowrap>
                    ${settlementAccountWithdrawcash.cityname}
                </td>
                <td nowrap>
                    ${settlementAccountWithdrawcash.disname}
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
