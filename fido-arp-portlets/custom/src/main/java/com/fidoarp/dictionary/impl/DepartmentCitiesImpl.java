package com.fidoarp.dictionary.impl;

import com.fidoarp.catalog.model.FidoBranch;
import com.fidoarp.catalog.service.FidoBranchLocalServiceUtil;
import com.fidoarp.dictionary.Dictionary;
import com.fidoarp.model.BankDepartmentType;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.*;

public class DepartmentCitiesImpl implements Dictionary {

    public List<DetailsPair> execute(PortletRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            try {
                List<FidoBranch> fidoBranches = FidoBranchLocalServiceUtil.getFidoBranchByBranchType(BankDepartmentType.DIVISION.name());
                Set<DetailsPair> detailsPairs = new HashSet<DetailsPair>();
                for(FidoBranch fidoBranch : fidoBranches){
                    String city = fidoBranch.getCity(themeDisplay.getLocale());
                    detailsPairs.add(new DetailsPair(city, city));
                }
                ArrayList<DetailsPair> list = new ArrayList<DetailsPair>(detailsPairs);
                Collections.sort(list);
                return list;
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
