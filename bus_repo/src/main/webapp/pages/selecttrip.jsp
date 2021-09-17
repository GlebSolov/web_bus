<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>заказ билета</title>
</head>
<body>
<form action="/userhome" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="обратно">
</form>
<h3>подходящие рейсы</h3>
<table style="text-align: center;
              vertical-align: middle;
              width: 100%;
              border-collapse: collapse;">
    <tr style="border: solid black 2px;">
        <th style="border: solid black 2px;">id</th>
        <th style="border: solid black 2px;">маршрут</th>
        <th style="border: solid black 2px;">дата</th>
        <th style="border: solid black 2px;">мест</th>
        <th style="border: solid black 2px;">цена</th>
    </tr>
    <c:forEach var="trip" items="${trips}">
        <tr style="border: solid black 1px;">
            <td style="border: solid black 1px;">${trip.trip_id}</td>
            <td style="border: solid black 1px;">${trip.route_id.route_name}</td>
            <td style="border: solid black 1px;">${trip.date_time}</td>
            <td style="border: solid black 1px;">${trip.seats}</td>
            <td style="border: solid black 1px;">${price}</td>
        </tr>
    </c:forEach>
</table>
<form action="/ordertrip" method="post">
    <label for="user_id">id пользователя</label>
    <input readonly required value="${user_id}" type="number" name="user_id" id="user_id">

    <label for="from_id">id откуда</label>
    <input readonly required value="${from.station_id}" type="number" name="from_id" id="from_id">

    <label for="to_id">id куда</label>
    <input readonly required value="${to.station_id}" type="number" name="to_id" id="to_id">

    <label for="trip_id">выберите id рейса</label>
    <select required name="trip_id" id="trip_id">
        <option></option>
        <c:forEach var="trip" items="${trips}">
            <option value="${trip.trip_id}">${trip.trip_id}</option>
        </c:forEach>
    </select>
    <input type="submit" value="заказать билет" id="finish_order">
</form>

</body>
</html>
