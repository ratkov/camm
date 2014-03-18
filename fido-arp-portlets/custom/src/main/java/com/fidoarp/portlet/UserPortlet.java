package com.fidoarp.portlet;

import com.fidoarp.util.UsersUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.*;

public class UserPortlet extends FidoMVCPortlet {

    private static Log log = LogFactoryUtil.getLog(UserPortlet.class);

    @Override
    public void doView(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Locale currentLocale = themeDisplay.getLocale();

        UsersUtil.resources = ResourceBundle.getBundle("/i18n/user/Resource", LocaleUtil.getMostRelevantLocale());
        List<Organization> partners = null;

        try {

            // TODO Need to implement the functionality to sort users by "status"
            UsersUtil.searchContainerData(renderRequest, renderResponse, currentLocale);
            partners = OrganizationLocalServiceUtil.getOrganizations(-1, -1);

            String action = renderRequest.getParameter("action");

            renderRequest.setAttribute("pswdGenerate", UsersUtil.resources.getString("user.password.generate"));

            if (StringUtils.equals("filterUser", action)) {
                getPortletContext().getRequestDispatcher("/html/jsp/portlet/users/view/user-table.jsp").include(renderRequest, renderResponse);
                return;
            }

        } catch (SystemException e) {
            log.error("Could not get organizations cause " + e.getMessage());
        }

        renderRequest.setAttribute("partners", partners);
        renderRequest.setAttribute("currentLocale", currentLocale);

        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest,
    			ResourceResponse resourceResponse) throws IOException {

        JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();

        String action = resourceRequest.getParameter("action");
        Map<String, String> resultMap = new HashMap<String, String>();

        if (StringUtils.equals(action, "addUserAction")) {
            String message = UsersUtil.addNewUser(resourceRequest);

            if (message != null) {
                resultMap.put("addUserError", message);
            }
        } else if (StringUtils.equals(action, "changeStatusAction")) {

            String status = resourceRequest.getParameter("status");
        }

        jsonFeed.put("resultMap", JSONFactoryUtil.
                createJSONSerializer().
                serializeDeep(resultMap.entrySet()));
        log.info("jsonFeed.toString()===" + jsonFeed.toString());
        resourceResponse.getWriter().write(jsonFeed.toString());

    }

}