<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>关联酒店</title>
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
    <li><a href="${ctx}/distributor/ota/">OTA列表</a></li>
    <li class="active"><a href="${ctx}/distributor/ota/assign?id=${distributorconfig.id}">关联酒店</a></li>
</ul>
<div class="container-fluid breadcrumb">
    <div class="row-fluid span12">
        <span class="span3">OTA编码: <b>${distributorconfig.otatype}</b></span>
        <span class="span3">渠道:  <b>${channelname}</b></span>
        <span class="span3"><%--OTA名称: <b>${distributorconfig.name}</b>--%></span>
        <span class="span3"></span>
    </div>
</div>
<form:form id="searchForm" modelAttribute="searchform" action="${ctx}/distributor/ota/assign?id=${distributorConfig.id}" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>酒店编码：</label>
            <input type="number" name="q_hotelid" value="${searchform.q_hotelid}">
            <%--<form:input path="q_hotelid" htmlEscape="false" class="input-small"/>--%>
        </li>
            <%-- <li><label>OTA名称：</label>
                 <form:input path="name" htmlEscape="false" class="input-small"/>
             </li>--%>
        <li><label>酒店名称：</label>
            <input type="text" name="q_hotelname" value="${searchform.q_hotelname}">
            <%--<form:input path="q_hotelname" htmlEscape="false" class="input-small"/>--%>
        </li>
        <li>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<shiro:hasPermission name="gds:ota:edit">
<div class="breadcrumb">
    <form id="assignHotelForm" action="${ctx}/distributor/ota/assigndistributorconfig" method="post" class="hide">
        <input type="hidden" name="configid" value="${distributorconfig.id}"/>
        <input id="ids" type="hidden" name="ids" value=""/>
    </form>
        <input id="assignButton" class="btn btn-primary" type="submit" value="添加关联"/>
        <input id="deleteButton" class="btn btn-primary" type="submit" value="批量删除"/>
    <script type="text/javascript">
        $("#assignButton").click(function () {
            top.$.jBox.open("iframe:${ctx}/distributor/ota/hoteltodistributor?id=${distributorconfig.id}&otatype=${distributorconfig.otatype}", "关联酒店", 810, $(top.document).height() - 240, {
                buttons: {"添加关联": "ok", "按搜索条件关联": "linkfilter", "关闭": false},
                bottomText: "通过查询过滤关联对应酒店。",
                submit: function (v, h, f) {
                    var ids = h.find("iframe")[0].contentWindow.ids;
                    if (v == "ok") {
                        // 执行保存
                        loading('正在提交，请稍等...');
                        $('#ids').val(ids);
                        $('#assignHotelForm').submit();
                        return true;
                    } else if (v == "linkfilter") {
                        h.find("iframe")[0].contentWindow.relateByCondition(function(){
                            window.location.href = window.location.href;
                        });
                        return false;
                    }
                },
                loaded: function (h) {
                    $(".jbox-content", top.document).css("overflow-y", "hidden");
                }
            });
        });

        $("#deleteButton").click(
                function () {
                    confirmx('确认要批量删除吗？', function () {
                        var selectedIds = "";
                        $(".selcheckbox").each(function () {
                            var $this = $(this);

                            if ($this.is(":checked")) {
                                selectedIds += $this.val() + ",";
                            }
                        });
                        if (selectedIds.length > 0) {
                            selectedIds = selectedIds.substr(0, selectedIds.length - 1);
                        }
                        if(selectedIds.length>0){
                            $("#assignHotelForm").attr("action","${ctx}/distributor/ota/batchouthotel");
                            $("#ids").attr("value",selectedIds);
                            $("#assignHotelForm").submit();
                        }else{
                            alertx("至少选择一条数据.");
                        }
                    })
                }
        );


        $(document).ready(function () {
            var all_checked = false;
            $(":checkbox").click(function () {
                var table = $(this).parents("table");
                //如果点击了全选的，那么下面都选或者不选
                if ($(this).attr("id") === "all") {
                    table.find(":checkbox").prop("checked", !all_checked);
                    all_checked = !all_checked;
                } else {
                    table.find(":checkbox[id!=all]").each(function (i) {
                        if (!$(this).is(":checked")) {
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
    </script>
</div>
</shiro:hasPermission>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th style="text-align: center"><input type="checkbox" name="all" id="all" /> </th>
        <th style="text-align: center">序列</th>
        <th>酒店ID</th>
        <th>酒店名称</th>
        <th>酒店类型</th>
        <shiro:hasPermission name="gds:ota:edit">
            <th style="text-align: center">操作</th>
        </shiro:hasPermission>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${page.list != nulll && page.list.size()>0}">
            <c:forEach items="${page.list}" var="item" varStatus="status">
                <tr>
                    <td style="text-align: center">
                        <input id="checkbox_${status.index+1}" type="checkbox" class="selcheckbox" name="check"
                               value="${item.id}"/>
                    </td>
                    <td style="text-align: center">${status.index+1}</td>
                    <td>${item.hotelid}</td>
                    <td>${item.hotelname}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.hoteltype == '1'}">
                                旅馆
                            </c:when>
                            <c:when test="${item.hoteltype == '2'}">
                                主题酒店
                            </c:when>
                            <c:when test="${item.hoteltype == '3'}">
                                精品酒店
                            </c:when>
                            <c:when test="${item.hoteltype == '4'}">
                                公寓
                            </c:when>
                            <c:when test="${item.hoteltype == '5'}">
                                招待所
                            </c:when>
                            <c:when test="${item.hoteltype == '6'}">
                                客栈
                            </c:when>
                        </c:choose>

                    </td>
                    <shiro:hasPermission name="gds:ota:edit">
                        <td style="text-align: center">
                            <a href="${ctx}/distributor/ota/hotel/suply?id=${distributorconfig.id}&hotelid=${item.hotelid}&otatype=${distributorconfig.otatype}">供应配置</a>&nbsp;
                            <a href="${ctx}/distributor/ota/hotel/stockquery?id=${distributorconfig.id}&hotelid=${item.hotelid}&otatype=${distributorconfig.otatype}">库存查询</a>&nbsp;
                            
                            <a href="${ctx}/distributor/ota/outhotel?id=${item.id}&configid=${distributorconfig.id}"
                               onclick="return confirmx('确认要将酒店<b>[${item.hotelname}]</b>从<b>[${channelname}]</b>中移除吗？', this.href)">移除</a>
                        </td>
                    </shiro:hasPermission>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="6" style="text-align: center;">暂无信息！</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
