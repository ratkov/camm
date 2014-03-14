<%@include file="../init-common.jsp"%>

<div>

    <div>
        <label><liferay-ui:message key="user.partners"/>&#58;</label>

        <div id="<portlet:namespace/>partnersBlock">
            <select id="<portlet:namespace/>partners" name="partners" data-url="${filterUserURL}">
                <option value="0"><liferay-ui:message key="user.partners.all"/></option>
                <c:forEach items="${partners}" var="partner">
                    <option value="${partner.organizationId}">
                            ${partner.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <a href="javascript:void(0);" id="<portlet:namespace/>showUserForm">
            <div class="add"><liferay-ui:message key="user.add"/></div>
            <div class="cancel" style="display: none"><liferay-ui:message key="user.cancel"/></div>
        </a>
    </div>

    <div id="<portlet:namespace/>userTable">
        <%@include file="user-table.jsp" %>
    </div>

    <%@include file="add-user.jsp" %>

    <div id="<portlet:namespace/>dialog-status" style="display: none" title="<liferay-ui:message key="user.change.status"/>">

        <form id="<portlet:namespace/>statusForm" action="${usersUrl}" method="POST">

            <div><input type="radio" name="status" value="0"/> <liferay-ui:message key="user.status.active"/></div>
            <div><input type="radio" name="status" value="1"/> <liferay-ui:message key="user.status.blocked"/></div>
            <div><input type="radio" name="status" value="2"/> <liferay-ui:message key="user.status.disabled"/></div>

            <div><input type="submit" id="<portlet:namespace/>changeStatus" value="OK"/></div>
        </form>

    </div>
</div>

