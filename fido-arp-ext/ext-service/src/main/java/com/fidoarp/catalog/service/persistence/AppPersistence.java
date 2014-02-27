package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.model.App;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPersistenceImpl
 * @see AppUtil
 * @generated
 */
public interface AppPersistence extends BasePersistence<App> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AppUtil} to access the app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the app in the entity cache if it is enabled.
    *
    * @param app the app
    */
    public void cacheResult(com.fidoarp.catalog.model.App app);

    /**
    * Caches the apps in the entity cache if it is enabled.
    *
    * @param apps the apps
    */
    public void cacheResult(java.util.List<com.fidoarp.catalog.model.App> apps);

    /**
    * Creates a new app with the primary key. Does not add the app to the database.
    *
    * @param appId the primary key for the new app
    * @return the new app
    */
    public com.fidoarp.catalog.model.App create(long appId);

    /**
    * Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appId the primary key of the app
    * @return the app that was removed
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App remove(long appId)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.fidoarp.catalog.model.App updateImpl(
        com.fidoarp.catalog.model.App app, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppException} if it could not be found.
    *
    * @param appId the primary key of the app
    * @return the app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App findByPrimaryKey(long appId)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param appId the primary key of the app
    * @return the app, or <code>null</code> if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App fetchByPrimaryKey(long appId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the apps where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findByUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the apps where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @return the range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findByUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the apps where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findByUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App findByUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App fetchByUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App findByUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App fetchByUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the apps before and after the current app in the ordered set where userId = &#63;.
    *
    * @param appId the primary key of the current app
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App[] findByUser_PrevAndNext(long appId,
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the apps.
    *
    * @return the apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the apps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @return the range of apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the apps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of apps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.fidoarp.catalog.model.App> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the apps where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the apps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of apps where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching apps
    * @throws SystemException if a system exception occurred
    */
    public int countByUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of apps.
    *
    * @return the number of apps
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
