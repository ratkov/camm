package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.model.AppStatus;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppStatusPersistenceImpl
 * @see AppStatusUtil
 * @generated
 */
public interface AppStatusPersistence extends BasePersistence<AppStatus> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AppStatusUtil} to access the app status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the app status in the entity cache if it is enabled.
    *
    * @param appStatus the app status
    */
    public void cacheResult(com.fidoarp.catalog.model.AppStatus appStatus);

    /**
    * Caches the app statuses in the entity cache if it is enabled.
    *
    * @param appStatuses the app statuses
    */
    public void cacheResult(
        java.util.List<com.fidoarp.catalog.model.AppStatus> appStatuses);

    /**
    * Creates a new app status with the primary key. Does not add the app status to the database.
    *
    * @param appStatusId the primary key for the new app status
    * @return the new app status
    */
    public com.fidoarp.catalog.model.AppStatus create(long appStatusId);

    /**
    * Removes the app status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status that was removed
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus remove(long appStatusId)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.fidoarp.catalog.model.AppStatus updateImpl(
        com.fidoarp.catalog.model.AppStatus appStatus, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus findByPrimaryKey(
        long appStatusId)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status, or <code>null</code> if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus fetchByPrimaryKey(
        long appStatusId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status where appStatusCode = &#63; or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
    *
    * @param appStatusCode the app status code
    * @return the matching app status
    * @throws com.fidoarp.catalog.NoSuchAppStatusException if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus findByCode(
        java.lang.String appStatusCode)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param appStatusCode the app status code
    * @return the matching app status, or <code>null</code> if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus fetchByCode(
        java.lang.String appStatusCode)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param appStatusCode the app status code
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching app status, or <code>null</code> if a matching app status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus fetchByCode(
        java.lang.String appStatusCode, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the app statuses.
    *
    * @return the app statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.AppStatus> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.fidoarp.catalog.model.AppStatus> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.fidoarp.catalog.model.AppStatus> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the app status where appStatusCode = &#63; from the database.
    *
    * @param appStatusCode the app status code
    * @return the app status that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus removeByCode(
        java.lang.String appStatusCode)
        throws com.fidoarp.catalog.NoSuchAppStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the app statuses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of app statuses where appStatusCode = &#63;.
    *
    * @param appStatusCode the app status code
    * @return the number of matching app statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByCode(java.lang.String appStatusCode)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of app statuses.
    *
    * @return the number of app statuses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
