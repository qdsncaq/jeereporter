<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房型映射管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".deleteroomtypemapping").click(function(){
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
		<form id="importForm" action="${ctx}/roomtypemapping/roomtypeMapping/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/roomtypemapping/roomtypeMapping/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/roomtypemapping/roomtypeMapping/">房型映射列表</a></li>
		<shiro:hasPermission name="roomtypemapping:roomtypeMapping:edit"><li><a href="${ctx}/roomtypemapping/roomtypeMapping/form">房型映射添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="roomtypeMapping" action="${ctx}/roomtypemapping/roomtypeMapping/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>酒店id：</label>
				<form:input path="hotelid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>ota酒店id：</label>
				<form:input path="otaHotelid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>房型id：</label>
				<form:input path="roomtypeid" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
				<li><label>房型名字：</label>
				<form:input path="roomtypename" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>ota房型id：</label>
				<form:input path="otaRoomtypeid" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>ota房型名：</label>
				<form:input path="otaRoomtypename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('RoomtypeMappingEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
				<li><label>床型：</label>
				<form:select path="bedtype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('BedTypeEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>渠道：</label>
				<form:select path="channelid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ChannelEnum')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>主键</th>
				<th>酒店id</th>
				<th>ota酒店id</th>
				<th>房型id</th>
				<th>房型名字</th>
				<th>ota房型id</th>
				<th>ota房型名</th>
				<th>床型</th>
				<th>状态</th>
				<th>渠道</th>
				<!-- <th>创建人</th> -->
				<th>创建时间</th>
				<!-- <th>修改人</th>
				<th>修改时间</th> -->
				<shiro:hasPermission name="roomtypemapping:roomtypeMapping:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="roomtypeMapping">
			<tr>
				<td><a href="${ctx}/roomtypemapping/roomtypeMapping/form?id=${roomtypeMapping.id}">
					${roomtypeMapping.id}
				</a></td>
				<td>
					${roomtypeMapping.hotelid}
				</td>
				<td>
					${roomtypeMapping.otaHotelid}
				</td>
				<td>
					${roomtypeMapping.roomtypeid}
				</td>
				<td>
					${roomtypeMapping.roomtypename}
				</td>
				<td>
					${roomtypeMapping.otaRoomtypeid}
				</td>
				<td>
					${roomtypeMapping.otaRoomtypename}
				</td>
				<td>
					${fns:getDictLabel(roomtypeMapping.bedtype, 'BedTypeEnum', '')}
				</td>
				<td>
					${fns:getDictLabel(roomtypeMapping.state, 'RoomtypeMappingEnum', '')}
				</td>
				<td>
					${fns:getDictLabel(roomtypeMapping.channelid, 'ChannelEnum', '')}
				</td>
				<%-- <td>
					${roomtypeMapping.createby}
				</td> --%>
				<td>
					<fmt:formatDate value="${roomtypeMapping.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${roomtypeMapping.updateby}
				</td>
				<td>
					<fmt:formatDate value="${roomtypeMapping.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<shiro:hasPermission name="roomtypemapping:roomtypeMapping:edit"><td>
    				<a href="${ctx}/roomtypemapping/roomtypeMapping/form?id=${roomtypeMapping.id}">修改</a>
					<a href="${ctx}/roomtypemapping/roomtypeMapping/delete?id=${roomtypeMapping.id}" class="deleteroomtypemapping">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>