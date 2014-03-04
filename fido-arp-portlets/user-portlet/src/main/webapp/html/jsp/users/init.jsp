<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%--<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>--%>
<%--<%@ taglib uri="http://struts.apache.org/tags-tiles-el" prefix="tiles-el" %>--%>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.DecimalFormat" %><%@
page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %><%@
page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collection" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Currency" %><%@
page import="java.util.Date" %><%@
page import="java.util.Enumeration" %><%@
page import="java.util.GregorianCalendar" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.HashSet" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.LinkedHashSet" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Properties" %><%@
page import="java.util.ResourceBundle" %><%@
page import="java.util.Set" %><%@
page import="java.util.Stack" %><%@
page import="java.util.TimeZone" %><%@
page import="java.util.TreeMap" %><%@
page import="java.util.TreeSet" %>

<%@ page import="javax.portlet.MimeResponse" %><%@
page import="javax.portlet.PortletConfig" %><%@
page import="javax.portlet.PortletContext" %><%@
page import="javax.portlet.PortletException" %><%@
page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.UnavailableException" %><%@
page import="javax.portlet.ValidatorException" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<%@
page import="com.liferay.portlet.announcements.model.AnnouncementsDelivery" %><%@
page import="com.liferay.portlet.announcements.model.AnnouncementsEntryConstants" %><%@
page import="com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryImpl" %><%@
page import="com.liferay.portlet.announcements.service.AnnouncementsDeliveryLocalServiceUtil" %><%@
page import="com.liferay.portlet.rolesadmin.search.OrganizationRoleChecker" %><%@
page import="com.liferay.portlet.rolesadmin.search.UserGroupRoleChecker" %><%@
page import="com.liferay.portlet.rolesadmin.search.UserRoleChecker" %><%@
page import="com.liferay.portlet.rolesadmin.util.RolesAdminUtil" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserGroupGroupChecker" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserGroupSearch" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserGroupSearchTerms" %><%@
page import="com.fidoarp.fidoarpusers.search.GroupDisplayTerms" %><%@
page import="com.fidoarp.fidoarpusers.search.GroupSearch" %><%@
page import="com.fidoarp.fidoarpusers.search.OrganizationDisplayTerms" %><%@
page import="com.fidoarp.fidoarpusers.search.OrganizationSearch" %><%@
page import="com.fidoarp.fidoarpusers.search.OrganizationSearchTerms" %><%@
page import="com.fidoarp.fidoarpusers.search.UserDisplayTerms" %><%@
page import="com.fidoarp.fidoarpusers.search.UserOrganizationChecker" %><%@
page import="com.fidoarp.fidoarpusers.search.UserSearch" %><%@
page import="com.fidoarp.fidoarpusers.search.UserSearchTerms" %><%@
page import="com.fidoarp.fidoarpusers.util.UsersAdminUtil" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="javax.swing.*" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>

<%@ page import="com.liferay.portal.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.portal.security.permission.ResourceActionsUtil" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.util.JS" %>
<%@ page import="com.liferay.portal.util.PortletKeys" %>
<%@ page import="com.liferay.portal.util.PropsValues" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages" %>
<%@ page import="com.liferay.portal.kernel.upload.UploadException" %>
<%@ page import="com.liferay.portal.model.*" %>
<%@ page import="com.liferay.portal.service.*" %>
<%@ page import="com.liferay.portal.*" %>
<%@ page import="com.liferay.portal.security.auth.PrincipalException" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>

<%@ page import="com.fidoarp.fidoarpusers.util.UsersAdminUtil" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.service.permission.*" %>
<%@ page import="com.liferay.portal.model.impl.OrgLaborImpl" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %>

<%
boolean showActiveUserSelect = true;

if (!(portletName.equals(PortletKeys.PASSWORD_POLICIES_ADMIN) || portletName.equals(PortletKeys.PORTAL_SETTINGS) || portletName.equals(PortletKeys.ROLES_ADMIN) || portletName.equals(PortletKeys.SITES_ADMIN) || portletName.equals(PortletKeys.USERS_ADMIN))) {
	showActiveUserSelect = false;
}

boolean filterManageableGroups = true;

boolean filterManageableOrganizations = true;

if (permissionChecker.hasPermission(0, Organization.class.getName(), company.getCompanyId(), ActionKeys.VIEW)) {
	filterManageableOrganizations = false;
}

boolean filterManageableRoles = true;
boolean filterManageableUserGroupRoles = true;
boolean filterManageableUserGroups = true;

if (portletName.equals(PortletKeys.MY_ACCOUNT)) {
	filterManageableGroups = false;
	filterManageableOrganizations = false;
	filterManageableRoles = false;
	filterManageableUserGroupRoles = false;
	filterManageableUserGroups = false;
}
else if (permissionChecker.isCompanyAdmin()) {
	filterManageableGroups = false;
	filterManageableOrganizations = false;
	filterManageableUserGroups = false;
}
%>

<%@ include file="init-ext.jsp" %>