package com.fidoarp.catalog.service.impl;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.base.ProductTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the product type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fidoarp.catalog.service.ProductTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.base.ProductTypeLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.ProductTypeLocalServiceUtil
 */
public class ProductTypeLocalServiceImpl extends ProductTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.fidoarp.catalog.service.ProductTypeLocalServiceUtil} to access the product type local service.
     */

    public List<ProductType> getListProductTypeByOrganizationId(long organizationId){
        try {
            return productTypePersistence.findByOrganizationId(organizationId);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductType> getListProductTypeByOrganizationIdStatus(long organizationId, boolean status){
        try {
            return productTypePersistence.findByOrganizationIdStatus(organizationId, status);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductType getProductTypeByCode(String code){
        try {
            return productTypePersistence.findByCode(code);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

}
