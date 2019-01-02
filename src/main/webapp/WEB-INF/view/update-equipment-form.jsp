<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang"
       value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="updateEq.title"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>
</head>
<body>


<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <button class="btn btn-outline-secondary"
                    onclick="location.href='${pageContext.request.contextPath}/equipment/list'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                <fmt:message key="updateEq.back"/>
            </button>
        </div>
    </div>
    <h2><fmt:message key="updateEq.header"/></h2>

    <div class="container-fluid justify-content-center">
        <form:form action="updateEquipment" modelAttribute="equipment" method="POST" acceptCharset="utf8">
            <form:hidden path="id"/>
            <form:hidden path="lastUpdate"/>
        <c:if test="${!empty message}">
        <div class="row py-4">
            <div class="col-12">
                <span class="text-center">${message}</span>
            </div>
        </div>
        </c:if>
        <div id="errorRow">
            <span id="errors"><form:errors/></span>
        </div>
        <script>
            hideEmptyErrorsRow();
        </script>

        <div class="row py-4 justify-content-center">
            <div class="col-12 col-md-4">
                <label class="form-header" for="nameupdateEq"><fmt:message key="updateEq.name"/></label>
                <form:input path="name" id="nameupdateEq" type="text" class="form-control"/>
                <label class="form-note" for="nameupdateEq"><form:errors path="name"/></label>
            </div>
            <div class="col-12 col-md-4">
                <label class="form-header" for="stateupdateEq"><fmt:message key="updateEq.state"/></label>
                <form:input path="state" id="stateupdateEq" class="form-control"/>
                <label class="form-note" for="stateupdateEq"><form:errors path="state"/></label>
            </div>
        </div>
        <div class="row py-4 justify-content-center">
            <div class="col-6 col-md-4 text-center">
                <label class="form-header" for="categoryupdateEq"><fmt:message key="updateEq.category"/></label>
                <form:select path="category" id="categoryupdateEq" class="form-control">
                    <c:if test="${equipment.category != null}">
                        <form:option value="${equipment.category.id}"
                                     label="${equipment.category.name}"/>
                    </c:if>
                    <c:forEach items="${categories}" var="category">
                        <form:option value="${category.id}" label="${category.name}"/>
                    </c:forEach>
                </form:select>
                <label class="form-note" for="categoryupdateEq"><form:errors path="category"/></label>
            </div>
        </div>
        <div class="row py-4 justify-content-center">
            <div class="col-12 col-md-8 text-center">
                <label class="form-header" for="commentupdateEq"><fmt:message key="updateEq.comment"/></label>
                <form:input path="comments" id="commentupdateEq" class="form-control"/>
                <label class="form-note" for="commentupdateEq"><form:errors path="comments"/></label>
            </div>
        </div>

        <div class="row py-4 justify-content-center">
            <div class=" col-4">
                <button id="addAccBtn" class="btn btn-lg btn-secondary btn-block"
                        type="submit"><fmt:message key="updateEq.submit"/>
                </button>
            </div>
        </div>
        </form:form>
</main>
<%@include file="footer.jsp" %>
</body>
</html>