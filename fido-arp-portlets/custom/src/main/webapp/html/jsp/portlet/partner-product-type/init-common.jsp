<%@include file="../init.jsp"%>

<portlet:renderURL var="mainViewUrl" portletMode="view" windowState="${normal}" />
<portlet:actionURL  var="saveProductType" name="saveProductType" />
<portlet:resourceURL var="jsonURL" id="jsonURL"/>

<portlet:renderURL var="editProduct" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="edit"/>
</portlet:renderURL>
<portlet:renderURL var="changePartner" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="changePartner"/>
</portlet:renderURL>
<portlet:renderURL var="changeStatus" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="changeStatus"/>
</portlet:renderURL>
<portlet:renderURL var="previewProduct" windowState="${exclusive}" portletMode="view">
    <portlet:param name="action" value="previewProduct"/>
</portlet:renderURL>