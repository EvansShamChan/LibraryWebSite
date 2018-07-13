<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="icon" href="http://icons.iconarchive.com/icons/pixelkit/swanky-outlines/256/05-Bookmark-Book-icon.png"
          type="image/png">
    <style>
        <%@include file="../css/registerPageStyle.css"%>
    </style>
</head>
<body>
<div align="center">
    <img class="mb-4"
         src="/images/signInPage.jpg"
         alt="" width="200" height="200">
    <form method="post" action="signIn">
        <table>
            <tr>
                <td><input type="text" name="username" required placeholder="User name"></td>
            </tr>
            <tr>

                <td><input type="password" name="password" required placeholder="Password"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Sign in">
                    <div class="errorText" style="${errorStyle}"><p>Wrong user name or password</p></div>
                    <br>
                    <a href="/pages/registerPage.jsp" style="margin:auto; text-align:center; display:block;">Register</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
