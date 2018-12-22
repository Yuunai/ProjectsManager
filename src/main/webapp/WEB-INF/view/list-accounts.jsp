<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="pl">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title>SpacjaTV Konta</title>
</head>
<body>

<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='/account/newAccount'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                Dodaj konto
            </button>
        </div>
    </div>

    <h2>Konta</h2>
    <div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Konto aktywne</th>
            <th scope="col">Akcja</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">

            <c:url var="detailsLink" value="/account/accountDetails">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <c:url var="editLink" value="/account/editAccount">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <c:url var="setEnabledLink" value="/account/setEnabled">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="enabled" value="${true}"/>
            </c:url>
            <tr>
                <td>${user.email}</td>
                <td><c:choose>
                    <c:when test="${user.enabled}">
                        Tak
                    </c:when>

                    <c:otherwise>
                        Nie
                    </c:otherwise>
                </c:choose></td>
                <td>
                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${editLink}'">
                        Szczegóły
                    </button>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
    </div>
</main>
</div>
</div>
<footer class="footer">
    <div class="footer-copyright text-center py-3">
        <div class="container">

            <ul class="list-unstyled list-inline text-center">
                <li class="list-inline-item flagShadow">
                    <a href="#pl"><span class="flag-icon flag-icon-pl"> </span></a>
                </li>
                <li class="list-inline-item flagShadow">
                    <a href="#gb"><span class="flag-icon flag-icon-gb"> </span></a>
                </li>
            </ul>
        </div>
        © 2018-2019 Copyright:
        <a href="https://www.facebook.com/SpacjaTv/"> SpacjaTV</a>
    </div>
</footer>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</body>
</html>