<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

<portlet:resourceURL var="usersUrl" id="getUsersUrl">
    <portlet:param name="action" value="addUserAction"/>
</portlet:resourceURL>

<portlet:renderURL var="filterUserURL" windowState="<%=LiferayWindowState.EXCLUSIVE.toString()%>" portletMode="view">
    <portlet:param name="action" value="filterUser"/>
</portlet:renderURL>