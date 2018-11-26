<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-11-26
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/login-page.css" />" rel="stylesheet">
	<title>Login page</title>
</head>
<body>

<div id="container" class="container">

	<c:if test="${param.error != null}">
		<i class="loginFailed">Niepoprawna nazwa użytkownika lub hasło!</i>
	</c:if>

	<c:if test="${param.logout != null}">
		<i class="loginFailed">Zostałeś wylogowany!</i>
	</c:if>


	<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
		<p>
			Username: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login">
	</form:form>
</div>

</body>
</html>