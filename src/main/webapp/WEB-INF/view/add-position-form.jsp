<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-12-02
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add position</title>

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

    <div class="row justify-content-center">
        <form:form action="addPosition" modelAttribute="position" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="lastUpdate"/>
            <table class="table col-4 table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="2" class="text-center">Edytuj pozycję</th>
                </tr>

                <tr>
                    <th scope="col">Właściwość</th>
                    <th scope="col">Wartość</th>
                </tr>
                </thead>
                <tbody>
                    <%--Error field for sql errors--%>
                <tr><td colspan="2"><form:errors/></td></tr>
                    <c:if test="${!empty message}" >
                        <tr>
                            <th scope="col" colspan="2" class="text-center">${message}</th>
                        </tr>
                    </c:if>
                <tr>
                    <td><label>Nazwa</label></td>
                    <td><form:input path="name" />
                        <form:errors path="name" /></td>
                </tr>
                <tr>
                    <td><label>Maksymalny czas wypożyczenia(dni)</label></td>
                    <td><form:input path="lendingTime" />
                        <form:errors path="lendingTime" /></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Zapisz pozycję"/></td>
                </tr>
                </tbody>
            </table>

        </form:form>


        <p>
            <a href="${pageContext.request.contextPath}/position/list">Wróć do listy</a>
        </p>

    </div>

</div>


</body>
</html>
