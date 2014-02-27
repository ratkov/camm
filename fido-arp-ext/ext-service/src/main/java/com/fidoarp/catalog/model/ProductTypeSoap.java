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
public class ProductTypeSoap implements Serializable {
    private long _productTypeId;
    private String _productTypeCode;
    private String _name;
    private String _description;
    private long _organizationId;
    private long _ddmtemplateId;

    public ProductTypeSoap() {
    }

    public static ProductTypeSoap toSoapModel(ProductType model) {
        ProductTypeSoap soapModel = new ProductTypeSoap();

        soapModel.setProductTypeId(model.getProductTypeId());
        soapModel.setProductTypeCode(model.getProductTypeCode());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setOrganizationId(model.getOrganizationId());
        soapModel.setDdmtemplateId(model.getDdmtemplateId());

        return soapModel;
    }

    public static ProductTypeSoap[] toSoapModels(ProductType[] models) {
        ProductTypeSoap[] soapModels = new ProductTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProductTypeSoap[][] toSoapModels(ProductType[][] models) {
        ProductTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProductTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new ProductTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProductTypeSoap[] toSoapModels(List<ProductType> models) {
        List<ProductTypeSoap> soapModels = new ArrayList<ProductTypeSoap>(models.size());

        for (ProductType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProductTypeSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _productTypeId;
    }

    public void setPrimaryKey(long pk) {
        setProductTypeId(pk);
    }

    public long getProductTypeId() {
        return _productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
        _productTypeId = productTypeId;
    }

    public String getProductTypeCode() {
        return _productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        _productTypeCode = productTypeCode;
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

    public long getOrganizationId() {
        return _organizationId;
    }

    public void setOrganizationId(long organizationId) {
        _organizationId = organizationId;
    }

    public long getDdmtemplateId() {
        return _ddmtemplateId;
    }

    public void setDdmtemplateId(long ddmtemplateId) {
        _ddmtemplateId = ddmtemplateId;
    }
}
