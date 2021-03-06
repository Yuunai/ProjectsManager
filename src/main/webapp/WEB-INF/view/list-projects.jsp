<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
	<title>Projects Manager</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/product/">

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
	</style>
	<!-- Custom styles for this template -->
	<link href="<c:url value="${pageContext.request.contextPath}/resources/css/list-projects.css" />" rel="stylesheet">
</head>
<body>

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
<section class="text-center">
	<div class="container my-3">
		<p>
<security:authorize access="hasRole('ADMIN')">

<button class="btn btn-outline-secondary" onclick="location.href='/project/new'">
				<span data-feather="plus" style="margin-bottom: 1px;"></span>
				Dodaj projekt
			</button>
			<button class="btn btn-outline-secondary" onclick="location.href='/account/list'">
				Konta
			</button>
			<button class="btn btn-outline-secondary" onclick="location.href='/user/list'">
				Użytkownicy
			</button>
</security:authorize>
			<button class="btn btn-outline-secondary" onclick="location.href='/user/userDetails?userId=${sessionScope.get("userId")}'">
				Mój profil
			</button>
		</p>
	</div>
</section>
<div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3">
	<c:forEach var="project" items="${projects}" >
		<c:url var="projectDetails" value="/project/details">
			<c:param name="pid" value="${project.id}"/>
		</c:url>
		<div class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden" onclick="location.href='${projectDetails}'">
			<div class="my-3 p-3">
				<h2 class="display-5">${project.name}</h2>
				<p class="lead">${project.description}</p>
			</div>
			<div class="bg-white shadow-sm mx-auto" style="width: 80%; height: 300px; border-radius: 21px 21px 0 0; overflow-y: scroll;">
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<tbody>
						<c:forEach var="task" items="${project.tasks}" >
							<tr>
								<td>${task.name}</td>
								<td>${task.description.length() > 40 ? task.description.substring(0, 40) + "..."
								: task.description}</td>
								<td>${task.deadline}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div></div>
		</div>
        <c:if test="${project.id %2 ==0}">
        </div>
        <div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3">

        </c:if>
	</c:forEach>

</div>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
</html>

