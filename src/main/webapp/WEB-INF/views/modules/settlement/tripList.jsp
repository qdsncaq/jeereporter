<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function chaxun() {
			$("#searchForm").submit();
		}
		// 导出
		function exportAll(){
			var accid = $("#accountid").val();
			/* var accrole = $("#accountrole").val(); */
			/* var hotelid = $("#hotelid").val(); */
			var sctype = $("#sctype").val();
			var scstate = $("#scstate").val();
			var accountname = $("#accountname").val();
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var paytype = $("#paytype").val();
			location.href = "${ctx }/scadmin/tripbill/exportall?accid="+accid+"&sctype="+sctype+"&scstate="+scstate+"&accountname="+accountname+"&beginDate="+beginDate+"&endDate="+endDate+"&paytype="+paytype;
		}
		// 重新结算
		function fun(id){
			$.post('${ctx }/scadmin/tripbill/resettlement',
					{id : id},
					function(data){
						if(data == 'true'){
							layer.msg("重新结算完成!");
							window.location.reload();//刷新当前页面.
						} else {
							layer.msg("重新结算失败!");
							window.location.reload();//刷新当前页面.
							return false;
						}
					}
			);
		}
		$(function() {
		    var all_checked = false;
		    $(":checkbox").click(function() {
		        var table = $(this).parents("table");
		         //如果点击了全选的，那么下面都选或者不选
		        if($(this).attr("id") === "all") {
		            table.find(":checkbox").prop("checked", !all_checked);
		            all_checked = !all_checked;
		        } else {
		            table.find(":checkbox[id!=all]").each(function (i) {
		                if(!$(this).is(":checked")) {
		                    table.find("#all").prop("checked", false);
		                    all_checked = false;
		                    return false;
		                }
		                $("#all").prop("checked", true);
		                all_checked = true;
		            });
		        }
		    });
		    
		});
		
		function jiesuan(){
	     	var tableElement = document.getElementById("table1");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		// alert(selectedIds);return false;
	   		if(selectedIds==""){
	   			alert("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			 selectedIds=selectedIds.substr(0, selectedIds.length-1);
	   			$.post('${ctx }/scadmin/tripbill/settlement',
						{ids : selectedIds},
						function(result){
							// alert("data="+result);return false;
							if(result > 0){
								layer.msg("结算完成!结算"+result+"条.");
								window.location.reload(50000);return false;//刷新当前页面.return false;
							} else {
								layer.msg("没有账单进行结算!");
								// window.location.reload();//刷新当前页面.
								return false;
							}
						}
				);
	   		}
		}
	</script>
</head>
<body>
	<div>
		<form id="searchForm" action="${ctx}/scadmin/tripbill/list" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
				<div>
			        <input type="hidden" id="id" name="id" value="${id }">
			        <li><label>开始日期：</label>
						<input name="beginBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${settlementAccntBill.beginBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
						<input name="endBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${settlementAccntBill.endBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
					</li>
					<li><label>结束日期：</label>
						<input name="beginEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${settlementAccntBill.beginEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
						<input name="endEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${settlementAccntBill.endEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
					</li>
			                    客户ID:<input type="text" id="accountid" name="accountid" value="${params.accountid }" style="width:130px;margin-right:15px;" >
					客户名称:<input type="text" id="accountname" name="accountname" value="${params.accountname }" style="width:130px;margin-right:15px;" >
                    <%-- 结算方式: <select id="sctype" name="sctype" style="width:130px;margin-right:15px;" >
	                   <option value="">全部</option>
	                   <option value="1" <c:if test="${params.sctype == '1' }">selected = "selected"</c:if>>周结</option>
	                   <option value="2" <c:if test="${params.sctype == '2' }">selected = "selected"</c:if>>月结</option>
              		</select> --%>
			                    财务状态: <select id="scstate" name="scstate" style="width:130px;margin-right:15px;" >
			                   <option value="">--请选择--</option>
			                   <option value="1" <c:if test="${params.scstate == '1' }">selected = "selected"</c:if>>未结算</option>
			                   <option value="2" <c:if test="${params.scstate == '2' }">selected = "selected"</c:if>>已结算</option>
			              </select> 
			                    付款方式: <select id="paytype" name="paytype" style="width:130px;margin-right:15px;" >
			                   <option value="">--请选择--</option>
			                   <option value="1" <c:if test="${params.paytype == '1' }">selected = "selected"</c:if>>储值</option>
			                   <option value="2" <c:if test="${params.paytype == '2' }">selected = "selected"</c:if>>后付费</option>
			              </select> 
			        <li><label>财务状态：</label>
						<option value="">--请选择--</option>
	                   	<option value="1" <c:if test="${params.scstate == '1' }">selected = "selected"</c:if>>未结算</option>
	                   	<option value="2" <c:if test="${params.scstate == '2' }">selected = "selected"</c:if>>已结算</option>
					</li>
					<li><label>结算总额：</label>
						<form:input path="beginScsum" htmlEscape="false" class="input-medium"/>
						- <form:input path="endScsum" htmlEscape="false" class="input-medium"/>
					</li>
					<li><label>订单总额：</label>
						<form:input path="beginBalance" htmlEscape="false" class="input-medium"/>
						- <form:input path="endBalance" htmlEscape="false" class="input-medium"/>
					</li>
			       	<%-- 结账日期:<input id="beginDate" name="beginDate" type="text" readonly="readonly" class="input-medium Wdate" style="width:130px;"
						value="<fmt:formatDate value="${params.sbeginDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						　--　
					    <input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:130px;"
						value="<fmt:formatDate value="${params.sendDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>  --%>
			       
			       <button class="btn btn-primary" type="button" onclick="chaxun()" >查询</button>
			       <button class="btn btn-primary" id="btnExport" name="btnExport" type="button" onclick="exportAll()" >导出</button>
			       <button class="btn btn-primary" id="jiesuanbtn" name="jiesuanbtn" type="button" onclick="jiesuan()" >结算</button>
				</div>
			</ul>
		</form>
		
		 <table  id="table1" class="table table-bordered table-hover"  style="margin-top:10px;">
                <tr>
                	<th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
			        <!--  <th>id</th> -->
			        <th>开始日期</th>
			        <th>结束日期</th>
			        <th>账户ID</th>
			        <th>客户名称</th>
			        <th>结算方式</th>
			        <th>付款方式</th>
			        <th>财务状态</th>
			        <th>结算金额</th>
			        <th>订单总额</th>
			        <th>折扣金额</th>
			        <th>调整金额</th>
			        <th>充值卡抵扣金额</th>
			        <!-- <th>账单状态</th> -->
			        <th>操作</th>
			    </tr>
			    <c:forEach items = "${message.result }" var = "item">
	                <tr>
	                	<td>
	                       <input id="checkboxId" type="checkbox" name="check" value="${item.id}" /> 
	                    </td>
	                	<td>${item.strBeginDate}</td>
			            <td>${item.strEndDate}</td>
	                    <td>${item.accountid}</td>
			            <td>${item.hotelname}</td>
			            <td>
			            	<%-- <c:if test="${item.sctype == 1}">周结</c:if> --%>周结
			            </td>
			            <td>
			            	<c:if test="${item.paytype == 1 }">储值</c:if>
			            	<c:if test="${item.paytype == 2 }">后付费</c:if>
			            </td>
			            <td>
			            	<c:if test="${item.scstate == 1 }">未结算</c:if>
			            	<c:if test="${item.scstate == 2 }">已结算</c:if>
			            </td>
	                    <td>${item.scsum}</td>
			            <td>${item.balance}</td>
	                    <td>${item.discount }</td>
	                    <td>${item.adjustvalue }</td>
			            <td>${item.deposit}</td>
			            <%-- <td>
			            	<c:if test="${item.billstate == 0 }">初始化</c:if>
			            </td> --%>
			            <td>
			            	<a href="${ctx }/scadmin/tripdetail/list?id=${item.id}">明细</a>
			            	<c:if test="${item.scstate == 1 }"><a href="javascript:;"onclick="fun(${item.id})"> | 重新结算</a></c:if>
			            </td>
                	</tr>
                </c:forEach>
            </table>
	    <div class="pagination">${page}</div>
	 </div>   
	 <script type="text/javascript">
	     //结算  
	     $scope.statements = function () {
	     	var tableElement = document.getElementById("table1");
	   		var size = tableElement.rows.length;
	   		var selectedIds = "";
	   		for(var i =0;i<size-1;i++) {
	   		var checkbox = document.getElementsByName("check")[i];
	   			if(checkbox.checked==true){
	   				selectedIds += checkbox.value + ",";
	   			}
	   		}
	   		if(selectedIds==""){
	   			alert("没有选择要结算的数据！");
	   			return;
	   		}else{
	   			 selectedIds=selectedIds.substr(0, selectedIds.length-1);
	       		 settlementresultService.statements(selectedIds).then(function (dataResponse) {
	       			if(dataResponse.data['result']>0){
	       				$scope.search1();
	//       				alert("结算成功！");
	       			}else{
	       				alert("结算失败！");
	       			}
	             });
	   		}
	     };
	 </script>
</body>
</html>