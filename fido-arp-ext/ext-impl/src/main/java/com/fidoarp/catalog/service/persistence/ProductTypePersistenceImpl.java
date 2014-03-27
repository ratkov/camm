package com.fidoarp.catalog.service.persistence;

import com.fidoarp.catalog.NoSuchProductTypeException;
import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.model.impl.ProductTypeImpl;
import com.fidoarp.catalog.model.impl.ProductTypeModelImpl;
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
 * The persistence implementation for the product type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductTypePersistence
 * @see ProductTypeUtil
 * @generated
 */
public class ProductTypePersistenceImpl extends BasePersistenceImpl<ProductType>
    implements ProductTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProductTypeUtil} to access the product type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProductTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_CODE = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByCode",
            new String[] { String.class.getName() },
            ProductTypeModelImpl.PRODUCTTYPECODE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CODE = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCode",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID =
        new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOrganizationId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID =
        new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOrganizationId",
            new String[] { Long.class.getName() },
            ProductTypeModelImpl.ORGANIZATIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONID = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOrganizationId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS =
        new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByOrganizationIdStatus",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS =
        new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByOrganizationIdStatus",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            ProductTypeModelImpl.ORGANIZATIONID_COLUMN_BITMASK |
            ProductTypeModelImpl.STATUS_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ORGANIZATIONIDSTATUS = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByOrganizationIdStatus",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, ProductTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PRODUCTTYPE = "SELECT productType FROM ProductType productType";
    private static final String _SQL_SELECT_PRODUCTTYPE_WHERE = "SELECT productType FROM ProductType productType WHERE ";
    private static final String _SQL_COUNT_PRODUCTTYPE = "SELECT COUNT(productType) FROM ProductType productType";
    private static final String _SQL_COUNT_PRODUCTTYPE_WHERE = "SELECT COUNT(productType) FROM ProductType productType WHERE ";
    private static final String _FINDER_COLUMN_CODE_PRODUCTTYPECODE_1 = "productType.productTypeCode IS NULL";
    private static final String _FINDER_COLUMN_CODE_PRODUCTTYPECODE_2 = "productType.productTypeCode = ?";
    private static final String _FINDER_COLUMN_CODE_PRODUCTTYPECODE_3 = "(productType.productTypeCode IS NULL OR productType.productTypeCode = ?)";
    private static final String _FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2 = "productType.organizationId = ?";
    private static final String _FINDER_COLUMN_ORGANIZATIONIDSTATUS_ORGANIZATIONID_2 =
        "productType.organizationId = ? AND ";
    private static final String _FINDER_COLUMN_ORGANIZATIONIDSTATUS_STATUS_2 = "productType.status = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "productType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProductType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProductType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    private static Log _log = LogFactoryUtil.getLog(ProductTypePersistenceImpl.class);
    private static ProductType _nullProductType = new ProductTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProductType> toCacheModel() {
                return _nullProductTypeCacheModel;
            }
        };

    private static CacheModel<ProductType> _nullProductTypeCacheModel = new CacheModel<ProductType>() {
            public ProductType toEntityModel() {
                return _nullProductType;
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
     * Caches the product type in the entity cache if it is enabled.
     *
     * @param productType the product type
     */
    public void cacheResult(ProductType productType) {
        EntityCacheUtil.putResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeImpl.class, productType.getPrimaryKey(), productType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
            new Object[] { productType.getProductTypeCode() }, productType);

        productType.resetOriginalValues();
    }

    /**
     * Caches the product types in the entity cache if it is enabled.
     *
     * @param productTypes the product types
     */
    public void cacheResult(List<ProductType> productTypes) {
        for (ProductType productType : productTypes) {
            if (EntityCacheUtil.getResult(
                        ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProductTypeImpl.class, productType.getPrimaryKey()) == null) {
                cacheResult(productType);
            } else {
                productType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all product types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProductTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProductTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the product type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProductType productType) {
        EntityCacheUtil.removeResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeImpl.class, productType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(productType);
    }

    @Override
    public void clearCache(List<ProductType> productTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProductType productType : productTypes) {
            EntityCacheUtil.removeResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProductTypeImpl.class, productType.getPrimaryKey());

            clearUniqueFindersCache(productType);
        }
    }

    protected void clearUniqueFindersCache(ProductType productType) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE,
            new Object[] { productType.getProductTypeCode() });
    }

    /**
     * Creates a new product type with the primary key. Does not add the product type to the database.
     *
     * @param productTypeId the primary key for the new product type
     * @return the new product type
     */
    public ProductType create(long productTypeId) {
        ProductType productType = new ProductTypeImpl();

        productType.setNew(true);
        productType.setPrimaryKey(productTypeId);

        return productType;
    }

    /**
     * Removes the product type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param productTypeId the primary key of the product type
     * @return the product type that was removed
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType remove(long productTypeId)
        throws NoSuchProductTypeException, SystemException {
        return remove(Long.valueOf(productTypeId));
    }

    /**
     * Removes the product type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the product type
     * @return the product type that was removed
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProductType remove(Serializable primaryKey)
        throws NoSuchProductTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProductType productType = (ProductType) session.get(ProductTypeImpl.class,
                    primaryKey);

            if (productType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProductTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(productType);
        } catch (NoSuchProductTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProductType removeImpl(ProductType productType)
        throws SystemException {
        productType = toUnwrappedModel(productType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, productType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(productType);

        return productType;
    }

    @Override
    public ProductType updateImpl(
        com.fidoarp.catalog.model.ProductType productType, boolean merge)
        throws SystemException {
        productType = toUnwrappedModel(productType);

        boolean isNew = productType.isNew();

        ProductTypeModelImpl productTypeModelImpl = (ProductTypeModelImpl) productType;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, productType, merge);

            productType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProductTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((productTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(productTypeModelImpl.getOriginalOrganizationId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
                    args);

                args = new Object[] {
                        Long.valueOf(productTypeModelImpl.getOrganizationId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID,
                    args);
            }

            if ((productTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(productTypeModelImpl.getOriginalOrganizationId()),
                        Boolean.valueOf(productTypeModelImpl.getOriginalStatus())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONIDSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS,
                    args);

                args = new Object[] {
                        Long.valueOf(productTypeModelImpl.getOrganizationId()),
                        Boolean.valueOf(productTypeModelImpl.getStatus())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORGANIZATIONIDSTATUS,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProductTypeImpl.class, productType.getPrimaryKey(), productType);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                new Object[] { productType.getProductTypeCode() }, productType);
        } else {
            if ((productTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CODE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        productTypeModelImpl.getOriginalProductTypeCode()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CODE, args);

                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CODE, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                    new Object[] { productType.getProductTypeCode() },
                    productType);
            }
        }

        return productType;
    }

    protected ProductType toUnwrappedModel(ProductType productType) {
        if (productType instanceof ProductTypeImpl) {
            return productType;
        }

        ProductTypeImpl productTypeImpl = new ProductTypeImpl();

        productTypeImpl.setNew(productType.isNew());
        productTypeImpl.setPrimaryKey(productType.getPrimaryKey());

        productTypeImpl.setProductTypeId(productType.getProductTypeId());
        productTypeImpl.setProductTypeCode(productType.getProductTypeCode());
        productTypeImpl.setName(productType.getName());
        productTypeImpl.setDescription(productType.getDescription());
        productTypeImpl.setStatus(productType.isStatus());
        productTypeImpl.setOrganizationId(productType.getOrganizationId());
        productTypeImpl.setTemplateId(productType.getTemplateId());

        return productTypeImpl;
    }

    /**
     * Returns the product type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the product type
     * @return the product type
     * @throws com.liferay.portal.NoSuchModelException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProductType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the product type with the primary key or throws a {@link com.fidoarp.catalog.NoSuchProductTypeException} if it could not be found.
     *
     * @param productTypeId the primary key of the product type
     * @return the product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByPrimaryKey(long productTypeId)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByPrimaryKey(productTypeId);

        if (productType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + productTypeId);
            }

            throw new NoSuchProductTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                productTypeId);
        }

        return productType;
    }

    /**
     * Returns the product type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the product type
     * @return the product type, or <code>null</code> if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProductType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the product type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param productTypeId the primary key of the product type
     * @return the product type, or <code>null</code> if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByPrimaryKey(long productTypeId)
        throws SystemException {
        ProductType productType = (ProductType) EntityCacheUtil.getResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProductTypeImpl.class, productTypeId);

        if (productType == _nullProductType) {
            return null;
        }

        if (productType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                productType = (ProductType) session.get(ProductTypeImpl.class,
                        Long.valueOf(productTypeId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (productType != null) {
                    cacheResult(productType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProductTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProductTypeImpl.class, productTypeId, _nullProductType);
                }

                closeSession(session);
            }
        }

        return productType;
    }

    /**
     * Returns the product type where productTypeCode = &#63; or throws a {@link com.fidoarp.catalog.NoSuchProductTypeException} if it could not be found.
     *
     * @param productTypeCode the product type code
     * @return the matching product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByCode(String productTypeCode)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByCode(productTypeCode);

        if (productType == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("productTypeCode=");
            msg.append(productTypeCode);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProductTypeException(msg.toString());
        }

        return productType;
    }

    /**
     * Returns the product type where productTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param productTypeCode the product type code
     * @return the matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByCode(String productTypeCode)
        throws SystemException {
        return fetchByCode(productTypeCode, true);
    }

    /**
     * Returns the product type where productTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param productTypeCode the product type code
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByCode(String productTypeCode,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { productTypeCode };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CODE,
                    finderArgs, this);
        }

        if (result instanceof ProductType) {
            ProductType productType = (ProductType) result;

            if (!Validator.equals(productTypeCode,
                        productType.getProductTypeCode())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PRODUCTTYPE_WHERE);

            if (productTypeCode == null) {
                query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_1);
            } else {
                if (productTypeCode.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_3);
                } else {
                    query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_2);
                }
            }

            query.append(ProductTypeModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (productTypeCode != null) {
                    qPos.add(productTypeCode);
                }

                List<ProductType> list = q.list();

                result = list;

                ProductType productType = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                        finderArgs, list);
                } else {
                    productType = list.get(0);

                    cacheResult(productType);

                    if ((productType.getProductTypeCode() == null) ||
                            !productType.getProductTypeCode()
                                            .equals(productTypeCode)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CODE,
                            finderArgs, productType);
                    }
                }

                return productType;
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
                return (ProductType) result;
            }
        }
    }

    /**
     * Returns all the product types where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationId(long organizationId)
        throws SystemException {
        return findByOrganizationId(organizationId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the product types where organizationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param organizationId the organization ID
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @return the range of matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationId(long organizationId,
        int start, int end) throws SystemException {
        return findByOrganizationId(organizationId, start, end, null);
    }

    /**
     * Returns an ordered range of all the product types where organizationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param organizationId the organization ID
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationId(long organizationId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONID;
            finderArgs = new Object[] { organizationId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONID;
            finderArgs = new Object[] {
                    organizationId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProductType> list = (List<ProductType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProductType productType : list) {
                if ((organizationId != productType.getOrganizationId())) {
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

            query.append(_SQL_SELECT_PRODUCTTYPE_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ProductTypeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                list = (List<ProductType>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first product type in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByOrganizationId_First(long organizationId,
        OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByOrganizationId_First(organizationId,
                orderByComparator);

        if (productType != null) {
            return productType;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProductTypeException(msg.toString());
    }

    /**
     * Returns the first product type in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByOrganizationId_First(long organizationId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProductType> list = findByOrganizationId(organizationId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last product type in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByOrganizationId_Last(long organizationId,
        OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByOrganizationId_Last(organizationId,
                orderByComparator);

        if (productType != null) {
            return productType;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProductTypeException(msg.toString());
    }

    /**
     * Returns the last product type in the ordered set where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByOrganizationId_Last(long organizationId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByOrganizationId(organizationId);

        List<ProductType> list = findByOrganizationId(organizationId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the product types before and after the current product type in the ordered set where organizationId = &#63;.
     *
     * @param productTypeId the primary key of the current product type
     * @param organizationId the organization ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType[] findByOrganizationId_PrevAndNext(long productTypeId,
        long organizationId, OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = findByPrimaryKey(productTypeId);

        Session session = null;

        try {
            session = openSession();

            ProductType[] array = new ProductTypeImpl[3];

            array[0] = getByOrganizationId_PrevAndNext(session, productType,
                    organizationId, orderByComparator, true);

            array[1] = productType;

            array[2] = getByOrganizationId_PrevAndNext(session, productType,
                    organizationId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProductType getByOrganizationId_PrevAndNext(Session session,
        ProductType productType, long organizationId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PRODUCTTYPE_WHERE);

        query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

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
            query.append(ProductTypeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(organizationId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(productType);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProductType> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the product types where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @return the matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationIdStatus(long organizationId,
        boolean status) throws SystemException {
        return findByOrganizationIdStatus(organizationId, status,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the product types where organizationId = &#63; and status = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @return the range of matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationIdStatus(long organizationId,
        boolean status, int start, int end) throws SystemException {
        return findByOrganizationIdStatus(organizationId, status, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the product types where organizationId = &#63; and status = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findByOrganizationIdStatus(long organizationId,
        boolean status, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS;
            finderArgs = new Object[] { organizationId, status };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORGANIZATIONIDSTATUS;
            finderArgs = new Object[] {
                    organizationId, status,
                    
                    start, end, orderByComparator
                };
        }

        List<ProductType> list = (List<ProductType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProductType productType : list) {
                if ((organizationId != productType.getOrganizationId()) ||
                        (status != productType.getStatus())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_PRODUCTTYPE_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_ORGANIZATIONID_2);

            query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_STATUS_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ProductTypeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                qPos.add(status);

                list = (List<ProductType>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first product type in the ordered set where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByOrganizationIdStatus_First(long organizationId,
        boolean status, OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByOrganizationIdStatus_First(organizationId,
                status, orderByComparator);

        if (productType != null) {
            return productType;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(", status=");
        msg.append(status);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProductTypeException(msg.toString());
    }

    /**
     * Returns the first product type in the ordered set where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByOrganizationIdStatus_First(long organizationId,
        boolean status, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProductType> list = findByOrganizationIdStatus(organizationId,
                status, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last product type in the ordered set where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType findByOrganizationIdStatus_Last(long organizationId,
        boolean status, OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = fetchByOrganizationIdStatus_Last(organizationId,
                status, orderByComparator);

        if (productType != null) {
            return productType;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("organizationId=");
        msg.append(organizationId);

        msg.append(", status=");
        msg.append(status);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProductTypeException(msg.toString());
    }

    /**
     * Returns the last product type in the ordered set where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching product type, or <code>null</code> if a matching product type could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType fetchByOrganizationIdStatus_Last(long organizationId,
        boolean status, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByOrganizationIdStatus(organizationId, status);

        List<ProductType> list = findByOrganizationIdStatus(organizationId,
                status, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the product types before and after the current product type in the ordered set where organizationId = &#63; and status = &#63;.
     *
     * @param productTypeId the primary key of the current product type
     * @param organizationId the organization ID
     * @param status the status
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next product type
     * @throws com.fidoarp.catalog.NoSuchProductTypeException if a product type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProductType[] findByOrganizationIdStatus_PrevAndNext(
        long productTypeId, long organizationId, boolean status,
        OrderByComparator orderByComparator)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = findByPrimaryKey(productTypeId);

        Session session = null;

        try {
            session = openSession();

            ProductType[] array = new ProductTypeImpl[3];

            array[0] = getByOrganizationIdStatus_PrevAndNext(session,
                    productType, organizationId, status, orderByComparator, true);

            array[1] = productType;

            array[2] = getByOrganizationIdStatus_PrevAndNext(session,
                    productType, organizationId, status, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProductType getByOrganizationIdStatus_PrevAndNext(
        Session session, ProductType productType, long organizationId,
        boolean status, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PRODUCTTYPE_WHERE);

        query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_ORGANIZATIONID_2);

        query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_STATUS_2);

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
            query.append(ProductTypeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(organizationId);

        qPos.add(status);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(productType);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProductType> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the product types.
     *
     * @return the product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the product types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @return the range of product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the product types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of product types
     * @param end the upper bound of the range of product types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of product types
     * @throws SystemException if a system exception occurred
     */
    public List<ProductType> findAll(int start, int end,
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

        List<ProductType> list = (List<ProductType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PRODUCTTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PRODUCTTYPE.concat(ProductTypeModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProductType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProductType>) QueryUtil.list(q, getDialect(),
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
     * Removes the product type where productTypeCode = &#63; from the database.
     *
     * @param productTypeCode the product type code
     * @return the product type that was removed
     * @throws SystemException if a system exception occurred
     */
    public ProductType removeByCode(String productTypeCode)
        throws NoSuchProductTypeException, SystemException {
        ProductType productType = findByCode(productTypeCode);

        return remove(productType);
    }

    /**
     * Removes all the product types where organizationId = &#63; from the database.
     *
     * @param organizationId the organization ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByOrganizationId(long organizationId)
        throws SystemException {
        for (ProductType productType : findByOrganizationId(organizationId)) {
            remove(productType);
        }
    }

    /**
     * Removes all the product types where organizationId = &#63; and status = &#63; from the database.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @throws SystemException if a system exception occurred
     */
    public void removeByOrganizationIdStatus(long organizationId, boolean status)
        throws SystemException {
        for (ProductType productType : findByOrganizationIdStatus(
                organizationId, status)) {
            remove(productType);
        }
    }

    /**
     * Removes all the product types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProductType productType : findAll()) {
            remove(productType);
        }
    }

    /**
     * Returns the number of product types where productTypeCode = &#63;.
     *
     * @param productTypeCode the product type code
     * @return the number of matching product types
     * @throws SystemException if a system exception occurred
     */
    public int countByCode(String productTypeCode) throws SystemException {
        Object[] finderArgs = new Object[] { productTypeCode };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CODE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PRODUCTTYPE_WHERE);

            if (productTypeCode == null) {
                query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_1);
            } else {
                if (productTypeCode.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_3);
                } else {
                    query.append(_FINDER_COLUMN_CODE_PRODUCTTYPECODE_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (productTypeCode != null) {
                    qPos.add(productTypeCode);
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
     * Returns the number of product types where organizationId = &#63;.
     *
     * @param organizationId the organization ID
     * @return the number of matching product types
     * @throws SystemException if a system exception occurred
     */
    public int countByOrganizationId(long organizationId)
        throws SystemException {
        Object[] finderArgs = new Object[] { organizationId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PRODUCTTYPE_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONID_ORGANIZATIONID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of product types where organizationId = &#63; and status = &#63;.
     *
     * @param organizationId the organization ID
     * @param status the status
     * @return the number of matching product types
     * @throws SystemException if a system exception occurred
     */
    public int countByOrganizationIdStatus(long organizationId, boolean status)
        throws SystemException {
        Object[] finderArgs = new Object[] { organizationId, status };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ORGANIZATIONIDSTATUS,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PRODUCTTYPE_WHERE);

            query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_ORGANIZATIONID_2);

            query.append(_FINDER_COLUMN_ORGANIZATIONIDSTATUS_STATUS_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(organizationId);

                qPos.add(status);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ORGANIZATIONIDSTATUS,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of product types.
     *
     * @return the number of product types
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PRODUCTTYPE);

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
     * Initializes the product type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.com.fidoarp.catalog.model.ProductType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProductType>> listenersList = new ArrayList<ModelListener<ProductType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProductType>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProductTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
