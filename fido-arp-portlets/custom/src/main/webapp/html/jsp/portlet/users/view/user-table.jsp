<%@include file="../../init.jsp"%>

<div class="panel panel-default">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th><liferay-ui:message key="user.name"/></th>
            <th><liferay-ui:message key="user.login"/></th>
            <th><liferay-ui:message key="user.email"/></th>
            <th>
                <liferay-ui:message key="user.status"/>
                <select id="<portlet:namespace/>filterStatus" class="form-control">
                    <option value="-1" <c:if test="${selectedStatus == -1}">selected</c:if>><liferay-ui:message key="user.status.all"/></option>
                    <option value="0" <c:if test="${selectedStatus == 0}">selected</c:if>><liferay-ui:message key="user.status.ACTIVE"/></option>
                    <option value="1" <c:if test="${selectedStatus == 1}">selected</c:if>><liferay-ui:message key="user.status.BLOCKED"/></option>
                    <option value="2" <c:if test="${selectedStatus == 2}">selected</c:if>><liferay-ui:message key="user.status.DISABLED"/></option>
                </select>
            </th>
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
                <td><a href="javascript:void(0);" id="<portlet:namespace/>changeStatus"><liferay-ui:message key="user.status.${userStatus[user.expandoBridge.attributes['status']]}"/></a></td>
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

    <c:if test="${total gt 1}">
        <div class="panel-footer">
            <ul class="pagination">
                <li <c:if test="${(cpage - 1) eq 0}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage - 1}">&laquo;</a></li>
                <c:forEach begin="1" end="${total}" var="page" step="1">
                    <li <c:if test="${page eq cpage}">class="active"</c:if>>
                        <a href="#" data-page="${page}">${page}</a></li>
                </c:forEach>
                <li <c:if test="${cpage eq total}">class="disabled"</c:if>>
                    <a href="#" data-page="${cpage + 1}">&raquo;</a></li>
            </ul>
        </div>
    </c:if>

    <input type="hidden" id="cpage" value="${cpage}"/>

</div>