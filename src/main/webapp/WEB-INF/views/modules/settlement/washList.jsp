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
		function chaxun() {
			$("#searchForm").submit();
		}
		// 导出
		function exportAll(){
			var accid = $("#accountid").val();
			var accrole = $("#accountrole").val();
			var hotelid = $("#hotelid").val();
			var sctype = $("#sctype").val();
			var accstate = $("#accountstate").val();
			location.href = "${ctx }/scadmin/washbill/exportall?accid="+accid+"&accrole="+accrole+"&hotelid="+hotelid+"&sctype="+sctype+"&accstate="+accstate;
		}
	</script>
</head>
<body>
	<div>
		<form id="searchForm" action="${ctx}/scadmin/washbill/list" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
			<ul class="ul-form">
				<div>
			        <input type="hidden" id="id" name="id" value="${id }">
			                   账户ID:<input type="text" id="accountid" name="accountid" value="${params.accountid }" style="width:130px;margin-right:15px;" >
                   <%-- 账户角色: <select id="accountrole" name="accountrole" style="width:130px;margin-right:15px;" >
	                   <option value="">全部</option>
	                   <option value="0" <c:if test="${params.accountrole == '1' }">selected = "selected"</c:if>>调整冲抵</option>
	                   <option value="1" <c:if test="${params.accountrole == '1' }">selected = "selected"</c:if>>酒店老板</option>
	                   <option value="2" <c:if test="${params.accountrole == '2' }">selected = "selected"</c:if>>酒店销售</option>
	                   <option value="3" <c:if test="${params.accountrole == '3' }">selected = "selected"</c:if>>旅行社</option>
	                   <option value="4" <c:if test="${params.accountrole == '4' }">selected = "selected"</c:if>>司机</option>
	                   <option value="5" <c:if test="${params.accountrole == '5' }">selected = "selected"</c:if>>洗涤厂</option>
	              </select>
			                   酒店id:<input type="text" id="hotelid" name="hotelid" value="${params.hotelid }" style="width:130px;margin-right:15px;" >  --%>
                   <%-- 结算方式: <select id="sctype" name="sctype" style="width:130px;margin-right:15px;" >
	                   <option value="">全部</option>
	                   <option value="1" <c:if test="${params.sctype == '1' }">selected = "selected"</c:if>>月结</option>
	                   <option value="2" <c:if test="${params.sctype == '2' }">selected = "selected"</c:if>>周结</option>
              		</select> --%>
			                   财务状态: <select id="accountstate" name="accountstate" style="width:130px;margin-right:15px;" >
			                   <option value="">全部</option>
			                   <option value="1" <c:if test="${params.accountstate == '1' }">selected = "selected"</c:if>>未结算</option>
			                   <option value="2" <c:if test="${params.accountstate == '2' }">selected = "selected"</c:if>>已结算</option>
			              </select>   
			                         
			       <button class="btn btn-primary" type="button" onclick="chaxun()" >查询</button>
			       <button class="btn btn-primary" id="btnExport" name="btnExport" type="button" onclick="exportAll()" >导出</button>
				</div>
			</ul>
		</form>
		
		 <table  id="table1" class="table table-bordered table-hover"  style="margin-top:10px;">
                <tr>
			        <!--  <th>id</th> -->
			        <th>开始日期</th>
			        <th>结束日期</th>
			        <th>账户ID</th>
			        <th>客户名称</th>
			        <th>结算方式</th>
			        <th>付款方式</th>
			        <th>财务状态</th>
			        <th>结算总额</th>
			        <th>订单总额</th>
			        <th>折扣金额</th>
			        <th>调整金额</th>
			        <th>充值卡抵扣金额</th>
			        <th>账单状态</th>
			        <th>操作</th>
			        
			    </tr>
			    <c:forEach items = "${message.result }" var = "item">
	                <tr >
	                	<td>${item.strBeginDate}</td>
			            <td>${item.strEndDate}</td>
	                    <td>${item.accountid}</td>
			            <td>${item.hotelname}</td>
			            <td>
			            	<c:if test="${item.sctype == 1}">月结</c:if>
			            </td>
			            <td>
			            	<c:if test="${item.paytype == 0 }">后付费</c:if>
			            	<c:if test="${item.paytype == 1 }">储蓄</c:if>
			            </td>
			            <td>
			            	<c:if test="${item.scstate == 0 }">未结算</c:if>
			            </td>
			            <td>${item.balance}</td>
	                    <td>${item.scsum}</td>
	                    <td>${item.discount }</td>
	                    <td>${item.adjustvalue }</td>
			            <td>${item.deposit}</td>
			            <td>
			            	<c:if test="${item.billstate == 0 }">初始化</c:if>
			            </td>
			            <td>
			            	<a href="${ctx }/scadmin/washdetail/list?id=${item.id}">明细</a>
			            </td>
                	</tr>
                </c:forEach>
            </table>
	    <div class="pagination">${page}</div>
	 </div>   
</body>
</html>