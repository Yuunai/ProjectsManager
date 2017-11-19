<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <title>Home page</title>
</head>
<body>

<div id="container" class="container" >

    <div id="window-header">
        <h1 id="header">SpacjaTV ERP</h1>
    </div>

    <div class="row">
        <!-- TODO add href for event details -->
        <div id="event_section" class="col-6">
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <th scope="col">Nazwa</th>
                    <th scope="col">Miejsce</th>
                    <th scope="col">Data</th>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${events}">
                        <tr>
                            <td>${event.name}</td>
                            <td>${event.place}</td>
                            <td>${event.date}</td>
                        </tr>

                    </c:forEach>
                <tr>
                    <td colspan="3">
                        <a class="btn btn-primary" href="event/addEventForm" role="button">Add Event</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="employees_section" class="col-6">

        </div>

        <div id="footer">

        </div>
    </div>

</div>


</body>
</html>
