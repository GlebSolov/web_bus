<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>обновление пользователя</title>
</head>
<body>
<form action="/adminhome" method="post">
    <input readonly required value="${user_id}" type="number" name="user_id">
    <input type="submit" value="администратор">
</form>

<form action="/updateuser" method="post">
    <label for="user_id">ваш id</label>
    <input readonly required value="${user_id}" type="number" name="user_id" id="user_id">
    <br>
    <label for="id">id</label>
    <input readonly required name="change_user_id" id="id" type="text" value="${olduser.user_id}">

    <label for="name">ФИО. старое ФИО: ${olduser.user_name}</label>
    <input required type="text" name="name" id="name">

    <label for="phone">телефон. старый телефон: ${olduser.user_contact_info}</label>
    <input required type="text" id="phone" name="phone">

    <label for="admin">админ? старое значение: ${olduser.is_admin}</label>
    <input required type="checkbox" id="admin" name="admin">

    <button type="submit">обновить пользователя</button>
</form>


</body>
</html>
