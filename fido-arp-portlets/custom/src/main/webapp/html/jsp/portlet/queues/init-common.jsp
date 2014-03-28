<%@include file="../init.jsp"%>

<portlet:actionURL  var="saveQuery" name="saveQuery" />
<portlet:resourceURL var="jsonURL" id="jsonURL"/>

<portlet:renderURL var="queryFormAdd" portletMode="view" windowState="${normal}">
    <portlet:param name="action" value="queryForm"/>
</portlet:renderURL>
<portlet:renderURL var="queryFormEdit" portletMode="view" windowState="${exclusive}">
    <portlet:param name="action" value="queryForm"/>
</portlet:renderURL>