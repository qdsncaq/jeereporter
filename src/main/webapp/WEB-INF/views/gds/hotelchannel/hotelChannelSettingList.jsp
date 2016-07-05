<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>酒店渠道分销开关管理</title>
    <meta name="decorator" content="default"/>
     <%@include file="../layouts/importJs.jsp" %>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
            });
            
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function changeStatus(hotelid,channel,status){
            layer.confirm("确定要操作吗？", function() {
            var posturl = $("#savestatus").val();
            var loginuser = $("#loginusername").val();
            var restatus = status;
             $.post(posturl,
                     {
                        hotelid:hotelid,
                        channel:channel,
                        status:status,
                        operator:loginuser
                      },
                      function(data,status){
                        var renum = data;
                        if(status=='success'){
                            if(data>0){
                                window.location.reload();
                            }if(data<=0){
                                layer.msg("修改失败!", {
                                    icon : 2
                                });
                            }
                        }else{
                            layer.msg("修改失败!", {
                                icon : 2
                            });
                        }
                      });
              });
        }
    </script>
</head>
<body>
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/hotelchannel/hotelChannelSetting/import" method="post" enctype="multipart/form-data"
            class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
            <a href="${ctx}/hotelchannel/hotelChannelSetting/import/template">下载模板</a>
        </form>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/hotelchannel/hotelChannelSetting/">酒店渠道分销开关列表</a></li>
        <%-- <shiro:hasPermission name="hotelchannel:hotelChannelSetting:edit"><li><a href="${ctx}/hotelchannel/hotelChannelSetting/form">酒店渠道分销开关添加</a></li></shiro:hasPermission> --%>
    </ul>
    <form:form id="searchForm" modelAttribute="hotelChannelSetting" action="${ctx}/hotelchannel/hotelChannelSetting/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input type="hidden" value="${fns:getUser().name}"  id="loginusername">
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li><label>酒店id：</label>
                <form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
                 <input type="hidden" value="${hotelChannelSetting.hotelid}"  id="hotelid">
            </li>
            <li><label>酒店名称：</label>
                <form:input path="hotelname" htmlEscape="false" maxlength="20" class="input-medium"/>
                 <input type="hidden" value="${hotelChannelSetting.hotelname}"  id="hotelname">
            </li>
            <li><label>渠道</label>
                <form:select path="channelid" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('ChannelEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>状态</label>
                 <form:select path="state" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('ChannelStatusEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>创建时间：</label>
                <input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${hotelChannelSetting.createtime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </li>
            <li><label>更新时间：</label>
                <input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    value="<fmt:formatDate value="${hotelChannelSetting.updatetime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <shiro:hasPermission name="hotelchannel:hotelChannelSetting:import">
                <li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
            </shiro:hasPermission>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>id</th>
                <th>酒店id</th>
                <th>酒店名称</th>
                <th>渠道</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>创建人</th>
                <th>更新时间</th>
                <th>更新人</th>
                <shiro:hasPermission name="hotelchannel:hotelChannelSetting:edit"><th>操作</th></shiro:hasPermission>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="hotelChannelSetting">
            <tr>
                <td>
                <%-- <a href="${ctx}/hotelchannel/hotelChannelSetting/form?id=${hotelChannelSetting.id}"> --%>
                    ${hotelChannelSetting.id}
                <!-- </a> -->
                </td>
                <td>
                    ${hotelChannelSetting.hotelid}
                </td>
                <td>
                    ${hotelChannelSetting.hotelname}
                </td>
                <td>
                    ${fns:getDictLabel(hotelChannelSetting.channelid, 'ChannelEnum', '')}
                </td>
                <td>
                    ${fns:getDictLabel(hotelChannelSetting.state, 'ChannelStatusEnum', '')}
                </td>
                <td>
                    <fmt:formatDate value="${hotelChannelSetting.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                    ${hotelChannelSetting.createuser}
                </td>
                <td>
                    <fmt:formatDate value="${hotelChannelSetting.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                    ${hotelChannelSetting.updateuser}
                </td>
                <shiro:hasPermission name="hotelchannel:hotelChannelSetting:edit">
                <td>
                    <%-- <a href="${ctx}/hotelchannel/hotelChannelSetting/form?id=${hotelChannelSetting.id}">修改</a>
                    <a href="${ctx}/hotelchannel/hotelChannelSetting/delete?id=${hotelChannelSetting.id}" onclick="return confirmx('确认要删除该酒店渠道分销开关吗？', this.href)">删除</a> --%>
                    <input type="hidden" id ="savestatus" value="${ctx}/hotelchannel/hotelChannelSetting/savestatus" />
                    <c:if test="${hotelChannelSetting.state==1}"><input id="changeStatus" class="btn btn-primary" type="button"  onclick="changeStatus(${hotelChannelSetting.hotelid},${hotelChannelSetting.channelid},2)" value="关闭"/></c:if>
                    <c:if test="${hotelChannelSetting.state==2}"><input id="changeStatus" class="btn btn-primary" type="button"  onclick="changeStatus(${hotelChannelSetting.hotelid},${hotelChannelSetting.channelid},1)" value="开通"/></c:if>
                </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>