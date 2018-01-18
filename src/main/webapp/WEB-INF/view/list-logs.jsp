<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-01-18
  Time: 08:24
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
    <title>Log list</title>
</head>
<body>

<div id="container" class="container" >

    <%@include file="header.jsp"%>

    <div id="logs-table-section">
        <table class="table col-4 table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" colspan="2" class="text-center">Logi</th>
            </tr>
            <c:if test="${!empty message}" >
                <tr>
                    <th scope="col" colspan="3" class="text-center">${message}</th>
                </tr>
            </c:if>
            <tr>
                <th scope="col">Data</th>
                <th scope="col">Log</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${logs}">

                <tr>
                    <td>${log.dateTime}</td>
                    <td>${log.log}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="footer">

    </div>

</div>


</body>
</html>

