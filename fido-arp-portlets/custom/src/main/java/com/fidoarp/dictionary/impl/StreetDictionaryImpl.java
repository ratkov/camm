package com.fidoarp.dictionary.impl;

import com.fidoarp.util.QuestionnaryDictionaryUtil;
import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.List;

public class StreetDictionaryImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String localityCode = ParamUtil.getString(resourceRequest, "localityName", null);
        String streetCode = ParamUtil.getString(resourceRequest, "streetType", null);
        if ((localityCode != null) && (streetCode != null)) {
            return QuestionnaryDictionaryUtil.getStreets(themeDisplay.getLocale(), localityCode, streetCode);
        }
        return null;
    }

}
