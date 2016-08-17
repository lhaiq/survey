package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.service.CustomerService;
import com.hongrui.survey.core.vo.CustomerVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

    @GetMapping(value = "/customer/validate")
    public void validateName(@RequestParam(required = false) String idCard, HttpServletResponse response) {
        CustomerModel param = new CustomerModel();
        param.setIdCard(idCard);
        long count = customerService.selectCount(param);
        try {
            response.getWriter().write(String.valueOf(count > 0 ? true : false));
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/customer")
    public String listCustomer(CustomerVO customerVO, Pageable pageable, Model model) {
        try {
            if(StringUtils.isNoneEmpty(customerVO.getName())){
                String name = new String(customerVO.getName().getBytes("ISO-8859-1"), "utf-8");
                customerVO.setName(name);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CustomerModel param = beanMapper.map(customerVO, CustomerModel.class);
        Page<CustomerModel> page = customerService.searchPage(param, pageable);
        model.addAttribute("data", page);
        return "customer/customer_list";
    }


}
