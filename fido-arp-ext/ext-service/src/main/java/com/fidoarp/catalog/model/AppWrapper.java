package com.fidoarp.catalog.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link App}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       App
 * @generated
 */
public class AppWrapper implements App, ModelWrapper<App> {
    private App _app;

    public AppWrapper(App app) {
        _app = app;
    }

    public Class<?> getModelClass() {
        return App.class;
    }

    public String getModelClassName() {
        return App.class.getName();
    }

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

    /**
    * Returns the primary key of this app.
    *
    * @return the primary key of this app
    */
    public long getPrimaryKey() {
        return _app.getPrimaryKey();
    }

    /**
    * Sets the primary key of this app.
    *
    * @param primaryKey the primary key of this app
    */
    public void setPrimaryKey(long primaryKey) {
        _app.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the app ID of this app.
    *
    * @return the app ID of this app
    */
    public long getAppId() {
        return _app.getAppId();
    }

    /**
    * Sets the app ID of this app.
    *
    * @param appId the app ID of this app
    */
    public void setAppId(long appId) {
        _app.setAppId(appId);
    }

    /**
    * Returns the user ID of this app.
    *
    * @return the user ID of this app
    */
    public long getUserId() {
        return _app.getUserId();
    }

    /**
    * Sets the user ID of this app.
    *
    * @param userId the user ID of this app
    */
    public void setUserId(long userId) {
        _app.setUserId(userId);
    }

    /**
    * Returns the user uuid of this app.
    *
    * @return the user uuid of this app
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _app.getUserUuid();
    }

    /**
    * Sets the user uuid of this app.
    *
    * @param userUuid the user uuid of this app
    */
    public void setUserUuid(java.lang.String userUuid) {
        _app.setUserUuid(userUuid);
    }

    /**
    * Returns the organization ID of this app.
    *
    * @return the organization ID of this app
    */
    public long getOrganizationId() {
        return _app.getOrganizationId();
    }

    /**
    * Sets the organization ID of this app.
    *
    * @param organizationId the organization ID of this app
    */
    public void setOrganizationId(long organizationId) {
        _app.setOrganizationId(organizationId);
    }

    /**
    * Returns the created date of this app.
    *
    * @return the created date of this app
    */
    public java.util.Date getCreatedDate() {
        return _app.getCreatedDate();
    }

    /**
    * Sets the created date of this app.
    *
    * @param createdDate the created date of this app
    */
    public void setCreatedDate(java.util.Date createdDate) {
        _app.setCreatedDate(createdDate);
    }

    /**
    * Returns the description of this app.
    *
    * @return the description of this app
    */
    public java.lang.String getDescription() {
        return _app.getDescription();
    }

    /**
    * Returns the localized description of this app in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized description of this app
    */
    public java.lang.String getDescription(java.util.Locale locale) {
        return _app.getDescription(locale);
    }

    /**
    * Returns the localized description of this app in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getDescription(java.util.Locale locale,
        boolean useDefault) {
        return _app.getDescription(locale, useDefault);
    }

    /**
    * Returns the localized description of this app in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized description of this app
    */
    public java.lang.String getDescription(java.lang.String languageId) {
        return _app.getDescription(languageId);
    }

    /**
    * Returns the localized description of this app in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this app
    */
    public java.lang.String getDescription(java.lang.String languageId,
        boolean useDefault) {
        return _app.getDescription(languageId, useDefault);
    }

    public java.lang.String getDescriptionCurrentLanguageId() {
        return _app.getDescriptionCurrentLanguageId();
    }

    public java.lang.String getDescriptionCurrentValue() {
        return _app.getDescriptionCurrentValue();
    }

    /**
    * Returns a map of the locales and localized descriptions of this app.
    *
    * @return the locales and localized descriptions of this app
    */
    public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
        return _app.getDescriptionMap();
    }

    /**
    * Sets the description of this app.
    *
    * @param description the description of this app
    */
    public void setDescription(java.lang.String description) {
        _app.setDescription(description);
    }

    /**
    * Sets the localized description of this app in the language.
    *
    * @param description the localized description of this app
    * @param locale the locale of the language
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale) {
        _app.setDescription(description, locale);
    }

    /**
    * Sets the localized description of this app in the language, and sets the default locale.
    *
    * @param description the localized description of this app
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _app.setDescription(description, locale, defaultLocale);
    }

    public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
        _app.setDescriptionCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized descriptions of this app from the map of locales and localized descriptions.
    *
    * @param descriptionMap the locales and localized descriptions of this app
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
        _app.setDescriptionMap(descriptionMap);
    }

    /**
    * Sets the localized descriptions of this app from the map of locales and localized descriptions, and sets the default locale.
    *
    * @param descriptionMap the locales and localized descriptions of this app
    * @param defaultLocale the default locale
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
        java.util.Locale defaultLocale) {
        _app.setDescriptionMap(descriptionMap, defaultLocale);
    }

    /**
    * Returns the status ID of this app.
    *
    * @return the status ID of this app
    */
    public long getStatusId() {
        return _app.getStatusId();
    }

    /**
    * Sets the status ID of this app.
    *
    * @param statusId the status ID of this app
    */
    public void setStatusId(long statusId) {
        _app.setStatusId(statusId);
    }

    /**
    * Returns the status change date of this app.
    *
    * @return the status change date of this app
    */
    public java.util.Date getStatusChangeDate() {
        return _app.getStatusChangeDate();
    }

    /**
    * Sets the status change date of this app.
    *
    * @param statusChangeDate the status change date of this app
    */
    public void setStatusChangeDate(java.util.Date statusChangeDate) {
        _app.setStatusChangeDate(statusChangeDate);
    }

    /**
    * Returns the client name of this app.
    *
    * @return the client name of this app
    */
    public java.lang.String getClientName() {
        return _app.getClientName();
    }

    /**
    * Sets the client name of this app.
    *
    * @param clientName the client name of this app
    */
    public void setClientName(java.lang.String clientName) {
        _app.setClientName(clientName);
    }

    /**
    * Returns the client okpo of this app.
    *
    * @return the client okpo of this app
    */
    public java.lang.String getClientOkpo() {
        return _app.getClientOkpo();
    }

    /**
    * Sets the client okpo of this app.
    *
    * @param clientOkpo the client okpo of this app
    */
    public void setClientOkpo(java.lang.String clientOkpo) {
        _app.setClientOkpo(clientOkpo);
    }

    /**
    * Returns the contact phone of this app.
    *
    * @return the contact phone of this app
    */
    public java.lang.String getContactPhone() {
        return _app.getContactPhone();
    }

    /**
    * Sets the contact phone of this app.
    *
    * @param contactPhone the contact phone of this app
    */
    public void setContactPhone(java.lang.String contactPhone) {
        _app.setContactPhone(contactPhone);
    }

    /**
    * Returns the credit amount of this app.
    *
    * @return the credit amount of this app
    */
    public int getCreditAmount() {
        return _app.getCreditAmount();
    }

    /**
    * Sets the credit amount of this app.
    *
    * @param creditAmount the credit amount of this app
    */
    public void setCreditAmount(int creditAmount) {
        _app.setCreditAmount(creditAmount);
    }

    /**
    * Returns the comments of this app.
    *
    * @return the comments of this app
    */
    public java.lang.String getComments() {
        return _app.getComments();
    }

    /**
    * Returns the localized comments of this app in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized comments of this app
    */
    public java.lang.String getComments(java.util.Locale locale) {
        return _app.getComments(locale);
    }

    /**
    * Returns the localized comments of this app in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized comments of this app. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getComments(java.util.Locale locale,
        boolean useDefault) {
        return _app.getComments(locale, useDefault);
    }

    /**
    * Returns the localized comments of this app in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized comments of this app
    */
    public java.lang.String getComments(java.lang.String languageId) {
        return _app.getComments(languageId);
    }

    /**
    * Returns the localized comments of this app in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized comments of this app
    */
    public java.lang.String getComments(java.lang.String languageId,
        boolean useDefault) {
        return _app.getComments(languageId, useDefault);
    }

    public java.lang.String getCommentsCurrentLanguageId() {
        return _app.getCommentsCurrentLanguageId();
    }

    public java.lang.String getCommentsCurrentValue() {
        return _app.getCommentsCurrentValue();
    }

    /**
    * Returns a map of the locales and localized commentses of this app.
    *
    * @return the locales and localized commentses of this app
    */
    public java.util.Map<java.util.Locale, java.lang.String> getCommentsMap() {
        return _app.getCommentsMap();
    }

    /**
    * Sets the comments of this app.
    *
    * @param comments the comments of this app
    */
    public void setComments(java.lang.String comments) {
        _app.setComments(comments);
    }

    /**
    * Sets the localized comments of this app in the language.
    *
    * @param comments the localized comments of this app
    * @param locale the locale of the language
    */
    public void setComments(java.lang.String comments, java.util.Locale locale) {
        _app.setComments(comments, locale);
    }

    /**
    * Sets the localized comments of this app in the language, and sets the default locale.
    *
    * @param comments the localized comments of this app
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setComments(java.lang.String comments, java.util.Locale locale,
        java.util.Locale defaultLocale) {
        _app.setComments(comments, locale, defaultLocale);
    }

    public void setCommentsCurrentLanguageId(java.lang.String languageId) {
        _app.setCommentsCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized commentses of this app from the map of locales and localized commentses.
    *
    * @param commentsMap the locales and localized commentses of this app
    */
    public void setCommentsMap(
        java.util.Map<java.util.Locale, java.lang.String> commentsMap) {
        _app.setCommentsMap(commentsMap);
    }

    /**
    * Sets the localized commentses of this app from the map of locales and localized commentses, and sets the default locale.
    *
    * @param commentsMap the locales and localized commentses of this app
    * @param defaultLocale the default locale
    */
    public void setCommentsMap(
        java.util.Map<java.util.Locale, java.lang.String> commentsMap,
        java.util.Locale defaultLocale) {
        _app.setCommentsMap(commentsMap, defaultLocale);
    }

    /**
    * Returns the questionnaire of this app.
    *
    * @return the questionnaire of this app
    */
    public java.lang.String getQuestionnaire() {
        return _app.getQuestionnaire();
    }

    /**
    * Sets the questionnaire of this app.
    *
    * @param questionnaire the questionnaire of this app
    */
    public void setQuestionnaire(java.lang.String questionnaire) {
        _app.setQuestionnaire(questionnaire);
    }

    public boolean isNew() {
        return _app.isNew();
    }

    public void setNew(boolean n) {
        _app.setNew(n);
    }

    public boolean isCachedModel() {
        return _app.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _app.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _app.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _app.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _app.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _app.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _app.setExpandoBridgeAttributes(serviceContext);
    }

    public void prepareLocalizedFieldsForImport(
        java.util.Locale defaultImportLocale)
        throws com.liferay.portal.LocaleException {
        _app.prepareLocalizedFieldsForImport(defaultImportLocale);
    }

    @Override
    public java.lang.Object clone() {
        return new AppWrapper((App) _app.clone());
    }

    public int compareTo(App app) {
        return _app.compareTo(app);
    }

    @Override
    public int hashCode() {
        return _app.hashCode();
    }

    public com.liferay.portal.model.CacheModel<App> toCacheModel() {
        return _app.toCacheModel();
    }

    public App toEscapedModel() {
        return new AppWrapper(_app.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _app.toString();
    }

    public java.lang.String toXmlString() {
        return _app.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _app.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public App getWrappedApp() {
        return _app;
    }

    public App getWrappedModel() {
        return _app;
    }

    public void resetOriginalValues() {
        _app.resetOriginalValues();
    }
}
