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
   <%@include file="../layouts/header.jsp" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
   <!-- BEGIN CONTAINER -->
      <div class="page-content">
         <!-- BEGIN PAGE HEADER-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<ul class="page-breadcrumb breadcrumb" style="margin-top: -24px;">
                  <li>
                     <i class="icon-th"></i>
                     <a href="javascript:;">分销商管理</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><a href="${ctx}/distributor/list">分销商列表</a><i class="icon-angle-right"></i></li>
                  <li><a href="${ctx}/distributor/list/hotelrelation?otatype=${otatype}">酒店关联</a></li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>

			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT -->
			<div class="row">
				<div class="col-xs-12">
				
				
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
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>酒店id：</label>
                              			<input type="text" class="form-control" id="hotelid"> 
                                    </div>
                                 </div>
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>酒店名称：</label>
                              			<input type="text" class="form-control" id="hotelname">
                                    </div>
                                 </div>
                              </div>
                  	 	</div>
                  	 		<div class="form-actions fluid">
                  	 			<div class="col-md-offset-5 col-md-7">
                                	<button type="button" class="btn btn-info" onclick="search()">查询</button>
                   		    	</div>
                            </div>
                     </form>
                  </div>
               </div>
					
					
					
				   <!-- 列表开始 -->
				   <div class="portlet box light-grey">
	                  <div class="portlet-title">
	                     <div class="caption"><i class="icon-list"></i>已关联酒店列表</div>
	                     <div class="tools">
	                        <a href="javascript:;" class="collapse"></a>
	                        <a href="${ctx}/distributor/list/hotelrelation?otatype=${otatype}"  class="reload"></a>
	                     </div>
	                     <div class="actions">
	                        <a href="javascript:void(0)" data-target="#relationmodal" data-toggle="modal" onclick="initprovince();searchChooseHotels(1)" class="btn btn-warning btn-sm"><i class="icon-plus"></i> 添加关联</a>
	                        <a href="javascript:void(0)" onclick="batchDelete()" class="btn btn-success btn-sm"><i class="icon-trash"></i> 批量删除</a>
	                     </div>
	                  </div>
	                  <div class="portlet-body">
	                     <table class="table table-striped table-bordered table-hover" id="datatable">
	                     </table>
	                        <script type="text/html" id="listTemplate">
	                        <thead>
	                           <tr>
	                                <th class="table-checkbox"><input id="checkAll" onclick="checkAll(this)" type="checkbox" class="group-checkable" /></th>
	                              	<th>序列</th>
									<th>酒店ID</th>
									<th>酒店名称</th>
									<th>酒店类型</th>
			                    	<th>操作</th>
	                           </tr>
	                        </thead>
	                        <tbody id="databody">
							{{if result.length==0}}
								<tr>
				                   <td colspan="6" align="center" style="height:100px">暂无信息！</td>
				                </tr>
							{{else}}
				        		{{each result}}
									<tr >
										<td >
											<input name="checkbox1" type="checkbox" value="{{$value.id}}"/>
										</td>
										<td class="center">
											{{$index+1}}
										</td>
										<td>{{$value.hotelid}}</td>
					                    <td>{{$value.hotelname}}</td>
					                    <td>{{if $value.hoteltype=='1'}}
												旅馆
                   							{{else if $value.hoteltype=='2'}}
                                                                                                主题酒店
                   							{{else if $value.hoteltype=='3'}}
                                                                                                 精品酒店
                   							{{else if $value.hoteltype=='4'}}
                                                                                                 公寓
                   							{{else if $value.hoteltype=='5'}}
                                                                                                招待所
                   							{{else if $value.hoteltype=='6'}}
                                                                                                 客栈
                   							{{/if}}
										</td>
										<td class="center">
											<a href="javascript:void(0)" onclick="provisioning('{{$value.hotelid}}')" class="btn btn-default btn-xs purple"><i class="icon-edit"></i> 供应配置</a>
                                            <a href="javascript:void(0)" onclick="stockquery('{{$value.hotelid}}')" class="btn btn-default btn-xs purple"><i class="icon-edit"></i> 库存查询</a>
											<a href="javascript:void(0)" onclick="del('{{$value.id}}')" class="btn btn-default btn-xs black"><i class="icon-trash"></i> 删除</a>
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
				   <!-- 列表结束 -->
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->

	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS -->
   <%@include file="../layouts/importJs.jsp" %>
   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
   <script type="text/javascript" src="<%=basePath%>/static/gds/assets/scripts/distributor/hotelrelation.js"></script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>




<div class="modal fade" id="relationmodal" tabindex="-1" role="basic" aria-hidden="true">
   <div class="modal-dialog modal-wide">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">关联酒店</h4>
         </div>
         <div class="modal-body">
            <div class="container-fluid">
				<div class="row m_b20">
			        <div class="col-xs-12">
				        <form class="form-horizontal" role="form" id="queryform">
				        	<div class="form-body">
								<div class="row">
									<div class="form-group">
										<label class="control-label col-md-2">酒店名称:</label>
										<div class="col-md-10">
											<input type="search" class="form-control" id="q_hotelname">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="control-label col-md-2">省:</label>
										<div class="col-md-10">
											<select class="form-control" id="q_provcode" onchange="changeCity()">
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="control-label col-md-2">市:</label>
										<div class="col-md-10">
											<select class="form-control" id="q_citycode" >
											</select>
										</div>
									</div>
								</div>
<!-- 								<div class="row"> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label class="control-label col-md-2">酒店类型:</label> -->
<!-- 											<div class="col-md-10"> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_1" value="1"><label for="q_hoteltype_1">商务会议</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_2" value="2"><label for="q_hoteltype_2">温泉度假</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_3" value="3"><label for="q_hoteltype_3">海滨休闲</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_4" value="4"><label for="q_hoteltype_4">主题情趣</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_5" value="5"><label for="q_hoteltype_5">娱乐休闲</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_6" value="6"><label for="q_hoteltype_6">景区酒店</label> -->
<!-- 												<input type="checkbox" name="q_hoteltype" id="q_hoteltype_7" value="7"><label for="q_hoteltype_7">青年旅舍</label> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 								</div> -->
								<div class="row">
										<div class="form-group">
											<label class="control-label col-md-2">商圈位置:</label>
											<div class="col-md-10">
											
											    <c:forEach var="item" items="${busiarea}" varStatus="status"> 
													<input type="checkbox" name="q_busiarea" id="q_busiarea_${item.key}" value="${item.key}"><label for="q_busiarea_${item.key}">${item.value}</label>
											    </c:forEach>
											</div>
										</div>
								</div>
								<div class="row">
										<div class="form-group">
											<label class="control-label col-md-2">类型特色:</label>
											<div class="col-md-10">
											
											    <c:forEach var="item" items="${feature}" varStatus="status"> 
													<input type="checkbox" name="q_feature" id="q_feature_${item.key}" value="${item.key}"><label for="q_feature_${item.key}">${item.value}</label>
											    </c:forEach>
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_8" value="8"><label for="q_feature_8">精品酒店</label> -->
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_19" value="19"><label for="q_feature_19">公寓</label> -->
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_20" value="20"><label for="q_feature_20">购物便捷</label> -->
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_21" value="21"><label for="q_feature_21">客栈</label> -->
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_22" value="22"><label for="q_feature_22">农家乐</label> -->
<!-- 												<input type="checkbox" name="q_feature" id="q_feature_23" value="23"><label for="q_feature_23">招待所</label> -->
											</div>
										</div>
								</div>

								<div class="row">
										<div class="form-group">
											<label class="control-label col-md-2">设施服务:</label>
											<div class="col-md-10">
				        						<c:forEach var="item" items="${facilities}">
													<input type="checkbox" name="q_facilities" id="q_facilities_${item.key}" value="${item.key}"><label for="q_facilities_${item.key}">${item.value}</label>
											    </c:forEach>
<!-- 												<input type="checkbox" name="q_other" id="q_other_24" value="24"><label for="q_other_24">免费无线</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_25" value="25"><label for="q_other_25">免费有线</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_26" value="26"><label for="q_other_26">含早餐</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_27" value="27"><label for="q_other_27">三人/家庭房</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_28" value="28"><label for="q_other_28">加床</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_29" value="29"><label for="q_other_29">停车场</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_30" value="30"><label for="q_other_30">新开业/新装修</label> -->
<!-- 												<input type="checkbox" name="q_other" id="q_other_36" value="36"><label for="q_other_36">旅游票务服务</label> -->
											</div>
										</div>
								</div>

<!-- 								<div class="row"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="control-label col-md-2">附加服务:</label> -->
<!-- 										<div class="col-md-10"> -->
<!-- 											<input type="checkbox" name="q_other" id="q_other_31" value="31"><label for="q_other_31">可有偿提供桌早或打包早</label> -->
<!-- 											<input type="checkbox" name="q_other" id="q_other_32" value="32"><label for="q_other_32">独立卫浴</label> -->
<!-- 											<input type="checkbox" name="q_other" id="q_other_33" value="33"><label for="q_other_33">免费洗漱用品</label> -->
<!-- 											<input type="checkbox" name="q_other" id="q_other_34" value="34"><label for="q_other_34">洗衣服务</label> -->
<!-- 											<input type="checkbox" name="q_other" id="q_other_35" value="35"><label for="q_other_35">可刷卡</label> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->

								<div class="row">
								   <div class="col-md-12">
										<div class="form-group">
											<div class="col-md-12">
												 <button type="button" class="btn btn-info" onclick="searchChooseHotels(1)"><span class="glyphicon glyphicon-search"></span> 查询</button>
												 <button type="button" class="btn btn-info btn_addrelation" onclick="chooseHotel();">添加关联</button>
												 <button type="button" class="btn btn-info btn_addrelation" onclick="relateByCondition();">按搜索条件关联</button>
											</div>
										</div>
									</div>
								</div>
				        	</div>
						</form>
			        </div>
				</div>
			<!-- 表格开始 -->
		    <div class="row">
		    	<div class="col-xs-12">    
		        <table class="table table-striped table-bordered table-hover table-responsive" id="chooseHotelsDatatable">
		        </table> 
		        <script type="text/html" id="chooseHotelTemplate">
					<thead>
						<tr>
							<th class="table-checkbox"><input id="checkAll" onclick="checkAll(this)" type="checkbox" class="group-checkable" /></th>
							<th>序列</th>
							<th>酒店ID</th>
							<th>酒店名称</th>
							<%--<th>酒店类型</th>--%>
						</tr>
					</thead>
					<tbody id="chooseHotelsDatabody">
											
				{{if result.length==0}}
					<tr>
                   		<td colspan="5" align="center" style="height:100px">暂无信息！</td>
                	</tr>
				{{else}}
					{{each result}}
					 <tr>
						<td >
							<input name="checkbox1" type="checkbox" value="{{$value.id}}"/>
						</td>
						<td >
							{{$index+1}}
						</td>
						<td>{{$value.id}}</td>
					    <td>{{$value.hotelname}}</td>
						<%--<td>{{if $value.hoteltype=='1'}}
								旅馆
                   			{{else if $value.hoteltype=='2'}}
                                                                主题酒店
                   			{{else if $value.hoteltype=='3'}}
                                                                精品酒店
                   			{{else if $value.hoteltype=='4'}}
                                                                公寓
                   			{{else if $value.hoteltype=='5'}}
                                                                招待所
                   			{{else if $value.hoteltype=='6'}}
                                                                客栈
                   			{{/if}}
						</td>--%>
                      <tr>
        			{{/each}}
				{{/if}}
				   </tbody>
    			   </script>
					<div id="chooseHotelpagediv"></div>
				</div>
			</div>
		 </div> 
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-info btn_addrelation" onclick="chooseHotel();">添加关联</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div>
      <!-- /.modal-content -->
   </div>
   <!-- /.modal-dialog -->
</div>
