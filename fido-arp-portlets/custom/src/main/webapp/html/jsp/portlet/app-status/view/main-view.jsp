<%@include file="../init-common.jsp"%>

<input type="hidden" value="${editAppStatus}" id="editAppStatus"/>
<h1>
    <liferay-ui:message key="partner.status.status.list"/>
</h1>
<c:if test="${not empty error}">
    <div class="alert alert-error"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<div class="clearfix">
    <a href="javascript:void(0);" class="btn btn-primary" id="add-app-status">
        <liferay-ui:message key="global.add" />
    </a>
</div>
<hr />
<div class="panel panel-default">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th><liferay-ui:message key="partner.status.id" /></th>
            <th><liferay-ui:message key="partner.status.code" /></th>
            <th><liferay-ui:message key="partner.status.name" /></th>
            <th><liferay-ui:message key="partner.status.description" /></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${appStatuses}" var="appStatus">
            <tr>
                <td>${appStatus.appStatusId}</td>
                <td>${appStatus.appStatusCode}</td>
                <td>${appStatus.getName(locale)}</td>
                <td>${appStatus.getDescription(locale)}</td>
                <td class="text-right">
                    <a href="javascript:void(0);" class="btn btn-default edit-app-status"
                       data-id="${appStatus.appStatusId}">
                        <i class="glyphicon glyphicon-edit"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>