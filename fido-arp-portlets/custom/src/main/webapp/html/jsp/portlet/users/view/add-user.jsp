<%@include file="../../init.jsp"%>

<div id="<portlet:namespace/>addUserForm" style="display: none">
    <form role="form" id="<portlet:namespace/>usersForm" action="${usersUrl}" method="POST" class="userContent">
        <div>
            <label id="<portlet:namespace/>addUserError" class="alert alert-danger"></label>
        </div>

        <div class="form-group">
            <label for="<portlet:namespace/>firstName">
                <liferay-ui:message key="user.first.name"/>&#58;&nbsp;&nbsp;*
            </label>

            <div>
                <input class="form-control"  id="<portlet:namespace/>firstName" type="text" name="firstName" required>
            </div>
        </div>
        <div class="form-group">
            <label for="<portlet:namespace/>middleName">
                <liferay-ui:message key="user.middle.name"/>&#58;&nbsp;&nbsp;*
            </label>

            <div>
                <input id="<portlet:namespace/>middleName" type="text" class="form-control" name="middleName" required>
            </div>
        </div>
        <div class="form-group">
            <label for="<portlet:namespace/>lastName">
                <liferay-ui:message key="user.last.name"/>&#58;&nbsp;&nbsp;*
            </label>

            <div>
                <input id="<portlet:namespace/>lastName" type="text" class="form-control" name="lastName" required>
            </div>
        </div>
        <div class="form-group">
            <label for="<portlet:namespace/>login">
                <liferay-ui:message key="user.login"/>&#58;&nbsp;&nbsp;*
            </label>

            <div>
                <input id="<portlet:namespace/>login" type="text" name="login" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="<portlet:namespace/>email">
                <liferay-ui:message key="user.email"/>&#58;&nbsp;&nbsp;*
            </label>

            <div>
                <input id="<portlet:namespace/>email" type="text" name="email" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label>
                <liferay-ui:message key="user.partners"/>&#58;&nbsp;&nbsp;
            </label>

            <div>
                <select name="partnerId" class="form-control">
                    <c:forEach items="${partners}" var="partner">
                        <option value="${partner.organizationId}">
                                ${partner.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <a href="javascript:void(0);" id="<portlet:namespace/>addUser" class="btn btn-primary"><liferay-ui:message key="user.save"/></a>
        </div>
    </form>
</div>