<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
		
		// 重新结算
		function resettlement(id,sbt,set,ct){
			if(id==null){
				alert("参数为空");
			return false;	
			}
			 $.ajax( {  
			    url:'${ctx}/sc/result/resettlement',// 跳转到 action  
			    data:{  
			             "id" : id,
			             "sbt" : sbt,
			             "set" : set,
			             "ct" : ct
			    },  
			    type:'post',  
			    cache:false,  
			    dataType:'html',  
			    success:function(data) {  
			        alert("操作成功."); 
			    },  
			    error : function() {  
			        // view("异常！");  
			        alert("计算失败！");  
			    }  
			});
		}
		
		// 结算
		function set(id){
	 		$.post( "${ctx }/sc/result/settlement",
	 			{
	 				id :id
	 			},
				function(data){
					// alert(data);
					if(data == 1){
						alert("结算成功！");
						setTimeout("window.location.reload('resultList.jsp')",1000);
					}else{
						alert("结算失败！该条记录已结算。");
					}
				});
	 	}
		
		// 导出
		function exportAll(){
			var tarName = $("#tarName").val();
			location.href = "${ctx }/sc/result/export?settlementTargetName="+tarName;
		}
	</script>
</head>
<body>
	<%-- 顶上标题栏//不显示
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sc/result/">结算结果查询</a></li>
	</ul><br/> --%>
	<div>
		<form:form id="searchForm" action="${ctx}/sc/result/list" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
				<div>
			        <input type="hidden" id="id" name="id" value="${id }">
			        <input type="hidden" id="settlementTargetId" name="settlementTargetId" value="${settlementTargetId }">
			        结算方名称:<input type="text" id="tarName" name="settlementTargetName" value="${params.settlementTargetName }" style="width:130px;margin-right:15px;" > 
			        状态: <select name="settlementStatus" style="width:130px;margin-right:15px;" >
			                   <option value="0">全部</option>
			                   <option value="1" <c:if test="${params.settlementStatus == '1' }">selected = "selected"</c:if>>未结算</option>
			                   <option value="2" <c:if test="${params.settlementStatus == '2' }">selected = "selected"</c:if>>已结算</option>
			              </select>
			       <%--  收/支: <select name="settlementCategory" style="width:130px;margin-right:15px;" >
			                   <option value="0">全部</option>
			                   <option value="1" <c:if test="${params.settlementCategory == '1' }">selected = "selected"</c:if>>收入</option>
			                   <option value="-1" <c:if test="${params.settlementCategory == '-1' }">selected = "selected"</c:if>>支出</option>
			              </select>    --%>
			       <%--  销售类型: <select name="saleType" style="width:130px;margin-right:15px;" >
			                   <option value="0">全部</option>
			                   <option value="1" <c:if test="${params.saleType == '1' }">selected = "selected"</c:if>>一般订单</option>
			                   <option value="2" <c:if test="${params.saleType == '2' }">selected = "selected"</c:if>>今夜特价</option>
			              </select> --%>
			       <button type="button" class="btn btn-primary" onclick="return page();">查询</button>
			       <button type="button" class="btn btn-primary" id="btnExport" name="btnExport" onclick="exportAll()">导出</button>			       
			       <%--
			       <button type="button" class="btn btn-primary" ng-click="export1(true)">导出本页</button>
			       --%>
				</div>
			</ul>
		</form:form>
		
		<table style="width:1400px;" id="table1" class="table table-bordered table-hover" >
                <tr>
			       <th style="width:10px;"><input type="checkbox" name="all" id="all" /> </th>
			        <th style="width:120px;">结算方名称</th>
			        <!-- <th>费用类型</th> -->
			        <th style="width:120px;">结算总金额</th>
			        <th style="width:120px;">折扣后总金额</th>
			        <th style="width:120px;">状态</th>
			        <th style="width:120px;">结算类型</th>
			        <!-- <th>收/支</th> -->
			        <!-- <th>销售类型</th> -->
			        <th style="width:160px;">起始日期</th>
			        <th style="width:160px;">截止日期</th>
			        <th style="width:160px;">创建时间</th>
			        <th >操作</th>
			    </tr>
			    <c:forEach items = "${message.result }" var = "item">
	                <tr >
	                	<td>
		                	<div >
	                        	<input id="id" type="checkbox" name="check" value="${item.id}" /> 
	                     	</div>
                     	</td>
	                    <td>${item.settlementTargetName}</td>
	                    <%-- <td>${item.strFeeType}</td> --%>
			            <td>${item.settlementTotalSum}</td>
			            <td>${item.settlementSaleSum}</td>
			            <td>${item.strSettlementStatus}</td>
			            <td>周结</td>
			            <%-- <td>${item.strSettlementCategory}</td> --%>
			            <%-- <td>${item.strSaleType}</td> --%>
			            <td>${item.strSettlementBeginTime}</td>
			            <td>${item.strSettlementEndTime}</td>
			            <td>${item.strCreateTime}</td>
			            <td>
	                        <%-- <a href="${ctx }/sc/result/settlement?id=${item.id }"> --%>
	                        	<button type="button" id="setButton" class="btn btn-primary" onclick="set('${item.id }')" >结算</button>
	                        <!-- </a> -->
	                    </td>
                	</tr>
                </c:forEach>
           </table>
	    <div class="pagination">${page}</div>
	 </div>   
</body>
</html>