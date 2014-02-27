package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.NoSuchAppException;
import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.model.impl.AppImpl;
import com.fidoarp.catalog.model.impl.AppModelImpl;
import com.fidoarp.catalog.service.persistence.AppPersistence;
import com.fidoarp.catalog.service.persistence.AppStatusPersistence;
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
 * The persistence implementation for the app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPersistence
 * @see AppUtil
 * @generated
 */
public class AppPersistenceImpl extends BasePersistenceImpl<App>
    implements AppPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AppUtil} to access the app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AppImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USER = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, AppImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUser",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, AppImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUser",
            new String[] { Long.class.getName() },
            AppModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USER = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUser",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, AppImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, AppImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_APP = "SELECT app FROM App app";
    private static final String _SQL_SELECT_APP_WHERE = "SELECT app FROM App app WHERE ";
    private static final String _SQL_COUNT_APP = "SELECT COUNT(app) FROM App app";
    private static final String _SQL_COUNT_APP_WHERE = "SELECT COUNT(app) FROM App app WHERE ";
    private static final String _FINDER_COLUMN_USER_USERID_2 = "app.userId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "app.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No App exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No App exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    private static Log _log = LogFactoryUtil.getLog(AppPersistenceImpl.class);
    private static App _nullApp = new AppImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<App> toCacheModel() {
                return _nullAppCacheModel;
            }
        };

    private static CacheModel<App> _nullAppCacheModel = new CacheModel<App>() {
            public App toEntityModel() {
                return _nullApp;
            }
        };

    @BeanReference(type = AppPersistence.class)
    protected AppPersistence appPersistence;
    @BeanReference(type = AppStatusPersistence.class)
    protected AppStatusPersistence appStatusPersistence;
    @BeanReference(type = ProductTypePersistence.class)
    protected ProductTypePersistence productTypePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the app in the entity cache if it is enabled.
     *
     * @param app the app
     */
    public void cacheResult(App app) {
        EntityCacheUtil.putResult(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppImpl.class, app.getPrimaryKey(), app);

        app.resetOriginalValues();
    }

    /**
     * Caches the apps in the entity cache if it is enabled.
     *
     * @param apps the apps
     */
    public void cacheResult(List<App> apps) {
        for (App app : apps) {
            if (EntityCacheUtil.getResult(AppModelImpl.ENTITY_CACHE_ENABLED,
                        AppImpl.class, app.getPrimaryKey()) == null) {
                cacheResult(app);
            } else {
                app.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all apps.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AppImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AppImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the app.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(App app) {
        EntityCacheUtil.removeResult(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppImpl.class, app.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<App> apps) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (App app : apps) {
            EntityCacheUtil.removeResult(AppModelImpl.ENTITY_CACHE_ENABLED,
                AppImpl.class, app.getPrimaryKey());
        }
    }

    /**
     * Creates a new app with the primary key. Does not add the app to the database.
     *
     * @param appId the primary key for the new app
     * @return the new app
     */
    public App create(long appId) {
        App app = new AppImpl();

        app.setNew(true);
        app.setPrimaryKey(appId);

        return app;
    }

    /**
     * Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param appId the primary key of the app
     * @return the app that was removed
     * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public App remove(long appId) throws NoSuchAppException, SystemException {
        return remove(Long.valueOf(appId));
    }

    /**
     * Removes the app with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the app
     * @return the app that was removed
     * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public App remove(Serializable primaryKey)
        throws NoSuchAppException, SystemException {
        Session session = null;

        try {
            session = openSession();

            App app = (App) session.get(AppImpl.class, primaryKey);

            if (app == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(app);
        } catch (NoSuchAppException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected App removeImpl(App app) throws SystemException {
        app = toUnwrappedModel(app);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, app);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(app);

        return app;
    }

    @Override
    public App updateImpl(com.fidoarp.catalog.model.App app, boolean merge)
        throws SystemException {
        app = toUnwrappedModel(app);

        boolean isNew = app.isNew();

        AppModelImpl appModelImpl = (AppModelImpl) app;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, app, merge);

            app.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !AppModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((appModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(appModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER,
                    args);

                args = new Object[] { Long.valueOf(appModelImpl.getUserId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER,
                    args);
            }
        }

        EntityCacheUtil.putResult(AppModelImpl.ENTITY_CACHE_ENABLED,
            AppImpl.class, app.getPrimaryKey(), app);

        return app;
    }

    protected App toUnwrappedModel(App app) {
        if (app instanceof AppImpl) {
            return app;
        }

        AppImpl appImpl = new AppImpl();

        appImpl.setNew(app.isNew());
        appImpl.setPrimaryKey(app.getPrimaryKey());

        appImpl.setAppId(app.getAppId());
        appImpl.setUserId(app.getUserId());
        appImpl.setOrganizationId(app.getOrganizationId());
        appImpl.setCreatedDate(app.getCreatedDate());
        appImpl.setDescription(app.getDescription());
        appImpl.setStatusId(app.getStatusId());
        appImpl.setStatusChangeDate(app.getStatusChangeDate());
        appImpl.setClientName(app.getClientName());
        appImpl.setClientOkpo(app.getClientOkpo());
        appImpl.setContactPhone(app.getContactPhone());
        appImpl.setCreditAmount(app.getCreditAmount());
        appImpl.setComments(app.getComments());
        appImpl.setQuestionnaire(app.getQuestionnaire());

        return appImpl;
    }

    /**
     * Returns the app with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the app
     * @return the app
     * @throws com.liferay.portal.NoSuchModelException if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public App findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the app with the primary key or throws a {@link com.fidoarp.catalog.NoSuchAppException} if it could not be found.
     *
     * @param appId the primary key of the app
     * @return the app
     * @throws com.fidoarp.catalog.NoSuchAppException if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public App findByPrimaryKey(long appId)
        throws NoSuchAppException, SystemException {
        App app = fetchByPrimaryKey(appId);

        if (app == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + appId);
            }

            throw new NoSuchAppException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                appId);
        }

        return app;
    }

    /**
     * Returns the app with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the app
     * @return the app, or <code>null</code> if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public App fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the app with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param appId the primary key of the app
     * @return the app, or <code>null</code> if a app with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public App fetchByPrimaryKey(long appId) throws SystemException {
        App app = (App) EntityCacheUtil.getResult(AppModelImpl.ENTITY_CACHE_ENABLED,
                AppImpl.class, appId);

        if (app == _nullApp) {
            return null;
        }

        if (app == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                app = (App) session.get(AppImpl.class, Long.valueOf(appId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (app != null) {
                    cacheResult(app);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(AppModelImpl.ENTITY_CACHE_ENABLED,
                        AppImpl.class, appId, _nullApp);
                }

                closeSession(session);
            }
        }

        return app;
    }

    /**
     * Returns all the apps where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching apps
     * @throws SystemException if a system exception occurred
     */
    public List<App> findByUser(long userId) throws SystemException {
        return findByUser(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<App> findByUser(long userId, int start, int end)
        throws SystemException {
        return findByUser(userId, start, end, null);
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
    public List<App> findByUser(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USER;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USER;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<App> list = (List<App>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (App app : list) {
                if ((userId != app.getUserId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_APP_WHERE);

            query.append(_FINDER_COLUMN_USER_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(AppModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<App>) QueryUtil.list(q, getDialect(), start, end);
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
     * Returns the first app in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching app
     * @throws com.fidoarp.catalog.NoSuchAppException if a matching app could not be found
     * @throws SystemException if a system exception occurred
     */
    public App findByUser_First(long userId, OrderByComparator orderByComparator)
        throws NoSuchAppException, SystemException {
        App app = fetchByUser_First(userId, orderByComparator);

        if (app != null) {
            return app;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAppException(msg.toString());
    }

    /**
     * Returns the first app in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching app, or <code>null</code> if a matching app could not be found
     * @throws SystemException if a system exception occurred
     */
    public App fetchByUser_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<App> list = findByUser(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    public App findByUser_Last(long userId, OrderByComparator orderByComparator)
        throws NoSuchAppException, SystemException {
        App app = fetchByUser_Last(userId, orderByComparator);

        if (app != null) {
            return app;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchAppException(msg.toString());
    }

    /**
     * Returns the last app in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching app, or <code>null</code> if a matching app could not be found
     * @throws SystemException if a system exception occurred
     */
    public App fetchByUser_Last(long userId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByUser(userId);

        List<App> list = findByUser(userId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    public App[] findByUser_PrevAndNext(long appId, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchAppException, SystemException {
        App app = findByPrimaryKey(appId);

        Session session = null;

        try {
            session = openSession();

            App[] array = new AppImpl[3];

            array[0] = getByUser_PrevAndNext(session, app, userId,
                    orderByComparator, true);

            array[1] = app;

            array[2] = getByUser_PrevAndNext(session, app, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected App getByUser_PrevAndNext(Session session, App app, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_APP_WHERE);

        query.append(_FINDER_COLUMN_USER_USERID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(AppModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(app);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<App> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the apps.
     *
     * @return the apps
     * @throws SystemException if a system exception occurred
     */
    public List<App> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<App> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    public List<App> findAll(int start, int end,
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

        List<App> list = (List<App>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_APP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_APP.concat(AppModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<App>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);
                } else {
                    list = (List<App>) QueryUtil.list(q, getDialect(), start,
                            end);
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
     * Removes all the apps where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUser(long userId) throws SystemException {
        for (App app : findByUser(userId)) {
            remove(app);
        }
    }

    /**
     * Removes all the apps from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (App app : findAll()) {
            remove(app);
        }
    }

    /**
     * Returns the number of apps where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching apps
     * @throws SystemException if a system exception occurred
     */
    public int countByUser(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USER,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_APP_WHERE);

            query.append(_FINDER_COLUMN_USER_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of apps.
     *
     * @return the number of apps
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_APP);

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
     * Initializes the app persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.fidoarp.catalog.model.App")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<App>> listenersList = new ArrayList<ModelListener<App>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<App>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AppImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
