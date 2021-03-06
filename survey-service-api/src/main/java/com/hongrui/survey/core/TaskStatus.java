package com.hongrui.survey.core;

/**
 * Created by haiquanli on 16/7/29.
 */
public enum TaskStatus {

    CREATED(0),
    STARTED(1),
    COMMIT(2),
    CHECKING(3),
    SUCCESS(4),
    FAILURE(5),
    DISCARD(6),;

    private int code;

    private TaskStatus(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
