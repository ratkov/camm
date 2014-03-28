package com.fidoarp.portlet;

import com.fidoarp.UserStatus;
import com.fidoarp.util.UsersUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.*;

public class UserPortlet extends FidoMVCPortlet {

    private static Log log = LogFactoryUtil.getLog(UserPortlet.class);

    @Override
    public void render(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException, PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Locale currentLocale = themeDisplay.getLocale();

        UsersUtil.resources = ResourceBundle.getBundle("/i18n/user/Resource", LocaleUtil.getMostRelevantLocale());
        List<Organization> partners = null;

        try {

            UsersUtil.searchUsers(renderRequest);
            partners = OrganizationLocalServiceUtil.getOrganizations(-1, -1);

            String action = renderRequest.getParameter("action");

            renderRequest.setAttribute("pswdGenerate", UsersUtil.resources.getString("user.password.generate"));
            renderRequest.setAttribute("userStatus", UserStatus.values());
            renderRequest.setAttribute("currentLocale", currentLocale);

            if (StringUtils.equals("addUser", action)) {

                if (addView(renderRequest, renderResponse, partners)) return;

            } else if (StringUtils.equals("filterUser", action)) {
                getPortletContext().getRequestDispatcher("/html/jsp/portlet/users/view/user-table.jsp").include(renderRequest, renderResponse);
                return;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        renderRequest.setAttribute("partners", partners);

        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest,
    			ResourceResponse resourceResponse) throws IOException {

        JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();

        Map<String, String> resultMap = new HashMap<String, String>();
        String action = resourceRequest.getParameter("action");
        String message = null;

        if (StringUtils.equals(action, "addUserAction")) {
            message = UsersUtil.saveUser(resourceRequest);

            if (message != null) {
                resultMap.put("addUserError", message);
            }

        } else if (StringUtils.equals(action, "changeStatusAction")) {
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String status = resourceRequest.getParameter("status");
            String userId = resourceRequest.getParameter("userId");

            message =  UsersUtil.changeStatus(status, userId, themeDisplay.getCompanyId());

            if (message != null) {
                resultMap.put("changeStatusError", message);
            }

        } else if (StringUtils.equals(action, "passwordRecoveryAction")) {

            String userId = resourceRequest.getParameter("userId");
            message = UsersUtil.changePassword(userId);

            if (message != null) {
                resultMap.put("changePasswordError", message);
            }

        }

        jsonFeed.put("resultMap", JSONFactoryUtil.
                createJSONSerializer().
                serializeDeep(resultMap.entrySet()));
        log.info("jsonFeed.toString()===" + jsonFeed.toString());
        resourceResponse.getWriter().write(jsonFeed.toString());

    }

    private boolean addView(RenderRequest renderRequest, RenderResponse renderResponse, List<Organization> partners) throws SystemException, PortalException, PortletException, IOException {

        Long userId = GetterUtil.getLong(renderRequest.getParameter("userId"), 0);
        User user;

        if (userId == 0) {
            user = UserLocalServiceUtil.createUser(0);

        } else {
            user = UserLocalServiceUtil.getUser(userId);
        }

        renderRequest.setAttribute("partners", partners);
        renderRequest.setAttribute("currentUser", user);
        getPortletContext().getRequestDispatcher("/html/jsp/portlet/users/view/add-user.jsp").include(renderRequest, renderResponse);

        return true;
    }

}
