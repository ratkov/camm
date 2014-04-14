<%@include file="../init.jsp"%>

<portlet:actionURL  var="saveQuery" name="saveQuery" />
<portlet:actionURL  var="saveSettings" name="saveSettings" />

<portlet:resourceURL var="jsonURL" id="jsonURL"/>
<portlet:resourceURL var="excelURL" id="excelURL">
    <portlet:param name="excel" value="true"/>
</portlet:resourceURL>

<portlet:renderURL var="mainViewUrl" portletMode="view" windowState="${normal}" />
<portlet:renderURL var="paginator" windowState="${exclusive}" />
<portlet:renderURL var="search" portletMode="view" windowState="${normal}" />
<portlet:renderURL var="queryFormAdd" portletMode="view" windowState="${normal}">
    <portlet:param name="action" value="queryForm"/>
</portlet:renderURL>
<portlet:renderURL var="queryFormEdit" portletMode="view" windowState="${exclusive}">
    <portlet:param name="action" value="queryForm"/>
</portlet:renderURL>