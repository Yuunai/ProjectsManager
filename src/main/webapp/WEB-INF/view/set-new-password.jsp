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
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="col-sm-3 col-md-2 mr-0 mx-0 my-1" href="/home"><img src ="${pageContext.request.contextPath}/resources/img/STV_350.png" style="max-width:120px;transform: rotate(350deg);" alt="SpacjaTV"></img></a>
	<div class="collapse navbar-collapse" id="navbarCollapse">

	</div>
</nav>

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
					<%--TODO wrong pass language versions and validation--%>
					<label class="form-note" id="errorPass" for="newPassword"><fmt:message key="newPass.passwordRequirements"/></label>
				</div>
				<div class="col-12 col-md-4">
					<label class="form-header" for="newPasswordRepeated"><fmt:message key="newPass.passwordRepeated"/></label>
					<fmt:message key="newPass.passwordPlaceholderRepeated" var="passPlaceholderRepeated"/>
					<input type="password" id="newPasswordRepeated" class="form-control"
						   placeholder="${passPlaceholderRepeated}"/>
				</div>
				<input name="token" type="hidden" value="${paramValues.get("token")[0]}" />
			</div>
		</form>

<%--TODO function validating--%>
		<div class="row py-4 justify-content-center">
			<div class=" col-4">
				<button id="saveNewPassBtn" class="btn btn-lg btn-secondary btn-block"
						type="button" onclick="document.forms['setNewPassword'].submit();"><fmt:message key="newPass.submit"/>
				</button>
			</div>
		</div>

</main>
<%@include file="footer.jsp" %>

</body>
</html>