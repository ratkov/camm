<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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

<%@ include file="init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

String portletResource = ParamUtil.getString(request, "portletResource");

String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace");

DDMTemplate template = (DDMTemplate)request.getAttribute(WebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE);

long templateId = BeanParamUtil.getLong(template, request, "templateId");

long groupId = BeanParamUtil.getLong(template, request, "groupId", scopeGroupId);

DDMStructure structure = (DDMStructure)request.getAttribute(WebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE);

if ((structure == null) && (template != null)) {
	structure = template.getStructure();
}

long structureId = BeanParamUtil.getLong(structure, request, "structureId");

String mode = BeanParamUtil.getString(template, request, "mode", "create");
String type = BeanParamUtil.getString(template, request, "type", "detail");
String script = BeanParamUtil.getString(template, request, "script");

JSONArray scriptJSONArray = null;
//JSONArray scriptJSONArrayAvailable = null;

if (type.equals("detail") && Validator.isNotNull(script)) {
    scriptJSONArray = DDMXSDUtil.getJSONArray(script);

    /*scriptJSONArrayAvailable =  scriptJSONArray;
    List<String> namesTemplate = templateJSONArray(scriptJSONArray);
    JSONArray structureJSONArray = DDMXSDUtil.getJSONArray(structure.getXsd());
    for(int y = 0; y < structureJSONArray.length(); y++){
        JSONObject jsonObject = structureJSONArray.getJSONObject(y);
        String joName = jsonObject.getString("name");
        int count = 0;
        for(String name : namesTemplate){
            if(name.equals(joName)){
                count++;
                continue;
            }
        }
        if(count == 0){
            scriptJSONArrayAvailable.put(jsonObject);
        }
    }*/
}

String structureAvailableFields = ParamUtil.getString(request, "structureAvailableFields");

if (Validator.isNotNull(structureAvailableFields)) {
	scopeAvailableFields = structureAvailableFields;
}
%>

<portlet:actionURL var="editTemplateURL">
	<portlet:param name="struts_action" value="/fido_arp_template/edit_template" />
</portlet:actionURL>

<aui:form action="<%= editTemplateURL %>" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveTemplate();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (template != null) ? Constants.UPDATE : Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
	<aui:input name="templateId" type="hidden" value="<%= templateId %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="structureId" type="hidden" value="<%= structureId %>" />
	<aui:input name="type" type="hidden" value="<%= type %>" />
	<aui:input name="structureAvailableFields" type="hidden" value="<%= structureAvailableFields %>" />
	<aui:input name="saveCallback" type="hidden" value="<%= saveCallback %>" />
	<aui:input name="saveAndContinue" type="hidden" value="<%= false %>" />
    <aui:input name="ddmResource" type="hidden" value="<%=ddmResource%>" />

	<liferay-ui:error exception="<%= TemplateNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= TemplateScriptException.class %>" message="please-enter-a-valid-script" />

	<%
	String title = null;

	if (structure != null) {
		if (template != null) {
			title = template.getName(locale) + " (" + structure.getName(locale) + ")";
		}
		else {
			title = LanguageUtil.format(pageContext, "new-template-for-structure-x", structure.getName(locale), false);
		}
	}
	%>

	<liferay-ui:header
		backURL="<%= backURL %>"
		localizeTitle="<%= false %>"
		title="<%= title %>"
	/>

	<aui:model-context bean="<%= template %>" model="<%= DDMTemplate.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<liferay-ui:panel-container cssClass="lfr-structure-entry-details-container" extended="<%= false %>" id="templateDetailsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="templateDetailsSectionPanel" persistState="<%= true %>" title="details">
				<aui:input name="description" />

				<c:if test='<%= type.equals("detail") %>'>
					<aui:select helpMessage="only-allow-deleting-required-fields-in-edit-mode" label="mode" name="mode">
						<aui:option label="create" />
						<aui:option label="edit" />
					</aui:select>

					<aui:script use="aui-base,event-valuechange">
						A.one('#<portlet:namespace />mode').on(
							'valueChange',
							function(event) {
								<portlet:namespace />toggleMode(event.newVal);
							}
						);
					</aui:script>
				</c:if>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<c:choose>
			<c:when test='<%= type.equals("detail") %>'>
				<%@ include file="edit_template_detail.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="edit_template_list.jspf" %>
			</c:otherwise>
		</c:choose>
	</aui:fieldset>
</aui:form>

<c:if test='<%= type.equals("detail") %>'>
	<%@ include file="form_builder.jspf" %>

	<aui:script use="aui-base">
		var hiddenAttributesMap = window.<portlet:namespace />formBuilder.MAP_HIDDEN_FIELD_ATTRS;

		window.<portlet:namespace />getFieldHiddenAttributes = function(mode, field) {
			var hiddenAttributes = hiddenAttributesMap[field.get('type')] || hiddenAttributesMap.DEFAULT;

			hiddenAttributes = A.Array(hiddenAttributes);

			if (mode === '<%= DDMTemplateConstants.TEMPLATE_MODE_EDIT %>') {
				A.Array.removeItem(hiddenAttributes, 'readOnly');
			}

			return hiddenAttributes;
		};

		window.<portlet:namespace />toggleMode = function(mode) {
			var modeEdit = (mode === '<%= DDMTemplateConstants.TEMPLATE_MODE_EDIT %>');

			window.<portlet:namespace />formBuilder.set('allowRemoveRequiredFields', modeEdit);

			window.<portlet:namespace />formBuilder.get('fields').each(
				function(item, index, collection) {
					var hiddenAttributes = window.<portlet:namespace />getFieldHiddenAttributes(mode, item);

					item.set('hiddenAttributes', hiddenAttributes);
				}
			);

			A.Array.each(
				window.<portlet:namespace />formBuilder.get('availableFields'),
				function(item, index, collection) {
					var hiddenAttributes = window.<portlet:namespace />getFieldHiddenAttributes(mode, item);

					item.set('hiddenAttributes', hiddenAttributes);
				}
			);
		};

		<portlet:namespace />toggleMode('<%= HtmlUtil.escape(mode) %>');
	</aui:script>
</c:if>

<aui:button-row>
	<aui:button onClick='<%= renderResponse.getNamespace() + "saveTemplate();" %>' value='<%= LanguageUtil.get(pageContext, "save") %>' />

	<aui:button href="<%= redirect %>" type="cancel" />
</aui:button-row>

<%!
    private void addFieldsToArray(List<String> names, JSONObject jsonObject) {
        JSONArray fields = jsonObject.getJSONArray("fields");
        for(int j = 0; j < fields.length(); j++) {
            JSONObject field = fields.getJSONObject(j);
            if(!field.getString("type").equals("fieldset") && !field.getString("type").equals("ddm-paragraph")) {
                if (field.has("name")) {
                    names.add(field.getString("name"));
                }
            }
            if(field.has("fields") && field.getJSONArray("fields").length() > 0){
                addFieldsToArray(names, field);
            }
        }
    }

    public List<String> templateJSONArray(JSONArray scriptJSONArray){
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < scriptJSONArray.length(); i++) {
            JSONObject jsonObject = scriptJSONArray.getJSONObject(i);
            names.add(jsonObject.getString("name"));
            if(jsonObject.has("fields") && jsonObject.getJSONArray("fields").length() > 0){
                addFieldsToArray(names, jsonObject);
            }
        }
        return names;
    }
%>