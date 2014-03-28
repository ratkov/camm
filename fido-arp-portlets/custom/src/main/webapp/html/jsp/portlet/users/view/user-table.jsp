<%@include file="../../init.jsp"%>

<input type="hidden" value="${passwordUrl}" id="<portlet:namespace/>passwordUrl">

<div class="panel panel-default">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th><liferay-ui:message key="user.name"/></th>
            <th><liferay-ui:message key="user.login"/></th>
            <th><liferay-ui:message key="user.email"/></th>
            <th><liferay-ui:message key="user.status"/></th>
            <th><liferay-ui:message key="user.password.recovery"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td id="id">${user.userId}</td>
                <td>${user.fullName}</td>
                <td id="login">${user.screenName}</td>
                <td>${user.emailAddress}</td>
                <td><a href="javascript:void(0);" id="<portlet:namespace/>changeStatus">${userStatus[user.expandoBridge.attributes['status']]}</a></td>
                <td><a href="javascript:void(0);" id="<portlet:namespace/>generatePassword">${pswdGenerate}</a></td>
                <td class="text-right">
                    <a href="javascript:void(0);" class="btn btn-default editUser"
                       data-partner="${user.organizationIds[0]}"
                       data-id="${user.userId}">
                        <i class="glyphicon glyphicon-edit"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>