<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="window-header" class="navigation-panel">
    <a href="/home" class="menu-button">Strona domowa</a>
    <a href="/user/list" class="menu-button">Członkowie</a>
    <a href="/equipment/list" class="menu-button">Wyposażenie</a>
    <a href="/role/list" class="menu-button">Role</a>
    <a href="/lending/list" class="menu-button">Wypożyczenia</a>
    <c:if test="${sessionScope.get('userId') != null}">
        <a href="/user/userDetails?userId=${sessionScope.get("userId")}" class="menu-button">Profil</a>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" value="Logout">
    </form:form>
</div>

