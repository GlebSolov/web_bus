<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>новый рейс</title>
</head>
<body>
<form action="/adminhome" method="post">
    <label>id пользователя</label>
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="администратор">
</form>
<form action="/addnewtrip" method="post">
    <label>id пользователя</label>
    <input readonly required value="${user_id}" type="number" name="user_id">
    <br>

    <label for="routeselect">маршрут</label>
    <select required id="routeselect" name="routeselect">
        <option></option>
        <c:forEach var="route" items="${allRoutes}">
            <option value="${route.route_id}">${route.route_name}</option>
        </c:forEach>
    </select>
    <label for="datetime">дата и время</label>
    <input required type="datetime-local" id="datetime" name="datetime">

    <label for="seats">количество мест</label>
    <input required type="number" min="1" id="seats" name="seats">

    <button type="submit" id="enter_submit_addtrip">добавить рейс</button>
</form>

</body>
</html>
