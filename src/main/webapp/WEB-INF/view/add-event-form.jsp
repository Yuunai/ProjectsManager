<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
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
        <form:form action="addEvent" modelAttribute="event" method="POST">

            <form:hidden path="id"/>
            <form:hidden path="archived"/>
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
                    <td><label>Nazwa</label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><label>Miejsce</label></td>
                    <td><form:input path="place" /></td>
                </tr>
                <tr>
                    <td><label>Data(YY-MM-DD HH:MM)</label></td>
                    <td><form:input path="date" />
                    <form:errors path="date"/>
                    </td>
                </tr>
                <tr>
                    <td><label>Organizator</label></td>
                    <td><form:input path="organizer" /></td>
                </tr>
                <tr>
                    <td><label>Number telefonu</label></td>
                    <td><form:input path="phoneNumber" /></td>
                </tr>
                <tr>
                    <td><label>Email</label></td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td><label>Komentarze</label></td>
                    <td><form:input path="comments" /></td>
                </tr>
                <tr>
                    <td><label>Priorytet</label></td>
                    <td><form:input path="priority" /></td>
                </tr>
                <tr>
                    <td><label>Deadline</label></td>
                    <td><form:input path="deadline" /></td>
                </tr>
                <tr>
                    <td><label>Typ nagrania</label></td>
                    <td><form:input path="videoType" /></td>
                </tr>
                <tr>
                    <td><label>Wartosc</label></td>
                    <td><form:input path="value" /></td>
                </tr>

                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Add Event"/></td>
                </tr>
                </tbody>
            </table>

        </form:form>


        <p>
            <a href="${pageContext.request.contextPath}/home">Back to List</a>
        </p>

    </div>

</div>


</body>
</html>
