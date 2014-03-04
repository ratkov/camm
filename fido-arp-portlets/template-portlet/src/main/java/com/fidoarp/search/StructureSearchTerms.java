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

package com.fidoarp.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portlet.dynamicdatamapping.search.*;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 */
public class StructureSearchTerms extends com.liferay.portlet.dynamicdatamapping.search.StructureDisplayTerms {

	public StructureSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		classNameId = DAOParamUtil.getLong(portletRequest, CLASS_NAME_ID);
		description = DAOParamUtil.getString(portletRequest, DESCRIPTION);
		name = DAOParamUtil.getString(portletRequest, NAME);
		storageType = DAOParamUtil.getString(portletRequest, STORAGE_TYPE);
	}

}