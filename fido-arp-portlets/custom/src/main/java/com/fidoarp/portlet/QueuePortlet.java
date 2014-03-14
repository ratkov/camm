package com.fidoarp.portlet;

import com.fidoarp.util.VelocityFormUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class QueuePortlet extends FidoMVCPortlet  {
    /**
     * The Constant LOG.
     */
    private static final Log log = LogFactoryUtil.getLog(QueuePortlet.class);

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            if (!renderRequest.getPortletMode().equals(PortletMode.VIEW)){
                super.render(renderRequest, renderResponse);
            }
            ServiceContext serviceContext = ServiceContextFactory.getInstance(QueuePortlet.class.getName(), renderRequest);

            List<DDMTemplate> ddmTemplates = DDMTemplateLocalServiceUtil.getDDMTemplates(-1, -1);

            renderRequest.setAttribute("ddmTemplates", ddmTemplates);

            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");

            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
                if(action.equals("createNewQuery")){
                    Long templateId = GetterUtil.getLong(renderRequest.getParameter("selectedTemplate"), 0);
                    VelocityFormUtil velocityFormUtil = new VelocityFormUtil();
                    StringWriter stringWriter = velocityFormUtil.getVelocityForm(renderRequest, templateId, serviceContext, "{}");
                    renderRequest.setAttribute("templateHtml", stringWriter);
                    renderRequest.setAttribute("show", "query-form.jsp");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }

    public void saveQuery(ActionRequest actionRequest, ActionResponse actionResponse){
        try{

        }catch (Exception e){
           log.error(e.getMessage(), e);
        }
    }
}
