<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">

<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/login-page.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title>SpacjaTV Login</title>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">


</head>
<body class="text-center">
<form:form class="form-signin" action="${pageContext.request.contextPath}/authUser" method="POST">
    <img class="mb-4 logopic" src="${pageContext.request.contextPath}/resources/img/STV_350.png" alt="SpacjaTV"></img>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.header"/></h1>
    <c:if test="${param.error != null}">
        <span class="loginFailed"><fmt:message key="login.loginFailed"/></span>
    </c:if>

    <c:if test="${param.logout != null}">
        <span class="loginFailed"><fmt:message key="login.logout"/></span>
    </c:if>
    <input type="email" name="username" class="form-control" placeholder="<fmt:message key="login.email"/>" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="<fmt:message key="login.pass"/>" required>
    <button class="btn btn-lg btn-secondary btn-block" type="submit"><fmt:message key="login.login"/></button>
    <div class="mt-5 mb-3 text-muted">
        <div class="container">

            <ul class="list-unstyled list-inline text-center">
                <li class="list-inline-item flagShadow">
                    <a href="?language=pl"><span class="flag-icon flag-icon-pl"> </span></a>
                </li>
                <li class="list-inline-item flagShadow">
                    <a href="?language=en"><span class="flag-icon flag-icon-gb"> </span></a>
                </li>
            </ul>
        </div>
        © 2018-2019 Copyright:
        <a href="https://www.facebook.com/SpacjaTv/"> SpacjaTV</a>
    </div>
</form:form>
</body>
</html>