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
    <shiro:hasPermission name="gds:order:view">
       <div class="page-content">
			  <!-- BEGIN PAGE HEADER-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
               <h3 class="page-title">
                  订单列表 <small>GDS.Admin</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-th"></i>
                     <a href="javascript:;">订单管理</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><a href="${ctx}/order/list"> 订单列表</a></li>
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
					<!-- 查询条件 -->
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-search"></i>查询条件
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<!-- 查询条件 -->
					<div class="portlet-body form">
						 <form action="${ctx}/order/list" method="get" id="hotellist" class="horizontal-form"   >
						    <div class="form-body">
                                <div class="row">
                                    <input type="hidden" value="${fns:getUser().name}"  id="loginusername">
								    <input  type="hidden" value="${pageItem.index }"  name="index" id="index"/>
								    <input  type="hidden" value="0" id="totalItem" name="totalItem"/>
								    <input  type="hidden" value="${otaType }" id="otaType"  name="otaType"/>
						            <%-- 
						            <input  type="hidden" value="${tripDateInfo.defaultStartDate }" id="beginTime"/>
						            <input  type="hidden" value="${tripDateInfo.defaultEndDate }" id="endTime"/>
					             --%>
						 
									<div class="col-md-4">
										<div class="form-group">
											<label>下单时间：</label>
											<div class="input-group input-large date-picker input-daterange datepicker-height" data-date-format="yyyy-mm-dd" style="margin:8px auto auto auto">
			                                    <input type="text" class="form-control datepicker-height" name="beginTime"    id="beginDateTime" placeholder="开始时间" value="${tripDateInfo.defaultStartDate }">
			                                    <span class="input-group-addon">to</span>
			                                    <input type="text" class="form-control datepicker-height" name="endTime" id="endDateTime"   placeholder="结束时间" value="${tripDateInfo.defaultEndDate }">
			                                 </div>
										</div>
									</div>
								</div>
							</div>	
									
									<div class="form-actions fluid">
                                        <div class="col-md-offset-5 col-md-7">
												<button type="button" class="btn btn-info"  onclick="doNewSearch()">查询</button>
											     <button type="reset" class="btn btn-default">重置</button>
										</div>
								    </div>
							</form>
							<div class="col-md-2" style="margin-top:8px;">
						           <c:if test="${pageItem.totalItem!=null }">共${pageItem.totalItem }条记录</c:if>
							</div>
						</div>
						
				</div>


				<div class="portlet box light-grey">
                  <div class="portlet-body">
                     <table class="table table-striped table-bordered table-hover">
                     <c:choose>  
                  <c:when test="${tripOrders!=null && tripOrders.size()!=0  }">
                       
				   <c:forEach var="item" items="${tripOrders}">
				      <tr >
				        <td style="width: 124px;" >
				        <div class="img-container">
				        <a href="${ctx}/hotel/hotel/form?id=${item.hotelid}"><img src="${item.hotelQuery.hotelpic }" class="err-product"  width="110px;"/></a>
				        </div>
				        </td>
				         
				         <td style="width: 25%;">
					         <div style="margin-top: 0px;font-size: 18px;color:#434443;">${item.hotelQuery.hotelname}</div>
					         <div style="margin-top: 15px;font-size: 16px;color:#434443;" >
						         <span style="font-size: 14px;color:#BFBCB9;">订单编号</span>
						         ${item.orderid}
					         </div>
					         <div style="margin-top: 15px;font-size: 16px;color:#434443;" >
						         <span style="font-size: 14px;color:#BFBCB9;">下单时间</span>
						         ${item.createTime }
					         </div>
					      
				          <td style="width: 13%;">
					         <div style="margin-top: 0px;font-size: 15px;color:#BFBCB9;text-align: center;">订房量&nbsp;(间/天)</div>
					         <div style="text-align: center;margin-top: 20px;">
						         <span style="font-size: 22px;color:#434443;">${item.orderRoomNum}</span><br>
						         
					         </div>
					      
				         </td> 
				        
				         </td>
				         <td style="width: 15%px;">
					         <div style="margin-top: 0px;font-size: 15px;color:#BFBCB9;text-align: center;">总价(元)</div>
					         <div style="text-align: center;margin-top: 20px;">
						         <span style="font-size: 22px;color:#434443;">${item.totalPrice }</span><br>
						         
					         </div>
					      
				         </td>
				         
				         <td style="width: 15%px;">
					         <div style="margin-top: 0px;font-size: 15px;color:#BFBCB9;text-align: center;">订单状态</div>
					         <div style="text-align: center;margin-top: 20px;">
						         <span style="font-size: 22px;color:#434443;">${item.statusName }</span><br>
						         
					         </div>
					      
				         </td>
				         <td style="width: 15%px;">
					         <div style="margin-top: 0px;font-size: 15px;color:#BFBCB9;text-align: center;">旅行社</div>
					         <div style="text-align: center;margin-top: 20px;">
						         <span style="font-size: 22px;color:#434443;">${item.otaName }</span><br>
						         
					         </div>
					      
				         </td>
				         
				           <td style="width: 20%;">
				           <shiro:hasAnyRoles name="cs,dept">
					            <c:if test="${item.status ==0 }">
	                           <div style="margin-top: 4px;">
	                            <button type="button" class="btn btn-success btn-sm" style="width: 90px;height: 26px;"  onclick="confirmorder('myModal','${item.orderid}')">确认订单</button>
	                            <br><br>
	                            <button type="button" class="btn btn-success btn-sm" style="width: 90px;height: 26px;"  onclick="refuseorder('myModal','${item.orderid}')">拒绝订单</button>
	                                </div>
	                            </c:if>
	                            
	                             <c:if test="${item.status == 10 }">
	                           <div style="margin-top: 4px;">
	                            <button type="button" class="btn btn-success btn-sm" style="width: 90px;height: 26px;color:#C0C0C0"  >确认中</button>
	                                </div>
	                            </c:if>
                            </shiro:hasAnyRoles>
	                            <c:if test="${item.status == 20 || item.status == 100 }">
	                           <div style="margin-top: 4px;">
	                            <button type="button" class="btn btn-success btn-sm" style="width: 90px;height: 26px;"  onclick="showorderdetail('myModal','${item.orderid}')">订单详情</button>
	                                </div>
	                            </c:if>
	                            <div style="margin-top: 4px;">
	                            	<button type="button" class="btn btn-success btn-sm" style="width: 90px;height: 26px;"  onclick="window.location.href='${ctx}/hotel/hotel/form?id=${item.hotelid}'">酒店详情</button>
	                            </div>
                            
                           </td>
                           
				      </tr>
				       </c:forEach>
				       </c:when>
                             <c:otherwise>
	                             <tr >
				                		<td colspan="7" style="text-align: center;">
				                			<div>
				                			<c:choose>  
				                               <c:when test="${tip!=null}">
				                                   ${tip }
				                               </c:when>
				                               <c:otherwise>
						                        	订单列表为空
				                               </c:otherwise>
				                             </c:choose>
				                            </div>
				                		</td>
	                              <tr>
                             </c:otherwise>
                          </c:choose>
                     </table>
                     <!-- 分页 -->
                     <%@include file="../layouts/page_list.jsp" %>  

                  </div>
                   
               </div>
  </div>
</div>
</div>
			<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true" >
		   <div class="modal-dialog" style="width:650px;">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="fac_hotelname">
		                             订单详情
		            </h4>
		         </div>
<!-- 		         <form id="fav_form" method="post"> -->
		         <div class="modal-body" id="fav_content">
		            
		         </div>
<!-- 		         </form> -->
<!-- 		         <div class="modal-footer"> -->
<!-- 		            <button type="button" class="btn btn-default"  -->
<!-- 		               data-dismiss="modal">关闭 -->
<!-- 		            </button> -->
<!-- 		            <button type="button" class="btn btn-primary"> -->
<!-- 		               提交更改 -->
<!-- 		            </button> -->
<!-- 		         </div> -->
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->	
		
		</div>
		
		
					<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModalupdateorder" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true" >
		   <div class="modal-dialog" style="width:550px;">
		      <div class="modal-content">
		       <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="fac_hotelname">
		                             修改联系人
		            </h4>
		         </div>
<!-- 		         <form id="fav_form" method="post"> -->
		         <div class="modal-body" id="fav_content" style="width: 500px;height: 320px;">
		            <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-8" style="margin-left:175px;">
								<div>
									<div class="input-group input-group-lg" >
									  		<i class="fa fa-user-plus fa-5x" style="color:#009EFF;"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					 <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-8" style="margin-left:75px;">
								<div>
									<div class="input-group input-group-lg">
									  <span class="input-group-addon" id="sizing-addon1">
									  		<i class="fa fa-user"></i>
									  </span>
									  <input type="text" id="contacts" class="form-control" placeholder="联系人" aria-describedby="sizing-addon1">
									</div>
								</div>
							</div>
						</div>
					</div>
					 <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-8" style="margin-left:75px;">
								<div>
								<input  type="hidden" id="orderidhidden" value="">
									<div class="input-group input-group-lg">
									  <span class="input-group-addon" id="sizing-addon1">
									  		<i class="fa fa-phone"></i>
									  </span>
									  <input type="text" class="form-control" id="contactPhone" placeholder="联系电话" aria-describedby="sizing-addon1">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					 <div class="panel-heading">
						<div class="row trip-input-addon">
							  <div class="col-md-3">
							 </div>             
							 <div class="col-md-6">
							     <button type="button" class="btn btn-success btn-block"   id="updatecontacts" >提&nbsp;&nbsp;&nbsp;&nbsp;交</button>
							 </div>             
							 <div class="col-md-4">
									</div>
					</div>
					
		         </div>
		         
		       
				 </div>
<!-- 		         </form> -->
<!-- 		         <div class="modal-footer"> -->
<!-- 		            <button type="button" class="btn btn-default"  -->
<!-- 		               data-dismiss="modal">关闭 -->
<!-- 		            </button> -->
<!-- 		            <button type="button" class="btn btn-primary"> -->
<!-- 		               提交更改 -->
<!-- 		            </button> -->
<!-- 		         </div> -->
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->	
		
		</div>
		
		
				
					<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModalcancalorder" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true" >
		   <div class="modal-dialog" style="width:550px;">
		      <div class="modal-content">
		       <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="fac_hotelname">
		                             取消订单
		            </h4>
		         </div>
<!-- 		         <form id="fav_form" method="post"> -->
		         <div class="modal-body" id="fav_content" style="width: 500px;height: 320px;">
		            <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-8" style="margin-left:175px;">
								<div>
									<div class="input-group input-group-lg" >
									  		<i class="fa fa-times-circle fa-5x" style="color:#FF4600;"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					 <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-8" style="margin-left:115px;">
								<div>
									<div class="input-group input-group-lg" data-dismiss="modal">
									  
									  		<h3>是否取消订单？</h3>
									  
									</div>
								</div>
							</div>
						</div>
					</div>
					 <div class="panel-heading">
						<div class="row trip-input-addon">
							<div class="col-md-3" style="margin-left:60px;">
								<div>
								<input  type="hidden" id="cancalorderhidden" value="">
									<div class="input-group input-group-lg">
									   <button type="button" class="btn btn-success btn-block  fa-2x "  data-dismiss="modal" id="nocancal" >暂不取消</button>
									</div>
								</div>
							</div>
							<div class="col-md-3" style="margin-left:50px;">
								<div>
								<input  type="hidden" id="orderidhidden" value="">
									<div class="input-group input-group-lg">
									   <button type="button" class="btn btn-success btn-block fa-2x" style="background-color:#FF4600;border-color:#FF4600;" id="cancalorder" >取消订单</button>
									</div>
								</div>
							</div>
						</div>
					</div>
			
		         
		       
				 </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->	
		
		</div>
	</shiro:hasPermission>
	  <shiro:lacksPermission name="gds:order:view">您没有操作本页面的权限.</shiro:lacksPermission>
	</body>
	
    <%@include file="../layouts/importJs.jsp" %>
   	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="/static/gds/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
   	<script src="/static/gds/assets/scripts/distributor/order.js"></script> 

	<script>

	$(function() {
		if (jQuery().datepicker) {
			$('.date-picker').datepicker({
				autoclose : true,
				language:"zh-CN",
			});
		}
	});
	</script>

</html>
