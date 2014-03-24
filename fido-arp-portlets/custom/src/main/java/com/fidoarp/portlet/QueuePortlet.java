package com.fidoarp.portlet;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;
import com.fidoarp.dictionary.Dictionaries;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.VelocityFormUtil;
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
                productTypes.addAll(ProductTypeLocalServiceUtil.getListProductTypeByOrganizationId(organizationId));
            }

            renderRequest.setAttribute("productTypes", productTypes);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");

            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("createNewQuery")){
                    Long productId = GetterUtil.getLong(renderRequest.getParameter("selectedProduct"), 0);
                    ProductType productType = ProductTypeLocalServiceUtil.getProductType(productId);
                    VelocityFormUtil velocityFormUtil = new VelocityFormUtil();
                    StringWriter stringWriter = velocityFormUtil.getVelocityForm(renderRequest, productType.getTemplateId(), serviceContext, "{}");
                    renderRequest.setAttribute("templateHtml", stringWriter);
                    renderRequest.setAttribute("productId", productId);
                    renderRequest.setAttribute("show", "query-form.jsp");
                }
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
            String productCode = GetterUtil.getString(actionRequest.getParameter("json"), "");
            Long productTypeId = GetterUtil.getLong(actionRequest.getParameter("productTypeId"), 0);
            actionResponse.setWindowState(LiferayWindowState.EXCLUSIVE);
        }catch (Exception e){
           log.error(e.getMessage(), e);
        }
    }
}
