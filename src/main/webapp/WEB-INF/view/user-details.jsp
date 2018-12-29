<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang"
       value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="user.title"/></title>
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
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="makeEditable()">
                <fmt:message key="user.edit"/>
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" data-toggle="modal" data-target="#passResetModal">
                <fmt:message key="user.changePassword"/>
            </button>
        </div>

        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <%--TODO if id is mine back to home else back to people--%>
            <button class="btn btn-outline-secondary"
                    onclick="location.href='${pageContext.request.contextPath}/home'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                <fmt:message key="user.back"/>
            </button>
        </div>
    </div>
    <h2><fmt:message key="user.header"/></h2>

    <div class="container-fluid justify-content-center">
        <%--TODO wrong redirection if invalid data--%>
        <form:form action="updateUser" modelAttribute="details" method="POST" acceptCharset="utf8">
            <form:hidden path="userId"/>
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
                <label class="form-header" for="firstNameProfile"><fmt:message key="user.name"/></label>
                <form:input path="firstName" id="firstNameProfile" type="text" class="form-control"
                            value="${details.firstName}"
                            disabled="true"/>
                <label class="form-note" for="firstNameProfile"><form:errors path="firstName"/></label>
            </div>
            <div class="col-12 col-md-4">
                <label class="form-header" for="lastNameProfile"><fmt:message key="user.surname"/></label>
                <form:input path="lastName" id="lastNameProfile" type="text" class="form-control"
                            value="${details.lastName}"
                            disabled="true"/>
                <label class="form-note" for="lastNameProfile"><form:errors path="lastName"/></label>
            </div>
            <div class="col-12 col-md-2">
                <label class="form-header" for="indexProfile"><fmt:message key="user.index"/>u</label>
                <form:input path="studentIndex" id="indexProfile" type="text" class="form-control"
                            value="${details.studentIndex}"
                            disabled="true"/>
                <label class="form-note" for="indexProfile"><form:errors path="studentIndex"/></label>
            </div>
        </div>
        <div class="row py-4">
            <div class="col-12 col-md-3">
                <label class="form-header" for="phoneProfile"><fmt:message key="user.phone"/></label>
                <form:input path="phoneNumber" id="phoneProfile" type="text" class="form-control"
                            value="${details.phoneNumber}" disabled="true"/>
                <label class="form-note" for="phoneProfile"><form:errors path="phoneNumber"/></label>
            </div>
            <div class="col-12 col-md-2 text-center">
                <label class="form-header" for="officeProfile"><fmt:message key="user.office"/></label>
                <form:checkbox path="officeEntrance" id="officeProfile" class="form-control" disabled="true"
                               value="${details.officeEntrance}"/>
            </div>
            <div class="col-12 col-md-1 text-center">
                <label class="form-header" for="carProfile"><fmt:message key="user.car"/></label>
                <form:checkbox path="car" id="carProfile" class="form-control" disabled="true"
                               value="${details.car}"/>
            </div>
        </div>

        <div class="row py-4 justify-content-center">
            <div class=" col-4">
                <button id="updateBtn" class="btn btn-lg btn-secondary btn-block" style="display: none"
                        type="submit"><fmt:message key="user.update"/>
                </button>
            </div>
        </div>


        </form:form>


        <div id="participationsTable" class="table-responsive">
            <h2><fmt:message key="user.header2"/></h2>
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="user.colEvent"/></th>
                    <th scope="col"><fmt:message key="user.colRole"/></th>
                    <th scope="col"><fmt:message key="users.colActive"/></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty participations}">
                    <tr>
                        <td colspan="3"><fmt:message key="user.noParticipations"/>.</td>
                    </tr>
                </c:if>
                <c:forEach var="participation" items="${participations}">
                    <c:url var="deleteLink" value="/participation/deleteParticipation">
                        <c:param name="roleId" value="${participation.role.id}"/>
                        <c:param name="userId" value="${participation.user.id}"/>
                        <c:param name="eventId" value="${participation.event.id}"/>
                    </c:url>
                    <tr>
                        <td>${participation.event.name}</td>
                        <td>${participation.role.name}</td>
                        <td>
                            <a href="${deleteLink}"
                               onclick="if (!(confirm('Are you sure you want to delete this participation?'))) return false"><fmt:message key="user.remove"/>
                            </a>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

</main>
<footer class="footer">
    <div class="footer-copyright text-center py-3">
        <div class="container">

            <ul class="list-unstyled list-inline text-center">
                <li class="list-inline-item flagShadow">
                    <script>var addresPl = window.location.href + '&language=pl';</script>
                    <a onclick="location.href=addresEn"><span class="flag-icon flag-icon-pl"> </span></a>
                </li>
                <li class="list-inline-item flagShadow">
                    <script>var addresEn = window.location.href + '&language=en';</script>
                    <a onclick="location.href=addresEn"><span class="flag-icon flag-icon-gb"> </span></a>
                </li>
            </ul>
        </div>
        © 2018-2019 Copyright:
        <a href="https://www.facebook.com/SpacjaTv/"> SpacjaTV</a>
    </div>
</footer>
<div class="modal fade" id="passResetModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="passResetModalLabel"><fmt:message key="user.modalHeader"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form id="resetPassForm" class="form-inline"
                           action="${pageContext.request.contextPath}/setNewPassword" method="POST">
                    <div class="form-group">
                        <input type="password" name="newPassword" class="form-control"
                               placeholder="<fmt:message key="user.newPassword"/>" required autofocus>
                        <input name="token" type="hidden" value="${paramValues.get("token")[0]}" />
                        <button class="btn btn-outline-secondary" type="submit"><fmt:message key="user.modalSubmit"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

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
