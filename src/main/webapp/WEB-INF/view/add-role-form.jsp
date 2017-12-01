<%--
  Created by IntelliJ IDEA.
  User: Yuunai
  Date: 2017-11-18
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add/Update Role Form</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
</head>
<body>

<div id="container" class="container">
    <div id="window-header">
        <h1 id="header">SpacjaTV ERP</h1>
    </div>


    <div class="row justify-content-center">
        <div>
            <form:form action="addRole" modelAttribute="role" method="POST" acceptCharset="utf8">

                <form:hidden path="id"/>
                <table class="table col-4 table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th class="col text-center" colspan="2">Edit Role</th>
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
                        <td><label>Nazwa:</label></td>
                        <td><form:input path="name" /></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Save Role"/></td>
                    </tr>
                    </tbody>
                </table>

            </form:form>

        </div>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/role/list">Back to List</a>
    </p>
</div>


</body>
</html>
