<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title>Projects Manager Details</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/list-projects.css" />" rel="stylesheet">
</head>
<body>

<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">O nas</h4>
                    <p class="text-muted">System zarządzania projektami studenckimi | Kontakt z administratorem -> </p>
                    <button class="btn btn-outline-secondary" onclick="location.href='/logout'">
                        Wyloguj się
                    </button>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Kontakt</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Facebook</a></li>
                        <li><a href="#" class="text-white">Email</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="/" class="navbar-brand d-flex align-items-center">
                <strong>ProjectsManager</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>
<section class="text-center">
    <c:url var="newTask" value="/task/new">
        <c:param name="pid" value="${project.id}"/>
    </c:url>
    <div class="container my-3">
        <p>
            <button class="btn btn-outline-secondary" onclick="">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                Edytuj
            </button>
            <button class="btn btn-outline-secondary" onclick="location.href='/'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                Dodaj komentarz
            </button>
        </p>
    </div>
</section>
<div class="container-fluid justify-content-center">
    <form:form action="save" modelAttribute="task" method="POST" acceptCharset="utf8">

        <form:hidden path="id"/>
        <form:hidden path="lastUpdate"/>
        <form:hidden path="project" value="${task.project.id}"/>





        <%--<c:if test="${!empty message}">--%>
        <%--<div class="row py-4">--%>
        <%--<div class="col-12">--%>
        <%--<span class="text-center"><fmt:message key="${message}"/></span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</c:if>--%>
        <div id="errorRow">
            <span id="errors"><form:errors/></span>
        </div>
        <%--<script>--%>
        <%--hideEmptyErrorsRow();--%>
        <%--</script>--%>

        <div class="row py-4 justify-content-center">
            <div class="col-12">
                <label class="form-header" for="name">Nazwa zadania</label>
                <form:input path="name" id="name" type="text" class="form-control" disabled="true"/>
                <label class="form-note" for="name"><form:errors path="name"/></label>
            </div>
        </div>
        <div class="row py-4 justify-content-center">

            <div class="col-12">
                <label class="form-header" for="description">Opis zadania</label>
                <form:input path="description" id="description" type="text" class="form-control" disabled="true"/>
                <label class="form-note" for="description"><form:errors path="description"/></label>
            </div>
        </div>
        <div class="row py-4 justify-content-center">

            <div class="col-12">
                <label class="form-header" for="status">Status zadania</label>
                <form:input path="status" id="status" type="number" class="form-control" disabled="true"/>
                <label class="form-note" for="status"><form:errors path="status"/></label>
            </div>
        </div>
        <div class="row py-4 justify-content-center">
            <div class="col-12 col-md-2">
                <label class="form-header" for="priority">Priorytet</label>
                <form:input path="priority" max="10" min="0" id="priority" type="number" class="form-control" disabled="true"/>
                <label class="form-note" for="priority"><form:errors path="priority"/></label>
            </div>
            <div class="col-12 col-md-3">
                <label class="form-header" for="deadline">Deadline</label>
                <form:input path="deadline" id="deadline" type="date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" class="form-control" disabled="true"/>
                <label class="form-note" for="deadline"><form:errors path="deadline"/></label>
            </div>

        </div>



        <div class="row py-4 justify-content-center">
            <div class=" col-4">
                <button class="btn btn-lg btn-secondary btn-block" type="submit" hidden>Dodaj projekt</button>
            </div>
        </div>

        <h3>Komentarze</h3>
        <c:url var="taskVar" value="/task/details">
            <c:param name="tid" value="${task.id}"/>
        </c:url>
        <c:forEach var="comm" items="${task.comments}">
            <div class="row py-4 justify-content-center">
                <div class="col-12">
                    <input type="text" class="form-control" disabled="true" value="${comm}"/>
                </div>
            </div>


        </c:forEach>


    </form:form>

</div>

</body>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</html>
