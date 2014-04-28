package com.fidoarp.portlet;

import com.fidoarp.model.PartnerStatus;
import com.fidoarp.model.PartnerWrapper;
import com.fidoarp.model.UserStatus;
import com.fidoarp.preferences.PartnerPreferences;
import com.fidoarp.util.PartnerPreferencesUtil;
import com.fidoarp.util.PartnerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartnerPortlet extends FidoMVCPortlet {
    /**
     * The Constant LOG.
     *
     */
    private static final Log log = LogFactoryUtil.getLog(PartnerPortlet.class);

    @Override
    public void render(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException, PortletException {
        try {
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)) {
                super.render(renderRequest, renderResponse);
                return;
            }

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");

            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("partnerForm")){
                    partnerForm(renderRequest);
                }
            }else{
                int partnerCount = OrganizationLocalServiceUtil.getOrganizationsCount();
                int itemCount = getItemCount(renderRequest);
                int cpage = GetterUtil.getInteger(renderRequest.getParameter("cpage"), 1);
                int remainder = partnerCount % itemCount;
                int count = (partnerCount - remainder) / itemCount;
                if (remainder != 0) count++;
                int start = (cpage - 1) * itemCount;
                int end = cpage * itemCount;

                List<Organization> partners = OrganizationLocalServiceUtil.getOrganizations(start, end);

                List<PartnerWrapper> organizationWrappers = new ArrayList<PartnerWrapper>();
                for(Organization organization : partners){
                    organizationWrappers.add(new PartnerWrapper(organization));
                }

                renderRequest.setAttribute("partnerStatus", PartnerStatus.values());
                renderRequest.setAttribute("partners", organizationWrappers);
                renderRequest.setAttribute("pageCount", count);
                renderRequest.setAttribute("cpage", cpage);
                renderRequest.setAttribute("orderPlid", getOrderLinkPlid(renderRequest));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }


    private Integer getItemCount(PortletRequest renderRequest) {
        PartnerPreferences preferences = PartnerPreferencesUtil.getPreferences(renderRequest);
        return preferences.getItemCount();
    }

    private long getOrderLinkPlid(PortletRequest renderRequest) {
        PartnerPreferences preferences = PartnerPreferencesUtil.getPreferences(renderRequest);
        return preferences.getOrderLink();
    }


    private void partnerForm(RenderRequest renderRequest) {
        try{
            Long partnerId = GetterUtil.getLong(renderRequest.getParameter("partnerId"), 0);
            if(partnerId != 0){
                Organization organization = OrganizationLocalServiceUtil.getOrganization(partnerId);
                renderRequest.setAttribute("partner", organization);
            }

            renderRequest.setAttribute("show", "add-partner.jsp");

        }catch (Exception e){
            log.error("PartnerPortlet.partnerForm() error: " + Arrays.toString(e.getStackTrace()), e);
        }
    }

    public void savePartner(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            actionResponse.setWindowState(LiferayWindowState.NORMAL);
            String message = new PartnerUtil(actionRequest.getLocale()).savePartner(actionRequest);

            if (message != null) {
                actionResponse.setRenderParameter("error", message);
                actionResponse.setRenderParameter("show", "add-partner.jsp");
            }

            actionResponse.setRenderParameter("info", "partner.save.success");
        }catch (Exception e){
            log.error("PartnerPortlet.savePartner() error: " + Arrays.toString(e.getStackTrace()), e);
            actionRequest.setAttribute("error", "partner.save.error");
        }
    }


    public void statusChange(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            actionResponse.setWindowState(LiferayWindowState.NORMAL);
            String message = null;
            ServiceContext serviceContext = ServiceContextFactory.getInstance(PartnerPortlet.class.getName(), actionRequest);
            Long partnerId = GetterUtil.getLong(actionRequest.getParameter("partnerId"), 0);
            String statusId = GetterUtil.getString(actionRequest.getParameter("status"), "");
            if(partnerId != 0 && StringUtils.isNotEmpty(statusId) && StringUtils.isNotBlank(statusId)){
                message = new PartnerUtil(actionRequest.getLocale()).changeStatus(statusId, partnerId, serviceContext.getCompanyId());
            }else{
                message = "partner.status.update.error";
            }

            if (message != null) {
                actionResponse.setRenderParameter("error", message);
            }else{
                actionResponse.setRenderParameter("info", "partner.save.success");
            }
        }catch (Exception e){
            log.error("PartnerPortlet.savePartner() error: " + Arrays.toString(e.getStackTrace()), e);
            actionRequest.setAttribute("error", "partner.save.error");
        }
    }

    //Edit Mode
    @Override
    public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        try{
            PartnerPreferences preferences = PartnerPreferencesUtil.getPreferences(renderRequest);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(PartnerPortlet.class.getName(), renderRequest);
            renderRequest.setAttribute("itemCount", preferences.getItemCount());
            renderRequest.setAttribute("defaultStatus", preferences.getDefaultStatus());
            renderRequest.setAttribute("orderLink", preferences.getOrderLink());
            renderRequest.setAttribute("layoutList", LayoutLocalServiceUtil.getLayouts(serviceContext.getScopeGroupId(), true));
            super.doEdit(renderRequest, renderResponse);
        }catch (Exception e){
            log.error("PartnerPortlet.doEdit() error: " + Arrays.toString(e.getStackTrace()), e);
        }
    }

    public void saveSettings(ActionRequest actionRequest, ActionResponse actionResponse){
        try{
            actionResponse.setWindowState(LiferayWindowState.NORMAL);
            int itemCount =  GetterUtil.getInteger(actionRequest.getParameter("itemCount"), 5);
            int defaultStatus =  GetterUtil.getInteger(actionRequest.getParameter("defaultStatus"), 0);
            int orderLink =  GetterUtil.getInteger(actionRequest.getParameter("orderLink"), 0);
            PartnerPreferences partnerPreferences = new PartnerPreferences();
            partnerPreferences.setItemCount(itemCount);
            partnerPreferences.setDefaultStatus(defaultStatus);
            partnerPreferences.setOrderLink(orderLink);
            PartnerPreferencesUtil.setPreferences(actionRequest, partnerPreferences);
            actionResponse.setRenderParameter("info", "global.data.is.saved.success");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            actionResponse.setRenderParameter("error", "global.data.is.wrong");
        }
    }

}
