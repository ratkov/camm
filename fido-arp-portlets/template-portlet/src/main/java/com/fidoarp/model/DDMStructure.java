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

package com.fidoarp.model;

import com.liferay.portal.model.PersistedModel;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureModel;

/**
 * The extended model interface for the DDMStructure service. Represents a row in the &quot;DDMStructure&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.dynamicdatamapping.model.DDMStructureModel
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl
 * @see com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl
 * @generated
 */
public interface DDMStructure extends DDMStructureModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<String> getAvailableLocales();

	public String getDefaultLocale();

	public com.liferay.portal.kernel.xml.Document getDocument();

	public String getFieldDataType(String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public String getFieldLabel(String fieldName,
                                java.util.Locale locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public String getFieldLabel(String fieldName,
                                String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.Set<String> getFieldNames();

	public String getFieldProperty(String fieldName,
                                   String property)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public String getFieldProperty(String fieldName,
                                   String property, String locale)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public boolean getFieldRequired(String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.Map<String, String> getFields(
            String fieldName, String attributeName,
            String attributeValue);

	public java.util.Map<String, String> getFields(
            String fieldName, String attributeName,
            String attributeValue, String locale);

	public java.util.Map<String, java.util.Map<String, String>> getFieldsMap();

	public java.util.Map<String, java.util.Map<String, String>> getFieldsMap(
            String locale);

	public String getFieldType(String fieldName)
		throws com.liferay.portlet.dynamicdatamapping.StructureFieldException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> getTemplates()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasField(String fieldName);

	public void prepareLocalizedFieldsForImport(
            java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException;

	public void setDocument(com.liferay.portal.kernel.xml.Document document);

	public void setLocalizedFieldsMap(
            java.util.Map<String, java.util.Map<String, java.util.Map<String, String>>> localizedFieldsMap);

	public void setXsd(String xsd);
}