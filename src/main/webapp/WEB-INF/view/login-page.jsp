<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang"
       value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="pl">

<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/login-page.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title>Projects Manager Login</title>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">

</head>
<body>

<form:form id="loginForm" class="form-signin" action="${pageContext.request.contextPath}/authUser" method="POST">
    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">Projects Manager</h1>
    </div>

    <c:if test="${message != null}">
        <span class="loginFailed">
            <fmt:message key="${message}"/>

        </span>
    </c:if>
    <c:if test="${param.error != null}">
        <span class="loginFailed">Login failed</span>
    </c:if>

    <c:if test="${param.logout != null}">
        <span class="loginFailed">Wylogowano</span>
    </c:if>
<div class="form-label-group">
<input type="email" name="username" class="form-control" required
           autofocus>
    <label for="username">Email</label>

</div>
<div class="form-label-group">

<input type="password" name="password" class="form-control"
           required>
    <label for="password">Hasło</label>

</div>
    <a data-toggle="modal" href="#passResetModal">Reset hasła</a>

    <button class="btn btn-lg btn-outline-secondary btn-block" type="submit">Zaloguj</button>
</form:form>


<div class="modal fade" id="passResetModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="passResetModalLabel">Resetuj hasło</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form id="resetPassForm" class="form-inline"
                           action="${pageContext.request.contextPath}/resetPassword" method="POST">
                    <div class="form-group">
                        <input type="email" name="email" class="form-control"
                               placeholder="Email" required autofocus>
                        <button class="btn btn-outline-secondary" type="submit">Resetuj</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>