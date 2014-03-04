package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.model.AppModel;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The base model implementation for the App service. Represents a row in the &quot;fido_app&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.fidoarp.catalog.model.AppModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AppImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppImpl
 * @see com.fidoarp.catalog.model.App
 * @see com.fidoarp.catalog.model.AppModel
 * @generated
 */
public class AppModelImpl extends BaseModelImpl<App> implements AppModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a app model instance should use the {@link com.fidoarp.catalog.model.App} interface instead.
     */
    public static final String TABLE_NAME = "fido_app";
    public static final Object[][] TABLE_COLUMNS = {
            { "id", Types.BIGINT },
            { "user_id", Types.BIGINT },
            { "organization_id", Types.BIGINT },
            { "created_date", Types.TIMESTAMP },
            { "description", Types.VARCHAR },
            { "status_id", Types.BIGINT },
            { "status_change_date", Types.TIMESTAMP },
            { "client_name", Types.VARCHAR },
            { "client_okpo", Types.VARCHAR },
            { "contact_phone", Types.VARCHAR },
            { "credit_amount", Types.INTEGER },
            { "comments", Types.VARCHAR },
            { "questionnaire", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table fido_app (id LONG not null primary key,user_id LONG,organization_id LONG,created_date DATE null,description STRING null,status_id LONG,status_change_date DATE null,client_name VARCHAR(75) null,client_okpo VARCHAR(75) null,contact_phone VARCHAR(75) null,credit_amount INTEGER,comments STRING null,questionnaire VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table fido_app";
    public static final String ORDER_BY_JPQL = " ORDER BY app.appId ASC";
    public static final String ORDER_BY_SQL = " ORDER BY fido_app.id ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.fidoarp.catalog.model.App"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.fidoarp.catalog.model.App"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.column.bitmask.enabled.com.fidoarp.catalog.model.App"),
            true);
    public static long USERID_COLUMN_BITMASK = 1L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.fidoarp.catalog.model.App"));
    private static ClassLoader _classLoader = App.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            App.class
        };
    private long _appId;
    private long _userId;
    private String _userUuid;
    private long _originalUserId;
    private boolean _setOriginalUserId;
    private long _organizationId;
    private Date _createdDate;
    private String _description;
    private String _descriptionCurrentLanguageId;
    private long _statusId;
    private Date _statusChangeDate;
    private String _clientName;
    private String _clientOkpo;
    private String _contactPhone;
    private int _creditAmount;
    private String _comments;
    private String _commentsCurrentLanguageId;
    private String _questionnaire;
    private long _columnBitmask;
    private App _escapedModelProxy;

    public AppModelImpl() {
    }

    public long getPrimaryKey() {
        return _appId;
    }

    public void setPrimaryKey(long primaryKey) {
        setAppId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_appId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return App.class;
    }

    public String getModelClassName() {
        return App.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("appId", getAppId());
        attributes.put("userId", getUserId());
        attributes.put("organizationId", getOrganizationId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("description", getDescription());
        attributes.put("statusId", getStatusId());
        attributes.put("statusChangeDate", getStatusChangeDate());
        attributes.put("clientName", getClientName());
        attributes.put("clientOkpo", getClientOkpo());
        attributes.put("contactPhone", getContactPhone());
        attributes.put("creditAmount", getCreditAmount());
        attributes.put("comments", getComments());
        attributes.put("questionnaire", getQuestionnaire());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long appId = (Long) attributes.get("appId");

        if (appId != null) {
            setAppId(appId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long organizationId = (Long) attributes.get("organizationId");

        if (organizationId != null) {
            setOrganizationId(organizationId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long statusId = (Long) attributes.get("statusId");

        if (statusId != null) {
            setStatusId(statusId);
        }

        Date statusChangeDate = (Date) attributes.get("statusChangeDate");

        if (statusChangeDate != null) {
            setStatusChangeDate(statusChangeDate);
        }

        String clientName = (String) attributes.get("clientName");

        if (clientName != null) {
            setClientName(clientName);
        }

        String clientOkpo = (String) attributes.get("clientOkpo");

        if (clientOkpo != null) {
            setClientOkpo(clientOkpo);
        }

        String contactPhone = (String) attributes.get("contactPhone");

        if (contactPhone != null) {
            setContactPhone(contactPhone);
        }

        Integer creditAmount = (Integer) attributes.get("creditAmount");

        if (creditAmount != null) {
            setCreditAmount(creditAmount);
        }

        String comments = (String) attributes.get("comments");

        if (comments != null) {
            setComments(comments);
        }

        String questionnaire = (String) attributes.get("questionnaire");

        if (questionnaire != null) {
            setQuestionnaire(questionnaire);
        }
    }

    public long getAppId() {
        return _appId;
    }

    public void setAppId(long appId) {
        _columnBitmask = -1L;

        _appId = appId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _columnBitmask |= USERID_COLUMN_BITMASK;

        if (!_setOriginalUserId) {
            _setOriginalUserId = true;

            _originalUserId = _userId;
        }

        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public long getOriginalUserId() {
        return _originalUserId;
    }

    public long getOrganizationId() {
        return _organizationId;
    }

    public void setOrganizationId(long organizationId) {
        _organizationId = organizationId;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    public String getDescription(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getDescription(languageId);
    }

    public String getDescription(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getDescription(languageId, useDefault);
    }

    public String getDescription(String languageId) {
        return LocalizationUtil.getLocalization(getDescription(), languageId);
    }

    public String getDescription(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getDescription(), languageId,
            useDefault);
    }

    public String getDescriptionCurrentLanguageId() {
        return _descriptionCurrentLanguageId;
    }

    @JSON
    public String getDescriptionCurrentValue() {
        Locale locale = getLocale(_descriptionCurrentLanguageId);

        return getDescription(locale);
    }

    public Map<Locale, String> getDescriptionMap() {
        return LocalizationUtil.getLocalizationMap(getDescription());
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void setDescription(String description, Locale locale) {
        setDescription(description, locale, LocaleUtil.getDefault());
    }

    public void setDescription(String description, Locale locale,
        Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(description)) {
            setDescription(LocalizationUtil.updateLocalization(
                    getDescription(), "Description", description, languageId,
                    defaultLanguageId));
        } else {
            setDescription(LocalizationUtil.removeLocalization(
                    getDescription(), "Description", languageId));
        }
    }

    public void setDescriptionCurrentLanguageId(String languageId) {
        _descriptionCurrentLanguageId = languageId;
    }

    public void setDescriptionMap(Map<Locale, String> descriptionMap) {
        setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
    }

    public void setDescriptionMap(Map<Locale, String> descriptionMap,
        Locale defaultLocale) {
        if (descriptionMap == null) {
            return;
        }

        Locale[] locales = LanguageUtil.getAvailableLocales();

        for (Locale locale : locales) {
            String description = descriptionMap.get(locale);

            setDescription(description, locale, defaultLocale);
        }
    }

    public long getStatusId() {
        return _statusId;
    }

    public void setStatusId(long statusId) {
        _statusId = statusId;
    }

    public Date getStatusChangeDate() {
        return _statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        _statusChangeDate = statusChangeDate;
    }

    public String getClientName() {
        if (_clientName == null) {
            return StringPool.BLANK;
        } else {
            return _clientName;
        }
    }

    public void setClientName(String clientName) {
        _clientName = clientName;
    }

    public String getClientOkpo() {
        if (_clientOkpo == null) {
            return StringPool.BLANK;
        } else {
            return _clientOkpo;
        }
    }

    public void setClientOkpo(String clientOkpo) {
        _clientOkpo = clientOkpo;
    }

    public String getContactPhone() {
        if (_contactPhone == null) {
            return StringPool.BLANK;
        } else {
            return _contactPhone;
        }
    }

    public void setContactPhone(String contactPhone) {
        _contactPhone = contactPhone;
    }

    public int getCreditAmount() {
        return _creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        _creditAmount = creditAmount;
    }

    public String getComments() {
        if (_comments == null) {
            return StringPool.BLANK;
        } else {
            return _comments;
        }
    }

    public String getComments(Locale locale) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getComments(languageId);
    }

    public String getComments(Locale locale, boolean useDefault) {
        String languageId = LocaleUtil.toLanguageId(locale);

        return getComments(languageId, useDefault);
    }

    public String getComments(String languageId) {
        return LocalizationUtil.getLocalization(getComments(), languageId);
    }

    public String getComments(String languageId, boolean useDefault) {
        return LocalizationUtil.getLocalization(getComments(), languageId,
            useDefault);
    }

    public String getCommentsCurrentLanguageId() {
        return _commentsCurrentLanguageId;
    }

    @JSON
    public String getCommentsCurrentValue() {
        Locale locale = getLocale(_commentsCurrentLanguageId);

        return getComments(locale);
    }

    public Map<Locale, String> getCommentsMap() {
        return LocalizationUtil.getLocalizationMap(getComments());
    }

    public void setComments(String comments) {
        _comments = comments;
    }

    public void setComments(String comments, Locale locale) {
        setComments(comments, locale, LocaleUtil.getDefault());
    }

    public void setComments(String comments, Locale locale, Locale defaultLocale) {
        String languageId = LocaleUtil.toLanguageId(locale);
        String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

        if (Validator.isNotNull(comments)) {
            setComments(LocalizationUtil.updateLocalization(getComments(),
                    "Comments", comments, languageId, defaultLanguageId));
        } else {
            setComments(LocalizationUtil.removeLocalization(getComments(),
                    "Comments", languageId));
        }
    }

    public void setCommentsCurrentLanguageId(String languageId) {
        _commentsCurrentLanguageId = languageId;
    }

    public void setCommentsMap(Map<Locale, String> commentsMap) {
        setCommentsMap(commentsMap, LocaleUtil.getDefault());
    }

    public void setCommentsMap(Map<Locale, String> commentsMap,
        Locale defaultLocale) {
        if (commentsMap == null) {
            return;
        }

        Locale[] locales = LanguageUtil.getAvailableLocales();

        for (Locale locale : locales) {
            String comments = commentsMap.get(locale);

            setComments(comments, locale, defaultLocale);
        }
    }

    public String getQuestionnaire() {
        if (_questionnaire == null) {
            return StringPool.BLANK;
        } else {
            return _questionnaire;
        }
    }

    public void setQuestionnaire(String questionnaire) {
        _questionnaire = questionnaire;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            App.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @SuppressWarnings("unused")
    public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
        throws LocaleException {
        setDescription(getDescription(defaultImportLocale),
            defaultImportLocale, defaultImportLocale);
        setComments(getComments(defaultImportLocale), defaultImportLocale,
            defaultImportLocale);
    }

    @Override
    public App toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (App) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public Object clone() {
        AppImpl appImpl = new AppImpl();

        appImpl.setAppId(getAppId());
        appImpl.setUserId(getUserId());
        appImpl.setOrganizationId(getOrganizationId());
        appImpl.setCreatedDate(getCreatedDate());
        appImpl.setDescription(getDescription());
        appImpl.setStatusId(getStatusId());
        appImpl.setStatusChangeDate(getStatusChangeDate());
        appImpl.setClientName(getClientName());
        appImpl.setClientOkpo(getClientOkpo());
        appImpl.setContactPhone(getContactPhone());
        appImpl.setCreditAmount(getCreditAmount());
        appImpl.setComments(getComments());
        appImpl.setQuestionnaire(getQuestionnaire());

        appImpl.resetOriginalValues();

        return appImpl;
    }

    public int compareTo(App app) {
        int value = 0;

        if (getAppId() < app.getAppId()) {
            value = -1;
        } else if (getAppId() > app.getAppId()) {
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

        App app = null;

        try {
            app = (App) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = app.getPrimaryKey();

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
        AppModelImpl appModelImpl = this;

        appModelImpl._originalUserId = appModelImpl._userId;

        appModelImpl._setOriginalUserId = false;

        appModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<App> toCacheModel() {
        AppCacheModel appCacheModel = new AppCacheModel();

        appCacheModel.appId = getAppId();

        appCacheModel.userId = getUserId();

        appCacheModel.organizationId = getOrganizationId();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            appCacheModel.createdDate = createdDate.getTime();
        } else {
            appCacheModel.createdDate = Long.MIN_VALUE;
        }

        appCacheModel.description = getDescription();

        String description = appCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            appCacheModel.description = null;
        }

        appCacheModel.statusId = getStatusId();

        Date statusChangeDate = getStatusChangeDate();

        if (statusChangeDate != null) {
            appCacheModel.statusChangeDate = statusChangeDate.getTime();
        } else {
            appCacheModel.statusChangeDate = Long.MIN_VALUE;
        }

        appCacheModel.clientName = getClientName();

        String clientName = appCacheModel.clientName;

        if ((clientName != null) && (clientName.length() == 0)) {
            appCacheModel.clientName = null;
        }

        appCacheModel.clientOkpo = getClientOkpo();

        String clientOkpo = appCacheModel.clientOkpo;

        if ((clientOkpo != null) && (clientOkpo.length() == 0)) {
            appCacheModel.clientOkpo = null;
        }

        appCacheModel.contactPhone = getContactPhone();

        String contactPhone = appCacheModel.contactPhone;

        if ((contactPhone != null) && (contactPhone.length() == 0)) {
            appCacheModel.contactPhone = null;
        }

        appCacheModel.creditAmount = getCreditAmount();

        appCacheModel.comments = getComments();

        String comments = appCacheModel.comments;

        if ((comments != null) && (comments.length() == 0)) {
            appCacheModel.comments = null;
        }

        appCacheModel.questionnaire = getQuestionnaire();

        String questionnaire = appCacheModel.questionnaire;

        if ((questionnaire != null) && (questionnaire.length() == 0)) {
            appCacheModel.questionnaire = null;
        }

        return appCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{appId=");
        sb.append(getAppId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", organizationId=");
        sb.append(getOrganizationId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", statusId=");
        sb.append(getStatusId());
        sb.append(", statusChangeDate=");
        sb.append(getStatusChangeDate());
        sb.append(", clientName=");
        sb.append(getClientName());
        sb.append(", clientOkpo=");
        sb.append(getClientOkpo());
        sb.append(", contactPhone=");
        sb.append(getContactPhone());
        sb.append(", creditAmount=");
        sb.append(getCreditAmount());
        sb.append(", comments=");
        sb.append(getComments());
        sb.append(", questionnaire=");
        sb.append(getQuestionnaire());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.fidoarp.catalog.model.App");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>appId</column-name><column-value><![CDATA[");
        sb.append(getAppId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationId</column-name><column-value><![CDATA[");
        sb.append(getOrganizationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusId</column-name><column-value><![CDATA[");
        sb.append(getStatusId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusChangeDate</column-name><column-value><![CDATA[");
        sb.append(getStatusChangeDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clientName</column-name><column-value><![CDATA[");
        sb.append(getClientName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clientOkpo</column-name><column-value><![CDATA[");
        sb.append(getClientOkpo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contactPhone</column-name><column-value><![CDATA[");
        sb.append(getContactPhone());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creditAmount</column-name><column-value><![CDATA[");
        sb.append(getCreditAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>comments</column-name><column-value><![CDATA[");
        sb.append(getComments());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>questionnaire</column-name><column-value><![CDATA[");
        sb.append(getQuestionnaire());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
