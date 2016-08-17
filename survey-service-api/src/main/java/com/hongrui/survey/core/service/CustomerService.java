
package com.hongrui.survey.core.service;

import com.hongrui.survey.core.model.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    public int create(CustomerModel customerModel);

    public int createSelective(CustomerModel customerModel);

    public CustomerModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(CustomerModel customerModel);

    public int updateByPrimaryKeySelective(CustomerModel customerModel);

    public int deleteByPrimaryKey(Long id);

    public long selectCount(CustomerModel customerModel);

    public List<CustomerModel> selectPage(CustomerModel customerModel, Pageable pageable);

    public Page<CustomerModel> searchPage(CustomerModel customerModel, Pageable pageable);



}