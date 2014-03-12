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

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.liferay.portal.LocaleException" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFolderConstants" %><%@
page import="com.liferay.portlet.documentlibrary.util.DLUtil" %><%@
page import="com.liferay.portlet.dynamicdatalists.model.DDLRecordSet" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchStructureException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.RequiredStructureException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureDuplicateElementException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureFieldException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureNameException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.StructureXsdException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateNameException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.TemplateScriptException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMStructure" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMTemplate" %><%@
page import="com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureDisplayTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureSearch" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.StructureSearchTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateDisplayTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateSearch" %><%@
page import="com.liferay.portlet.dynamicdatamapping.search.TemplateSearchTerms" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMPermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMStructurePermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.permission.DDMTemplatePermission" %><%@
page import="com.liferay.portlet.dynamicdatamapping.storage.StorageType" %><%@
page import="com.liferay.portlet.dynamicdatamapping.util.DDMXSDUtil" %>
<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="java.text.Format" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.util.JS" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.security.permission.ResourceActionsUtil" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="javax.portlet.PortletMode" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="javax.portlet.PortletRequest" %>
<%@ page import="com.liferay.portal.util.PortletKeys" %>
<%@ page import="com.liferay.portlet.PortletURLFactoryUtil" %>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.GroupConstants" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.Group" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects/>

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

    PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

    String chooseCallback = ParamUtil.getString(request, "chooseCallback");
//    String ddmResource = ParamUtil.getString(request, "ddmResource");
    String ddmResource = "com.liferay.portlet.dynamicdatalists";
    String saveCallback = ParamUtil.getString(request, "saveCallback");
//    String scopeAvailableFields = ParamUtil.getString(request, "scopeAvailableFields");
    String scopeAvailableFields = "Liferay.FormBuilder.AVAILABLE_FIELDS.DDM_STRUCTURE";
//    String scopeStorageType = ParamUtil.getString(request, "scopeStorageType");
    String scopeStorageType = "xml";
    String scopeStructureName = ParamUtil.getString(request, "scopeStructureName");
//    String scopeStructureType = ParamUtil.getString(request, "scopeStructureType");
    String scopeStructureType = "com.liferay.portlet.dynamicdatalists.model.DDLRecordSet";
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