<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>去哪订单日志</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    var ctx = "${ctx}";
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action",ctx+"/qunar/orderlog/");
			$("#searchForm").submit();
        	return false;
        }
		function exportexcel(){
			$("#searchForm").attr("action",ctx+"/qunar/orderlog/exportexcel");
			$("#searchForm").submit();
        	return false;
        }
		function query(){
			if ($("#hotelid").val() && !IsNum($("#hotelid").val())) {
				alert("输入异常，酒店id应为数字");
				return;
			}
			/* if ($("#ordertype").val() && !IsNum($("#ordertype").val())) {
				alert("输入异常，订单类型应为数字");
				return;
			} */
			$("#searchForm").attr("action",ctx+"/qunar/orderlog/");
			$("#searchForm").submit();
			return false;
        }
		function reset(){
			$("#searchForm").attr("action",ctx+"/qunar/orderlog/");
			$("#searchForm").reset();
			$("#searchForm select").val('');
			return false;
        }

        function IsNum(s) {
		    if (s!=null && s!="") {
		        return !isNaN(s);
		    }
	    	return false;
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="orders" action="${ctx}/qunar/orderlog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号</label>
				<form:input path="orderno" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店id</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店名称</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
<%-- 		<li><label>订单类型</label>
				<form:input path="ordertype" htmlEscape="false" maxlength="5" class="input-medium" width="100px;"/>
			</li>--%>
 			<li><label>订单状态</label>
				<form:select path="qunarStatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('QunarOrderStatusEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间</label>
				<input name="createtime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orders.createtime1}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="createtime2" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orders.createtime2}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns">
				<input onclick="query()" id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>酒店编号</th>
				<th>酒店名称</th>
				<th>订单号</th>
				<!-- <th>订单类型</th> -->
				<th>订单状态</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orders">
			<tr>
				<td>
					${orders.hotelid}
				</td>
				<td>
					${orders.hotelname}
				</td>
				<td>
					${orders.orderno}
				</td>
				<%-- <td>
					${orders.ordertype}
				</td> --%>
				<td>
					${fns:getDictLabel(orders.qunarStatus, 'QunarOrderStatusEnum', '')}
				</td>
				<td>
					<fmt:formatDate value="${orders.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>