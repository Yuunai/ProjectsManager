<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-01-05
  Time: 16:17
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
    <title>Lending list</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="lending-table-section">
        <table class="table col-6 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="6" class="text-center">Lista wypożyczeń</th>
            </tr>
            <c:if test="${!empty message}" >
                <tr>
                    <th scope="col" colspan="6" class="text-center">${message}</th>
                </tr>
            </c:if>
            <tr>
                <th scope="col">Wydarzenie</th>
                <th scope="col">Wypożyczający</th>
                <th scope="col">Od</th>
                <th scope="col">Do</th>
                <th scope="col">Status</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lending" items="${lendings}">

                <c:url var="updateLink" value="/lending/updateLendingForm">
                    <c:param name="lendingId" value="${lending.id}" />
                </c:url>

                <c:url var="deleteLink" value="/lending/deleteLending">
                    <c:param name="lendingId" value="${lending.id}" />
                </c:url>

                <tr>
                    <td>${lending.event.name}</td>
                    <td>${lending.employee.firstName} ${lending.employee.lastName}</td>
                    <td>${lending.since}</td>
                    <td>${lending.end}</td>
                    <c:if test="${lending.return_time == null}">
                        <td>Aktualne</td>
                    </c:if>
                    <c:if test="${lending.return_time != null}">
                        <td>
                            Zakończone<br>
                            ${lending.return_time}
                        </td>
                    </c:if>
                    <td>
                        <a href="${updateLink}">Aktualizuj</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć to wypożyczenie?'))) return false">Usuń</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="6">
                    <a class="btn btn-primary" href="/lending/addLendingForm" role="button">Nowe wypożyczenie</a>
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
