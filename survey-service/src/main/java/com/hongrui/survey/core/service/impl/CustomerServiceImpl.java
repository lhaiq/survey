package com.hongrui.survey.core.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Customer;
import com.hongrui.survey.core.repository.CustomerRepository;
import com.hongrui.survey.core.model.CustomerModel;
import com.hongrui.survey.core.service.CustomerService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public List<CustomerModel> selectPage(CustomerModel customerModel, Pageable pageable) {
        Customer customer = beanMapper.map(customerModel, Customer.class);
        return beanMapper.mapAsList(customerRepo.selectPage(customer, pageable), CustomerModel.class);
    }

    public Page<CustomerModel> searchPage(CustomerModel customerModel, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        String name = customerModel.getName();
        sql.append("select * from customer where 1=1 ");
        StringBuffer countSql = new StringBuffer();
        countSql.append("select count(1) from customer where 1=1  ");
        if (StringUtils.isNotEmpty(name)) {
            sql.append(" and  name like '%" + name + "%'");
            countSql.append(" and  name like '%" + name + "%'");

        }
        List<Object> pageParam = new ArrayList<>();
        List<Object> countParam = new ArrayList<>();

        if(null!=customerModel.getSyndicId()){
            sql.append(" and  syndic_id = ? \n");
            countSql.append(" and  syndic_id = ? \n");
            pageParam.add(customerModel.getSyndicId());
            countParam.add(customerModel.getSyndicId());
        }

        sql.append("order by id desc\n" +
                "limit ?,?");
        pageParam.add(pageable.getOffset());
        pageParam.add(pageable.getPageSize());




        List<CustomerModel> content = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(CustomerModel.class),
                pageParam.toArray());
        long count = jdbcTemplate.queryForObject(countSql.toString(), Long.class,countParam.toArray());
        Page<CustomerModel> page = new PageImpl<>(content, pageable, count);

        return page;
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
