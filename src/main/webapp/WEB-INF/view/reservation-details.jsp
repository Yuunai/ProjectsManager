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
    <title><fmt:message key="addReservation.title"/></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/jquery.flexdatalist.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-style.css"/>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/add-forms.js"></script>


</head>
<body>


<%@include file="header.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0 ml-auto">
            <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/reservation/list'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                <fmt:message key="addReservation.back"/>
            </button>
        </div>
    </div>
    <h2><fmt:message key="addReservation.header"/></h2>

    <div class="container-fluid justify-content-center">
        <form:form name="addReservation" action="addReservation" modelAttribute="reservation" method="POST"
                   acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="lastUpdate"/>
            <form:hidden path="eventId"/>
            <form:hidden path="userId"/>

            <c:if test="${!empty message}">
                <div class="row py-4">
                    <div class="col-12">
                        <span class="text-center"><fmt:message key="${message}"/></span>
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
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateSince"><fmt:message key="addReservation.fromDate"/></label>
                    <form:input path="dateSince" id="dateSince" type="date" class="form-control" disabled="true"/>
                    <label class="form-note" for="dateSince">
                        <form:errors path="dateSince"/>
                    </label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="timeSince"><fmt:message key="addReservation.fromTime"/></label>
                    <form:input path="timeSince" id="timeSince" type="time" class="form-control" disabled="true"/>
                    <label class="form-note" for="timeSince">
                        <form:errors path="timeSince"/>
                    </label>
                </div>

            </div>
            <div class="row py-4 justify-content-center">
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateTo"><fmt:message key="addReservation.toDate"/></label>
                    <form:input path="dateTo" id="dateTo" type="date" class="form-control" disabled="true"/>
                    <label class="form-note" for="dateTo">
                        <form:errors path="dateTo"/>
                    </label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="timeTo"><fmt:message key="addReservation.toTime"/></label>
                    <form:input path="timeTo" id="timeTo" type="time" class="form-control" disabled="true"/>
                    <label class="form-note" for="timeTo">
                        <form:errors path="timeTo"/>
                    </label>
                </div>
            </div>
            <div class="row py-4 justify-content-center">
                <div class="col-12 col-md-5">
                    <label class="form-header"><fmt:message key="addReservation.eqList"/></label>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                            <tr>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <c:forEach items="${reservation.equipmentList}" var="equipment">
                                        <form:checkbox path="equipmentList" label="${equipment.name}"
                                                       value="${equipment.id}" checked="checked" disabled="true"/>
                                    </c:forEach>
                                </td>
                            </tr>
                            </tbody>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <div class="row py-4 justify-content-center">

                <div class="col-12 col-md-5 text-center">
                    <label class="form-header" for="comm"><fmt:message key="addReservation.comment"/></label>
                    <form:input path="comments" id="comm" type="text" class="form-control" disabled="true"/>
                    <label class="form-note" for="comm">
                        <form:errors path="comments"/>
                    </label>
                </div>
            </div>
        </form:form>
    </div>
</main>
<%@include file="footer.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery.flexdatalist.min.js"></script>
<script>
    $('.flexdatalist').flexdatalist({
        minLength: 1,
        valueProperty: 'id',
        cache: false
    });
</script>
</body>
</html>