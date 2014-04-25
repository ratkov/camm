package com.fidoarp.model;

public enum PartnerStatus {

    ACTIVE(0), BLOCKED(1), DISABLED(2);

    public final int status;

    PartnerStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
