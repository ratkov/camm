package com.fidoarp.util;

import com.fidoarp.model.UserStatus;
import com.fidoarp.preferences.UserPreferences;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.util.PwdGenerator;
import com.liferay.util.portlet.PortletProps;
import org.apache.commons.lang.StringUtils;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import java.util.*;

public class UsersUtil {

    private static Log log = LogFactoryUtil.getLog(UsersUtil.class);

    public static ResourceBundle resources;

    public static String saveUser (ResourceRequest resourceRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String firstName = resourceRequest.getParameter("firstName");
        String middleName = resourceRequest.getParameter("middleName");
        String lastName = resourceRequest.getParameter("lastName");
        String email = resourceRequest.getParameter("email");
        String login = resourceRequest.getParameter("login");
        String userId = resourceRequest.getParameter("userId");

        String validationResult = validateUser(userId, firstName, middleName, lastName, login, email, themeDisplay.getCompanyId());

        if (validationResult == null) {

            try {

                User user;
                String partnerId = resourceRequest.getParameter("partnerId");

                long [] organizationIds = {Long.parseLong(partnerId)};

                if (StringUtils.equals(userId, "0")) {
                    String password = PwdGenerator.getPassword();
                    int status = UserStatus.ACTIVE.getStatus();

                    user = UserLocalServiceUtil.addUser(0,
                            themeDisplay.getCompanyId(),
                            false, password, password,
                            true, login, email, 0, StringUtils.EMPTY,
                            LocaleUtil.getMostRelevantLocale(), firstName, middleName, lastName, 0,
                            0, true, 1, 1, 1970, "",
                            null, organizationIds, null, null, false, new ServiceContext());

                    ExpandoUtils.addUserValues(user, "status", status);

                    sendPassword(email, password);

                } else {
                    UserLocalServiceUtil.updateUser(Long.parseLong(userId), "", "", "", false,
                            "", "", login, email, 0, "", LocaleUtil.getMostRelevantLocale().toString(),
                            null, null, null, firstName, middleName, lastName, 0, 0, true, 1, 1, 1970,
                            null, "", "", "", "", "", "", "", "", "", "", null, organizationIds, null,
                            null, null, new ServiceContext());
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }

        return validationResult;
    }

    public static void searchUsers(RenderRequest renderRequest) {

        List<User> userList = null;
        String url = PortalUtil.getCurrentURL(renderRequest);

        long partnerId = 0;

        if (renderRequest.getParameter("partnerId") == null) {

            String urlParam = StringUtils.substringAfter(url, "partner=");
            partnerId = urlParam.equals("") ? 0 : Long.parseLong(urlParam);

        } else {
            partnerId = Long.parseLong(renderRequest.getParameter("partnerId"));
        }

        userList = getUsers(renderRequest, partnerId);

        renderRequest.setAttribute("selectedPartnerId", partnerId);
        renderRequest.setAttribute("userList", userList);
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

    private static String validateUser(String userId, String firstName, String middleName, String lastName,
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

                    if (user.getUserId() != Long.parseLong(userId)) {

                        result = resources.getString("user.validation.login.exist");
                        return result;
                    }
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

                        if (user.getUserId() != Long.parseLong(userId)) {
                            result = resources.getString("user.validation.email.exist");
                            return result;

                        }
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

    private static List<User> getUsers(RenderRequest renderRequest, long partnerId) {
        List<User> userList = new ArrayList<User>();

        String action = renderRequest.getParameter("action");
        String status = renderRequest.getParameter("status");

        UserPreferences preferences = UserPreferencesUtil.getPreferences(renderRequest);

        int itemCount = preferences.getItemCount();
        int cpage = GetterUtil.getInteger(renderRequest.getParameter("cpage"), 1);

        int usersCount = 0;
        int remainder = 0;
        int count = 0;

        try {

            if (StringUtils.equals("filterByStatus", action)
                    && !StringUtils.equals("-1", status)) {

                ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();
                DynamicQuery expandoCount = DynamicQueryFactoryUtil
                        .forClass(ExpandoValue.class, classLoader)
                        .add(PropertyFactoryUtil.forName("data").eq(status));

                usersCount = (int) UserLocalServiceUtil.dynamicQueryCount(expandoCount);
                remainder = usersCount % itemCount;
                count = remainder == 0
                        ? usersCount / itemCount
                        : (usersCount / itemCount) + 1;

                DynamicQuery expando = DynamicQueryFactoryUtil
                        .forClass(ExpandoValue.class, classLoader)
                        .add(PropertyFactoryUtil.forName("data").eq(status));

                expando.setLimit((cpage - 1) * itemCount, cpage * itemCount);

                List<ExpandoValue> expandoValues = UserLocalServiceUtil.dynamicQuery(expando);
                List<Long> userIds = new ArrayList<Long>();

                for (ExpandoValue value : expandoValues) {
                    userIds.add(value.getClassPK());
                }

                if (!userIds.isEmpty()) {

                    DynamicQuery userQuery = DynamicQueryFactoryUtil
                            .forClass(User.class, classLoader)
                            .add(PropertyFactoryUtil.forName("userId").in(userIds.toArray()));

                    userList = UserLocalServiceUtil.dynamicQuery(userQuery);
                }

                renderRequest.setAttribute("selectedStatus", status);

            } else {

                usersCount = partnerId == 0
                        ? UserLocalServiceUtil.getUsersCount()
                        : UserLocalServiceUtil.getOrganizationUsersCount(partnerId);

                remainder = usersCount % itemCount;
                count = remainder == 0
                        ? usersCount / itemCount
                        : (usersCount / itemCount) + 1;

                // TODO Need to check!!!
                int lastIndex = usersCount <= itemCount ? cpage * usersCount : cpage * itemCount;

                userList = partnerId == 0
                        ? UserLocalServiceUtil.getUsers((cpage - 1) * itemCount, cpage * itemCount)
                        : UserLocalServiceUtil.getOrganizationUsers(partnerId).subList((cpage - 1) * itemCount, lastIndex);
            }

            renderRequest.setAttribute("cpage", cpage);
            renderRequest.setAttribute("total", count);

        } catch (SystemException e) {
            log.error(e.getMessage(), e);
        }

        return userList;
    }
}
