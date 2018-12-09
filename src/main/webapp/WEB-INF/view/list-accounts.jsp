<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-12-06
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
	<title>Accounts list</title>
</head>
<body>

<div id="container" class="container" >

	<%@include file="header.jsp"%>

	<div>
		<table class="table col-5 table-bordered">
			<thead class="thead-dark">
			<tr>
				<th scope="col" colspan="3" class="text-center">Lista członków</th>
			</tr>
			<c:if test="${!empty message}" >
				<tr>
					<th scope="col" colspan="3" class="text-center">${message}</th>
				</tr>
			</c:if>
			<tr>
				<th scope="col">Email</th>
				<th scope="col">Konto aktywne</th>
				<th scope="col">Akcja</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">

				<c:url var="detailsLink" value="/account/accountDetails">
					<c:param name="userId" value="${user.id}" />
				</c:url>

				<c:url var="editLink" value="/account/editAccount">
					<c:param name="userId" value="${user.id}" />
				</c:url>

				<c:url var="setEnabledLink" value="/account/setEnabled">
					<c:param name="userId" value="${user.id}" />
					<c:param name="enabled" value="${true}" />
				</c:url>

				<tr>
					<td>${user.email}</td>
					<td><c:choose>
						<c:when test="${user.enabled}">
							Tak
						</c:when>

						<c:otherwise>
							Nie
						</c:otherwise>
					</c:choose></td>
					<td>
						<a href="${detailsLink}">Szczegóły</a> |
						<a href="${editLink}">Edytuj</a>
					</td>
				</tr>

			</c:forEach>
			<tr>
				<td colspan="3">
					<a class="btn btn-primary" href="/account/newAccount" role="button">Nowe konto</a>
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div id="footer">

	</div>

</div>


</body>

