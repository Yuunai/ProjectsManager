<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang"
       value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title><fmt:message key="acc.title"/></title>
</head>
<body>

<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='/account/newAccount'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                <fmt:message key="acc.addAcc"/>
            </button>
        </div>
    </div>

    <h2><fmt:message key="acc.header"/></h2>
    <div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="acc.colEmail"/></th>
            <th scope="col"><fmt:message key="acc.colActive"/></th>
            <th scope="col"><fmt:message key="acc.colAction"/></th>
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
                        <fmt:message key="acc.yes"/>
                    </c:when>

                    <c:otherwise>
                        <fmt:message key="acc.no"/>
                    </c:otherwise>
                </c:choose></td>
                <td>
                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${editLink}'">
                        <fmt:message key="acc.details"/>
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
<%@include file="footer.jsp" %>


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