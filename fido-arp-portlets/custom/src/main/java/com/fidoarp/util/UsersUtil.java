package com.fidoarp.util;

import com.fidoarp.UserStatus;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.PwdGenerator;
import com.liferay.util.portlet.PortletProps;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.util.*;

public class UsersUtil {

    private static Log log = LogFactoryUtil.getLog(UsersUtil.class);

    public static ResourceBundle resources;

    public static String addNewUser (ResourceRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String firstName = resourceRequest.getParameter("firstName");
        String middleName = resourceRequest.getParameter("middleName");
        String lastName = resourceRequest.getParameter("lastName");
        String email = resourceRequest.getParameter("email");
        String login = resourceRequest.getParameter("login");

        String validationResult = validateUser(firstName, middleName, lastName, login, email, themeDisplay.getCompanyId());

        if (validationResult == null) {

            try {
                String partnerId = resourceRequest.getParameter("partnerId");
                String password = PwdGenerator.getPassword();

                int status = UserStatus.DISABLED.getStatus();
                long [] organizationIds = {Long.parseLong(partnerId)};

                User user = UserLocalServiceUtil.addUser(themeDisplay.getUserId(),
                        themeDisplay.getCompanyId(),
                        false, password, password,
                        true, login, email, 0, StringUtils.EMPTY,
                        LocaleUtil.getMostRelevantLocale(), firstName, middleName, lastName, 0,
                        0, true, 1, 1, 1970, "",
                        null, organizationIds, null, null, false, new ServiceContext());

                ExpandoUtils.addUserValues(user, "status", status);

                sendPassword(email, password);

            } catch (Exception e) {
                log.error("Could not create user cause: " + e.getMessage());
            }

        }

        return validationResult;
    }

    public static void searchContainerData(RenderRequest renderRequest, RenderResponse renderResponse, Locale currentLocale) {

        PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        List<User> userList = null;

        PortletURL iteratorURL= renderResponse.createRenderURL();

        SearchContainer<User> searchContainer = new SearchContainer<User>(renderRequest, null,
                null, SearchContainer.DEFAULT_CUR_PARAM, ParamUtil.getInteger(renderRequest, SearchContainer.DEFAULT_DELTA_PARAM, 1),
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

    public static String changeStatus(String status, String userId, long companyId) {

        String result = null;

        if (StringUtils.isNotBlank(status) && StringUtils.isNotBlank(userId)) {
            ExpandoUtils.updateUserValues(Long.parseLong(userId), companyId, status);
        } else {
            result = resources.getString("user.status.update.error");
        }

        return result;
    }

    public static String changePassword(String userId) {

        String result = resources.getString("user.change.password.error");

        if (StringUtils.isNotBlank(userId)) {

            try {

                User user = UserLocalServiceUtil.getUser(Long.parseLong(userId));
                String password = PwdGenerator.getPassword();
                Calendar calendar = GregorianCalendar.getInstance();
                Date date = calendar.getTime();
                String dig = user.getDigest(password);

                user.setDigest(dig);
                user.setPasswordModifiedDate(date);
                user.setComments(null);

                boolean sendEmail = sendPassword(user.getEmailAddress(), password);

                if (sendEmail) {
                    UserLocalServiceUtil.updateUser(user);
                    UserLocalServiceUtil.updatePassword(user.getUserId(), password, password, false);
                    result = null;
                } else {
                    result = resources.getString("user.send.email.error");
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }

        return result;
    }


    // TODO Need create email template
    private static boolean sendPassword(String email, String password) {
        String subject = "";
        String body = "Your password: " + password;
        String form = PortletProps.get("send.mail.from");

        return FidoARPUtils.sendMail(form, email, subject, body);
    }

    private static String validateUser(String firstName, String middleName, String lastName,
                                       String login, String email, long companyId) {

        String result = null;

        // Validate user's first name
        if (StringUtils.isBlank(firstName)) {
            result = resources.getString("user.validation.full.name.required");

            return result;

        } else {
            for (char c : firstName.trim().replaceAll("[- ']", "").toCharArray()) {
                if (!Character.isLetter(c)) {
                    result = resources.getString("user.validation.first.name.failure");

                    return  result;
                }

            }
        }

        // Validate user's middle name
        if (StringUtils.isBlank(middleName)) {
            result = resources.getString("user.validation.full.name.required");

            return result;

        } else {
            for (char c : middleName.trim().replaceAll("[- ']", "").toCharArray()) {
                if (!Character.isLetter(c)) {
                    result = resources.getString("user.validation.middle.name.failure");

                    return result;
                }

            }
        }

        // Validate user's last name
        if (StringUtils.isBlank(lastName)) {
            result = resources.getString("user.validation.full.name.required");

            return result;

        } else {
            for (char c : lastName.trim().replaceAll("[- ']", "").toCharArray()) {
                if (!Character.isLetter(c)) {
                    result = resources.getString("user.validation.last.name.failure");

                    return result;
                }

            }
        }

        // Validate user's login
        if (StringUtils.isNotBlank(login)) {

            try {
                User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, login);

                if (user != null) {
                    result = resources.getString("user.validation.login.exist");
                    return result;
                }

            } catch (SystemException e) {
                log.error(e.getMessage(), e);
            }

        } else {
            result = resources.getString("user.validation.login.required");

            return result;
        }

        // Validate user's email
        if (StringUtils.isNotBlank(email)) {

            if (!Validator.isEmailAddress(email)) {
                result = resources.getString("user.validation.email.wrong.format");

                return result;

            } else {

                try {
                    User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);

                    if (user != null) {
                        result = resources.getString("user.validation.email.exist");
                        return result;
                    }

                } catch (SystemException e) {
                    log.error(e.getMessage(), e);
                }
            }

        } else {
            result = resources.getString("user.validation.email.required");

            return result;
        }

        return result;
    }

}
