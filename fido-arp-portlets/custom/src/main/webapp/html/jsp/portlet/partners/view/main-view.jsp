<%@include file="../init-common.jsp"%>

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
    <a href="javascript:void(0);" class="btn btn-primary" id="add-app-partner">
        <liferay-ui:message key="global.add" />
    </a>
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
                <td>${partner.name}</td>
                <td><a href="javascript:void(0);" id="<portlet:namespace/>changeStatus">
                    <liferay-ui:message key="partner.status.${partner.status}"/></a>
                </td>
                <td class="text-center">${partner.monthOrderCount}</td>
                <td class="text-center">${partner.viewedOrderCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<div id="<portlet:namespace/>dialog-status" style="display: none"
     title="<liferay-ui:message key="partner.change.status"/>&#58;&nbsp;">

    <form id="<portlet:namespace/>statusForm" action="${statusUrl}" method="POST">
        <div class="">
            <label class="" id="<portlet:namespace/>changeStatusError"></label>
        </div>
        <input type="hidden" name="partnerId" value="" id="<portlet:namespace/>partnerId">

        <div><input type="radio" name="status" value="0"/> <liferay-ui:message key="partner.status.ACTIVE"/></div>
        <div><input type="radio" name="status" value="1"/> <liferay-ui:message key="partner.status.BLOCKED"/></div>
        <div><input type="radio" name="status" value="2"/> <liferay-ui:message key="partner.status.DISABLED"/></div>

    </form>

    <div id="<portlet:namespace/>successContent" style="display: none">
        <liferay-ui:message key="partner.status.update.successfully"/>
    </div>

</div>
