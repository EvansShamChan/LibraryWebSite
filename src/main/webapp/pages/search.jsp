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
    <title>Library</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="icon" href="http://icons.iconarchive.com/icons/pixelkit/swanky-outlines/256/05-Bookmark-Book-icon.png"
          type="image/png">
    <style>
        h1 {
            padding-top: 100px;
        }
    </style>
</head>
<body>
<center>
    <h1>Library</h1>
    <h2>
        <a href="/search/new">Add New Book</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/search/list">List All Books</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2></h2></caption>
        <tr>
            <th>Title</th>
            <th>Authors</th>
            <th>Publication date</th>
            <th>Availability</th>
            <th>Action</th>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.publicationDate}"/></td>
                <td><c:out value="${book.availability}"/></td>
                <td>
                    <a href="/search/edit?name=<c:out value='${book.name}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/search/delete?name=<c:out value='${book.name}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>