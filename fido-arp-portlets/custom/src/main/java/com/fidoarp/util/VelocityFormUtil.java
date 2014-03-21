package com.fidoarp.util;

import com.fidoarp.model.questionnaire.Field;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.velocity.VelocityContext;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.service.ServiceContext;
import org.apache.commons.lang.StringUtils;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class VelocityFormUtil {

    private static final Log log = LogFactoryUtil.getLog(VelocityFormUtil.class);

    public StringWriter getVelocityForm(PortletRequest portletRequest, Long templateId, ServiceContext serviceContext, String jsonObjectStr){
        try {
            StringWriter clientWriter = new StringWriter();
            String velocityClientTemplateId = "/vm/form-template.vm";

            VelocityEngineUtil.flushTemplate(velocityClientTemplateId);
            String velocityTemplateContent = StringUtil.read(getClass().getClassLoader(), velocityClientTemplateId);


            String languageId = GetterUtil.getString(
                    serviceContext.getAttribute("languageId"),
                    serviceContext.getLanguageId());

            String defaultLanguageId = GetterUtil.getString(serviceContext.getAttribute("defaultLanguageId"));

            //for Field
            StringWriter fieldWriter = new StringWriter();
            String velocityFieldTemplateId = "/vm/field.vm";
            String velocityFieldTemplateContent = StringUtil.read(getClass().getClassLoader(), velocityFieldTemplateId);
            VelocityContext velocityFieldContext = VelocityEngineUtil.getEmptyContext();
            VelocityEngineUtil.mergeTemplate(velocityFieldTemplateId, velocityFieldTemplateContent, velocityFieldContext, fieldWriter);

            //for Field
            StringWriter fieldHiddenWriter = new StringWriter();
            String velocityHiddenFieldTemplateId = "/vm/hidden-field.vm";
            String velocityHiddenFieldTemplateContent = StringUtil.read(getClass().getClassLoader(), velocityHiddenFieldTemplateId);
            VelocityContext velocityHiddenFieldContext = VelocityEngineUtil.getEmptyContext();
            VelocityEngineUtil.mergeTemplate(velocityHiddenFieldTemplateId, velocityHiddenFieldTemplateContent, velocityHiddenFieldContext, fieldHiddenWriter);

            //because questionnaires must be in ukrainian language
            // languageId will be uk_UA
            languageId = "uk_UA";
            Locale locale = LocaleUtil.fromLanguageId(languageId);
            FieldsUtil fieldsUtil = new FieldsUtil();
            List<Field> fields = fieldsUtil.getFields(templateId, languageId, defaultLanguageId, jsonObjectStr, portletRequest);

//            fieldsUtil.hideSteps(fields, templateId, jsonObjectStr);

            //creating template
            VelocityContext velocityContext = VelocityEngineUtil.getEmptyContext();
            velocityContext.put("fields", fields);
            velocityContext.put("locale", languageId);
            velocityContext.put("fieldWriter", fieldWriter);
            velocityContext.put("fieldHiddenWriter", fieldHiddenWriter);
            velocityContext.put("esc", Class.forName("org.apache.commons.lang.StringEscapeUtils"));
            ResourceBundle res = ResourceBundle.getBundle("i18n.Resource", locale);
            velocityContext.put("continue", res.getString("continue.step"));
            VelocityEngineUtil.mergeTemplate(velocityClientTemplateId, velocityTemplateContent, velocityContext, clientWriter);


            return clientWriter;
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
