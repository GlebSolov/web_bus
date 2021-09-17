
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>обновление рейса</title>
</head>
<body>
<form action="/adminhome" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="администратор">
</form>
<form action="/updatetrip" method="post">
    <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">

    <label for="id">id</label>
    <input required name="trip_id" id="id" type="text" value="${oldtrip.trip_id}">

    <label for="routeselect">маршрут. старое значение: ${oldtrip.route_id.route_name}</label>
    <select required id="routeselect" name="routeselect">
        <option></option>
        <c:forEach var="route" items="${allRoutes}">
            <option value="${route.route_id}">${route.route_name}</option>
        </c:forEach>
    </select>
    <label for="datetime">дата и время. старое значение: ${oldtrip.date_time}</label>
    <input required type="datetime-local" id="datetime" name="datetime">

    <label for="seats">количество мест. старое значение: ${oldtrip.seats}</label>
    <input required type="number" min="1" id="seats" name="seats">

    <button type="submit">обновить рейс</button>
</form>

</body>
</html>
