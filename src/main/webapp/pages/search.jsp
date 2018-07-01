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
    <form action="/search" method="get">
        <table border="1" cellpadding="5">
            <input type="text" name="searchKey" placeholder="Search" value="${searchKey}">
            <input type="submit" value="Search">
            <label>Rows per page</label>
            <select name="rowsPerPage" id="rowsPerPage">
                <option value="1">1</option>
                <option value="5">5</option>
                <option value="10" selected>10</option>
                <option value="15">15</option>
                <option value="20">20</option>
            </select><br>
            <input type="radio" value="author" name="checkBy">By Author
            <input type="radio" value="bookName" name="checkBy" checked>By Book name
            <input type="hidden" name="currentPage" value="1">

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

        <nav aria-label="Navigation for countries">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="search?rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${nOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="search?rowsPerPage=${rowsPerPage}&currentPage=${i}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt nOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="search?rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </form>
</div>
</body>
</html>