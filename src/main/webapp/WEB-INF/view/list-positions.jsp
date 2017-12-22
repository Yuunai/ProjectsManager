<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-12-02
  Time: 13:14
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
    <title>Positions list</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="role-table-section">
        <table class="table col-4 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="3" class="text-center">Lista pozycji</th>
            </tr>
            <c:if test="${!empty message}" >
                <tr>
                    <th scope="col" colspan="3" class="text-center">${message}</th>
                </tr>
            </c:if>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Maksymalny czas wypożyczenia(dni)</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="position" items="${positions}">

                <c:url var="updateLink" value="/position/updatePositionForm">
                    <c:param name="positionId" value="${position.id}" />
                </c:url>

                <c:url var="deleteLink" value="/position/deletePosition">
                    <c:param name="positionId" value="${position.id}" />
                </c:url>

                <tr>
                    <td>${position.name}</td>
                    <td>${position.lendingTime}</td>
                    <td>
                        <a href="${updateLink}">Aktualizuj</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć tą pozycję?'))) return false">Usuń</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="3">
                    <a class="btn btn-primary" href="/position/addPositionForm" role="button">Dodaj pozycję</a>
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
