<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>渠道管理管理</title>
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
		function closechannel(id){
		    layer.confirm("确定要关闭吗？", function() {
		    	var posturl = $("#gdschannelurl").val()+'close';
                $.post(posturl,
                        {
                           id:id
                         },
                         function(data,status){
                        	 window.location.reload();
                         });
           });
		}
		function openchannel(id){
			layer.confirm("确定要开通吗？", function() {
                var posturl = $("#gdschannelurl").val()+'open';
                 $.post(posturl,
                         {
                            id:id
                          },
                          function(data,status){
                              //alert(data);
                        	  window.location.reload();
                          });
            });
		}
	    function deletechannel(id){
	    	layer.confirm("确定要删除吗？", function() {
	    		var posturl = $("#gdschannelurl").val()+'delete';
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
		<li class="active"><a href="${ctx}/channel/gdsChannel/">渠道管理列表</a></li>
		<shiro:hasPermission name="channel:gdsChannel:edit"><li><a href="${ctx}/channel/gdsChannel/form">渠道管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gdsChannel" action="${ctx}/channel/gdsChannel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
<!-- 			<li><label>id：</label> -->
<%-- 				<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/> --%>
<!-- 			</li> -->
			<li><label>渠道id：</label>
				<form:input path="channelid" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>渠道名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
<!-- 			<li><label>是否启用1启用 2未启用，预留扩展审核状态：</label> -->
<%-- 				<form:select path="state" class="input-medium"> --%>
<%-- 					<form:option value="" label=""/> --%>
<%-- 					<form:options items="${fns:getDictList('GdsChannelValidStateEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 				</form:select> --%>
<!-- 			</li> -->
<!-- 			<li><label>是否可见，F不可见，T可见：</label> -->
<%-- 				<form:input path="isVisible" htmlEscape="false" maxlength="1" class="input-medium"/> --%>
<!-- 			</li> -->
<!-- 			<li><label>创建时间：</label> -->
<!-- 				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" -->
<%-- 					value="<fmt:formatDate value="${gdsChannel.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -->
<!-- 			</li> -->
<!-- 			<li><label>创建人：</label> -->
<%-- 				<form:input path="createuser" htmlEscape="false" maxlength="20" class="input-medium"/> --%>
<!-- 			</li> -->
<!-- 			<li><label>更新时间：</label> -->
<!-- 				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" -->
<%-- 					value="<fmt:formatDate value="${gdsChannel.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -->
<!-- 			</li> -->
<!-- 			<li><label>更新人：</label> -->
<%-- 				<form:input path="updateuser" htmlEscape="false" maxlength="20" class="input-medium"/> --%>
<!-- 			</li> -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<input type="hidden" id ="gdschannelurl" value="${ctx}/channel/gdsChannel/" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>渠道id</th>
				<th>渠道名称</th>
				<th>状态</th>
<!-- 				<th>是否可见，F不可见，T可见</th> -->
				<th>创建时间</th>
				<th>创建人</th>
				<th>更新时间</th>
				<th>更新人</th>
				<shiro:hasPermission name="channel:gdsChannel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gdsChannel">
			<tr>
				<td><a href="${ctx}/channel/gdsChannel/form?id=${gdsChannel.id}">
					${gdsChannel.id}
				</a></td>
				<td>
					${gdsChannel.channelid}
				</td>
				<td>
					${gdsChannel.name}
				</td>
				<td>
					${fns:getDictLabel(gdsChannel.state, 'GdsChannelValidStateEnum', '')}
				</td>
<!-- 				<td> -->
<%-- 					${gdsChannel.isVisible} --%>
<!-- 				</td> -->
				<td>
					<fmt:formatDate value="${gdsChannel.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gdsChannel.createuser}
				</td>
				<td>
					<fmt:formatDate value="${gdsChannel.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gdsChannel.updateuser}
				</td>
				<shiro:hasPermission name="channel:gdsChannel:edit"><td>
				    <c:choose>
				       <c:when test="${gdsChannel.state != null && gdsChannel.state!='' && gdsChannel.state =='1'}">
				         <input class="btn btn-primary" type="button"  onclick="closechannel(${gdsChannel.id})" value="关闭"/>
				           <%-- <a href="${ctx}/channel/gdsChannel/close?id=${gdsChannel.id}" onclick="return confirmx('确认要关闭分销吗？', this.href)">关闭分销</a> --%>
				       </c:when>
				       <c:otherwise>
				          <input class="btn btn-primary" type="button"  onclick="openchannel(${gdsChannel.id})" value="开通"/>
				        <%--   <a href="${ctx}/channel/gdsChannel/open?id=${gdsChannel.id}" onclick="return confirmx('确认要开通分销吗？', this.href)">开通分销</a> --%>
				       </c:otherwise>
				    </c:choose> 
				
				
    				<a class="btn btn-primary"  href="${ctx}/channel/gdsChannel/form?id=${gdsChannel.id}">修改</a>
    				<input class="btn btn-primary" type="button"  onclick="deletechannel(${gdsChannel.id})" value="删除"/>
					<%-- <a href="${ctx}/channel/gdsChannel/delete?id=${gdsChannel.id}" onclick="return confirmx('确认要删除该渠道管理吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>