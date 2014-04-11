package com.fidoarp.dictionary.impl;

import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.QuestionnaryDictionaryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.List;

public class StreetTypeDictionaryImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String localityCode = ParamUtil.getString(resourceRequest, "localityName", null);
        if(localityCode != null){
            return QuestionnaryDictionaryUtil.getStreetTypesByAvailableStreets(themeDisplay.getLocale(), localityCode);
        }
        return null;
    }

}
