package com.fidoarp.model;

public enum BankDepartmentType {
    DIVISION("bank.department.division"),
    ATM("bank.department.atm");

    private String label;

    BankDepartmentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
