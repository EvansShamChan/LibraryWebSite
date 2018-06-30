<%--
  Created by IntelliJ IDEA.
  User: khrystyna
  Date: 28.06.18
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Search</h2></caption>
        <tr>
            <th>Title</th>
            <th>Authors</th>
            <th>Publication date</th>
            <th>Availability</th>

        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.publicationDate}"/></td>
                <td><c:out value="${book.availability}"/></td>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>