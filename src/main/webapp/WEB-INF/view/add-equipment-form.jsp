<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-12-01
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add/Update Equipment Form</title>

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
        <div>
            <form:form action="addEquipment" modelAttribute="equipment" method="POST" acceptCharset="utf8">

                <form:hidden path="id"/>
                <table class="table col-4 table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th class="col text-center" colspan="2"></th>
                    </tr>
                    <tr>
                        <th class="col">Właściwość</th>
                        <th class="col">Wartość</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%--Error field for sql errors--%>
                        <tr><td colspan="2"><form:errors/></td></tr>
                        <tr>
                            <td><label>Nazwa</label></td>
                            <td>
                                <form:input path="name" />
                                <form:errors path="name" />
                            </td>
                        </tr>
                        <tr>
                            <td><label>Stan</label></td>
                            <td>
                                <form:input path="state" />
                                <form:errors path="state" />
                            </td>
                        </tr>
                        <tr>
                            <td><label>Komentarz</label></td>
                            <td>
                                <form:input path="comments" />
                                <form:errors path="comments" />
                            </td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Zapisz przedmiot"/></td>
                        </tr>
                    </tbody>
                </table>

            </form:form>

        </div>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/equipment/list">Wróc do listy</a>
    </p>
</div>


</body>
</html>
