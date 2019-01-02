<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta charset="utf-8">
<link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script
        src="https://code.jquery.com/jquery-3.3.1.js"
        integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous">
</script>
<div>

    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th scope="col">Nazwa</th>
                <th scope="col">Data</th>
            </tr>
            </thead>
            <tbody>
            <%--TODO create new entity for it--%>
            <%--TODO Parametrize ?countToDisplay=2--%>
            <c:forEach var="event" items="${events}">
                <c:url var="eventDetails" value="/event/eventDetails">
                    <c:param name="eventId" value="${event.id}"/>
                </c:url>
                <tr>
                    <td>${event.name}</td>
                    <td>${event.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="border-top">
    <div class="btn-toolbar float-right m-2">
        <button class="btn btn-sm btn-outline-secondary" data-toggle="modal"
                data-target="#moreModal">Więcej
        </button>
    </div>
</div>
</div>
<div class="modal fade" id="moreModal" tabindex="-1" role="dialog" aria-labelledby="moreModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="moreModalLabel">Wydarzenia</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Nazwa</th>
                            <th scope="col">Data</th>
                            <th scope="col">Godzina</th>
                            <th scope="col">Miejsce</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="event" items="${events}">
                            <c:url var="eventDetails" value="/event/eventDetails">
                                <c:param name="eventId" value="${event.id}"/>
                            </c:url>
                            <tr>
                                <td>${event.name}</td>
                                <td>${event.date}</td>
                                <td>${event.time}</td>
                                <td>${event.place}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
