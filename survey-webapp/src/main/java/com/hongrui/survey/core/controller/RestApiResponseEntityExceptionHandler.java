package com.hongrui.survey.core.controller;

import com.wlw.pylon.core.ErrorCode;
import com.wlw.pylon.core.exception.BeanValidationException;
import com.wlw.pylon.core.exception.BusinessException;
import com.wlw.pylon.core.exception.LocalizableBusinessException;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.exception.RestApiError;
import com.wlw.pylon.web.rest.exception.ValidationError;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class RestApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestApiResponseEntityExceptionHandler.class);
    /**
     * The spring message source used to localization the exception message
     */
    @Getter
    @Setter
    private MessageSource messageSource;

    @Getter
    @Setter
    private boolean logAsError = false;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleControllerException(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        if (ex instanceof BeanValidationException) {
            return handleBeanValidationException((BeanValidationException) ex, headers, status, request);
        }
        if (ex instanceof LocalizableBusinessException) {
            return handleLocalizableBusinessException((LocalizableBusinessException) ex, headers, status, request);
        }
        if (ex instanceof BusinessException) {
            return handleBusinessException((BusinessException) ex, headers, status, request);
        }

        if (ex instanceof HttpMessageNotReadableException) {
            return handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex, request);
        }

        if (ex instanceof MissingServletRequestParameterException) {
            return handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex);
        }
        return handleException(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleException(Exception ex, HttpHeaders headers,
                                                     HttpStatus status, WebRequest request) {

        doLog("The Exception is caught", ex);
        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode("-1");
        restApiError.setMessage("Oops, something wrong happened! Please check log for detail!");
        restApiError.setRawMessage(ex.getLocalizedMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return handleExceptionInternal(ex, envelope, headers, status, request);
    }

    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ex.getErrorCode());
        String localizedMsg = "";
        try {
            localizedMsg = messageSource.getMessage(ex.getMessage(), null,
                    request.getLocale());
        } catch (NoSuchMessageException nsme) {
            localizedMsg = ex.getMessage();
        }
        restApiError.setMessage(localizedMsg);
        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(
                restApiError, false);
        return handleExceptionInternal(ex, envelope, headers, status, request);
    }

    protected ResponseEntity<Object> handleLocalizableBusinessException(LocalizableBusinessException ex,
                                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ex.getErrorCode());
        //lang set through ?lang=* way
        //Accept_Language is also supported

        restApiError.setMessage(messageSource.getMessage(ex.getMessage(),
                ex.getArguments(), ex.getMessage(), LocaleContextHolder.getLocale()));

        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return handleExceptionInternal(ex, envelope, headers, status, request);
    }

    protected ResponseEntity<Object> handleBeanValidationException(BeanValidationException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ex.getErrorCode());
        // start to parse the validation error
        ValidationError validationError = new ValidationError();
        Errors errors = ex.getErrors();
        if (errors.hasGlobalErrors()) {
            Map<String, String> globalErrors = new HashMap<String, String>();
            List<ObjectError> objectErrorList = errors.getGlobalErrors();
            for (ObjectError objectError : objectErrorList) {
                globalErrors.put(objectError.getObjectName(), objectError.getDefaultMessage());
            }
            validationError.setGlobalErrors(globalErrors);
        }
        if (errors.hasFieldErrors()) {
            Map<String, String> fieldErrors = new HashMap<String, String>();
            List<FieldError> fieldErroList = errors.getFieldErrors();
            for (FieldError fieldError : fieldErroList) {
                fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            validationError.setFieldErrors(fieldErrors);
        }
        // put the validation error into RestApiError
        restApiError.setValidationError(validationError);

        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return handleExceptionInternal(ex, envelope, headers, status, request);
    }


    protected ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        doLog("The Exception is caught", ex);

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ErrorCode.FIELD_MUST.getCode());
        restApiError.setMessage(ex.getMessage());
        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return new ResponseEntity<Object>(envelope, HttpStatus.OK);
    }


    protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                           WebRequest webRequest) {

        logger.error(webRequest.getContextPath(), ex);

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ErrorCode.JSON_FORMAT_ERROR.getCode().toString());
        restApiError.setMessage(ErrorCode.JSON_FORMAT_ERROR.getErrorMsg());
        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return new ResponseEntity<Object>(envelope, HttpStatus.OK);
    }


    private void doLog(String msg, Throwable t) {
        if (logAsError) {
            logger.error(msg, t);
        } else {
            logger.info(msg, t);
        }
    }

}
