<%@include file="../init.jsp"%>

<portlet:resourceURL var="usersUrl" id="getUsersUrl">
    <portlet:param name="action" value="addUserAction"/>
</portlet:resourceURL>

<portlet:resourceURL var="statusUrl" id="changeStatusUrl">
    <portlet:param name="action" value="changeStatusAction"/>
</portlet:resourceURL>

<portlet:resourceURL var="passwordUrl" id="passwordRecoveryUrl">
    <portlet:param name="action" value="passwordRecoveryAction"/>
</portlet:resourceURL>

<portlet:renderURL var="filterUserURL" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="filterUser"/>
</portlet:renderURL>

<portlet:renderURL var="filterByStatusURL" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="filterByStatus"/>
</portlet:renderURL>

<portlet:renderURL var="addUser" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="addUser"/>
</portlet:renderURL>

<portlet:renderURL var="paginator" windowState="${exclusive}">
    <portlet:param name="action" value="pagination"/>
</portlet:renderURL>

<portlet:renderURL var="mainViewUrl" portletMode="view" windowState="${normal}" />
<portlet:actionURL  var="saveSettings" name="saveSettings" />