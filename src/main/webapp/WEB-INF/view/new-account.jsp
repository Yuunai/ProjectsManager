<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>SpacjaTV Dodaj konto</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>
</head>
<body>


<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
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
				<label class="form-header" for="emailAddAcc">Email</label>
				<form:input path="email" id="emailAddAcc" type="text" class="form-control"
							placeholder="xyz@zyx.pl"/>
				<label class="form-note" for="emailAddAcc"><form:errors path="email"/></label>
			</div>
			<div class="col-12 col-md-4">
				<label class="form-header" for="passAddAcc">Hasło</label>
				<form:password path="password" id="passAddAcc" class="form-control"
							placeholder="Podaj haslo"/>
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
<footer class="footer">
	<div class="footer-copyright text-center py-3">
		<div class="container">

			<ul class="list-unstyled list-inline text-center">
				<li class="list-inline-item flagShadow">
					<a href="#pl"><span class="flag-icon flag-icon-pl"> </span></a>
				</li>
				<li class="list-inline-item flagShadow">
					<a href="#gb"><span class="flag-icon flag-icon-gb"> </span></a>
				</li>
			</ul>
		</div>
		© 2018-2019 Copyright:
		<a href="https://www.facebook.com/SpacjaTv/"> SpacjaTV</a>
	</div>
</footer>

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

