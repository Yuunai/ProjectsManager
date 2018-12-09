<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-12-08
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Account Details</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/header-style.css" />

</head>
<body>

<div id="container" class="container">

	<%@include file="header.jsp"%>

	<div >
		<div id="accountDetailsTable">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col" colspan="2" class="text-center">Użytkownik</th>
				</tr>
				<tr>
					<th scope="col">Właściwość</th>
					<th scope="col">Wartość</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td><label>Email</label></td>
					<td>${user.email}</td>
				</tr>
				<tr>
					<td><label>Konto aktywne</label></td>
					<td><c:choose>
						<c:when test="${user.enabled}">
							Tak
						</c:when>

						<c:otherwise>
							Nie
						</c:otherwise>
					</c:choose></td>
				</tr>
				<tr>
					<td><label>Role administracyjne</label></td>
					<td>
						<c:forEach var="admRole" items="${user.admRoles}">
							${admRole.label}
						</c:forEach>
					</td>
				</tr>
				</tbody>
			</table>
		</div>

		<p>
			<a href="${pageContext.request.contextPath}/account/list">Wróc do listy</a>
		</p>
	</div>

</body>
</html>