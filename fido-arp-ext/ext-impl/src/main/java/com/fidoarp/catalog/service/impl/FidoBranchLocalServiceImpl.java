package com.fidoarp.catalog.service.impl;

import com.fidoarp.catalog.model.FidoBranch;
import com.fidoarp.catalog.service.base.FidoBranchLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the fido branch local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fidoarp.catalog.service.FidoBranchLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.base.FidoBranchLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.FidoBranchLocalServiceUtil
 */
public class FidoBranchLocalServiceImpl extends FidoBranchLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.fidoarp.catalog.service.FidoBranchLocalServiceUtil} to access the fido branch local service.
     */
    public List<FidoBranch> getFidoBranchByCity(String city)
            throws SystemException {
        return fidoBranchPersistence.findByCity("%"+city +"%");
    }

    public List<FidoBranch> getFidoBranchByBranchType(String branchType)
            throws SystemException {
        return fidoBranchPersistence.findByBranchType(branchType);
    }

    public List<FidoBranch> getFidoBranchByBranchTypeInCity(String branchType, String city)
            throws SystemException {
        return fidoBranchPersistence.findByBranchTypeInCity(branchType, "%"+city +"%");
    }

}
