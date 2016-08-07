package com.hongrui.survey.core.service.impl;

import com.hongrui.survey.core.HRErrorCode;
import com.hongrui.survey.core.model.CustomerModel;
import com.wlw.pylon.core.ErrorCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.User;
import com.hongrui.survey.core.repository.UserRepository;
import com.hongrui.survey.core.model.UserModel;
import com.hongrui.survey.core.service.UserService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(UserModel userModel) {
        return userRepo.insert(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int createSelective(UserModel userModel) {
        return userRepo.insertSelective(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return userRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel findByPrimaryKey(Long id) {
        User user = userRepo.selectByPrimaryKey(id);
        return beanMapper.map(user, UserModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(UserModel userModel) {
        return userRepo.selectCount(beanMapper.map(userModel, User.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserModel> selectPage(UserModel userModel, Pageable pageable) {
        User user = beanMapper.map(userModel, User.class);
        return beanMapper.mapAsList(userRepo.selectPage(user, pageable), UserModel.class);
    }

    @Override
    public UserModel login(UserModel userModel) {
        String sql = "select * from user where account = ?";
        UserModel existedUser = findUserByAccount(userModel.getAccount());

        if (null == existedUser) {
            HRErrorCode.throwBusinessException(HRErrorCode.USER_NOT_EXISTED);
        }

        if (!DigestUtils.md5Hex(userModel.getPassword()).equals(existedUser.getPassword())) {
            HRErrorCode.throwBusinessException(HRErrorCode.PASSWORD_INCORRECT);
        }
        return existedUser;
    }

    @Transactional
    @Override
    public void addUser(UserModel userModel) {

        UserModel existedUser = findUserByAccount(userModel.getAccount());
        if (null != existedUser) {
            HRErrorCode.throwBusinessException(HRErrorCode.USER_HAVE_EXISTED);
        }

        userModel.setCreateTime(new Date());
        userModel.setPassword(DigestUtils.md5Hex(userModel.getPassword()));
        createSelective(userModel);
    }

    @Transactional
    @Override
    public void updateUser(UserModel userModel) {
        userModel.setPassword(DigestUtils.md5Hex(userModel.getPassword()));
        updateByPrimaryKeySelective(userModel);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(UserModel userModel) {
        return userRepo.updateByPrimaryKey(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(UserModel userModel) {
        return userRepo.updateByPrimaryKeySelective(beanMapper.map(userModel, User.class));
    }


    private UserModel findUserByAccount(String account) {
        String sql = "select * from user where account = ?";
        UserModel userModel = null;

        try {
            userModel = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class),
                    account);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return userModel;
    }

    public Page<UserModel> searchPage(UserModel userModel, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        String account = userModel.getAccount();
        Integer role = userModel.getRole();
        sql.append("select * from user ");
        if (StringUtils.isNotEmpty(account)) {
            sql.append(" where role = ? ");
            sql.append(" and  account like '%" + account + "%'");
        } else {
            sql.append(" where role = ? ");
        }


        sql.append(" limit ? ,? ");

        StringBuffer countSql = new StringBuffer();
        countSql.append("select count(1) from user ");
        if (StringUtils.isNotEmpty(account)) {
            countSql.append(" where role = ? ");
            countSql.append(" and  account like '%" + account + "%'");
        } else {
            countSql.append(" where role = ? ");
        }


        List<UserModel> content = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(UserModel.class), role,
                pageable.getOffset(), pageable.getPageSize());
        long count = jdbcTemplate.queryForObject(countSql.toString(), Long.class, role);
        Page<UserModel> page = new PageImpl<>(content, pageable, count);

        return page;
    }
}
