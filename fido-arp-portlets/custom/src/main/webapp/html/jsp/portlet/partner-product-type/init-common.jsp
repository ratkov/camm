<%@include file="../init.jsp"%>

<portlet:renderURL var="mainViewUrl" portletMode="view" />
<portlet:actionURL  var="saveProductType" name="saveProductType" />

<portlet:renderURL var="editProduct" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="edit"/>
</portlet:renderURL>
<portlet:renderURL var="changePartner" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="changePartner"/>
</portlet:renderURL>