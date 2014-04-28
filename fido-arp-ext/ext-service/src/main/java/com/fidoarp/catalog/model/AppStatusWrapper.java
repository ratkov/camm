package com.fidoarp.catalog.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AppStatus}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppStatus
 * @generated
 */
public class AppStatusWrapper implements AppStatus, ModelWrapper<AppStatus> {
    private AppStatus _appStatus;

    public AppStatusWrapper(AppStatus appStatus) {
        _appStatus = appStatus;
    }

    public Class<?> getModelClass() {
        return AppStatus.class;
    }

    public String getModelClassName() {
        return AppStatus.class.getName();
    }

    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("appStatusId", getAppStatusId());
        attributes.put("appStatusCode", getAppStatusCode());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

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

    /**
    * Returns the primary key of this app status.
    *
    * @return the primary key of this app status
    */
    public long getPrimaryKey() {
        return _appStatus.getPrimaryKey();
    }

    /**
    * Sets the primary key of this app status.
    *
    * @param primaryKey the primary key of this app status
    */
    public void setPrimaryKey(long primaryKey) {
        _appStatus.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the app status ID of this app status.
    *
    * @return the app status ID of this app status
    */
    public long getAppStatusId() {
        return _appStatus.getAppStatusId();
    }

    /**
    * Sets the app status ID of this app status.
    *
    * @param appStatusId the app status ID of this app status
    */
    public void setAppStatusId(long appStatusId) {
        _appStatus.setAppStatusId(appStatusId);
    }

    /**
    * Returns the app status code of this app status.
    *
    * @return the app status code of this app status
    */
    public java.lang.String getAppStatusCode() {
        return _appStatus.getAppStatusCode();
    }

    /**
    * Sets the app status code of this app status.
    *
    * @param appStatusCode the app status code of this app status
    */
    public void setAppStatusCode(java.lang.String appStatusCode) {
        _appStatus.setAppStatusCode(appStatusCode);
    }

    /**
    * Returns the name of this app status.
    *
    * @return the name of this app status
    */
    public java.lang.String getName() {
        return _appStatus.getName();
    }

    /**
    * Returns the localized name of this app status in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized name of this app status
    */
    public java.lang.String getName(java.util.Locale locale) {
        return _appStatus.getName(locale);
    }

    /**
    * Returns the localized name of this app status in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized name of this app status. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
        return _appStatus.getName(locale, useDefault);
    }

    /**
    * Returns the localized name of this app status in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized name of this app status
    */
    public java.lang.String getName(java.lang.String languageId) {
        return _appStatus.getName(languageId);
    }

    /**
    * Returns the localized name of this app status in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized name of this app status
    */
    public java.lang.String getName(java.lang.String languageId,
        boolean useDefault) {
        return _appStatus.getName(languageId, useDefault);
    }

    public java.lang.String getNameCurrentLanguageId() {
        return _appStatus.getNameCurrentLanguageId();
    }

    public java.lang.String getNameCurrentValue() {
        return _appStatus.getNameCurrentValue();
    }

    /**
    * Returns a map of the locales and localized names of this app status.
    *
    * @return the locales and localized names of this app status
    */
    public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
        return _appStatus.getNameMap();
    }

    /**
    * Sets the name of this app status.
    *
    * @param name the name of this app status
    */
    public void setName(java.lang.String name) {
        _appStatus.setName(name);
    }

    /**
    * Sets the localized name of this app status in the language.
    *
    * @param name the localized name of this app status
    * @param locale the locale of the language
    */
    public void setName(java.lang.String name, java.util.Locale locale) {
        _appStatus.setName(name, locale);
    }

    /**
    * Sets the localized name of this app status in the language, and sets the default locale.
    *
    * @param name the localized name of this app status
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setName(java.lang.String name, java.util.Locale locale,
        java.util.Locale defaultLocale) {
        _appStatus.setName(name, locale, defaultLocale);
    }

    public void setNameCurrentLanguageId(java.lang.String languageId) {
        _appStatus.setNameCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized names of this app status from the map of locales and localized names.
    *
    * @param nameMap the locales and localized names of this app status
    */
    public void setNameMap(
        java.util.Map<java.util.Locale, java.lang.String> nameMap) {
        _appStatus.setNameMap(nameMap);
    }

    /**
    * Sets the localized names of this app status from the map of locales and localized names, and sets the default locale.
    *
    * @param nameMap the locales and localized names of this app status
    * @param defaultLocale the default locale
    */
    public void setNameMap(
        java.util.Map<java.util.Locale, java.lang.String> nameMap,
        java.util.Locale defaultLocale) {
        _appStatus.setNameMap(nameMap, defaultLocale);
    }

    /**
    * Returns the description of this app status.
    *
    * @return the description of this app status
    */
    public java.lang.String getDescription() {
        return _appStatus.getDescription();
    }

    /**
    * Returns the localized description of this app status in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized description of this app status
    */
    public java.lang.String getDescription(java.util.Locale locale) {
        return _appStatus.getDescription(locale);
    }

    /**
    * Returns the localized description of this app status in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this app status. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getDescription(java.util.Locale locale,
        boolean useDefault) {
        return _appStatus.getDescription(locale, useDefault);
    }

    /**
    * Returns the localized description of this app status in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized description of this app status
    */
    public java.lang.String getDescription(java.lang.String languageId) {
        return _appStatus.getDescription(languageId);
    }

    /**
    * Returns the localized description of this app status in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized description of this app status
    */
    public java.lang.String getDescription(java.lang.String languageId,
        boolean useDefault) {
        return _appStatus.getDescription(languageId, useDefault);
    }

    public java.lang.String getDescriptionCurrentLanguageId() {
        return _appStatus.getDescriptionCurrentLanguageId();
    }

    public java.lang.String getDescriptionCurrentValue() {
        return _appStatus.getDescriptionCurrentValue();
    }

    /**
    * Returns a map of the locales and localized descriptions of this app status.
    *
    * @return the locales and localized descriptions of this app status
    */
    public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
        return _appStatus.getDescriptionMap();
    }

    /**
    * Sets the description of this app status.
    *
    * @param description the description of this app status
    */
    public void setDescription(java.lang.String description) {
        _appStatus.setDescription(description);
    }

    /**
    * Sets the localized description of this app status in the language.
    *
    * @param description the localized description of this app status
    * @param locale the locale of the language
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale) {
        _appStatus.setDescription(description, locale);
    }

    /**
    * Sets the localized description of this app status in the language, and sets the default locale.
    *
    * @param description the localized description of this app status
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setDescription(java.lang.String description,
        java.util.Locale locale, java.util.Locale defaultLocale) {
        _appStatus.setDescription(description, locale, defaultLocale);
    }

    public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
        _appStatus.setDescriptionCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized descriptions of this app status from the map of locales and localized descriptions.
    *
    * @param descriptionMap the locales and localized descriptions of this app status
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
        _appStatus.setDescriptionMap(descriptionMap);
    }

    /**
    * Sets the localized descriptions of this app status from the map of locales and localized descriptions, and sets the default locale.
    *
    * @param descriptionMap the locales and localized descriptions of this app status
    * @param defaultLocale the default locale
    */
    public void setDescriptionMap(
        java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
        java.util.Locale defaultLocale) {
        _appStatus.setDescriptionMap(descriptionMap, defaultLocale);
    }

    public boolean isNew() {
        return _appStatus.isNew();
    }

    public void setNew(boolean n) {
        _appStatus.setNew(n);
    }

    public boolean isCachedModel() {
        return _appStatus.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _appStatus.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _appStatus.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _appStatus.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _appStatus.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _appStatus.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _appStatus.setExpandoBridgeAttributes(serviceContext);
    }

    public void prepareLocalizedFieldsForImport(
        java.util.Locale defaultImportLocale)
        throws com.liferay.portal.LocaleException {
        _appStatus.prepareLocalizedFieldsForImport(defaultImportLocale);
    }

    @Override
    public java.lang.Object clone() {
        return new AppStatusWrapper((AppStatus) _appStatus.clone());
    }

    public int compareTo(com.fidoarp.catalog.model.AppStatus appStatus) {
        return _appStatus.compareTo(appStatus);
    }

    @Override
    public int hashCode() {
        return _appStatus.hashCode();
    }

    public com.liferay.portal.model.CacheModel<com.fidoarp.catalog.model.AppStatus> toCacheModel() {
        return _appStatus.toCacheModel();
    }

    public com.fidoarp.catalog.model.AppStatus toEscapedModel() {
        return new AppStatusWrapper(_appStatus.toEscapedModel());
    }

    public com.fidoarp.catalog.model.AppStatus toUnescapedModel() {
        return new AppStatusWrapper(_appStatus.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _appStatus.toString();
    }

    public java.lang.String toXmlString() {
        return _appStatus.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _appStatus.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AppStatusWrapper)) {
            return false;
        }

        AppStatusWrapper appStatusWrapper = (AppStatusWrapper) obj;

        if (Validator.equals(_appStatus, appStatusWrapper._appStatus)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public AppStatus getWrappedAppStatus() {
        return _appStatus;
    }

    public AppStatus getWrappedModel() {
        return _appStatus;
    }

    public void resetOriginalValues() {
        _appStatus.resetOriginalValues();
    }
}
