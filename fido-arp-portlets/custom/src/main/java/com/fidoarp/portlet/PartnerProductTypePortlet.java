package com.fidoarp.portlet;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;
import com.fidoarp.dictionary.Dictionaries;
import com.fidoarp.model.questionnaire.DetailsPair;
import com.fidoarp.util.VelocityFormUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PartnerProductTypePortlet extends FidoMVCPortlet {
    /**
     * The Constant LOG.
     */
    private static final Log log = LogFactoryUtil.getLog(PartnerProductTypePortlet.class);
    private final Dictionaries dictionaries = new Dictionaries();

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            boolean mainView = false;
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)){
                super.render(renderRequest, renderResponse);
            }
            ServiceContext serviceContext = ServiceContextFactory.getInstance(PartnerProductTypePortlet.class.getName(), renderRequest);

            List<Organization> organizations = OrganizationLocalServiceUtil.getOrganizations(-1, -1);
            List<DDMTemplate> ddmTemplates = DDMTemplateLocalServiceUtil.getDDMTemplates(-1, -1);

            Long partnerId = GetterUtil.getLong(renderRequest.getParameter("partnerId"), 0);

            if(partnerId == 0 && organizations.iterator().hasNext()){
                partnerId = organizations.iterator().next().getOrganizationId();
            }

            renderRequest.setAttribute("selPartner", partnerId);
            renderRequest.setAttribute("ddmTemplates", ddmTemplates);
            renderRequest.setAttribute("partners", organizations);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");
            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("changePartner")){
                    mainView = true;
                }else if(action.equals("edit")){
                    if (editView(renderRequest, renderResponse, partnerId)) return;
                }else if(action.equals("changeStatus")){
                    if (changeStatus(renderRequest, renderResponse))
                        mainView = true;
                }else if(action.equals("previewProduct")){
                    Long productTypeId = GetterUtil.getLong(renderRequest.getParameter("productTypeId"), 0);
                    ProductType productType = ProductTypeLocalServiceUtil.getProductType(productTypeId);
                    long templateId = productType.getTemplateId();
                    VelocityFormUtil velocityFormUtil = new VelocityFormUtil();
                    StringWriter stringWriter = velocityFormUtil.getVelocityForm(renderRequest, templateId, serviceContext, "{}");
                    renderRequest.setAttribute("templateHtml", stringWriter);
                    getPortletContext().getRequestDispatcher("/html/jsp/portlet/partner-product-type/view/preview-template.jsp").include(renderRequest, renderResponse);
                    return;
                }
            }

            List<ProductType> partnerProductTypes = ProductTypeLocalServiceUtil.getListProductTypeByOrganizationId(partnerId);
            renderRequest.setAttribute("partnerProductTypes", partnerProductTypes);

            if(mainView){
                getPortletContext().getRequestDispatcher("/html/jsp/portlet/partner-product-type/view/main-view.jsp").include(renderRequest, renderResponse);
                return;
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


    private boolean changeStatus(RenderRequest renderRequest, RenderResponse renderResponse) throws com.liferay.portal.kernel.exception.PortalException, SystemException, PortletException, IOException {
        Long productTypeId = GetterUtil.getLong(renderRequest.getParameter("productTypeId"), 0);
        ProductType productType = ProductTypeLocalServiceUtil.getProductType(productTypeId);
        boolean status = productType.getStatus();
        productType.setStatus(!status);
        ProductTypeLocalServiceUtil.updateProductType(productType);
        return true;
    }

    private boolean editView(RenderRequest renderRequest, RenderResponse renderResponse, Long partnerId) throws SystemException, com.liferay.portal.kernel.exception.PortalException, PortletException, IOException {
        Long productTypeId = GetterUtil.getLong(renderRequest.getParameter("productTypeId"), 0);

        if(partnerId != 0){
            ProductType productType;
            if(productTypeId == 0){
                productType = ProductTypeLocalServiceUtil.createProductType(CounterLocalServiceUtil.increment());
                productType.setOrganizationId(partnerId);
                productType.setProductTypeId(0);
            }else{
                productType = ProductTypeLocalServiceUtil.getProductType(productTypeId);
            }

            renderRequest.setAttribute("productType", productType);
            getPortletContext().getRequestDispatcher("/html/jsp/portlet/partner-product-type/view/edit-product-type.jsp").include(renderRequest, renderResponse);
            return true;
        }
        return false;
    }

    public void saveProductType(ActionRequest actionRequest, ActionResponse actionResponse){
        try {
            String productCode = GetterUtil.getString(actionRequest.getParameter("productCode"), "");
            Map<Locale, String> productNames = LocalizationUtil.getLocalizationMap(actionRequest, "productName");
            Map<Locale, String> productDescriptions = LocalizationUtil.getLocalizationMap(actionRequest, "productDescription");
            Boolean productStatus = GetterUtil.getBoolean(actionRequest.getParameter("productStatus"), false);
            Long partnerId = GetterUtil.getLong(actionRequest.getParameter("partnerId"), 0);
            Long productTypeId = GetterUtil.getLong(actionRequest.getParameter("productTypeId"), 0);
            Long productTemplate = GetterUtil.getLong(actionRequest.getParameter("productTemplate"), 0);

            actionResponse.setWindowState(LiferayWindowState.NORMAL);

            if(StringUtils.isNotBlank(productCode) && StringUtils.isNotEmpty(productCode) &&
                productNames.size() > 0 && partnerId != 0 &&  productTemplate != 0){
                ProductType productType = productTypeId == 0
                            ? ProductTypeLocalServiceUtil.createProductType(CounterLocalServiceUtil.increment())
                            : ProductTypeLocalServiceUtil.getProductType(productTypeId);
                productType.setProductTypeCode(productCode);
                productType.setNameMap(productNames);
                productType.setDescriptionMap(productDescriptions);
                productType.setStatus(productStatus);
                productType.setTemplateId(productTemplate);
                productType.setOrganizationId(partnerId);
                if(productTypeId == 0){
                    ProductType productTypeByCode = ProductTypeLocalServiceUtil.getProductTypeByCode(productCode);
                    if(productTypeByCode != null){
                        actionRequest.setAttribute("error", "app.status.code.is.not.unique");
                        actionRequest.setAttribute("action", "edit");
                        return;
                    }
                    ProductTypeLocalServiceUtil.addProductType(productType);
                } else {
                    ProductTypeLocalServiceUtil.updateProductType(productType);
                }
                actionRequest.setAttribute("info", "partner.product.type.data.is.saved.success");
                actionResponse.setRenderParameter("partnerId", partnerId.toString());
            }else{
                actionRequest.setAttribute("error", "partner.product.type.data.is.wrong");
            }
            actionRequest.setAttribute("action", "edit");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
