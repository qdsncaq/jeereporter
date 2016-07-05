<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		
		function serSubTree(treeId){
			var url = "${ctx}/crm/location/tCity/serSubTree";
			//重置select2控件的值
			var subIdSelect = $("#cityCode");
			$("#cityCode option:not(:first)").remove();
			subIdSelect.select2("val","");
			//区县
			var thirdIdSelect = $("#disCode");
			$("#disCode option:not(:first)").remove();
			thirdIdSelect.select2("val","");

			$.ajax({
				url: url,
				dataType: 'json',
				data:{
					proid:treeId
				},
				cache: false,
				async: true,
				success: function(data, textStatus) {
					var secTreeList = data;
					for(var node in secTreeList){
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityid+"' >"+secTreeList[node].cityname+"</option>";
						subIdSelect.append(option);
					}
				}
			});
		}

        function serQzName(username) {
            $("#qz_userName").val(username);
        }

        function serQjName(username) {
            $("#qj_userName").val(username);
        }
		
		function phoneNumCheck(obj){
			var regExp= /^1\d{10}$/;
			if (!obj.value) {
				$("#phoneNumMsg").html("输入不能为空");
				return false;
			}
			if (regExp.test(obj.value)) {
				$("#phoneNumMsg").html("");
				return true;
			} else {
				$("#phoneNumMsg").html("请正确输入手机号");
				return false;
			}
		}
		
		/**
			酒店退回公海
		*/
        function returnPublic() {
			 var userCode = $("#userCode").val();
			 var userName = $("#userName").val();
        	if(window.confirm("你确定要\"" + userName + "\"的私海酒店退回公海吗？")){
        		/* $("#inputForm").attr("action","${ctx}/crm/user/syOrgUser/returnPublic");
                 $("#inputForm").submit(); */
	        	 $.ajax({
	                  type : "POST",  //提交方式  
	                  url : "${ctx}/crm/user/syOrgUser/returnPublic",//路径  
	                  data : {  
	                      "userCode" : userCode 
	                  },
	                  success : function(result) {
	                	  var obj = JSON.parse(result);
	                	  if(obj && obj.code == "success"){
	                		  $("#resultSuccess").html(obj.msg); 
	                		  $("#resultSuccess").show(); 
	                		  $("#resultError").hide(); 
	                	  }else  if(obj && obj.code == "error"){
	                		  $("#resultError").html(obj.msg); 
	                		  $("#resultError").show(); 
	                		  $("#resultSuccess").hide(); 
	                	  }
	                	   setTimeout("resultDivHide()",3000);
	                  }  
	              });  
             }
        } 
		/**隐藏结果提示的div*/
        function resultDivHide(){
        	$("#resultSuccess").hide(); 
  		  	$("#resultError").hide(); 
 	   }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/crm/user/syOrgUser/">用户列表</a></li>
		<li class="active"><a href="${ctx}/crm/user/syOrgUser/form?id=${syOrgUser.id}">用户<shiro:hasPermission name="user:syOrgUser:edit">${not empty syOrgUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:syOrgUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<div id="resultSuccess" class="alert alert-success" style="display:none">
	</div>
	<div id="resultError" class="alert alert-error" style="display:none">
	</div>
	<form:form id="inputForm" modelAttribute="syOrgUser" action="${ctx}/crm/user/syOrgUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
        <form:hidden path="qzUserName" id="qz_userName" />
        <form:hidden path="qjUserName" id="qj_userName" />
		<sys:message content="${message}"/>		
		
		<div class='row'>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">用户id：</label>
					<div class="controls">
						<form:input id="userCode" path="userCodeView" htmlEscape="false" maxlength="40" class="input-xlarge" disabled="true" />
					</div>
				</div>
 			</div>
			<div class='span7'>
		 		 
			</div>
		</div>
		<div class='row'>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">用户名(拼音)：</label>
					<div class="controls">
						<form:input path="userLoginName" htmlEscape="false" maxlength="40" class="input-xlarge required "/>
					</div>
				</div>
 			</div>
			<div class='span7'>
		 		<div class="control-group">
					<label class="control-label">用户名称：</label>
					<div class="controls">
						<form:input id="userName" path="userName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
					</div>
				</div>
			</div>
		</div>
		<%-- <div class='row'>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">是否修改密码：</label>
					<div class="controls">
						<form:select path="isModifyPw" class="input-medium">
							<form:option value="false">否</form:option>
							<form:option value="true">是</form:option>
						</form:select>
					</div>
				</div>
			</div>
			<div class='span7'>
		 		<div class="control-group">
					<label class="control-label">用户密码：</label>
					<div class="controls">
						<form:input  type="password" path="userPassword" htmlEscape="false" maxlength="80" class="input-xlarge required"/>
					</div>
				</div>
 			</div>
		</div> --%>
		<div class='row'>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">手机号码：</label>
					<div class="controls">
						<form:input path="userMobileNew" htmlEscape="false" maxlength="40" class="input-xlarge required" onblur="phoneNumCheck(this);"/>
						<span id="phoneNumMsg" style="color:red"></span>
					</div>
				</div>
			</div>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">用户邮箱：</label>
					<div class="controls">
						<form:input path="userEmail" htmlEscape="false" maxlength="40" class="input-xlarge "/>
					</div>
				</div>
 			</div>
		</div>
		<div class='row'>
			<div class='span7'>
				 <div class="control-group">
					<label class="control-label">人员状态：</label>
					<div class="controls">
						<form:select path="userState" class="input-medium required">
							<form:options items="${fns:getDictList('crm_user_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</div>
			<div class='span7'>
			 	<div class="control-group">
					<label class="control-label">启用标志：</label>
					<div class="controls">
						<form:select path="sFlag" class="input-medium required">
							<form:options items="${fns:getDictList('crm_sflag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
 			</div>
			<%-- <div class='span7'>
				<div class="control-group">
					<label class="control-label">部门编码：</label>
					<div class="controls">
						<form:input path="deptCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
					</div>
				</div>
			</div> --%>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">省编码：</label>
					<div class="controls">
						<form:select id="provCode" path="provCode" class="input-medium required" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
							<form:option value="" label="选择省"/>
							<c:forEach items="${provinceList}" var="prov" varStatus="status">
								<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}"/>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">市编码：</label>
					<div class="controls">
						<form:select path="cityCode" class="input-medium">
							<form:option value="" label="选择市"/>
							<c:forEach items="${cityList}" var="city" varStatus="status">
								<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
							</c:forEach>
						</form:select>
						
					</div>
				</div>
			</div>
			<div class='span7'>
				<div class="control-group">
					<label class="control-label">所属公司：</label>
					<div class="controls">
						<form:select path="type" class="input-medium">
							<form:option value="" label="选择公司"/>
							<form:options items="${fns:getDictList('crm_company')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>

					</div>
				</div>
			</div>
            <div class='span7'>
                <div class="control-group">
                    <label class="control-label">职务：</label>
                    <div class="controls">
                        <form:select path="level" class="input-medium">
                            <form:option value="" label="选择职务"/>
                            <form:options items="${fns:getDictList('area_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>

                    </div>
                </div>
            </div>
            <div class='span7'>
                <div class="control-group">
                    <label class="control-label">所属区总：</label>
                    <div class="controls">
                        <form:select path="qzUserCode" class="input-medium" onchange="serQzName($(this).find('option:selected').attr('valueid'));">
                            <form:option value="" label="无" valueid=""/>
                            <c:forEach items="${qzList}" var="qz">
                                <form:option value="${qz.userCode}" label="${qz.userName}" valueid="${qz.userName}"/>
                            </c:forEach>
                        </form:select>

                    </div>
                </div>
            </div>
            <div class='span7'>
                <div class="control-group">
                    <label class="control-label">所属区经：</label>
                    <div class="controls">
                        <form:select path="qjUserCode" class="input-medium" onchange="serQjName($(this).find('option:selected').attr('valueid'));">
                            <form:option value="" label="无" valueid=""/>
                            <c:forEach items="${qjList}" var="qj">
                                <form:option value="${qj.userCode}" label="${qj.userName}" valueid="${qj.userName}"/>
                            </c:forEach>
                        </form:select>

                    </div>
                </div>
            </div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:syOrgUser:edit"><input id="btnReturnPublic" class="btn btn-primary" type="button" value="退回公海" onclick="returnPublic()"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="user:syOrgUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>