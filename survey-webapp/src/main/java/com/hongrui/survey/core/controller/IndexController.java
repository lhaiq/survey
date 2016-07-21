package com.hongrui.survey.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by haiquanli on 16/7/18.
 */
@Controller
public class IndexController {


    @GetMapping(value = "index")
    public String index(){
        return "common/index";
    }
}
