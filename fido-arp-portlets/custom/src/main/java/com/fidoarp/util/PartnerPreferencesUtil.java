package com.fidoarp.util;

import com.fidoarp.preferences.PartnerConstant;
import com.fidoarp.preferences.PartnerPreferences;
import com.fidoarp.preferences.QueueConstant;
import com.fidoarp.preferences.QueuePreferences;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

public class PartnerPreferencesUtil {

    private final static Log _log = LogFactoryUtil.getLog(PartnerPreferencesUtil.class);

    public static PartnerPreferences getPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();

        PartnerPreferences partnerPreferences = new PartnerPreferences();

        Integer defaultCount = Integer.valueOf(preferences.getValue(PartnerConstant.PARTNER_ITEM_COUNT, "5"));
        Integer defaultStatus = Integer.valueOf(preferences.getValue(PartnerConstant.PARTNER_DEFAULT_STATUS, "0"));
        Integer orderLink =  Integer.valueOf(preferences.getValue(PartnerConstant.PARTNER_ORDER_LINK, "0"));

        partnerPreferences.setItemCount(defaultCount);
        partnerPreferences.setDefaultStatus(defaultStatus);
        partnerPreferences.setOrderLink(orderLink);

        return partnerPreferences;
    }


    public static void setPreferences(PortletRequest request, PartnerPreferences partnerPreferences) {
        PortletPreferences portletPreferences = request.getPreferences();
        try {
            portletPreferences.setValue(PartnerConstant.PARTNER_ITEM_COUNT, partnerPreferences.getItemCount().toString());
            portletPreferences.setValue(PartnerConstant.PARTNER_DEFAULT_STATUS, partnerPreferences.getDefaultStatus().toString());
            portletPreferences.setValue(PartnerConstant.PARTNER_ORDER_LINK, partnerPreferences.getOrderLink().toString());
            portletPreferences.store();
        } catch (Exception e) {
            _log.error("Could not store preferences for queue " + e.getMessage());
        }
    }
}
