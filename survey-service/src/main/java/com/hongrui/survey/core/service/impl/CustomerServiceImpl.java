package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Customer;
import com.hongrui.survey.core.repository.CustomerRepository;
import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.service.CustomerService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private CustomerRepository customerRepo;

	@Transactional
	@Override
	public int create(CustomerModel customerModel) {
		return customerRepo.insert(beanMapper.map(customerModel, Customer.class));
	}

	@Transactional
	@Override
	public int createSelective(CustomerModel customerModel) {
		return customerRepo.insertSelective(beanMapper.map(customerModel, Customer.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return customerRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public CustomerModel findByPrimaryKey(Long id) {
		Customer customer = customerRepo.selectByPrimaryKey(id);
		return beanMapper.map(customer, CustomerModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(CustomerModel customerModel) {
		return customerRepo.selectCount(beanMapper.map(customerModel, Customer.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<CustomerModel> selectPage(CustomerModel customerModel,Pageable pageable) {
		Customer customer = beanMapper.map(customerModel, Customer.class);
		return beanMapper.mapAsList(customerRepo.selectPage(customer,pageable),CustomerModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(CustomerModel customerModel) {
		return customerRepo.updateByPrimaryKey(beanMapper.map(customerModel, Customer.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(CustomerModel customerModel) {
		return customerRepo.updateByPrimaryKeySelective(beanMapper.map(customerModel, Customer.class));
	}

}
