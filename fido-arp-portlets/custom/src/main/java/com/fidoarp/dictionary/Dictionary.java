package com.fidoarp.dictionary;

import com.fidoarp.model.questionnaire.DetailsPair;

import javax.portlet.PortletRequest;
import java.util.List;

public interface Dictionary {

    List<DetailsPair> execute(PortletRequest resourceRequest);

}
