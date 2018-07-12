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
        <%@include file="../css/userSearchBookPageStyle.css"%>
    </style>
</head>
<body>
<div align="center">
    <h1>Library</h1>
</div>
<div align="center">
    <form action="/searchPag" method="get">
        <table border="1" cellpadding="5">
            <br><br>
            <label class="rowsPerPage">Rows per page</label>

            <c:choose>
                <c:when test="${rowsPerPage == 5}"><c:set var="rows5" value="selected"/></c:when>
                <c:when test="${rowsPerPage == 10}"><c:set var="rows10" value="selected"/></c:when>
                <c:when test="${rowsPerPage == 15}"><c:set var="rows15" value="selected"/></c:when>
                <c:when test="${rowsPerPage == 20}"><c:set var="rows20" value="selected"/></c:when>
            </c:choose>

            <select name="rowsPerPage" id="rowsPerPage">
                <option value="5" ${rows5}>5</option>
                <option value="10" ${rows10}>10</option>
                <option value="15" ${rows15}>15</option>
                <option value="20" ${rows20}>20</option>
            </select><br>
            <input type="hidden" name="currentPage" value="1">
            <c:if test="${allBooksByUserId != null}">
                <tr>
                    <th>Book name</th>
                    <th>Taking date</th>
                    <th>Return until</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </c:if>
            <c:forEach var="book" items="${allBooksByUserId}">
                <tr>
                    <td><c:out value="${book.bookName}"/></td>
                    <td><c:out value="${book.takeDate}"/></td>
                    <td><c:out value="${book.returnUntil}"/></td>
                    <c:if test="${book.returned == true}">
                        <td>returned</td>
                    </c:if>

                    <c:if test="${book.returned == false}">
                        <td>on hands</td>
                    </c:if>
                    <td>
                        <a method="get"
                           href="/takeTheBook?bookId=${book.idBook}&userID=${book.idUser}">Return book</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
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