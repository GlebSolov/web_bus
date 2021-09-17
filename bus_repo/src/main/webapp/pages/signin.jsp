<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>авторизация</title>
</head>
<body>
    <h3>авторизация</h3>
    <form action="/auth" method="post">
        <input id="enter_id" required type="text" name="user_id" placeholder="id пользователя">
        <input id="enter_passw" required type="text" name="passw" placeholder="Password">
        <input type="submit" value="войти" id="enter_submit">
    </form>
    <h2><a href="/signup" id="reg">зарегистрироваться</a></h2>
</body>
</html>