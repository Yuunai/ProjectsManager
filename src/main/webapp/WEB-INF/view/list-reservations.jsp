<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title><fmt:message key="reservations.title"/></title>
</head>
<body>

<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">

        </div>
    </div>

    <h2><fmt:message key="reservations.header"/></h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="reservations.colResName"/></th>
                <th scope="col"><fmt:message key="reservations.colResUser"/></th>
                <th scope="col"><fmt:message key="reservations.colSince"/></th>
                <th scope="col"><fmt:message key="reservations.colTo"/></th>
                <th scope="col"></th>
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
                    <td>${usersNames.get(reservation.user.id)}</td>
                    <td>${reservation.dateSince} ${reservation.timeSince}</td>
                    <td>${reservation.dateTo} ${reservation.timeTo}</td>
                    <td>
                        <a href="${updateLink}"><fmt:message key="reservations.details"/></a>
                        <security:authorize access="hasAnyRole('ADMIN','MODERATOR')">
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('<fmt:message key="reservations.confirmMessage"/>'))) return false"><fmt:message key="reservations.remove"/></a>
                        </security:authorize>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
            </tbody>
        </table>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>