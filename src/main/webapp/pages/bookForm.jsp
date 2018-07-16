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
        $('#AddBook').click(function (event) {
            var path = "/author/add?bookName=" + $('input[name=name]').val();
            $(".add-author").each(function () {
                path += "&" + $(this).attr("name") + "=" + $(this).val()
            });
            var row = '<tr class="classname">' +
                '<td>' + $("#lastName").val() + '</td>' +
                '<td>' + $("#firstName").val() + '</td>' +
                '<td id="td-delete">' +
                '<a id="delete" href="/author/delete?firstName=' + $("#firstName").val() + '&lastName=' + $("#lastName").val() + '&bookName=' + $('input[name=name]').val() + '">Delete</a>' +
                '</td>' +
                '</tr>';

            event.preventDefault();
            $.ajax({
                url: path,
                success: function (response) {
                    $('#lastName').parent().parent().before(row);
                    $("#lastName").val("");
                    $("#firstName").val("");
                },
                error: function (response) {
                    alert('This author already exists!');
                    $("#lastName").val("");
                    $("#firstName").val("");
                }
            });
        })
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
                        },
                        error: function (data) {
                            alert('то!');
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
    <h1><img src="/images/sova.png" width="100" alt="book clipart transparent background"/>Library <a id="logOut"
                                                                                                      href="/pages/signIn.jsp">Log
        out</a>
        <a id="userOptions" href="/options">Statistic</a>
        <a id="addNewBook" href="/search/new">Add new book</a></h1></div>
<br><br><br>
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
                            <td id="td-delete">
                                <a href="/author/delete?firstName=<c:out value='${author.firstName}' />&lastName=<c:out value='${author.lastName}' />&bookName=<c:out value='${book.name}'/>"
                                   id="delete">Delete</a>
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
                        <td style="border-right-style:hidden;border-top-style:hidden;border-bottom-style:hidden;">
                            <a href="" id="AddBook"
                               style='background:#247c18;color:#ffffff;font-size:15px;padding:8px 20px;border-radius:3px;-moz-border-radius:3px;-webkit-border-radius:3px;text-decoration:none;'>Add</a>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td style="border-right-style:hidden;border-left-style:hidden;border-bottom-style:hidden;">

                    </td>
                    <td style="border-right-style:hidden;border-left-style:hidden;border-bottom-style:hidden;">
                        <input class="btn  btn-primary btn-block btn pull-right" type="submit" value="Save"/>
                    </td>
                </tr>
                </tbody>
            </table>
</div>
</body>
</html>