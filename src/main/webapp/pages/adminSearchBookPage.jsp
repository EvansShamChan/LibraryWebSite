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
        <%@include file="../css/searchPageStyle.css"%>
    </style>
</head>
<body>
<div align="center">
    <h1>Library</h1>
    <h2>
        <a href="/search/new">Add New Book</a>
    </h2>
</div>
<div align="center">
    <form action="/searchPag" method="get">
        <table border="1" cellpadding="5">
            <input type="text" name="searchKey" placeholder="Search" value="${searchKey}">
            <input type="submit" value="Search"><br><br>
            <label>Rows per page</label>
            <select name="rowsPerPage" id="rowsPerPage">
                <option value="5">5</option>
                <option value="10" selected>10</option>
                <option value="15">15</option>
                <option value="20">20</option>
            </select><br>
            <input type="radio" value="author" name="checkBy">By Author
            <input type="radio" value="bookName" name="checkBy" checked>By Book name
            <input type="radio" value="publicationDate" name="checkBy">By publication date
            <input type="hidden" name="currentPage" value="1">
            <c:if test="${listBook != null}">
                <tr>
                    <th>Title</th>
                    <th>Authors</th>
                    <th>Publication date</th>
                    <th>Availability</th>
                    <th>Action</th>
                </tr>
            </c:if>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.name}"/></td>
                    <td><c:out value="${book.authors}"/></td>
                    <td><c:out value="${book.publicationDate}"/></td>
                    <td><c:out value="${book.available}"/></td>
                    <td>
                        <a href="/search/edit?name=<c:out value='${book.name}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/search/delete?name=<c:out value='${book.name}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:if test="${listBook != null}">
            <nav aria-label="Navigation for countries">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <a method="post" class="page-link"
                           href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">Previous</a>
                    </c:if>

                    <c:forEach begin="1" end="${nOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </c:when>
                            <c:otherwise>
                                <a method="post" class="page-link"
                                   href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${i}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt nOfPages}">
                        <a method="post" class="page-link"
                           href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">Next</a>
                    </c:if>
                </ul>
            </nav>
        </c:if>
    </form>
</div>
</body>
</html>