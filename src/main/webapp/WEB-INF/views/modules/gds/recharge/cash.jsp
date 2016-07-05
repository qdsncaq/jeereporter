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
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
               <h3 class="page-title">
						充值管理 <small>GDS.Admin</small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li><i class="icon-th"></i> <a href="javascript:;">分销商管理</a>
							<i class="icon-angle-right"></i></li>
						<li><a href="${ctx}/distributor/list">分销商列表</a><i
							class="icon-angle-right"></i></li>
						<li><a href="${ctx}/recharge/cash?otatype=${otatype}">充值</a></li>
					</ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE CONTENT -->
         <div class="row">
				<div class="col-xs-12">
								<div class="portlet">
									<div class="portlet-title">
										<div class="caption">
											<i class="icon-list"></i>账户充值
										</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a> 
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal">
											<div class="form-body">
												<input type="hidden" class="form-control" id="id">
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">账户余额</label>
															<div class="col-md-9">
																<input disabled type="text" class="form-control" id="balance" value="${balance}">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">充值金额<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="cash" maxlength="10" >
															</div>
														</div>
													</div>
												</div>
											
												
                                                <!--/row-->
											</div>
											<div class="form-actions fluid">
												<div class="row">
													<div class="col-md-6">
														<div class="col-md-offset-3 col-md-9">
															<button type="button" onclick="save()" class="btn btn-success">保存</button>
															<button type="button" onclick="cancel()" class="btn btn-info">取消</button>
														</div>
													</div>
													<div class="col-md-6"></div>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
				</div>
			</div>
			<!-- END PAGE CONTENT -->
      </div>
      <!-- END PAGE -->    
   </div>
   
    <%@include file="../layouts/importJs.jsp" %>
   	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
   	<script src="/static/gds/assets/scripts/distributor/cash.js"></script> 

</body>
<!-- END BODY -->
</html>