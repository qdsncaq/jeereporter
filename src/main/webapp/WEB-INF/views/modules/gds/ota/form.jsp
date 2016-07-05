<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>OTA管理</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    function controllabel(val){
      if(val == 1){
        $("#comrate").hide();
      }else if(val == 2){
        $("#comrate").show();
      }
    }
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
      controllabel('${distributorconfig.saletype}');
      $('#channelid').attr('disabled',${distributorconfig.id!=null && distributorconfig.id!=''});
    });
  </script>
</head>
<ul class="nav nav-tabs">
  <li><a href="${ctx}/distributor/ota/">OTA列表</a></li>
  <li class="active"><a href="${ctx}/distributor/ota/form?id=${oTADistributorConfig.id}">OTA<shiro:hasPermission name="gds:ota:edit">${not empty distributorconfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gds:ota:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<sys:message content="${message}"/>
<form:form id="inputForm" modelAttribute="distributorconfig" action="${ctx}/distributor/ota/save" method="post" class="form-horizontal">
  <form:hidden path="id"/>
  <div class="control-group">
    <label class="control-label">OTA编码：</label>
    <div class="controls">
      <form:input path="otatype" htmlEscape="false" maxlength="20" class="input-xlarge required digits" readonly="true" />
      <span class="help-inline"><font color="red">*</font> </span>
    </div>
  </div>
  <%--<div class="control-group">
    <label class="control-label">OTA名称：</label>
    <div class="controls">
      <form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
      <span class="help-inline"><font color="red">*</font> </span>
    </div>
  </div>--%>
  <div class="control-group">
    <label class="control-label">渠道：</label>
    <div class="controls">
      <form:select path="channelid" htmlEscape="false" maxlength="10" class="input-xlarge required digits">
        <form:option value="" label=""/>
        <form:options items="${channeldic}" itemLabel="label" itemValue="value" htmlEscape="false"/>
      </form:select>
      <span class="help-inline"><font color="red">*</font> </span>
    </div>
  </div>

  <div class="control-group">
    <label class="control-label">上浮金额：</label>
    <div class="controls">
      <form:input path="upprice" htmlEscape="false" class="input-xlarge number"/>元&nbsp;
      <span class="help-inline"><font color="red">同时设置默认按上浮金额计算</font> </span>
    </div>
  </div>

  <div class="control-group">
    <label class="control-label">上浮比例：</label>
    <div class="controls">
      <form:input path="upper" htmlEscape="false" class="input-xlarge number"/>%&nbsp;
      <span class="help-inline"><font color="red">同时设置默认按上浮金额计算</font> </span>
    </div>
  </div>

  <div class="control-group">
    <label class="control-label">销售方式：</label>
    <div class="controls">
        <%--<form:select path="saletype" htmlEscape="false" maxlength="10" class="input-xlarge required"/>--%>
      <form:radiobuttons path="saletype" onclick="controllabel(this.value)" items="${saletypedic}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required"/>
    </div>
  </div>

  <div class="control-group" id="comrate">
    <label class="control-label">佣金比例：</label>
    <div class="controls">
      <form:input path="commissionrate" htmlEscape="false" class="input-xlarge number"/>%
    </div>
  </div>

  <c:if test="${distributorconfig.channelid==2}">
  <div class="control-group">
    <label class="control-label">新业务模式：</label>
    <div class="controls">
      <span><input id="Q_BUSI_MODE_1" name="musimode" class="input-xlarge required" type="radio" value="true" ${Q_BUSI_MODE=="true" ? "checked":""}><label for="Q_BUSI_MODE_1">开通</label></span>
      <span><input id="Q_BUSI_MODE_2" name="musimode" class="input-xlarge required" type="radio" value="false" ${Q_BUSI_MODE=="false" ? "checked":""}><label for="Q_BUSI_MODE_2">关闭</label></span>
    </div>
  </div>
  </c:if>
  <div class="form-actions">
    <shiro:hasPermission name="gds:ota:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
  </div>
</form:form>
</body>
</html>