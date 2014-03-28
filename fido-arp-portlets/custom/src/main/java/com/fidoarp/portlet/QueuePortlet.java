package com.fidoarp.portlet;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.AppLocalService;
import com.fidoarp.catalog.service.AppLocalServiceUtil;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;
import com.fidoarp.dictionary.Dictionaries;
import com.fidoarp.model.AppWrapper;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.FieldsUtil;
import com.fidoarp.util.VelocityFormUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QueuePortlet extends FidoMVCPortlet  {
    /**
     * The Constant LOG.
     */
    private static final Log log = LogFactoryUtil.getLog(QueuePortlet.class);
    private final Dictionaries dictionaries = new Dictionaries();

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)){
                super.render(renderRequest, renderResponse);
            }
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), renderRequest);

            User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

            List<ProductType> productTypes = new ArrayList<ProductType>();
            long[] listOrganizations = user.getOrganizationIds();
            for (long organizationId : listOrganizations){
                productTypes.addAll(ProductTypeLocalServiceUtil.getListProductTypeByOrganizationIdStatus(organizationId, true));
            }

            List<AppStatus> appStatuses = AppStatusLocalServiceUtil.getAppStatuses(-1, -1);
            List<App> apps = AppLocalServiceUtil.getApps(-1, -1);
            List<AppWrapper> appWrappers = new ArrayList<AppWrapper>();
            for(App app : apps){
                appWrappers.add(new AppWrapper(app, serviceContext.getLocale()));
            }

            renderRequest.setAttribute("productTypes", productTypes);
            renderRequest.setAttribute("appStatuses", appStatuses);
            renderRequest.setAttribute("apps", appWrappers);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");

            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("queryForm")){
                    queryForm(renderRequest, serviceContext);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
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

    @Override
    public void serveResource(ResourceRequest resourceRequest,
                              ResourceResponse resourceResponse) throws IOException, PortletException {
        //needs for load dictionary by ajax
        String dictionary = ParamUtil.getString(resourceRequest, "dictionary");
        if(StringUtils.isNotEmpty(dictionary) && StringUtils.isNotBlank(dictionary)){

            List<DetailsPair> detailsPairs = dictionaries.get(dictionary).execute(resourceRequest);
            JSONArray jsonFeed = convertListToJsonValues(detailsPairs);

            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonFeed.toString());
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

    public void saveQuery(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), actionRequest);
            actionResponse.setWindowState(LiferayWindowState.EXCLUSIVE);

            String json = GetterUtil.getString(actionRequest.getParameter("json"), "");
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

            long appId = jsonObject.getLong("appId", 0);
            long productId = jsonObject.getLong("productId", 0);
            long appStatusId = jsonObject.getLong("appStatusId", 0);

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
                        Integer loanSumUAH = jsonObject.getInt("loanSumUAH", 0);
                        app.setCreditAmount(loanSumUAH);
                        //set phone
                        String contactPhone = jsonObject.getString("mobilePhone", "");
                        app.setContactPhone(contactPhone);
                        if(isNew)
                            AppLocalServiceUtil.addApp(app);
                        else
                            AppLocalServiceUtil.updateApp(app);
                        actionRequest.setAttribute("info", "queues.data.is.saved.success");
                    }
                }else{
                    actionRequest.setAttribute("error", validation);
                }
            }

        }catch (Exception e){
           log.error("QueuePortlet.saveQuery() error: " + Arrays.toString(e.getStackTrace()), e);
        }
    }
}
