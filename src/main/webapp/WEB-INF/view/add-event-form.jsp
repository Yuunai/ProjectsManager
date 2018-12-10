<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>SpacjaTV Dodaj Wydarzenie</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
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
                    Wróć
                </button>
            </div>
        </div>
        <h2>Dodaj wydarzenie</h2>

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
                    <form:input path="name" id="nameEvent" type="text" class="form-control" placeholder="Nazwa"/>
                    <label class="form-note" for="nameEvent"><form:errors path="name"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <form:input path="place" id="placeEvent" type="text" class="form-control" placeholder="Miejsce"/>
                    <label class="form-note" for="placeEvent"><form:errors path="place"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <form:input path="date" id="dateEvent" type="date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="form-control" placeholder="Data rrrr-mm-dd"/>
                    <label class="form-note" for="dateEvent"><form:errors path="date"/></label>
                </div>
                <div class="col-12 col-md-1">
                    <form:input path="dateTime" id="dateEvent" type="date" pattern="[0-9]{2}:[0-9]{2}" class="form-control" placeholder="Rozpoczęcie hh:mm"/>
                    <label class="form-note" for="dateEvent"><form:errors path="dateTime"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 col-md-4">
                    <form:input path="organizer" id="orgNameEvent" type="text" class="form-control" placeholder="Organizator"/>
                    <label class="form-note" for="orgNameEvent"><form:errors path="organizer"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <form:input path="phoneNumber" id="telOrgEvent" type="text" class="form-control" placeholder="Tel. Organizatora"/>
                    <label class="form-note" for="telOrgEvent"><form:errors path="phoneNumber"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <form:input path="email" id="emailOrgEvent" type="text" class="form-control" placeholder="Email Organizatora"/>
                    <label class="form-note" for="emailOrgEvent"><form:errors path="email"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class=" col-12 col-md-11">
                    <form:input path="comments" id="commentEvent" type="text" class="form-control" placeholder="Komentarz"/>
                    <label class="form-note" for="commentEvent"><form:errors path="comments"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class="col-12 col-md-2">
                    <form:input path="priority" id="prioEvent" type="number" class="form-control" placeholder="Priorytet"/>
                    <label class="form-note" for="prioEvent"><form:errors path="priority"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <form:input path="deadline" id="deadlineEvent" type="date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="form-control" placeholder="Deadline rrrr-mm-dd"/>
                    <label class="form-note" for="deadlineEvent"><form:errors path="deadline"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <form:input path="videoType" id="typeEvent" type="text" class="form-control" placeholder="Typ nagrania"/>
                    <label class="form-note" for="typeEvent"><form:errors path="videoType"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class=" col-4">
                    <button class="btn btn-lg btn-secondary btn-block" type="submit">Wyślij</button>
                </div>
            </div>


        </form:form>

    </div>
    </main>
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
        feather.replace()
    </script>

</body>
</html>
