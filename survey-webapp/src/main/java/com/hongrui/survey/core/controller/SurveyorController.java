package com.hongrui.survey.core.controller;

import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.service.UserService;
import com.hongrui.survey.core.vo.UserVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by haiquanli on 16/7/18.
 */
@Controller
@RequestMapping(value = "/survey")
public class SurveyorController {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/surveyor_ui")
    public String index() {
        return "surveyor/add_surveyor";
    }

    @GetMapping(value = "/surveyor")
    public String listSurvey(UserVO userVO, Pageable pageable, Model model) {

        UserModel param = beanMapper.map(userVO, UserModel.class);
        List<UserModel> userModelModels = userService.selectPage(param, pageable);
        long count = userService.selectCount(param);
        Page<UserModel> page = new PageImpl<>(userModelModels, pageable, count);
        model.addAttribute("data", page);
        return "surveyor/surveyor_list";
    }

}
