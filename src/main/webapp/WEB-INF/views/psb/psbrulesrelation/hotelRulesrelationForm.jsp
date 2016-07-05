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
		
	  function sst(treeId,sel){
		 if(treeId==undefined){
		  $("#citycode option:not(:first)").remove();
            return false;
         }
		      // alert(treeId); return false;
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
                      //  var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
                      //  subIdSelect.append(option);
                    
                     if(sel!=null && secTreeList[node].code==sel){
                          var option = "<option  selected='selected' value='"+secTreeList[node].code+"' valueid='" + secTreeList[node].cityid + ',' +secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
                        }else{
                           var option = "<option value='"+secTreeList[node].code+"' valueid='" + secTreeList[node].cityid + ',' +secTreeList[node].cityname+"' >"+secTreeList[node].cityname+"</option>";
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
	  
		function serThirdTree(treeId){
			var arr = treeId.split(",");
			$("#city_hidname").val(arr[1]);
			var url = "${ctx}/crm/location/tDistrict/serSubTree";
			//重置select2控件的值
			var thirdIdSelect = $("#discode");
			$("#disCode option:not(:first)").remove();
			thirdIdSelect.select2("val","");

			$.ajax({
				url: url,
				dataType: 'json',
				data:{
					cityid:arr[0]
				},
				cache: false,
				async: true,
				success: function(data, textStatus) {
					var secTreeList = data;
					for(var node in secTreeList){
						var option = "<option value='"+secTreeList[node].code+"' valueid='"+secTreeList[node].disname+"'>"+secTreeList[node].disname+"</option>";
						thirdIdSelect.append(option);
					}
				}
			});
		}
		
		function serDisName(disname){
			$("#dis_hidname").val(disname);
		}
		
		function serSubTree(treeId){
	       sst(treeId,null);
		};
		
		function setCityName(cityname) {
			$("#city_hidname").val(cityname);
        };
        
        function bigChange(){
          $("#hrr").empty();
          $("#hrr").append("*");
        }
        
        function cge(i){
          $("#val"+i).empty();
           $("#val"+i).append("*");
        }
        
        function validate(){
	         var a1=$("#hotel_id").val();
	         var a2=$("#hotel_pms").val();
	         var a3=$("#hotel_name").val();
	         var v0=0;
	         if(a1.length<1|| a1.length>200){
		         $("#val1").empty();
	             $("#val1").append("*长度必须介于1和200之间*");
	             v0++;
	         }
	         if(a2.length<1|| a2.length>200){
	             $("#val2").empty();
                 $("#val2").append("*长度必须介于1和200之间*");
                  v0++;
	         }
	         if(a3.length<1|| a3.length>200){
	           $("#val3").empty();
               $("#val3").append("*长度必须介于1和200之间*");
                v0++;
	         }
	         if(v0>0){
	          return false;
	         }
	         return true;
        }
 
        function save() {
         if(validate()){
          $("#hrr").empty();
          $("#hrr").append("*");
           $("#hotel_rules").val("");
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
            $("#hotel_rules").val(ids);
            $("#inputForm").submit();
          }
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
	             var b=$('#hotel_rules').val();
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
<body >
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/list">规则配置列表</a></li>
		<li class="active"><a href="${ctx}/psbrulesrelation/settlementPsbrulesrelation/form?id=${settlementPsbrulesrelation.id}">酒店规则配置<shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit">${not empty settlementPsbrulesrelation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="psbrulesrelation:settlementPsbrulesrelation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<!-- 酒店规则设置 -->
	<form:form id="inputForm" modelAttribute="settlementPsbrulesrelation"  action="${ctx}/psbrulesrelation/settlementPsbrulesrelation/save"   method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="proname" id="pro_hidname"/>
		<form:hidden path="cityname" id="city_hidname"/>
		<form:hidden path="disname" id="dis_hidname"/>
		<%-- <form:hidden path="citycode" id="city_code"/> --%>
		<%-- <form:hidden path="cityname" id="city_hidname"/> --%>
		<form:hidden path="hotelrules" id="hotel_rules" />
		<%-- <sys:message content="${message}"/> --%>
		<div class="control-group">
			<div style="text-align: left;"><h1>酒店规则配置</h1></div>
		</div>
		<div class="control-group">
			<label class="control-label">酒店ID：</label>
			<div class="controls">
				<form:input path="hotelid"  id="hotel_id"  oninput="cge(1)"  htmlEscape="false" maxlength="50" class="input-xlarge "/>
				<span   id="" class="help-inline">
                     <font color="red">
                      <span   id="val1" >
                         *
                      </span>
                    </font> 
                </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PMS编码：</label>
			<div class="controls">
				<form:input path="hotelpms"  id="hotel_pms" oninput="cge(2)"   htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span   id="" class="help-inline">
                     <font color="red">
                      <span   id="val2" >
                         *
                      </span>
                    </font> 
                </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">酒店名称：</label>
			<div class="controls">
				<form:input path="hotelname" id="hotel_name" oninput="cge(3)"   htmlEscape="false" maxlength="500" class="input-xlarge "/>
				<span   id="" class="help-inline">
                     <font color="red">
                      <span   id="val3" >
                         *
                      </span>
                    </font> 
                </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">PSB编码：</label>
			<div class="controls">
				<%-- <form:input path="psbid" htmlEscape="false" maxlength="11" class="input-xlarge required"/> --%>
				<form:select path="psbid" class="input-medium required"  id="cpsbid" onchange="serPsbTime($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="--请选择--"/>
					<%-- <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<c:forEach items="${psbList }" var="psbinfo" varStatus="status">
						<form:option value="${psbinfo.id }" label="${psbinfo.id } : ${psbinfo.psbname }" valueid="${psbinfo.id},${psbinfo.psbname}"/>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选择城市：</label>
			<div class="controls">
				<%-- <form:input path="proname" htmlEscape="false" maxlength="200" class="input-xlarge required"/> --%>
				<form:select path="procode" class="input-medium  required" id="province" onchange="serSubTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择省"/>
					<%--<form:options items="${provinceList}" itemLabel="proname" itemValue="proid" htmlEscape="false"/>--%>
					<c:forEach items="${provinceList}" var="prov" varStatus="status">
						<form:option value="${prov.code}" label="${prov.proname}" valueid="${prov.proid},${prov.proname}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<form:select path="citycode" class="input-medium required" onchange="serThirdTree($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择市"/>
					<%--<form:options items="${serSubTreeList}" itemLabel="cityname" itemValue="cityid" htmlEscape="false"/>--%>
					<c:forEach items="${cityList}" var="city" varStatus="status">
						<form:option value="${city.code}" label="${city.cityname}"/>
					</c:forEach>
				</form:select>
				&nbsp;&nbsp;
				<form:select path="discode" class="input-medium required" onchange="serDisName($(this).find('option:selected').attr('valueid'));">
					<form:option value="" label="选择县/区"/>
					<%-- <form:options items="${districtList}" itemLabel="disname" itemValue="code" htmlEscape="false"/> --%>
					<c:forEach items="${districtList}" var="dis" varStatus="status">
						<form:option value="${dis.code}" label="${dis.disname}" valueid="${dis.disname}"/>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">酒店对应规则：</label>
			<div class="controls"     >
<!-- 			<div class="controls" id="serPsbRuleId"> -->
				<%-- <form:checkboxes path="hotelrules" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/> --%>
				<div id="ckbspan"  > 
<%-- 				   <form:checkboxes path="hotelrules" items="${ruleList}" itemLabel="rulename" itemValue="psbid" htmlEscape="false" /> --%>
				</div>
				<div id="" class="help-inline">
					 <font color="red">
					  <span   id="hrr" >
					     *
					  </span>
					</font> 
				</div>
<%-- 				<c:forEach items="${ruleList }" var="rule" varStatus="status"> --%>
<%-- 					<form:checkbox path="cityrules" value="00" label="99" /> --%>
<%-- 				</c:forEach > --%>
			</div>
		</div>
		
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
<%-- 			  <shiro:hasPermission name="psbrulesrelation:settlementPsbrulesrelation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
            <input id="btnCancel2"class="btn btn-primary"   type="button" value="保存酒店规则" onclick="save()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>