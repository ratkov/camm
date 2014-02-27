package com.fidoarp.catalog.model.impl;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the ProductType service. Represents a row in the &quot;fido_product_type&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProductTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductTypeImpl
 * @see com.fidoarp.catalog.model.ProductType
 * @generated
 */
public abstract class ProductTypeBaseImpl extends ProductTypeModelImpl
    implements ProductType {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a product type model instance should use the {@link ProductType} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProductTypeLocalServiceUtil.addProductType(this);
        } else {
            ProductTypeLocalServiceUtil.updateProductType(this);
        }
    }
}
