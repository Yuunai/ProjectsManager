<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="home.language"/>">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title><fmt:message key="home.title"/></title>
</head>
<body>

<%@include file="header.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='event/addEventForm'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                <fmt:message key="home.addEventBtn"/>
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary">
                <span data-feather="archive" style="margin-bottom: 1px;"></span>
                <fmt:message key="home.archiveBtn"/>
            </button>
        </div>
    </div>

    <h2><fmt:message key="home.header"/></h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="home.colEventName"/></th>
                <th scope="col"><fmt:message key="home.colEventDate"/></th>
                <th scope="col"><fmt:message key="home.colEventPlace"/></th>
                <th scope="col"><fmt:message key="home.colEventType"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="event" items="${events}">
                <c:url var="eventDetails" value="/event/eventDetails">
                    <c:param name="eventId" value="${event.id}"/>
                </c:url>
                <tr>
                    <td>${event.name}</td>
                    <td>${event.date}</td>
                    <td>${event.place}</td>
                    <td>TODO</td>
                    <td>
                        <div class="btn-group mr-2">
                            <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${eventDetails}'">
                                <fmt:message key="home.eventDetailsBtn"/>
                            </button>
                            <button class="btn btn-sm btn-outline-secondary" data-toggle="modal"
                                    data-target="#joinModal"><fmt:message key="home.eventJoinBtn"/>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<div class="modal fade" id="joinModal" tabindex="-1" role="dialog" aria-labelledby="joinModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="joinModalLabel"><fmt:message key="home.modalHeader"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="home.modalName"/></th>
                            <th scope="col"><fmt:message key="home.modalRole"/></th>
                            <th scope="col"><fmt:message key="home.modalAction"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty participations}">
                            <tr>
                                <td colspan="3"><fmt:message key="home.modalNoParticipations"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="participation" items="${participations}">
                            <c:url var="deleteLink" value="/participation/deleteParticipation">
                                <c:param name="roleId" value="${participation.role.id}"/>
                                <c:param name="userId" value="${participation.user.id}"/>
                                <c:param name="eventId" value="${participation.event.id}"/>
                            </c:url>
                            <tr>
                                    <%--TODO replace user email with first and last names--%>
                                <td>${participation.user.email}</td>
                                <td>${participation.role.name}</td>
                                <td>
                                    <a href="${deleteLink}"
                                       onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false"><fmt:message key="home.modalRemoveParticipation"/>
                                    </a>
                            </tr>
                        </c:forEach>
                        <%--TODO no model participation--%>
                        <%--<form:form action="/participation/addParticipation" modelAttribute="participation" method="POST"--%>
                        <%--acceptCharset="utf8">--%>
                        <%--<form:hidden path="eventId"/>--%>
                        <%--<tr>--%>
                        <%--<td>--%>
                        <%--<form:select path="userId" class="form-control">--%>
                        <%--<form:option value="0" label="Wybierz pracownika"/>--%>
                        <%--<c:forEach items="${users}" var="user">--%>
                        <%--<form:option value="${user.userId}" label="${user.firstName} ${user.lastName}"/>--%>
                        <%--</c:forEach>--%>
                        <%--</form:select>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--<form:select path="roleId" class="form-control">--%>
                        <%--<form:option value="0" label="Wybierz rolę"/>--%>
                        <%--<c:forEach items="${roles}" var="role">--%>
                        <%--<form:option value="${role.id}" label="${role.name}"/>--%>
                        <%--</c:forEach>--%>
                        <%--</form:select>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</form:form>--%>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <%--TODO move submit button to form--%>
                    <button class="btn btn-outline-secondary" type="submit"><fmt:message key="home.modalSubmit"/></button>
                </div>
            </div>
        </div>
    </div>
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
