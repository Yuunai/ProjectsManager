<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="event.title"/>"</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>
    <script>
        function makeEditable() {
            var x = document.getElementsByClassName("form-control");
            var i;
            for (i = 0; i < x.length; i++) {
                if (x[i].disabled == true) {
                    x[i].disabled = false;
                }
            }
            document.getElementById("updateBtn").style.display = "inline";
        }
    </script>
</head>
<body>

<%@include file="header.jsp" %>
<%--TODO add reservations table below participations table--%>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="makeEditable()">
                <fmt:message key="event.edit"/>
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/reservation/addReservationForm?eventId=${event.id}'">
                <fmt:message key="event.reserve"/>
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/home'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                <fmt:message key="event.back"/>
            </button>
        </div>
    </div>
    <h2><fmt:message key="event.header"/></h2>

    <div class="container-fluid justify-content-center">
        <%--TODO action edit form--%>
        <form:form action="addEvent" modelAttribute="event" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="lastUpdate"/>

            <c:if test="${!empty message}">
                <div class="row py-4">
                    <div class="col-12">
                        <span class="text-center"><fmt:message key="${message}"/></span>
                    </div>
                </div>
            </c:if>
            <div id="errorRow">
                <span id="errors"><form:errors/></span>
            </div>
            <script>
                hideEmptyErrorsRow();
            </script>

            <div class="row py-4">
                <div class="col-12 col-md-4">
                    <label class="form-header" for="nameEvent"><fmt:message key="event.eventName"/></label>
                    <form:input path="name" id="nameEvent" type="text" class="form-control" value="${event.name}"
                                disabled="true"/>
                    <label class="form-note" for="nameEvent"><form:errors path="name"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="placeEvent"><fmt:message key="event.eventPlace"/></label>
                    <form:input path="place" id="placeEvent" type="text" class="form-control" value="${event.place}"
                                disabled="true"/>
                    <label class="form-note" for="placeEvent"><form:errors path="place"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateEvent"><fmt:message key="event.eventDate"/></label>
                    <form:input path="date" id="dateEvent" type="date" class="form-control" value="${event.date}"
                                disabled="true"/>
                    <label class="form-note" for="dateEvent"><form:errors path="date"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="dateEvent"><fmt:message key="event.eventTime"/></label>
                    <form:input path="time" id="dateEvent" type="time" class="form-control" value="${event.time}"
                                disabled="true"/>
                    <label class="form-note" for="dateEvent"><form:errors path="time"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 col-md-4">
                    <label class="form-header" for="orgNameEvent"><fmt:message key="event.eventOrganizator"/></label>
                    <form:input path="organizer" id="orgNameEvent" type="text" class="form-control"
                                value="${event.organizer}" disabled="true"/>
                    <label class="form-note" for="orgNameEvent"><form:errors path="organizer"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="telOrgEvent"><fmt:message key="event.eventPhone"/></label>
                    <form:input path="phoneNumber" id="telOrgEvent" type="text" class="form-control"
                                value="${event.phoneNumber}" disabled="true"/>
                    <label class="form-note" for="telOrgEvent"><form:errors path="phoneNumber"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="emailOrgEvent"><fmt:message key="event.eventEmail"/></label>
                    <form:input path="email" id="emailOrgEvent" type="text" class="form-control" value="${event.email}"
                                disabled="true"/>
                    <label class="form-note" for="emailOrgEvent"><form:errors path="email"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class=" col-12 col-md-11">
                    <label class="form-header" for="emailOrgEvent"><fmt:message key="event.eventComment"/></label>
                    <form:input path="comments" id="commentEvent" type="text" class="form-control"
                                value="${event.comments}" disabled="true"/>
                    <label class="form-note" for="commentEvent"><form:errors path="comments"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 col-md-1 mr-3">
                    <label class="form-header text-center" for="archEvent"><fmt:message key="event.eventArchive"/></label>
                    <c:choose>
                        <c:when test="${event.archived}">
                            <form:checkbox path="archived" id="archEvent" class="form-control" disabled="true"
                                           value="${event.archived}" checked="checked"/>
                        </c:when>

                        <c:otherwise>
                            <form:checkbox path="archived" id="archEvent" class="form-control" disabled="true"
                                           value="${event.archived}"/>
                        </c:otherwise>
                     </c:choose>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="prioEvent"><fmt:message key="event.eventPriority"/></label>
                    <form:input path="priority" max="10" min="0" id="prioEvent" type="number" class="form-control"
                                value="${event.priority}" disabled="true"/>
                    <label class="form-note" for="prioEvent"><form:errors path="priority"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="deadlineEvent"><fmt:message key="event.eventDeadline"/></label>
                    <form:input path="deadline" id="deadlineEvent" type="date" class="form-control"
                                value="${event.deadline}" disabled="true"/>
                    <label class="form-note" for="deadlineEvent"><form:errors path="deadline"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="typeEvent"><fmt:message key="event.eventType"/></label>
                    <form:input path="videoType" id="typeEvent" type="text" class="form-control"
                                value="${event.videoType}" disabled="true"/>
                    <label class="form-note" for="typeEvent"><form:errors path="videoType"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class=" col-4">
                    <button id="updateBtn" class="btn btn-lg btn-secondary btn-block" style="display: none"
                            type="submit"><fmt:message key="event.eventSubmit"/>
                    </button>
                </div>
            </div>


        </form:form>
        <div id="participationsTable" class="table-responsive">
            <h2><fmt:message key="event.header2"/></h2>
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="event.colName"/></th>
                    <th scope="col"><fmt:message key="event.colRole"/></th>
                    <th scope="col"><fmt:message key="event.colAction"/></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty participations}">
                    <tr>
                        <td colspan="3"><fmt:message key="event.noParticipations"/></td>
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
                               onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false"><fmt:message key="event.remove"/>
                            </a>
                    </tr>
                </c:forEach>
                <form:form action="/participation/addParticipation" modelAttribute="participation" method="POST"
                           acceptCharset="utf8">
                    <form:hidden path="eventId"/>
                    <tr>
                        <td>
                            <form:select path="userId" class="form-control">
                                <form:option value="0"><fmt:message key="event.selectUser"/></form:option>
                                <c:forEach items="${users}" var="user">
                                    <form:option value="${user.userId}" label="${user.firstName} ${user.lastName}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:select path="roleId" class="form-control">
                                <form:option value="0"><fmt:message key="event.selectRole"/></form:option>
                                <c:forEach items="${roles}" var="role">
                                    <form:option value="${role.id}" label="${role.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <button class="btn btn-outline-secondary" type="submit"><fmt:message key="event.add"/></button>
                        </td>
                    </tr>
                </form:form>
                </tbody>
            </table>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
