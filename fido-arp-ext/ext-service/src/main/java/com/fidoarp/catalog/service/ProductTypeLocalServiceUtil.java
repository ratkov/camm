package com.fidoarp.catalog.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the product type local service. This utility wraps {@link com.fidoarp.catalog.service.impl.ProductTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductTypeLocalService
 * @see com.fidoarp.catalog.service.base.ProductTypeLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.impl.ProductTypeLocalServiceImpl
 * @generated
 */
public class ProductTypeLocalServiceUtil {
    private static ProductTypeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.fidoarp.catalog.service.impl.ProductTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the product type to the database. Also notifies the appropriate model listeners.
    *
    * @param productType the product type
    * @return the product type that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType addProductType(
        com.fidoarp.catalog.model.ProductType productType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addProductType(productType);
    }

    /**
    * Creates a new product type with the primary key. Does not add the product type to the database.
    *
    * @param productTypeId the primary key for the new product type
    * @return the new product type
    */
    public static com.fidoarp.catalog.model.ProductType createProductType(
        long productTypeId) {
        return getService().createProductType(productTypeId);
    }

    /**
    * Deletes the product type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param productTypeId the primary key of the product type
    * @return the product type that was removed
    * @throws PortalException if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType deleteProductType(
        long productTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProductType(productTypeId);
    }

    /**
    * Deletes the product type from the database. Also notifies the appropriate model listeners.
    *
    * @param productType the product type
    * @return the product type that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType deleteProductType(
        com.fidoarp.catalog.model.ProductType productType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteProductType(productType);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static com.fidoarp.catalog.model.ProductType fetchProductType(
        long productTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProductType(productTypeId);
    }

    /**
    * Returns the product type with the primary key.
    *
    * @param productTypeId the primary key of the product type
    * @return the product type
    * @throws PortalException if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType getProductType(
        long productTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProductType(productTypeId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the product types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of product types
    * @param end the upper bound of the range of product types (not inclusive)
    * @return the range of product types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.ProductType> getProductTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProductTypes(start, end);
    }

    /**
    * Returns the number of product types.
    *
    * @return the number of product types
    * @throws SystemException if a system exception occurred
    */
    public static int getProductTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProductTypesCount();
    }

    /**
    * Updates the product type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param productType the product type
    * @return the product type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType updateProductType(
        com.fidoarp.catalog.model.ProductType productType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProductType(productType);
    }

    /**
    * Updates the product type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param productType the product type
    * @param merge whether to merge the product type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the product type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.ProductType updateProductType(
        com.fidoarp.catalog.model.ProductType productType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateProductType(productType, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.util.List<com.fidoarp.catalog.model.ProductType> getListProductTypeByOrganizationId(
        long organizationId) {
        return getService().getListProductTypeByOrganizationId(organizationId);
    }

    public static java.util.List<com.fidoarp.catalog.model.ProductType> getListProductTypeByOrganizationIdStatus(
        long organizationId, boolean status) {
        return getService()
                   .getListProductTypeByOrganizationIdStatus(organizationId,
            status);
    }

    public static com.fidoarp.catalog.model.ProductType getProductTypeByCode(
        java.lang.String code) {
        return getService().getProductTypeByCode(code);
    }

    public static ProductTypeLocalService getService() {
        if (_service == null) {
            _service = (ProductTypeLocalService) PortalBeanLocatorUtil.locate(ProductTypeLocalService.class.getName());

            ReferenceRegistry.registerReference(ProductTypeLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(ProductTypeLocalService service) {
    }
}
