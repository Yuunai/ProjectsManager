<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          href="${pageContext.request.contextPath}/resources/css/header-style.css" />
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>

</head>
<body>

<div id="container" class="container">

    <%@include file="header.jsp"%>

    <div class="row justify-content-center">
        <form:form action="addEvent" modelAttribute="event" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="archived"/>
            <form:hidden path="lastUpdate"/>
            <table class="table col-4 table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th scope="col" colspan="2" class="text-center">Edytuj wydarzenie</th>
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
                    <td><label>Nazwa</label></td>
                    <td><form:input path="name" />
                    <form:errors path="name" /></td>
                </tr>
                <tr>
                    <td><label>Miejsce</label></td>
                    <td><form:input path="place" />
                    <form:errors path="place" /></td>
                </tr>
                <tr>
                    <td><label>Data(dd.MM.yyyy hh:mm)</label></td>
                    <td><form:input path="date" />
                    <form:errors path="date"/>
                    </td>
                </tr>
                <tr>
                    <td><label>Organizator</label></td>
                    <td><form:input path="organizer" />
                    <form:errors path="organizer" /></td>
                </tr>
                <tr>
                    <td><label>Numer telefonu</label></td>
                    <td><form:input path="phoneNumber" />
                    <form:errors path="phoneNumber" /></td>
                </tr>
                <tr>
                    <td><label>Email</label></td>
                    <td><form:input path="email" />
                    <form:errors path="email" /></td>
                </tr>
                <tr>
                    <td><label>Komentarze</label></td>
                    <td><form:input path="comments" />
                    <form:errors path="comments" /></td>
                </tr>
                <tr>
                    <td><label>Priorytet</label></td>
                    <td><form:input path="priority" />
                    <form:errors path="priority" /></td>
                </tr>
                <tr>
                    <td><label>Deadline(dd.MM.yyyy hh:mm)</label></td>
                    <td><form:input path="deadline" />
                        <form:errors path="deadline"/></td>
                </tr>
                <tr>
                    <td><label>Typ nagrania</label></td>
                    <td><form:input path="videoType" />
                    <form:errors path="videoType" /></td>
                </tr>
                <tr>
                    <td><label>Wartość</label></td>
                    <td><form:input path="value" />
                    <form:errors path="value" /></td>
                </tr>

                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Zapisz wydarzenie"/></td>
                </tr>
                </tbody>
            </table>

        </form:form>


        <p>
            <a href="${pageContext.request.contextPath}/home">Wróc do listy</a>
        </p>

    </div>

</div>


</body>
</html>
