<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Reset password</title>
</head>
<body>

<c:if test="${message != null}">
	<h3>
		${message}
	</h3>
</c:if>

<form action="${pageContext.request.contextPath}/resetPassword" method="POST">
	Email: <input name="email" type="text"/>
	Reset <input type="submit"/>
</form>

</body>
</html>
