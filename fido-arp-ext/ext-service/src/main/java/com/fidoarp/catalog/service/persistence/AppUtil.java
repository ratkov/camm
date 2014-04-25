package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.model.App;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app service. This utility wraps {@link AppPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPersistence
 * @see AppPersistenceImpl
 * @generated
 */
public class AppUtil {
    private static AppPersistence _persistence;

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
    public static void clearCache(App app) {
        getPersistence().clearCache(app);
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
    public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<App> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static App update(App app, boolean merge) throws SystemException {
        return getPersistence().update(app, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static App update(App app, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(app, merge, serviceContext);
    }

    /**
    * Caches the app in the entity cache if it is enabled.
    *
    * @param app the app
    */
    public static void cacheResult(com.fidoarp.catalog.model.App app) {
        getPersistence().cacheResult(app);
    }

    /**
    * Caches the apps in the entity cache if it is enabled.
    *
    * @param apps the apps
    */
    public static void cacheResult(
        java.util.List<com.fidoarp.catalog.model.App> apps) {
        getPersistence().cacheResult(apps);
    }

    /**
    * Creates a new app with the primary key. Does not add the app to the database.
    *
    * @param appId the primary key for the new app
    * @return the new app
    */
    public static com.fidoarp.catalog.model.App create(long appId) {
        return getPersistence().create(appId);
    }

    /**
    * Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appId the primary key of the app
    * @return the app that was removed
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App remove(long appId)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(appId);
    }

    public static com.fidoarp.catalog.model.App updateImpl(
        com.fidoarp.catalog.model.App app, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(app, merge);
    }

    /**
    * Returns the app with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppException} if it could not be found.
    *
    * @param appId the primary key of the app
    * @return the app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByPrimaryKey(long appId)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(appId);
    }

    /**
    * Returns the app with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param appId the primary key of the app
    * @return the app, or <code>null</code> if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByPrimaryKey(long appId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(appId);
    }

    /**
    * Returns all the apps where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUser(userId);
    }

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
    public static java.util.List<com.fidoarp.catalog.model.App> findByUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUser(userId, start, end);
    }

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
    public static java.util.List<com.fidoarp.catalog.model.App> findByUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUser(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUser_First(userId, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUser_First(userId, orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUser_Last(userId, orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUser_Last(userId, orderByComparator);
    }

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
    public static com.fidoarp.catalog.model.App[] findByUser_PrevAndNext(
        long appId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUser_PrevAndNext(appId, userId, orderByComparator);
    }

    /**
    * Returns all the apps where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @return the matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganization(
        long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByOrganization(organizationId);
    }

    /**
    * Returns a range of all the apps where organizationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @return the range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganization(
        long organizationId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByOrganization(organizationId, start, end);
    }

    /**
    * Returns an ordered range of all the apps where organizationId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganization(
        long organizationId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganization(organizationId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganization_First(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganization_First(organizationId, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganization_First(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganization_First(organizationId, orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganization_Last(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganization_Last(organizationId, orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganization_Last(
        long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganization_Last(organizationId, orderByComparator);
    }

    /**
    * Returns the apps before and after the current app in the ordered set where organizationId = &#63;.
    *
    * @param appId the primary key of the current app
    * @param organizationId the organization ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App[] findByOrganization_PrevAndNext(
        long appId, long organizationId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganization_PrevAndNext(appId, organizationId,
            orderByComparator);
    }

    /**
    * Returns all the apps where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @return the matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByMonth(
        long organizationId, java.util.Date createdDate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth(organizationId, createdDate);
    }

    /**
    * Returns a range of all the apps where organizationId = &#63; and createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @return the range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByMonth(
        long organizationId, java.util.Date createdDate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth(organizationId, createdDate,
            start, end);
    }

    /**
    * Returns an ordered range of all the apps where organizationId = &#63; and createdDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByMonth(
        long organizationId, java.util.Date createdDate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth(organizationId, createdDate,
            start, end, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganizationByMonth_First(
        long organizationId, java.util.Date createdDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth_First(organizationId,
            createdDate, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganizationByMonth_First(
        long organizationId, java.util.Date createdDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganizationByMonth_First(organizationId,
            createdDate, orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganizationByMonth_Last(
        long organizationId, java.util.Date createdDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth_Last(organizationId, createdDate,
            orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganizationByMonth_Last(
        long organizationId, java.util.Date createdDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganizationByMonth_Last(organizationId,
            createdDate, orderByComparator);
    }

    /**
    * Returns the apps before and after the current app in the ordered set where organizationId = &#63; and createdDate = &#63;.
    *
    * @param appId the primary key of the current app
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App[] findByOrganizationByMonth_PrevAndNext(
        long appId, long organizationId, java.util.Date createdDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByMonth_PrevAndNext(appId,
            organizationId, createdDate, orderByComparator);
    }

    /**
    * Returns all the apps where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @return the matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByStatus(
        long organizationId, long statusId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus(organizationId, statusId);
    }

    /**
    * Returns a range of all the apps where organizationId = &#63; and statusId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @return the range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByStatus(
        long organizationId, long statusId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus(organizationId, statusId, start,
            end);
    }

    /**
    * Returns an ordered range of all the apps where organizationId = &#63; and statusId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param start the lower bound of the range of apps
    * @param end the upper bound of the range of apps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findByOrganizationByStatus(
        long organizationId, long statusId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus(organizationId, statusId, start,
            end, orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganizationByStatus_First(
        long organizationId, long statusId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus_First(organizationId, statusId,
            orderByComparator);
    }

    /**
    * Returns the first app in the ordered set where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganizationByStatus_First(
        long organizationId, long statusId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganizationByStatus_First(organizationId, statusId,
            orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app
    * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App findByOrganizationByStatus_Last(
        long organizationId, long statusId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus_Last(organizationId, statusId,
            orderByComparator);
    }

    /**
    * Returns the last app in the ordered set where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching app, or <code>null</code> if a matching app could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App fetchByOrganizationByStatus_Last(
        long organizationId, long statusId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOrganizationByStatus_Last(organizationId, statusId,
            orderByComparator);
    }

    /**
    * Returns the apps before and after the current app in the ordered set where organizationId = &#63; and statusId = &#63;.
    *
    * @param appId the primary key of the current app
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next app
    * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App[] findByOrganizationByStatus_PrevAndNext(
        long appId, long organizationId, long statusId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.fidoarp.catalog.NoSuchAppException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOrganizationByStatus_PrevAndNext(appId,
            organizationId, statusId, orderByComparator);
    }

    /**
    * Returns all the apps.
    *
    * @return the apps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.fidoarp.catalog.model.App> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.fidoarp.catalog.model.App> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.fidoarp.catalog.model.App> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the apps where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUser(userId);
    }

    /**
    * Removes all the apps where organizationId = &#63; from the database.
    *
    * @param organizationId the organization ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByOrganization(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByOrganization(organizationId);
    }

    /**
    * Removes all the apps where organizationId = &#63; and createdDate = &#63; from the database.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByOrganizationByMonth(long organizationId,
        java.util.Date createdDate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByOrganizationByMonth(organizationId, createdDate);
    }

    /**
    * Removes all the apps where organizationId = &#63; and statusId = &#63; from the database.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByOrganizationByStatus(long organizationId,
        long statusId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByOrganizationByStatus(organizationId, statusId);
    }

    /**
    * Removes all the apps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of apps where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static int countByUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUser(userId);
    }

    /**
    * Returns the number of apps where organizationId = &#63;.
    *
    * @param organizationId the organization ID
    * @return the number of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static int countByOrganization(long organizationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByOrganization(organizationId);
    }

    /**
    * Returns the number of apps where organizationId = &#63; and createdDate = &#63;.
    *
    * @param organizationId the organization ID
    * @param createdDate the created date
    * @return the number of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static int countByOrganizationByMonth(long organizationId,
        java.util.Date createdDate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByOrganizationByMonth(organizationId, createdDate);
    }

    /**
    * Returns the number of apps where organizationId = &#63; and statusId = &#63;.
    *
    * @param organizationId the organization ID
    * @param statusId the status ID
    * @return the number of matching apps
    * @throws SystemException if a system exception occurred
    */
    public static int countByOrganizationByStatus(long organizationId,
        long statusId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByOrganizationByStatus(organizationId, statusId);
    }

    /**
    * Returns the number of apps.
    *
    * @return the number of apps
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AppPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AppPersistence) PortalBeanLocatorUtil.locate(AppPersistence.class.getName());

            ReferenceRegistry.registerReference(AppUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated
     */
    public void setPersistence(AppPersistence persistence) {
    }
}
