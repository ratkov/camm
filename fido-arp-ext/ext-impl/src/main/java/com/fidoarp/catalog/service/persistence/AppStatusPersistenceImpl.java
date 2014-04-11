package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.NoSuchAppStatusException;
import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.model.impl.AppStatusImpl;
import com.fidoarp.catalog.model.impl.AppStatusModelImpl;
import com.fidoarp.catalog.service.persistence.AppPersistence;
import com.fidoarp.catalog.service.persistence.AppStatusPersistence;
import com.fidoarp.catalog.service.persistence.DictionaryPersistence;
import com.fidoarp.catalog.service.persistence.FidoBranchPersistence;
import com.fidoarp.catalog.service.persistence.ProductTypePersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the app status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppStatusPersistence
 * @see AppStatusUtil
 * @generated
 */
public class AppStatusPersistenceImpl extends BasePersistenceImpl<AppStatus>
    implements AppStatusPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AppStatusUtil} to access the app status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AppStatusImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusModelImpl.FINDER_CACHE_ENABLED, AppStatusImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByCode",
            new String[] { String.class.getName() },
            AppStatusModelImpl.APPSTATUSCODE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusModelImpl.FINDER_CACHE_ENABLED, AppStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusModelImpl.FINDER_CACHE_ENABLED, AppStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_APPSTATUS = "SELECT appStatus FROM AppStatus appStatus";
    private static final String _SQL_SELECT_APPSTATUS_WHERE = "SELECT appStatus FROM AppStatus appStatus WHERE ";
    private static final String _SQL_COUNT_APPSTATUS = "SELECT COUNT(appStatus) FROM AppStatus appStatus";
    private static final String _SQL_COUNT_APPSTATUS_WHERE = "SELECT COUNT(appStatus) FROM AppStatus appStatus WHERE ";
    private static final String _FINDER_COLUMN_CODE_APPSTATUSCODE_1 = "appStatus.appStatusCode IS NULL";
    private static final String _FINDER_COLUMN_CODE_APPSTATUSCODE_2 = "appStatus.appStatusCode = ?";
    private static final String _FINDER_COLUMN_CODE_APPSTATUSCODE_3 = "(appStatus.appStatusCode IS NULL OR appStatus.appStatusCode = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "appStatus.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AppStatus exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AppStatus exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    private static Log _log = LogFactoryUtil.getLog(AppStatusPersistenceImpl.class);
    private static AppStatus _nullAppStatus = new AppStatusImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AppStatus> toCacheModel() {
                return _nullAppStatusCacheModel;
            }
        };

    private static CacheModel<AppStatus> _nullAppStatusCacheModel = new CacheModel<AppStatus>() {
            public AppStatus toEntityModel() {
                return _nullAppStatus;
            }
        };

    @BeanReference(type = AppPersistence.class)
    protected AppPersistence appPersistence;
    @BeanReference(type = AppStatusPersistence.class)
    protected AppStatusPersistence appStatusPersistence;
    @BeanReference(type = DictionaryPersistence.class)
    protected DictionaryPersistence dictionaryPersistence;
    @BeanReference(type = FidoBranchPersistence.class)
    protected FidoBranchPersistence fidoBranchPersistence;
    @BeanReference(type = ProductTypePersistence.class)
    protected ProductTypePersistence productTypePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the app status in the entity cache if it is enabled.
     *
     * @param appStatus the app status
     */
    public void cacheResult(AppStatus appStatus) {
        EntityCacheUtil.putResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusImpl.class, appStatus.getPrimaryKey(), appStatus);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
            new Object[] { appStatus.getAppStatusCode() }, appStatus);

        appStatus.resetOriginalValues();
    }

    /**
     * Caches the app statuses in the entity cache if it is enabled.
     *
     * @param appStatuses the app statuses
     */
    public void cacheResult(List<AppStatus> appStatuses) {
        for (AppStatus appStatus : appStatuses) {
            if (EntityCacheUtil.getResult(
                        AppStatusModelImpl.ENTITY_CACHE_ENABLED,
                        AppStatusImpl.class, appStatus.getPrimaryKey()) == null) {
                cacheResult(appStatus);
            } else {
                appStatus.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all app statuses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AppStatusImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AppStatusImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the app status.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AppStatus appStatus) {
        EntityCacheUtil.removeResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusImpl.class, appStatus.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(appStatus);
    }

    @Override
    public void clearCache(List<AppStatus> appStatuses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AppStatus appStatus : appStatuses) {
            EntityCacheUtil.removeResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
                AppStatusImpl.class, appStatus.getPrimaryKey());

            clearUniqueFindersCache(appStatus);
        }
    }

    protected void cacheUniqueFindersCache(AppStatus appStatus) {
        if (appStatus.isNew()) {
            Object[] args = new Object[] { appStatus.getAppStatusCode() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args, appStatus);
        } else {
            AppStatusModelImpl appStatusModelImpl = (AppStatusModelImpl) appStatus;

            if ((appStatusModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { appStatus.getAppStatusCode() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE, args,
                    appStatus);
            }
        }
    }

    protected void clearUniqueFindersCache(AppStatus appStatus) {
        AppStatusModelImpl appStatusModelImpl = (AppStatusModelImpl) appStatus;

        Object[] args = new Object[] { appStatus.getAppStatusCode() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);

        if ((appStatusModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
            args = new Object[] { appStatusModelImpl.getOriginalAppStatusCode() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);
        }
    }

    /**
     * Creates a new app status with the primary key. Does not add the app status to the database.
     *
     * @param appStatusId the primary key for the new app status
     * @return the new app status
     */
    public AppStatus create(long appStatusId) {
        AppStatus appStatus = new AppStatusImpl();

        appStatus.setNew(true);
        appStatus.setPrimaryKey(appStatusId);

        return appStatus;
    }

    /**
     * Removes the app status with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param appStatusId the primary key of the app status
     * @return the app status that was removed
     * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus remove(long appStatusId)
        throws NoSuchAppStatusException, SystemException {
        return remove(Long.valueOf(appStatusId));
    }

    /**
     * Removes the app status with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the app status
     * @return the app status that was removed
     * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AppStatus remove(Serializable primaryKey)
        throws NoSuchAppStatusException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AppStatus appStatus = (AppStatus) session.get(AppStatusImpl.class,
                    primaryKey);

            if (appStatus == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAppStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(appStatus);
        } catch (NoSuchAppStatusException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AppStatus removeImpl(AppStatus appStatus)
        throws SystemException {
        appStatus = toUnwrappedModel(appStatus);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, appStatus);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(appStatus);

        return appStatus;
    }

    @Override
    public AppStatus updateImpl(com.fidoarp.catalog.model.AppStatus appStatus,
        boolean merge) throws SystemException {
        appStatus = toUnwrappedModel(appStatus);

        boolean isNew = appStatus.isNew();

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, appStatus, merge);

            appStatus.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !AppStatusModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
            AppStatusImpl.class, appStatus.getPrimaryKey(), appStatus);

        clearUniqueFindersCache(appStatus);
        cacheUniqueFindersCache(appStatus);

        return appStatus;
    }

    protected AppStatus toUnwrappedModel(AppStatus appStatus) {
        if (appStatus instanceof AppStatusImpl) {
            return appStatus;
        }

        AppStatusImpl appStatusImpl = new AppStatusImpl();

        appStatusImpl.setNew(appStatus.isNew());
        appStatusImpl.setPrimaryKey(appStatus.getPrimaryKey());

        appStatusImpl.setAppStatusId(appStatus.getAppStatusId());
        appStatusImpl.setAppStatusCode(appStatus.getAppStatusCode());
        appStatusImpl.setName(appStatus.getName());
        appStatusImpl.setDescription(appStatus.getDescription());

        return appStatusImpl;
    }

    /**
     * Returns the app status with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the app status
     * @return the app status
     * @throws com.liferay.portal.NoSuchModelException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AppStatus findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the app status with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
     *
     * @param appStatusId the primary key of the app status
     * @return the app status
     * @throws com.fidoarp.catalog.NoSuchAppStatusException if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus findByPrimaryKey(long appStatusId)
        throws NoSuchAppStatusException, SystemException {
        AppStatus appStatus = fetchByPrimaryKey(appStatusId);

        if (appStatus == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appStatusId);
            }

            throw new NoSuchAppStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                appStatusId);
        }

        return appStatus;
    }

    /**
     * Returns the app status with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the app status
     * @return the app status, or <code>null</code> if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AppStatus fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the app status with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param appStatusId the primary key of the app status
     * @return the app status, or <code>null</code> if a app status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus fetchByPrimaryKey(long appStatusId)
        throws SystemException {
        AppStatus appStatus = (AppStatus) EntityCacheUtil.getResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
                AppStatusImpl.class, appStatusId);

        if (appStatus == _nullAppStatus) {
            return null;
        }

        if (appStatus == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                appStatus = (AppStatus) session.get(AppStatusImpl.class,
                        Long.valueOf(appStatusId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (appStatus != null) {
                    cacheResult(appStatus);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(AppStatusModelImpl.ENTITY_CACHE_ENABLED,
                        AppStatusImpl.class, appStatusId, _nullAppStatus);
                }

                closeSession(session);
            }
        }

        return appStatus;
    }

    /**
     * Returns the app status where appStatusCode = &#63; or throws a {@link com.fidoarp.catalog.NoSuchAppStatusException} if it could not be found.
     *
     * @param appStatusCode the app status code
     * @return the matching app status
     * @throws com.fidoarp.catalog.NoSuchAppStatusException if a matching app status could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus findByCode(String appStatusCode)
        throws NoSuchAppStatusException, SystemException {
        AppStatus appStatus = fetchByCode(appStatusCode);

        if (appStatus == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("appStatusCode=");
            msg.append(appStatusCode);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchAppStatusException(msg.toString());
        }

        return appStatus;
    }

    /**
     * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param appStatusCode the app status code
     * @return the matching app status, or <code>null</code> if a matching app status could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus fetchByCode(String appStatusCode)
        throws SystemException {
        return fetchByCode(appStatusCode, true);
    }

    /**
     * Returns the app status where appStatusCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param appStatusCode the app status code
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching app status, or <code>null</code> if a matching app status could not be found
     * @throws SystemException if a system exception occurred
     */
    public AppStatus fetchByCode(String appStatusCode, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { appStatusCode };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
                    finderArgs, this);
        }

        if (result instanceof AppStatus) {
            AppStatus appStatus = (AppStatus) result;

            if (!Validator.equals(appStatusCode, appStatus.getAppStatusCode())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_APPSTATUS_WHERE);

            if (appStatusCode == null) {
                query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_1);
            } else {
                if (appStatusCode.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_3);
                } else {
                    query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_2);
                }
            }

            query.append(AppStatusModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (appStatusCode != null) {
                    qPos.add(appStatusCode);
                }

                List<AppStatus> list = q.list();

                result = list;

                AppStatus appStatus = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                        finderArgs, list);
                } else {
                    appStatus = list.get(0);

                    cacheResult(appStatus);

                    if ((appStatus.getAppStatusCode() == null) ||
                            !appStatus.getAppStatusCode().equals(appStatusCode)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                            finderArgs, appStatus);
                    }
                }

                return appStatus;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (AppStatus) result;
            }
        }
    }

    /**
     * Returns all the app statuses.
     *
     * @return the app statuses
     * @throws SystemException if a system exception occurred
     */
    public List<AppStatus> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<AppStatus> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
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
    public List<AppStatus> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<AppStatus> list = (List<AppStatus>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_APPSTATUS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_APPSTATUS.concat(AppStatusModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<AppStatus>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<AppStatus>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes the app status where appStatusCode = &#63; from the database.
     *
     * @param appStatusCode the app status code
     * @return the app status that was removed
     * @throws SystemException if a system exception occurred
     */
    public AppStatus removeByCode(String appStatusCode)
        throws NoSuchAppStatusException, SystemException {
        AppStatus appStatus = findByCode(appStatusCode);

        return remove(appStatus);
    }

    /**
     * Removes all the app statuses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (AppStatus appStatus : findAll()) {
            remove(appStatus);
        }
    }

    /**
     * Returns the number of app statuses where appStatusCode = &#63;.
     *
     * @param appStatusCode the app status code
     * @return the number of matching app statuses
     * @throws SystemException if a system exception occurred
     */
    public int countByCode(String appStatusCode) throws SystemException {
        Object[] finderArgs = new Object[] { appStatusCode };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CODE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_APPSTATUS_WHERE);

            if (appStatusCode == null) {
                query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_1);
            } else {
                if (appStatusCode.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_3);
                } else {
                    query.append(_FINDER_COLUMN_CODE_APPSTATUSCODE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (appStatusCode != null) {
                    qPos.add(appStatusCode);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CODE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of app statuses.
     *
     * @return the number of app statuses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_APPSTATUS);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the app status persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.fidoarp.catalog.model.AppStatus")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AppStatus>> listenersList = new ArrayList<ModelListener<AppStatus>>();

                for (String listenerClassName : listenerClassNames) {
                    Class<?> clazz = getClass();

                    listenersList.add((ModelListener<AppStatus>) InstanceFactory.newInstance(
                            clazz.getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AppStatusImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
