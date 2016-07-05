<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="form-group">
	<div class="text-left col-md-12 wrapper">
		<input type="hidden" id="domain" value="7xp8rx.com1.z0.glb.clouddn.com"> 
		<input type="hidden" id="uptoken" value="${uptoken}"> 
		<input type="hidden" id="logopath" value="">
	</div>
	<div >
		<div>
			<div id="container" style="height:100px">
				<label class="control-label col-md-3">分销商logo<span class="required">*</span></label> 
				<a class="btn btn-default btn-md " id="pickfiles" style="left: 15px;">
					<i class="glyphicon glyphicon-plus"></i> <sapn>上传logo</sapn>
				</a>

				<div id="html5_197igj75aami1c13vhi9rf1n9v3_container"
					class="moxie-shim moxie-shim-html5"
					style="position: absolute; top: 0px; left: 0px; width: 167px; height: 45px; overflow: hidden; z-index: 0;">
					<input id="html5_197igj75aami1c13vhi9rf1n9v3" type="file"
						style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;"
						multiple="" accept="">
				</div>
				<img alt="" src="" id="img_logo" style="position: absolute; top: 0px; left: 295px; width: 100px; height: 100px; z-index: 0;">
			</div>
		</div>

		<div style="display: none" id="success" class="col-md-12">
			<div class="alert-success">队列全部文件处理完毕</div>
		</div>
		<div class="col-md-12 " style="display: none">
			<table class="table table-striped table-hover text-left"
				style="margin-top: 40px; display: none">
				<thead>
					<tr>
						<th class="col-md-4">文件名</th>
						<th class="col-md-2">大小</th>
						<th class="col-md-6">详情</th>
					</tr>
				</thead>
				<tbody id="fsUploadProgress">
				</tbody>
			</table>
		</div>
	</div>
</div>
