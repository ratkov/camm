<%@include file="../init-common.jsp"%>

<form class="form-horizontal validateForm" action="${saveProductType}" method="post">
    <c:if test="${not empty error}">
        <div class="alert alert-error"><liferay-ui:message key="${error}"/></div>
    </c:if>
    <c:if test="${not empty info}">
        <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
    </c:if>
    <input type="hidden" id="productTypeId" name="productTypeId" value="${productType.productTypeId}" />
    <input type="hidden" id="partnerId" name="partnerId" value="${productType.organizationId}" />
    <div class="form-group">
        <label class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.code" /></label>
        <div class="col-sm-10">
            <input type='text' class="form-control required" name="productCode" id="productCode" value="${productType.productTypeCode}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.name" /></label>
        <div class="col-sm-10">
            <input type='text' class="form-control required" name="productName" id="productName"  value="${productType.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.description" /></label>
        <div class="col-sm-10">
            <textarea class="form-control" name="productDescription" id="productDescription" value="${productType.description}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.template" /></label>
        <div class="col-sm-10">
            <select id='productTemplate' name='productTemplate' class="form-control required">
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
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.status" /></label>
        <div class="col-sm-10">
            <select id='productStatus' name='productStatus' class="form-control required">
                <c:forEach items="${statuses}" var="status">
                    <c:choose>
                        <c:when test="${productType.status}">
                            <option value="${status}" <c:if test="${status eq productType.status}">selected</c:if>><liferay-ui:message key="${status.label}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${status}"><liferay-ui:message key="${status.label}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary submit"><liferay-ui:message key="global.ok"/></button>
            <a href="${mainViewUrl}" class="btn btn-default"><liferay-ui:message key="global.close"/></a>
        </div>
    </div>
</form>
