<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 14/07/2018
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Library</title>
    <style>
        <%@ include file="../css/adminOptionsStyle.css"%>
    </style>
</head>
<body>
    <div class="buttons">
        <div class="optionButton">
            <a id="userStatisticButton" href="/options/userStatistic">All User Statistic</a>
        </div>
        <div class="optionButton">
            <a id="getDeptorsButton" href="/options/deptors">Get Deptors</a>
        </div>
        <div class="optionButton">
            <a href="/options/eachUserStatistic">User Statistic</a>
        </div>
    </div>

    <c:if test="${statistic != null}">
        <div id="userStatistic" align="center">
            <p>Average age: ${statistic.avgAge} years.</p>
            <p>Average time library usage: ${statistic.avgLibraryUsage} days.</p>
            <p>Average count of appeal: ${statistic.avgCountOfAppeal} times.</p>
        </div>
    </c:if>
    <c:if test="${deptors != null}">
        <table id="deptorsTable" border="1" cellpadding="4">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Book name</th>
                <th>Take date</th>
            </tr>
            <c:forEach items="${deptors}" var="deptor">
                <tr>
                    <th>${deptor.firstName}</th>
                    <th>${deptor.lastName}</th>
                    <th>${deptor.bookName}</th>
                    <th>${deptor.takeDate}</th>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if  test="${allUsers != null}">
        <table class="deptorsTable" border="1" cellpadding="4">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Check user info</th>
            </tr>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <th>${user.firstName}</th>
                    <th>${user.lastName}</th>
                    <th><a method="get"
                           href="/takenBooks?&userID=${user.userId}">Check info</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;</th>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
