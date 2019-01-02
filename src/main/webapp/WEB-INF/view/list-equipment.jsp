<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-12-01
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <title>Equipment List</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="role-table-section">
        Kategorie:
        <a href="${pageContext.request.contextPath}/equipment/list">Wszystko</a> |
        <a href="${pageContext.request.contextPath}/equipment/list?categoryId=1">Video</a> |
        <a href="${pageContext.request.contextPath}/equipment/list?categoryId=2">Audio</a> |
        <a href="${pageContext.request.contextPath}/equipment/list?categoryId=3">Live</a> |
        <a href="${pageContext.request.contextPath}/equipment/list?categoryId=4">Akcesoria</a>
        <table class="table col-4 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="4" class="text-center">Lista wyposażenia</th>
            </tr>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Stan</th>
                <th scope="col">Komentarz</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${equipment}">

                <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                    <c:param name="itemId" value="${item.id}" />
                </c:url>

                <c:url var="deleteLink" value="/equipment/deleteEquipment">
                    <c:param name="itemId" value="${item.id}" />
                </c:url>

                <tr>
                    <td>${item.name}</td>
                    <td>${item.state}</td>
                    <td>${item.comments}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć ten przedmiot?'))) return false">Delete</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="4">
                    <a class="btn btn-primary" href="/equipment/addEquipmentForm" role="button">Dodaj przedmiot</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div id="footer">

    </div>

</div>


</body>
</html>


