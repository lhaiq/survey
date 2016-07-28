package com.hongrui.survey.core.controller;

import com.google.common.cache.Cache;
import com.hongrui.survey.core.RandomUtil;
import com.hongrui.survey.core.vo.UserInfoVO;
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

	@PostMapping(value = "/user/login")
	public ResponseEnvelope<UserInfoVO> userLogin(@RequestBody UserVO userVO){
		UserModel userModel = beanMapper.map(userVO, UserModel.class);
		UserModel existedUser = userService.login(userModel);
		UserInfoVO userInfoVO = beanMapper.map(existedUser,UserInfoVO.class);
		String sessionId = RandomUtil.generateAuthToken();
		sessionCache.put(sessionId,existedUser.getId());
		userInfoVO.setSessionId(sessionId);
		ResponseEnvelope<UserInfoVO> responseEnv = new ResponseEnvelope<>(userInfoVO,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/user/logout")
	public ResponseEnvelope<String> userLogout(@RequestHeader("Authorization")String authorization){
		sessionCache.invalidate(authorization);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>("ok",true);
        return responseEnv;
	}


    @PutMapping(value = "/user/passrest")
	public ResponseEnvelope<Integer> passrest(@RequestAttribute Long id,
					@RequestBody UserVO userVO){
		UserModel userModel = beanMapper.map(userVO, UserModel.class);
		userModel.setId(id);
		Integer  result = userService.updateByPrimaryKeySelective(userModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

}
