<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选择销售</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
	
		var areasTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
            areasTree = $.fn.zTree.init($("#areasTree"), setting, ${areas});
            selectedTree = $.fn.zTree.init($("#selectedTree"), settings, ${selectUsers});
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true},keep:{leaf:true}},
				callback: {onClick: treeOnClick},
                async:{enable:true,autoParam:["id", "type"],type:"post",url:"${ctx}/crm/visit/rfCrmVisitRecord/findAreasByParentId"}};

        var settings = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
            data: {simpleData: {enable: true}},
            callback: {onClick: treeOnClick}};

		var selectedNodes =[];
		
		var pre_ids = "";
		var selectUsers = "${selectUsersCode}" == "" ? [] : "${selectUsersCode}".split(",");
        var selectUsersName = "${selectUsersName}" == "" ? [] : "${selectUsersName}".split(",");

		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			if("areasTree"==treeId){
                var url = "${ctx}/crm/user/rfCrmUserArea/findUserByArea?";
                if ("1" == treeNode.type) {
                    url = url + "provCode=" + treeNode.code;
                } else if ("2" == treeNode.type) {
                    url = url + "cityCode=" + treeNode.code;
                } else {
                    url = url + "disCode=" + treeNode.code;
                }

				$.get(url, function(userNodes){
					$.fn.zTree.init($("#userTree"), settings, userNodes);
				});
			}
			if("userTree"==treeId){
				if($.inArray(String(treeNode.code), selectUsers)<0){
					selectedTree.addNodes(null, treeNode);
                    selectUsers.push(String(treeNode.code));
                    selectUsersName.push(String(treeNode.name));
				}
			};
			if("selectedTree"==treeId){
				if($.inArray(String(treeNode.code), pre_ids)<0){
					selectedTree.removeNode(treeNode);
                    selectUsers.splice($.inArray(String(treeNode.code), selectUsers), 1);
                    selectUsersName.splice($.inArray(String(treeNode.name), selectUsersName), 1);
				}else{
					top.$.jBox.tip("原有成员不能清除！", 'info');
				}
			}
		};
		function clearAssign(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
                    selectUsers = [];
					$.fn.zTree.init($("#selectedTree"), setting, []);
			    	top.$.jBox.tip("已选人员清除成功！", 'info');
			    } else if (v == 'cancel'){
			    	// 取消
			    	top.$.jBox.tip("取消清除操作！", 'info');
			    }
			    return true;
			};
			tips="确定要完全清除已选人员？";
			top.$.jBox.confirm(tips, "清除确认", submit);
		};
	</script>
</head>
<body>
	<div id="assignRole" class="row-fluid span12">
		<div class="span4" style="border-right: 1px solid #A8A8A8;">
			<p>所在区域：</p>
			<div id="areasTree" class="ztree"></div>
		</div>
		<div class="span3">
			<p>待选人员：</p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="span3" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p>已选人员：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
