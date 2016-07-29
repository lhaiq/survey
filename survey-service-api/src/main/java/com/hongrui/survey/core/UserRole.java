package com.hongrui.survey.core;

/**
 * Created by haiquanli on 16/7/29.
 */
public enum UserRole {

    SURVEYOR(0),
    PERSISTENT(1);

    private int code;

    private UserRole(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
