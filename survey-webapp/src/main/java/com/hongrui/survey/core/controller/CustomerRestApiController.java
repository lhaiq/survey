package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.service.CustomerService;
import com.hongrui.survey.core.vo.CustomerVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class CustomerRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CustomerRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/customer")
    public ResponseEnvelope<Integer> createCustomer(@SessionAttribute("user") UserModel user,
                                                    CustomerVO customerVO) {
        CustomerModel customerModel = beanMapper.map(customerVO, CustomerModel.class);
        customerModel.setSyndicId(user.getId());
        Integer result = customerService.createSelective(customerModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEnvelope<Integer> deleteCustomer(@PathVariable Long id) {
        Integer result = customerService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/customer/{id}")
    public ResponseEnvelope<Integer> updateCustomer(@PathVariable Long id,
                                                    CustomerVO customerVO) {
        CustomerModel customerModel = beanMapper.map(customerVO, CustomerModel.class);
        customerModel.setId(id);
        Integer result = customerService.updateByPrimaryKeySelective(customerModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

}
