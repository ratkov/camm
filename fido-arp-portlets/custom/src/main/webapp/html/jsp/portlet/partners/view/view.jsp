<%@include file="../init-common.jsp"%>

<liferay-util:html-bottom>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            window.page = new PartnersProcessor('<portlet:namespace/>');
        });
    </script>
</liferay-util:html-bottom>

<div id="<portlet:namespace/>partner-view">
    <c:choose>
        <c:when test="${empty show}">
            <c:import url="main-view.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="${show}"/>
        </c:otherwise>
    </c:choose>
</div>