package com.fidoarp.dictionary.impl;

import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.QuestionnaryDictionaryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.Collections;
import java.util.List;

public class DistrictDictionaryImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
         ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String regionCode = ParamUtil.getString(resourceRequest, "region", null);
        if (regionCode != null) {
            return QuestionnaryDictionaryUtil.getDistricts(themeDisplay.getLocale(), regionCode);
        }
        return Collections.emptyList();
    }
}
