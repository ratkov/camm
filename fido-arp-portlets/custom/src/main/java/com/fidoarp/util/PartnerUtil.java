package com.fidoarp.util;

import com.fidoarp.model.PartnerStatus;
import com.fidoarp.model.UserStatus;
import com.fidoarp.preferences.PartnerPreferences;
import com.fidoarp.preferences.UserPreferences;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.util.PwdGenerator;
import com.liferay.util.portlet.PortletProps;
import org.apache.commons.lang.StringUtils;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import java.util.*;

public class PartnerUtil {

    private static Log log = LogFactoryUtil.getLog(PartnerUtil.class);

    public static ResourceBundle resources;

    public PartnerUtil(Locale locale) {
        resources = new FidoARPUtils().getResourceBundle("/i18n/Resource", locale, "UTF-8");
    }

    public String savePartner(PortletRequest resourceRequest) {

        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String name = resourceRequest.getParameter("name");

        String validationResult = validatePartner(name);

        if (validationResult == null) {

            try {

                int status = getDefaultStatus(resourceRequest);

                Organization organization = OrganizationLocalServiceUtil.createOrganization(CounterLocalServiceUtil.increment());
                organization.setName(name);
                organization.setRecursable(false);
                organization.setCompanyId(themeDisplay.getCompanyId());
                organization.setType("regular-organization");
                organization.setParentOrganizationId(0);
                organization.setStatusId(ListTypeConstants.ORGANIZATION_STATUS_DEFAULT);
                organization.setExpandoBridgeAttributes(new ServiceContext());
                OrganizationLocalServiceUtil.addOrganization(organization);

                ExpandoUtils.addOrganizationValues(organization.getOrganizationId(), "status", status);


            } catch (Exception e) {
                log.error(e.getMessage(), e);
                validationResult = resources.getString("partner.status.update.error");
            }

        }

        return validationResult;
    }

    private Integer getDefaultStatus(PortletRequest renderRequest) {
        PartnerPreferences preferences = PartnerPreferencesUtil.getPreferences(renderRequest);
        return preferences.getDefaultStatus();
    }

    public String changeStatus(String status, long organizationId, long companyId) {

        String result = null;

        if (organizationId != 0) {
            ExpandoUtils.updateOrganizationValues(organizationId, status);
        } else {
            result = resources.getString("partner.status.update.error");
        }

        return result;
    }

    private String validatePartner(String name) {

        String result = null;

        // Validate user's first name
        if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
            result = resources.getString("partner.validation.name.required");

            return result;
        }

        return null;
    }


}
