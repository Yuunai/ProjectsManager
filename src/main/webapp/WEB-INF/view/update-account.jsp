<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-12-08
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Edit account</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/header-style.css" />
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>

</head>
<body>

<div id="container" class="container">

	<%@include file="header.jsp"%>

	<div class="row justify-content-center">
		<form:form action="updateAccount" modelAttribute="user" method="POST" acceptCharset="utf8">

			<form:hidden path="id"/>
			<form:hidden path="lastUpdate"/>
		<table class="table col-4 table-bordered">
			<thead class="thead-dark">
			<tr>
				<th scope="col" colspan="2" class="text-center">Edytuj wypożyczenie</th>
			</tr>

			<tr>
				<th scope="col">Właściwość</th>
				<th scope="col">Wartość</th>
			</tr>
			</thead>
			<tbody>
				<%--Error field for sql errors--%>
			<c:if test="${!empty message}" >
				<tr>
					<th scope="col" colspan="2" class="text-center">${message}</th>
				</tr>
			</c:if>
			<tr id="errorRow"><td colspan="2" id="errors"><form:errors /></td></tr>
			<script>
                hideEmptyErrorsRow();
			</script>
			<tr>
				<td><label>Email</label></td>
				<td>
					<form:input path="email"></form:input>
					<form:errors path="email"></form:errors>
				</td>
			</tr>
			<tr>
				<td><label>Konto aktywne</label></td>
				<td>
					<form:checkbox path="enabled"></form:checkbox>
					<form:errors path="enabled"></form:errors>
				</td>
			</tr>

			<tr>
				<td><label>Uprawnienia</label></td>
				<td>
					<c:forEach items="${user.admRoles}" var="role">
						<form:checkbox path="admRoles" label="${role.label}" value="${role.id}" checked="checked"/>
					</c:forEach>
					<c:forEach items="${admRoles}" var="role">
						<form:checkbox path="admRoles" label="${role.label}" value="${role.id}"/>
					</c:forEach>
					<form:errors path="admRoles"/>
				</td>
			</tr>

			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Zapisz"/></td>
			</tr>
			</tbody>
		</table>
		</form:form>

		<p>
			<a href="${pageContext.request.contextPath}/account/list">Wróć do listy</a>
		</p>

	</div>
</div>

</body>
</html>