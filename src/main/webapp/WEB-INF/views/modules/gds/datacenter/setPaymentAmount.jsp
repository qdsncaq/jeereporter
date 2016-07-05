<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置赔付金额</title>
	<meta name="decorator" content="blank"/>
	<%@include file="../layouts/importJs.jsp" %>
    <style type="text/css">
        #jbox-content {
            overflow-y: hidden;
        }
        label#remark_label, textarea#desc{
            display: inline-block;
            width: 100%;
        }
        textarea#desc{
            padding: 0;
        }
    </style>
<script type="text/javascript">

	$(document).ready(function() {
		$("textarea#note").keyup(function() {
			var area = $(this);
			var max = parseInt(area.attr("maxlength"), 10); //获取maxlength的值 
			if (max > 0) {
				if (area.val().length >= max) { //textarea的文本长度大于maxlength 
					//area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
					layer.tips('备注不能超过255', '#desc', {
						tips : 1
					});
				}
			}
		});
	});
	function checkform() {
		var note = $("#note").val().trim();
		if (note.length == 0) {
			layer.tips('请输入备注信息', '#note', {
				tips : 1
			});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
    <form:form id="inputForm" modelAttribute="datacenterOrders"  class="breadcrumb form-search">
    <ul class="ul-form">
        <li style="height: auto; float: inherit; overflow: hidden;">
            <table class="table">
                <tr>
                    <td style="width: 20%; text-align: right;">
                        <label id="remark_label">赔付金额：</label>
                    </td>
                    <td style="width: 80%;">
	                    <input type="text" id="payment_amount" maxlength="18" class="input-xlarge " />
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%; text-align: right;">
                        <label id="remark_label">备注：</label>
                    </td>
                    <td style="width: 80%; ">
                        <textarea id="note" rows="5"  style="width: 94%; " maxlength="255" ></textarea>                        
                    </td>
                </tr>
            </table>
        </li>
    </ul>
    </form:form>
</body>
</html>
