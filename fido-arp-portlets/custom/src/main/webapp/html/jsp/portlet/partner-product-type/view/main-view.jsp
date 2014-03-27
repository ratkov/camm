<%@include file="../init-common.jsp"%>

<input type="hidden" value="${editProduct}" id="editProduct"/>
<input type="hidden" value="${changeStatus}" id="changeStatus"/>
<input type="hidden" value="${previewProduct}" id="previewProduct"/>

<h1>
    <liferay-ui:message key="partner.product.type.list"/>
</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger"><liferay-ui:message key="${error}"/></div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info"><liferay-ui:message key="${info}"/></div>
</c:if>
<div class="clearfix">
    <label for="partner" class="col-sm-2 control-label"><liferay-ui:message key="partner.product.type.partner" /></label>
    <div class="col-sm-6">
        <select id="partner" class="form-control" data-url="${changePartner}">
            <c:forEach items="${partners}" var="partner">
                <option value="${partner.organizationId}"
                        <c:if test="${partner.organizationId eq selPartner}">selected="selected"</c:if>>${partner.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-sm-2">
        <a href="javascript:void(0);" class="btn btn-primary" id="add-product-type">
            <liferay-ui:message key="partner.product.type.add" />
        </a>
    </div>
</div>
<hr />
<div class="panel panel-default">
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th><liferay-ui:message key="partner.product.type.template" /></th>
                <th><liferay-ui:message key="partner.product.type.status" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${partnerProductTypes}" var="partnerProductType">
            <tr>
                <td>${partnerProductType.getName(locale)}</td>
                <td>
                    <c:choose>
                        <c:when test="${partnerProductType.status}">
                            <liferay-ui:message key="partner.product.type.active" />
                        </c:when>
                        <c:otherwise>
                            <liferay-ui:message key="partner.product.type.inactive" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-right">
                    <a href="javascript:void(0);" class="btn btn-default previewProduct"
                       data-id="${partnerProductType.productTypeId}">
                        <i class="glyphicon glyphicon-eye-open"></i>
                    </a>
                    <a href="javascript:void(0);" class="btn btn-default editProduct"
                           data-partner="${partnerProductType.organizationId}"
                           data-id="${partnerProductType.productTypeId}">
                        <i class="glyphicon glyphicon-edit"></i>
                    </a>
                    <a href="javascript:void(0);" class="btn btn-default changeStatus"
                       data-id="${partnerProductType.productTypeId}">
                    <c:choose>
                        <c:when test="${partnerProductType.status}">
                            <i class="glyphicon glyphicon-ban-circle"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="glyphicon glyphicon-ok-circle"></i>
                        </c:otherwise>
                    </c:choose>
                    </a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>