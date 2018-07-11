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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<style>
    <%@include file="../css/bookFormStyle.css"%>
    <%@include file="../css/custom-theme/jquery-ui-1.10.0.custom.css"%>
</style>
<script>
    $(document).ready(function () {
        $(".add-author").change(function () {
            var path = "/author/add?bookName=" + $('input[name=name]').val();
            $(".add-author").each(function () {
                path += "&" + $(this).attr("name") + "=" + $(this).val()
            })

            $('#AddBook').attr("href", path);
        });
        $(function () {
            $("#lastName").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: "/auto",
                        dataType: "json",
                        data: {query: request.term},
                        success: function (data) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.lastName + ', ' + item.firstName,
                                    value: item.lastName,
                                    firstName: item.firstName
                                }

                            }))
                        }
                    });
                },
                select: function (event, ui) {
                    $("#lastName").val(ui.item.label);
                    $("#firstName").val(ui.item.firstName);
                }
            });
        });
    });
</script>
<body>
<div align="center">
    <c:if test="${book != null}">
    <form action="/search/update" method="post">
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
                        <c:if test="${book == null}">
                            <input style="border:none" type="text" name="name" size="45"
                                   value="<c:out value='${book.name}'/>"/>
                        </c:if>
                        <c:if test="${book != null}">
                            <input style="border:none" type="text" name="name" size="45" readonly
                                   value="<c:out value='${book.name}'/>"/>
                        </c:if>
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
                <c:if test="${book != null}">
                    <tr>
                        <th>Authors:</th>
                        <td>
                        </td>
                    </tr>
                    <c:forEach var="author" items="${book.authors}">
                        <tr class="classname">
                            <td><c:out value="${author.lastName}"/></td>
                            <td><c:out value="${author.firstName}"/></td>
                            <td>
                                <a href="/author/delete?firstName=<c:out value='${author.firstName}' />&lastName=<c:out value='${author.lastName}' />&bookName=<c:out value='${book.name}'/>">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <input id="lastName" class="add-author" style="border:none" type="text" name="lastName"
                                   size="45"/>
                        </td>
                        <td>
                            <input id="firstName" class="add-author" style="border:none" type="text" name="firstName"
                                   size="25"/>
                        </td>
                        <td>
                            <a href="" id="AddBook">Add</a>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="4" align="center">
                        <input class="btn btn-lg btn-primary btn-block " type="submit" value="Save"/>
                    </td>
                </tr>
                </tbody>
            </table>
</div>
</body>
</html>