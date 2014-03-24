<%@include file="../init-common.jsp"%>

<input type="hidden" value="${jsonURL}" id="urlResource"/>

<div id="query-form">
    <form class="validateForm" data-locale="uk_UA" data-url="${saveQuery}">
        <input type="hidden" value="${productId}" id="productId" />
        ${templateHtml}
    </form>
</div>
