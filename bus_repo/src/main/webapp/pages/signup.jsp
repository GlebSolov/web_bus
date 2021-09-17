<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>регистрация</title>
</head>
<body>
    <h3>регистрация</h3>
    <form action="/register" method="post">
        <input required type="text" placeholder="ФИО" name="name" id="name">
        <input required type="text" placeholder="телефон" name="contact_info" id="phone">
        <input required type="text" placeholder="Password" name="name" id="passw">

        <input type="submit" value="зарегистрироваться" id="submit_reg">
    </form>
    <h2><a href="/">войти</a></h2>
</body>
</html>