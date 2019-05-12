<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
	<title><fmt:message key="newPass.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
</head>
<body>
<script
		src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous">
</script>
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

<main role="main" class="mt-0 ml-2">

	<h2><fmt:message key="newPass.header"/></h2>

	<div class="container-fluid justify-content-center">
		<c:if test="${!empty message}">
		<div class="row py-4">
			<div class="col-12">
				<span class="text-center"><fmt:message key="${message}"/></span>
			</div>
		</div>
		</c:if>
		<form  name="setNewPassword" action="${pageContext.request.contextPath}/setNewPassword" method="POST">
			<div class="row py-4 justify-content-center">
				<div class="col-12 col-md-4">
					<label class="form-header" for="newPassword"><fmt:message key="newPass.password"/></label>
					<fmt:message key="newPass.passwordPlaceholder" var="passPlaceholder"/>
					<input name="newPassword" type="password" id="newPassword" class="form-control"
						   placeholder="${passPlaceholder}"/>
					<label class="form-note" id="errorPass" for="newPassword"><fmt:message key="newPass.passwordRequirements"/></label>
				</div>
				<div class="col-12 col-md-4">
					<label class="form-header" for="newPasswordRepeated"><fmt:message key="newPass.passwordRepeated"/></label>
					<fmt:message key="newPass.passwordPlaceholderRepeated" var="passPlaceholderRepeated"/>
					<input type="password" id="newPasswordRepeated" class="form-control"
						   placeholder="${passPlaceholderRepeated}"/>
					<label class="form-note" id="errorPassRep" for="newPasswordRepeated"></label>

				</div>
				<input name="token" type="hidden" value="${paramValues.get("token")[0]}" />
			</div>
		</form>
<script>
	function validate() {
        var nPassInp = document.getElementById("newPassword");
        var nPassInpRep = document.getElementById("newPasswordRepeated");
		var repLabel = document.getElementById("errorPassRep");

		if (nPassInp.value =="")
		{
            nPassInpRep.value="";
            nPassInp.value="";
            repLabel.innerText = '<fmt:message key="newPass.emptyInput"/>'
		}
		else if (nPassInp.value == nPassInpRep.value)
		{

            var regex = new RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!,.])(?=\\S+$).{8,}$");
            if (regex.test(nPassInp.value)) {
                document.forms['setNewPassword'].submit();
            } else {
                nPassInpRep.value="";
                nPassInp.value="";
                repLabel.innerText = '<fmt:message key="newPass.passwordRequirements"/>'
            }
		}
		else
		{
		    nPassInpRep.value="";
		    nPassInp.value="";
		    repLabel.innerText = '<fmt:message key="newPass.passNotMatch"/>'
		}
    }
</script>
		<div class="row py-4 justify-content-center">
			<div class=" col-4">
				<button id="saveNewPassBtn" class="btn btn-lg btn-secondary btn-block"
						type="button" onclick="validate()"><fmt:message key="newPass.submit"/>
				</button>
			</div>
		</div>

</main>
<%@include file="footer.jsp" %>

</body>
</html>