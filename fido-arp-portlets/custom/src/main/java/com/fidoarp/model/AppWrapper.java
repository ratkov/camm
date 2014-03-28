package com.fidoarp.model;

import com.fidoarp.catalog.model.App;
import com.fidoarp.catalog.service.AppStatusLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class AppWrapper {
    private static final Log log = LogFactoryUtil.getLog(AppWrapper.class);

    private long _appId;
    private String _user;
    private long _organizationId;
    private Date _createdDate;
    private String _description;
    private String _status;
    private Date _statusChangeDate;
    private String _clientName;
    private String _clientOkpo;
    private String _contactPhone;
    private int _creditAmount;
    private String _comments;
    private String _questionnaire;

    public AppWrapper(App app, Locale locale) {
        try{
            this._appId = app.getAppId();
            this._user = UserLocalServiceUtil.getUser(app.getUserId()).getScreenName();
            this._organizationId = app.getOrganizationId();
            this._createdDate = app.getCreatedDate();
            this._description = app.getDescription(locale);
            this._status = AppStatusLocalServiceUtil.getAppStatus(app.getStatusId()).getName(locale);
            this._statusChangeDate = app.getStatusChangeDate();
            this._clientName = app.getClientName();
            this._clientOkpo = app.getClientOkpo();
            this._contactPhone = app.getContactPhone();
            this._creditAmount = app.getCreditAmount();
            this._comments = app.getComments(locale);
            this._questionnaire = app.getQuestionnaire();
        }catch (Exception e){
            log.error("AppWrapper error:" + Arrays.toString(e.getStackTrace()), e);
        }
    }

    public long getAppId() {
        return _appId;
    }

    public String getUser() {
        return _user;
    }

    public long getOrganizationId() {
        return _organizationId;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public String getDescription() {
        return _description;
    }

    public String getStatus() {
        return _status;
    }

    public Date getStatusChangeDate() {
        return _statusChangeDate;
    }

    public String getClientName() {
        return _clientName;
    }

    public String getClientOkpo() {
        return _clientOkpo;
    }

    public String getContactPhone() {
        return _contactPhone;
    }

    public int getCreditAmount() {
        return _creditAmount;
    }

    public String getComments() {
        return _comments;
    }

    public String getQuestionnaire() {
        return _questionnaire;
    }
}
