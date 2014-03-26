<%@include file="../init-common.jsp"%>

<%--<formdata-url="${saveAppStatus}" method="post" data-locale="${themeDisplay.locale}">--%>
<c:if test="${not empty error}">
    <div class="alert alert-error"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<aui:form name="fm" cssClass="validateEditForm" method="POST" data-locale="${themeDisplay.locale}" onSubmit="return false;" action="${saveAppStatus}">
    <aui:fieldset>
        <aui:input type="hidden" id="appStatusId" name="appStatusId" value="${appStatus.appStatusId}" />

        <aui:field-wrapper cssClass="regRow" label="partner.product.type.code">
            <div class="enterField">
                <input type="text" class="required" name="<portlet:namespace/>appStatusCode" value="${appStatus.appStatusCode}" />
            </div>
        </aui:field-wrapper>
        <aui:field-wrapper label="partner.product.type.name" name="appStatusName" cssClass="regRow">
            <liferay-ui:input-localized name="appStatusName" xml="${appStatusName.name}" cssClass="required" />
        </aui:field-wrapper>
        <aui:field-wrapper label='partner.product.type.description' cssClass="regRow" name="appStatusDescription">
            <liferay-ui:input-localized type="textarea" name="appStatusDescription" xml="${appStatusName.description}" cssClass="required" />
        </aui:field-wrapper>
        <div class="regRow">
            <label></label>
            <div class="enterField">
                <button type="submit" class="btn btn-primary submit"><liferay-ui:message key='global.ok'/></button>
                <a href="${mainViewUrl}" class="btn btn-default"><liferay-ui:message key="global.close"/></a>
            </div>
        </div>
    </aui:fieldset>

</aui:form>
