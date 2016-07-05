<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%

String urlPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+urlPath+"/"; 
%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	<%@include file="../layouts/importCss.jsp" %>
    
	</head>
<body>
<div id="warning" style="display:none"> 
<div class="alert alert-warning" >
<!--    <a href="#" class="close" data-dismiss="alert"   href="#" aria-hidden="true"> -->
<!--       &times; -->
<!--    </a> -->
   <strong>警告！</strong><span id="alertInfo"></span>
</div>
</div>





<div class="container-fluid" style="width:610px;">
	    
	<div class="portlet box light-grey">
                  <div class="portlet-body">
			  		 <table class="table table-striped table-bordered table-hover">
			  		   <thead>
                           <tr>
								<th>房间号</th>
								<th>房型</th>
								<th>金额</th>
								<!-- <th>入住人</th> -->
								<th>入住时间</th>
								<th>离店时间</th>
                           </tr>
                           <tbody>
                             <c:choose>  
                             <c:when test="${orderDetails.size()!=0 && orderDetails!=null}">
                        
                             <c:forEach var="item" items="${orderDetails}">
                        
                        <tr valign="top" style="height:35px; border-bottom: 1px solid #dfdfdf;">
                            <td> ${item.roomno }</td>
                            <td>${item.roomtypename } </td>
                            <td>${item.totalprice }</td>
                           <%--  <td>${item.contacts } </td> --%>
                            <td>
                             <fmt:formatDate value="${item.checkintime }" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                            <td>
                             <fmt:formatDate value="${item.checkouttime }" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        
                        </c:forEach>
                        
                         </c:when>
                             <c:otherwise>
                             <tr>
                		 <td colspan="5">
                			<div style="text-align:center">
                			订单详情为空
                            </div>
                		</td>
                    </tr>
                             </c:otherwise>
                          </c:choose>
                           </tbody>
                        </thead>
			  		 </table>
			  </div>
			  </div>
			  <div class="portlet-body">
			  <div class="row">  
			
				 <div class="col-md-4">
				 </div>             
				 <div class="col-md-4">
				     <button type="button" class="btn btn-success btn-block" data-dismiss="modal"  >关闭</button>
				 </div>             
				 <div class="col-md-4">
				 </div>             
			</div> 
			  </div>
			<!-- 				  
			<div class="row">  
			
				 <div class="col-md-4">
				 </div>             
				 <div class="col-md-4">
				     <button type="button" class="btn btn-success btn-block" id="submitOrder" >提交订单</button>
				 </div>             
				 <div class="col-md-4">
				 </div>             
			</div>   -->				  
			  
			  
	
</div>



<%-- 		<script type="text/javascript" src="<%=urlPath %>/bower_components/jquery/dist/jquery.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%=urlPath %>/bower_components/underscore/underscore-min.js"></script> --%>
		
<%-- 		<script type="text/javascript" src="<%=urlPath %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script> --%>

<%--         <script language="JavaScript" type="text/javascript" src="<%=urlPath %>/js/page/trip/submitorder.js"></script> --%>
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
    
</body>
</html>