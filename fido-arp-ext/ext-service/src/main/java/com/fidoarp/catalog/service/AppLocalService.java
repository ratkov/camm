package com.fidoarp.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the app local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppLocalServiceUtil
 * @see com.fidoarp.catalog.service.base.AppLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.impl.AppLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface AppLocalService extends BaseLocalService,
    PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AppLocalServiceUtil} to access the app local service. Add custom service methods to {@link com.fidoarp.catalog.service.impl.AppLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the app to the database. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was added
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App addApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new app with the primary key. Does not add the app to the database.
    *
    * @param appId the primary key for the new app
    * @return the new app
    */
    public com.fidoarp.catalog.model.App createApp(long appId);

    /**
    * Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param appId the primary key of the app
    * @return the app that was removed
    * @throws PortalException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App deleteApp(long appId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the app from the database. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App deleteApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.fidoarp.catalog.model.App fetchApp(long appId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app with the primary key.
    *
    * @param appId the primary key of the app
    * @return the app
    * @throws PortalException if a app with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.fidoarp.catalog.model.App getApp(long appId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.fidoarp.catalog.model.App> getApps(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of apps.
    *
    * @return the number of apps
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getAppsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @return the app that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App updateApp(
        com.fidoarp.catalog.model.App app)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param app the app
    * @param merge whether to merge the app with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the app that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.App updateApp(
        com.fidoarp.catalog.model.App app, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.fidoarp.catalog.model.App> getAppByUser(
        long userId, int start, int end);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getAppCountByUser(long userId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getMonthlyAppCountByPartner(long organizationId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getAppCountByPartnerStatus(long organizationId, long statusId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.fidoarp.catalog.model.App> getSearchResult(
        long id, java.util.Date startDate, java.util.Date endDate,
        java.lang.String name, java.lang.String okpo, java.lang.String phone,
        double creditAmount, long statusId, java.lang.String comment,
        long userId, long organizationId, int start, int end);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Integer getSearchResultCount(long id,
        java.util.Date startDate, java.util.Date endDate,
        java.lang.String name, java.lang.String okpo, java.lang.String phone,
        double creditAmount, long statusId, java.lang.String comment,
        long userId, long organizationId);
}
