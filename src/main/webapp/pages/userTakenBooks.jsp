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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        <%@include file="../css/searchPageStyle.css"%>
    </style>
</head>
<body>
<div align="center">
    <h1><img src="/images/sova.png" width="100" alt="book clipart transparent background"/>Library

        <a id="logOut" href="/pages/signIn.jsp">Log out</a>
        <a id="back" href="/searchPag?searchKey=&rowsPerPage=10&checkBy=bookName&currentPage=1&sort=desc" title="back to search page"> Back to previous page </a>
    </h1>
</div>
<div align="center">
    <form action="/searchPag" method="get">
        <table border="1" cellpadding="5">
            <br><br>
            <c:if test="${allBooksByUserId != null}">
                <tr>
                    <th>Book name</th>
                    <th>Taking date</th>
                    <th>Return until</th>
                    <th>Status</th>
                    <c:if test="${sessionScope.userOrAdmin != 'admin'}">
                        <th>Action</th>
                    </c:if>
                </tr>
            </c:if>
            <c:forEach var="book" items="${allBooksByUserId}">
                <tr>
                    <td>"<c:out value="${book.bookName}"/>"</td>
                    <td align="middle"><c:out value="${book.takeDate}"/></td>
                    <td align="middle"><c:out value="${book.returnUntil}"/></td>
                    <c:if test="${book.returned == true}">
                        <td align="middle">Returned</td>
                    </c:if>

                    <c:if test="${book.returned == false}">
                        <td align="middle">On hands</td>
                    </c:if>

                    <c:if test="${sessionScope.userOrAdmin != 'admin'}">
                        <c:if test="${book.returned == true}">
                            <td align="middle">Book is already returned</td>
                        </c:if>

                        <c:if test="${book.returned == false}">
                            <td align="middle">
                                <a id="return" method="get"
                                   href="/returnBook?bookId=${book.idBook}&userID=${book.idUser}">Return book</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            </td>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:if test="${listBook != null}">
            <nav aria-label="Navigation">
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