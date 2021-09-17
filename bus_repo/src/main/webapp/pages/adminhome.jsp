<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>администратор</title>
</head>
<body>
<h2>администратор</h2>
<form action="/userhome" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="пользователь">
</form>
<br>

<h3>пользователи</h3>
<table style="text-align: center;
              vertical-align: middle;
              width: 100%;
              border-collapse: collapse;">
    <tr style="border: solid black 2px;">
        <th style="border: solid black 2px;">id</th>
        <th style="border: solid black 2px;">Имя</th>
        <th style="border: solid black 2px;">Контактные данные</th>
        <th style="border: solid black 2px;">админ?</th>
        <th style="border: solid black 2px;"></th>
        <th style="border: solid black 2px;"></th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr style="border: solid black 1px;">
            <td style="border: solid black 1px;"><form action="/usersorders/${user.user_id}" method="post">
                                                    <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                                                    <input type="submit" value="${user.user_id}"></input>
                                                 </form></td>
            <td style="border: solid black 1px;">${user.user_name}</td>
            <td style="border: solid black 1px;">${user.user_contact_info}</td>
            <td style="border: solid black 1px;">${user.is_admin}</td>
            <td style="border: solid black 1px;"><form action="/deleteuser" method="post">
                <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                <input type="submit" name="delete_user_id" value="${user.user_id}" >удалить</input>
            </form></td>
            <td style="border: solid black 1px;"><form action="/updateuser/${user.user_id}" method="post">
                <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                <input type="submit" value="редактировать"></input>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form action="/adduser" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="добавить пользователя" id="enter_adduser">
</form>
<br>

<h3>рейсы</h3>
<table style="text-align: center;
              vertical-align: middle;
              width: 100%;
              border-collapse: collapse;">
    <tr style="border: solid black 2px;">
        <th style="border: solid black 2px;">id</th>
        <th style="border: solid black 2px;">маршрут</th>
        <th style="border: solid black 2px;">дата</th>
        <th style="border: solid black 2px;">мест</th>
        <th style="border: solid black 2px;"></th>
        <th style="border: solid black 2px;"></th>
    </tr>
    <c:forEach var="trip" items="${tripsList}">
        <tr style="border: solid black 1px;">
            <td style="border: solid black 1px;"><form action="/tripsorders/${trip.trip_id}" method="post">
                                                    <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                                                    <input type="submit" value="${trip.trip_id}"></input>
                                                </form></td>
            <td style="border: solid black 1px;">${trip.route_id.route_name}</td>
            <td style="border: solid black 1px;">${trip.date_time}</td>
            <td style="border: solid black 1px;">${trip.seats}</td>
            <td style="border: solid black 1px;"><form action="/deletetrip" method="post">
                                                    <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                                                    <input type="submit" name="trip_id" value="${trip.trip_id}" >удалить</input>
                                                 </form></td>
            <td style="border: solid black 1px;"><form action="/updatetrip/${trip.trip_id}" method="post">
                <input style="width: 0px" readonly required value="${user_id}" type="number" name="user_id">
                <input type="submit" value="редактировать"></input>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form action="/addtrip" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="добавить рейс" id="enter_addtrip">
</form>
<!--<a href="/addtrip">добавить рейс</a>
--->
</body>
</html>
