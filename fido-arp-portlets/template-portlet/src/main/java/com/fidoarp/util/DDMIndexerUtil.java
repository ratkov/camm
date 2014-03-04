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

package com.fidoarp.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.util.*;

/**
 * @author Alexander Chow
 */
public class DDMIndexerUtil {

	public static void addAttributes(
		Document document, DDMStructure ddmStructure, Fields fields) {

		getDDMIndexer().addAttributes(document, ddmStructure, fields);
	}

	public static String encodeName(long ddmStructureId, String fieldName) {
		return getDDMIndexer().encodeName(ddmStructureId, fieldName);
	}

	public static com.liferay.portlet.dynamicdatamapping.util.DDMIndexer getDDMIndexer() {
		PortalRuntimePermission.checkGetBeanProperty(DDMIndexerUtil.class);

		return _ddmIndexer;
	}

	public void setDDMIndexer(com.liferay.portlet.dynamicdatamapping.util.DDMIndexer ddmIndexer) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmIndexer = ddmIndexer;
	}

	private static com.liferay.portlet.dynamicdatamapping.util.DDMIndexer _ddmIndexer;

}