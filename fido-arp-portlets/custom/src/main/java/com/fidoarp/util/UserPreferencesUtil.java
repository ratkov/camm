package com.fidoarp.util;

import com.fidoarp.preferences.UserConstant;
import com.fidoarp.preferences.UserPreferences;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

public class UserPreferencesUtil {

    private final static Log _log = LogFactoryUtil.getLog(UserPreferencesUtil.class);

    public static UserPreferences getPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();

        UserPreferences userPreferences = new UserPreferences();

        Integer defaultCount = Integer.valueOf(preferences.getValue(UserConstant.USER_COUNT, "10"));

        userPreferences.setItemCount(defaultCount);

        return userPreferences;
    }


    public static void setPreferences(PortletRequest request, UserPreferences userPreferences) {
        PortletPreferences portletPreferences = request.getPreferences();
        try {
            portletPreferences.setValue(UserConstant.USER_COUNT, userPreferences.getItemCount().toString());
            portletPreferences.store();
        } catch (Exception e) {
            _log.error("Could not store preferences for user " + e.getMessage());
        }
    }

}
