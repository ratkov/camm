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

<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
WindowState windowState = null;
PortletMode portletMode = null;

PortletURL currentURLObj = null;

if (renderRequest != null) {
	windowState = renderRequest.getWindowState();
	portletMode = renderRequest.getPortletMode();

	currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);
}
else if (resourceRequest != null) {
	windowState = resourceRequest.getWindowState();
	portletMode = resourceRequest.getPortletMode();

	currentURLObj = PortletURLUtil.getCurrent(resourceRequest, resourceResponse);
}

String currentURL = currentURLObj.toString();
//String currentURL = PortalUtil.getCurrentURL(request);
%>

<%@ page import="com.liferay.portal.LocaleException" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFolderConstants" %><%@
page import="com.liferay.portlet.documentlibrary.util.DLUtil" %><%@
page import="com.liferay.portlet.dynamicdatalists.model.DDLRecordSet" %><%@
page import="com.fidoarp.dynamicdatamapping.NoSuchStructureException" %><%@
page import="com.fidoarp.dynamicdatamapping.RequiredStructureException" %><%@
page import="com.fidoarp.dynamicdatamapping.StructureDuplicateElementException" %><%@
page import="com.fidoarp.dynamicdatamapping.StructureFieldException" %><%@
page import="com.fidoarp.dynamicdatamapping.StructureNameException" %><%@
page import="com.fidoarp.dynamicdatamapping.StructureXsdException" %><%@
page import="com.fidoarp.dynamicdatamapping.TemplateNameException" %><%@
page import="com.fidoarp.dynamicdatamapping.TemplateScriptException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMStructure" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMTemplate" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants" %><%@
page import="com.fidoarp.dynamicdatamapping.search.StructureDisplayTerms" %><%@
page import="com.fidoarp.dynamicdatamapping.search.StructureSearch" %><%@
page import="com.fidoarp.dynamicdatamapping.search.StructureSearchTerms" %><%@
page import="com.fidoarp.dynamicdatamapping.search.TemplateDisplayTerms" %><%@
page import="com.fidoarp.dynamicdatamapping.search.TemplateSearch" %><%@
page import="com.fidoarp.dynamicdatamapping.search.TemplateSearchTerms" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="javax.swing.*" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>

<%--<%@ page import="com.liferay.portal.util.WebKeys" %>--%>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.portal.security.permission.ResourceActionsUtil" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.util.JS" %>
<%@ page import="com.liferay.portal.util.PortletKeys" %>
<%@ page import="com.liferay.portlet.dynamicdatamapping.storage.StorageType" %>

<%@ page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMPermission" %>

<%
PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

String chooseCallback = ParamUtil.getString(request, "chooseCallback");
String ddmResource = ParamUtil.getString(request, "ddmResource");
String saveCallback = ParamUtil.getString(request, "saveCallback");
String scopeAvailableFields = ParamUtil.getString(request, "scopeAvailableFields");
String scopeStorageType = ParamUtil.getString(request, "scopeStorageType");
String scopeStructureName = ParamUtil.getString(request, "scopeStructureName");
String scopeStructureType = ParamUtil.getString(request, "scopeStructureType");
String scopeTemplateMode = ParamUtil.getString(request, "scopeTemplateMode");
String scopeTemplateType = ParamUtil.getString(request, "scopeTemplateType");
boolean showGlobalScope = ParamUtil.getBoolean(request, "showGlobalScope");
boolean showManageTemplates = ParamUtil.getBoolean(request, "showManageTemplates", true);
boolean showToolbar = ParamUtil.getBoolean(request, "showToolbar", true);

long classNameId = PortalUtil.getClassNameId(scopeStructureType);

String storageTypeValue = StringPool.BLANK;

if (scopeStorageType.equals("expando")) {
	storageTypeValue = StorageType.EXPANDO.getValue();
}
else if (scopeStorageType.equals("xml")) {
	storageTypeValue = StorageType.XML.getValue();
}

String templateHeaderTitle = ParamUtil.getString(request, "templateHeaderTitle");

String templateTypeValue = StringPool.BLANK;

if (scopeTemplateType.equals(DDMTemplateConstants.TEMPLATE_TYPE_DETAIL)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_DETAIL;
}
else if (scopeStorageType.equals(DDMTemplateConstants.TEMPLATE_TYPE_LIST)) {
	templateTypeValue = DDMTemplateConstants.TEMPLATE_TYPE_LIST;
}

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="init-ext.jsp" %>