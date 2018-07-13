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
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#submit").click(function () {
                var password = $("#password").val();
                var confirmPassword = $("#confirmPassword").val();
                if (password != confirmPassword) {
                    alert("Passwords do not match.");
                    return false;
                }
                return true;
            })
        });
    </script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#dateOfBirth").datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: "1900:2018"
            })
        });</script>
</head>
<body>
<div align="center">
    <img class="mb-4"
         src="/images/registrationPage.jpg"
         alt="" width="200" height="200">
    <form method="post" action="/register">
        <div class="errorText" style="${userSameError}"><p>User already exist</p></div>
        <table>
            <tr>
                <td><input type="text" name="username" id="username" value="${usernameAtr}" placeholder="User name"
                           required></td>
            </tr>
            <tr>
                <td><input type="password" name="password" id="password" value="${passwordAtr}" placeholder="Password"
                           required></td>
            </tr>
            <tr>
                <td><input type="password" name="password" id="confirmPassword" value="${passwordAtr}"
                           placeholder="Confirm password" required></td>
            </tr>
            <tr>
                <td><input type="text" name="firstname" id="firstname" value="${firstnameAtr}" placeholder="First name"
                           required></td>
            </tr>
            <tr>
                <td><input type="text" name="lastname" id="lastname" value="${lastnameAtr}" placeholder="Last name"
                           required></td>
            </tr>
            <tr>
                <td><input type="text" name="dateOfBirth" id="dateOfBirth" placeholder="Birth date" required>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="submit" value="Register">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
