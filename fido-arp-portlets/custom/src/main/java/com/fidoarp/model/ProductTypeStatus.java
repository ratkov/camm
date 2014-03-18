package com.fidoarp.model;

public enum ProductTypeStatus {
    ACTIVE("partner.product.type.active"),
    INACTIVE("partner.product.type.inactive");

    private String label;

    ProductTypeStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
