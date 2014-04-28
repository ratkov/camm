<%@include file="../init.jsp"%>

<portlet:actionURL  var="savePartner" name="savePartner" />
<portlet:actionURL  var="saveSettings" name="saveSettings" />
<portlet:actionURL  var="statusChange" name="statusChange" />

<portlet:renderURL var="paginator" windowState="${exclusive}" />
<portlet:renderURL var="mainViewUrl" portletMode="view" windowState="${normal}" />
<portlet:renderURL var="partnerFormAdd" portletMode="view" windowState="${normal}">
    <portlet:param name="action" value="partnerForm"/>
</portlet:renderURL>