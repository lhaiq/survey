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

    @GetMapping(value = "/editCustomerUI/{id}")
    public String editCustomerUI(@PathVariable Long id, Model model) {
        model.addAttribute("data", customerService.findByPrimaryKey(id));
        return "customer/edit_customer";
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


}
