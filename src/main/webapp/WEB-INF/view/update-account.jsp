<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>SpacjaTV Konto</title>
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
                Edytuj
            </button>
        </div>
        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <button class="btn btn-outline-secondary"
                    onclick="location.href='${pageContext.request.contextPath}/account/list'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                Wróć
            </button>
        </div>
    </div>
    <h2>Szczegóły Profilu</h2>

    <div class="container-fluid justify-content-center">
        <form:form action="updateAccount" modelAttribute="user" method="POST" acceptCharset="utf8">
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
                <label class="form-header" for="emailAcc">Email</label>
                <form:input path="email" id="emailAcc" type="text" class="form-control" value="${user.email}"
                            disabled="true"/>
                <label class="form-note" for="emailAcc"><form:errors path="email"/></label>
            </div>
            <div class="col-12 col-md-2">
                <label class="form-header" for="enaabledAcc">Konto aktywne</label>
                <form:checkbox path="enabled" id="enaabledAcc" class="form-control" disabled="true"
                               value="${user.enabled}"/>
            </div>
            <div class="col-12 col-md-2">
                <label class="form-header" for="indexProfile">Uprawnienia</label>
                <div class="form-group">
                    <form:select path="admRoles" id="indexProfile" class="form-control" disabled="true">
                        <c:forEach items="${user.admRoles}" var="role">
                            <form:option value="${role.id}" label="${role.label}" selected="true"/>
                        </c:forEach>
                        <c:forEach items="${admRoles}" var="role">
                            <form:option value="${role.id}" label="${role.label}"/>
                        </c:forEach>
                    </form:select>
                    <label class="form-note" for="indexProfile"><form:errors path="admRoles"/></label>
                </div>
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


</main>
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