<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>SpacjaTV Wydarzenie</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
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
        <div class="btn-toolbar mb-2 mb-md-0 mr-3">
            <button class="btn btn-outline-secondary" onclick="location.href='#'">
                Dołącz
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="makeEditable()">
                Edytuj
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/home'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                Wróć
            </button>
        </div>
    </div>
    <h2>Szczegóły wydarzenia</h2>

    <div class="container-fluid justify-content-center">
        <%--TODO action edit form--%>
        <form:form action="addEvent" modelAttribute="event" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
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
                    <label class="form-header" for="nameEvent">Nazwa wydarzenia</label>
                    <form:input path="name" id="nameEvent" type="text" class="form-control" value="${event.name}"
                                disabled="true"/>
                    <label class="form-note" for="nameEvent"><form:errors path="name"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="placeEvent">Miejsce</label>
                    <form:input path="place" id="placeEvent" type="text" class="form-control" value="${event.place}"
                                disabled="true"/>
                    <label class="form-note" for="placeEvent"><form:errors path="place"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateEvent">Termin</label>
                    <form:input path="date" id="dateEvent" type="date" class="form-control" value="${event.date}"
                                disabled="true"/>
                    <label class="form-note" for="dateEvent"><form:errors path="date"/></label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="dateEvent">Godzina</label>
                    <form:input path="time" id="dateEvent" type="time" class="form-control" value="${event.time}"
                                disabled="true"/>
                    <label class="form-note" for="dateEvent"><form:errors path="time"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 col-md-4">
                    <label class="form-header" for="orgNameEvent">Organizator</label>
                    <form:input path="organizer" id="orgNameEvent" type="text" class="form-control"
                                value="${event.organizer}" disabled="true"/>
                    <label class="form-note" for="orgNameEvent"><form:errors path="organizer"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="telOrgEvent">Telefon kontaktowy</label>
                    <form:input path="phoneNumber" id="telOrgEvent" type="text" class="form-control"
                                value="${event.phoneNumber}" disabled="true"/>
                    <label class="form-note" for="telOrgEvent"><form:errors path="phoneNumber"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="emailOrgEvent">Email kontaktowy</label>
                    <form:input path="email" id="emailOrgEvent" type="text" class="form-control" value="${event.email}"
                                disabled="true"/>
                    <label class="form-note" for="emailOrgEvent"><form:errors path="email"/></label>
                </div>
            </div>
            <div class="row py-4">
                <div class=" col-12 col-md-11">
                    <label class="form-header" for="emailOrgEvent">Komentarz</label>
                    <form:input path="comments" id="commentEvent" type="text" class="form-control"
                                value="${event.comments}" disabled="true"/>
                    <label class="form-note" for="commentEvent"><form:errors path="comments"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class="col-12 col-md-2">
                        <%--TODO checkbox not updating--%>
                    <label class="form-header" for="archEvent">Zarchiwizowane</label>
                    <form:checkbox path="archived" id="archEvent" class="form-control" disabled="true"
                                   value="${event.archived}"/>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="prioEvent">Priorytet</label>
                    <form:input path="priority" max="10" min="0" id="prioEvent" type="number" class="form-control"
                                value="${event.priority}" disabled="true"/>
                    <label class="form-note" for="prioEvent"><form:errors path="priority"/></label>
                </div>
                <div class="col-12 col-md-3">
                    <label class="form-header" for="deadlineEvent">Deadline</label>
                    <form:input path="deadline" id="deadlineEvent" type="date" class="form-control"
                                value="${event.deadline}" disabled="true"/>
                    <label class="form-note" for="deadlineEvent"><form:errors path="deadline"/></label>
                </div>
                <div class="col-12 col-md-4">
                    <label class="form-header" for="typeEvent">Typ nagrania</label>
                    <form:input path="videoType" id="typeEvent" type="text" class="form-control"
                                value="${event.videoType}" disabled="true"/>
                    <label class="form-note" for="typeEvent"><form:errors path="videoType"/></label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class=" col-4">
                    <button id="updateBtn" class="btn btn-lg btn-secondary btn-block" style="display: none"
                            type="submit">Uaktualnij
                    </button>
                </div>
            </div>


        </form:form>
        <div id="participationsTable" class="table-responsive">
            <h2>Uczestnicy</h2>
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">Imię i nazwisko</th>
                    <th scope="col">Rola</th>
                    <th scope="col">Akcja</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty participations}">
                    <tr>
                        <td colspan="3">Brak chętnych. Bądź pierwszy!</td>
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
                               onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false">Usuń
                            </a>
                    </tr>
                </c:forEach>
                <form:form action="/participation/addParticipation" modelAttribute="participation" method="POST"
                           acceptCharset="utf8">
                    <form:hidden path="eventId"/>
                    <tr>
                        <td>
                            <form:select path="userId" class="form-control">
                                <form:option value="0" label="Wybierz pracownika"/>
                                <c:forEach items="${users}" var="user">
                                    <form:option value="${user.userId}" label="${user.firstName} ${user.lastName}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <form:select path="roleId" class="form-control">
                                <form:option value="0" label="Wybierz rolę"/>
                                <c:forEach items="${roles}" var="role">
                                    <form:option value="${role.id}" label="${role.name}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td>
                            <button class="btn btn-outline-secondary" type="submit">Dodaj</button>
                        </td>
                    </tr>
                </form:form>
                </tbody>
            </table>
        </div>
    </div>
</main>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</body>