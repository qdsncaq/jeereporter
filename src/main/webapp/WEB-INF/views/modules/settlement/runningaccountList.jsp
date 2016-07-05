<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>结算中心流水账</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
    <form:form id="searchForm" action="${ctx}/scadmin/runningaccount/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <%--
        <div>
            <label>流水ID：</label><input id="id" name="id" type="text" maxlength="50" class="input-mini" value="${item.id}"/>
            <label>用户ID：</label><input id="accountid" name="accountid" type="text" maxlength="50" class="input-mini" value="${log.createBy.id}"/>
            <label>URI：</label><input id="requestUri" name="requestUri" type="text" maxlength="50" class="input-mini" value="${log.requestUri}"/>
        </div>
        <div style="margin-top:8px;">
            <label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
            <label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
            &nbsp;<label for="exception"><input id="exception" name="exception" type="checkbox"${log.exception eq '1'?' checked':''} value="1"/>只查询异常信息</label>
            &nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
        </div>
        --%>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>ID</th>
            <th>账户ID</th>
            <th>订单ID</th>
            <th>酒店ID</th>
            <th>酒店名称</th>
            <th>业务类型</th>
            <th>金额</th>
            <th>订单时间</th>
            <th>账户角色</th>
          </tr>
        </thead>
        <tbody><%request.setAttribute("strEnter", "\n");request.setAttribute("strTab", "\t");%>
        <c:forEach items="${page.list}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.accountid}</td>
                <td>${item.orderid}</td>
                <td>${item.hotelid}</td>
                <td>${item.hotelname}</td>
                <td>${item.strBiztype}</td>
                <td>${item.ordertotal}</td>
                <td><fmt:formatDate value="${item.ordertime}" type="both"/></td>
                <td>${item.strAccountrole}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>