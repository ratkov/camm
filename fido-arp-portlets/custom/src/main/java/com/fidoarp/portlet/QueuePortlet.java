package com.fidoarp.portlet;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.AppLocalServiceUtil;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;
import com.fidoarp.dictionary.Dictionaries;
import com.fidoarp.model.AppWrapper;
import com.fidoarp.model.PartnerStatus;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.preferences.QueuePreferences;
import com.fidoarp.util.ExportUtil;
import com.fidoarp.util.FieldsUtil;
import com.fidoarp.util.QueuePreferencesUtil;
import com.fidoarp.util.VelocityFormUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactory;
import com.liferay.portal.security.permission.PermissionCheckerFactoryImpl;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.portlet.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueuePortlet extends FidoMVCPortlet  {
    /**
     * The Constant LOG.
     *
     */
    private static final Log log = LogFactoryUtil.getLog(QueuePortlet.class);
    private final Dictionaries dictionaries = new Dictionaries();

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)){
                super.render(renderRequest, renderResponse);
                return;
            }
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), renderRequest);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");
            int cpage = GetterUtil.getInteger(renderRequest.getParameter("cpage"), 1);

            renderRequest.setAttribute("cpage", cpage);

            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("queryForm")){
                    queryForm(renderRequest, serviceContext);
                }
            }else{
                User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

                List<ProductType> productTypes = new ArrayList<ProductType>();
                long[] listOrganizations = user.getOrganizationIds();
                for (long organizationId : listOrganizations){
                    Organization organization = OrganizationLocalServiceUtil.getOrganization(organizationId);
                    if(organization.getExpandoBridge().getAttribute("status") != null){
                        String status = organization.getExpandoBridge().getAttribute("status").toString();
                        if(Integer.valueOf(status) == PartnerStatus.DISABLED.getStatus()){
                           continue;
                       }
                    }
                    productTypes.addAll(ProductTypeLocalServiceUtil.getListProductTypeByOrganizationIdStatus(organizationId, true));
                }

                List<AppStatus> appStatuses = AppStatusLocalServiceUtil.getAppStatuses(-1, -1);
                List<User> users = UserLocalServiceUtil.getUsers(-1, -1);

                renderRequest.setAttribute("productTypes", productTypes);
                renderRequest.setAttribute("appStatuses", appStatuses);
                renderRequest.setAttribute("users", users);

                getApps(renderRequest, serviceContext.getLocale(), serviceContext.getCompanyId(), true);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest,
                              ResourceResponse resourceResponse) throws IOException, PortletException {
        //needs for load dictionary by ajax
        String dictionary = ParamUtil.getString(resourceRequest, "dictionary");
        //needs for excel file
        boolean excel = GetterUtil.getBoolean(ParamUtil.getString(resourceRequest, "excel"), false);
        if(StringUtils.isNotEmpty(dictionary) && StringUtils.isNotBlank(dictionary)){

            List<DetailsPair> detailsPairs = dictionaries.get(dictionary).execute(resourceRequest);
            JSONArray jsonFeed = convertListToJsonValues(detailsPairs);

            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonFeed.toString());
        }else if(excel){
            try{
                ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), resourceRequest);
                Locale locale = serviceContext.getLocale();
                List<App> apps = getApps(resourceRequest, locale, serviceContext.getCompanyId(), false);
                String sheetName = "cred_app_" + new Date().getTime();
                Workbook workbook = new ExportUtil().getAppExcel(apps, sheetName, locale);
                resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+sheetName+".xls");
                resourceResponse.setContentType("application/vnd.ms-excel");
                resourceResponse.setCharacterEncoding("UTF-8");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                PortletResponseUtil.sendFile(resourceRequest, resourceResponse, sheetName+".xls", baos.toByteArray(), "application/vnd.ms-excel");
            }catch (Exception e){
                log.error(e.getMessage(), e);
            }
        }
    }

    public void saveQuery(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), actionRequest);
            actionResponse.setWindowState(LiferayWindowState.EXCLUSIVE);

            String json = GetterUtil.getString(actionRequest.getParameter("json"), "");

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

            long appId = jsonObject.getLong("appId", 0);
            long productId = jsonObject.getLong("productId", 0);
            long appStatusId = jsonObject.getLong("appStatusId", 0);
            String cpage = jsonObject.getString("cpage", "1");

            if(appId != 0 && productId != 0){
                ProductType productType = ProductTypeLocalServiceUtil.getProductType(productId);
                long templateId = productType.getTemplateId();
                String validation = new FieldsUtil().validateTemplate(jsonObject, templateId);
                if(StringUtils.isEmpty(validation) || StringUtils.isBlank(validation)){
                    App app = null;
                    boolean isNew = false;
                    try{
                        if(AppLocalServiceUtil.getApp(appId) != null)
                            app = AppLocalServiceUtil.getApp(appId);
                    }catch(Exception e){
                        app = AppLocalServiceUtil.createApp(appId);
                        isNew = true;
                    }

                    if(app != null){
                        app.setProductTypeId(productId);
                        app.setOrganizationId(productType.getOrganizationId());
                        app.setCreatedDate(new Date());
                        app.setStatusId(appStatusId);
                        app.setUserId(serviceContext.getUserId());
                        //set whole questionnaire
                        JSONObject questionnaire = new FieldsUtil().mergeStructure(jsonObject, templateId, appId);
                        app.setQuestionnaire(questionnaire.toString());
                        //set client name
                        String lastName = jsonObject.getString("lastName", "");
                        String firstName = jsonObject.getString("firstName", "");
                        String middleName = jsonObject.getString("middleName", "");
                        app.setClientName(lastName  + " " + firstName + " "+ middleName);
                        //set client okpo
                        String clientOkpo = jsonObject.getString("identificationNumber", "");
                        app.setClientOkpo(clientOkpo);
                        //set sum
                        Double loanSumUAH = jsonObject.getDouble("loanSumUAH", 0);
                        app.setCreditAmount(loanSumUAH);
                        //set phone
                        String contactPhone = jsonObject.getString("mobilePhone", "");
                        app.setContactPhone(contactPhone);
                        if(isNew){
                            app.setStatusChangeDate(new Date());
                            AppLocalServiceUtil.addApp(app);
                        }
                        else{
                            AppLocalServiceUtil.updateApp(app);
                        }
                        actionRequest.setAttribute("info", "queues.data.is.saved.success");
                    }
                }else{
                    actionRequest.setAttribute("error", validation);
                }
                actionResponse.setRenderParameter("cpage", cpage);
            }
        }catch (Exception e){
            log.error("QueuePortlet.saveQuery() error: " + Arrays.toString(e.getStackTrace()), e);
        }
    }


    private JSONArray convertListToJsonValues(List<DetailsPair> list) {
        JSONArray values = JSONFactoryUtil.getJSONFactory().createJSONArray();
        if (list != null) {
            for (DetailsPair pair : list) {
                JSONObject obj = JSONFactoryUtil.getJSONFactory().createJSONObject();
                obj.put("id", pair.getLeft());
                obj.put("value", pair.getRight());
                values.put(obj);
            }
        }
        return values;
    }


    private List<App> getApps(PortletRequest renderRequest, Locale locale, long companyId, boolean hasPaging) throws com.liferay.portal.kernel.exception.SystemException {
        try{
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

            long partner = GetterUtil.getLong(renderRequest.getParameter("partner"), 0);

            PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
            boolean isAdmin = false;

            Map<String, Object> map = getAttrMap(renderRequest, companyId);
            boolean search = GetterUtil.getBoolean(renderRequest.getParameter("isSearch"), false);
            Integer itemCount = getItemCount(renderRequest);

            if(permissionChecker.isOmniadmin() || permissionChecker.isCompanyAdmin()){
                isAdmin = true;
            }


            int appCount = 0;
            if(search){
                if(isAdmin){
                    appCount = AppLocalServiceUtil.getSearchResultCount((Long) map.get("searchId"), convertDate((String) map.get("searchDateStart")),
                        convertDate((String) map.get("searchDateEnd")), (String) map.get("searchName"), (String) map.get("searchOkpo"), (String) map.get("searchPhone"),
                        (Double) map.get("searchSum"), (Long) map.get("searchAppStatus"), (String) map.get("searchComment"), (Long) map.get("searchUser"), partner);
                } else {
                    appCount = AppLocalServiceUtil.getSearchResultCount((Long) map.get("searchId"), convertDate((String) map.get("searchDateStart")),
                            convertDate((String) map.get("searchDateEnd")), (String) map.get("searchName"), (String) map.get("searchOkpo"), (String) map.get("searchPhone"),
                            (Double) map.get("searchSum"), (Long) map.get("searchAppStatus"), (String) map.get("searchComment"), themeDisplay.getUserId(), partner);
                }
            }else {
                if(isAdmin){
                    appCount = partner != 0 ?
                            AppLocalServiceUtil.getSearchResultCount( 0, null, null, "", "", "", 0, 0, "", 0, partner) : AppLocalServiceUtil.getAppsCount();
                }else{
                    appCount = partner != 0 ?
                            AppLocalServiceUtil.getSearchResultCount( 0, null, null, "", "", "", 0, 0, "", themeDisplay.getUserId(), partner)
                            : AppLocalServiceUtil.getAppCountByUser(themeDisplay.getUserId());
                }
            }
            int start = -1;
            int end = -1;
            if(hasPaging){
                int cpage = GetterUtil.getInteger(renderRequest.getParameter("cpage"), 1);
                int remainder = appCount % itemCount;
                int count = (appCount - remainder) / itemCount;
                if (remainder != 0) count++;
                start = (cpage - 1) * itemCount;
                end = cpage * itemCount;

                renderRequest.setAttribute("pageCount", count);
                renderRequest.setAttribute("cpage", cpage);
            }

            List<App> apps = Collections.emptyList();
            if(search){
                renderRequest.setAttribute("isSearch", true);
                renderRequest.setAttribute("mapSearch", map);

                if(isAdmin){
                    apps =  AppLocalServiceUtil.getSearchResult((Long)map.get("searchId"), convertDate((String)map.get("searchDateStart")),
                        convertDate((String) map.get("searchDateEnd")), (String) map.get("searchName"), (String) map.get("searchOkpo"), (String) map.get("searchPhone"),
                        (Double) map.get("searchSum"), (Long) map.get("searchAppStatus"), (String) map.get("searchComment"), (Long) map.get("searchUser"), partner, start, end);
                } else {
                    apps =  AppLocalServiceUtil.getSearchResult((Long)map.get("searchId"), convertDate((String)map.get("searchDateStart")),
                            convertDate((String) map.get("searchDateEnd")), (String) map.get("searchName"), (String) map.get("searchOkpo"), (String) map.get("searchPhone"),
                            (Double) map.get("searchSum"), (Long) map.get("searchAppStatus"), (String) map.get("searchComment"), themeDisplay.getUserId(), partner, start, end);
                }
            }else{
                if(isAdmin){
                    apps = partner != 0
                            ? AppLocalServiceUtil.getSearchResult(0l, null, null, "", "", "", 0, 0, "", 0l, partner, start, end)
                            : AppLocalServiceUtil.getApps(start, end);
                }else{
                    apps = partner != 0
                            ? AppLocalServiceUtil.getSearchResult(0l, null, null, "", "", "", 0, 0l, "", themeDisplay.getUserId(), partner, start, end) :
                            AppLocalServiceUtil.getAppByUser(themeDisplay.getUserId(), start, end);
                }
            }


            List<AppWrapper> appWrappers = new ArrayList<AppWrapper>();
            for(App app : apps){
                appWrappers.add(new AppWrapper(app, locale));
            }

            renderRequest.setAttribute("apps", appWrappers);
            renderRequest.setAttribute("isAdmin", isAdmin);
            if(partner != 0){
                renderRequest.setAttribute("partner", partner);
                renderRequest.setAttribute("partnerName", OrganizationLocalServiceUtil.getOrganization(partner).getName());
            }

            return apps;
        }catch (Exception e){
            log.error("QueuePortlet.getApps() error: " + Arrays.toString(e.getStackTrace()), e);
        }
        return Collections.emptyList();
    }


    private Map<String, Object> getAttrMap(PortletRequest renderRequest, long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map.put("searchId", GetterUtil.getLong(renderRequest.getParameter("searchId"), 0));
            map.put("searchDateStart", GetterUtil.getString(renderRequest.getParameter("searchDateStart"), ""));
            map.put("searchDateEnd", GetterUtil.getString(renderRequest.getParameter("searchDateEnd"), ""));
            map.put("searchName", GetterUtil.getString(renderRequest.getParameter("searchName"), ""));
            map.put("searchOkpo", GetterUtil.getString(renderRequest.getParameter("searchOkpo"), ""));
            map.put("searchPhone", GetterUtil.getString(renderRequest.getParameter("searchPhone"), ""));
            map.put("searchSum",  GetterUtil.getDouble(renderRequest.getParameter("searchSum"), 0));
            map.put("searchAppStatus", GetterUtil.getLong(renderRequest.getParameter("searchAppStatus"), 0));
            map.put("searchComment", GetterUtil.getString(renderRequest.getParameter("searchComment"), ""));
            map.put("searchUser", GetterUtil.getLong(renderRequest.getParameter("searchUser"), 0));
        }catch (Exception e){
            log.error("QueuePortlet.getAttrMap() error: " + Arrays.toString(e.getStackTrace()), e);
        }
        return map;
    }

    private Date convertDate(String dateStr) throws ParseException {
        if(StringUtils.isNotBlank(dateStr) && StringUtils.isNotEmpty(dateStr)){
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");
            Date date = formatter.parse(dateStr);

            Calendar start = Calendar.getInstance();
            start.setTime(date);
            return new Date(start.getTimeInMillis());
        }
        return null;
    }

    private void queryForm(RenderRequest renderRequest, ServiceContext serviceContext) throws com.liferay.portal.kernel.exception.PortalException, com.liferay.portal.kernel.exception.SystemException {
        Long appId = GetterUtil.getLong(renderRequest.getParameter("appId"), 0);
        Long appStatusId;
        ProductType productType = null;
        if(appId == 0){
            Long productId = GetterUtil.getLong(renderRequest.getParameter("selectedProduct"), 0);
            appStatusId = GetterUtil.getLong(renderRequest.getParameter("selectedAppStatus"), 0);
            if(productId != 0){
                productType = ProductTypeLocalServiceUtil.getProductType(productId);

                VelocityFormUtil velocityFormUtil = new VelocityFormUtil();
                StringWriter stringWriter = velocityFormUtil.getVelocityForm(renderRequest, productType.getTemplateId(), serviceContext, "{}");
                renderRequest.setAttribute("templateHtml", stringWriter);
                renderRequest.setAttribute("productId", productId);
                renderRequest.setAttribute("appId", CounterLocalServiceUtil.increment());
                renderRequest.setAttribute("appStatusId", appStatusId);
            }
        }else{
            App app = AppLocalServiceUtil.getApp(appId);
            appStatusId = app.getStatusId();
            productType = ProductTypeLocalServiceUtil.getProductType(app.getProductTypeId());

            VelocityFormUtil velocityFormUtil = new VelocityFormUtil();
            StringWriter stringWriter = velocityFormUtil.getVelocityForm(renderRequest, productType.getTemplateId(), serviceContext, app.getQuestionnaire());
            renderRequest.setAttribute("templateHtml", stringWriter);
            renderRequest.setAttribute("productId", app.getProductTypeId());
            renderRequest.setAttribute("appId", app.getAppId());
            renderRequest.setAttribute("appStatusId", app.getStatusId());
        }

        AppStatus appStatus = AppStatusLocalServiceUtil.getAppStatusByCode("PROJECT");
        if(appStatus != null){
            long statusProjectId = appStatus.getAppStatusId();
            renderRequest.setAttribute("isProject", statusProjectId == appStatusId);
        }
        renderRequest.setAttribute("show", "query-form.jsp");
    }

    private Integer getItemCount(PortletRequest renderRequest) {
        QueuePreferences preferences = QueuePreferencesUtil.getPreferences(renderRequest);
        return preferences.getItemCount();
    }

    //Edit Mode
    @Override
    public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        QueuePreferences preferences = QueuePreferencesUtil.getPreferences(renderRequest);
        renderRequest.setAttribute("itemCount", preferences.getItemCount());
        super.doEdit(renderRequest, renderResponse);
    }

    public void saveSettings(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            int itemCount =  GetterUtil.getInteger(actionRequest.getParameter("itemCount"), 5);
            if(itemCount != 0){
                QueuePreferences queuePreferences = new QueuePreferences();
                queuePreferences.setItemCount(itemCount);
                QueuePreferencesUtil.setPreferences(actionRequest, queuePreferences);
                actionResponse.setRenderParameter("info", "global.data.is.saved.success");
            }else{
                actionResponse.setRenderParameter("error", "global.data.is.wrong");
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            actionResponse.setRenderParameter("error", "global.data.is.wrong");
        }
    }

}
