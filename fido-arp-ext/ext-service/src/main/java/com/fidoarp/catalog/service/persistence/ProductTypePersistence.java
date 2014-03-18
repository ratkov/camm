package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.model.ProductType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the product type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductTypePersistenceImpl
 * @see ProductTypeUtil
 * @generated
 */
public interface ProductTypePersistence extends BasePersistence<ProductType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProductTypeUtil} to access the product type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the product type in the entity cache if it is enabled.
    *
    * @param productType the product type
    */
    public void cacheResult(com.fidoarp.catalog.model.ProductType productType);

    /**
    * Caches the product types in the entity cache if it is enabled.
    *
    * @param productTypes the product types
    */
    public void cacheResult(
        java.util.List<com.fidoarp.catalog.model.ProductType> productTypes);

    /**
    * Creates a new product type with the primary key. Does not add the product type to the database.
    *
    * @param productTypeId the primary key for the new product type
    * @return the new product type
    */
    public com.fidoarp.catalog.model.ProductType create(long productTypeId);

    /**
    * Removes the product type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param productTypeId the primary key of the product type
    * @return the product type that was removed
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType remove(long productTypeId)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.fidoarp.catalog.model.ProductType updateImpl(
        com.fidoarp.catalog.model.ProductType productType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product type with the primary key or throws a {@link com.fidoarp.catalog.NoSuchProductTypeException} if it could not be found.
    *
    * @param productTypeId the primary key of the product type
    * @return the product type
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType findByPrimaryKey(
        long productTypeId)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param productTypeId the primary key of the product type
    * @return the product type, or <code>null</code> if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType fetchByPrimaryKey(
        long productTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product type where productTypeCode = &#63; or throws a {@link com.fidoarp.catalog.NoSuchProductTypeException} if it could not be found.
    *
    * @param productTypeCode the product type code
    * @return the matching product type
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType findByCode(
        java.lang.String productTypeCode)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product type where productTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param productTypeCode the product type code
    * @return the matching product type, or <code>null</code> if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType fetchByCode(
        java.lang.String productTypeCode)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product type where productTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param productTypeCode the product type code
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching product type, or <code>null</code> if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType fetchByCode(
        java.lang.String productTypeCode, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the product types where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @return the matching product types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.ProductType> findByOrganizationId(
        long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the product types where organizationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param start the lower bound of the range of product types
    * @param end the upper bound of the range of product types (not inclusive)
    * @return the range of matching product types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.ProductType> findByOrganizationId(
        long organizationId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the product types where organizationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param start the lower bound of the range of product types
    * @param end the upper bound of the range of product types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching product types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.ProductType> findByOrganizationId(
        long organizationId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first product type in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching product type
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType findByOrganizationId_First(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first product type in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching product type, or <code>null</code> if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType fetchByOrganizationId_First(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last product type in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching product type
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType findByOrganizationId_Last(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last product type in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching product type, or <code>null</code> if a matching product type could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType fetchByOrganizationId_Last(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the product types before and after the current product type in the ordered set where organizationId = &#63;.
    *
    * @param productTypeId the primary key of the current product type
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next product type
    * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType[] findByOrganizationId_PrevAndNext(
        long productTypeId, long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the product types.
    *
    * @return the product types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.ProductType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.fidoarp.catalog.model.ProductType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the product types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of product types
    * @param end the upper bound of the range of product types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of product types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.ProductType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the product type where productTypeCode = &#63; from the database.
    *
    * @param productTypeCode the product type code
    * @return the product type that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.ProductType removeByCode(
        java.lang.String productTypeCode)
        throws com.fidoarp.catalog.NoSuchProductTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the product types where organizationId = &#63; from the database.
    *
    * @param organizationId the organization ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByOrganizationId(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the product types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of product types where productTypeCode = &#63;.
    *
    * @param productTypeCode the product type code
    * @return the number of matching product types
    * @throws SystemException if a system exception occurred
    */
    public int countByCode(java.lang.String productTypeCode)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of product types where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @return the number of matching product types
    * @throws SystemException if a system exception occurred
    */
    public int countByOrganizationId(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of product types.
    *
    * @return the number of product types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
