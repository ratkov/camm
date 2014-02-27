package com.fidoarp.catalog.service.base;

import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.service.AppLocalService;
import com.fidoarp.catalog.service.AppStatusLocalService;
import com.fidoarp.catalog.service.ProductTypeLocalService;
import com.fidoarp.catalog.service.persistence.AppPersistence;
import com.fidoarp.catalog.service.persistence.AppStatusPersistence;
import com.fidoarp.catalog.service.persistence.ProductTypePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the app status local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.fidoarp.catalog.service.impl.AppStatusLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.impl.AppStatusLocalServiceImpl
 * @see com.fidoarp.catalog.service.AppStatusLocalServiceUtil
 * @generated
 */
public abstract class AppStatusLocalServiceBaseImpl extends BaseLocalServiceImpl
    implements AppStatusLocalService, IdentifiableBean {
    @BeanReference(type = AppLocalService.class)
    protected AppLocalService appLocalService;
    @BeanReference(type = AppPersistence.class)
    protected AppPersistence appPersistence;
    @BeanReference(type = AppStatusLocalService.class)
    protected AppStatusLocalService appStatusLocalService;
    @BeanReference(type = AppStatusPersistence.class)
    protected AppStatusPersistence appStatusPersistence;
    @BeanReference(type = ProductTypeLocalService.class)
    protected ProductTypeLocalService productTypeLocalService;
    @BeanReference(type = ProductTypePersistence.class)
    protected ProductTypePersistence productTypePersistence;
    @BeanReference(type = CounterLocalService.class)
    protected CounterLocalService counterLocalService;
    @BeanReference(type = ResourceLocalService.class)
    protected ResourceLocalService resourceLocalService;
    @BeanReference(type = ResourceService.class)
    protected ResourceService resourceService;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserLocalService.class)
    protected UserLocalService userLocalService;
    @BeanReference(type = UserService.class)
    protected UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    @BeanReference(type = PersistedModelLocalServiceRegistry.class)
    protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
    private String _beanIdentifier;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link com.fidoarp.catalog.service.AppStatusLocalServiceUtil} to access the app status local service.
     */

    /**
     * Adds the app status to the database. Also notifies the appropriate model listeners.
     *
     * @param appStatus the app status
     * @return the app status that was added
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    public AppStatus addAppStatus(AppStatus appStatus)
        throws SystemException {
        appStatus.setNew(true);

        return appStatusPersistence.update(appStatus, false);
    }

    /**
     * Creates a new app status with the primary key. Does not add the app status to the database.
     *
     * @param appStatusId the primary key for the new app status
     * @return the new app status
     */
    public AppStatus createAppStatus(long appStatusId) {
        return appStatusPersistence.create(appStatusId);
    }

    /**
     * Deletes the app status with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param appStatusId the primary key of the app status
     * @return the app status that was removed
     * @throws PortalException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    public AppStatus deleteAppStatus(long appStatusId)
        throws PortalException, SystemException {
        return appStatusPersistence.remove(appStatusId);
    }

    /**
     * Deletes the app status from the database. Also notifies the appropriate model listeners.
     *
     * @param appStatus the app status
     * @return the app status that was removed
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    public AppStatus deleteAppStatus(AppStatus appStatus)
        throws SystemException {
        return appStatusPersistence.remove(appStatus);
    }

    public DynamicQuery dynamicQuery() {
        Class<?> clazz = getClass();

        return DynamicQueryFactoryUtil.forClass(AppStatus.class,
            clazz.getClassLoader());
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return appStatusPersistence.findWithDynamicQuery(dynamicQuery);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return appStatusPersistence.findWithDynamicQuery(dynamicQuery, start,
            end);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return appStatusPersistence.findWithDynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
    public long dynamicQueryCount(DynamicQuery dynamicQuery)
        throws SystemException {
        return appStatusPersistence.countWithDynamicQuery(dynamicQuery);
    }

    public AppStatus fetchAppStatus(long appStatusId) throws SystemException {
        return appStatusPersistence.fetchByPrimaryKey(appStatusId);
    }

    /**
     * Returns the app status with the primary key.
     *
     * @param appStatusId the primary key of the app status
     * @return the app status
     * @throws PortalException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus getAppStatus(long appStatusId)
        throws PortalException, SystemException {
        return appStatusPersistence.findByPrimaryKey(appStatusId);
    }

    public PersistedModel getPersistedModel(Serializable primaryKeyObj)
        throws PortalException, SystemException {
        return appStatusPersistence.findByPrimaryKey(primaryKeyObj);
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
    public List<AppStatus> getAppStatuses(int start, int end)
        throws SystemException {
        return appStatusPersistence.findAll(start, end);
    }

    /**
     * Returns the number of app statuses.
     *
     * @return the number of app statuses
     * @throws SystemException if a system exception occurred
     */
    public int getAppStatusesCount() throws SystemException {
        return appStatusPersistence.countAll();
    }

    /**
     * Updates the app status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param appStatus the app status
     * @return the app status that was updated
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    public AppStatus updateAppStatus(AppStatus appStatus)
        throws SystemException {
        return updateAppStatus(appStatus, true);
    }

    /**
     * Updates the app status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param appStatus the app status
     * @param merge whether to merge the app status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the app status that was updated
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    public AppStatus updateAppStatus(AppStatus appStatus, boolean merge)
        throws SystemException {
        appStatus.setNew(false);

        return appStatusPersistence.update(appStatus, merge);
    }

    /**
     * Returns the app local service.
     *
     * @return the app local service
     */
    public AppLocalService getAppLocalService() {
        return appLocalService;
    }

    /**
     * Sets the app local service.
     *
     * @param appLocalService the app local service
     */
    public void setAppLocalService(AppLocalService appLocalService) {
        this.appLocalService = appLocalService;
    }

    /**
     * Returns the app persistence.
     *
     * @return the app persistence
     */
    public AppPersistence getAppPersistence() {
        return appPersistence;
    }

    /**
     * Sets the app persistence.
     *
     * @param appPersistence the app persistence
     */
    public void setAppPersistence(AppPersistence appPersistence) {
        this.appPersistence = appPersistence;
    }

    /**
     * Returns the app status local service.
     *
     * @return the app status local service
     */
    public AppStatusLocalService getAppStatusLocalService() {
        return appStatusLocalService;
    }

    /**
     * Sets the app status local service.
     *
     * @param appStatusLocalService the app status local service
     */
    public void setAppStatusLocalService(
        AppStatusLocalService appStatusLocalService) {
        this.appStatusLocalService = appStatusLocalService;
    }

    /**
     * Returns the app status persistence.
     *
     * @return the app status persistence
     */
    public AppStatusPersistence getAppStatusPersistence() {
        return appStatusPersistence;
    }

    /**
     * Sets the app status persistence.
     *
     * @param appStatusPersistence the app status persistence
     */
    public void setAppStatusPersistence(
        AppStatusPersistence appStatusPersistence) {
        this.appStatusPersistence = appStatusPersistence;
    }

    /**
     * Returns the product type local service.
     *
     * @return the product type local service
     */
    public ProductTypeLocalService getProductTypeLocalService() {
        return productTypeLocalService;
    }

    /**
     * Sets the product type local service.
     *
     * @param productTypeLocalService the product type local service
     */
    public void setProductTypeLocalService(
        ProductTypeLocalService productTypeLocalService) {
        this.productTypeLocalService = productTypeLocalService;
    }

    /**
     * Returns the product type persistence.
     *
     * @return the product type persistence
     */
    public ProductTypePersistence getProductTypePersistence() {
        return productTypePersistence;
    }

    /**
     * Sets the product type persistence.
     *
     * @param productTypePersistence the product type persistence
     */
    public void setProductTypePersistence(
        ProductTypePersistence productTypePersistence) {
        this.productTypePersistence = productTypePersistence;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the resource remote service.
     *
     * @return the resource remote service
     */
    public ResourceService getResourceService() {
        return resourceService;
    }

    /**
     * Sets the resource remote service.
     *
     * @param resourceService the resource remote service
     */
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Returns the resource persistence.
     *
     * @return the resource persistence
     */
    public ResourcePersistence getResourcePersistence() {
        return resourcePersistence;
    }

    /**
     * Sets the resource persistence.
     *
     * @param resourcePersistence the resource persistence
     */
    public void setResourcePersistence(ResourcePersistence resourcePersistence) {
        this.resourcePersistence = resourcePersistence;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public void afterPropertiesSet() {
        persistedModelLocalServiceRegistry.register("com.fidoarp.catalog.model.AppStatus",
            appStatusLocalService);
    }

    public void destroy() {
        persistedModelLocalServiceRegistry.unregister(
            "com.fidoarp.catalog.model.AppStatus");
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    protected Class<?> getModelClass() {
        return AppStatus.class;
    }

    protected String getModelClassName() {
        return AppStatus.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = appStatusPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
