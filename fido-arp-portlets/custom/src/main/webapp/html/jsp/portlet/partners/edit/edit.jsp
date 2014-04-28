<%@include file="../init-common.jsp"%>

<div>
    <h1><liferay-ui:message key="global.edit"/></h1>
    <c:if test="${not empty error}">
        <div class="portlet-msg-error">${error}</div>
    </c:if>
    <c:if test="${not empty info}">
        <div class="portlet-msg-info">${info}</div>
    </c:if>
    <form method="post" id="settings-partner" action="${saveSettings}">
        <div class="regRow">
            <label><liferay-ui:message key="global.item.count"/>:</label>
            <div class="enterField selField">
                <select id="itemCount" name="itemCount" class="sel required">
                    <c:forEach begin="5" end="100" step="5" var="itemCountVar">
                        <option value="${itemCountVar}"
                                <c:if test="${itemCountVar eq itemCount}">selected="true"</c:if>>${itemCountVar}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="regRow">
            <label><liferay-ui:message key="partner.default.status"/>:</label>
            <div class="enterField selField">
                <select id="defaultStatus" name="defaultStatus" class="sel required">
                    <option value="0" <c:if test="${defaultStatus eq 0}">selected="true"</c:if>>
                        <liferay-ui:message key="partner.status.ACTIVE"/></option>
                    <option value="1" <c:if test="${defaultStatus eq 1}">selected="true"</c:if>>
                        <liferay-ui:message key="partner.status.BLOCKED"/></option>
                    <option value="2" <c:if test="${defaultStatus eq 2}">selected="true"</c:if>>
                        <liferay-ui:message key="partner.status.DISABLED"/></option>
                </select>
            </div>
        </div>
        <div class="regRow">
            <label><liferay-ui:message key="partner.link.to.orders.page"/>:</label>
            <div class="enterField">
                <select name="orderLink" id="orderLink">
                    <c:forEach items="${layoutList}" var="pageLayout">
                        <option value="${pageLayout.plid}"
                                <c:if test="${pageLayout.plid eq orderLink}">selected="true"</c:if>>${pageLayout.getName(locale)}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="regRow">
            <label></label>
            <div class="enterField">
                <button type="submit" class="btn btn-primary"><liferay-ui:message key="global.save"/></button>
                <a href="${mainViewUrl}" class="checkBtn btn btn-default"><liferay-ui:message key="global.close"/></a>
            </div>
        </div>
    </form>
</div>