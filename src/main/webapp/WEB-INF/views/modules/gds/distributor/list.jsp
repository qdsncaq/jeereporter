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
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/gds/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
   <!-- BEGIN PAGE LEVEL STYLES -->
   <!-- END PAGE LEVEL STYLES -->
   <!-- <link rel="shortcut icon" href="favicon.ico" /> -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body >
	<!-- BEGIN HEADER -->
	  <%@include file="../layouts/header.jsp" %>
	<!-- END HEADER -->
   <shiro:hasPermission name="gds:distributor:view">
   <div class="clearfix"></div>
   <!-- BEGIN CONTAINER -->   
   <div class="page-container">
      <!-- BEGIN PAGE -->
      <div class="page-content">
         <!-- BEGIN PAGE HEADER-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
               <ul class="page-breadcrumb breadcrumb" style="margin-top: -24px;">
                  <li>
                     <i class="icon-th"></i>
                     <a href="javascript:;">旅行社管理</a>
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><a href="${ctx}/distributor/list">旅行社列表</a></li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE CONTENT -->
         	<div class="row">
               <div class="col-xs-12">
                  <!-- BEGIN EXAMPLE TABLE PORTLET-->
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-search"></i>查询条件</div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                     </div>
                  </div>
                  <div class="portlet-body form">
                  	 <form action="#" class="horizontal-form">
                  	 	<div class="form-body">
                  	 		<div class="row">
                                 <div class="col-md-4 ">
                                    <div class="form-group">
                                       <label>旅行社：</label>
                              		   <select class="form-control" id="otatype">
                              		   	<option value="">全部</option>
									   </select>
                                    </div>
                                 </div>
                                 <div class="col-md-4 ">
                                    <div class="form-group">
                                       <label>登陆账号：</label>
                              		   <select class="form-control" id="loginaccount">
                              		   	<option value="">全部</option>
									   </select>
                                    </div>
                                 </div>
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>联系人：</label>
                              			<input type="text" class="form-control" id="contactor"> 
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>联系电话：</label>
                              			<input type="text" class="form-control" id="contactphone">
                                    </div>
                                 </div>
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>服务开始时间：</label>
                              			<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
		                                    <input type="text" class="form-control" name="from" id="begintime_from">
		                                    <span class="input-group-addon">to</span>
		                                    <input type="text" class="form-control" name="to" id="begintime_end">
		                                 </div>
                                    </div>
                                 </div>
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>服务结束时间：</label>
                              			<div class="input-group input-large date-picker input-daterange" data-date-format="yyyy-mm-dd">
		                                    <input type="text" class="form-control" name="from" id="endtime_from">
		                                    <span class="input-group-addon">to</span>
		                                    <input type="text" class="form-control" name="to" id="endtime_end">
		                                 </div>
                                    </div>
                                 </div>
                                 
                                  <!-- <div class="col-md-4">
                                    <div class="form-group">
                                        <label>状态：</label>
                                        <select  class="form-control"  name="status"  id="status"> 
                                                <option value ="1">正常</option>
                                                <option value ="2">已删除</option>
                                                <option value ="0">全部</option>
                                         </select>
                                    </div>
                                 </div> -->
                              </div>
                  	 	</div>
                  	 		<div class="form-actions fluid">
                  	 			<div class="col-md-offset-5 col-md-7">
                                	<button type="button" class="btn btn-info" onclick="isearch()">查询</button>
                       		    	<button type="reset" class="btn btn-default">重置</button>
                   		    	</div>
                            </div>
                     </form>
                  </div>
               </div>
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-list"></i>旅行社列表</div>
                     <div class="tools">
                        <a href="javascript:;" class="collapse"></a>
                        <a href="javascript:void(0)" onclick="isearch()" class="reload"></a>
                     </div>
                     <div class="actions">
                        <a href="javascript:void(0)" onclick="add()" class="btn btn-warning btn-sm"><i class="icon-plus"></i> 添加</a>
                        <shiro:hasPermission name="gds:distributor:delete">
                        <a href="javascript:void(0)" onclick="batchDelete()" class="btn btn-success btn-sm"><i class="icon-trash"></i> 批量删除</a>
                        </shiro:hasPermission>
                     </div>
                  </div>
                  <div class="portlet-body">
                     <!-- <div class="table-toolbar">
                         <div class="btn-group">
                           <button id="addbtn" class="btn" onclick="add()">
                           Add <i class="icon-plus"></i>
                           </button>
                        </div>
                        <div class="btn-group">
                           <button id="deletebtn" class="btn" onclick="batchDelete()">
                           Delete <i class="icon-trash"></i>
                           </button>
                        </div> -->
                        <!-- <div class="btn-group pull-right">
                           <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>
                           </button>
                           <ul class="dropdown-menu pull-right">
                              <li><a href="#">Print</a></li>
                              <li><a href="#">Save as PDF</a></li>
                              <li><a href="#">Export to Excel</a></li>
                           </ul>
                        </div>
                     </div>-->
                     
                     <table class="table table-striped table-bordered table-hover" id="datatable">
                     </table>
                     	<script type="text/html" id="listTemplate">
                        <thead>
                           <tr>
                                <th class="table-checkbox"><input id="checkAll" onclick="checkAll(this)" type="checkbox" class="group-checkable" /></th>
                              	<th>序列</th>
								<th>旅行社类型</th>
								<th>旅行社名称</th>
                              	<th>登录账号</th>
								<th>联系人</th>
								<th>联系方式</th>
								<th>服务开始时间</th>
								<th>服务结束时间</th>
		                    	<th>操作</th>
                           </tr>
                        </thead>
                        <tbody id="databody">
							{{if result.length==0}}
								<tr>
				                   <td colspan="9" align="center" style="height:100px">暂无信息！</td>
				                </tr>
							{{else}}
				        		{{each result}}
									<tr >
										<td >
											<input name="checkbox1" type="checkbox" value="{{$value.id}}" account="{{$value.loginaccount}}"/>
										</td>
										<td class="center">
											{{$index+1}}
										</td>
										<td>{{$value.otatype}}</td>
					                    <td>{{$value.name}}</td>
                                        <td>{{$value.loginaccount}}</td>
					                    <td>{{$value.contact}}</td>
					                    <td>{{$value.contactphone}}</td>
					                    <td>{{$value.serviceStarttimedesc}}</td>
					                    <td>{{$value.serviceEndtimedesc}}</td>
										<td class="center">
											<shiro:hasPermission name="gds:distributor:edit">
											<a href="javascript:void(0)" onclick="edit('{{$value.id}}')" class="btn btn-default btn-xs purple"><i class="icon-edit"></i> 编辑</a>
											<a href="javascript:void(0)" onclick="relation('{{$value.otatype}}','{{$value.name}}')" class="btn btn-default btn-xs black"><i class="icon-star"></i> 酒店关联</a>
											<a href="javascript:void(0)" onclick="checkAccType('{{$value.otatype}}')" class="btn btn-default btn-xs black"><i class="icon-money"></i>充值</a>
											</shiro:hasPermission>
                                            <shiro:hasPermission name="gds:distributor:delete">
											<a href="javascript:void(0)" onclick="del('{{$value.id}}','{{$value.loginaccount}}')" class="btn btn-default btn-xs black"><i class="icon-trash"></i> 删除</a>
                                            </shiro:hasPermission>
										</td>
									</tr>
				        		{{/each}}
							{{/if}}
                      </tbody>
    			 </script>
                     
                     <!-- 分页 -->
                     <div id="pagediv"></div>
                  </div>
               </div>
               <!-- END EXAMPLE TABLE PORTLET-->
               </div>
            </div>
         <!-- END PAGE CONTENT -->
      </div>
      <!-- END PAGE -->    
   </div>
   <!-- END CONTAINER -->
   <!-- BEGIN JAVASCRIPTS -->
   </shiro:hasPermission>
   <shiro:lacksPermission name="gds:distributor:view">您没有操作本页面的权限.</shiro:lacksPermission>
   		<%@include file="../layouts/importJs.jsp" %>
   		<script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
   		<script src="<%=basePath%>/static/gds/assets/scripts/distributor/index.js"></script>
   <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>