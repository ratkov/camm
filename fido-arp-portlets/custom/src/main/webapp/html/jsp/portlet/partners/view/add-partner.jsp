<%@include file="../init-common.jsp" %>

<input type="hidden" id="paginator" value="${paginator}"/>
<h1>
    <liferay-ui:message key="partner.add.new.partner"/>
</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<hr />

<form role="form" id="<portlet:namespace/>partnerForm" action="${savePartner}"
      method="POST" class="validationForm" data-locale="${themeDisplay.locale}">
    <div class="regRow">
        <label for="<portlet:namespace/>name">
            <liferay-ui:message key="partner.new.partner.name"/>&#58;
        </label>

        <div class="enterField">
            <input class="form-control required" id="<portlet:namespace/>name" type="text" name="name">
        </div>
    </div>
    <div class="regRow">
        <label></label>
        <div class="enterField">
            <a href="javascript:void(0);" id="<portlet:namespace/>add-partner" class="btn btn-primary"><liferay-ui:message key="global.save"/></a>
            <a href="${mainViewUrl}" class="btn btn-default"><liferay-ui:message key="global.close"/></a>
        </div>
    </div>
</form>
