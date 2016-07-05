<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店映射管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".deletehotemapping").click(function(){
				return confirm("您确认要删除吗?");
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/hotelmapping/hotelMapping/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/hotelmapping/hotelMapping/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hotelmapping/hotelMapping/">酒店映射列表</a></li>
		<shiro:hasPermission name="hotelmapping:hotelMapping:edit"><li><a href="${ctx}/hotelmapping/hotelMapping/form">酒店映射添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hotelMapping" action="${ctx}/hotelmapping/hotelMapping/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>ota酒店id：</label>
				<form:input path="otaHotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店名字：</label>
				<form:input path="hotelname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>酒店地址：</label>
				<form:input path="hoteladdr" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>渠道：</label>
				<form:select path="channelid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ChannelEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>酒店电话：</label>
				<form:input path="hotelphone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>酒店状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('HotelMappingState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>酒店id</th>
				<th>ota酒店id</th>
				<th>酒店名字</th>
				<th>酒店地址</th>
				<th>渠道</th>
				<th>酒店电话</th>
				<th>酒店状态</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>修改人</th>
				<th>修改时间</th>
				<shiro:hasPermission name="hotelmapping:hotelMapping:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hotelMapping">
			<tr>
				<td><a href="${ctx}/hotelmapping/hotelMapping/form?id=${hotelMapping.id}">
					${hotelMapping.id}
				</a></td>
				<td>
					${hotelMapping.hotelid}
				</td>
				<td>
					${hotelMapping.otaHotelid}
				</td>
				<td>
					${hotelMapping.hotelname}
				</td>
				<td>
					${hotelMapping.hoteladdr}
				</td>
				<td>
					${fns:getDictLabel(hotelMapping.channelid, 'ChannelEnum', '')}
				</td>
				<td>
					${hotelMapping.hotelphone}
				</td>
				<td>
					${fns:getDictLabel(hotelMapping.state, 'HotelMappingState', '')}
				</td>
				<td>
					${hotelMapping.createby}
				</td>
				<td>
					<fmt:formatDate value="${hotelMapping.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${hotelMapping.updateby}
				</td>
				<td>
					<fmt:formatDate value="${hotelMapping.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="hotelmapping:hotelMapping:edit"><td>
    				<a href="${ctx}/hotelmapping/hotelMapping/form?id=${hotelMapping.id}">修改</a>
					<a href="${ctx}/hotelmapping/hotelMapping/delete?id=${hotelMapping.id}" class="deletehotemapping">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>