package com.fidoarp.catalog.service.impl;

import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.service.base.AppStatusLocalServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * The implementation of the app status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fidoarp.catalog.service.AppStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.fidoarp.catalog.service.base.AppStatusLocalServiceBaseImpl
 * @see com.fidoarp.catalog.service.AppStatusLocalServiceUtil
 */
public class AppStatusLocalServiceImpl extends AppStatusLocalServiceBaseImpl {

    private static final Log log = LogFactoryUtil.getLog(AppStatusLocalServiceImpl.class);
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.fidoarp.catalog.service.AppStatusLocalServiceUtil} to access the app status local service.
     */
    public AppStatus getAppStatusByCode(String code){
        try {
            return appStatusPersistence.findByCode(code);
        } catch (Exception e) {
            log.info("Error in AppStatusLocalServiceImpl.getAppStatusByCode(code): " + e.getStackTrace());
        }
        return null;
    }
}
