package com.hongrui.survey.core.controller;

import com.google.common.cache.Cache;
import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.RandomUtil;
import com.hongrui.survey.core.UserRole;
import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.service.UserService;
import com.hongrui.survey.core.vo.UpdatePassVO;
import com.hongrui.survey.core.vo.UserInfoVO;
import com.hongrui.survey.core.vo.UserVO;
import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.core.exception.BusinessException;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/survey/core")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private Cache<String, Long> sessionCache;

    @PostMapping(value = "/user/login")
    public String userLogin(UserVO userVO, HttpSession session, Model model) {

        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        UserModel existedUser = null;
        try {
            existedUser = userService.login(userModel);
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }

        session.setAttribute("user",existedUser);
        return "redirect:/index";
    }

}
