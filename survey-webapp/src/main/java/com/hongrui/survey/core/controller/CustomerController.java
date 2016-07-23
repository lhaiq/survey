package com.hongrui.survey.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.CustomerService;
import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.vo.CustomerVO;

import java.util.List;

@Controller
@RequestMapping("/survey")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/addCustomerUI")
    public String addCustomerUI() {
        return "customer/add_customer";
    }

    @GetMapping(value = "/surveyCustomerUI/{id}")
    public String surveyCustomerUI(@PathVariable Long id) {
        return "task/add_task";
    }

    @GetMapping(value = "/customer")
    public String listCustomer(CustomerVO customerVO, Pageable pageable, Model model) {

        CustomerModel param = beanMapper.map(customerVO, CustomerModel.class);
        List<CustomerModel> customerModelModels = customerService.selectPage(param, pageable);
        long count = customerService.selectCount(param);
        Page<CustomerModel> page = new PageImpl<>(customerModelModels, pageable, count);
        model.addAttribute("data", page);
        return "customer/customer_list";
    }

    @PostMapping(value = "/core/customer")
    public ResponseEnvelope<Integer> createCustomer(@RequestBody CustomerVO customerVO) {
        CustomerModel customerModel = beanMapper.map(customerVO, CustomerModel.class);
        Integer result = customerService.create(customerModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/core/customer/{id}")
    public ResponseEnvelope<Integer> deleteCustomerByPrimaryKey(@PathVariable Long id) {
        Integer result = customerService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/core/customer/{id}")
    public ResponseEnvelope<Integer> updateCustomerByPrimaryKeySelective(@PathVariable Long id,
                                                                         @RequestBody CustomerVO customerVO) {
        CustomerModel customerModel = beanMapper.map(customerVO, CustomerModel.class);
        customerModel.setId(id);
        Integer result = customerService.updateByPrimaryKeySelective(customerModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

}