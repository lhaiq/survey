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

    @GetMapping(value = "/core/sign/{id}")
    public ResponseEnvelope<SignVO> getSignById(@PathVariable Long id) {
        SignModel signModel = signService.findByPrimaryKey(id);
        SignVO signVO = beanMapper.map(signModel, SignVO.class);
        ResponseEnvelope<SignVO> responseEnv = new ResponseEnvelope<>(signVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/core/sign")
    public ResponseEnvelope<Page<SignModel>> listSign(SignVO signVO, Pageable pageable) {

        SignModel param = beanMapper.map(signVO, SignModel.class);
        List<SignModel> signModelModels = signService.selectPage(param, pageable);
        long count = signService.selectCount(param);
        Page<SignModel> page = new PageImpl<>(signModelModels, pageable, count);
        ResponseEnvelope<Page<SignModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

    @PostMapping(value = "/core/sign")
    public ResponseEnvelope<Integer> createSign(@RequestBody SignVO signVO) {
        SignModel signModel = beanMapper.map(signVO, SignModel.class);
        Integer result = signService.create(signModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/core/sign/{id}")
    public ResponseEnvelope<Integer> deleteSignByPrimaryKey(@PathVariable Long id) {
        Integer result = signService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/core/sign/{id}")
    public ResponseEnvelope<Integer> updateSignByPrimaryKeySelective(@PathVariable Long id,
                                                                     @RequestBody SignVO signVO) {
        SignModel signModel = beanMapper.map(signVO, SignModel.class);
        signModel.setId(id);
        Integer result = signService.updateByPrimaryKeySelective(signModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

}
