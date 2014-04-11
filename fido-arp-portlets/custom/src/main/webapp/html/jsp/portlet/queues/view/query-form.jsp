<%@include file="../init-common.jsp"%>

<input type="hidden" value="${jsonURL}" id="urlResource"/>

<div id="query-form">
    <div class="regTitle">
        <label><liferay-ui:message key="queues.query"/> <liferay-ui:message key="queues.#"/> ${appId}</label>
    </div>
    <c:if test="${isProject}">
        <div>
            <a href="#" class="btn btn-default"><liferay-ui:message key="global.save"/></a>
            <a href="#" class="btn btn-default"><liferay-ui:message key="global.download.xml"/></a>
            <a href="#" class="btn btn-default"><liferay-ui:message key="global.send"/></a>
        </div>

        <hr/>
    </c:if>
    <form class="validateForm" data-locale="uk_UA" data-url="${saveQuery}">
        <input type="hidden" value="${productId}" id="productId" name="productId"/>
        <input type="hidden" value="${appId}" id="appId" name="appId"/>
        <input type="hidden" value="${appStatusId}" id="appStatusId" name="appStatusId"/>
        <input type="hidden" value="${cpage}" id="cpage" name="cpage"/>
        ${templateHtml}
    </form>
</div>
