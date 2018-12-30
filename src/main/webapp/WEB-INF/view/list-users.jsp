<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
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
    <title><fmt:message key="users.title"/></title>
</head>
<body>

<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <%--TODO--%>
    <%--To powinno być if admin--%>
    <%--<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">--%>
        <%--<div class="btn-toolbar mb-2 mb-md-0">--%>
            <%--<button class="btn btn-outline-secondary" onclick="location.href='/user/addUserForm'">--%>
                <%--<span data-feather="plus" style="margin-bottom: 1px;"></span>--%>
                <%--Dodaj użytkownika--%>
            <%--</button>--%>
        <%--</div>--%>
    <%--</div>--%>

    <h2><fmt:message key="users.header"/></h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="users.colName"/></th>
                <th scope="col"><fmt:message key="users.colSurname"/></th>
                <th scope="col"><fmt:message key="users.colActive"/></th>
                <th scope="col"><fmt:message key="users.colPhone"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">

                <c:url var="detailsLink" value="/user/userDetails">
                    <c:param name="userId" value="${user.userId}" />
                </c:url>
                <c:url var="adminDetailsLink" value="/account/accountDetails">
                    <c:param name="userId" value="${user.userId}" />
                </c:url>

                <c:url var="editLink" value="/user/updateUserForm">
                    <c:param name="userId" value="${user.userId}" />
                </c:url>

                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><c:choose>
                        <c:when test="${user.active}">
                            <fmt:message key="users.yes"/>
                        </c:when>

                        <c:otherwise>
                            <fmt:message key="users.no"/>
                        </c:otherwise>
                    </c:choose></td>
                    <td>${user.phoneNumber}</td>
                    <td>
                        <%--TODO--%>
                        <%--if nie admin--%>
                                <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${detailsLink}'">
                                    <fmt:message key="users.detailsBtn"/>
                                </button>
                            <%--else czyli if admin--%>
                        <%--<div class="btn-group mr-2">--%>
                            <%--<button class="btn btn-sm btn-outline-secondary" onclick="location.href='${adminDetailsLink}'">--%>
                                <%--Więcej--%>
                            <%--</button>--%>
                            <%--<button class="btn btn-sm btn-outline-secondary" onclick="location.href='${editLink}'">Aktualizuj</button>--%>
                        <%--</div>--%>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</div>
</div>
<%@include file="footer.jsp" %>

</body>
</html>

