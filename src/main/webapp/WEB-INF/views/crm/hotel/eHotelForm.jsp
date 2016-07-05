<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
	<meta name="decorator" content="default"/>
<!-- 	<script src="../common/common.js" type="text/javascript"></script> -->
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
		
		function phoneNumCheck(value) {
			var regExp = /^1\d{10}$/;
			if(value == ""){
				return;
			}
			if (!regExp.test(value)) {
				$("#phoneNumMsg").html("请正确输入手机号");
				return false;
			}
			return true;
		}
		
		function submitForm(){
			var obj = $("#bossPhoneNew");
			if (obj.val() == "") {
				$("#phoneNumMsg").html("请正确输入手机号");
				return false;
			}
			if ($("#bossName").val() == "") {
				$("#bossNameMsg").html("请输入老板名称");
				return false;
			}
			if(phoneNumCheck(obj.val())){
				$("#btnSubmit").submit();
			}
		}
		function clearMsg(){
			$("#phoneNumMsg").html("");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/crm/hotel/eHotel/">任务列表</a></li>
		<li class="active"><a href="${ctx}/crm/hotel/eHotel/form?id=${eHotel.id}">任务<shiro:hasPermission name="hotel:eHotel:edit">${not empty eHotel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hotel:eHotel:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="eHotel" action="${ctx}/crm/hotel/eHotel/modifyBossPhone" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class='row'>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">酒店id：</label>
					<div class="controls">
						<form:input path="id" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
					</div>
				</div>
 			</div>
			<div class='span7'>
		 		 <div class="control-group">
					<label class="control-label">酒店名称：</label>
					<div class="controls">
						<form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
					</div>
				</div>
			</div>
		</div>
		<div class='row'>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">老板名称：</label>
					<div class="controls">
						<form:input path="bossName" id="bossName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
					</div>
				</div>
 			</div>
			<div class='span7'>
		 		 <div class="control-group">
					<label class="control-label">老板手机号(旧)：</label>
					<div class="controls">
						<form:input path="bossPhone" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
						<span id="bossNameMsg" style="color:red"></span>
					</div>
				</div>
			</div>
		</div>
		<div class='row'>
			<div class='span7'>
		 		 <div class="control-group">
					<label class="control-label">老板手机号(新)：</label>
					<div class="controls">
						<form:input path="bossPhoneNew" id="bossPhoneNew" htmlEscape="false" maxlength="50" class="input-xlarge required" 
						            onblur="phoneNumCheck(this.value);"
						            onfocus="clearMsg()"/>
						<span class="help-inline"><font color="red">*</font> </span>
						<span id="phoneNumMsg" style="color:red"></span>
					</div>
				</div>
			</div>
			<div class='span7'>
		 		 <div class="control-group">
					<label class="control-label">修改类型：</label>
					<div class="controls">
						<form:select path="modifyType" class="input-medium">
							<form:option value="1" label="修改老板手机号" />
							<form:option value="2" label="酒店转移"/>
						</form:select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hotel:eHotel:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="submitForm()" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>