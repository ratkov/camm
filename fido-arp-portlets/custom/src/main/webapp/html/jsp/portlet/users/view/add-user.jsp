<div id="<portlet:namespace/>addUserForm" style="display: none">
    <form id="<portlet:namespace/>usersForm" action="${usersUrl}" method="POST" class="userContent">
        <div class="">
            <label class="" id="<portlet:namespace/>addUserError"></label>
        </div>

        <input id="<portlet:namespace/>fullName" type="text" name="fullName" required>
        <input id="<portlet:namespace/>login" type="text" name="login" required>
        <input id="<portlet:namespace/>email" type="text" name="email" required>
        <label><liferay-ui:message key="user.partners"/>&#58;</label>
        <select name="partners">
            <c:forEach items="${partners}" var="partner">
                <option value="${partner.organizationId}">
                        ${partner.name}
                </option>
            </c:forEach>
        </select>

        <div>
            <a href="javascript:void(0);" id="<portlet:namespace/>addUser"><liferay-ui:message key="user.save"/></a>
        </div>
    </form>
</div>