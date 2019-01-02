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
            <button class="btn btn-outline-secondary" onclick="location.href='${pageContext.request.contextPath}/home'">
                <span data-feather="x" style="margin-bottom: 1px;"></span>
                <fmt:message key="addReservation.back"/>
            </button>
        </div>
    </div>
    <h2><fmt:message key="addReservation.header"/></h2>

    <div class="container-fluid justify-content-center">
        <form:form action="addReservation" modelAttribute="reservation" method="POST" acceptCharset="utf8">

            <form:hidden path="id"/>
            <form:hidden path="lastUpdate"/>
            <form:hidden path="eventId"/>
            <form:hidden path="userId"/>

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
            <div class="row py-4">
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateSince"><fmt:message key="addReservation.fromDate"/></label>
                    <form:input path="dateSince" id="dateSince" type="date" class="form-control"/>
                    <label class="form-note" for="dateSince">
                        <form:errors path="dateSince"/>
                    </label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="timeSince"><fmt:message key="addReservation.fromTime"/></label>
                    <form:input path="timeSince" id="timeSince" type="time" class="form-control"/>
                    <label class="form-note" for="timeSince">
                        <form:errors path="timeSince"/>
                    </label>
                </div>

            </div>
            <div class="row py-4">
                <div class="col-12 col-md-3">
                    <label class="form-header" for="dateTo"><fmt:message key="addReservation.toDate"/></label>
                    <form:input path="dateTo" id="dateTo" type="date" class="form-control"/>
                    <label class="form-note" for="dateTo">
                            <form:errors path="dateTo"/>
                    </label>
                </div>
                <div class="col-12 col-md-2">
                    <label class="form-header" for="timeTo"><fmt:message key="addReservation.toTime"/></label>
                    <form:input path="timeTo" id="timeTo" type="time" class="form-control"/>
                    <label class="form-note" for="timeTo">
                            <form:errors path="timeTo"/>
                    </label>
                </div>

            </div>
            <div class="row py-4">

                <div class="col-12 col-md-5">
                    <button class="btn btn-outline-secondary" type="button" onclick="lockReserv()">
                        <fmt:message key="addReservation.lock"/>
                    </button>
                </div>
            </div>
            <div class="row py-4">


                <div class="col-12 col-md-5">
                    <label class="form-header" for="equipmentList"><fmt:message key="addReservation.eqList"/></label>
                    <fmt:message key="addReservation.eqListPlaceholder" var="eqListPlaceholder"/>
                    <fmt:message key="addReservation.eqListNoItems" var="eqListNoItems"/>
                    <form:input path="equipmentList" type='text'
                           placeholder='${eqListPlaceholder}'
                           class='flexdatalist form-control'
                           data-search-in='["name", "category"]'
                           data-visible-properties='["name"]'
                           data-group-by='category'
                           data-selection-required='true'
                           data-focus-first-result='true'
                           data-min-length='1'
                           noResultsText='${eqListNoItems}'
                           data-search-contain='true'
                           multiple='multiple'
                           id='equipmentList'
                           name='equipmentList'/>
                    <label class="form-note" for="equipmentList">
                            <form:errors path="equipmentList"/>
                    </label>
                </div>
            </div>

            <div class="row py-4 justify-content-center">
                <div class=" col-4">
                    <button class="btn btn-lg btn-secondary btn-block" type="submit"><fmt:message key="addReservation.reserveSubmit"/></button>
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
    $('.flexdatalist').flexdatalist('disabled', true);

    function lockReserv(){
        if(document.getElementById('dateSince').disabled == false) {
            document.getElementById('dateSince').disabled = true;
            document.getElementById('timeSince').disabled = true;
            document.getElementById('dateTo').disabled = true;
            document.getElementById('timeTo').disabled = true;
            $('.flexdatalist').flexdatalist('disabled', false);
        }
        else {
            document.getElementById('dateSince').disabled = false;
            document.getElementById('timeSince').disabled = false;
            document.getElementById('dateTo').disabled = false;
            document.getElementById('timeTo').disabled = false;
            $('.flexdatalist').flexdatalist('disabled', true);
        }

        $('.flexdatalist').flexdatalist('data', '${pageContext.request.contextPath}/api/getFreeEquipment?date-since='+
        document.getElementById('dateSince').value+'&time-since='+document.getElementById('timeSince').value+'&date-to='+document.getElementById('dateTo').value+'&time-to='+document.getElementById('timeTo').value);

    };

</script>
</body>
</html>