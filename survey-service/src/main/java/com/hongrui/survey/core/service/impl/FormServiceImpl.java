package com.hongrui.survey.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.hongrui.survey.core.entity.Form;
import com.hongrui.survey.core.repository.FormRepository;
import com.hongrui.survey.core.model.FormModel;
import com.hongrui.survey.core.service.FormService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private FormRepository formRepo;

	@Transactional
	@Override
	public int create(FormModel formModel) {
		return formRepo.insert(beanMapper.map(formModel, Form.class));
	}

	@Transactional
	@Override
	public int createSelective(FormModel formModel) {
		return formRepo.insertSelective(beanMapper.map(formModel, Form.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Long id) {
		return formRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public FormModel findByPrimaryKey(Long id) {
		Form form = formRepo.selectByPrimaryKey(id);
		return beanMapper.map(form, FormModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(FormModel formModel) {
		return formRepo.selectCount(beanMapper.map(formModel, Form.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<FormModel> selectPage(FormModel formModel,Pageable pageable) {
		Form form = beanMapper.map(formModel, Form.class);
		return beanMapper.mapAsList(formRepo.selectPage(form,pageable),FormModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(FormModel formModel) {
		return formRepo.updateByPrimaryKey(beanMapper.map(formModel, Form.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(FormModel formModel) {
		return formRepo.updateByPrimaryKeySelective(beanMapper.map(formModel, Form.class));
	}

}
