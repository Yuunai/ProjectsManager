<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.css" />" rel="stylesheet">
    <title>Role list</title>
</head>
<body>

<div id="container" class="container" >

    <div id="window-header">
        <h1 id="header">SpacjaTV ERP</h1>
    </div>
    <div id="role-table-section">
        <table class="table col-4 table-bordered">
            <thead class="thead-dark">
            <th scope="col">Id</th>
            <th scope="col">Nazwa</th>
            <th scope="col">Akcja</th>
            </thead>
            <tbody>
            <c:forEach var="role" items="${roles}">

                <c:url var="updateLink" value="/role/updateRoleForm">
                    <c:param name="roleId" value="${role.id}" />
                </c:url>

                <c:url var="deleteLink" value="/role/deleteRole">
                    <c:param name="roleId" value="${role.id}" />
                </c:url>

                <tr>
                    <td>${role.id}</td>
                    <td>${role.name}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this role?'))) return false">Delete</a>
                    </td>
                </tr>

            </c:forEach>
            <tr>
                <td colspan="3">
                    <a class="btn btn-primary" href="/role/addRoleForm" role="button">Add Role</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div id="footer">

    </div>

</div>


</body>
</html>

