package com.fidoarp.portlet;

import com.fidoarp.model.PartnerWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ArrayList;
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


            List<Organization> partners = OrganizationLocalServiceUtil.getOrganizations(-1, -1);

            List<PartnerWrapper> organizationWrappers = new ArrayList<PartnerWrapper>();
            for(Organization organization : partners){
                organizationWrappers.add(new PartnerWrapper(organization));
            }

            renderRequest.setAttribute("partners", organizationWrappers);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }

}
