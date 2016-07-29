package com.hongrui.survey.core;

/**
 * Created by haiquanli on 16/7/29.
 */
public enum ConfType {

    SURVEY(0),
    PHOTO(1),
    TEMPLATE(2);

    private int type;

    private ConfType(int type) {
        this.type = type;
    }


    public int getType() {
        return type;
    }
}
