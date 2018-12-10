<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-20
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add Event</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/header-style.css" />

</head>
<body>

<div id="container" class="container">

    <%@include file="header.jsp"%>

    <%--TODO add lendings table below participations table--%>
    <div >

        <div id="eventDetailsTable" class="float-left w-50">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="2" class="text-center">Wydarzenie</th>
                </tr>
                <tr>
                    <th scope="col">Właściwość</th>
                    <th scope="col">Wartość</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><label>Nazwa</label></td>
                    <td>${event.name}</td>
                </tr>
                <tr>
                    <td><label>Miejsce</label></td>
                    <td>${event.place}</td>
                </tr>
                <tr>
                    <td><label>Data</label></td>
                    <td>${event.date}</td>
                </tr>
                <tr>
                    <td><label>Data(dd.MM.yyyy hh:mm)</label></td>
                    <td>${event.time}</td>
                </tr>
                <tr>
                    <td><label>Organizator</label></td>
                    <td>${event.organizer}</td>
                </tr>
                <tr>
                    <td><label>Number telefonu</label></td>
                    <td>${event.phoneNumber}</td>
                </tr>
                <tr>
                    <td><label>Email</label></td>
                    <td>${event.email}</td>
                </tr>
                <tr>
                    <td><label>Komentarze</label></td>
                    <td>${event.comments}</td>
                </tr>
                <tr>
                    <td><label>Priorytet</label></td>
                    <td>${event.priority}</td>
                </tr>
                <tr>
                    <td><label>Deadline(dd.MM.yy hh:mm)</label></td>
                    <td>${event.deadline}</td>
                </tr>
                <tr>
                    <td><label>Typ nagrania</label></td>
                    <td>${event.videoType}</td>
                </tr>
                <tr>
                    <td><label>Archiwizowane</label></td>
                    <td>${event.archived}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="participationsTable" class="float-left w-50">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="3" class="text-center">Uczestnicy</th>
                </tr>
                <tr>
                    <th scope="col">Imię i nazwisko</th>
                    <th scope="col">Rola</th>
                    <th scope="col">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty participations}">
                    <tr>
                        <td colspan="3">Brak chętnych :c Bądź pierwszy!</td>
                    </tr>
                </c:if>
                <c:forEach var="participation" items="${participations}">
                    <c:url var="deleteLink" value="/participation/deleteParticipation">
                        <c:param name="roleId" value="${participation.role.id}" />
                        <c:param name="employeeId" value="${participation.user.id}" />
                        <c:param name="eventId" value="${participation.event.id}" />
                    </c:url>
                    <tr>
                        <td>${participation.user.firstName} ${participation.user.lastName}</td>
                        <td>${participation.role.name}</td>
                        <td>
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false">Usuń
                            </a>
                    </tr>
                </c:forEach>
                <form:form action="/participation/addParticipation" modelAttribute="participation" method="POST" acceptCharset="utf8">
                    <form:hidden path="eventId"/>
                    <tr>
                        <td>
                            <form:select path="employeeId">
                                <form:option value="0" label="Wybierz pracownika"/>
                                <c:forEach items="${users}" var="user">
                                    <form:option value="${user.id}" label="${user.firstName} ${user.lastName}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:select path="roleId">
                                <form:option value="0" label="Wybierz rolę"/>
                                <c:forEach items="${roles}" var="role">
                                    <form:option value="${role.id}" label="${role.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <input type="submit" value="Dodaj"/>
                        </td>
                    </tr>
                </form:form>
                </tbody>
            </table>
        </div>

        <p>
            <a href="${pageContext.request.contextPath}/home">Wróc do listy</a>
        </p>


</div>


</body>
</html>

