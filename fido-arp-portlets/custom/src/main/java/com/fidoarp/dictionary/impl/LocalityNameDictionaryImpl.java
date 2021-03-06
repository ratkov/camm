package com.fidoarp.dictionary.impl;

import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.QuestionnaryDictionaryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.List;

public class LocalityNameDictionaryImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String districtCode = ParamUtil.getString(resourceRequest, "district", null);
        String typeCode = ParamUtil.getString(resourceRequest, "localityType", null);
        if ((districtCode != null) && (typeCode != null)) {
            return QuestionnaryDictionaryUtil.getSettlementNames(themeDisplay.getLocale(), districtCode, typeCode);
        }
        return null;
    }

}
