<%@include file="../init-common.jsp"%>

<c:if test="${not empty error}">
    <div class="alert alert-error"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<div class="row-fluid">
    <label for="partner" class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.partner" /></label>
    <div class="col-sm-8">
        <select id="partner" class="form-control">
            <c:forEach items="${partners}" var="partner">
                <option value="${partner.organizationId}"
                        <c:if test="${partner.organizationId eq selPartner}">selected</c:if>>${partner.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-sm-2">
        <a href="javascript:void(0);" class="btn btn-primary" id="add-product-type" data-url="${editProduct}">
            <liferay-ui:message key="partner.product.type.add" />
        </a>
    </div>
</div>
<div>

</div>