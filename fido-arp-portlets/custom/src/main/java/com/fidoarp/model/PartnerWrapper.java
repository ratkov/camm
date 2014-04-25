package com.fidoarp.model;

import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.service.AppLocalServiceUtil;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Organization;

import java.util.Arrays;

public class PartnerWrapper {
    private static final Log log = LogFactoryUtil.getLog(PartnerWrapper.class);
    private long _id;
    private String _name;
    private String _status;
    private int _monthOrderCount;
    private int _viewedOrderCount;

    public PartnerWrapper(Organization organization) {
        try {
            this._id = organization.getOrganizationId();
            this._name = organization.getName();
            this._status = String.valueOf(organization.getExpandoBridge().getAttribute("status"));
            this._monthOrderCount = AppLocalServiceUtil.getMonthlyAppCountByPartner(organization.getOrganizationId());
            AppStatus status = AppStatusLocalServiceUtil.getAppStatusByCode("IN_VIEW");
            this._viewedOrderCount = AppLocalServiceUtil.getAppCountByPartnerStatus(organization.getOrganizationId(), status.getAppStatusId());
        }catch (Exception e){
            log.error("PartnerWrapper error:" + Arrays.toString(e.getStackTrace()), e);
        }
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getStatus() {
        return _status;
    }

    public int getMonthOrderCount() {
        return _monthOrderCount;
    }

    public int getViewedOrderCount() {
        return _viewedOrderCount;
    }
}
