package com.fidoarp.catalog.service.impl;

import com.fidoarp.catalog.model.Dictionary;
import com.fidoarp.catalog.service.base.DictionaryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the dictionary local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fidoarp.catalog.service.DictionaryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.base.DictionaryLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.DictionaryLocalServiceUtil
 */
public class DictionaryLocalServiceImpl extends DictionaryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.fidoarp.catalog.service.DictionaryLocalServiceUtil} to access the dictionary local service.
     */
    public List<Dictionary> getDictionariesByCode(String code, int start, int end)
            throws SystemException {
        return dictionaryPersistence.findByCode(code, start, end);
    }

    public List<Dictionary> getDictionariesByCode(String code)
            throws SystemException {
        return dictionaryPersistence.findByCode(code);
    }

    public List<Dictionary> getDictionariesByCodeParent(String code, String parentId)
            throws SystemException {
        return dictionaryPersistence.findByCodeParent(code, parentId);
    }

    public List<Dictionary> getDictionariesByCodeParents(String code, String parentId, String otherParentId)
            throws SystemException {
        return dictionaryPersistence.findByCodeParents(code, parentId, otherParentId);
    }
}
