package com.fidoarp.catalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class AppStatusSoap implements Serializable {
    private long _appStatusId;
    private String _appStatusCode;
    private String _name;
    private String _description;

    public AppStatusSoap() {
    }

    public static AppStatusSoap toSoapModel(AppStatus model) {
        AppStatusSoap soapModel = new AppStatusSoap();

        soapModel.setAppStatusId(model.getAppStatusId());
        soapModel.setAppStatusCode(model.getAppStatusCode());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static AppStatusSoap[] toSoapModels(AppStatus[] models) {
        AppStatusSoap[] soapModels = new AppStatusSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AppStatusSoap[][] toSoapModels(AppStatus[][] models) {
        AppStatusSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AppStatusSoap[models.length][models[0].length];
        } else {
            soapModels = new AppStatusSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AppStatusSoap[] toSoapModels(List<AppStatus> models) {
        List<AppStatusSoap> soapModels = new ArrayList<AppStatusSoap>(models.size());

        for (AppStatus model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AppStatusSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _appStatusId;
    }

    public void setPrimaryKey(long pk) {
        setAppStatusId(pk);
    }

    public long getAppStatusId() {
        return _appStatusId;
    }

    public void setAppStatusId(long appStatusId) {
        _appStatusId = appStatusId;
    }

    public String getAppStatusCode() {
        return _appStatusCode;
    }

    public void setAppStatusCode(String appStatusCode) {
        _appStatusCode = appStatusCode;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
