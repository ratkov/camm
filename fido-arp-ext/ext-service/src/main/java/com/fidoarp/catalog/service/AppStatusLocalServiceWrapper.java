package com.fidoarp.catalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AppStatusLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppStatusLocalService
 * @generated
 */
public class AppStatusLocalServiceWrapper implements AppStatusLocalService,
    ServiceWrapper<AppStatusLocalService> {
    private AppStatusLocalService _appStatusLocalService;

    public AppStatusLocalServiceWrapper(
        AppStatusLocalService appStatusLocalService) {
        _appStatusLocalService = appStatusLocalService;
    }

    /**
    * Adds the app status to the database. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was added
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus addAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.addAppStatus(appStatus);
    }

    /**
    * Creates a new app status with the primary key. Does not add the app status to the database.
    *
    * @param appStatusId the primary key for the new app status
    * @return the new app status
    */
    public com.fidoarp.catalog.model.AppStatus createAppStatus(long appStatusId) {
        return _appStatusLocalService.createAppStatus(appStatusId);
    }

    /**
    * Deletes the app status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status that was removed
    * @throws PortalException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus deleteAppStatus(long appStatusId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.deleteAppStatus(appStatusId);
    }

    /**
    * Deletes the app status from the database. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus deleteAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.deleteAppStatus(appStatus);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _appStatusLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.dynamicQuery(dynamicQuery);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.dynamicQuery(dynamicQuery, start, end);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.fidoarp.catalog.model.AppStatus fetchAppStatus(long appStatusId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.fetchAppStatus(appStatusId);
    }

    /**
    * Returns the app status with the primary key.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status
    * @throws PortalException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus getAppStatus(long appStatusId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.getAppStatus(appStatusId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.getPersistedModel(primaryKeyObj);
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
    public java.util.List<com.fidoarp.catalog.model.AppStatus> getAppStatuses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.getAppStatuses(start, end);
    }

    /**
    * Returns the number of app statuses.
    *
    * @return the number of app statuses
    * @throws SystemException if a system exception occurred
    */
    public int getAppStatusesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.getAppStatusesCount();
    }

    /**
    * Updates the app status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus updateAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.updateAppStatus(appStatus);
    }

    /**
    * Updates the app status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @param merge whether to merge the app status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the app status that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus updateAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _appStatusLocalService.updateAppStatus(appStatus, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _appStatusLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _appStatusLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public AppStatusLocalService getWrappedAppStatusLocalService() {
        return _appStatusLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedAppStatusLocalService(
        AppStatusLocalService appStatusLocalService) {
        _appStatusLocalService = appStatusLocalService;
    }

    public AppStatusLocalService getWrappedService() {
        return _appStatusLocalService;
    }

    public void setWrappedService(AppStatusLocalService appStatusLocalService) {
        _appStatusLocalService = appStatusLocalService;
    }
}
