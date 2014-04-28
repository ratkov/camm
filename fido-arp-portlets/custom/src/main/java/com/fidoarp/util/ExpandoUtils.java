package com.fidoarp.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.*;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.Arrays;

public class ExpandoUtils {

    private static Log log = LogFactoryUtil.getLog(ExpandoUtils.class);

    public static ExpandoTable getExpandoTable(String className) throws SystemException, PortalException {
        ExpandoTable table = null;
        long companyId = 0;

        try {
            companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
            table = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className);
        } catch (NoSuchTableException e) {
            log.warn("User Expando table not found. First time running?..");
            table = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, className);
        }
        return table;
    }

    public static ExpandoColumn getColumn(ExpandoTable table, String attrName) throws SystemException, PortalException {
        ExpandoColumn column = null;
        try {
            column = ExpandoColumnLocalServiceUtil.getColumn(table.getTableId(), attrName);
            if (column == null) {
                column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), attrName, ExpandoColumnConstants.LONG);
            }
        } catch (NoSuchColumnException e) {
            log.warn("User column not found...");
            column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), attrName, ExpandoColumnConstants.LONG);
        }
        return column;
    }

    public static void addUserValues(User user, String attrName, long attrValue) throws SystemException, PortalException {
        ExpandoTable table = getExpandoTable(User.class.getName());
        ExpandoColumn column = getColumn(table, attrName);
        ExpandoValueLocalServiceUtil.addValue(column.getCompanyId(), User.class.getName(), table.getName(), column.getName(), user.getUserId(), attrValue);
    }

    public static void addOrganizationValues(long organizationId, String attrName, long attrValue) throws SystemException, PortalException {
        ExpandoTable table = getExpandoTable(Organization.class.getName());
        ExpandoColumn column = getColumn(table, attrName);
        ExpandoValueLocalServiceUtil.addValue(column.getCompanyId(), Organization.class.getName(), table.getName(), column.getName(), organizationId, attrValue);
    }

    public static void updateUserValues(long userId, long companyId, String status) {

        try {
            ExpandoTable table = getExpandoTable(User.class.getName());
            ExpandoColumn column = getColumn(table, "status");
            ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(companyId ,User.class.getName(), table.getName(), column.getName(), userId);
            expandoValue.setData(status);
            ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);

        } catch (Exception e) {
            log.error("Could not update user expando value cause: " + Arrays.toString(e.getStackTrace()), e);

        }
    }

    public static void updateOrganizationValues(long organizationId, String status) {
        try {
            ExpandoTable table = getExpandoTable(Organization.class.getName());
            ExpandoColumn column = getColumn(table, "status");
            ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(column.getColumnId(), table.getCompanyId(), organizationId);
            if(expandoValue == null){
               addOrganizationValues(organizationId, "status", Long.valueOf(status));
            } else {
                expandoValue.setData(status);
                ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);
            }
        } catch (Exception e) {
            log.error("Could not update organization expando value cause: " + Arrays.toString(e.getStackTrace()), e);
        }
    }

}
