<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang"
	   value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
	   scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<html>
<head>
	<title>HTTP ${pageContext.response.status}</title>
</head>
<body>

<h2>
	${message}
</h2>

<a href="${pageContext.request.contextPath}/home"><fmt:message key="error.homePage"/></a>

</body>
</html>
