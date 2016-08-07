package com.hongrui.survey.core.controller;

import com.google.common.cache.Cache;
import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.RandomUtil;
import com.hongrui.survey.core.UserRole;
import com.hongrui.survey.core.annotation.IgnoreAuth;
import com.hongrui.survey.core.vo.UpdatePassVO;
import com.hongrui.survey.core.vo.UserInfoVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.hongrui.survey.core.service.UserService;
import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.vo.UserVO;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class UserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private Cache<String, Long> sessionCache;

    @IgnoreAuth
    @PostMapping(value = "/user/login")
    public ResponseEnvelope<UserInfoVO> userLogin(@RequestBody UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setRole(UserRole.SURVEYOR.getCode());
        UserModel existedUser = userService.login(userModel);
        if (existedUser.getRole() != UserRole.SURVEYOR.getCode()) {
            HRErrorCode.throwBusinessException(HRErrorCode.ROLE_INVALID);
        }
        UserInfoVO userInfoVO = beanMapper.map(existedUser, UserInfoVO.class);
        String sessionId = RandomUtil.generateAuthToken();
        sessionCache.put(sessionId, existedUser.getId());
        userInfoVO.setSessionId(sessionId);
        ResponseEnvelope<UserInfoVO> responseEnv = new ResponseEnvelope<>(userInfoVO, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/user/logout")
    public ResponseEnvelope<String> userLogout(@RequestHeader("Authorization") String authorization) {
        sessionCache.invalidate(authorization);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @PutMapping(value = "/user/passrest")
    public ResponseEnvelope<Integer> passrest(@RequestAttribute Long userId,
                                              @RequestBody UpdatePassVO updatePassVO) {
        UserModel userModel = userService.findByPrimaryKey(userId);
        if (!DigestUtils.md5Hex(updatePassVO.getOldPassword()).equals(userModel.getPassword())) {
            HRErrorCode.throwBusinessException(HRErrorCode.OLD_PASSWORD_INCORRECT);
        }

        UserModel param = new UserModel();
        param.setId(userId);
        param.setPassword(DigestUtils.md5Hex(updatePassVO.getNewPassword()));
        Integer result = userService.updateByPrimaryKeySelective(param);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @PostMapping(value = "/user/updatePass/{id}")
    public ResponseEnvelope<Integer> updatePass(@PathVariable Long id,
                                              @RequestBody UpdatePassVO updatePassVO) {
        UserModel userModel = userService.findByPrimaryKey(id);
        if (!DigestUtils.md5Hex(updatePassVO.getOldPassword()).equals(userModel.getPassword())) {
            HRErrorCode.throwBusinessException(HRErrorCode.OLD_PASSWORD_INCORRECT);
        }

        UserModel param = new UserModel();
        param.setId(id);
        param.setPassword(DigestUtils.md5Hex(updatePassVO.getNewPassword()));
        Integer result = userService.updateByPrimaryKeySelective(param);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEnvelope<String> deleteUser(@PathVariable Long id) {
        userService.deleteByPrimaryKey(id);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEnvelope<UserModel> getUser(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKey(id);
        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel, true);
        return responseEnv;
    }

    @GetMapping(value = "/user")
    public ResponseEnvelope<Page<UserModel>> listSurvey(UserVO userVO, Pageable pageable) {

        UserModel param = beanMapper.map(userVO, UserModel.class);
        List<UserModel> userModelModels = userService.selectPage(param, pageable);
        long count = userService.selectCount(param);
        Page<UserModel> page = new PageImpl<>(userModelModels, pageable, count);
        ResponseEnvelope<Page<UserModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }


    @PostMapping(value = "/user")
    public ResponseEnvelope<String> createUser(UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userService.addUser(userModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEnvelope<String> updateUser(@PathVariable Long id,
                                               UserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(id);
        userService.updateUser(userModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok", true);
        return responseEnv;
    }

}
