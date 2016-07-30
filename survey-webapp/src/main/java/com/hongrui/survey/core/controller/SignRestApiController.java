package com.hongrui.survey.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.SignService;
import com.hongrui.survey.core.model.SignModel;
import com.hongrui.survey.core.vo.SignVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SignRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SignRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SignService signService;


    @PostMapping(value = "/{taskId}/sign")
    public ResponseEnvelope<String> createSign(@PathVariable Long taskId,
                                                @RequestBody SignVO signVO) {
        SignModel signModel = beanMapper.map(signVO, SignModel.class);
        signModel.setTaskId(taskId);
        signService.createSign(signModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


}
