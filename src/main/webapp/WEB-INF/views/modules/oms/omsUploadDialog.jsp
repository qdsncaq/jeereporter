<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" /> 
	<script src="${ctxStatic}/fileinput/fileinput.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/fileinput/fileinput_locale_zh.js" type="text/javascript"></script>	
	<link href="${ctxStatic}/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />		
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>	
		
</head>
<body style="padding:20px;">
	<form id = "fileUploadForm" action="${ctx}/oms/omsOrder/impLogistic" enctype="multipart/form-data" method="post">
		<input id="xlsFile" type="file" name="xlsFile" accept="*/*" /></br>
		<input type="submit" value="确定" class="btn  btn-primary" />
		<script>
/* 			$("#xlsFile").fileinput({
				previewFileType : "image",
				browseClass : "btn btn-success",
				browseLabel : "上传文件",
				browseIcon : '<i class="glyphicon glyphicon-picture"></i>',
				removeClass : "btn btn-danger",
				removeLabel : "Delete",
				removeIcon : '<i class="glyphicon glyphicon-trash"></i>',
				uploadClass : "btn btn-info",
				uploadLabel : "Upload",
				uploadIcon : '<i class="glyphicon glyphicon-upload"></i>',
			}); */
		</script>
	</form>
</body>	
</html>	