<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务管理</title>
	<meta name="decorator" content="default"/>
	<script> 
      $(function () { 
//    	  $('#hotelTab a:first').tab('show');//初始化显示哪个tab

		  <%--<c:if test="${param.tabVal == 'tab1'}">--%>
		  	<%--$('#tab1').tab('show');//初始化显示哪个tab--%>
		  <%--</c:if>--%>
		  <%--<c:if test="${param.tabVal == 'tab2'}">--%>
		  	<%--$('#tab2').tab('show');//初始化显示哪个tab--%>
		  <%--</c:if>--%>
		  <%--<c:if test="${param.tabVal == 'tab3'}">--%>
		  	<%--$('#tab3').tab('show');//初始化显示哪个tab--%>
		  <%--</c:if>--%>

          $('#roomTypeTab a:first').tab('show');//初始化显示哪个tab 
        
          $('#hotelTab a').click(function (e) { 
            e.preventDefault();//阻止a链接的跳转行为
            $(this).tab('show');//显示当前选中的链接及关联的content 
          });
          
          
          $('#roomTypeTab a').click(function (e) { 
            e.preventDefault();//阻止a链接的跳转行为 
            $(this).tab('show');//显示当前选中的链接及关联的content 
          });

          var li = $('#roomTypeTab a:first').attr("attr");
          $("#" + li).addClass("active");

		  <%--$('#tab4').click(function (e){--%>
			  <%--location.href="${ctx}/crm/hotel/eHotel/auditLogList?hotelId=${eHotel.id }&hotelName=${eHotel.hotelname }";--%>
		  <%--});--%>
      });
      
      /** 审核酒店 */
      function checkHotel(hotelId){
    	  
      }
      
      /** 审核酒店 */
      function rejectHotel(hotelId){
    	  
      }
    </script>
</head>
<body>
	<ul class="nav nav-tabs" id="hotelTab"> 
      <li class="active"><a id="tab1" href="#home">酒店基本信息</a></li>
      <li><a id="tab2" href="#pic">酒店图片</a></li>
      <li><a id="tab3" href="#roomType">房型信息</a></li>
	  <li><a id="tab4" href="#auditLog">操作日志</a></li>
    </ul> 
    <form:form id="inputForm" modelAttribute="eHotel" method="post" class="form-horizontal">
	      <div class="tab-content"> 
	      <!-- 	      酒店基本信息                  -->
	      <div class="tab-pane active" id="home">
	      	<div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong> 酒店名称：</strong></span>${eHotel.hotelname }
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>前台电话：</strong></span>${eHotel.qtphone }
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>老板手机号：</strong></span>${eHotel.bossPhone }
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>酒店地址：</strong></span>
                    ${eHotel.provName }&nbsp;&nbsp; ${eHotel.cityName }&nbsp;&nbsp;${eHotel.disName }&nbsp;&nbsp;${eHotel.detailaddr }
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>开业时间：</strong></span>
                    <fmt:formatDate value="${eHotel.opentime}" pattern="yyyy-MM-dd" />
                </span>
                 <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>装修时间：</strong></span>
                    <fmt:formatDate value="${eHotel.repairtime }" pattern="yyyy-MM-dd" />
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>最晚保留时间：</strong></span>
                     ${fn:substring(eHotel.retentiontime,0,2) }:${fn:substring(eHotel.retentiontime,2,4) }
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable"><strong>默认离店时间：</strong></span>
                    ${fn:substring(eHotel.defaultleavetime,0,2) }:${fn:substring(eHotel.defaultleavetime,2,4) }
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                   <span class="div_controls_span_lable">设施服务：</span>
	                <c:forEach items="${eHotel.basicService}" var="tag"> 
		                <c:choose>  
						   <c:when test="${tag.checkbox}">
			               	 <input type="checkbox" disabled="disabled" maxlength="20" checked/> ${tag.name}
						   </c:when>  
						   <c:otherwise>
			               	 <input type="checkbox" disabled="disabled" maxlength="20" /> ${tag.name}
						   </c:otherwise>  
						</c:choose>  
					</c:forEach>
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                   <span class="div_controls_span_lable">类型特色：</span>
	                <c:forEach items="${eHotel.typeSpecial}" var="tag"> 
		                <c:choose>  
						   <c:when test="${tag.checkbox }">
			               	 <input type="checkbox" disabled="disabled" maxlength="20" checked/> ${tag.name}
						   </c:when>  
						   <c:otherwise>
			               	 <input type="checkbox" disabled="disabled" maxlength="20" /> ${tag.name}
						   </c:otherwise>  
						</c:choose>  
					</c:forEach>
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                   <span class="div_controls_span_lable">商圈位置：</span>
	                <c:forEach items="${eHotel.businessArea}" var="tag"> 
		                <c:choose>  
						   <c:when test="${tag.checkbox }">
			               	 <input type="checkbox" disabled="disabled" maxlength="20" checked/> ${tag.name}
						   </c:when>  
						   <c:otherwise>
			               	 <input type="checkbox" disabled="disabled" maxlength="20" /> ${tag.name}
						   </c:otherwise>  
						</c:choose>  
					</c:forEach>
                </span>
	        </div>
	        <div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">酒店介绍：</span>
                    <form:textarea path="introduction" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
                </span>
	        </div>
	      </div> 
	      
	      <!-- 	      酒店图片                  -->
	      <div class="tab-pane" id="pic">
	      	<div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">门头照片：</span>
                    <c:if test="${!empty  eHotel.facadePic}"> 
                    	<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${eHotel.facadePic}?imageMogr/auto-orient" width="276" height="368">
				 	</c:if>
                   	<c:if test="${empty  eHotel.facadePic}"> <strong>无图片 </strong> </c:if>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">大堂：</span>
                    <c:if test="${!empty  eHotel.lobby}">
						<c:forEach items="${eHotel.lobby}" var="lobby" varStatus="status">
							<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${lobby}?imageMogr/auto-orient" width="276" height="368">
						</c:forEach>
				 	</c:if>
                   	<c:if test="${empty  eHotel.lobby}"> <strong>无图片 </strong> </c:if>
                </span>
	        </div>
	        <%--<div class="control-group">--%>
            	<%--<c:forEach items="${eHotel.roomTypeList}" var="roomType" varStatus="status">--%>
                	<%--<span class="div_controls_span">--%>
                   		<%--<span class="div_controls_span_lable">${roomType.name}：</span>--%>
                   		<%--<c:if test="${!empty  roomType.pic}">--%>
	                    	<%--<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${roomType.pic}?imageMogr/auto-orient" width="276" height="368">--%>
					 	<%--</c:if>--%>
                    	<%--<c:if test="${empty  roomType.pic}"> <strong>无图片 </strong> </c:if>--%>
                	<%--</span>--%>
            	<%--</c:forEach>--%>
	        <%--</div>--%>


		  <div class="control-group">
			<span class="div_controls_span">
				<span class="div_controls_span_lable">主力房型：</span>
				<c:if test="${!empty  eHotel.mainHousing}">
					<c:forEach items="${eHotel.mainHousing}" var="mainHousing" varStatus="status">
						<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${mainHousing}?imageMogr/auto-orient" width="276" height="368">
					</c:forEach>
				</c:if>
				<c:if test="${empty  eHotel.mainHousing}"> <strong>无图片 </strong> </c:if>
			</span>
		  </div>
	        
	        <div class="control-group">
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">营业执照：</span>
                    <c:if test="${!empty  eHotel.businesslicensefront}"> 
                    	<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${eHotel.businesslicensefront}?imageMogr/auto-orient" width="276" height="368">
					 </c:if>
                    <c:if test="${empty  eHotel.businesslicensefront}"> <strong>无图片 </strong> </c:if>
                </span>
                <span class="div_controls_span">
                    <span class="div_controls_span_lable">身份证：</span>
                     <c:if test="${!empty eHotel.idcardfront}"> 
                    	<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${eHotel.idcardfront}?imageMogr/auto-orient" width="276" height="368">
					 </c:if>
                     <c:if test="${!empty eHotel.idcardback}"> 
                    	<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${eHotel.idcardback}?imageMogr/auto-orient" width="276" height="368">
					 </c:if>
					 
					 <c:if test="${empty eHotel.idcardfront && empty eHotel.idcardback}"><strong>无图片 </strong> </c:if>
                </span>
	        </div>
	      </div> 
	      
		  <!-- 	      房型信息                  -->
	      <div class="tab-pane" id="roomType">
	      	 <div class="span3">
				<ul class="nav nav-tabs nav-stacked" id = "roomTypeTab">
					<c:forEach items="${eHotel.roomTypeList}" var="roomType" varStatus="status">
                   	 	<c:choose>  
						   <c:when test="${status.first}">
	                   			<li class="active"><a href="#${roomType.roomtypeid}" attr="${roomType.roomtypeid}">${roomType.name}</a></li>
						   </c:when>  
						   <c:otherwise>
	                   			<li><a href="#${roomType.roomtypeid}">${roomType.name}</a></li>
						   </c:otherwise>  
						</c:choose> 
	            	</c:forEach>
				</ul>
			  </div>
			  <div class="span9 tab-content">
				  <c:forEach items="${eHotel.roomTypeList}" var="roomType" varStatus="status">
					  	<div class="tab-pane" id="${roomType.roomtypeid}">
				  			<div class="control-group">
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">房间面积：</span>${roomType.maxarea}
				                </span>
					        </div>
					        
					        <div class="control-group">
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">床型尺寸：</span>
				                    <c:if test="${roomType.bedtype == 1}"> 单人床 </c:if>
				                    <c:if test="${roomType.bedtype == 2}"> 双人床 </c:if>
				                    <c:if test="${roomType.bedtype > 2}"> 其他 </c:if>
				                </span>
				                
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">床型尺寸：</span>${roomType.bedsize}
				                </span>
					        </div>
					        
					        <div class="control-group">
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">主展图：</span>
				                     <c:if test="${!empty  roomType.def}">
					                     <img onclick="changePicSize(this)" style="cursor:zoom-in" src="${roomType.def}?imageMogr/auto-orient" width="276" height="368">
				                     </c:if>
				                     <c:if test="${empty  roomType.def}"> <strong>无图片 </strong> </c:if>
				                </span>
					        </div>
					        
					        <div class="control-group">
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">卫生间：</span>
				                     <c:if test="${!empty  roomType.toilet}">
					                    <img onclick="changePicSize(this)" style="cursor:zoom-in" src="${roomType.toilet}?imageMogr/auto-orient" width="276" height="368">
				                     </c:if>
				                     <c:if test="${empty  roomType.toilet}"> <strong>无图片 </strong> </c:if>
				                </span>
					        </div>
					        
					        <div class="control-group">
				                <span class="div_controls_span">
				                    <span class="div_controls_span_lable">床：</span>
				                     <c:if test="${!empty  roomType.bed}">
				                     	<img onclick="changePicSize(this)" style="cursor:zoom-in" src="${roomType.bed}?imageMogr/auto-orient" width="276" height="368">
				                     </c:if>
				                     <c:if test="${empty  roomType.bed}"> <strong>无图片 </strong> </c:if>
				                </span>
					        </div>
				  		</div>
	              </c:forEach>
              </div>
	      </div>


		<%--审核日志--%>
		  <div class="tab-pane" id="auditLog">
			  <table id="contentTable" class="table table-striped table-bordered table-condensed">
				  <thead>
				  <tr>
					  <th>操作类型</th>
					  <th>操作时间</th>
					  <th>酒店ID</th>
					  <th>pms</th>
					  <th>操作人</th>
					  <th>备注</th>
					  <th>操作内容</th>
				  </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${page.list}" var="auditLog">
					  <tr>
						  <td>
								  ${auditLog.typeName}
						  </td>
						  <td>
							  <fmt:formatDate value="${auditLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						  </td>
						  <td>
								  ${auditLog.hotelId}
						  </td>
						  <td>
								  ${auditLog.pms}
						  </td>
						  <td>
								  ${auditLog.operateName}
						  </td>
						  <td>
								  ${auditLog.remarks}
						  </td>
						  <td>
							  <c:forEach items="${auditLog.differents}" var="diff">
								  ${diff} <br>
							  </c:forEach>
						  </td>
					  </tr>
				  </c:forEach>
				  </tbody>
			  </table>
		  </div>


		  </div>
	</form:form>
	<div class="form-actions">
		<c:if test="${eHotel.checkState == 1}">
			<a class="btn btn-primary" href="${ctx}/crm/hotel/eHotel/checkNormalHotel?hotelId=${eHotel.id}" onclick="return confirmx('确认要审核该酒店吗？', this.href)">审核</a>
	        <a class="btn btn-primary" href="${ctx}/crm/hotel/eHotel/rejectNormalHotel?hotelId=${eHotel.id}&reason=" onclick="return promptx('驳回申请', '驳回原因', this.href)">驳回</a>
        </c:if>
		<%--<a class="btn" href="${ctx}/crm/hotel/eHotel/returnCheckList">返回</a>--%>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</body>
</html>