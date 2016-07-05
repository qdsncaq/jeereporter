<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>合同管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/contract/hotelContract/">合同列表</a></li>
        <shiro:hasPermission name="contract:hotelContract:edit"><li><a href="${ctx}/contract/hotelContract/form">合同添加</a></li></shiro:hasPermission>
    </ul>
    <form:form id="searchForm" modelAttribute="hotelContract" action="${ctx}/contract/hotelContract/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li><label>合同编写人：</label>
                <form:input path="author" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li><label>订单状态</label>
                <form:select path="status" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('HotelContractStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>订单类型</label>
                <form:select path="type" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('HotelContractType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>创建时间：</label>
                <input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${hotelContract.createtime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>id</th>
                <th>合同编写人</th>
                <th>合同状态</th>
                <th>合同类型</th>
                <th>合同创建时间</th>
                <th>合同版本号</th>
               <%--  <shiro:hasPermission name="contract:hotelContract:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="hotelContract">
            <tr>
                <td>
                <a href="${ctx}/contract/hotelContract/form?id=${hotelContract.id}">
                    ${hotelContract.id}
                </a>
                </td>
                <td>
                    ${hotelContract.author}
                </td>
                <td>
                    ${fns:getDictLabel(hotelContract.status, 'HotelContractStatus', '')}
                </td>
                <td>
                    ${fns:getDictLabel(hotelContract.type, 'HotelContractType', '')}
                </td>
                <td>
                    <fmt:formatDate value="${hotelContract.createtime}" pattern="yyyy-MM-dd"/>
                </td>
                  <td>
                    ${hotelContract.version}
                </td>
               <%--  <shiro:hasPermission name="contract:hotelContract:edit"><td>
                    <a href="${ctx}/contract/hotelContract/form?id=${hotelContract.id}">修改</a>
                    <a href="${ctx}/contract/hotelContract/delete?id=${hotelContract.id}" onclick="return confirmx('确认要删除该合同吗？', this.href)">删除</a>
                </td></shiro:hasPermission> --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>