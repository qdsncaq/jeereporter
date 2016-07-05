<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>psbrulesrelation管理</title>
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
			
		  $("#cpsbid").change(function(){
		  select($(this).val(),null);
         });
		});
		
		
	    function select(psbid,ids){
          if(ids!=null && ids!=undefined && ids!=""){
            ids=ids.trim();
            if(ids.length>0){
              ids=","+ids+",";
            }
          }
          $("#hrr").empty();
          $("#hrr").append("*");
          var url = "${ctx}/psbrules/settlementPsbrules/serPsbRule";
            $.ajax({
                url: url,
                dataType: 'json',
                data:{ "psbid":psbid },
                cache: false,
                async: false,
                success: function(data, textStatus) {
                   $("#ckbspan").empty();
                   var str="";
                      $.each(data,function(i,item){
                          //选中的
                          if( ids!=null && ids.length>0 && ids.indexOf(item.id) > 0 ){
                             str = str + "<input id='spr' type='checkbox' checked='checked'  onclick='bigChange()' value='"+item.id+"' />"+item.rulename;
                          }else{ //不选中的
                             str = str + "<input id='spr' type='checkbox'  onclick='bigChange()' value='"+item.id+"' />"+item.rulename;
                         }
                      });
                  $("#ckbspan").append(str);
                }
            });
        
        }
		
	    function bigChange(){
          $("#hrr").empty();
          $("#hrr").append("*");
        }
        
        function sst(treeId,sel){
	        if(treeId==undefined){
				$("#citycode option:not(:first)").remove();
				return false;
	        }
            //   alert("treeId="+treeId +"||||sel="+sel); return false;
            var arr = treeId.split(",");
            // alert(arr.join("\n"));
            $("#pro_hidname").val(arr[1]);
           // var url = "${ctx}/crm/location/tCity/serSubTree";
            var url = "${ctx}/psbrulesrelation/settlementPsbrulesrelation/serSubTree";
            //重置select2控件的值
            var subIdSelect = $("#citycode");
            $("#citycode option:not(:first)").remove();
            subIdSelect.select2("val","");
            //区县
            var thirdIdSelect = $("#discode");
            $("#discode option:not(:first)").remove();
            thirdIdSelect.select2("val","");

            $.ajax({
                url: url,
                dataType: 'json',
                data:{
                    proid:arr[0]
                },
                cache: false,
                async: true,
                success: function(data, textStatus) {
                    var secTreeList = data;
                    for(var node in secTreeList){
                        if(sel!=null && secTreeList[node].code==sel){
							var option = "<option  selected='selected' value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
                        }else{
							var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
                        }
                        subIdSelect.append(option);
                    }
                    //给选中的赋值
                    if(sel!=null ){
                       $("#citycode").select2("val",sel);
                    }
                }
            });
        }
        
		function serSubTree(treeId){
		  // alert("treeId="+treeId);
		   sst(treeId,null);
		};
		
		function setCityName(cityname) {
			// alert(cityname);
			$("#city_hidname").val(cityname);
		};
        
       function save() {
			var citycode = $("#citycode").val();
			//    alert(citycode); 
			if(citycode == "" || citycode == null){
				confirmx("是否添加/修改省下全部市.请确认.", function(){
				    var ids="";                 
				    $("#ckbspan input[id=spr]").each(function(){  
				         if($(this).attr("checked")){
				           ids += $(this).val() + ",";
				         }  
				      });
				    if(ids.length<=0){
				      $("#hrr").empty();
				      $("#hrr").append("*必须选择*");
				      return ;
				    }    
				    ids=ids.substring(0,ids.length-1);
				    $("#city_rules").val(ids);
				    $("#inputForm").submit();
				});
				return ;
            }
            var ids="";                 
            $("#ckbspan input[id=spr]").each(function(){  
                 if($(this).attr("checked")){
                   ids += $(this).val() + ",";
                 }  
              });
            if(ids.length<=0){
              $("#hrr").empty();
              $("#hrr").append("*必须选择*");
              return ;
            }    
            ids=ids.substring(0,ids.length-1);
            $("#city_rules").val(ids);
            $("#inputForm").submit();
        };
            
        function serPsbTime(psbid){
        	if(psbid != null){
	        	var arr = psbid.split(",");
	        	var url = "${ctx}/psbrulesrelation/settlementPsbrulesrelation/serPsbTime";
	        	$.ajax({
	        		url : url,
	        		dataType : 'json',
	        		data : {"psbid" : arr[0]},
	        		cache : false,
	        		async : false,
	        		success : function(data){
	        			// alert(data.begintime);
	        			// alert(data.endtime);
	        			$("#begintime").val(data.begintime);
	        			$("#endtime").val(data.endtime);
	        		}
	        	});
        	} else{
        		$("#begintime").val(null);
    			$("#endtime").val(null);
        	}
        }
        
        window.onload=load(); 
        function load(){
            document.onreadystatechange=function() {  
            if(document.readyState=="complete"){
                 var a=$('#cpsbid option:selected').val();
                 var b=$('#city_rules').val();
                  //规则选中 
                  select(a,b);  
                  //当前的省份id
                   var p= $('#province').find('option:selected').attr('valueid');
                   //市选中的
                   var sel=$('#city_code').val();
                   //没值说明是新加，不是更新
                   if(p!=null && p!="" && sel!=null && sel!=""){
                     sst(p,sel);
                   } 
              }  
           }  
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/list">规则配置列表</a></li>
		<li class="active"><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/cityform?id=${settlementPsbrulesrelation.id}">城市规则配置<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit">${not empty settlementPsbrulesrelation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="psbrulesrelation:settlementPsbrulesrelation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="settlementPsbrulesrelation" action="${ctx}/psbrulesrelation/settlementPsbrulesrelation/citysave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="proname" id="pro_hidname"/>
		<form:hidden path="cityname" id="city_hidname"/>
		<form:hidden path="cityrules" id="city_rules"/>
		<%-- <form:hidden path="citycode" id="city_code"/> --%>
		<%-- <sys:message content="${message}"/> --%>
		<div class="control-group">
			<div style="text-align: left;"><h1>城市规则配置</h1></div>
		</div>
		<div class="control-group">
			<label class="control-label">PSB编码：</label>
			<div class="controls">
				<%-- <form:input path="psbid" htmlEscape="false" maxlength="11" class="input-xlarge required"/> --%>
				<form:select path="psbid" class="input-medium required"  id="cpsbid" onchange="serPsbTime($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="--请选择--"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.id } : ${psbinfo.psbname }" valueid="${psbinfo.id },${psbinfo.psbname }" />
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选择城市：</label>
			<div class="controls">
				<%-- <form:input path="proname" htmlEscape="false" maxlength="200" class="input-xlarge required"/> --%>
				<form:select path="procode" class="input-medium required" id="province" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid},${prov.proname}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<%--<form:select path="cityCode" class="input-medium" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">--%>
				<form:select path="citycode" class="input-medium " onchange="setCityName($(this).find('option:selected').attr('valueid'));">
                    <form:option value="" label="<-全部地市->"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}" valueid="${city.cityname }"/>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label required">城市对应规则：</label>
			<div class="controls"  >
				<%-- <form:checkboxes path="cityrules" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/> --%>
				<%-- <form:checkboxes path="cityrules" items="${ruleList }" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
 					--%>				
<%--  				<c:forEach items="${ruleList }" var="rule" varStatus="status"> --%>
<%-- 					<form:checkbox path="cityrules" value="${rule.id }" label="${rule.rulename }" /> --%>
<%-- 				</c:forEach> --%>
		      <div id="ckbspan"  > 
<%--                   <form:checkboxes path="hotelrules" items="${ruleList}" itemLabel="rulename" itemValue="psbid" htmlEscape="false" /> --%>
                </div>
                <div   id="" class="help-inline">
                     <font color="red">
                      <span   id="hrr" >
                         *
                      </span>
                    </font> 
                </div>
		    </div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">酒店对应规则：</label>
			<div class="controls">
				<form:checkboxes path="hotelrules" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">生效时间：</label>
			<div class="controls">
				<input id="begintime" name="begintime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">失效时间：</label>
			<div class="controls">
				<input id="endtime" name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${settlementPsbrulesrelation.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
<%-- 			<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
		 <input id="btnCancel2"class="btn btn-primary" type="button" value="保存城市规则" onclick="save()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>