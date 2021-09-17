<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>заказы</title>
</head>
<body>
<form action="/adminhome" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="обратно">
</form>
<br>
<table style="text-align: center;
              vertical-align: middle;
              width: 100%;
              border-collapse: collapse;">
    <tr style="border: solid black 2px;">
        <th style="border: solid black 2px;">id рейса</th>
        <th style="border: solid black 2px;">откуда</th>
        <th style="border: solid black 2px;">куда</th>
        <th style="border: solid black 2px;">номер рейса</th>
    </tr>
    <c:forEach var="order" items="${usersOrders}">
        <tr style="border: solid black 1px;">
            <td style="border: solid black 1px;">${order.trip.trip_id}</td>
            <td style="border: solid black 1px;">${order.from_station_id.station_name}</td>
            <td style="border: solid black 1px;">${order.to_station_id.station_name}</td>
            <td style="border: solid black 1px;">${order.trip.date_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
