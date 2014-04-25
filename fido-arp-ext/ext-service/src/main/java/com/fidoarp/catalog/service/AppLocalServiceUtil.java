package com.fidoarp.catalog.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the app local service. This utility wraps {@link com.fidoarp.catalog.service.impl.AppLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppLocalService
 * @see com.fidoarp.catalog.service.base.AppLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.impl.AppLocalServiceImpl
 * @generated
 */
public class AppLocalServiceUtil {
    private static AppLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.fidoarp.catalog.service.impl.AppLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the app to the database. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App addApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addApp(app);
    }

    /**
    * Creates a new app with the primary key. Does not add the app to the database.
    *
    * @param appId the primary key for the new app
    * @return the new app
    */
    public static com.fidoarp.catalog.model.App createApp(long appId) {
        return getService().createApp(appId);
    }

    /**
    * Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appId the primary key of the app
    * @return the app that was removed
    * @throws PortalException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App deleteApp(long appId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteApp(appId);
    }

    /**
    * Deletes the app from the database. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App deleteApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteApp(app);
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

    public static com.fidoarp.catalog.model.App fetchApp(long appId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchApp(appId);
    }

    /**
    * Returns the app with the primary key.
    *
    * @param appId the primary key of the app
    * @return the app
    * @throws PortalException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App getApp(long appId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getApp(appId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.fidoarp.catalog.model.App> getApps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getApps(start, end);
    }

    /**
    * Returns the number of apps.
    *
    * @return the number of apps
    * @throws SystemException if a system exception occurred
    */
    public static int getAppsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAppsCount();
    }

    /**
    * Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App updateApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateApp(app);
    }

    /**
    * Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @param merge whether to merge the app with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the app that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.fidoarp.catalog.model.App updateApp(
        com.fidoarp.catalog.model.App app, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateApp(app, merge);
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

    public static java.util.List<com.fidoarp.catalog.model.App> getAppByUser(
        long userId, int start, int end) {
        return getService().getAppByUser(userId, start, end);
    }

    public static int getAppCountByUser(long userId) {
        return getService().getAppCountByUser(userId);
    }

    public static int getMonthlyAppCountByPartner(long organizationId) {
        return getService().getMonthlyAppCountByPartner(organizationId);
    }

    public static int getAppCountByPartnerStatus(long organizationId,
        long statusId) {
        return getService().getAppCountByPartnerStatus(organizationId, statusId);
    }

    public static java.util.List<com.fidoarp.catalog.model.App> getSearchResult(
        long id, java.util.Date startDate, java.util.Date endDate,
        java.lang.String name, java.lang.String okpo, java.lang.String phone,
        double creditAmount, long statusId, java.lang.String comment,
        long userId, int start, int end) {
        return getService()
                   .getSearchResult(id, startDate, endDate, name, okpo, phone,
            creditAmount, statusId, comment, userId, start, end);
    }

    public static java.lang.Integer getSearchResultCount(long id,
        java.util.Date startDate, java.util.Date endDate,
        java.lang.String name, java.lang.String okpo, java.lang.String phone,
        double creditAmount, long statusId, java.lang.String comment,
        long userId) {
        return getService()
                   .getSearchResultCount(id, startDate, endDate, name, okpo,
            phone, creditAmount, statusId, comment, userId);
    }

    public static AppLocalService getService() {
        if (_service == null) {
            _service = (AppLocalService) PortalBeanLocatorUtil.locate(AppLocalService.class.getName());

            ReferenceRegistry.registerReference(AppLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(AppLocalService service) {
    }
}
