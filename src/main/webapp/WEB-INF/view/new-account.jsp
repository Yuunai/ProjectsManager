<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>Dodaj konto</title>
	<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/product/">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>
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
<main role="main" class="mt-0 justify-content-center">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<div class="btn-toolbar mb-2 mb-md-0 ml-auto">
			<%--TODO if id is mine back to home else back to people--%>
			<button class="btn btn-outline-secondary"
					onclick="location.href='${pageContext.request.contextPath}/account/list'">
				<span data-feather="x" style="margin-bottom: 1px;"></span>
				Wróć
			</button>
		</div>
	</div>
	<h2>Dodaj konto</h2>

	<div class="container-fluid justify-content-center">
		<%--TODO wrong redirection if invalid data--%>
		<form:form action="createAccount" modelAttribute="user" method="POST" acceptCharset="utf8">
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
				<label class="form-header" for="emailAddAcc">Email</label>
				<form:input path="email" id="emailAddAcc" type="text" class="form-control"
							placeholder="xyz@zyx.pl"/>
				<label class="form-note" for="emailAddAcc"><form:errors path="email"/></label>
			</div>
			<div class="col-12 col-md-4">
				<label class="form-header" for="passAddAcc">Hasło</label>
				<fmt:message key="addAcc.passwordPlaceholder" var="passPlaceholder"/>
				<form:password path="password" id="passAddAcc" class="form-control"
							placeholder="${passPlaceholder}"/>
				<label class="form-note" for="passAddAcc"><form:errors path="password"/></label>
			</div>
		</div>
		<div class="row py-4">
			<div class="col-12 col-md-2 text-center">
				<label class="form-header" for="activeAcc">Konto aktywne</label>
				<form:checkbox path="enabled" id="activeAcc" class="form-control"/>
			</div>
		</div>

		<div class="row py-4 justify-content-center">
			<div class=" col-4">
				<button id="addAccBtn" class="btn btn-lg btn-secondary btn-block"
						type="submit">Stwórz konto
				</button>
			</div>
		</div>


		</form:form>




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

