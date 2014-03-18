<%@include file="../../init.jsp"%>

<div id="<portlet:namespace/>addUserForm" style="display: none">
    <fieldset style="width: 300px">
        <form id="<portlet:namespace/>usersForm" action="${usersUrl}" method="POST" class="userContent">
            <div class="">
                <label class="" id="<portlet:namespace/>addUserError"></label>
            </div>

            <div>
                <label for="<portlet:namespace/>firstName">
                    <liferay-ui:message key="user.first.name"/>&#58;&nbsp;&nbsp;*
                </label>

                <div>
                    <input id="<portlet:namespace/>firstName" type="text" name="firstName" required>
                </div>
            </div>
            <div>
                <label for="<portlet:namespace/>middleName">
                    <liferay-ui:message key="user.middle.name"/>&#58;&nbsp;&nbsp;*
                </label>

                <div>
                    <input id="<portlet:namespace/>middleName" type="text" name="middleName" required>
                </div>
            </div>
            <div>
                <label for="<portlet:namespace/>lastName">
                    <liferay-ui:message key="user.last.name"/>&#58;&nbsp;&nbsp;*
                </label>

                <div>
                    <input id="<portlet:namespace/>lastName" type="text" name="lastName" required>
                </div>
            </div>
            <div>
                <label for="<portlet:namespace/>login">
                    <liferay-ui:message key="user.login"/>&#58;&nbsp;&nbsp;*
                </label>

                <div>
                    <input id="<portlet:namespace/>login" type="text" name="login" required>
                </div>
            </div>
            <div>
                <label for="<portlet:namespace/>email">
                    <liferay-ui:message key="user.email"/>&#58;&nbsp;&nbsp;*
                </label>

                <div>
                    <input id="<portlet:namespace/>email" type="text" name="email" required>
                </div>
            </div>
            <div>
                <label>
                    <liferay-ui:message key="user.partners"/>&#58;&nbsp;&nbsp;
                </label>

                <div>
                    <select name="partnerId">
                        <c:forEach items="${partners}" var="partner">
                            <option value="${partner.organizationId}">
                                    ${partner.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div>
                <a href="javascript:void(0);" id="<portlet:namespace/>addUser"><liferay-ui:message key="user.save"/></a>
            </div>
        </form>
    </fieldset>
</div>