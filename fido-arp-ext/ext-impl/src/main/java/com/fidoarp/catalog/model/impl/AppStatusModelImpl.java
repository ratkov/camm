package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.model.AppStatusModel;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The base model implementation for the AppStatus service. Represents a row in the &quot;fido_app_status&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.fidoarp.catalog.model.AppStatusModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AppStatusImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppStatusImpl
 * @see com.fidoarp.catalog.model.AppStatus
 * @see com.fidoarp.catalog.model.AppStatusModel
 * @generated
 */
public class AppStatusModelImpl extends BaseModelImpl<AppStatus>
    implements AppStatusModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a app status model instance should use the {@link com.fidoarp.catalog.model.AppStatus} interface instead.
     */
    public static final String TABLE_NAME = "fido_app_status";
    public static final Object[][] TABLE_COLUMNS = {
            { "id", Types.BIGINT },
            { "code", Types.VARCHAR },
            { "name", Types.VARCHAR },
            { "description", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table fido_app_status (id LONG not null primary key,code VARCHAR(75) null,name STRING null,description VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table fido_app_status";
    public static final String ORDER_BY_JPQL = " ORDER BY appStatus.appStatusId ASC";
    public static final String ORDER_BY_SQL = " ORDER BY fido_app_status.id ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.fidoarp.catalog.model.AppStatus"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.fidoarp.catalog.model.AppStatus"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.column.bitmask.enabled.com.fidoarp.catalog.model.AppStatus"),
            true);
    public static long APPSTATUSCODE_COLUMN_BITMASK = 1L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.fidoarp.catalog.model.AppStatus"));
    private static ClassLoader _classLoader = AppStatus.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            AppStatus.class
        };
    private long _appStatusId;
    private String _appStatusCode;
    private String _originalAppStatusCode;
    private String _name;
    private String _nameCurrentLanguageId;
    private String _description;
    private long _columnBitmask;
    private AppStatus _escapedModelProxy;

    public AppStatusModelImpl() {
    }

    public long getPrimaryKey() {
        return _appStatusId;
    }

    public void setPrimaryKey(long primaryKey) {
        setAppStatusId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_appStatusId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return AppStatus.class;
    }

    public String getModelClassName() {
        return AppStatus.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("appStatusId", getAppStatusId());
        attributes.put("appStatusCode", getAppStatusCode());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long appStatusId = (Long) attributes.get("appStatusId");

        if (appStatusId != null) {
            setAppStatusId(appStatusId);
        }

        String appStatusCode = (String) attributes.get("appStatusCode");

        if (appStatusCode != null) {
            setAppStatusCode(appStatusCode);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    public long getAppStatusId() {
        return _appStatusId;
    }

    public void setAppStatusId(long appStatusId) {
        _columnBitmask = -1L;

        _appStatusId = appStatusId;
    }

    public String getAppStatusCode() {
        if (_appStatusCode == null) {
            return StringPool.BLANK;
        } else {
            return _appStatusCode;
        }
    }

    public void setAppStatusCode(String appStatusCode) {
        _columnBitmask |= APPSTATUSCODE_COLUMN_BITMASK;

        if (_originalAppStatusCode == null) {
            _originalAppStatusCode = _appStatusCode;
        }

        _appStatusCode = appStatusCode;
    }

    public String getOriginalAppStatusCode() {
        return GetterUtil.getString(_originalAppStatusCode);
    }

    public String getName() {
        if (_name == null) {
            return StringPool.BLANK;
        } else {
            return _name;
        }
    }

    public String getName(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getName(languageId);
    }

    public String getName(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getName(languageId, useDefault);
    }

    public String getName(String languageId) {
        return LocalizationUtil.getLocalization(getName(), languageId);
    }

    public String getName(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getName(), languageId,
            useDefault);
    }

    public String getNameCurrentLanguageId() {
        return _nameCurrentLanguageId;
    }

    @JSON
    public String getNameCurrentValue() {
        Locale locale = getLocale(_nameCurrentLanguageId);

        return getName(locale);
    }

    public Map<Locale, String> getNameMap() {
        return LocalizationUtil.getLocalizationMap(getName());
    }

    public void setName(String name) {
        _name = name;
    }

    public void setName(String name, Locale locale) {
        setName(name, locale, LocaleUtil.getDefault());
    }

    public void setName(String name, Locale locale, Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(name)) {
            setName(LocalizationUtil.updateLocalization(getName(), "Name",
                    name, languageId, defaultLanguageId));
        } else {
            setName(LocalizationUtil.removeLocalization(getName(), "Name",
                    languageId));
        }
    }

    public void setNameCurrentLanguageId(String languageId) {
        _nameCurrentLanguageId = languageId;
    }

    public void setNameMap(Map<Locale, String> nameMap) {
        setNameMap(nameMap, LocaleUtil.getDefault());
    }

    public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
        if (nameMap == null) {
            return;
        }

        Locale[] locales = LanguageUtil.getAvailableLocales();

        for (Locale locale : locales) {
            String name = nameMap.get(locale);

            setName(name, locale, defaultLocale);
        }
    }

    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    public void setDescription(String description) {
        _description = description;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            AppStatus.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @SuppressWarnings("unused")
    public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
        throws LocaleException {
        setName(getName(defaultImportLocale), defaultImportLocale,
            defaultImportLocale);
    }

    @Override
    public AppStatus toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (AppStatus) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public Object clone() {
        AppStatusImpl appStatusImpl = new AppStatusImpl();

        appStatusImpl.setAppStatusId(getAppStatusId());
        appStatusImpl.setAppStatusCode(getAppStatusCode());
        appStatusImpl.setName(getName());
        appStatusImpl.setDescription(getDescription());

        appStatusImpl.resetOriginalValues();

        return appStatusImpl;
    }

    public int compareTo(AppStatus appStatus) {
        int value = 0;

        if (getAppStatusId() < appStatus.getAppStatusId()) {
            value = -1;
        } else if (getAppStatusId() > appStatus.getAppStatusId()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        AppStatus appStatus = null;

        try {
            appStatus = (AppStatus) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = appStatus.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        AppStatusModelImpl appStatusModelImpl = this;

        appStatusModelImpl._originalAppStatusCode = appStatusModelImpl._appStatusCode;

        appStatusModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<AppStatus> toCacheModel() {
        AppStatusCacheModel appStatusCacheModel = new AppStatusCacheModel();

        appStatusCacheModel.appStatusId = getAppStatusId();

        appStatusCacheModel.appStatusCode = getAppStatusCode();

        String appStatusCode = appStatusCacheModel.appStatusCode;

        if ((appStatusCode != null) && (appStatusCode.length() == 0)) {
            appStatusCacheModel.appStatusCode = null;
        }

        appStatusCacheModel.name = getName();

        String name = appStatusCacheModel.name;

        if ((name != null) && (name.length() == 0)) {
            appStatusCacheModel.name = null;
        }

        appStatusCacheModel.description = getDescription();

        String description = appStatusCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            appStatusCacheModel.description = null;
        }

        return appStatusCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{appStatusId=");
        sb.append(getAppStatusId());
        sb.append(", appStatusCode=");
        sb.append(getAppStatusCode());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.fidoarp.catalog.model.AppStatus");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>appStatusId</column-name><column-value><![CDATA[");
        sb.append(getAppStatusId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>appStatusCode</column-name><column-value><![CDATA[");
        sb.append(getAppStatusCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
