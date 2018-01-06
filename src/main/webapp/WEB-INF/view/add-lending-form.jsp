<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2018-01-06
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add Lending</title>

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
        <form:form action="addLending" modelAttribute="lending" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="since"/>
            <form:hidden path="end"/>
            <form:hidden path="return_time"/>
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
                    <tr><td colspan="2"><form:errors/></td></tr>
                    <tr>
                        <td><label>Wydarzenie</label></td>
                        <td><form:select path="event">
                            <c:if test="${lending.event != null}" >
                                <form:option value="${lending.event.id}" label="${lending.event.name}"/>
                            </c:if>
                            <c:forEach items="${events}" var="event">
                                <form:option value="${event.id}" label="${event.name}"/>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    <tr>
                        <td><label>Wypożyczający</label></td>
                        <td><form:select path="employee">
                            <c:if test="${lending.employee != null}">
                                <form:option value="${lending.employee.id}" label="${lending.employee.firstName} ${lending.employee.lastName}"/>
                            </c:if>
                            <c:forEach items="${employees}" var="employee">
                                <form:option value="${employee.id}" label="${employee.firstName} ${employee.lastName}"/>
                            </c:forEach>
                        </form:select></td>
                    </tr>
                    
                    <tr>
                        <td><label>Przedmioty</label></td>
                        <td>
                            <c:forEach items="${lending.equipmentList}" var="equipment">
                                <form:checkbox path="equipmentList" label="${equipment.name}" value="${equipment.id}"/>
                            </c:forEach>
                            <c:forEach items="${equipmentList}" var="equipment">
                                <form:checkbox path="equipmentList" label="${equipment.name}" value="${equipment.id}"/>
                            </c:forEach>
                        </td>
                    </tr>

                    <tr>
                        <td><label>Komentarze</label></td>
                        <td><form:input path="comments"/></td>
                    </tr>

                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Zapisz wypożyczenie"/></td>
                    </tr>
                </tbody>
            </table>

        </form:form>


        <p>
            <a href="${pageContext.request.contextPath}/lending/list">Wróc do listy</a>
        </p>

    </div>

</div>


</body>
</html>
