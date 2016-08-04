package com.hongrui.survey.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by haiquanli on 16/7/18.
 */
@Controller
public class IndexController {


    @GetMapping(value = "index")
    public String index(Model model){
        return "common/index";
    }

    @GetMapping(value = "login")
    public String login(Model model){
        model.addAttribute("admin",false);
        return "login";
    }

    @GetMapping(value = "/survey/updatePasswordUI")
    public String updatePassUI(Model model){
        return "update_password";
    }
}
