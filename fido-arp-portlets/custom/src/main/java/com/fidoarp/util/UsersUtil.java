package com.fidoarp.util;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class UsersUtil {

    private static Log log = LogFactoryUtil.getLog(UsersUtil.class);

    public static ResourceBundle resources;

    public static String addNewUser (ResourceRequest resourceRequest) {
        String fullName = resourceRequest.getParameter("fullName");
        String email = resourceRequest.getParameter("email");
        String login = resourceRequest.getParameter("login");
        String partnerId = resourceRequest.getParameter("partnerId");

        String validationResult = validateUser(fullName, email, login);

        if (validationResult == null) {
            // TODO Here will be code for saving new user

        }

        return validationResult;
    }

    public static void searchContainerData(RenderRequest renderRequest, RenderResponse renderResponse, Locale currentLocale) {

        PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        List<User> userList = null;

        PortletURL iteratorURL= renderResponse.createRenderURL();

        SearchContainer<User> searchContainer = new SearchContainer<User>(renderRequest, null,
                null, SearchContainer.DEFAULT_CUR_PARAM, ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM, 5),
                iteratorURL, null, LanguageUtil.get(portletConfig, currentLocale, "No UserGroups were Found"));

        int total = 0;

        try {

            String action = renderRequest.getParameter("action");

            if (StringUtils.equals("filterUser", action)) {

                String partnerId = renderRequest.getParameter("partnerId");

                userList = StringUtils.equals("0", partnerId)
                        ? UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd())
                        : UserLocalServiceUtil.getOrganizationUsers(Long.parseLong(partnerId));

                total = StringUtils.equals("0", partnerId)
                        ? UserLocalServiceUtil.getUsersCount()
                        : UserLocalServiceUtil.getOrganizationUsersCount(Long.parseLong(partnerId));

                renderRequest.setAttribute("userList", userList);

            } else {
                userList = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
                total = UserLocalServiceUtil.getUsersCount();
            }

        } catch (SystemException e) {
            log.error("Could not get users cause " + e.getMessage());
        }

        searchContainer.setTotal(total);
        searchContainer.setResults(userList);
        renderRequest.setAttribute("userSearchContainer", searchContainer);

    }

    private static String validateUser(String fullName, String login, String email) {

        String result = null;

        // Validate user's full name
        if (StringUtils.isBlank(fullName)) {
            result = resources.getString("user.validation.full.name.required");

            return result;
        }

        // Validate user's login
        if (StringUtils.isNotBlank(login)) {
            // TODO Here will be user's login validation

        } else {
            result = resources.getString("user.validation.login.required");

            return result;
        }

        // Validate user's email
        if (StringUtils.isNotBlank(email)) {
            // TODO Here will be user's email validation

        } else {
            result = resources.getString("user.validation.email.required");

            return result;
        }

        return result;
    }

}
