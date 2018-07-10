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
            <input class="inputClass" style="${searchFieldWithMessage}" type="text" id="searchKey" name="searchKey" placeholder="Search" value="${searchKey}">
            <a class="alreadyTakenBooks" method="get"
               href="/takenBooks?userID=${sessionScope.userID}">Already taken books</a>
            <div class="successText" style="${bookWasSuccessfullyAdded}"><p>Book was successfully added</p></div>
            <div class="errorTextMessage" style="${thisBookCannotBeTaken}"><p>This book cannot be taken</p></div>
            <input type="submit" value="Search"><br><br>
            <label>Rows per page</label>
            <c:choose>
                <c:when test="${checkBy == 'bookName'}"><c:set var="checkByBook" value="checked"/></c:when>
                <c:when test="${checkBy == 'author'}"><c:set var="checkByAuthor" value="checked"/></c:when>
                <c:when test="${checkBy == 'publicationDate'}"><c:set var="checkByDate" value="checked"/></c:when>
            </c:choose>

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

            <input type="radio" id= "author" value="author" name="checkBy" ${checkByAuthor}>By Author
            <input type="radio" id="bookName" value="bookName" name="checkBy" ${checkByBook}>By Book name
            <input type="radio" id="publicationDate" value="publicationDate" name="checkBy" ${checkByDate}>By publication date
            <input type="hidden" name="currentPage" value="1">
            <c:if test="${listBook != null}">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Authors</th>
                    <th>Publication date</th>
                    <th>Availability</th>
                    <th>Number of taken</th>
                    <th>Action</th>
                </tr>
            </c:if>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.name}"/></td>
                    <td><c:out value="${book.authors}"/></td>
                    <td><c:out value="${book.publicationDate}"/></td>
                    <td><c:out value="${book.available}"/></td>
                    <td><c:out value="${book.numberOfTaken}"/></td>
                    <td>
                        <a method="get"
                           href="/takeTheBook?bookId=${book.id}&userID=${sessionScope.userID}&available=${book.available}">Take</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
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
<script src="../js/bookSearchScript.js" charset="utf-8">
    <%@include file="../js/bookSearchScript.js"%>
</script>
</body>
</html>