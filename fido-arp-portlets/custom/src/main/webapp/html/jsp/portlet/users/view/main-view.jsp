<%@include file="../init-common.jsp" %>

<div class="row-fluid clearfix">
    <label class="col-sm-2 control-label"><liferay-ui:message key="user.partners"/>&#58;</label>
    <input type="hidden" value="${addUser}" id="addUser"/>

    <div id="<portlet:namespace/>partnersBlock" class="col-sm-6">
        <select id="<portlet:namespace/>partners" name="partners" data-url="${filterUserURL}" class="form-control">
            <option value="0"><liferay-ui:message key="user.partners.all"/></option>
            <c:forEach items="${partners}" var="partner">
                <option value="${partner.organizationId}" <c:if test="${selectedPartnerId == partner.organizationId}">selected</c:if>>
                        ${partner.name}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-sm-2">
        <a href="javascript:void(0);" id="<portlet:namespace/>showUserForm" class="btn btn-primary">
            <liferay-ui:message key="user.add"/>
        </a>
    </div>
</div>

<hr/>

<div id="<portlet:namespace/>userTable">
    <%@include file="user-table.jsp" %>
</div>

<div id="<portlet:namespace/>dialog-status" style="display: none"
     title="<liferay-ui:message key="user.change.status"/>&#58;&nbsp;">

    <form id="<portlet:namespace/>statusForm" action="${statusUrl}" method="POST">
        <div class="">
            <label class="" id="<portlet:namespace/>changeStatusError"></label>
        </div>
        <input type="hidden" name="userId" value="" id="<portlet:namespace/>userId">

        <div><input type="radio" name="status" value="0"/> <liferay-ui:message key="user.status.active"/></div>
        <div><input type="radio" name="status" value="1"/> <liferay-ui:message key="user.status.blocked"/></div>
        <div><input type="radio" name="status" value="2"/> <liferay-ui:message key="user.status.disabled"/></div>

    </form>

    <div id="<portlet:namespace/>successContent" style="display: none">
        <liferay-ui:message key="user.status.update.successfully"/>
    </div>

</div>

<div id="<portlet:namespace/>dialog-password" class="change-password" style="display: none"
     title="<liferay-ui:message key="user.change.password"/>&#58;&nbsp;">

    <div class="">
        <label class="" id="<portlet:namespace/>changePasswordError"></label>
    </div>
    <input type="hidden" name="userId" value="" id="<portlet:namespace/>dialog-password-userId">

    <div id="<portlet:namespace/>change-password-info">
        <liferay-ui:message key="user.change.password.info"/>
    </div>

    <div id="<portlet:namespace/>change-password-successContent" style="display: none">
        <liferay-ui:message key="user.change.password.successfully"/>
    </div>

</div>


