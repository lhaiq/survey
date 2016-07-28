package com.hongrui.survey.core;

import com.wlw.pylon.core.ErrorCode;

/**
 * Created by haiquanli on 16/7/28.
 */
public class HRErrorCode extends ErrorCode{

    public static final ErrorCode USER_NOT_EXISTED = ErrorCode("20000", "用户不存在");
    public static final ErrorCode PASSWORD_INCORRECT = ErrorCode("20001", "密码不正确");

    protected HRErrorCode(String code, String errorMsg) {
        super(code, errorMsg);
    }
}
