package com.fidoarp.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class ExpandoUtils {

    private static Log log = LogFactoryUtil.getLog(ExpandoUtils.class);

    public static ExpandoTable getExpandoTable() throws SystemException, PortalException {
        ExpandoTable table = null;
        long companyId = 0;

        try {
            companyId = CompanyLocalServiceUtil.getCompanies().get(0).getCompanyId();
            table = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, User.class.getName());
        } catch (NoSuchTableException e) {
            log.warn("User Expando table not found. First time running?..");
            table = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, User.class.getName());
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
        ExpandoTable table = getExpandoTable();
        ExpandoColumn column = getColumn(table, attrName);
        ExpandoValueLocalServiceUtil.addValue(column.getCompanyId(), User.class.getName(), table.getName(), column.getName(), user.getUserId(), attrValue);
    }

    public static void updateUserValues(long userId, long companyId, String status) {

        try {
            ExpandoTable table = getExpandoTable();
            ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(companyId ,User.class.getName(), table.getName(), "status", userId);
            expandoValue.setData(status);
            ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);

        } catch (Exception e) {
            log.error("Could not update user expando value cause: " + e);

        }
    }
}
