<%@include file="../init-common.jsp"%>

<h1><liferay-ui:message key="partner.product.type.add.edit.product" /></h1>
<aui:form name="fm" cssClass="validateForm" method="POST" data-locale="${themeDisplay.locale}" onSubmit="return false;" action="${saveProductType}">
    <c:if test="${not empty error}">
        <div class="alert alert-danger"><liferay-ui:message key="${error}"/></div>
    </c:if>
    <c:if test="${not empty info}">
        <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
    </c:if>
    <aui:input type="hidden" id="productTypeId" name="productTypeId" value="${productType.productTypeId}" />
    <aui:input type="hidden" id="partnerId" name="partnerId" value="${productType.organizationId}" />
    <aui:field-wrapper cssClass="regRow" label="app.status.code">
        <div class="enterField">
            <input type='text' class="required" name="<portlet:namespace/>productCode" id="<portlet:namespace/>productCode" value="${productType.productTypeCode}"/>
        </div>
    </aui:field-wrapper>
    <aui:field-wrapper label="partner.product.type.name" name="productName" cssClass="regRow">
        <liferay-ui:input-localized name="productName" xml="${productType.name}" cssClass="required"/>
    </aui:field-wrapper>
    <aui:field-wrapper label='partner.product.type.description' cssClass="regRow" name="productDescription">
        <liferay-ui:input-localized name="productDescription" xml="${productType.description}" type="textarea"/>
    </aui:field-wrapper>
    <aui:field-wrapper cssClass="regRow" label="partner.product.type.template">
        <div class="enterField">
            <select id='<portlet:namespace/>productTemplate' name='<portlet:namespace/>productTemplate' class="sel required">
                <c:forEach items="${ddmTemplates}" var="template">
                    <c:choose>
                        <c:when test="${productType.templateId ne 0}">
                            <option value="${template.templateId}" <c:if test="${template.templateId eq productType.templateId}">selected</c:if>>${template.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${template.templateId}">${template.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </aui:field-wrapper>
    <aui:field-wrapper cssClass="regRow" label="partner.product.type.status">
        <div class="enterField">
                <select id='<portlet:namespace/>productStatus' name='<portlet:namespace/>productStatus' class="sel required">
                    <option value="true" <c:if test="${productType.status}">selected</c:if>><liferay-ui:message key="partner.product.type.active"/></option>
                <option value="false" <c:if test="${!productType.status}">selected</c:if>><liferay-ui:message key="partner.product.type.inactive"/></option>
            </select>
        </div>
    </aui:field-wrapper>
    <div class="form-group">
        <label></label>
        <div class="enterField">
            <button type="submit" class="btn btn-primary submit"><liferay-ui:message key="global.ok"/></button>
            <a href="${mainViewUrl}" class="btn btn-default"><liferay-ui:message key="global.close"/></a>
        </div>
    </div>
</aui:form>
