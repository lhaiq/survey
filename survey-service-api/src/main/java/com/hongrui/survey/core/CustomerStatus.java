package com.hongrui.survey.core;

/**
 * Created by haiquanli on 16/7/29.
 */
public enum CustomerStatus {

    UNALLOT(0),
    ALLOT(1),
    ;

    private int code;

    private CustomerStatus(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
