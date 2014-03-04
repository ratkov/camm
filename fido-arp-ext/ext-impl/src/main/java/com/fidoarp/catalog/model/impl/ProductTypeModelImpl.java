package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.model.ProductTypeModel;

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
 * The base model implementation for the ProductType service. Represents a row in the &quot;fido_product_type&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.fidoarp.catalog.model.ProductTypeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductTypeImpl
 * @see com.fidoarp.catalog.model.ProductType
 * @see com.fidoarp.catalog.model.ProductTypeModel
 * @generated
 */
public class ProductTypeModelImpl extends BaseModelImpl<ProductType>
    implements ProductTypeModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a product type model instance should use the {@link com.fidoarp.catalog.model.ProductType} interface instead.
     */
    public static final String TABLE_NAME = "fido_product_type";
    public static final Object[][] TABLE_COLUMNS = {
            { "id", Types.BIGINT },
            { "code", Types.VARCHAR },
            { "name", Types.VARCHAR },
            { "description", Types.VARCHAR },
            { "organization_id", Types.BIGINT },
            { "ddmtemplate_id", Types.BIGINT }
        };
    public static final String TABLE_SQL_CREATE = "create table fido_product_type (id LONG not null primary key,code VARCHAR(75) null,name STRING null,description VARCHAR(75) null,organization_id LONG,ddmtemplate_id LONG)";
    public static final String TABLE_SQL_DROP = "drop table fido_product_type";
    public static final String ORDER_BY_JPQL = " ORDER BY productType.productTypeId ASC";
    public static final String ORDER_BY_SQL = " ORDER BY fido_product_type.id ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.fidoarp.catalog.model.ProductType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.fidoarp.catalog.model.ProductType"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.column.bitmask.enabled.com.fidoarp.catalog.model.ProductType"),
            true);
    public static long PRODUCTTYPECODE_COLUMN_BITMASK = 1L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.fidoarp.catalog.model.ProductType"));
    private static ClassLoader _classLoader = ProductType.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            ProductType.class
        };
    private long _productTypeId;
    private String _productTypeCode;
    private String _originalProductTypeCode;
    private String _name;
    private String _nameCurrentLanguageId;
    private String _description;
    private long _organizationId;
    private long _ddmtemplateId;
    private long _columnBitmask;
    private ProductType _escapedModelProxy;

    public ProductTypeModelImpl() {
    }

    public long getPrimaryKey() {
        return _productTypeId;
    }

    public void setPrimaryKey(long primaryKey) {
        setProductTypeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_productTypeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return ProductType.class;
    }

    public String getModelClassName() {
        return ProductType.class.getName();
    }

    @Override
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

    @Override
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

    public long getProductTypeId() {
        return _productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
        _columnBitmask = -1L;

        _productTypeId = productTypeId;
    }

    public String getProductTypeCode() {
        if (_productTypeCode == null) {
            return StringPool.BLANK;
        } else {
            return _productTypeCode;
        }
    }

    public void setProductTypeCode(String productTypeCode) {
        _columnBitmask |= PRODUCTTYPECODE_COLUMN_BITMASK;

        if (_originalProductTypeCode == null) {
            _originalProductTypeCode = _productTypeCode;
        }

        _productTypeCode = productTypeCode;
    }

    public String getOriginalProductTypeCode() {
        return GetterUtil.getString(_originalProductTypeCode);
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

    public long getOrganizationId() {
        return _organizationId;
    }

    public void setOrganizationId(long organizationId) {
        _organizationId = organizationId;
    }

    public long getDdmtemplateId() {
        return _ddmtemplateId;
    }

    public void setDdmtemplateId(long ddmtemplateId) {
        _ddmtemplateId = ddmtemplateId;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            ProductType.class.getName(), getPrimaryKey());
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
    public ProductType toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (ProductType) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public Object clone() {
        ProductTypeImpl productTypeImpl = new ProductTypeImpl();

        productTypeImpl.setProductTypeId(getProductTypeId());
        productTypeImpl.setProductTypeCode(getProductTypeCode());
        productTypeImpl.setName(getName());
        productTypeImpl.setDescription(getDescription());
        productTypeImpl.setOrganizationId(getOrganizationId());
        productTypeImpl.setDdmtemplateId(getDdmtemplateId());

        productTypeImpl.resetOriginalValues();

        return productTypeImpl;
    }

    public int compareTo(ProductType productType) {
        int value = 0;

        if (getProductTypeId() < productType.getProductTypeId()) {
            value = -1;
        } else if (getProductTypeId() > productType.getProductTypeId()) {
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

        ProductType productType = null;

        try {
            productType = (ProductType) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = productType.getPrimaryKey();

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
        ProductTypeModelImpl productTypeModelImpl = this;

        productTypeModelImpl._originalProductTypeCode = productTypeModelImpl._productTypeCode;

        productTypeModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<ProductType> toCacheModel() {
        ProductTypeCacheModel productTypeCacheModel = new ProductTypeCacheModel();

        productTypeCacheModel.productTypeId = getProductTypeId();

        productTypeCacheModel.productTypeCode = getProductTypeCode();

        String productTypeCode = productTypeCacheModel.productTypeCode;

        if ((productTypeCode != null) && (productTypeCode.length() == 0)) {
            productTypeCacheModel.productTypeCode = null;
        }

        productTypeCacheModel.name = getName();

        String name = productTypeCacheModel.name;

        if ((name != null) && (name.length() == 0)) {
            productTypeCacheModel.name = null;
        }

        productTypeCacheModel.description = getDescription();

        String description = productTypeCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            productTypeCacheModel.description = null;
        }

        productTypeCacheModel.organizationId = getOrganizationId();

        productTypeCacheModel.ddmtemplateId = getDdmtemplateId();

        return productTypeCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{productTypeId=");
        sb.append(getProductTypeId());
        sb.append(", productTypeCode=");
        sb.append(getProductTypeCode());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", organizationId=");
        sb.append(getOrganizationId());
        sb.append(", ddmtemplateId=");
        sb.append(getDdmtemplateId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.fidoarp.catalog.model.ProductType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>productTypeId</column-name><column-value><![CDATA[");
        sb.append(getProductTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productTypeCode</column-name><column-value><![CDATA[");
        sb.append(getProductTypeCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationId</column-name><column-value><![CDATA[");
        sb.append(getOrganizationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ddmtemplateId</column-name><column-value><![CDATA[");
        sb.append(getDdmtemplateId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
