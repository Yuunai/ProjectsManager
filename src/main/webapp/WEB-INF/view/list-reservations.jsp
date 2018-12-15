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

    <div id="reservation-table-section">
        <table class="table col-6 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="5" class="text-center">Lista rezerwacji</th>
            </tr>
            <c:if test="${!empty message}" >
                <tr>
                    <th scope="col" colspan="5" class="text-center">${message}</th>
                </tr>
            </c:if>
            <tr>
                <th scope="col">Rezerwacja</th>
                <th scope="col">Rezerwujacy</th>
                <th scope="col">Od</th>
                <th scope="col">Do</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reservation" items="${reservations}">

                <c:url var="updateLink" value="/reservation/updateReservationForm">
                    <c:param name="reservationId" value="${reservation.id}" />
                </c:url>

                <c:url var="deleteLink" value="/reservation/deleteReservation">
                    <c:param name="reservationId" value="${reservation.id}" />
                </c:url>

                <tr>
                    <td>${reservation.event.name}</td>
                    <td>${reservation.user.email}</td>
                    <td>${reservation.dateSince} ${reservation.timeSince}</td>
                    <td>${reservation.dateTo} ${reservation.timeTo}</td>
                    <td>
                        <a href="${updateLink}">Aktualizuj</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Czy na pewno chcesz usunąć to wypożyczenie?'))) return false">Usuń</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="5">
                    <a class="btn btn-primary" href="reservation/addReservationForm" role="button">Nowe wypożyczenie</a>
                </td>
            </tr>
            <tr><td colspan="5">
            <form action="${pageContext.request.contextPath}/reservation/addReservationForm" method="GET">
                Start: <input type="date" name="date-since"/> <input type="time" name="time-since"/>
                Koniec: <input type="date" name="date-to"/> <input type="time" name="time-to">
                <input class="btn btn-primary" type="submit" value="Dodaj rezerwację">
            </form>
            </td></tr>
            </tbody>
        </table>
    </div>

    <div id="footer">

    </div>

</div>


</body>
</html>
