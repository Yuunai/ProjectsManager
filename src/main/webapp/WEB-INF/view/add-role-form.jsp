<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add/Update Role Form</title>

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
        <div>
            <form:form action="addRole" modelAttribute="role" method="POST" acceptCharset="utf8">

                <form:hidden path="id"/>
                <form:hidden path="lastUpdate"/>
                <table class="table col-4 table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th class="col text-center" colspan="2">Edytuj rolę</th>
                    </tr>
                    <tr>
                        <th class="col">Właściwość</th>
                        <th class="col">Wartość</th>
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
                        <td><label>Nazwa:</label></td>
                        <td><form:input path="name" /></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Zapisz rolę"/></td>
                    </tr>
                    </tbody>
                </table>

            </form:form>

        </div>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/role/list">Wróć do listy</a>
    </p>
</div>


</body>
</html>
