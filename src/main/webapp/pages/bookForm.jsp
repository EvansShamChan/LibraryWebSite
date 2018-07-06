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
</head>
<style>
    <%@include file="../css/bookFormStyle.css"%>
</style>
<body>
<div align="center">
    <c:if test="${book != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <h2>
                    <c:if test="${book == null}">
                        Add New Book
                    </c:if>
                    <c:if test="${book != null}">
                        Edit Book
                    </c:if>
                </h2>
                <tbody>
                <tr>
                    <th>Name:</th>
                    <td>
                        <input style="border:none" type="text" name="name" size="45"
                               value="<c:out value='${book.name}'/>"/>
                    </td>
                </tr>
                <tr>
                    <th>Publication date:</th>
                    <td>
                        <input style="border:none" type="text" name="publicationDate" size="45" placeholder="YYYY"
                               value="<c:out value='${book.publicationDate}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Available:</th>
                    <td>
                        <input style="border:none" type="text" name="available" size="45" placeholder="12"
                               value="<c:out value='${book.available}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Authors:</th>
                    <td>
                        <input style="border:none" type="text" name="publicationDate" size="45" placeholder=""
                               value="<c:out value='${book.authors}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
</div>
</body>
</html>
