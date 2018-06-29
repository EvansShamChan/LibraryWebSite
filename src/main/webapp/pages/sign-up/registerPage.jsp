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
    <style>
        <%@include file="../../css/sign_up/registerPageStyle.css"%>
    </style>
</head>
<body>
    <div align="center">
        <form action="/register" method="post">
            <label>Username: <input type="text" name="username" id="username"></label><br>
            <label>Password: <input type="password" name="password" id="password"></label><br>
            <label>Firstname: <input type="text" name="firstname" id="firstname"></label><br>
            <label>Lastname: <input type="text" name="lastname" id="lastname"></label><br>
            <label>Date of birth: <input type="text" name="dateOfBirth" id="dateOfBirth"
                                         placeholder="Example: 2018-06-29"></label><br>
            <div id="errorDateText" style="${dateErrorStyle}"><p>Incorrect value. Please try again</p></div>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
