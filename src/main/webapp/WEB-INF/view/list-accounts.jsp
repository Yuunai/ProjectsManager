<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<html lang="pl">
<head>
    <meta charset="utf-8">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/product/">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title>Konta</title>
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
<main role="main" class="mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='/account/newAccount'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                Dodaj konto
            </button>
        </div>
    </div>

    <h2>Konta</h2>
    <div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Konto aktywne</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">

            <c:url var="detailsLink" value="/account/accountDetails">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <c:url var="editLink" value="/account/editAccount">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <c:url var="setEnabledLink" value="/account/setEnabled">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="enabled" value="${true}"/>
            </c:url>
            <tr>
                <td>${user.email}</td>
                <td><c:choose>
                    <c:when test="${user.enabled}">
                        Tak
                    </c:when>

                    <c:otherwise>
                        Nie
                    </c:otherwise>
                </c:choose></td>
                <td>
                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${editLink}'">
                        Szczegóły
                    </button>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</body>
</html>