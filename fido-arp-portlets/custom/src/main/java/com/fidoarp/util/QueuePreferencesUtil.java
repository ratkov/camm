package com.fidoarp.util;

import com.fidoarp.preferences.QueueConstant;
import com.fidoarp.preferences.QueuePreferences;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

public class QueuePreferencesUtil {

    private final static Log _log = LogFactoryUtil.getLog(QueuePreferencesUtil.class);

    public static QueuePreferences getPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();

        QueuePreferences queuePreferences = new QueuePreferences();

        Integer defaultCount = Integer.valueOf(preferences.getValue(QueueConstant.QUEUE_ITEM_COUNT, "5"));

        queuePreferences.setItemCount(defaultCount);

        return queuePreferences;
    }


    public static void setPreferences(PortletRequest request, QueuePreferences queuePreferences) {
        PortletPreferences portletPreferences = request.getPreferences();
        try {
            portletPreferences.setValue(QueueConstant.QUEUE_ITEM_COUNT, queuePreferences.getItemCount().toString());
            portletPreferences.store();
        } catch (Exception e) {
            _log.error("Could not store preferences for queue " + e.getMessage());
        }
    }
}
