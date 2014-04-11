package com.fidoarp.portlet;

import com.fidoarp.catalog.model.AppStatus;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import org.apache.commons.lang.StringUtils;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AppStatusPortlet extends FidoMVCPortlet {

    private static final Log log = LogFactoryUtil.getLog(AppStatusPortlet.class);

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        try {
            List<AppStatus> appStatusList = AppStatusLocalServiceUtil.getAppStatuses(-1, -1);
            renderRequest.setAttribute("appStatuses", appStatusList);
            String action = GetterUtil.getString(renderRequest.getParameter("action"), "");
            if(StringUtils.isNotEmpty(action) && StringUtils.isNotBlank(action)){
               if(action.equals("edit")){
                  if(editView(renderRequest, renderResponse)) return;
               }
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        super.doView(renderRequest, renderResponse);
    }

    private boolean editView(RenderRequest renderRequest, RenderResponse renderResponse) throws SystemException, com.liferay.portal.kernel.exception.PortalException, PortletException, IOException {
        Long appStatusId = GetterUtil.getLong(renderRequest.getParameter("appStatusId"), 0);

        AppStatus appStatus;
        if(appStatusId == 0){
            appStatus = AppStatusLocalServiceUtil.createAppStatus(CounterLocalServiceUtil.increment());
            appStatus.setAppStatusId(0);
        }else{
            appStatus = AppStatusLocalServiceUtil.getAppStatus(appStatusId);
        }

        renderRequest.setAttribute("appStatus", appStatus);
        getPortletContext().getRequestDispatcher("/html/jsp/portlet/app-status/view/edit-app-status.jsp").include(renderRequest, renderResponse);
        return true;
    }

    public void saveAppStatus(ActionRequest actionRequest, ActionResponse actionResponse){
        try {
            String appStatusCode = GetterUtil.getString(actionRequest.getParameter("appStatusCode"), "");
            Map<Locale, String> appStatusNames = LocalizationUtil.getLocalizationMap(actionRequest, "appStatusName");
            Map<Locale, String> appStatusDescriptions = LocalizationUtil.getLocalizationMap(actionRequest, "appStatusDescription");
            Long appStatusId = GetterUtil.getLong(actionRequest.getParameter("appStatusId"), 0);

            actionResponse.setWindowState(LiferayWindowState.NORMAL);

            if(StringUtils.isNotBlank(appStatusCode) && StringUtils.isNotEmpty(appStatusCode)
                && appStatusNames.size() > 0){
                AppStatus appStatus = appStatusId == 0
                        ? AppStatusLocalServiceUtil.createAppStatus(CounterLocalServiceUtil.increment())
                        : AppStatusLocalServiceUtil.getAppStatus(appStatusId);

                AppStatus appStatusByCode = AppStatusLocalServiceUtil.getAppStatusByCode(appStatusCode);
                if(appStatusByCode != null && appStatusId != 0 &&
                   appStatusByCode.getAppStatusId() != appStatus.getAppStatusId()){
                    actionRequest.setAttribute("error", "app.status.code.is.not.unique");
                    actionRequest.setAttribute("action", "edit");
                    return;
                }
                appStatus.setAppStatusCode(appStatusCode);
                appStatus.setNameMap(appStatusNames);
                appStatus.setDescriptionMap(appStatusDescriptions);
                if(appStatusId == 0){
                    AppStatusLocalServiceUtil.addAppStatus(appStatus);
                } else {
                    AppStatusLocalServiceUtil.updateAppStatus(appStatus);
                }
                actionRequest.setAttribute("info", "app.status.data.is.saved.success");
            }else{
                actionRequest.setAttribute("error", "app.status.data.is.wrong");
                actionRequest.setAttribute("action", "edit");
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
