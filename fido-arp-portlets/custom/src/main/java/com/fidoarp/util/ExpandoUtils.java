package com.fidoarp.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchColumnException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

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
            if (column == null) ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), attrName, ExpandoColumnConstants.LONG);
        } catch (NoSuchColumnException e) {
            log.warn("User column not found...");
            column = ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), attrName, ExpandoColumnConstants.LONG);
        }
        return column;
    }

    public static User getUserByExpando(long companyId, String attrName) throws SystemException, PortalException {
        User user = null;
        ExpandoTable table = getExpandoTable();
        ExpandoColumn column = getColumn(table, attrName);
        List<ExpandoValue> valueList = ExpandoValueLocalServiceUtil.getColumnValues(companyId, User.class.getName(), table.getName(), column.getName(), -1, -1);
        if (valueList != null && !valueList.isEmpty()) {
            try {
                user = UserLocalServiceUtil.getUser(valueList.get(0).getClassPK());
            } catch (NoSuchUserException ignored) {
                log.warn("User with id #" + valueList.get(0).getClassPK() + " not exists, but record exists.");
            }
        }
        return user;
    }

    public static void addUserValues(User user, String attrName, long attrValue) throws SystemException, PortalException {
        ExpandoTable table = getExpandoTable();
        ExpandoColumn column = getColumn(table, attrName);

        ExpandoValue value = ExpandoValueLocalServiceUtil.createExpandoValue(CounterLocalServiceUtil.increment());
        value.setClassNameId(ClassNameLocalServiceUtil.getClassNameId(User.class));
        value.setTableId(table.getTableId());
        value.setColumn(column);
        value.setClassPK(user.getUserId());
        value.setData(String.valueOf(attrValue));

        ExpandoValueLocalServiceUtil.addExpandoValue(value);
    }

    public static long getUserExpandoValue(User user, String attrName) throws SystemException, PortalException {
        ExpandoTable table = getExpandoTable();
        ExpandoColumn column = getColumn(table, attrName);
        ExpandoValue value = ExpandoValueLocalServiceUtil.getValue(User.class.getName(), table.getName(), column.getName(), user.getUserId());
        if (value == null) {
            return 0L;
        }
        return value.getLong();
    }
}
