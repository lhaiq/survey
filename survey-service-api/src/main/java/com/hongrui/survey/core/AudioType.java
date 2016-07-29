package com.hongrui.survey.core;

/**
 * Created by haiquanli on 16/7/29.
 */
public enum AudioType {

    TEMPORARY(0),
    PERSISTENT(1);

    private int type;

    private AudioType(int type) {
        this.type = type;
    }


    public int getType() {
        return type;
    }
}
