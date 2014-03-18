package com.fidoarp;

public enum UserStatus {

    ACTIVE(0), BLOCKED(1), DISABLED(2);

    public final int status;

    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
