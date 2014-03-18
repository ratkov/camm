package com.fidoarp.portlet;

import com.fidoarp.catalog.model.ProductType;
import com.fidoarp.catalog.model.impl.ProductTypeImpl;
import com.fidoarp.catalog.service.ProductTypeLocalService;
import com.fidoarp.catalog.service.ProductTypeLocalServiceUtil;
import com.fidoarp.model.ProductTypeStatus;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartnerProductTypePortlet extends FidoMVCPortlet {
    /**
     * The Constant LOG.
     */
    private static final Log log = LogFactoryUtil.getLog(PartnerProductTypePortlet.class);

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)){
                super.render(renderRequest, renderResponse);
            }
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), renderRequest);

            List<Organization> organizations = OrganizationLocalServiceUtil.getOrganizations(-1,-1);
            List<DDMTemplate> ddmTemplates = DDMTemplateLocalServiceUtil.getDDMTemplates(-1, -1);

            Long partnerId = GetterUtil.getLong(renderRequest.getParameter("partnerId"), 0);

            if(partnerId == 0 && organizations.iterator().hasNext()){
                partnerId = organizations.iterator().next().getOrganizationId();
                renderRequest.setAttribute("selPartner", partnerId);
            }

            List<ProductType> partnerProductTypes = ProductTypeLocalServiceUtil.getListProductTypeByOrganizationId(partnerId);

            renderRequest.setAttribute("ddmTemplates", ddmTemplates);
            renderRequest.setAttribute("partners", organizations);
            renderRequest.setAttribute("statuses", ListUtil.fromArray(ProductTypeStatus.values()));
            renderRequest.setAttribute("partnerProductTypes", partnerProductTypes);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");
            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("changePartner")){

                }else if(action.equals("edit")){
                    if (editView(renderRequest, renderResponse, partnerId)) return;
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }

    private boolean editView(RenderRequest renderRequest, RenderResponse renderResponse, Long partnerId) throws SystemException, com.liferay.portal.kernel.exception.PortalException, PortletException, IOException {
        Long productTypeId = GetterUtil.getLong(renderRequest.getParameter("productTypeId"), 0);

        if(partnerId != 0){
            ProductType productType;
            if(productTypeId == 0){
                productType = ProductTypeLocalServiceUtil.createProductType(CounterLocalServiceUtil.increment());
                productType.setOrganizationId(partnerId);
                ProductTypeLocalServiceUtil.addProductType(productType);
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
            String productName = GetterUtil.getString(actionRequest.getParameter("productName"), "");
            String productDescription = GetterUtil.getString(actionRequest.getParameter("productDescription"), "");
            String  productStatus = GetterUtil.getString(actionRequest.getParameter("productStatus"), "");
            Long partnerId = GetterUtil.getLong(actionRequest.getParameter("partnerId"), 0);
            Long productTypeId = GetterUtil.getLong(actionRequest.getParameter("productTypeId"), 0);
            Long productTemplate = GetterUtil.getLong(actionRequest.getParameter("productTemplate"), 0);

            if(StringUtils.isNotBlank(productCode) && StringUtils.isNotEmpty(productCode) &&
                StringUtils.isNotBlank(productName) && StringUtils.isNotEmpty(productName) &&
                StringUtils.isNotBlank(productStatus) && StringUtils.isNotEmpty(productStatus) &&
                partnerId != 0 &&  productTemplate != 0){
                ProductType productType;
                    productType = productTypeId == 0
                                    ? ProductTypeLocalServiceUtil.createProductType(CounterLocalServiceUtil.increment())
                                    : ProductTypeLocalServiceUtil.getProductType(productTypeId);
                    productType.setProductTypeCode(productCode);
                    productType.setName(productName);
                    productType.setDescription(productDescription);
                    productType.setStatus(productStatus);
                    productType.setTemplateId(productTemplate);
                    productType.setOrganizationId(partnerId);
                    if(productTypeId == 0){
                        ProductTypeLocalServiceUtil.addProductType(productType);
                    } else {
                        ProductTypeLocalServiceUtil.updateProductType(productType);
                    }
                    actionRequest.setAttribute("info", "partner.product.type.data.is.saved.success");
            }else{
                actionRequest.setAttribute("error", "partner.product.type.data.is.wrong");
            }
            actionRequest.setAttribute("action", "edit");
            actionResponse.setWindowState(LiferayWindowState.EXCLUSIVE);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
