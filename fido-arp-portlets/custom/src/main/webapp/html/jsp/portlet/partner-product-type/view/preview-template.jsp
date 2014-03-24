<%@include file="../init-common.jsp"%>

<input type="hidden" value="${changePartner}" id="backToMain"/>

<form class="validateForm" data-locale="uk_UA">
    <input type="hidden" value="${selPartner}" id="partnerId" />
    ${templateHtml}
</form>