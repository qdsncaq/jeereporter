<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>OTA注册管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/distributor/ota/">OTA列表</a></li>
    <shiro:hasPermission name="gds:ota:edit">
        <li><a href="${ctx}/distributor/ota/form">OTA添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="distributorconfig" action="${ctx}/distributor/ota/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>OTA编码：</label>
            <form:input path="otatype" htmlEscape="false" class="input-small"/>
        </li>
       <%-- <li><label>OTA名称：</label>
            <form:input path="name" htmlEscape="false" class="input-small"/>
        </li>--%>
        <li><label>渠道名称：</label>
            <form:select path="channelid" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${channeldic}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>销售方式：</label>
            <form:select path="saletype" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${saletypedic}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>OTA编码</th>
        <%--<th>OTA名称</th>--%>
        <th>渠道</th>
        <th>佣金比例</th>
        <th>销售方式</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>
                <a href="${ctx}/distributor/ota/form?id=${item.id}">
                        ${item.otatype}
                </a>
            </td>
           <%-- <td>
                    ${item.name}
            </td>--%>
            <td>
                    ${channeldkey.get(item.channelid)}
            </td>
            <td>
                    ${item.commissionrate}
            </td>
            <td>
                <c:if test="${item.saletype!=null && item.saletype.trim().length() > 0}">
                    ${saletypekey.get(item.saletype)}
                </c:if>
            </td>
            <td>
                <a href="${ctx}/distributor/ota/assign?id=${item.id}">关联酒店</a>&nbsp;&nbsp;
                <shiro:hasPermission name="gds:ota:edit">
                    <a href="${ctx}/distributor/ota/form?id=${item.id}">修改</a>&nbsp;&nbsp;
                    <a href="${ctx}/distributor/ota/delete?id=${item.id}"
                   onclick="return confirmx('确认要删除该OTA注册信息吗？', this.href)">删除</a>
                </shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>