<%@include file="../init-common.jsp"%>

<input type="hidden" value="${changePartner}" id="backToMain"/>
<input type="hidden" value="${jsonURL}" id="urlResource"/>

<form class="validateForm" data-locale="uk_UA">
    <input type="hidden" value="${selPartner}" id="partnerId" />
    ${templateHtml}
</form>