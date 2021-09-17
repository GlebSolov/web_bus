<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/style/style.css"/>" rel="stylesheet" type="text/css"/>
    <title>пользователь</title>
</head>
<body>
<h2>пользователь</h2>
<p>
    <c:if test="${is_admin}">
        <form action="/adminhome" method="post">
            <input readonly required value="${user_id}" type="number" name="user_id">
            <input type="submit" value="администратор" id="enter_admin">
        </form>
        <!--<a href="/adminhome"><button type="button">администратор</button></a>-->
    </c:if>
<img src="https://i.yapx.ru/MQSzA.png" width="500px" height="535px">
    <!-- MAP -->
</p>

<p>
<form action="/selecttrip" method="post">
    <label for="user_id">ваш id:</label>
    <input id="user_id" readonly required value="${user_id}" type="number" name="user_id">
    <label for="from">откуда</label>
    <select required name="from" id="from">
        <option></option>
        <option value="1">озеро</option>
        <option value="2">камень</option>
        <option value="3">пляж</option>
        <option value="4">холм</option>
        <option value="5">лес</option>
        <option value="6">школа</option>
        <option value="7">университет</option>
        <option value="8">отель</option>
        <option value="9">дума</option>
        <option value="10">метро</option>
        <option value="11">перекосившийся дом</option>
        <option value="12">депо</option>
        <option value="13">стадион</option>
        <option value="14">театр</option>
        <option value="15">кинотеатр</option>
        <option value="16">цирк</option>
        <option value="17">суд</option>
        <option value="18">учксток</option>
        <option value="19">поле</option>
    </select>
    <label for="to">куда</label>
    <select required name="to" id="to">
        <option></option>
        <option value="1">озеро</option>
        <option value="2">камень</option>
        <option value="3">пляж</option>
        <option value="4">холм</option>
        <option value="5">лес</option>
        <option value="6">школа</option>
        <option value="7">университет</option>
        <option value="8">отель</option>
        <option value="9">дума</option>
        <option value="10">метро</option>
        <option value="11">перекосившийся дом</option>
        <option value="12">депо</option>
        <option value="13">стадион</option>
        <option value="14">театр</option>
        <option value="15">кинотеатр</option>
        <option value="16">цирк</option>
        <option value="17">суд</option>
        <option value="18">учксток</option>
        <option value="19">поле</option>
    </select>
    <input type="submit" value="билеты" id="observe_trips">
</form>
</p>

</body>
</html>
