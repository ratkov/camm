<%@include file="../init.jsp"%>

<portlet:actionURL  var="saveQuery" name="saveQuery" />
<portlet:resourceURL var="jsonURL" id="jsonURL"/>

<portlet:renderURL var="createNewQuery" portletMode="view" windowState="${normal}">
    <portlet:param name="action" value="createNewQuery"/>
</portlet:renderURL>