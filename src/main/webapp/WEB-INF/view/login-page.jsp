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
<body class="justify-content-center">
<header class="fixed-top">
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">O nas</h4>
                    <p class="text-muted">System zarządzania projektami studenckimi | Kontakt z administratorem -> </p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Kontakt</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Facebook</a></li>
                        <li><a href="#" class="text-white">Email</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="/" class="navbar-brand d-flex align-items-center">
                <strong>ProjectsManager</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>
<main role="main" class="mt-0">
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
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>