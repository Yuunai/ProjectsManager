<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Szczegóły użytkownika</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/product/">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
            <button class="btn btn-outline-secondary" onclick="makeEditable()">
                Edytuj
            </button>
        </div>
        <c:if test="${pageContext.session.getAttribute('userId')==paramValues.get('userId')[0]}">
            <div class="btn-toolbar mb-2 mb-md-0">
                <button class="btn btn-outline-secondary" data-toggle="modal" data-target="#passResetModal">
                Zmień hasło
                </button>
            </div>
        </c:if>
    </div>
    <h2>Szczegóły</h2>

    <div class="container-fluid justify-content-center">
        <%--TODO wrong redirection if invalid data--%>
        <form:form action="updateUser" modelAttribute="details" method="POST" acceptCharset="utf8">
            <form:hidden path="userId"/>
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
                <label class="form-header" for="firstNameProfile">Imię</label>
                <form:input path="firstName" id="firstNameProfile" type="text" class="form-control"
                            value="${details.firstName}"
                            disabled="true"/>
                <label class="form-note" for="firstNameProfile"><form:errors path="firstName"/></label>
            </div>
            <div class="col-12 col-md-4">
                <label class="form-header" for="lastNameProfile">Nazwisko</label>
                <form:input path="lastName" id="lastNameProfile" type="text" class="form-control"
                            value="${details.lastName}"
                            disabled="true"/>
                <label class="form-note" for="lastNameProfile"><form:errors path="lastName"/></label>
            </div>
        </div>
        <div class="row py-4">
            <div class="col-12 col-md-3">
                <label class="form-header" for="phoneProfile">Numer telefonu</label>
                <form:input path="phoneNumber" id="phoneProfile" type="text" class="form-control"
                            value="${details.phoneNumber}" disabled="true"/>
                <label class="form-note" for="phoneProfile"><form:errors path="phoneNumber"/></label>
            </div>
        </div>

        <div class="row py-4 justify-content-center">
            <div class=" col-4">
                <button id="updateBtn" class="btn btn-lg btn-secondary btn-block" style="display: none"
                        type="submit">Aktualizuj
                </button>
            </div>
        </div>


        </form:form>

</main>
<div class="modal fade" id="passResetModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="passResetModalLabel">Resetuj hasło</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form id="resetPassForm" class="form-inline"
                           action="${pageContext.request.contextPath}/user/setNewPassword" method="POST">
                    <div class="container">
                        <div class="row my-4">
                            <span id="modalMSG"></span>
                        </div>
                        <div class="row my-4">
                            <input type="password" name="password" id="newPassword" class="form-control"
                                   placeholder="Nowe hasło" required autofocus>
                        </div>
                        <div class="row my-4">
                            <input type="password" id="newPasswordRepeated" class="form-control"
                                   placeholder="Powtórz hasło" required autofocus>
                        </div>
                        <div class="row my-4">
                            <button class="btn btn-outline-secondary" type="button" onclick="validateModal()">
                                Zmień</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    function validateModal() {
        var nPassInp = document.getElementById("newPassword");
        var nPassInpRep = document.getElementById("newPasswordRepeated");
        var repLabel = document.getElementById("modalMSG");

        if (nPassInp.value == "") {
            nPassInpRep.value = "";
            nPassInp.value = "";
            repLabel.innerText = 'Hasło nie moze byc puste'
        }
        else if (nPassInp.value == nPassInpRep.value) {

            var regex = new RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!,.])(?=\\S+$).{8,}$");
            if (regex.test(nPassInp.value)) {
                document.forms['resetPassForm'].submit();
            } else {
                nPassInpRep.value = "";
                nPassInp.value = "";
                repLabel.innerText = 'Hasło nie spełnia wymagan'
            }
        }
        else {
            nPassInpRep.value = "";
            nPassInp.value = "";
            repLabel.innerText = 'Hasła nie pasują'
        }
    }
</script>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</html>
