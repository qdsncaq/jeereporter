<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店分销价管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="../layouts/importJs.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		  function deletechannel(id){
	            layer.confirm("确定要删除吗？", function() {
	                var posturl = $("#gdschannelurl").val();
	                 $.post(posturl,
	                         {
	                            id:id
	                          },
	                          function(data,status){
	                              window.location.reload();
	                          });
	            });
	        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/distributprice/rackDistributePrice/">酒店分销价列表</a></li>
		<shiro:hasPermission name="distributprice:rackDistributePrice:edit"><li><a href="${ctx}/distributprice/rackDistributePrice/form">酒店分销价添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rackDistributePrice" action="${ctx}/distributprice/rackDistributePrice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="20" class="input-medium"/>
                 <input type="hidden" value="${rackDistributePrice.hotelname}"  id="hotelname">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<input type="hidden" id ="gdschannelurl" value="${ctx}/distributprice/rackDistributePrice/delete" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主键</th>
				<th>酒店id</th>
				<th>酒店名称</th>
				<th>房型id</th>
				<th>房型名称</th>
				<th>分销价</th>
				<th>创建时间</th>
				<th>创建人</th>
				<th>更新时间</th>
				<th>更新人</th>
				<shiro:hasPermission name="distributprice:rackDistributePrice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rackDistributePrice">
			<tr>
				<td><a href="${ctx}/distributprice/rackDistributePrice/form?id=${rackDistributePrice.id}">
					${rackDistributePrice.id}
				</a></td>
				<td>
					${rackDistributePrice.hotelid}
				</td>
				<td>
                    ${rackDistributePrice.hotelname}
                </td>
				<td>
					${rackDistributePrice.roomtypeid}
				</td>
				<td>
                    ${rackDistributePrice.roomtypename}
                </td>
				<td>
					${rackDistributePrice.price}
				</td>
				<td>
					<fmt:formatDate value="${rackDistributePrice.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rackDistributePrice.createuser}
				</td>
				<td>
					<fmt:formatDate value="${rackDistributePrice.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rackDistributePrice.updateuser}
				</td>
				<shiro:hasPermission name="distributprice:rackDistributePrice:edit"><td>
    				<a  class="btn btn-primary"  href="${ctx}/distributprice/rackDistributePrice/form?id=${rackDistributePrice.id}">修改</a>
					<%-- <a href="${ctx}/distributprice/rackDistributePrice/delete?id=${rackDistributePrice.id}" onclick="return confirmx('确认要删除该酒店分销价吗？', this.href)">删除</a> --%>
					<input class="btn btn-primary" type="button"  onclick="deletechannel(${rackDistributePrice.id})" value="删除"/>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>