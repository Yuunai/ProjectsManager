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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


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
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
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
            <button class="btn btn-outline-secondary" onclick="location.href='${newTask}'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                Dodaj zadanie
            </button>
            <button class="btn btn-outline-secondary" data-toggle="modal"
                    data-target="#editModal">
                Edytuj
            </button>
            <button class="btn btn-outline-secondary" onclick="location.href='/projectRights?projectId=${project.id}'">
                Lista moderatorów
            </button>
        </p>
    </div>
</section>
<main role="main" class="col-12 px-6 mt-0">
    <h1>${project.name}</h1>
    <h4>${project.description}</h4>
    <br>
    <%--<h2>Uczestnicy</h2>--%>
    <%--<c:forEach var="usr" items="${project.users}">--%>
    <%--<span>${usr.email}</span>--%>
    <%--</c:forEach>--%>
    <%--<br>--%>
    <h2>Zadania</h2>
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Deadline</th>
                <th scope="col">Priorytet</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${project.tasks}">
                <c:url var="taskDetails" value="/task/details">
                    <c:param name="tid" value="${task.id}"/>
                </c:url>
                <tr>
                    <td>${task.name}</td>
                    <td>${task.deadline}</td>
                    <td>${task.priority}</td>
                    <td>
                        <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${taskDetails}'">
                            Szczegóły
                        </button>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edytuj projekt</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <form:form action="save" modelAttribute="project" method="POST"
                           acceptCharset="utf8">
                    <form:hidden path="id" value="${project.id}"/>
                    <form:hidden path="lastUpdate"/>
                    <div class="row py-4 justify-content-center">
                        <div class="col-12">
                            <label class="form-header" for="name">Nazwa</label>
                            <form:input path="name" id="name" type="text" class="form-control" value="${project.name}"/>
                            <label class="form-note" for="name"><form:errors path="name"/></label>
                        </div>
                    </div>
                    <div class="row py-4 justify-content-center">
                        <div class="col-12">
                            <label class="form-header" for="name">Opis</label>
                            <form:input path="description" id="name" type="text" class="form-control"
                                        value="${project.description}"/>
                            <label class="form-note" for="name"><form:errors path="description"/></label>
                        </div>
                    </div>
                    <div class="row py-4 justify-content-center">
                        <div class=" col-4">
                            <button class="btn btn-lg btn-secondary btn-block" type="submit">Zapisz</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function hideEmptyErrorsRow() {
        var x = document.getElementById("errors");
        if (x.innerHTML == "" || x.innerHTML == null) {
            document.getElementById("errorRow").style.display = " none";
        }
    }

    function edit() {
        document.getElementById("name").disabled = false;
        document.getElementById("description").disabled = false;
        document.getElementById("status").disabled = false;
        document.getElementById("priority").disabled = false;
        document.getElementById("deadline").disabled = false;
        document.getElementById("saveBtn").hidden = false;

    }

</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</html>
