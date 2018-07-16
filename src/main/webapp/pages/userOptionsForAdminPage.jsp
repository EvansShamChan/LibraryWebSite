<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="icon" href="http://icons.iconarchive.com/icons/pixelkit/swanky-outlines/256/05-Bookmark-Book-icon.png"
          type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="../css/searchPageStyle.css"%>
    </style>
</head>
<body>
<div align="center">
    <h1><img src="/images/sova.png" width="100" alt="book clipart transparent background"/>Library
        <a id="logOut" href="/pages/signIn.jsp">Log out</a>
        <a id="back" href="/searchPag?searchKey=&rowsPerPage=10&checkBy=bookName&currentPage=1&sort=desc"
           title="back to search page"> Back to search page </a>
    </h1>
</div>
<br><br><br><br>
<div class="row" align="middle">
    <div class="col-md-4">
        <img src="/images/icons8-pie-chart-96.png" alt="">
        <a id="userStatisticButton" href="/options/userStatistic">All User Statistic</a>
    </div>
    <div class="col-md-4">
        <img src="/images/icons8-business-report-96.png" alt="">
        <a id="getDeptorsButton" href="/options/deptors">Get Debtors</a>
    </div>
    <div class="col-md-4">
        <img src="/images/icons8-statistics-96.png" alt="">
        <a href="/options/eachUserStatistic">User Statistic</a>
    </div>
</div>
<br><br><br>
<c:if test="${statistic != null}">
    <div align="center" id="average">
        <p>Average age: ${statistic.avgAge} years.</p>
        <p>Average time library usage: ${statistic.avgLibraryUsage} days.</p>
        <p>Average count of appeal: ${statistic.avgCountOfAppeal} times.</p>
    </div>
</c:if>
<c:if test="${deptors != null}">
    <table align="center">
        <tr align="middle">
            <th>First name</th>
            <th>Last name</th>
            <th>Book name</th>
            <th>Take date</th>
        </tr>
        <c:forEach items="${deptors}" var="deptor">
            <tr>
                <td align="middle">${deptor.firstName}</td>
                <td align="middle">${deptor.lastName}</td>
                <td align="middle">"${deptor.bookName}"</td>
                <td align="middle">${deptor.takeDate}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${allUsers != null}">
    <table id="usertable" align="center">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Check user info</th>
        </tr>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td align="middle"><a method="get" id="check"
                                      href="/takenBooks?&userID=${user.userId}">Check info</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>