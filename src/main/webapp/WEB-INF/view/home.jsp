<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <title>Home page</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div class="row">
        <div id="event_section" class="col-6">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="4" class="text-center">Nadchodzące wydarzenia</th>
                </tr>
                <tr>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Miejsce</th>
                    <th scope="col">Data</th>
                    <th scope="col">Szczegóły</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${events}">
                        <c:url var="eventDetails" value="/event/eventDetails">
                            <c:param name="eventId" value="${event.id}" />
                        </c:url>
                        <tr>
                            <td>${event.name}</td>
                            <td>${event.place}</td>
                            <td>${event.date}</td>
                            <td>
                                <a href="${eventDetails}">Szczegóły</a>
                            </td>
                        </tr>
                    </c:forEach>
                <tr>
                    <td colspan="4">
                        <a class="btn btn-primary" href="event/addEventForm" role="button">Add Event</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="footer">

        </div>
    </div>

</div>


</body>
</html>
