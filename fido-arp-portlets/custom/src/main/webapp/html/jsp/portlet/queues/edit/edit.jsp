<%@include file="../init-common.jsp"%>

<div>
    <h1><liferay-ui:message key="global.edit"/></h1>
    <c:if test="${not empty error}">
        <div class="portlet-msg-error">${error}</div>
    </c:if>
    <c:if test="${not empty info}">
        <div class="portlet-msg-info">${info}</div>
    </c:if>
    <form method="post" id="settings-queue" action="${saveSettings}">
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
            <label></label>
            <div class="enterField">
                <button type="submit" class="btn btn-primary"><liferay-ui:message key="global.save"/></button>
                <a href="${mainViewUrl}" class="checkBtn btn btn-default"><liferay-ui:message key="global.close"/></a>
            </div>
        </div>
    </form>
</div>