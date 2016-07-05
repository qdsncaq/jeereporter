<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规则配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function addRule(){
			
		}
	</script>
</head>
<body>
	<div>
	   <form id="searchForm" action="" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<label class="control-label no-padding-right" >业务类型:</label>
				<select style="margin-left: 10px; width: 180px;" name="biztype" >
		 			<option value="1" <c:if test="${params.biztype == '1' }">selected = "selected"</c:if>>OTA分销</option>
					<option value="2" <c:if test="${params.biztype == '2' }">selected = "selected"</c:if>>旅行社分销</option>
					<option value="3" <c:if test="${params.biztype == '3' }">selected = "selected"</c:if>>洗涤</option>
					<option value="4" <c:if test="${params.biztype == '4' }">selected = "selected"</c:if>>采购</option>
		        </select>
		        <label class="control-label no-padding-right" style="margin-left: 40px;" >账户角色:</label>
		        <select style="margin-left: 10px; width: 180px;" name="accountrole" >
		 			<option value="1" <c:if test="${params.accountrole == '1' }">selected = "selected"</c:if>>酒店老板</option>
					<option value="2" <c:if test="${params.accountrole == '2' }">selected = "selected"</c:if>>销售</option>
					<option value="3" <c:if test="${params.accountrole == '3' }">selected = "selected"</c:if>>旅行社</option>
					<option value="4" <c:if test="${params.accountrole == '4' }">selected = "selected"</c:if>>司机</option>
					<option value="5" <c:if test="${params.accountrole == '5' }">selected = "selected"</c:if>>洗涤厂</option>
		        </select>
		        <label class="control-label no-padding-right" style="margin-left: 40px;" >规则类型:</label>
		        <select style="margin-left: 10px; width: 180px;" name="ruletype" >
					<option value="3" <c:if test="${params.ruletype == '3' }">selected = "selected"</c:if>>OTA销售提成</option>
					<option value="4" <c:if test="${params.ruletype == '4' }">selected = "selected"</c:if>>旅行社销售提成</option>
					<option value="5" <c:if test="${params.ruletype == '5' }">selected = "selected"</c:if>>洗涤销售提成</option>
					<option value="5" <c:if test="${params.ruletype == '5' }">selected = "selected"</c:if>>采购销售提成</option>
		 			<option value="1" <c:if test="${params.ruletype == '1' }">selected = "selected"</c:if>>旅行社后付费折扣</option>
					<option value="2" <c:if test="${params.ruletype == '2' }">selected = "selected"</c:if>>旅行社预付费折扣</option>
		        </select>
			</ul>
		</form>
		
		<table id="contentTable" style="width: 900px; height: 190px;" class=" table-striped table-bordered table-condensed">
		    <tr>
		        <th>编号</th>
		        <th>范围</th>
		        <th>折扣比率</th>
		        <th>描述</th>
		        <th>操作</th>
		    </tr>
		    <tr>
		    	<th>1</th>
		    	<th>0-9999</th>
		    	<th>0.9</th>
		    	<th>旅行社后付费账户在0-9999之间打9折</th>
		    	<th>
			    	<a href="">编辑 </a>
			    	| <a href="javascript:;"onclick="addRule()">添加</a>
		    	</th>
		    </tr>
		    <tr>
		    	<th><input value="1" disabled="disabled"></input></th>
		    	<th>0-9999</th>
		    	<th>0.9</th>
		    	<th>旅行社后付费账户在0-9999之间打9折</th>
		    	<th>
			    	<a href="">编辑 </a>
			    	| <a href="javascript:;"onclick="addRule()">添加</a>
		    	</th>
		    </tr>
		    <tr>
		    	<th>2</th>
		    	<th>9999-20000</th>
		    	<th>0.8</th>
		    	<th>旅行社后付费账户在9999-20000之间打8折</th>
		    	<th>编辑 | 添加</th>
		    </tr>
		    <tr>
		    	<th>3</th>
		    	<th>20000-50000</th>
		    	<th>0.7</th>
		    	<th>旅行社后付费账户在20000-50000之间打7折</th>
		    	<th>编辑 | 添加</th>
		    </tr>
		    <tr>
		    	<th>4</th>
		    	<th>50000</th>
		    	<th>0.6</th>
		    	<th>旅行社后付费账户在50000以上打6折</th>
		    	<th>编辑 | 添加</th>
		    </tr>
	    </table>
		
	</div>
</body>
</html>