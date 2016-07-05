<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自建酒店详情</title>
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

        function changePicSize(e) {
            var cur = e.style.cursor;
            if (cur == 'zoom-in') {
                e.style.cursor = 'zoom-out';
                e.width = '1000';
                e.height = '1200';
            } else {
                e.style.cursor = 'zoom-in';
                e.width = '276';
                e.height = '368';
            }
        }

    </script>
    <style>
        .controls{
            margin-left:1px !important;
        }
        .div_controls_span{
            margin:5px 15px 5px 5px;
        }
        .div_controls_span_lable{
            width: 80px;
            height: 30px;
            line-height: 30px;
            text-align: right;
        }

    </style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="eHotel" method="post" class="form-horizontal">
		<%--<form:hidden path="id"/>--%>
		<%--<sys:message content="${message}"/>--%>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">酒店id：</span>
                    <form:input path="id" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">酒店名称：</span>
                    <form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">酒店地址：</span>
                    <form:input path="detailaddr" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">省：</span>
                    <form:input path="provName" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">市：</span>
                    <form:input path="cityName" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">区：</span>
                    <form:input path="disName" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">前台电话：</span>
                    <form:input path="qtphone" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">老板名称：</span>
                    <form:input path="bossName" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">老板手机号：</span>
                    <form:input path="bossPhone" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">销售：</span>
                    <form:input path="userName" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">销售电话：</span>
                    <form:input path="userMobile" htmlEscape="false" maxlength="50" class="input-large" disabled="true"/>
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">酒店介绍：</span>
                    <form:textarea path="introduction" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
                </span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">门头照片：</span>
                    <img onclick="changePicSize(this)" style="cursor:zoom-in" src="${eHotel.facadePic}?imageMogr/auto-orient" width="276" height="368">
                </span>
            </div>
        </div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>