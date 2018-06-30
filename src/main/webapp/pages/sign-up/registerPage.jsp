<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 29/06/2018
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="icon" href="http://icons.iconarchive.com/icons/pixelkit/swanky-outlines/256/05-Bookmark-Book-icon.png"
          type="image/png">
    <style>
        <%@include file="../../css/sign_up/registerPageStyle.css"%>
    </style>
</head>
<body>
    <div align="center">
        <form action="/register" method="post">
            <label>Username:</label>
            <input type="text" name="username" id="username" value="${usernameAtr}" required>
            <div class="errorText" style="${userSameError}"><p>This user is already in use</p></div><br>
            <label>Password:</label>
            <input type="password" name="password" id="password" value="${passwordAtr}" required><br>
            <label>Firstname:</label>
            <input type="text" name="firstname" id="firstname" value="${firstnameAtr}" required><br>
            <label>Lastname:</label>
            <input type="text" name="lastname" id="lastname" value="${lastnameAtr}" required><br>
            <label>Birth date:</label>
            <input type="text" name="dateOfBirth" id="dateOfBirth" placeholder="Example: 2018-06-29" required><br>
            <div class="errorText" style="${dateErrorStyle}"><p>Birth date is incorrect. Please try again</p></div>
            <input type="submit" id="submit" value="Register">
        </form>
    </div>
</body>
</html>
