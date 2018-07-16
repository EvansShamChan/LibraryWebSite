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
    <script src="../js/bookSearchScript.js" charset="utf-8">
        <%@include file="../js/bookSearchScript.js"%>
    </script>
</head>
<body>
<div align="center">
    <h1><img src="/images/sova.png" width="100" alt="book clipart transparent background"/>Library
        <a id="logOut" href="/pages/signIn.jsp">Log out</a>
        <a id="alreadyTakenBooks" method="get"
           href="/takenBooks?userID=${sessionScope.userID}">Already taken books</a>
    </h1>
    <div class="errorTextMessage" style="${bookMessageStyle}"><p>${bookTakeResultMessage}</p></div>
    <form action="/searchPag" method="get">
        <input type="text" align="left" id="searchKey" name="searchKey" placeholder="Search.." value="${searchKey}">
        <br>
        <br>
        <c:choose>
            <c:when test="${sort == 'desc'}">
                <c:set var="sortDesc" value="checked"></c:set>
            </c:when>
            <c:when test="${sort == 'asc'}">
                <c:set var="sortAsc" value="checked"></c:set>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${checkBy == 'bookName'}">
                <c:set var="checkByBook" value="checked"/>
            </c:when>
            <c:when test="${checkBy == 'author'}">
                <c:set var="checkByAuthor" value="checked"/>
            </c:when>
            <c:when test="${checkBy == 'publicationDate'}">
                <c:set var="checkByDate" value="checked"/>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${rowsPerPage == 5}">
                <c:set var="rows5" value="selected"/>
            </c:when>
            <c:when test="${rowsPerPage == 10}">
                <c:set var="rows10" value="selected"/>
            </c:when>
            <c:when test="${rowsPerPage == 15}">
                <c:set var="rows15" value="selected"/>
            </c:when>
            <c:when test="${rowsPerPage == 20}">
                <c:set var="rows20" value="selected"/>
            </c:when>
        </c:choose>
        <label>Rows per page:</label>
        <select name="rowsPerPage" id="rowsPerPage">
            <option value="5" ${rows5}>5</option>
            <option value="10" ${rows10}>10</option>
            <option value="15" ${rows15}>15</option>
            <option value="20" ${rows20}>20</option>
        </select>
        <div class="row">
            <div class="col-md-6" id="left">
                <input type="radio" id="author" value="author" name="checkBy" ${checkByAuthor}> Author
                <input type="radio" id="bookName" value="bookName" name="checkBy" ${checkByBook}> Book name
                <input type="radio" id="publicationDate" value="publicationDate" name="checkBy" ${checkByDate}>
                Publication date
            </div>
            <div class="col-md-6" id="right">

                <input type="radio" id="sortBy1" value="desc" name="sort" ${sortDesc}> Popular
                <input type="radio" id="sortBy2" value="asc" name="sort" ${sortAsc}> Unpopular

            </div>
        </div>
        <input type="hidden" name="currentPage" value="1">
    </form>
    <table border="1" cellpadding="5">
        <col style="width:18%">
        <col style="width:36%">
        <col style="width:12%">
        <col style="width:11%">
        <col style="width:11%">
        <col style="width:10%">
        <c:if test="${listBook != null}">
            <tr>
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
                <td>"<c:out value="${book.name}"/>"</td>
                <td>
                    <c:forEach var="author" items="${book.authors}" varStatus="status">
                        <c:out value="${author}"/>
                        <c:if test="${not status.last}">,</c:if>
                    </c:forEach>
                </td>
                <td align="middle"><c:out value="${book.publicationDate}"/></td>
                <td align="middle"><c:out value="${book.available}"/></td>
                <td align="middle"><c:out value="${book.numberOfTaken}"/></td>
                <td align="middle">
                    <a method="get" id="take"
                       href="/takeTheBook?bookId=${book.id}&userID=${sessionScope.userID}&available=${book.available}">Take</a>
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
                       href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>&sort=<%= request.getParameter("sort")%>">Previous</a>
                </c:if>

                <c:forEach begin="1" end="${nOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </c:when>
                        <c:otherwise>
                            <a method="post" class="page-link"
                               href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${i}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>&sort=<%= request.getParameter("sort")%>">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt nOfPages}">
                    <a method="post" class="page-link"
                       href="searchPag?rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}&checkBy=<%= request.getParameter("checkBy")%>&searchKey=<%= request.getParameter("searchKey")%>&sort=<%= request.getParameter("sort")%>">Next</a>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <br>
</div>
</body>
</html>