<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房型价格</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
	
	</script>
</head>
<body>
    <table id="roomtype_prices_table" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th width="15%">门市价</th>
            <th width="15%">周末价</th>
            <th width="15%">特殊价</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    ${rackRateList[0].rackRatePrice}
                </td>
                <td>
                    <c:forEach items="${weekRateList}" var="weekPrices">
                        ${fns:getDictLabel(weekPrices.week, 'week', weekPrices.week)}:
                        ${weekPrices.weekPrice}<br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${dailyRateList}" var="dailyPrices">
                        ${dailyPrices.dayStr}:
                        ${dailyPrices.dayPrice}
                        <br>
                    </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
