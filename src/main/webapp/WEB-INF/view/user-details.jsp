<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-29
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>User Details</title>

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

    <div >
        <div class="float-left w-50">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="2" class="text-center">Członek</th>
                </tr>
                <tr>
                    <th scope="col">Właściwość</th>
                    <th scope="col">Wartość</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><label>Imię</label></td>
                    <td>${details.firstName}</td>
                </tr>
                <tr>
                    <td><label>Nazwisko</label></td>
                    <td>${details.lastName}</td>
                </tr>
                <tr>
                    <td><label>Indeks</label></td>
                    <td>${details.studentIndex}</td>
                </tr>
                <tr>
                    <td><label>Numer telefonu</label></td>
                    <td>${details.phoneNumber}</td>
                </tr>
                <tr>
                    <td><label>Wejście do biura</label></td>
                    <c:if test="${details.officeEntrance}">
                        <td>Tak</td>
                    </c:if>
                    <c:if test="${details.officeEntrance == false}">
                        <td>Nie</td>
                    </c:if>
                </tr>
                <tr>
                    <td><label>Samochód</label></td>
                    <c:if test="${details.car}">
                        <td>Tak</td>
                    </c:if>
                    <c:if test="${details.car == false}">
                        <td>Nie</td>
                    </c:if>
                </tr>
                <tr>
                    <td><label>Aktywny</label></td>
                    <c:if test="${details.active}">
                        <td>Tak</td>
                    </c:if>
                    <c:if test="${details.active == false}">
                        <td>Nie</td>
                    </c:if>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="participationsTable" class="float-left w-50">
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="3" class="text-center">Uczestnictwa</th>
                </tr>
                <tr>
                    <th scope="col">Wydarzenie</td></th>
                    <th scope="col">Rola</th>
                    <th scope="col">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty participations}">
                    <tr>
                        <td colspan="3">Skryty członek, który nie lubi uczestniczyć w wydarzeniach :c</td>
                    </tr>
                </c:if>
                <c:forEach var="participation" items="${participations}">
                    <c:url var="deleteLink" value="/participation/deleteParticipation">
                        <c:param name="roleId" value="${participation.role.id}" />
                        <c:param name="employeeId" value="${participation.user.id}" />
                        <c:param name="eventId" value="${participation.event.id}" />
                    </c:url>
                    <tr>
                        <td>${participation.event.name}</td>
                        <td>${participation.role.name}</td>
                        <td>
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false">Usuń
                            </a>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <p>
            <a href="${pageContext.request.contextPath}/user/list">Wróc do listy</a>
        </p>


    </div>


</body>
</html>
