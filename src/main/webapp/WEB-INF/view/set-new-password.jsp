<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Set new password</title>
</head>
<body>

<c:if test="${message != null}">
	<h3>
		${message}
	</h3>
</c:if>

<form action="${pageContext.request.contextPath}/setNetPassword" method="POST">
	Nowe hasło: <input name="newPassword" type="password"/>
	<input name="token" type="hidden" value="${paramValues.get("token")[0]}" />
	Zatwierdź <input type="submit"/>
</form>

</body>
</html>
