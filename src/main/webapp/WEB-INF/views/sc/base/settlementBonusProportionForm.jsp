<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>奖金分配比例管理</title>
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
		
        /**
         * 
         */
        function serSubTree(treeId, provname){
            var url = "${ctx}/crm/location/tCity/serSubTree";
            //重置select2控件的值
            var subIdSelect = $("#city");
            $("#city option:not(:first)").remove();
            subIdSelect.select2("val","");
            //区县
            var thirdIdSelect = $("#district");
            $("#district option:not(:first)").remove();
            thirdIdSelect.select2("val","");
            // 省份名称
            $('#proname').val(provname);

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

        /**
         * 
         */
        function serThirdTree(treeId){
            var url = "${ctx}/crm/location/tDistrict/serSubTree";
            //重置select2控件的值
            var thirdIdSelect = $("#district");
            $("#district option:not(:first)").remove();
            thirdIdSelect.select2("val","");

            $.ajax({
                url: url,
                dataType: 'json',
                data:{
                    cityid:treeId
                },
                cache: false,
                async: true,
                success: function(data, textStatus) {
                    var secTreeList = data;
                    for(var node in secTreeList){
                        var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].id+"'>"+secTreeList[node].disname+"</option>";
                        thirdIdSelect.append(option);
                    }
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/base/settlementBonusProportion/">奖金分配比例列表</a></li>
		<li class="active"><a href="${ctx}/base/settlementBonusProportion/form?id=${settlementBonusProportion.id}">奖金分配比例<shiro:hasPermission name="base:settlementBonusProportion:edit">${not empty settlementBonusProportion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="base:settlementBonusProportion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="settlementBonusProportion" action="${ctx}/base/settlementBonusProportion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">省级编码：</label>
			<div class="controls">
				<form:select path="province" htmlEscape="false" maxlength="20" class="input-xlarge required" onchange="serSubTree($(this).find('option:selected').attr('valueid'), $(this).find('option:selected').attr('provname'));">
                    <form:option value="" label="选择省"/>
                    <c:forEach items="${provinceList}" var="prov" varStatus="status">
                        <form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid}" provname="${prov.proname}" />
                    </c:forEach>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">省份名称：</label>
            <div class="controls">
                <form:input path="proname" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </div>
        </div>
        <!--
		<div class="control-group">
			<label class="control-label">市级编码：</label>
			<div class="controls">
				<form:select path="city" htmlEscape="false" maxlength="20" class="input-xlarge " onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
                    <form:option value="" label="选择市"/>
                    <c:forEach items="${cityList}" var="city" varStatus="status">
                        <form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityid}"/>
                    </c:forEach>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区级编码：</label>
			<div class="controls">
				<form:select path="district" htmlEscape="false" maxlength="20" class="input-xlarge ">
                    <form:option value="" label="选择县/区"/>
                    <form:options items="${districtList}" itemLabel="disname" itemValue="code" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">角色：</label>
			<div class="controls">
				<form:select path="role" htmlEscape="false" maxlength="20" class="input-xlarge required">
                    <form:option value="" label="--请选择--"/>
                    <form:options items="${fns:getDictList('AccountRoleEnums')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分配比例：</label>
			<div class="controls">
				<form:input type="number" path="proportion" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="base:settlementBonusProportion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>