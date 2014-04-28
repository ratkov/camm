<%@include file="../init-common.jsp"%>

<input type="hidden" id="<portlet:namespace/>paginator" value="${paginator}"/>
<h1>
    <liferay-ui:message key="partners"/>
</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<div class="clearfix">
    <a href="${partnerFormAdd}" class="btn btn-primary" id="add-app-partner"><liferay-ui:message key="global.add" /></a>
</div>
<hr />

<div class="panel panel-default">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th><liferay-ui:message key="partner.name" /></th>
            <th><liferay-ui:message key="partner.status" /></th>
            <th><liferay-ui:message key="partner.order.count.month" /></th>
            <th><liferay-ui:message key="partner.order.count.by.view" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partners}" var="partner">
            <tr>
                <liferay-portlet:renderURL var="ordersPartner"
                        plid="${orderPlid}"
                        portletName="fido_arp_queues_WAR_custom"
                        portletMode="view">
                    <portlet:param name="partner" value="${partner.id}" />
                </liferay-portlet:renderURL>
                <td><a href="${ordersPartner}" target="_blank">${partner.name}</a></td>
                <td><a href="javascript:void(0);" class="change-status" data-partner="${partner.id}"
                       data-name="${partner.name}" data-status="${partner.status}">
                    <liferay-ui:message key="partner.status.${partnerStatus[partner.status]}"/></a></td>
                <td class="text-center">${partner.monthOrderCount}</td>
                <td class="text-center">${partner.viewedOrderCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${pageCount gt 1}">
        <div class="panel-footer">
            <ul class="pagination">
                <li <c:if test="${(cpage - 1) eq 0}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage - 1}">&laquo;</a></li>
                <c:forEach begin="1" end="${pageCount}" var="page" step="1">
                    <li <c:if test="${page eq cpage}">class="active"</c:if>>
                        <a href="#" data-page="${page}">${page}</a></li>
                </c:forEach>
                <li <c:if test="${cpage eq pageCount}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage + 1}">&raquo;</a></li>
            </ul>
        </div>
    </c:if>

    <input type="hidden" id="cpage" value="${cpage}"/>

</div>

<input type="hidden" id="<portlet:namespace/>title-dialog" value="<liferay-ui:message key="partner.change.status"/>&#58;">
<div id="<portlet:namespace/>dialog-status"></div>

<script type="text/x-handlebars-template" id="<portlet:namespace/>dialog-status-script">

    <form id="<portlet:namespace/>statusForm" action="${statusChange}" method="POST">

        <input type="hidden" name="partnerId" id="<portlet:namespace/>partnerId">

        <div><input type="radio" name="status" value="0" id="status1" checked="checked"/>
            <label for="status1"><liferay-ui:message key="partner.status.ACTIVE"/></label></div>
        <div><input type="radio" name="status" value="1" id="status2"/>
            <label for="status2"><liferay-ui:message key="partner.status.BLOCKED"/></label></div>
        <div><input type="radio" name="status" value="2" id="status3"/>
            <label for="status3"><liferay-ui:message key="partner.status.DISABLED"/></label></div>

        <div class="text-right">
            <input type="submit" class="btn btn-primary" value='<liferay-ui:message key="global.ok"/>' />
        </div>

    </form>

</script>
