package com.fidoarp.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class AppSoap implements Serializable {
    private long _appId;
    private long _userId;
    private long _organizationId;
    private Date _createdDate;
    private String _description;
    private long _statusId;
    private long _productTypeId;
    private Date _statusChangeDate;
    private String _clientName;
    private String _clientOkpo;
    private String _contactPhone;
    private Double _creditAmount;
    private String _comments;
    private String _questionnaire;

    public AppSoap() {
    }

    public static AppSoap toSoapModel(App model) {
        AppSoap soapModel = new AppSoap();

        soapModel.setAppId(model.getAppId());
        soapModel.setUserId(model.getUserId());
        soapModel.setOrganizationId(model.getOrganizationId());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setDescription(model.getDescription());
        soapModel.setStatusId(model.getStatusId());
        soapModel.setProductTypeId(model.getProductTypeId());
        soapModel.setStatusChangeDate(model.getStatusChangeDate());
        soapModel.setClientName(model.getClientName());
        soapModel.setClientOkpo(model.getClientOkpo());
        soapModel.setContactPhone(model.getContactPhone());
        soapModel.setCreditAmount(model.getCreditAmount());
        soapModel.setComments(model.getComments());
        soapModel.setQuestionnaire(model.getQuestionnaire());

        return soapModel;
    }

    public static AppSoap[] toSoapModels(App[] models) {
        AppSoap[] soapModels = new AppSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AppSoap[][] toSoapModels(App[][] models) {
        AppSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AppSoap[models.length][models[0].length];
        } else {
            soapModels = new AppSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AppSoap[] toSoapModels(List<App> models) {
        List<AppSoap> soapModels = new ArrayList<AppSoap>(models.size());

        for (App model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AppSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _appId;
    }

    public void setPrimaryKey(long pk) {
        setAppId(pk);
    }

    public long getAppId() {
        return _appId;
    }

    public void setAppId(long appId) {
        _appId = appId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getOrganizationId() {
        return _organizationId;
    }

    public void setOrganizationId(long organizationId) {
        _organizationId = organizationId;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public long getStatusId() {
        return _statusId;
    }

    public void setStatusId(long statusId) {
        _statusId = statusId;
    }

    public long getProductTypeId() {
        return _productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
        _productTypeId = productTypeId;
    }

    public Date getStatusChangeDate() {
        return _statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        _statusChangeDate = statusChangeDate;
    }

    public String getClientName() {
        return _clientName;
    }

    public void setClientName(String clientName) {
        _clientName = clientName;
    }

    public String getClientOkpo() {
        return _clientOkpo;
    }

    public void setClientOkpo(String clientOkpo) {
        _clientOkpo = clientOkpo;
    }

    public String getContactPhone() {
        return _contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        _contactPhone = contactPhone;
    }

    public Double getCreditAmount() {
        return _creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        _creditAmount = creditAmount;
    }

    public String getComments() {
        return _comments;
    }

    public void setComments(String comments) {
        _comments = comments;
    }

    public String getQuestionnaire() {
        return _questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        _questionnaire = questionnaire;
    }
}
