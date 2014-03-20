package com.fidoarp.dictionary.impl;

import com.fidoarp.util.QuestionnaryDictionaryUtil;
import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.List;

public class TypeOrganizationDictionaryImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        return QuestionnaryDictionaryUtil.getOrganizationTypes(themeDisplay.getLocale());
    }

}
