<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="lang"--%>
       <%--value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"--%>
       <%--scope="session"/>--%>
<%--<fmt:setLocale value="${lang}"/>--%>
<%--<fmt:setBundle basename="lang"/>--%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="col-sm-3 col-md-2 mr-0 mx-0 my-1" href="/home"><img src ="${pageContext.request.contextPath}/resources/img/STV_350.png" style="max-width:120px;transform: rotate(350deg);" alt="SpacjaTV"></img></a>
    <button class="navbar-toggler my-1" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/home">
                    <span data-feather="tv"></span>
                    <fmt:message key="nav.events"/><span class="sr-only"></span>
                </a>
            </li>
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/equipment/list">
                    <span data-feather="video"></span>
                    <fmt:message key="nav.eq"/>
                </a>
            </li>
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/reservation/list">
                    <span data-feather="calendar"></span>
                    <fmt:message key="nav.reservations"/>
                </a>
            </li>
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/user/list">
                    <span data-feather="users"></span>
                    <fmt:message key="nav.people"/>
                </a>
            </li>
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/account/list">
                    <span data-feather="users"></span>
                    <fmt:message key="nav.accounts"/>
                </a>
            </li>
            <li class="nav-item text-nowrap">
                <a class="nav-link" href="/user/userDetails?userId=${sessionScope.get("userId")}">
                    <span data-feather="user"></span>
                    <fmt:message key="nav.myAcc"/>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav px-3 ml-auto">
            <li class="nav-item text-nowrap">
                <form:form action="${pageContext.request.contextPath}/logout" method="POST"  id="logout">
                    <a class="nav-link" href="#" onclick="document.getElementById('logout').submit()">
                        <span data-feather="log-out"></span>
                        <fmt:message key="nav.logout"/>
                    </a>
                </form:form>
            </li>
        </ul>

    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span><fmt:message key="nav.sideHeader"/></span>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="users"></span>
                            <fmt:message key="nav.importatntPpl"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="book"></span>
                            <fmt:message key="nav.trainings"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                            <fmt:message key="nav.tutorials"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="https://www.youtube.com/user/SpacjaTVEiT">
                            <span data-feather="youtube"></span>
                            Youtube
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="http://facebook.com/SpacjaTv">
                            <span data-feather="facebook"></span>
                            Facebook
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>