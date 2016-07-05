<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>供应配置</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/gds/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var bathpath = "${ctx}/";
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/distributor/ota/">OTA列表</a></li>
	<li><a href="${ctx}/distributor/ota/assign?id=${distributorConfig.id}">关联酒店</a></li>
	<li class="active"><a href="">供应配置</a></li>
</ul>
<div class="container-fluid breadcrumb">
	<div class="row-fluid span12">
		<span class="span3">OTA编码: ${otatype}</span>
		<span class="span3">渠道名称: ${channelname}</span>
		<span class="span3">酒店编码: ${hotelid}</span>
		<span class="span3">酒店名称: ${hotelname}</span>
	</div>
</div>
<form:form id="searchForm" modelAttribute="searchform" action="" method="post"  class="breadcrumb form-search">
	<ul class="ul-form">
		<li class="btns">
			<a href="javascript:void(0)" data-target="#relationmodal"
			   data-toggle="modal" onclick="configUpperPrice()"
			   class="btn btn-success">统一设置价格上浮</a>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="weektable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th style="text-align: center;  vertical-align: middle;" colspan="2"><a href="javascript:void(0)" onclick="left()"><i
				class="icon-chevron-left"></i></a> <span class="title"></span> <a
				href="javascript:void(0)" onclick="right()"><i
				class="icon-chevron-right"></i></a></th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期一</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期二</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期三</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期四</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期五</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期六</th>
		<th style="text-align: center;  vertical-align: middle;"><span class="title1"></span><br>星期日</th>
	</thead>
	<tbody id="xxtable">
	</tbody>
</table>

<div id="relationmodal" class="modal fade" tabindex="-1"
	 aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
				<h4 class="modal-title">设置价格上浮(同时设置默认取上浮金额)</h4>
			</div>
			<div class="modal-body">
				<div class=" form">
					<form action="#" class="form-horizontal" id="configform">
						<div class="form-body">
							<input type="hidden" class="form-control" id="otahotelid">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<!-- <div class="col-md-1">
											<input type="radio" name="optionsRadios" ref="upprice" >
										</div> -->
										<label class="control-label col-md-3">上浮金额</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="upprice">
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<!-- <div class="col-md-1">
											<input type="radio" name="optionsRadios" ref="upper" >
										</div> -->
										<label class="control-label col-md-3">上浮比例 %</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="upper">
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
				<button type="button" class="btn btn-success" onclick="saveConfigUpperPrice()">保存</button>
			</div>
		</div>
	</div>
</div>
<!--//统一配置模板 -->
<script type="text/html" id="listTemplate1">
	{{each result}}
	<tr>
		<th class="active">{{$value.roomtypename}}</th>
		<th >总数:{{$value.totalnum}} <br>分配:&nbsp;<input onchange="saveSaleRoomNum('{{$value.otaroomtypeid}}',this.value,'{{$value.totalnum}}')" type="text" size="3" maxlength="4" value="{{$value.saleroomnum}}" style="width:20%; height:15px;"/></th>
		<td day="{{$value.days[0]}}">门市价:{{$value.rackprice[0]}}<br>分销价:{{$value.saleprice[0]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[0]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[0]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[1]}}">门市价:{{$value.rackprice[1]}}<br>分销价:{{$value.saleprice[1]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[1]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[1]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[2]}}">门市价:{{$value.rackprice[2]}}<br>分销价:{{$value.saleprice[2]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[2]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[2]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[3]}}">门市价:{{$value.rackprice[3]}}<br>分销价:{{$value.saleprice[3]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[3]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[3]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[4]}}">门市价:{{$value.rackprice[4]}}<br>分销价:{{$value.saleprice[4]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[4]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[4]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[5]}}">门市价:{{$value.rackprice[5]}}<br>分销价:{{$value.saleprice[5]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[5]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[5]}}" style="width:20%;height:15px;"/></td>
		<td day="{{$value.days[6]}}">门市价:{{$value.rackprice[6]}}<br>分销价:{{$value.saleprice[6]}}<br>渠道价:&nbsp;<input onchange="saveChannelPrice('{{$value.roomtypeid}}','{{$value.days[6]}}',this.value)" type="text" size="3" maxlength="4" value="{{$value.channelprice[6]}}" style="width:20%;height:15px;"/></td>
	</tr>
	{{/each}}
</script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/scripts/app.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/laypage-v1.3/laypage/laypage.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/template/_template.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/scripts/utils.js"></script>

<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/scripts/distributor/suplyprovisioning.js"></script>
<script type="text/javascript" src="${ctxStatic}/gds/assets/scripts/distributor/dateutil.js"></script>
</body>
</html>
