<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="lang" value="${not empty param.language ? param.language : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="lang"/>


<html lang="<fmt:message key="lang.language"/>">
<head>
    <meta charset="utf-8">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/header-style.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/flag-icon.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Maciej Jaskiewicz, Krystian Minta">
    <title><fmt:message key="eq.title"/></title>
</head>
<body>


<%@include file="header.jsp" %>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-6 mt-0">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <div class="btn-toolbar mb-2 mb-md-0">
            <button class="btn btn-outline-secondary" onclick="location.href='/equipment/addEquipmentForm'">
                <span data-feather="plus" style="margin-bottom: 1px;"></span>
                <fmt:message key="eq.addEqBtn"/>
            </button>
        </div>
    </div>

    <h2><fmt:message key="eq.header"/></h2>
    <ul class="nav nav-tabs" id="eqTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link" id="everything-tab" href="${pageContext.request.contextPath}/equipment/list" role="tab" aria-controls="home" aria-selected="true"><fmt:message key="eq.everything"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="video-tab" href="${pageContext.request.contextPath}/equipment/list?categoryId=1" role="tab" aria-controls="video" aria-selected="false"><fmt:message key="eq.video"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="audio-tab" href="${pageContext.request.contextPath}/equipment/list?categoryId=2" role="tab" aria-controls="audio" aria-selected="false"><fmt:message key="eq.audio"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="live-tab" href="${pageContext.request.contextPath}/equipment/list?categoryId=3" role="tab" aria-controls="live" aria-selected="false"><fmt:message key="eq.live"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="accessory-tab" href="${pageContext.request.contextPath}/equipment/list?categoryId=4" role="tab" aria-controls="accessory" aria-selected="false"><fmt:message key="eq.accessories"/></a>
        </li>
    </ul>
    <script>
        var categoryID =0;
        var addrCategoryID = window.location.href.split('&',1).toString();
        if(addrCategoryID.includes('categoryId=')) {
            addrCategoryID = addrCategoryID.substr(addrCategoryID.indexOf('categoryId=')+11,1);
            categoryID = parseInt(addrCategoryID);
        }
        else{
            categoryID = 0;
        }
        switch (categoryID) {
            case 0:
                document.getElementById("everything-tab").className += " active";
                break;
            case 1:
                document.getElementById("video-tab").className += " active";
                break;
            case 2:
                document.getElementById("audio-tab").className += " active";
                break;
            case 3:
                document.getElementById("live-tab").className += " active";
                break;
            case 4:
                document.getElementById("accessory-tab").className += " active";
                break;
        }
    </script>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="everything" role="tabpanel" aria-labelledby="everything-tab">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="eq.colName"/></th>
                        <th scope="col"><fmt:message key="eq.colState"/></th>
                        <th scope="col"><fmt:message key="eq.colComment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${equipment}">

                        <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/equipment/deleteEquipment">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <tr>
                            <td>${item.name}</td>
                            <td>${item.state}</td>
                            <td>${item.comments}</td>
                            <td>
                                <div class="btn-group mr-2">
                                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${updateLink}'">
                                        <fmt:message key="eq.eqUpdate"/>
                                    </button>
                                    <button class="btn btn-sm btn-outline-secondary" data-toggle="confirmation" onclick="location.href='${deleteLink}'">
                                        <fmt:message key="eq.eqDelete"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="tab-pane fade" id="video" role="tabpanel" aria-labelledby="video-tab">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="eq.colName"/></th>
                        <th scope="col"><fmt:message key="eq.colState"/></th>
                        <th scope="col"><fmt:message key="eq.colComment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${equipment}">

                        <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/equipment/deleteEquipment">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <tr>
                            <td>${item.name}</td>
                            <td>${item.state}</td>
                            <td>${item.comments}</td>
                            <td>
                                <div class="btn-group mr-2">
                                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${updateLink}'">
                                        <fmt:message key="eq.eqUpdate"/>
                                    </button>
                                    <button class="btn btn-sm btn-outline-secondary" data-toggle="confirmation" onclick="location.href='${deleteLink}'">
                                        <fmt:message key="eq.eqDelete"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        <div class="tab-pane fade" id="audio" role="tabpanel" aria-labelledby="audio-tab">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="eq.colName"/></th>
                        <th scope="col"><fmt:message key="eq.colState"/></th>
                        <th scope="col"><fmt:message key="eq.colComment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${equipment}">

                        <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/equipment/deleteEquipment">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <tr>
                            <td>${item.name}</td>
                            <td>${item.state}</td>
                            <td>${item.comments}</td>
                            <td>
                                <div class="btn-group mr-2">
                                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${updateLink}'">
                                        <fmt:message key="eq.eqUpdate"/>
                                    </button>
                                    <button class="btn btn-sm btn-outline-secondary" data-toggle="confirmation" onclick="location.href='${deleteLink}'">
                                        <fmt:message key="eq.eqDelete"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        <div class="tab-pane fade" id="live" role="tabpanel" aria-labelledby="live-tab">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="eq.colName"/></th>
                        <th scope="col"><fmt:message key="eq.colState"/></th>
                        <th scope="col"><fmt:message key="eq.colComment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${equipment}">

                        <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/equipment/deleteEquipment">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <tr>
                            <td>${item.name}</td>
                            <td>${item.state}</td>
                            <td>${item.comments}</td>
                            <td>
                                <div class="btn-group mr-2">
                                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${updateLink}'">
                                        <fmt:message key="eq.eqUpdate"/>
                                    </button>
                                    <button class="btn btn-sm btn-outline-secondary" data-toggle="confirmation" onclick="location.href='${deleteLink}'">
                                        <fmt:message key="eq.eqDelete"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        <div class="tab-pane fade" id="accessory" role="tabpanel" aria-labelledby="accessory-tab">
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="eq.colName"/></th>
                        <th scope="col"><fmt:message key="eq.colState"/></th>
                        <th scope="col"><fmt:message key="eq.colComment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${equipment}">

                        <c:url var="updateLink" value="/equipment/updateEquipmentForm">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/equipment/deleteEquipment">
                            <c:param name="itemId" value="${item.id}" />
                        </c:url>

                        <tr>
                            <td>${item.name}</td>
                            <td>${item.state}</td>
                            <td>${item.comments}</td>
                            <td>
                                <div class="btn-group mr-2">
                                    <button class="btn btn-sm btn-outline-secondary" onclick="location.href='${updateLink}'">
                                        <fmt:message key="eq.eqUpdate"/>
                                    </button>
                                    <button class="btn btn-sm btn-outline-secondary" data-toggle="confirmation" onclick="location.href='${deleteLink}'">
                                        <fmt:message key="eq.eqDelete"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </div>




</main>
<%@include file="footer.jsp" %>
</body>
</html>