package com.fidoarp.catalog.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the app status local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppStatusLocalServiceUtil
 * @see com.fidoarp.catalog.service.base.AppStatusLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.impl.AppStatusLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface AppStatusLocalService extends BaseLocalService,
    PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AppStatusLocalServiceUtil} to access the app status local service. Add custom service methods to {@link com.fidoarp.catalog.service.impl.AppStatusLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the app status to the database. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was added
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus addAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new app status with the primary key. Does not add the app status to the database.
    *
    * @param appStatusId the primary key for the new app status
    * @return the new app status
    */
    public com.fidoarp.catalog.model.AppStatus createAppStatus(long appStatusId);

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
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the app status from the database. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus deleteAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
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
    public com.fidoarp.catalog.model.AppStatus fetchAppStatus(long appStatusId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the app status with the primary key.
    *
    * @param appStatusId the primary key of the app status
    * @return the app status
    * @throws PortalException if a app status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.fidoarp.catalog.model.AppStatus getAppStatus(long appStatusId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.fidoarp.catalog.model.AppStatus> getAppStatuses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of app statuses.
    *
    * @return the number of app statuses
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getAppStatusesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the app status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param appStatus the app status
    * @return the app status that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.fidoarp.catalog.model.AppStatus updateAppStatus(
        com.fidoarp.catalog.model.AppStatus appStatus)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.fidoarp.catalog.model.AppStatus getAppStatusByCode(
        java.lang.String code);
}
