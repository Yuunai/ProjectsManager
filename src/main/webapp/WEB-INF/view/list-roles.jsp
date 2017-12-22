<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 20:00
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
    <title>Role list</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="role-table-section">
        <table class="table col-4 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="2" class="text-center">Lista ról</th>
            </tr>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="role" items="${roles}">

                <c:url var="updateLink" value="/role/updateRoleForm">
                    <c:param name="roleId" value="${role.id}" />
                </c:url>

                <c:url var="deleteLink" value="/role/deleteRole">
                    <c:param name="roleId" value="${role.id}" />
                </c:url>

                <tr>
                    <td>${role.name}</td>
                    <td>
                        <a href="${updateLink}">Aktualizuj</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć tą rolę?'))) return false">Usuń</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="3">
                    <a class="btn btn-primary" href="/role/addRoleForm" role="button">Dodaj Rolę</a>
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

