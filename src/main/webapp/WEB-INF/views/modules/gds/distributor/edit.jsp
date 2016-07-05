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
   <link rel="stylesheet" type="text/css" href="${ctxStatic}/gds/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
   <link rel="stylesheet" type="text/css" href="${ctxStatic}/gds/assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
   <link rel="stylesheet" type="text/css" href="${ctxStatic}/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
   <link rel="stylesheet" type="text/css" href="${ctxStatic}/gds/assets/plugins/bootstrap-switch/static/stylesheets/bootstrap-switch-conquer.css"/>
   
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
					<ul class="page-breadcrumb breadcrumb" style="margin-top: -24px;">
						<li><i class="icon-th"></i> <a href="javascript:;">分销商管理</a>
							<i class="icon-angle-right"></i></li>
						<li><a href="${ctx}/distributor/list">分销商列表</a><i
							class="icon-angle-right"></i></li>
						<li><a href="${ctx}/distributor/list/edit">分销商新增或修改</a></li>
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
											<i class="icon-list"></i>分销商基本信息
										</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a> 
										</div>
									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="#" class="form-horizontal">
											<div class="form-body">
												<!-- <h3 class="form-section">分销商基本信息</h3> -->
												<input type="hidden" class="form-control" id="id">
												<!--/row-->
												 <div class="row">
													<div class="col-md-6">
														<%-- <div class="form-group">
															<label class="control-label col-md-3">分销商logo</label>
															<div class="col-md-12">
															<input type="hidden" id="domain" value="7xp8rx.com1.z0.glb.clouddn.com">
        													<input type="hidden" id="uptoken" value="${uptoken}">
												                <a class="btn btn-default btn-lg " id="pickfiles" href="#" >
												                    <i class="glyphicon glyphicon-plus"></i>
												                    <span>选择文件</span>
												                </a>
												               
													        </div>
													        <label class="control-label col-md-3" id="logopath"></label>
														</div> --%>
														<%@include file="./upload.jsp"%>
													</div>
													
												</div> 
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">分销商id</label>
															<div class="col-md-9">
																<input disabled type="text" class="form-control" id="otatype">
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">分销商名称<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="name" maxlength="50" onblur="checkname()">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">登陆账号(英文和数字)<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="loginaccount" maxlength="30" onblur="checkLoginaccount()">
															</div>
														</div>
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">联系人姓名<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="contact" maxlength="50" onblur="checkcontact()">
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">固定电话</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="telephone" maxlength="20">
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">联系人手机<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="contactphone" maxlength="20" onblur="checkcontactphone()">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">公司网址</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="website" maxlength="30">
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">联系人岗位</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="contactposition" maxlength="20">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">QQ号码</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="qqNumber" maxlength="20">
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">联系人邮箱</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="contactemail" maxlength="50">
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">传真号码</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="faxNumber" maxlength="30">
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">省</label>
															<div class="col-md-9">
																<select class="form-control" id="provcode" onchange="changeCity()">
																</select>
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">市</label>
															<div class="col-md-9">
																<select class="form-control" id="citycode">
																</select>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">详细地址<span class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="address" maxlength="100" onblur="checkaddress()">
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">服务时间</label>
															<div class="col-md-9">
																<div class="input-group date form_datetime">                                       
								                                    <input type="text" size="16" class="form-control" id="serviceStarttimedesc">
								                                    <span class="input-group-btn">
								                                    <button class="btn btn-success date-set" type="button"><i class="icon-calendar"></i></button>
								                                    </span>
								                                 </div>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">至</label>
															<div class="col-md-9">
																<div class="input-group date form_datetime">                                       
								                                    <input type="text" size="16" class="form-control" id="serviceEndtimedesc">
								                                    <span class="input-group-btn">
								                                    <button class="btn btn-success date-set" type="button"><i class="icon-calendar"></i></button>
								                                    </span>
								                                 </div>
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												<div class="row">
													
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">结算方式</label>
															<div class="col-md-3">
																<select class="form-control" id="settlementCurrency" onchange="changeCurrency()">
																<option value="1">预付结算</option>
																<option value="2">后付结算</option>
																</select>
															</div>
															<div class="col-md-6" id="postPayThresholdDiv">
																<label class="control-label col-md-3">阈值</label>
																<div class="col-md-9">
																	<input type="text" class="form-control" id="postPayThreshold" maxlength="100" >
																</div>
															</div>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">开户行</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="bankname" maxlength="100" >
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">返佣金(最多两位小数类型:0.01)</label>
															<div class="col-md-9">
															    <input type="text" class="form-control" id="commission" maxlength="30">
															</div>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">账号</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="bankaccount" maxlength="50" >
															</div>
														</div>
													</div>
                                                </div>
                                                <!--/row-->
                                                <div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">开户行单位全称</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="companyname" maxlength="100">
															</div>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">税号</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="tariffno" maxlength="30" >
															</div>
														</div>
													</div>
													   
												</div>
                                                <!--/row-->
                                                <div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">上浮金额(同时设置默认按上浮金额计算)</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="upprice" maxlength="3">
															</div>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">上浮比例 %(同时设置默认按上浮金额计算)</label>
															<div class="col-md-9">
																<input type="text" class="form-control" id="upper" maxlength="4" >
															</div>
														</div>
													</div>
													   
												</div>
												 <!--/row-->
                                                <div class="row">
                                                	<%--<div class="col-md-6">
														<div class="form-group">
															<label class="control-label col-md-3">渠道</label>
															<div class="col-md-9">
																<select class="form-control" id="channelid">
																</select>
															</div>
														</div>
													</div>--%>
													<div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3">公司简介</label>
                                                            <div class="col-md-9">
                                                                <textarea class="form-control" rows="4" id="introduction" ></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
													<div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3">自动关联酒店开关</label>
                                                            <div class="col-md-9">
                                                                <div id="autoRelateSwitch" class="make-switch" data-on="success" data-off="danger" data-on-label="开" data-off-label="关">
							                                       <input id ="hehe" type="checkbox" class="toggle" />
							                                    </div>
                                                            </div>
                                                        </div>
													</div>
													<!--/span-->
                                                 
                                                    <!--/span-->
												</div>
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
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
   <%@include file="../layouts/importJs.jsp" %>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"  ></script>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/plupload/plupload.full.min.js"></script>
   <script type="text/javascript" src="${ctxStatic}/gds/assets/plugins/plupload/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctxStatic}/gds/assets/scripts/ui.js"></script>
   <script src="${ctxStatic}/gds/assets/plugins/qiniu.js"></script>
   <script src="${ctxStatic}/gds/assets/scripts/distributor/main.js"></script>
   <script src="${ctxStatic}/gds/assets/scripts/distributor/edit.js"></script>
	<!-- END FOOTER -->

<script>
/* $("#pickfiles").click(function(){
	Qiniu.uploader.start();
}); */
</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>