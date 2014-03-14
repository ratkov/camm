<%@include file="../init.jsp"%>

<portlet:actionURL  var="saveQuery" name="saveQuery" />


<portlet:renderURL var="createNewQuery" portletMode="view">
    <portlet:param name="action" value="createNewQuery"/>
</portlet:renderURL>