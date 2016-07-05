<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

        function showPrices(roomtypeId, hotelId, roomtypeName) {
            top.$.jBox.open("iframe:${ctx}/roomtype/roomtype/price?id=" + roomtypeId + "&hotelid=" + hotelId, roomtypeName + "  房型价格",810,$(top.document).height()-540,{
                buttons:{"关闭":true}, loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hotel/hotel/">酒店查询</a></li>
		<li class="active"><a href="${ctx}/hotel/hotel/form?id=${hotel.id}">酒店<shiro:hasPermission name="hotel:hotel:edit">${not empty hotel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hotel:hotel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hotel" action="${ctx}/hotel/hotel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">酒店名称：</label>
			<div class="controls">
				<form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="hotelcontactname" htmlEscape="false" maxlength="25" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册时间：</label>
			<div class="controls">
				<input name="regtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hotel.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="detailaddr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开业时间：</label>
			<div class="controls">
				<input name="opentime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hotel.opentime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修时间：</label>
			<div class="controls">
				<input name="repairtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hotel.repairtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房间数：</label>
			<div class="controls">
				<form:input path="roomnum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">酒店介绍：</label>
			<div class="controls">
				<form:textarea path="introduction" htmlEscape="false" class="input-xlarge " style="min-height:150px;min-width:600px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">许可证(前)：</label>
			<div class="controls">
			    <img src="${hotel.businesslicensefront }" width="300px">
<%-- 				<form:input path="businesslicensefront" htmlEscape="false" maxlength="100" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">许可证(背)：</label>
			<div class="controls">
			   
			   <img src="${hotel.idcardback }" width="300px">
<%-- 				<form:input path="businesslicenseback" htmlEscape="false" maxlength="100" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">酒店pms：</label>
			<div class="controls">
				<form:input path="hotelpms" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否上线：</label>
			<div class="controls">
				<form:input path="" value="${fns:getDictLabel(hotel.isvisible, 'yesOrNo', hotel.isvisible)}" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否在线：</label>
			<div class="controls">
				<form:input path="" 
				value="${fns:getDictLabel(hotel.isonline, 'yesOrNo', hotel.isonline)}"
				htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证(前)：</label>
			<div class="controls">
			    <img src="${hotel.idcardfront }" width="300px">
<%-- 				<form:input path="idcardfront" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证(背)：</label>
			<div class="controls">
			<img src="${hotel.idcardback }" width="300px">
<%-- 				<form:input path="idcardback" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保留时间：</label>
			<div class="controls">
				<form:input path="retentiontime" value="" 
				htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">默认离店时间：</label>
			<div class="controls">
				<form:input path="defaultleavetime" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="hotelphone" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">酒店类型：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="hoteltype" htmlEscape="false" maxlength="2" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">区县编码：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="discode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">前台电话：</label>
			<div class="controls">
				<form:input path="qtphone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">市行政编码：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="citycode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">省行政编码：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="provcode" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">pas类型，眯客:mike、金天鹅、昆仑、等等：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="pmstype" htmlEscape="false" maxlength="10" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">上下线时间戳：</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<input name="isonlinetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " -->
<%-- 					value="<fmt:formatDate value="${hotel.isonlinetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">酒店传真：</label>
			<div class="controls">
				<form:input path="hotelfax" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">酒店状态4店长正在编辑中，（审核通过）5审核中：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="state" htmlEscape="false" maxlength="4" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
        <div>
            <table id="roomtype_table" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th width="15%">房型id</th>
                    <th width="15%">房型名称</th>
                    <th width="15%">房型PMS</th>
                    <th width="15%">数量</th>
                    <th width="15%">房型类型</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${roomtypeList}" var="roomtype">
                    <tr>
                        <td>
                                ${roomtype.id}
                        </td>
                        <td>
                                ${roomtype.name}
                        </td>
                        <td>
                                ${roomtype.roomtypepms}
                        </td>
                        <td>
                                ${roomtype.roomnum}
                        </td>
                        <td>
                            ${fns:getDictLabel(roomtype.type, 'roomtype', '')}
                        </td>
                        <td>
                            <a onclick="showPrices(${roomtype.id}, ${roomtype.hotelid}, '${roomtype.name}')">查询价格</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="hotel:hotel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<p>
	<table id="log_table" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th width="15%">业务类型</th>
			<th width="15%">订单编号</th>
			<th width="15%">操作员</th>
			<th width="40%">内容</th>
			<th width="15%">操作时间</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${loglist}" var="log">
			<tr>
				<td>
						${log.bussinssType}
				</td>
				<td>
						${log.bussinessId}
				</td>
				<td>
						${log.operator}
				</td>
				<td>
						${log.content}
				</td>
				<td>
					<fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>