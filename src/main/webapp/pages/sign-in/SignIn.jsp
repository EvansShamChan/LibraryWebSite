<%--
  Created by IntelliJ IDEA.
  User: vitalii
  Date: 29.06.2018
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>My title</title>
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
        <form method="post" action="SignIn">
            <table>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" required></td>
                </tr>
            </table>
            <input type="submit" value="Sign in">
            <div class="errorText" style="${errorStyle}"><p>Wrong user name or password</p></div>
            <br>
            <a href="/pages/sign-up/registerPage.jsp">Register</a>
        </form>
    </div>
</body>
</html>
