<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="addEvent.title"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>

</head>
<body>


    <%@include file="header.jsp" %>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
                <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/home'">
                    <span data-feather="x" style="margin-bottom: 1px;"></span>
                    <fmt:message key="addEvent.back"/>
                </button>
            </div>
        </div>
        <h2><fmt:message key="addEvent.header"/></h2>

    <div class="container-fluid justify-content-center">
        <form:form action="addEvent" modelAttribute="event" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="archived"/>
            <form:hidden path="lastUpdate"/>

            <c:if test="${!empty message}">
                <div class="row py-4">
                    <div class="col-12">
                        <span class="text-center">${message}</span>
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
                    <label class="form-header" for="nameEvent"><fmt:message key="addEvent.eventName"/></label>
                    <fmt:message key="addEvent.eventNamePlaceholder" var="namePlaceholder"/>
                    <form:input path="name" id="nameEvent" type="text" class="form-control" placeholder="${namePlaceholder}"/>
                    <label class="form-note" for="nameEvent"><form:errors path="name"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="placeEvent"><fmt:message key="addEvent.eventPlace"/></label>
                    <fmt:message key="addEvent.eventPlacePlaceholder" var="placePlaceholder"/>
                    <form:input path="place" id="placeEvent" type="text" class="form-control" placeholder="${placePlaceholder}"/>
                    <label class="form-note" for="placeEvent"><form:errors path="place"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateEvent"><fmt:message key="addEvent.eventDate"/></label>
                    <form:input path="date" id="dateEvent" type="date" class="form-control"/>
                    <label class="form-note" for="dateEvent"><form:errors path="date"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="dateEvent"><fmt:message key="addEvent.eventTime"/></label>
                    <form:input path="time" id="dateEvent" type="time" class="form-control"/>
                    <label class="form-note" for="dateEvent"><form:errors path="time"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 col-md-4">
                    <label class="form-header" for="orgNameEvent"><fmt:message key="addEvent.eventOrganizator"/></label>
                    <fmt:message key="addEvent.eventOrganizatorPlaceholder" var="organizatorPlaceholder"/>
                    <form:input path="organizer" id="orgNameEvent" type="text" class="form-control" placeholder="${organizatorPlaceholder}"/>
                    <label class="form-note" for="orgNameEvent"><form:errors path="organizer"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="telOrgEvent"><fmt:message key="addEvent.eventPhone"/></label>
                    <fmt:message key="addEvent.eventPhonePlaceholder" var="phonePlaceholder"/>
                    <form:input path="phoneNumber" id="telOrgEvent" type="text" class="form-control" placeholder="${phonePlaceholder}"/>
                    <label class="form-note" for="telOrgEvent"><form:errors path="phoneNumber"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="emailOrgEvent"><fmt:message key="addEvent.eventEmail"/></label>
                    <fmt:message key="addEvent.eventEmailPlaceholder" var="emailPlaceholder"/>
                    <form:input path="email" id="emailOrgEvent" type="text" class="form-control" placeholder="${emailPlaceholder}"/>
                    <label class="form-note" for="emailOrgEvent"><form:errors path="email"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class=" col-12 col-md-11">
                    <label class="form-header" for="emailOrgEvent"><fmt:message key="addEvent.eventComment"/></label>
                    <fmt:message key="addEvent.eventCommentPlaceholder" var="commentPlaceholder"/>
                    <form:input path="comments" id="commentEvent" type="text" class="form-control" placeholder="${commentPlaceholder}"/>
                    <label class="form-note" for="commentEvent"><form:errors path="comments"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class="col-12 col-md-2">
                    <label class="form-header" for="prioEvent"><fmt:message key="addEvent.eventPriority"/></label>
                    <form:input path="priority" max="10" min="0" id="prioEvent" type="number" class="form-control"/>
                    <label class="form-note" for="prioEvent"><form:errors path="priority"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="deadlineEvent"><fmt:message key="addEvent.eventDeadline"/></label>
                    <form:input path="deadline" id="deadlineEvent" type="date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="form-control"/>
                    <label class="form-note" for="deadlineEvent"><form:errors path="deadline"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="typeEvent"><fmt:message key="addEvent.eventType"/></label>
                    <fmt:message key="addEvent.eventTypePlaceholder" var="typePlaceholder"/>
                    <form:input path="videoType" id="typeEvent" type="text" class="form-control" placeholder="${typePlaceholder}"/>
                    <label class="form-note" for="typeEvent"><form:errors path="videoType"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class=" col-4">
                    <button class="btn btn-lg btn-secondary btn-block" type="submit"><fmt:message key="addEvent.eventSubmit"/></button>
                </div>
            </div>


        </form:form>

    </div>
    </main>
    <%@include file="footer.jsp" %>
</body>
</html>
