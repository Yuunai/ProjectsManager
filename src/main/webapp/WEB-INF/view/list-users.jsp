<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-24
  Time: 19:42
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
    <title>Users list</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="role-table-section">
        <table class="table col-5 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="5" class="text-center">Lista członków</th>
            </tr>
            <c:if test="${!empty message}" >
                <tr>
                    <th scope="col" colspan="5" class="text-center">${message}</th>
                </tr>
            </c:if>
            <tr>
                <th scope="col">Imię</th>
                <th scope="col">Nazwisko</th>
                <th scope="col">Indeks</th>
                <th scope="col">Numer telefonu</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">

                <c:url var="detailsLink" value="/user/userDetails">
                    <c:param name="employeeId" value="${user.id}" />
                </c:url>

                <c:url var="deleteLink" value="/user/deleteUser">
                    <c:param name="employeeId" value="${user.id}" />
                </c:url>

                <c:url var="editLink" value="/user/updateUserForm">
                    <c:param name="employeeId" value="${user.id}" />
                </c:url>

                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.studentIndex}</td>
                    <td>${user.phoneNumber}</td>
                    <td><a href="${detailsLink}">Szczegóły</a> |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć tego członka?'))) return false">Usuń</a> |
                        <a href="${editLink}">Aktualizuj</a></td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="5">
                    <a class="btn btn-primary" href="/user/addUserForm" role="button">Dodaj członka</a>
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