
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.UserModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public int create(UserModel userModel);

    public int createSelective(UserModel userModel);

    public UserModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(UserModel userModel);

    public int updateByPrimaryKeySelective(UserModel userModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(UserModel userModel);

    public List<UserModel> selectPage(UserModel userModel, Pageable pageable);

    public UserModel login(UserModel userModel);

}