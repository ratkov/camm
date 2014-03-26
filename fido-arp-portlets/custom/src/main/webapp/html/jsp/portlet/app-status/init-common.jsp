<%@include file="../init.jsp"%>

<portlet:renderURL var="mainViewUrl" portletMode="view" windowState="${normal}" />
<portlet:actionURL  var="saveAppStatus" name="saveAppStatus" />

<portlet:renderURL var="editAppStatus" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="edit"/>
</portlet:renderURL>