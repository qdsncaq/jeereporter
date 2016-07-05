<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>查询酒店</title>
    <meta name="decorator" content="blank"/>
    <script language="javascript">
        var ids;
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
                ids = selectedIds;
            });
        });

        function relateByCondition(cb){
            var onOk = function(data,status,xhr){
                cb();
                top.$.jBox.close(true);
            }
            var hotelname = $("#searchForm").find("#hotelname").val();
            var provcode = $("#searchForm").find("#provcode").val();
            var citycode = $("#searchForm").find("#citycode").val();
            var busiarea = new Array();
            $.each($("input[name='busiarea']:checked"), function() {
                busiarea.push($(this).val());
            });
            var feature = new Array();
            $.each($("input[name='feature']:checked"), function() {
                feature.push($(this).val());
            });
            var facilities = new Array();
            $.each($("input[name='facilities']:checked"), function() {
                facilities.push($(this).val());
            });
            var other = new Array();
            $.each($("input[name='other']:checked"), function() {
                other.push($(this).val());
            });

            var query = {
                'pageNo': 1,
                'pageSize': 20,
                'otatype': ${distributorconfig.otatype},
                'hotelname': hotelname,
                'provcode': provcode,
                'citycode': citycode,
                'busiarea': busiarea,
                'feature': feature,
                'facilities': facilities,
                'other': other
            };
            AjaxEx("json","POST","${ctx}/distributor/ota/relateByCondition?id=${distributorconfig.id}",query,onOk,null,true,false);
        }

        function changeCity(){
            var onOk = function(data,status,xhr){
                var provinces = data.result;
                $("#citycode").empty();
                $("#citycode").append('<option value="">所有</option>');
                $.each(provinces, function (n, value) {
                    $("#citycode").append('<option value='+value.code+'>'+value.name+'</option>');
                });
            }
            var province = $("#provcode").find("option:selected").attr("provinceid") || '';
            if(province){
                AjaxEx("json","POST","${ctx}/province/getAllCitys",{'proid':province},onOk,null,true,false);
            }else{
                $("#citycode").empty();
                $("#citycode").append('<option value="">所有</option>');
            }
            $("#citycode option[text='']").attr("selected", true);
        }

        /**
         * AJAX工具中间件
         * @param dataType  数据类型
         * @param methodType POST or GET
         * @param url   请求地址
         * @param params    参数
         * @param onComplete    成功回调
         * @param onError   错误回调
         * @param issync   是否同步
         * @param isloading   是否弹出全屏遮罩层
         * @constructor
         *  Modify by jianghe
         */
        function AjaxEx(dataType,methodType, url, params, onComplete, onError, issync,isloading) {
            issync = !issync;// TODO 暂用为是否同步 传true 同步

            var index;
            if(isloading != undefined && isloading){
                index = layer.load(0, {shade: false});
            }
            $.ajax({
                type : methodType,
                dataType : dataType,
                url : url,
                data : params,
                async: issync,
                success : function(data, status, xhr) {
                    switch (xhr.status) {
                        case 200:
                            if (onComplete == null || onComplete == undefined) {
                                // 状态为200且没有给出适用函数
                            } else {
                                if (dataType != "JSON" && dataType != 'json') {
                                    // 返回数据不为json直接调用适用函数
                                    onComplete(data, status, xhr);
                                    if(isloading != undefined && isloading){
                                        layer.close(index);
                                    }
                                } else {
                                    if (data != null) {
                                        // 返回为json且返回successed为true调用适用函数
                                        onComplete(data, status, xhr);
                                        if(isloading != undefined && isloading){
                                            layer.close(index);
                                        }
                                    }
                                }
                            }
                            break;
                        default:
                        //不可预知的错误发生,请稍后再试
                    }
                },
                complete : function(xhr, textStatus) {
                    if (xhr.status == 200) {
                        return;
                    }
                    switch (xhr.status) {
                        case 400:
                            break;
                        case 401:
                            break;
                        case 403:
                            layer.alert("对不起,您没有权限访问此功能.如需访问请联系管理员.");
                            break;
                        case 404:
                            break;
                        case 500:
                            break;
                        case 503:
                            break;
                        case 0:
                            break;
                        default:
                            console.log("错误状态："+xhr.status);
                    }
                }
            })

        }
    </script>
</head>
<body>
<form:form id="searchForm" modelAttribute="hotelModelEsExtBean"
           action="${ctx}/distributor/ota/hoteltodistributor?id=${distributorconfig.id}" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="otatype" name="otatype" type="hidden" value="${distributorconfig.otatype}"/>
    <input id="ids" name="ids" type="hidden" value=""/>
    <ul class="ul-form">
        <li><label>酒店名称：</label>
            <form:input path="hotelname" htmlEscape="false" class="input-small"/>
        </li>
        <li><label>省：</label>
            <form:select path="provcode" class="input-medium" onchange="changeCity()">
                <form:option value="" label="全部"/>
                <c:forEach items="${provinces}" var="item" >
                    <form:option value="${item.value}" provinceid='${item.description}'>
                        ${item.label}
                    </form:option>
                </c:forEach>
            </form:select>
        </li>
        <li><label>市：</label>
            <form:select path="citycode" class="input-medium">
                <form:option value="" label="全部"/>
            </form:select>
        </li>

        <li><label>商圈位置：</label>
            <form:checkboxes path="busiarea" items="${busiarea}" itemLabel="label" itemValue="value"
                             htmlEscape="false"></form:checkboxes>
        </li>
        <li style="margin-bottom: 12px; line-height: 10px;"><label>类型特色：</label>
            <form:checkboxes path="feature" items="${feature}" itemLabel="label" itemValue="value"
                             htmlEscape="false"></form:checkboxes>
        </li>
        <li style="margin-bottom: 12px; line-height: 10px;"><label>设施服务：</label>
            <form:checkboxes path="facilities" items="${facilities}" itemLabel="label" itemValue="value"
                             htmlEscape="false"></form:checkboxes>
        </li>
        <li class="clearfix"></li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="linkfilter()" value="按搜索条件关联"/></li>--%>
    </ul>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th style="text-align: center"><input type="checkbox" name="all" id="all"/></th>
        <th style="text-align: center">序列</th>
        <th>酒店ID</th>
        <th>酒店名称</th>
    </tr>
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
                        <td>${item.id}</td>
                        <td>${item.hotelname}</td>
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
