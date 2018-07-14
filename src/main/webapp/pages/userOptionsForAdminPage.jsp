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
            <a id="userStatisticButton" href="/options/userStatistic">User Statistic</a>
        </div>
        <div class="optionButton">
            <a id="getDeptorsButton" href="/options/deptors">Get Deptors</a>
        </div>
    </div>

    <c:if test="${statistic != null}">
        <div id="userStatistic" align="center">
            <p>Average age: ${statistic.avgAge} years.</p>
            <p>Average time library usage: ${statistic.avgLibraryUsage} days.</p>
            <p>Average count of appeal: ${statistic.avgCountOfAppeal} times.</p>
        </div>
    </c:if>
</body>
</html>
