<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>酒店提现申请列表</title>
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
        //提交财务
        function throughaudit(){
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
            // alert(length22);return false;
            if(selectedIds==""){
                alertx("请您先选择酒店提现申请数据！");
                return;
            }else{
                selectedIds=selectedIds.substr(0, selectedIds.length-1);
                // alert(length22);return false;
                confirmx('确认审核通过吗?共勾选'+length22+'条数据.', function(){
                        $.post('${ctx }/accntwithdraw/settlementAccountWithdrawcash/update',
                            {ids : selectedIds},
                            function(result){
                                // alert(result);return false;
                                if(result == -1){
                                    loading();
                                    alertx("没有选择酒店提现申请数据！");
                                    window.location.reload(50000);return false;//刷新当前页面.return false;
                                } else if(result > null){
                                    loading();
                                    alertx("酒店提现申请审核通过"+result+"条.");
                                    window.location.reload(50000);return false;//刷新当前页面.return false;
                                } else{
                                    loading();
                                    alertx("没有可审核的数据.");
                                    window.location.reload(50000);return false;//刷新当前页面.return false;
                                }
                            }
                        );
                });
            }
        }
        //驳回
        function throughauditNo(){
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
            // alert(selectedIds);return false;
            if(selectedIds==""){
                alertx("请您先选择要驳回的酒店提现申请数据！");
                return;
            }else{
                selectedIds=selectedIds.substr(0, selectedIds.length-1);
                var url = "${ctx }/accntwithdraw/settlementAccountWithdrawcash/updateno";
                // alert(confirmx('确认要删除该accntwithdraw吗？'));return false;
                confirmx('确认驳回吗?共勾选'+length+'条数据.', function(){
                    $.post( url,
                        {ids : selectedIds},
                        function(result){
                            // alert("data="+result);return false;
                            if(result == -1){
                                loading();
                                alertx("没有选择要驳回的数据！");
                                // alert("没有选择要驳回的数据！");
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                            }if(result > 0){
                                loading();
                                alertx("已完成!驳回记录共"+result+"条.");
                                // alert("已完成!驳回记录共"+result+"条.");
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                            } else {
                                loading();
                                alertx("没有记录驳回.");
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                                return false;
                            }
                        }
                    );
                });
            }
        }
        // 导出酒店提现申请记录
        function exportexcelHotel(){
            $("#searchForm").attr("action",ctx+"/accntwithdraw/settlementAccountWithdrawcash/exportexcelHotel");
            $("#searchForm").submit();
            return false;
        }
        // 已打款
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
            // alert(selectedIds);return false;
            if(selectedIds==""){
                alertx("没有选择数据！");
                return;
            }else{
                selectedIds=selectedIds.substr(0, selectedIds.length-1);
                confirmx('是否已经打款?共勾选'+length+'条数据.', function(){
                    $.post('${ctx }/accntwithdraw/settlementAccountWithdrawcash/paydone',
                        {ids : selectedIds},
                        function(result){
                            // alert("data="+result);return false;
                            if(result == -1){
                                loading();
                                alertx("没有选择数据！");
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                            } else if(result > 0){
                                loading();
                                alertx("完成!修改打款记录"+result+"条.");
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                            } else {
                                loading();
                                alertx("没有记录可修改为已打款.");
                                // window.location.reload();//刷新当前页面.
                                window.location.reload(50000);return false;//刷新当前页面.return false;
                            }
                        }
                    );
                });
            }
        }
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
        <li class="active"><a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfHotel">酒店提现列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="settlementAccountWithdrawcash" action="${ctx}/accntwithdraw/settlementAccountWithdrawcash/listOfHotel" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <%-- <li><label>省编码：</label>
                <form:input path="procode" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li> --%>
            <li><label>省份：</label>
                <form:input path="proname" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <%-- <li><label>市编码：</label>
                <form:input path="citycode" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li> --%>
            <li><label>城市：</label>
                <form:input path="cityname" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <%-- <li><label>区编码：</label>
                <form:input path="discode" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li> --%>
            <li><label>区域：</label>
                <form:input path="disname" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <%-- <c:if test="${settlementAccountWithdrawcash.accountrole == 1}"> --%>
                <li><label>酒店ID：</label>
                    <form:input path="hotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
                </li>
                <%-- <li><label>PMS编码：</label>
                    <form:input path="hotelpms" htmlEscape="false" maxlength="100" class="input-medium"/>
                </li> --%>
                <li><label>酒店名称：</label>
                    <form:input path="hotelname" htmlEscape="false" maxlength="500" class="input-medium"/>
                </li>
            <%-- </c:if> --%>
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
            <%-- <li><label>卡号：</label>
                <form:input path="account" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li> --%>
            <li><label>开户行：</label>
                <form:input path="bank" htmlEscape="false" maxlength="100" class="input-medium"/>
            </li>
            <%-- <li><label>支行：</label>
                <form:input path="bankbranch" htmlEscape="false" maxlength="100" class="input-medium"/>
            </li> --%>
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
<%--             <li><label>账户角色：</label>
                <form:select path="accountrole" class="input-medium">
                    <form:option value="" label="--请选择--"/>
                    <form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li> --%>
            <li><label>提现状态：</label>
                <form:select path="status" class="input-medium">
                    <form:option value="" label="所有提现状态"/>
                    <form:option value="1" label="提交申请"/>
                    <form:option value="2" label="财务确认"/>
                    <form:option value="3" label="财务打款"/>
                    <form:option value="4" label="财务驳回"/>
                </form:select>
            </li>
            <%-- <li><label>更新时间：</label>
                <input name="beginUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccountWithdrawcash.beginUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
                <input name="endUpdateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${settlementAccountWithdrawcash.endUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li> --%>
                <li class="clearfix"></li>
            <li class="btns"><input onclick="return page();"  id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="btns"><input class="btn btn-primary" type="button" onclick="exportexcelHotel()" value="导出excel"/></li>
            
            <%-- 
            <li class="btns"><input id="ThroughAudit" class="btn btn-primary" type="button" onclick="throughaudit()" value="通过"/></li>
            <li class="btns"><input id="ThroughAuditno" class="btn btn-primary" type="button" onclick="throughauditNo()" value="驳回"/></li>
            <shiro:hasRole name="SC_FI_BOSSREVIEWED">
                <li class="btns"><input id="ThroughAudit" class="btn btn-primary" type="button" onclick="throughaudit()" value="通过"/></li>
                <li class="btns"><input id="ThroughAuditno" class="btn btn-primary" type="button" onclick="throughauditNo()" value="驳回"/></li>
            </shiro:hasRole>
            <shiro:hasRole name="SC_FI_SALESREVIEWED">
                <li class="btns"><input id="ThroughAudit" class="btn btn-primary" type="button" onclick="throughaudit()" value="通过"/></li>
                <li class="btns"><input id="ThroughAuditno" class="btn btn-primary" type="button" onclick="throughauditNo()" value="驳回"/></li>
            </shiro:hasRole>
             --%>
            <shiro:hasAnyRoles name="SC_FI_ALLSREVIEWED,SC_FI_BOSSREVIEWED">
                <li class="btns"><input id="ThroughAudit" class="btn btn-primary" type="button" onclick="throughaudit()" value="通过"/></li>
                <li class="btns"><input id="ThroughAuditno" class="btn btn-primary" type="button" onclick="throughauditNo()" value="驳回"/></li>
            </shiro:hasAnyRoles>
            
            <shiro:hasRole name="SC_FI_PAYDONE">
                <li class="btns"><input class="btn btn-primary" type="button" onclick="paydone()" value="打款"/></li>
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
                <!-- <th>省编码</th> -->
                <th>城市</th>
                <!-- <th>区编码</th> -->
                <th>区域</th>
                <!-- <th>市编码</th> -->
                <%-- <c:if test="${settlementAccountWithdrawcash.accountrole == 1}"> --%>
                    <th>酒店ID</th>
                    <!-- <th>酒店PMS编码</th> -->
                    <th>酒店名称</th>
                <%-- </c:if> --%>
                <th>账户ID</th>
                <th>账户名称</th>
                <th>联系电话</th>
                <th>账户角色</th>
                <th>可提现金额</th>
                <th>提取金额</th>
                <th>提现后余额</th>
                <th>申请时间</th>
                <!-- <th>更新时间</th> -->
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
                <%-- <td><a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/form?id=${settlementAccountWithdrawcash.id}">
                    ${settlementAccountWithdrawcash.procode}
                </a></td> --%>
                <td nowrap>
                    ${settlementAccountWithdrawcash.proname}</a>
                </td>
                <%-- <td>
                    ${settlementAccountWithdrawcash.citycode}
                </td> --%>
                <td nowrap>
                    ${settlementAccountWithdrawcash.cityname}
                </td>
                <%-- <td>
                    ${settlementAccountWithdrawcash.discode}
                </td> --%>
                <td nowrap>
                    ${settlementAccountWithdrawcash.disname}
                </td>
                <%-- <c:if test="${settlementAccountWithdrawcash.accountrole == 1}"> --%>
                    <td nowrap>
                        ${settlementAccountWithdrawcash.hotelid}
                    </td>
                    <%-- <td>
                        ${settlementAccountWithdrawcash.hotelpms}
                    </td> --%>
                    <td nowrap>
                        ${settlementAccountWithdrawcash.hotelname}
                    </td>
                <%-- </c:if> --%>
                <%-- <td><a href="${ctx}/accntwithdraw/settlementAccountWithdrawcash/form?id=${settlementAccountWithdrawcash.id}">
                    ${settlementAccountWithdrawcash.accountid}
                </a></td> --%>
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
                <%-- <td>
                    <fmt:formatDate value="${settlementAccountWithdrawcash.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td> --%>
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
