<%@include file="../init-common.jsp"%>

<div id="queues-view">
    <c:choose>
        <c:when test="${empty show}">
            <c:import url="main-view.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="${show}"/>
        </c:otherwise>
    </c:choose>
</div>

