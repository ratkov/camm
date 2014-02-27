package com.fidoarp.catalog.model;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the App service. Represents a row in the &quot;fido_app&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.fidoarp.catalog.model.impl.AppModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.fidoarp.catalog.model.impl.AppImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see App
 * @see com.fidoarp.catalog.model.impl.AppImpl
 * @see com.fidoarp.catalog.model.impl.AppModelImpl
 * @generated
 */
public interface AppModel extends BaseModel<App> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a app model instance should use the {@link App} interface instead.
     */

    /**
     * Returns the primary key of this app.
     *
     * @return the primary key of this app
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this app.
     *
     * @param primaryKey the primary key of this app
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the app ID of this app.
     *
     * @return the app ID of this app
     */
    public long getAppId();

    /**
     * Sets the app ID of this app.
     *
     * @param appId the app ID of this app
     */
    public void setAppId(long appId);

    /**
     * Returns the user ID of this app.
     *
     * @return the user ID of this app
     */
    public long getUserId();

    /**
     * Sets the user ID of this app.
     *
     * @param userId the user ID of this app
     */
    public void setUserId(long userId);

    /**
     * Returns the user uuid of this app.
     *
     * @return the user uuid of this app
     * @throws SystemException if a system exception occurred
     */
    public String getUserUuid() throws SystemException;

    /**
     * Sets the user uuid of this app.
     *
     * @param userUuid the user uuid of this app
     */
    public void setUserUuid(String userUuid);

    /**
     * Returns the organization ID of this app.
     *
     * @return the organization ID of this app
     */
    public long getOrganizationId();

    /**
     * Sets the organization ID of this app.
     *
     * @param organizationId the organization ID of this app
     */
    public void setOrganizationId(long organizationId);

    /**
     * Returns the created date of this app.
     *
     * @return the created date of this app
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this app.
     *
     * @param createdDate the created date of this app
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the description of this app.
     *
     * @return the description of this app
     */
    public String getDescription();

    /**
     * Returns the localized description of this app in the language. Uses the default language if no localization exists for the requested language.
     *
     * @param locale the locale of the language
     * @return the localized description of this app
     */
    @AutoEscape
    public String getDescription(Locale locale);

    /**
     * Returns the localized description of this app in the language, optionally using the default language if no localization exists for the requested language.
     *
     * @param locale the local of the language
     * @param useDefault whether to use the default language if no localization exists for the requested language
     * @return the localized description of this app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
     */
    @AutoEscape
    public String getDescription(Locale locale, boolean useDefault);

    /**
     * Returns the localized description of this app in the language. Uses the default language if no localization exists for the requested language.
     *
     * @param languageId the ID of the language
     * @return the localized description of this app
     */
    @AutoEscape
    public String getDescription(String languageId);

    /**
     * Returns the localized description of this app in the language, optionally using the default language if no localization exists for the requested language.
     *
     * @param languageId the ID of the language
     * @param useDefault whether to use the default language if no localization exists for the requested language
     * @return the localized description of this app
     */
    @AutoEscape
    public String getDescription(String languageId, boolean useDefault);

    @AutoEscape
    public String getDescriptionCurrentLanguageId();

    @AutoEscape
    public String getDescriptionCurrentValue();

    /**
     * Returns a map of the locales and localized descriptions of this app.
     *
     * @return the locales and localized descriptions of this app
     */
    public Map<Locale, String> getDescriptionMap();

    /**
     * Sets the description of this app.
     *
     * @param description the description of this app
     */
    public void setDescription(String description);

    /**
     * Sets the localized description of this app in the language.
     *
     * @param description the localized description of this app
     * @param locale the locale of the language
     */
    public void setDescription(String description, Locale locale);

    /**
     * Sets the localized description of this app in the language, and sets the default locale.
     *
     * @param description the localized description of this app
     * @param locale the locale of the language
     * @param defaultLocale the default locale
     */
    public void setDescription(String description, Locale locale,
        Locale defaultLocale);

    public void setDescriptionCurrentLanguageId(String languageId);

    /**
     * Sets the localized descriptions of this app from the map of locales and localized descriptions.
     *
     * @param descriptionMap the locales and localized descriptions of this app
     */
    public void setDescriptionMap(Map<Locale, String> descriptionMap);

    /**
     * Sets the localized descriptions of this app from the map of locales and localized descriptions, and sets the default locale.
     *
     * @param descriptionMap the locales and localized descriptions of this app
     * @param defaultLocale the default locale
     */
    public void setDescriptionMap(Map<Locale, String> descriptionMap,
        Locale defaultLocale);

    /**
     * Returns the status ID of this app.
     *
     * @return the status ID of this app
     */
    public long getStatusId();

    /**
     * Sets the status ID of this app.
     *
     * @param statusId the status ID of this app
     */
    public void setStatusId(long statusId);

    /**
     * Returns the status change date of this app.
     *
     * @return the status change date of this app
     */
    public Date getStatusChangeDate();

    /**
     * Sets the status change date of this app.
     *
     * @param statusChangeDate the status change date of this app
     */
    public void setStatusChangeDate(Date statusChangeDate);

    /**
     * Returns the client name of this app.
     *
     * @return the client name of this app
     */
    @AutoEscape
    public String getClientName();

    /**
     * Sets the client name of this app.
     *
     * @param clientName the client name of this app
     */
    public void setClientName(String clientName);

    /**
     * Returns the client okpo of this app.
     *
     * @return the client okpo of this app
     */
    @AutoEscape
    public String getClientOkpo();

    /**
     * Sets the client okpo of this app.
     *
     * @param clientOkpo the client okpo of this app
     */
    public void setClientOkpo(String clientOkpo);

    /**
     * Returns the contact phone of this app.
     *
     * @return the contact phone of this app
     */
    @AutoEscape
    public String getContactPhone();

    /**
     * Sets the contact phone of this app.
     *
     * @param contactPhone the contact phone of this app
     */
    public void setContactPhone(String contactPhone);

    /**
     * Returns the credit amount of this app.
     *
     * @return the credit amount of this app
     */
    public int getCreditAmount();

    /**
     * Sets the credit amount of this app.
     *
     * @param creditAmount the credit amount of this app
     */
    public void setCreditAmount(int creditAmount);

    /**
     * Returns the comments of this app.
     *
     * @return the comments of this app
     */
    public String getComments();

    /**
     * Returns the localized comments of this app in the language. Uses the default language if no localization exists for the requested language.
     *
     * @param locale the locale of the language
     * @return the localized comments of this app
     */
    @AutoEscape
    public String getComments(Locale locale);

    /**
     * Returns the localized comments of this app in the language, optionally using the default language if no localization exists for the requested language.
     *
     * @param locale the local of the language
     * @param useDefault whether to use the default language if no localization exists for the requested language
     * @return the localized comments of this app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
     */
    @AutoEscape
    public String getComments(Locale locale, boolean useDefault);

    /**
     * Returns the localized comments of this app in the language. Uses the default language if no localization exists for the requested language.
     *
     * @param languageId the ID of the language
     * @return the localized comments of this app
     */
    @AutoEscape
    public String getComments(String languageId);

    /**
     * Returns the localized comments of this app in the language, optionally using the default language if no localization exists for the requested language.
     *
     * @param languageId the ID of the language
     * @param useDefault whether to use the default language if no localization exists for the requested language
     * @return the localized comments of this app
     */
    @AutoEscape
    public String getComments(String languageId, boolean useDefault);

    @AutoEscape
    public String getCommentsCurrentLanguageId();

    @AutoEscape
    public String getCommentsCurrentValue();

    /**
     * Returns a map of the locales and localized commentses of this app.
     *
     * @return the locales and localized commentses of this app
     */
    public Map<Locale, String> getCommentsMap();

    /**
     * Sets the comments of this app.
     *
     * @param comments the comments of this app
     */
    public void setComments(String comments);

    /**
     * Sets the localized comments of this app in the language.
     *
     * @param comments the localized comments of this app
     * @param locale the locale of the language
     */
    public void setComments(String comments, Locale locale);

    /**
     * Sets the localized comments of this app in the language, and sets the default locale.
     *
     * @param comments the localized comments of this app
     * @param locale the locale of the language
     * @param defaultLocale the default locale
     */
    public void setComments(String comments, Locale locale, Locale defaultLocale);

    public void setCommentsCurrentLanguageId(String languageId);

    /**
     * Sets the localized commentses of this app from the map of locales and localized commentses.
     *
     * @param commentsMap the locales and localized commentses of this app
     */
    public void setCommentsMap(Map<Locale, String> commentsMap);

    /**
     * Sets the localized commentses of this app from the map of locales and localized commentses, and sets the default locale.
     *
     * @param commentsMap the locales and localized commentses of this app
     * @param defaultLocale the default locale
     */
    public void setCommentsMap(Map<Locale, String> commentsMap,
        Locale defaultLocale);

    /**
     * Returns the questionnaire of this app.
     *
     * @return the questionnaire of this app
     */
    @AutoEscape
    public String getQuestionnaire();

    /**
     * Sets the questionnaire of this app.
     *
     * @param questionnaire the questionnaire of this app
     */
    public void setQuestionnaire(String questionnaire);

    public boolean isNew();

    public void setNew(boolean n);

    public boolean isCachedModel();

    public void setCachedModel(boolean cachedModel);

    public boolean isEscapedModel();

    public Serializable getPrimaryKeyObj();

    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    public ExpandoBridge getExpandoBridge();

    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
        throws LocaleException;

    public Object clone();

    public int compareTo(App app);

    public int hashCode();

    public CacheModel<App> toCacheModel();

    public App toEscapedModel();

    public String toString();

    public String toXmlString();
}