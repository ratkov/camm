package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.model.AppStatus;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app status service. This utility wraps {@link AppStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppStatusPersistence
 * @see AppStatusPersistenceImpl
 * @generated
 */
public class AppStatusUtil {
    private static AppStatusPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(AppStatus appStatus) {
        getPersistence().clearCache(appStatus);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<AppStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AppStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AppStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static AppStatus update(AppStatus appStatus, boolean merge)
        throws SystemException {
        return getPersistence().update(appStatus, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static AppStatus update(AppStatus appStatus, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(appStatus, merge, serviceContext);
    }

    /**
    * Caches the app status in the entity cache if it is enabled.
    *
    * @param appStatus the app status
    */
    public static void cacheResult(
        com.fidoarp.catalog.model.AppStatus appStatus) {
        getPersistence().cacheResult(appStatus);
    }

    /**
    * Caches the app statuses in the entity cache if it is enabled.
    *
    * @param appStatuses the app statuses
    */
    public static void cacheResult(
        java.util.List<com.fidoarp.catalog.model.AppStatus> appStatuses) {
        getPersistence().cacheResult(appStatuses);
    }

    /**
    * Creates a new app status with the primary key. Does not add the app status to the database.
    *
    * @param appStatusId the primary key for the new app status
    * @return the new app status
    */
    public static com.fidoarp.catalog.model.AppStatus create(long appStatusId) {
        return getPersistence().create(appStatusId);
    }

    /**
    * Removes the app status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status that was removed
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus remove(long appStatusId)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(appStatusId);
    }

    public static com.fidoarp.catalog.model.AppStatus updateImpl(
        com.fidoarp.catalog.model.AppStatus appStatus, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(appStatus, merge);
    }

    /**
    * Returns the app status with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus findByPrimaryKey(
        long appStatusId)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(appStatusId);
    }

    /**
    * Returns the app status with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status, or <code>null</code> if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus fetchByPrimaryKey(
        long appStatusId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(appStatusId);
    }

    /**
    * Returns the app status where appStatusCode = &#63; or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
    *
    * @param appStatusCode the app status code
    * @return the matching app status
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus findByCode(
        java.lang.String appStatusCode)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCode(appStatusCode);
    }

    /**
    * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param appStatusCode the app status code
    * @return the matching app status, or <code>null</code> if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus fetchByCode(
        java.lang.String appStatusCode)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCode(appStatusCode);
    }

    /**
    * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param appStatusCode the app status code
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching app status, or <code>null</code> if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus fetchByCode(
        java.lang.String appStatusCode, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCode(appStatusCode, retrieveFromCache);
    }

    /**
    * Returns all the app statuses.
    *
    * @return the app statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.AppStatus> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the app statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of app statuses
    * @param end the upper bound of the range of app statuses (not inclusive)
    * @return the range of app statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.AppStatus> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the app statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of app statuses
    * @param end the upper bound of the range of app statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of app statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.AppStatus> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the app status where appStatusCode = &#63; from the database.
    *
    * @param appStatusCode the app status code
    * @return the app status that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.AppStatus removeByCode(
        java.lang.String appStatusCode)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByCode(appStatusCode);
    }

    /**
    * Removes all the app statuses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of app statuses where appStatusCode = &#63;.
    *
    * @param appStatusCode the app status code
    * @return the number of matching app statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCode(java.lang.String appStatusCode)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCode(appStatusCode);
    }

    /**
    * Returns the number of app statuses.
    *
    * @return the number of app statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AppStatusPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AppStatusPersistence) PortalBeanLocatorUtil.locate(AppStatusPersistence.class.getName());

            ReferenceRegistry.registerReference(AppStatusUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated
     */
    public void setPersistence(AppStatusPersistence persistence) {
    }
}
