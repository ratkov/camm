package com.fidoarp.catalog.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProductType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProductType
 * @generated
 */
public class ProductTypeWrapper implements ProductType,
    ModelWrapper<ProductType> {
    private ProductType _productType;

    public ProductTypeWrapper(ProductType productType) {
        _productType = productType;
    }

    public Class<?> getModelClass() {
        return ProductType.class;
    }

    public String getModelClassName() {
        return ProductType.class.getName();
    }

    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productTypeId", getProductTypeId());
        attributes.put("productTypeCode", getProductTypeCode());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("organizationId", getOrganizationId());
        attributes.put("ddmtemplateId", getDdmtemplateId());

        return attributes;
    }

    public void setModelAttributes(Map<String, Object> attributes) {
        Long productTypeId = (Long) attributes.get("productTypeId");

        if (productTypeId != null) {
            setProductTypeId(productTypeId);
        }

        String productTypeCode = (String) attributes.get("productTypeCode");

        if (productTypeCode != null) {
            setProductTypeCode(productTypeCode);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long organizationId = (Long) attributes.get("organizationId");

        if (organizationId != null) {
            setOrganizationId(organizationId);
        }

        Long ddmtemplateId = (Long) attributes.get("ddmtemplateId");

        if (ddmtemplateId != null) {
            setDdmtemplateId(ddmtemplateId);
        }
    }

    /**
    * Returns the primary key of this product type.
    *
    * @return the primary key of this product type
    */
    public long getPrimaryKey() {
        return _productType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this product type.
    *
    * @param primaryKey the primary key of this product type
    */
    public void setPrimaryKey(long primaryKey) {
        _productType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the product type ID of this product type.
    *
    * @return the product type ID of this product type
    */
    public long getProductTypeId() {
        return _productType.getProductTypeId();
    }

    /**
    * Sets the product type ID of this product type.
    *
    * @param productTypeId the product type ID of this product type
    */
    public void setProductTypeId(long productTypeId) {
        _productType.setProductTypeId(productTypeId);
    }

    /**
    * Returns the product type code of this product type.
    *
    * @return the product type code of this product type
    */
    public java.lang.String getProductTypeCode() {
        return _productType.getProductTypeCode();
    }

    /**
    * Sets the product type code of this product type.
    *
    * @param productTypeCode the product type code of this product type
    */
    public void setProductTypeCode(java.lang.String productTypeCode) {
        _productType.setProductTypeCode(productTypeCode);
    }

    /**
    * Returns the name of this product type.
    *
    * @return the name of this product type
    */
    public java.lang.String getName() {
        return _productType.getName();
    }

    /**
    * Returns the localized name of this product type in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param locale the locale of the language
    * @return the localized name of this product type
    */
    public java.lang.String getName(java.util.Locale locale) {
        return _productType.getName(locale);
    }

    /**
    * Returns the localized name of this product type in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param locale the local of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized name of this product type. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
    */
    public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
        return _productType.getName(locale, useDefault);
    }

    /**
    * Returns the localized name of this product type in the language. Uses the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @return the localized name of this product type
    */
    public java.lang.String getName(java.lang.String languageId) {
        return _productType.getName(languageId);
    }

    /**
    * Returns the localized name of this product type in the language, optionally using the default language if no localization exists for the requested language.
    *
    * @param languageId the ID of the language
    * @param useDefault whether to use the default language if no localization exists for the requested language
    * @return the localized name of this product type
    */
    public java.lang.String getName(java.lang.String languageId,
        boolean useDefault) {
        return _productType.getName(languageId, useDefault);
    }

    public java.lang.String getNameCurrentLanguageId() {
        return _productType.getNameCurrentLanguageId();
    }

    public java.lang.String getNameCurrentValue() {
        return _productType.getNameCurrentValue();
    }

    /**
    * Returns a map of the locales and localized names of this product type.
    *
    * @return the locales and localized names of this product type
    */
    public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
        return _productType.getNameMap();
    }

    /**
    * Sets the name of this product type.
    *
    * @param name the name of this product type
    */
    public void setName(java.lang.String name) {
        _productType.setName(name);
    }

    /**
    * Sets the localized name of this product type in the language.
    *
    * @param name the localized name of this product type
    * @param locale the locale of the language
    */
    public void setName(java.lang.String name, java.util.Locale locale) {
        _productType.setName(name, locale);
    }

    /**
    * Sets the localized name of this product type in the language, and sets the default locale.
    *
    * @param name the localized name of this product type
    * @param locale the locale of the language
    * @param defaultLocale the default locale
    */
    public void setName(java.lang.String name, java.util.Locale locale,
        java.util.Locale defaultLocale) {
        _productType.setName(name, locale, defaultLocale);
    }

    public void setNameCurrentLanguageId(java.lang.String languageId) {
        _productType.setNameCurrentLanguageId(languageId);
    }

    /**
    * Sets the localized names of this product type from the map of locales and localized names.
    *
    * @param nameMap the locales and localized names of this product type
    */
    public void setNameMap(
        java.util.Map<java.util.Locale, java.lang.String> nameMap) {
        _productType.setNameMap(nameMap);
    }

    /**
    * Sets the localized names of this product type from the map of locales and localized names, and sets the default locale.
    *
    * @param nameMap the locales and localized names of this product type
    * @param defaultLocale the default locale
    */
    public void setNameMap(
        java.util.Map<java.util.Locale, java.lang.String> nameMap,
        java.util.Locale defaultLocale) {
        _productType.setNameMap(nameMap, defaultLocale);
    }

    /**
    * Returns the description of this product type.
    *
    * @return the description of this product type
    */
    public java.lang.String getDescription() {
        return _productType.getDescription();
    }

    /**
    * Sets the description of this product type.
    *
    * @param description the description of this product type
    */
    public void setDescription(java.lang.String description) {
        _productType.setDescription(description);
    }

    /**
    * Returns the organization ID of this product type.
    *
    * @return the organization ID of this product type
    */
    public long getOrganizationId() {
        return _productType.getOrganizationId();
    }

    /**
    * Sets the organization ID of this product type.
    *
    * @param organizationId the organization ID of this product type
    */
    public void setOrganizationId(long organizationId) {
        _productType.setOrganizationId(organizationId);
    }

    /**
    * Returns the ddmtemplate ID of this product type.
    *
    * @return the ddmtemplate ID of this product type
    */
    public long getDdmtemplateId() {
        return _productType.getDdmtemplateId();
    }

    /**
    * Sets the ddmtemplate ID of this product type.
    *
    * @param ddmtemplateId the ddmtemplate ID of this product type
    */
    public void setDdmtemplateId(long ddmtemplateId) {
        _productType.setDdmtemplateId(ddmtemplateId);
    }

    public boolean isNew() {
        return _productType.isNew();
    }

    public void setNew(boolean n) {
        _productType.setNew(n);
    }

    public boolean isCachedModel() {
        return _productType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _productType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _productType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _productType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _productType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _productType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _productType.setExpandoBridgeAttributes(serviceContext);
    }

    public void prepareLocalizedFieldsForImport(
        java.util.Locale defaultImportLocale)
        throws com.liferay.portal.LocaleException {
        _productType.prepareLocalizedFieldsForImport(defaultImportLocale);
    }

    @Override
    public java.lang.Object clone() {
        return new ProductTypeWrapper((ProductType) _productType.clone());
    }

    public int compareTo(ProductType productType) {
        return _productType.compareTo(productType);
    }

    @Override
    public int hashCode() {
        return _productType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProductType> toCacheModel() {
        return _productType.toCacheModel();
    }

    public ProductType toEscapedModel() {
        return new ProductTypeWrapper(_productType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _productType.toString();
    }

    public java.lang.String toXmlString() {
        return _productType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _productType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProductType getWrappedProductType() {
        return _productType;
    }

    public ProductType getWrappedModel() {
        return _productType;
    }

    public void resetOriginalValues() {
        _productType.resetOriginalValues();
    }
}
