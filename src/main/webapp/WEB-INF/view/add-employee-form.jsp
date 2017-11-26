<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-25
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Add Event</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-event-style.css" />

</head>
<body>

<div id="container" class="container">
    <div id="window-header">
        <h1 id="header">SpacjaTV ERP</h1>
    </div>

    <div class="row justify-content-center">
        <form:form action="saveEmployee" modelAttribute="employee" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="userType"/>
            <form:hidden path="marioDollars"/>
            <form:hidden path="active"/>
            <table class="table col-4 table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="2" class="text-center">Edit Event</th>
                </tr>
                <tr>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Wartość</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><label>Imię</label></td>
                    <td><form:input path="firstName" />
                        <form:errors path="firstName" /></td>
                </tr>
                <tr>
                    <td><label>Nazwisko</label></td>
                    <td><form:input path="lastName" />
                        <form:errors path="lastName" /></td>
                </tr>
                <tr>
                    <td><label>Numer indeksu</label></td>
                    <td><form:input path="studentIndex" />
                        <form:errors path="studentIndex"/>
                    </td>
                </tr>
                <tr>
                    <td><label>Number telefonu</label></td>
                    <td><form:input path="phoneNumber" />
                        <form:errors path="phoneNumber" /></td>
                </tr>
                <tr>
                    <td><label>Email</label></td>
                    <td><form:input path="email" />
                        <form:errors path="email" /></td>
                </tr>
                <tr>
                    <td><label>Wejście do biura</label></td>
                    <td><form:select path="officeEntrance" >
                        <form:option value="1" label="Tak" />
                        <form:option value="0" label="Nie" />
                    </form:select>
                    </td>
                </tr>
                <td><label>Pozycja początkowa</label></td>
                <td><form:select path="position" >
                    <c:forEach var="position" items="${positions}">
                        <form:option value="${position.id}" label="${position.name}"/>
                    </c:forEach>
                </form:select>
                </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Zapisz członka"/></td>
                </tr>
                </tbody>
            </table>

        </form:form>


        <p>
            <a href="${pageContext.request.contextPath}/employee/list">Back to List</a>
        </p>

    </div>

</div>


</body>
</html>

