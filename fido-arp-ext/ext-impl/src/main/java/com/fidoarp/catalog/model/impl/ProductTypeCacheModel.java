package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.ProductType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ProductType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProductType
 * @generated
 */
public class ProductTypeCacheModel implements CacheModel<ProductType>,
    Serializable {
    public long productTypeId;
    public String productTypeCode;
    public String name;
    public String description;
    public long organizationId;
    public long ddmtemplateId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{productTypeId=");
        sb.append(productTypeId);
        sb.append(", productTypeCode=");
        sb.append(productTypeCode);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", organizationId=");
        sb.append(organizationId);
        sb.append(", ddmtemplateId=");
        sb.append(ddmtemplateId);
        sb.append("}");

        return sb.toString();
    }

    public ProductType toEntityModel() {
        ProductTypeImpl productTypeImpl = new ProductTypeImpl();

        productTypeImpl.setProductTypeId(productTypeId);

        if (productTypeCode == null) {
            productTypeImpl.setProductTypeCode(StringPool.BLANK);
        } else {
            productTypeImpl.setProductTypeCode(productTypeCode);
        }

        if (name == null) {
            productTypeImpl.setName(StringPool.BLANK);
        } else {
            productTypeImpl.setName(name);
        }

        if (description == null) {
            productTypeImpl.setDescription(StringPool.BLANK);
        } else {
            productTypeImpl.setDescription(description);
        }

        productTypeImpl.setOrganizationId(organizationId);
        productTypeImpl.setDdmtemplateId(ddmtemplateId);

        productTypeImpl.resetOriginalValues();

        return productTypeImpl;
    }
}
