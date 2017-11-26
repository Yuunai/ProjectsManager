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
    <title>Employees list</title>
</head>
<body>

<div id="container" class="container" >

    <div id="window-header">
        <h1 id="header">SpacjaTV ERP</h1>
    </div>
    <div id="role-table-section">
        <table class="table col-5 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="5" class="text-center">Lista członków</th>
            </tr>
            <tr>
                <th scope="col">Imię</th>
                <th scope="col">Nazwisko</th>
                <th scope="col">Indeks</th>
                <th scope="col">Number telefonu</th>
                <th scope="col">Szczegóły</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">

                <c:url var="detailsLink" value="/employee/employeeDetails">
                    <c:param name="employeeId" value="${employee.id}" />
                </c:url>

                <tr>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.studentIndex}</td>
                    <td>${employee.phoneNumber}</td>
                    <td><a href="${detailsLink}">Szczegóły</a></td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="5">
                    <a class="btn btn-primary" href="/employee/addEmployeeForm" role="button">Dodaj członka</a>
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