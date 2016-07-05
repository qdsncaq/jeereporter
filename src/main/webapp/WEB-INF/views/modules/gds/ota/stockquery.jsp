<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<!-- BEGIN HEAD -->
<head>
   <meta charset="utf-8" />
   <title>GDS.Admin</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   <%@include file="../layouts/importCss.jsp" %>
   <link rel="stylesheet" type="text/css" href="/static/gds/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
   <link rel="stylesheet" type="text/css" href="/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
   <!-- BEGIN PAGE LEVEL STYLES -->
   <!-- END PAGE LEVEL STYLES -->
   <!-- <link rel="shortcut icon" href="favicon.ico" /> -->
   <%@include file="../layouts/header.jsp" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
   <!-- BEGIN CONTAINER -->   
      <div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						供应配置 <small>GDS.Admin</small>&nbsp;&nbsp;&nbsp;<small>分销商：<span id="otaname"></span></small>&nbsp;&nbsp;&nbsp;<small>酒店：<span id="hotelname"></span></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
                        <li><i class="icon-th"></i> <a href="javascript:;">OTA列表</a>
                            <i class="icon-angle-right"></i></li>
                        <li><a href="${ctx}/distributor/ota/list">OTA列表</a><i
                            class="icon-angle-right"></i></li>
                        <li><a
                            href="${ctx}/distributor/ota/assign?id=${id}">酒店关联</a><i
                            class="icon-angle-right"></i></li>
                        <li><a href="${ctx}/distributor/ota/hotel/stockquery?id=${id}&hotelid=${hotelid}&otatype=${otatype}">库存查询</a></li>
                    </ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT -->
			<div class="row">
				<div class="col-xs-12">
					<!-- 列表开始 -->
					<div class="portlet box light-grey">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-globe"></i>库存查询
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
							<div class="actions">
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-bordered" id="weektable" >
								<thead>
									<tr class="active">
										<th class="center" colspan="1"><a href="javascript:void(0)" onclick="left()"><i
												class="icon-chevron-left"></i></a> <span class="title"></span> <a
											href="javascript:void(0)" onclick="right()"><i
												class="icon-chevron-right"></i></a></th>
										<th><span class="title1" ></span><br>星期一</th>
										<th><span class="title1"></span><br>星期二</th>
										<th><span class="title1"></span><br>星期三</th>
										<th><span class="title1"></span><br>星期四</th>
										<th><span class="title1"></span><br>星期五</th>
										<th><span class="title1"></span><br>星期六</th>
										<th><span class="title1"></span><br>星期日</th>
									</tr>
								</thead>
								<tbody id="xxtable">
								</tbody>
								<script type="text/html" id="listTemplate1"> 
				        		{{each result}}
									<tr>
										<th class="active" style="text-align:center">{{$value.roomtypename}}</th>
                                        <td day="{{$value.days[0]}}">房间数:{{$value.roomnum[0]}}</td>
                                        <td day="{{$value.days[1]}}">房间数:{{$value.roomnum[1]}}</td>
                                        <td day="{{$value.days[2]}}">房间数:{{$value.roomnum[2]}}</td>
                                        <td day="{{$value.days[3]}}">房间数:{{$value.roomnum[3]}}</td>
                                        <td day="{{$value.days[4]}}">房间数:{{$value.roomnum[4]}}</td>
                                        <td day="{{$value.days[5]}}">房间数:{{$value.roomnum[5]}}</td>
                                        <td day="{{$value.days[6]}}">房间数:{{$value.roomnum[6]}}</td>
									</tr>
				        		{{/each}}
    			 </script>
							</table>
						</div>
					</div>
					<!-- 列表结束 -->
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
   <%@include file="../layouts/importJs.jsp" %>
   <script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
   <script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
   <script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
   <script src="/static/gds/assets/scripts/distributor/stockquery.js"></script> 
   <script src="/static/gds/assets/scripts/distributor/dateutil.js"></script> 
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>





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