<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拜访记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
	<style>
		.controls{
			margin-left:1px !important;
		}
		.div_controls_span{
			margin:5px 15px 5px 5px;
		}
		.div_controls_span_lable{
			width: 80px;
			height: 30px;
			line-height: 30px;
			text-align: right;
		}

	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/crm/visit/rfCrmVisitRecord/list?queryVisitType=${param.queryVisitType}">拜访记录列表</a></li>
		<li class="active"><a>拜访记录查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rfCrmVisitRecord"  method="post" class="form-horizontal">
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">酒店ID：</span>
					<form:input path="visitHotelId" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">拜访日期：</span>
					<input type="text" value="<fmt:formatDate value="${rfCrmVisitRecord.visitTime}" pattern="yyyy-MM-dd"/>" class="input-large" disabled="disabled">
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">销售：</span>
					<form:input path="visitUserName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">省：</span>
					<form:input path="provName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">市：</span>
					<form:input path="cityName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">区/县：</span>
					<form:input path="disName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">酒店名称：</span>
					<form:input path="visitHotelName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">拜访方式：</span>
					<form:input path="visitWayName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">创建时间：</span>
					<input type="text" value="<fmt:formatDate value="${rfCrmVisitRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="input-large" disabled="disabled">
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">联系人：</span>
					<form:input path="hotelContacts" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">拜访类型：</span>
					<form:input path="visitTypeName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
			<c:if test="${'switch' == param.queryVisitType }">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">是否签约：</span>
					<form:input path="isSignName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
			</c:if>
			</div>
		</div>
		<c:if test="${'wash' == param.queryVisitType }">
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">签约意向：</span>
					<form:input path="washIntentionName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">是否签约：</span>
					<form:input path="washSignName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">洗涤签约日期：</span>
					<input type="text" class="input-large" value="<fmt:formatDate value="${rfCrmVisitRecord.washSignDate}" pattern="yyyy-MM-dd"/>" disabled />
				</span>
			</div>
		</div>
		</c:if>
		<c:if test="${'switch' == param.queryVisitType }">
			<div class="control-group">
				<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">签约失败原因：</span>
					<form:textarea path="signFailure" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span" style="float: left;">
					<span class="div_controls_span_lable">拜访图片：</span>
					<table >
						<tr>
							<td style="width: 1080px;">
								<c:forEach var="pic" items="${rfCrmVisitRecord.picArray}"  >
									<a href="<c:out value="${pic}" />" target="_blank"><img src="<c:out value="${pic}?imageView2/1/w/150/h/150" />" ></a>
								</c:forEach>
							</td>
						</tr>
					</table>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">拜访反馈：</span>
					<form:textarea path="visitDesc" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
			</div>
		</div>
	<c:if test="${'marketing' == param.queryVisitType }">
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">采购意向：</span>
					<form:input path="purchaseIntentionName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">样品发放：</span>
					<form:input path="sampleGrantName" htmlEscape="false" maxlength="20" class="input-large" disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">牙具：</span>
					<form:textarea path="yaju" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">沐浴露：</span>
					<form:textarea path="muyulu" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">香皂：</span>
					<form:textarea path="xiangzao" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">洗发液：</span>
					<form:textarea path="xifaye" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">梳子：</span>
					<form:textarea path="shuzi" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">水：</span>
					<form:textarea path="shui" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<span class="div_controls_span">
					<span class="div_controls_span_lable">纸：</span>
					<form:textarea path="zhi" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
				<span class="div_controls_span">
					<span class="div_controls_span_lable">垃圾袋：</span>
					<form:textarea path="lajidai" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
				</span>
			</div>
		</div>
	</c:if>

			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</form:form>
</body>
</html>